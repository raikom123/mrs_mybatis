package mrs.domail.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.RoleName;
import mrs.domain.model.User;

class ReservationTests {

    @Test
    void overlap_ReservableRoomIdが違う場合falseを返却すること() {
        Reservation source = new Reservation();
        ReservableRoomId sourceReservableRoomId = new ReservableRoomId(1, LocalDate.now());
        source.setReservableRoom(new ReservableRoom(sourceReservableRoomId));

        Reservation target = new Reservation();
        ReservableRoomId targetReservableRoomId = new ReservableRoomId(2, LocalDate.now());
        target.setReservableRoom(new ReservableRoom(targetReservableRoomId));

        assertFalse(source.overlap(target));
        assertFalse(target.overlap(source));
    }

    @Test
    void overlap_時刻が重ならない場合falseを返却すること() {
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.now());
        Reservation source = new Reservation();
        source.setReservableRoom(new ReservableRoom(reservableRoomId));
        source.setStartTime(LocalTime.of(1, 0));
        source.setEndTime(LocalTime.of(2, 0));

        Reservation target = new Reservation();
        target.setReservableRoom(new ReservableRoom(reservableRoomId));
        target.setStartTime(LocalTime.of(3, 0));
        target.setEndTime(LocalTime.of(4, 0));

        assertFalse(source.overlap(target));
        assertFalse(target.overlap(source));
    }

    @Test
    void overlap_開始時刻と終了時刻が同じ場合falseを返却すること() {
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.now());
        Reservation source = new Reservation();
        source.setReservableRoom(new ReservableRoom(reservableRoomId));
        source.setStartTime(LocalTime.of(1, 0));
        source.setEndTime(LocalTime.of(2, 0));

        Reservation target = new Reservation();
        target.setReservableRoom(new ReservableRoom(reservableRoomId));
        target.setStartTime(LocalTime.of(2, 0));
        target.setEndTime(LocalTime.of(3, 0));

        assertFalse(source.overlap(target));
        assertFalse(target.overlap(source));
    }

    @Test
    void overlap_開始時刻よりも終了時刻が重なる場合trueを返却すること() {
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.now());
        Reservation source = new Reservation();
        source.setReservableRoom(new ReservableRoom(reservableRoomId));
        source.setStartTime(LocalTime.of(1, 0));
        source.setEndTime(LocalTime.of(2, 0));

        Reservation target = new Reservation();
        target.setReservableRoom(new ReservableRoom(reservableRoomId));
        target.setStartTime(LocalTime.of(1, 30));
        target.setEndTime(LocalTime.of(3, 0));

        assertTrue(source.overlap(target));
        assertTrue(target.overlap(source));
    }

    @Test
    void overlap_予約時刻が範囲内の場合trueを返却すること() {
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.now());
        Reservation source = new Reservation();
        source.setReservableRoom(new ReservableRoom(reservableRoomId));
        source.setStartTime(LocalTime.of(1, 0));
        source.setEndTime(LocalTime.of(3, 0));

        Reservation target = new Reservation();
        target.setReservableRoom(new ReservableRoom(reservableRoomId));
        target.setStartTime(LocalTime.of(1, 30));
        target.setEndTime(LocalTime.of(2, 30));

        assertTrue(source.overlap(target));
        assertTrue(target.overlap(source));
    }

    @Test
    void enabledCancel_RoleNameがADMINの場合trueを返却すること() {
        User user = new User();
        user.setRoleName(RoleName.ADMIN);

        Reservation reservation = new Reservation();
        assertTrue(reservation.enabledCancel(user));
    }

    @Test
    void enabledCancel_userIdが同じ場合trueを返却すること() {
        User user = new User();
        user.setUserId("test");
        user.setRoleName(RoleName.USER);

        Reservation reservation = new Reservation();
        User reservationUser = new User();
        reservationUser.setUserId("test");
        reservation.setUser(reservationUser);
        assertTrue(reservation.enabledCancel(user));
    }

    @Test
    void enabledCancel_userIdが異なる場合falseを返却すること() {
        User user = new User();
        user.setUserId("test");
        user.setRoleName(RoleName.USER);

        Reservation reservation = new Reservation();
        User reservationUser = new User();
        reservationUser.setUserId("reservation");
        reservation.setUser(reservationUser);
        assertFalse(reservation.enabledCancel(user));
    }

}
