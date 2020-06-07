package mrs.domain.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mrs.domain.model.ReservationEx;
import mrs.domain.model.mybatis.ReservableRoomKey;

@Mapper
public interface ReservationsMapper {

	List<ReservationEx> selectReservationExList(@Param("room_id") Integer roomId, @Param("reserved_date") LocalDate reservedDate);

	ReservableRoomKey selectReservableRoomForUpdate(@Param("room_id") Integer roomId, @Param("reserved_date") LocalDate reservedDate);
}