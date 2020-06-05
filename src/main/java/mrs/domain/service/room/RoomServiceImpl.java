package mrs.domain.service.room;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.repository.MeetingRoomRepository;
import mrs.domain.repository.ReservableRoomRepository;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private ReservableRoomRepository reservableRoomRepository;

    private MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public RoomServiceImpl(ReservableRoomRepository reservableRoomRepository,
            MeetingRoomRepository meetingRoomRepository) {
        this.reservableRoomRepository = reservableRoomRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    @Override
    public List<ReservableRoom> findReservableRoomList(LocalDate date) {
        return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
    }

    @Override
    public MeetingRoom findMeetingRoom(Integer roomId) {
        return meetingRoomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("部屋が取得できませんでした。"));
    }

}
