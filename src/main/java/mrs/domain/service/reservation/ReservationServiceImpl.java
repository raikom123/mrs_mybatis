package mrs.domain.service.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.mapper.ReservationsMapper;
import mrs.domain.mapper.mybatis.MeetingRoomMapper;
import mrs.domain.mapper.mybatis.ReservationMapper;
import mrs.domain.model.ReservationEx;
import mrs.domain.model.mybatis.MeetingRoom;
import mrs.domain.model.mybatis.Reservation;
import mrs.domain.model.mybatis.ReservationExample;
import mrs.domain.model.mybatis.Usr;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	private ReservationMapper reservationMapper;

	private MeetingRoomMapper meetingRoomMapper;

	private ReservationsMapper reservationsMapper;

	@Autowired
	public ReservationServiceImpl(ReservationMapper reservationMapper,
			MeetingRoomMapper meetingRoomMapper,
			ReservationsMapper reservationsMapper) {
		this.reservationMapper = reservationMapper;
		this.meetingRoomMapper = meetingRoomMapper;
		this.reservationsMapper = reservationsMapper;
	}

	@Override
	public List<ReservationEx> findReservationList(Integer roomId, LocalDate date) {
		return reservationsMapper.selectReservationExList(roomId, date);
	}

	@Override
	public Reservation reserve(Reservation reservation) {
		// 対象の部屋が予約可能かどうかをチェック
		Optional.ofNullable(reservationsMapper.selectReservableRoomForUpdate(reservation.getRoomId(), reservation.getReservedDate()))
				.orElseThrow(() -> new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。"));

		// 重複チェック
		boolean overlap = reservationMapper.selectByExample(new ReservationExample() {
			{
				createCriteria()
						.andRoomIdEqualTo(reservation.getRoomId())
						.andReservedDateEqualTo(reservation.getReservedDate());
			}
		}).stream().anyMatch(x -> x.overlap(reservation));
		if (overlap) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
		}

		reservationMapper.insert(reservation);
		return reservation;
	}

	@Override
	public Reservation update(Reservation reservation) {
		// 対象の部屋が予約可能かどうかをチェック
		Optional.ofNullable(reservationsMapper.selectReservableRoomForUpdate(reservation.getRoomId(), reservation.getReservedDate()))
				.orElseThrow(() -> new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。"));

		boolean overlap = reservationMapper.selectByExample(new ReservationExample() {
			{
				createCriteria()
						.andRoomIdEqualTo(reservation.getRoomId())
						.andReservedDateEqualTo(reservation.getReservedDate())
						.andReservationIdNotEqualTo(reservation.getReservationId());
			}
		}).stream().anyMatch(x -> x.overlap(reservation));
		if (overlap) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
		}

		reservationMapper.updateByPrimaryKeySelective(reservation);
		return reservation;
	}

	@Override
	public void cancel(Integer reservationId, Usr requestUser) {
		Reservation reservation = Optional.ofNullable(reservationMapper.selectByPrimaryKey(reservationId)).orElseThrow(
				() -> new EntityNotFoundException("指定された予約が見つかりません。"));
		if (reservation.enabledCancel(requestUser)) {
			reservationMapper.deleteByPrimaryKey(reservationId);
		} else {
			throw new AccessDeniedException("要求されたキャンセルは許可できません。");
		}
	}

	@Override
	public MeetingRoom findMeetingRoom(Integer roomId) {
		return Optional.ofNullable(meetingRoomMapper.selectByPrimaryKey(roomId)).orElseThrow(
				() -> new EntityNotFoundException("指定された部屋が見つかりません。"));
	}

}
