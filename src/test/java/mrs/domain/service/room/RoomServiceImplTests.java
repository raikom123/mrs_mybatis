package mrs.domain.service.room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.repository.MeetingRoomRepository;
import mrs.domain.repository.ReservableRoomRepository;

class RoomServiceImplTests {

    @Test
    void findReservableRoomList_Repositoryから取得した結果が返却されること() {
        List<ReservableRoom> actual = List.of(reservableRoom(1, "test1"), reservableRoom(2, "test2"));

        // モック作成
        ReservableRoomRepository reservableRoomRepository = mock(ReservableRoomRepository.class);
        when(reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(any()))
                .thenReturn(actual);

        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(reservableRoomRepository, null);
        List<ReservableRoom> expected = roomServiceImpl.findReservableRoomList(LocalDate.now());

        assertEquals(expected, actual);
    }

    @Test
    void findMeetingRoom_データが取得できない場合Exceptionがthrowされること() {
        // モック作成
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        when(meetingRoomRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            RoomServiceImpl roomServiceImpl = new RoomServiceImpl(null, meetingRoomRepository);
            roomServiceImpl.findMeetingRoom(any());
        }, "部屋が取得できませんでした。");
    }

    @Test
    void findMeetingRoom_Repositoryから取得した結果が返却されること() {
        MeetingRoom actual = meetingRoom(1, "test");
        // モック作成
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        when(meetingRoomRepository.findById(any())).thenReturn(Optional.of(actual));

        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(null, meetingRoomRepository);
        MeetingRoom expected = roomServiceImpl.findMeetingRoom(any());

        assertEquals(expected, actual);
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

}
