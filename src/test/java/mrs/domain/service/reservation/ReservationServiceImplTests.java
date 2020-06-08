package mrs.domain.service.reservation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import mrs.domain.mapper.ReservationsMapper;
import mrs.domain.mapper.mybatis.MeetingRoomMapper;
import mrs.domain.mapper.mybatis.ReservationMapper;
import mrs.domain.model.ReservationEx;
import mrs.domain.model.RoleName;
import mrs.domain.model.mybatis.MeetingRoom;
import mrs.domain.model.mybatis.ReservableRoomKey;
import mrs.domain.model.mybatis.Reservation;
import mrs.domain.model.mybatis.Usr;

class ReservationServiceImplTests {

	@Test
	void findReservationList_Mapperの結果がそのまま返却されること() {
		var actual = List.of(reservationEx(1, user(), 9), reservationEx(2, admin(), 10));

		// モック作成
		var reservationsMapper = mock(ReservationsMapper.class);
		when(reservationsMapper.selectReservationExList(any(), any())).thenReturn(actual);

		var reservationServiceImpl = new ReservationServiceImpl(null, null, reservationsMapper);
		var expected = reservationServiceImpl.findReservationList(1, LocalDate.now());

		assertEquals(expected, actual);
	}

	@Test
	void reserve_ReservableRoomIdでデータが取得できない場合Exceptionがthrowされること() {
		// モック作成
		var reservationsMapper = mock(ReservationsMapper.class);
		when(reservationsMapper.selectReservableRoomForUpdate(any(), any())).thenReturn(null);

		assertThrows(UnavailableReservationException.class, () -> {
			ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(null, null, reservationsMapper);
			reservationServiceImpl.reserve(reservation(1, user(), 9));
		}, "入力の日付・部屋の組み合わせは予約できません。");
	}

	@Test
	void reserve_登録済のデータと重複している場合Exceptionがthrowされること() {
		var reservation = reservation(1, user(), 9);

		// モック作成
		var reservationsMapper = mock(ReservationsMapper.class);
		when(reservationsMapper.selectReservableRoomForUpdate(any(), any())).thenReturn(reservableRoomKey(reservation));
		var reservationMapper = mock(ReservationMapper.class);
		when(reservationMapper.selectByExample(any())).thenReturn(List.of(reservation));

		assertThrows(AlreadyReservedException.class, () -> {
			var reservationServiceImpl = new ReservationServiceImpl(reservationMapper, null, reservationsMapper);
			reservationServiceImpl.reserve(reservation);
		}, "入力の時間帯はすでに予約済みです。");
	}

	@Test
	void reserve_登録できた場合saveの戻り値が返却されること() {
		var actual = reservation(1, user(), 10);

		// モック作成
		var reservationsMapper = mock(ReservationsMapper.class);
		when(reservationsMapper.selectReservableRoomForUpdate(any(), any())).thenReturn(reservableRoomKey(actual));
		var reservationMapper = mock(ReservationMapper.class);
		when(reservationMapper.selectByExample(any())).thenReturn(List.of());
		when(reservationMapper.insert(any())).thenReturn(1);

		var reservationServiceImpl = new ReservationServiceImpl(reservationMapper, null, reservationsMapper);
		var expected = reservationServiceImpl.reserve(actual);

		assertEquals(expected, actual);

		// reservationMapper#save が1度呼び出されたことを確認
		verify(reservationMapper, times(1)).insert(actual);
	}

	@Test
	void cancel_予約が見つからない場合Exceptionをthrowすること() {
		// モックを作成
		var reservationMapper = mock(ReservationMapper.class);
		when(reservationMapper.selectByPrimaryKey(any())).thenReturn(null);

		assertThrows(EntityNotFoundException.class, () -> {
			var reservationServiceImpl = new ReservationServiceImpl(reservationMapper, null, null);
			reservationServiceImpl.cancel(1, user());
		}, "指定された予約が見つかりません。");
	}

