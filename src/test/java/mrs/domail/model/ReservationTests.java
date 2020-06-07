package mrs.domail.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import mrs.domain.model.RoleName;
import mrs.domain.model.mybatis.Reservation;
import mrs.domain.model.mybatis.Usr;

class ReservationTests {

	@Test
	void overlap_ReservableRoomIdが違う場合falseを返却すること() {
		var source = new Reservation() {
			{
				setRoomId(1);
				setReservedDate(LocalDate.now());
			}
		};

		var target = new Reservation() {
			{
				setRoomId(2);
				setReservedDate(LocalDate.now());
			}
		};

		assertFalse(source.overlap(target));
		assertFalse(target.overlap(source));
	}

	@Test
	void overlap_時刻が重ならない場合falseを返却すること() {
		var source = new Reservation() {
			{
				setRoomId(1);
				setReservedDate(LocalDate.now());
				setStartTime(LocalTime.of(1, 0));
				setEndTime(LocalTime.of(2, 0));
			}
		};

		var target = new Reservation() {
			{
				setRoomId(source.getRoomId());
				setReservedDate(source.getReservedDate());
				setStartTime(LocalTime.of(3, 0));
				setEndTime(LocalTime.of(4, 0));
			}
		};

		assertFalse(source.overlap(target));
		assertFalse(target.overlap(source));
	}

	@Test
	void overlap_開始時刻と終了時刻が同じ場合falseを返却すること() {
		var source = new Reservation() {
			{
				setRoomId(1);
				setReservedDate(LocalDate.now());
				setStartTime(LocalTime.of(1, 0));
				setEndTime(LocalTime.of(2, 0));
			}
		};

		var target = new Reservation() {
			{
				setRoomId(source.getRoomId());
				setReservedDate(source.getReservedDate());
				setStartTime(LocalTime.of(2, 0));
				setEndTime(LocalTime.of(3, 0));
			}
		};

		assertFalse(source.overlap(target));
		assertFalse(target.overlap(source));
	}

	@Test
	void overlap_開始時刻よりも終了時刻が重なる場合trueを返却すること() {
		var source = new Reservation() {
			{
				setRoomId(1);
				setReservedDate(LocalDate.now());
				setStartTime(LocalTime.of(1, 0));
				setEndTime(LocalTime.of(2, 0));
			}
		};

		var target = new Reservation() {
			{
				setRoomId(source.getRoomId());
				setReservedDate(source.getReservedDate());
				setStartTime(LocalTime.of(1, 30));
				setEndTime(LocalTime.of(3, 0));
			}
		};

		assertTrue(source.overlap(target));
		assertTrue(target.overlap(source));
	}

	@Test
	void overlap_予約時刻が範囲内の場合trueを返却すること() {
		var source = new Reservation() {
			{
				setRoomId(1);
				setReservedDate(LocalDate.now());
				setStartTime(LocalTime.of(1, 0));
				setEndTime(LocalTime.of(3, 0));
			}
		};

		var target = new Reservation() {
			{
				setRoomId(source.getRoomId());
				setReservedDate(source.getReservedDate());
				setStartTime(LocalTime.of(1, 30));
				setEndTime(LocalTime.of(2, 30));
			}
		};

		assertTrue(source.overlap(target));
		assertTrue(target.overlap(source));
	}

	@Test
	void enabledCancel_RoleNameがADMINの場合trueを返却すること() {
		var user = new Usr();
		user.setRoleName(RoleName.ADMIN.name());

		var reservation = new Reservation();
		assertTrue(reservation.enabledCancel(user));
	}

	@Test
	void enabledCancel_userIdが同じ場合trueを返却すること() {
		var user = new Usr() {
			{
				setUserId("test");
				setRoleName(RoleName.USER.name());
			}
		};

		var reservation = new Reservation() {
			{
				setUserId("test");
			}
		};
		assertTrue(reservation.enabledCancel(user));
	}

	@Test
	void enabledCancel_userIdが異なる場合falseを返却すること() {
		var user = new Usr() {
			{
				setUserId("test");
				setRoleName(RoleName.USER.name());
			}
		};
		var reservation = new Reservation() {
			{
				setUserId("reservation");
			}
		};
		assertFalse(reservation.enabledCancel(user));
	}

}
