package mrs.domain.service.reservation;

import java.util.List;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.User;

public interface ReservationService {

    List<Reservation> findReservationList(ReservableRoomId reservableRoomId);

    Reservation reserve(Reservation reservation);

    void cancel(Integer reservationId, User requestUser);

    void cancel(Reservation reservation);

    Reservation findOne(Integer reservationId);

    MeetingRoom findMeetingRoom(Integer roomId);

}