	@Test
	void cancel_キャンセルする権限がない場合Exceptionをthrowすること() {
		Reservation reservation = reservation(1, admin(), 9);

		// モックを作成
		var reservationMapper = mock(ReservationMapper.class);
		when(reservationMapper.selectByPrimaryKey(any())).thenReturn(reservation);

		assertThrows(AccessDeniedException.class, () -> {
			var reservationServiceImpl = new ReservationServiceImpl(reservationMapper, null, null);
			reservationServiceImpl.cancel(2, user());
		}, "要求されたキャンセルは許可できません。");
	}

	@Test
	void cancel_キャンセルした場合deleteが呼び出されること() {
		Usr user = admin();
		Reservation reservation = reservation(1, user, 9);

		// モックを作成
		var reservationMapper = mock(ReservationMapper.class);
		when(reservationMapper.selectByPrimaryKey(any())).thenReturn(reservation);
		when(reservationMapper.deleteByPrimaryKey(any())).thenReturn(1);

		var reservationServiceImpl = new ReservationServiceImpl(reservationMapper, null, null);
		reservationServiceImpl.cancel(reservation.getReservationId(), user);

		verify(reservationMapper, times(1)).deleteByPrimaryKey(any());
	}

	@Test
	void findMeetingRoom_部屋が取得できない場合Exceptionがthrowされること() {
		// モック作成
		MeetingRoomMapper meetingRoomMapper = mock(MeetingRoomMapper.class);
		when(meetingRoomMapper.selectByPrimaryKey(any())).thenReturn(null);

		assertThrows(EntityNotFoundException.class, () -> {
			var reservationServiceImpl = new ReservationServiceImpl(null, meetingRoomMapper, null);
			reservationServiceImpl.findMeetingRoom(any());
		}, "指定された部屋が見つかりません。");

	}

	@Test
	void findMeetingRoom_部屋が取得できた場合Mapperから取得した結果が返却されること() {
		var actual = meetingRoom(1, "test");

		// モック作成
		MeetingRoomMapper meetingRoomMapper = mock(MeetingRoomMapper.class);
		when(meetingRoomMapper.selectByPrimaryKey(anyInt())).thenReturn(actual);

		var reservationServiceImpl = new ReservationServiceImpl(null, meetingRoomMapper, null);
		var expected = reservationServiceImpl.findMeetingRoom(anyInt());

		assertEquals(expected, actual);
	}

	private Reservation reservation(Integer reservationId, Usr user, int hour) {
		return new Reservation() {
			{
				setReservationId(1);
				setReservedDate(LocalDate.now());
				setRoomId(1);
				setUserId(user.getUserId());
				setStartTime(LocalTime.of(hour, 00));
				setEndTime(LocalTime.of(hour, 30));
			}
		};
	}

	private ReservationEx reservationEx(Integer reservationId, Usr user, int hour) {
		return new ReservationEx() {
			{
				setReservationId(1);
				setReservedDate(LocalDate.now());
				setRoomId(1);
				setUserId(user.getUserId());
				setStartTime(LocalTime.of(hour, 00));
				setEndTime(LocalTime.of(hour, 30));
				setFirstName(user.getFirstName());
				setLastName(user.getLastName());
			}
		};
	}

	private MeetingRoom meetingRoom(Integer roomId, String roomName) {
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setRoomId(roomId);
		meetingRoom.setRoomName(roomName);
		return meetingRoom;
	}

	private Usr user() {
		return new Usr() {
			{
				setFirstName("test");
				setLastName("user");
				setPassword("pass");
				setRoleName(RoleName.USER);
				setUserId("test-user");
			}
		};
	}

	private Usr admin() {
		return new Usr() {
			{
				setFirstName("test");
				setLastName("admin");
				setPassword("word");
				setRoleName(RoleName.ADMIN);
				setUserId("test-admin");
			}
		};
	}

	private ReservableRoomKey reservableRoomKey(Reservation reservation) {
		return new ReservableRoomKey() {{
			setReservedDate(reservation.getReservedDate());
			setRoomId(reservation.getRoomId());
		}};
	}

}
