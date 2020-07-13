package mrs.domain.service.room;

import java.time.LocalDate;
import java.util.List;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.mybatis.MeetingRoom;

public interface RoomService {

  List<ReservableRoom> findReservableRoomList(LocalDate date);

  MeetingRoom findMeetingRoom(Integer roomId);
}
