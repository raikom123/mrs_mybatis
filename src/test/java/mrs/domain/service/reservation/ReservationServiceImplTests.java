package mrs.domain.service.reservation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.RoleName;
import mrs.domain.model.User;
import mrs.domain.repository.MeetingRoomRepository;
import mrs.domain.repository.ReservableRoomRepository;
import mrs.domain.repository.ReservationRepository;

class ReservationServiceImplTests {

    @Test
    void findReservationList_Repositoryの結果がそのまま返却されること() {
        var actual = List.of(reservation(1, user(), 9), reservation(2, admin(), 10));

        // モック作成
        var reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(any())).thenReturn(actual);

        var reservationServiceImpl = new ReservationServiceImpl(reservationRepository, null, null);
        var expected = reservationServiceImpl.findReservationList(any());

        assertEquals(expected, actual);
    }

    @Test
    void reserve_ReservableRoomIdでデータが取得できない場合Exceptionがthrowされること() {
        // モック作成
        var reservableRoomRepository = mock(ReservableRoomRepository.class);
        when(reservableRoomRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnavailableReservationException.class, () -> {
            ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(null, reservableRoomRepository,
                    null);
            reservationServiceImpl.reserve(reservation(1, user(), 9));
        }, "入力の日付・部屋の組み合わせは予約できません。");
    }

    @Test
    void reserve_登録済のデータと重複している場合Exceptionがthrowされること() {
        var reservation = reservation(1, user(), 9);

        // モック作成
        var reservableRoomRepository = mock(ReservableRoomRepository.class);
        when(reservableRoomRepository.findOneForUpdateByReservableRoomId(any())).thenReturn(Optional.of(reservation.getReservableRoom()));
        var reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(any()))
                .thenReturn(List.of(reservation));

        assertThrows(AlreadyReservedException.class, () -> {
            var reservationServiceImpl = new ReservationServiceImpl(reservationRepository,
                    reservableRoomRepository, null);
            reservationServiceImpl.reserve(reservation);
        }, "入力の時間帯はすでに予約済みです。");
    }

    @Test
    void reserve_登録できた場合saveの戻り値が返却されること() {
        var input = reservation(1, user(), 9);
        var actual = reservation(2, user(), 10);

        // モック作成
        var reservableRoomRepository = mock(ReservableRoomRepository.class);
        when(reservableRoomRepository.findOneForUpdateByReservableRoomId(any())).thenReturn(Optional.of(input.getReservableRoom()));
        var reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(any()))
                .thenReturn(List.of());
        when(reservationRepository.save(any())).thenReturn(actual);

        var reservationServiceImpl = new ReservationServiceImpl(reservationRepository,
                reservableRoomRepository, null);
        var expected = reservationServiceImpl.reserve(input);

        assertEquals(expected, actual);

        // reservationRepository#save が1度呼び出されたことを確認
        verify(reservationRepository, times(1)).save(input);
    }

    @Test
    void cancel_予約が見つからない場合Exceptionをthrowすること() {
        // モックを作成
        var reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            var reservationServiceImpl = new ReservationServiceImpl(reservationRepository,
                    null, null);
            reservationServiceImpl.cancel(1, user());
        }, "指定された予約が見つかりません。");
    }

    @Test
    void cancel_キャンセルする権限がない場合Exceptionをthrowすること() {
        Reservation reservation = reservation(1, admin(), 9);

        // モックを作成
        var reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findById(any())).thenReturn(Optional.of(reservation));

        assertThrows(AccessDeniedException.class, () -> {
            ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(reservationRepository,
                    null, null);
            reservationServiceImpl.cancel(2, user());
        }, "要求されたキャンセルは許可できません。");
    }

    @Test
    void cancel_キャンセルした場合deleteが呼び出されること() {
        Reservation reservation = reservation(1, admin(), 9);

        // モックを作成
        var reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findById(any())).thenReturn(Optional.of(reservation));
        doNothing().when(reservationRepository).delete(any());

        ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(reservationRepository,
                null, null);
        reservationServiceImpl.cancel(reservation.getReservationId(), reservation.getUser());

        verify(reservationRepository, times(1)).delete(any());
    }

    @Test
    void findMeetingRoom_部屋が取得できない場合Exceptionがthrowされること() {
        // モック作成
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        when(meetingRoomRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(null,
                    null, meetingRoomRepository);
            reservationServiceImpl.findMeetingRoom(any());
        }, "指定された部屋が見つかりません。");

    }

    @Test
    void findMeetingRoom_部屋が取得できた場合Repositoryから取得した結果が返却されること() {
        MeetingRoom actual = meetingRoom(1, "test");

        // モック作成
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        when(meetingRoomRepository.findById(any())).thenReturn(Optional.of(actual));

        ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(null,
                null, meetingRoomRepository);
        MeetingRoom expected = reservationServiceImpl.findMeetingRoom(any());

        assertEquals(expected, actual);
    }

    private Reservation reservation(Integer reservationId, User user, int hour) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setReservableRoom(reservableRoom(1, "test room 1"));
        reservation.setUser(user);
        reservation.setStartTime(LocalTime.of(hour, 00));
        reservation.setEndTime(LocalTime.of(hour, 30));
        return reservation;
    }

    private ReservableRoom reservableRoom(Integer roomId, String roomName) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, LocalDate.now());
        ReservableRoom reservableRoom = new ReservableRoom(reservableRoomId);
        reservableRoom.setMeetingRoom(meetingRoom(roomId, roomName));
        return reservableRoom;
    }

    private MeetingRoom meetingRoom(Integer roomId, String roomName) {
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setRoomId(roomId);
        meetingRoom.setRoomName(roomName);
        return meetingRoom;
    }

    private User user() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("user");
        user.setPassword("pass");
        user.setRoleName(RoleName.USER);
        user.setUserId("test-user");
        return user;
    }

    private User admin() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("admin");
        user.setPassword("word");
        user.setRoleName(RoleName.ADMIN);
        user.setUserId("test-admin");
        return user;
    }

}
