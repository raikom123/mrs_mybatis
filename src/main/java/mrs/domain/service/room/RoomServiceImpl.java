package mrs.domain.service.room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.mapper.RoomsMapper;
import mrs.domain.mapper.mybatis.MeetingRoomMapper;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.mybatis.MeetingRoom;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	private RoomsMapper roomsMapper;

	private MeetingRoomMapper meetingRoomMapper;

	@Autowired
	public RoomServiceImpl(RoomsMapper roomsMapper,
			MeetingRoomMapper meetingRoomMapper) {
		this.roomsMapper = roomsMapper;
		this.meetingRoomMapper = meetingRoomMapper;
	}

	@Override
	public List<ReservableRoom> findReservableRoomList(LocalDate date) {
		return roomsMapper.selectReservationRoomList(date);
	}

	@Override
	public MeetingRoom findMeetingRoom(Integer roomId) {
		return Optional.ofNullable(meetingRoomMapper.selectByPrimaryKey(roomId))
				.orElseThrow(() -> new EntityNotFoundException("部屋が取得できませんでした。"));
	}

}
