package mrs.domain.service.reservation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.User;
import mrs.domain.repository.MeetingRoomRepository;
import mrs.domain.repository.ReservableRoomRepository;
import mrs.domain.repository.ReservationRepository;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private ReservableRoomRepository reservableRoomRepository;

    private MeetingRoomRepository meetingRoomRepostory;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
            ReservableRoomRepository reservableRoomRepository,
            MeetingRoomRepository meetingRoomRepostory) {
        this.reservationRepository = reservationRepository;
        this.reservableRoomRepository = reservableRoomRepository;
        this.meetingRoomRepostory = meetingRoomRepostory;
    }

    @Override
    public List<Reservation> findReservationList(ReservableRoomId reservableRoomId) {
        return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
    }

    @Override
    public Reservation reserve(Reservation reservation) {
        ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();

        // 対象の部屋が予約可能かどうかをチェック
        reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId).orElseThrow(
                () -> new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。"));

        // 重複チェック
        boolean overlap = reservationRepository
                .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
                .stream()
                .anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancel(Integer reservationId, User requestUser) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new EntityNotFoundException("指定された予約が見つかりません。"));
        if (reservation.enabledCancel(requestUser)) {
            reservationRepository.delete(reservation);
        } else {
            throw new AccessDeniedException("要求されたキャンセルは許可できません。");
        }
    }

    @Override
    public MeetingRoom findMeetingRoom(Integer roomId) {
        return meetingRoomRepostory.findById(roomId).orElseThrow(
                () -> new EntityNotFoundException("指定された部屋が見つかりません。"));
    }

    @Override
    @PreAuthorize("reservation.enabledCancel(principal.user)")
    public void cancel(@P("reservation") Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation findOne(Integer reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(
                () -> new EntityNotFoundException("指定された予約が取得できません。"));
    }

}
