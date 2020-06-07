package mrs.domain.service.room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import mrs.domain.mapper.RoomsMapper;
import mrs.domain.mapper.mybatis.MeetingRoomMapper;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.mybatis.MeetingRoom;

class RoomServiceImplTests {

	@Test
	void findReservableRoomList_Mapperから取得した結果が返却されること() {
		var actual = List.of(reservableRoom(1, "test1"), reservableRoom(2, "test2"));

		// モック作成
		RoomsMapper reservableRoomMapper = mock(RoomsMapper.class);
		when(reservableRoomMapper.selectReservationRoomList(any())).thenReturn(actual);

		RoomServiceImpl roomServiceImpl = new RoomServiceImpl(reservableRoomMapper, null);
		var expected = roomServiceImpl.findReservableRoomList(LocalDate.now());

		assertEquals(expected, actual);
	}

	@Test
	void findMeetingRoom_データが取得できない場合Exceptionがthrowされること() {
		// モック作成
		MeetingRoomMapper meetingRoomMapper = mock(MeetingRoomMapper.class);
		when(meetingRoomMapper.selectByPrimaryKey(any())).thenReturn(null);

		assertThrows(RuntimeException.class, () -> {
			RoomServiceImpl roomServiceImpl = new RoomServiceImpl(null, meetingRoomMapper);
			roomServiceImpl.findMeetingRoom(any());
		}, "部屋が取得できませんでした。");
	}

	@Test
	void findMeetingRoom_Mapperから取得した結果が返却されること() {
		MeetingRoom actual = meetingRoom(1, "test");
		// モック作成
		MeetingRoomMapper meetingRoomMapper = mock(MeetingRoomMapper.class);
		when(meetingRoomMapper.selectByPrimaryKey(any())).thenReturn(actual);

		RoomServiceImpl roomServiceImpl = new RoomServiceImpl(null, meetingRoomMapper);
		MeetingRoom expected = roomServiceImpl.findMeetingRoom(any());

		assertEquals(expected, actual);
	}

	private ReservableRoom reservableRoom(Integer roomId, String roomName) {
		return new ReservableRoom() {{
			setRoomId(roomId);
			setReservedDate(LocalDate.now());
			setRoomName(roomName);
		}};
	}

	private MeetingRoom meetingRoom(Integer roomId, String roomName) {
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setRoomId(roomId);
		meetingRoom.setRoomName(roomName);
		return meetingRoom;
	}

}
