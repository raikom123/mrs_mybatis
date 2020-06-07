package mrs.domain.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mrs.domain.model.ReservableRoom;

@Mapper
public interface RoomsMapper {

	List<ReservableRoom> selectReservationRoomList(@Param("reserved_date") LocalDate date);

}
