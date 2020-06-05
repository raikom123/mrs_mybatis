package mrs.domain.service.room;

import java.time.LocalDate;
import java.util.List;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;

public interface RoomService {

    List<ReservableRoom> findReservableRoomList(LocalDate date);

    MeetingRoom findMeetingRoom(Integer roomId);

}
