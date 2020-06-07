package mrs.domain.mapper.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mrs.domain.model.mybatis.Reservation;
import mrs.domain.model.mybatis.ReservationExample;

@Mapper
public interface ReservationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    long countByExample(ReservationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int deleteByExample(ReservationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int deleteByPrimaryKey(Integer reservationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int insert(Reservation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int insertSelective(Reservation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    List<Reservation> selectByExample(ReservationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    Reservation selectByPrimaryKey(Integer reservationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int updateByExampleSelective(@Param("record") Reservation record, @Param("example") ReservationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int updateByExample(@Param("record") Reservation record, @Param("example") ReservationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int updateByPrimaryKeySelective(Reservation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.reservation
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    int updateByPrimaryKey(Reservation record);
}