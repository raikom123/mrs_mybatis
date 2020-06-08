package mrs.domain.model.mybatis;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public ReservationExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andReservationIdIsNull() {
			addCriterion("reservation_id is null");
			return (Criteria) this;
		}

		public Criteria andReservationIdIsNotNull() {
			addCriterion("reservation_id is not null");
			return (Criteria) this;
		}

		public Criteria andReservationIdEqualTo(Integer value) {
			addCriterion("reservation_id =", value, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdNotEqualTo(Integer value) {
			addCriterion("reservation_id <>", value, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdGreaterThan(Integer value) {
			addCriterion("reservation_id >", value, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("reservation_id >=", value, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdLessThan(Integer value) {
			addCriterion("reservation_id <", value, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdLessThanOrEqualTo(Integer value) {
			addCriterion("reservation_id <=", value, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdIn(List<Integer> values) {
			addCriterion("reservation_id in", values, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdNotIn(List<Integer> values) {
			addCriterion("reservation_id not in", values, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdBetween(Integer value1, Integer value2) {
			addCriterion("reservation_id between", value1, value2, "reservationId");
			return (Criteria) this;
		}

		public Criteria andReservationIdNotBetween(Integer value1, Integer value2) {
			addCriterion("reservation_id not between", value1, value2, "reservationId");
			return (Criteria) this;
		}

		public Criteria andStartTimeIsNull() {
			addCriterion("start_time is null");
			return (Criteria) this;
		}

		public Criteria andStartTimeIsNotNull() {
			addCriterion("start_time is not null");
			return (Criteria) this;
		}

		public Criteria andStartTimeEqualTo(LocalTime value) {
			addCriterion("start_time =", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotEqualTo(LocalTime value) {
			addCriterion("start_time <>", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeGreaterThan(LocalTime value) {
			addCriterion("start_time >", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeGreaterThanOrEqualTo(LocalTime value) {
			addCriterion("start_time >=", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeLessThan(LocalTime value) {
			addCriterion("start_time <", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeLessThanOrEqualTo(LocalTime value) {
			addCriterion("start_time <=", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeIn(List<LocalTime> values) {
			addCriterion("start_time in", values, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotIn(List<LocalTime> values) {
			addCriterion("start_time not in", values, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeBetween(LocalTime value1, LocalTime value2) {
			addCriterion("start_time between", value1, value2, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotBetween(LocalTime value1, LocalTime value2) {
			addCriterion("start_time not between", value1, value2, "startTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeIsNull() {
			addCriterion("end_time is null");
			return (Criteria) this;
		}

		public Criteria andEndTimeIsNotNull() {
			addCriterion("end_time is not null");
			return (Criteria) this;
		}

		public Criteria andEndTimeEqualTo(LocalTime value) {
			addCriterion("end_time =", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotEqualTo(LocalTime value) {
			addCriterion("end_time <>", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeGreaterThan(LocalTime value) {
			addCriterion("end_time >", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeGreaterThanOrEqualTo(LocalTime value) {
			addCriterion("end_time >=", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeLessThan(LocalTime value) {
			addCriterion("end_time <", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeLessThanOrEqualTo(LocalTime value) {
			addCriterion("end_time <=", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeIn(List<LocalTime> values) {
			addCriterion("end_time in", values, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotIn(List<LocalTime> values) {
			addCriterion("end_time not in", values, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeBetween(LocalTime value1, LocalTime value2) {
			addCriterion("end_time between", value1, value2, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotBetween(LocalTime value1, LocalTime value2) {
			addCriterion("end_time not between", value1, value2, "endTime");
			return (Criteria) this;
		}

		public Criteria andReservedDateIsNull() {
			addCriterion("reserved_date is null");
			return (Criteria) this;
		}

		public Criteria andReservedDateIsNotNull() {
			addCriterion("reserved_date is not null");
			return (Criteria) this;
		}

		public Criteria andReservedDateEqualTo(LocalDate value) {
			addCriterion("reserved_date =", value, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateNotEqualTo(LocalDate value) {
			addCriterion("reserved_date <>", value, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateGreaterThan(LocalDate value) {
			addCriterion("reserved_date >", value, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateGreaterThanOrEqualTo(LocalDate value) {
			addCriterion("reserved_date >=", value, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateLessThan(LocalDate value) {
			addCriterion("reserved_date <", value, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateLessThanOrEqualTo(LocalDate value) {
			addCriterion("reserved_date <=", value, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateIn(List<LocalDate> values) {
			addCriterion("reserved_date in", values, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateNotIn(List<LocalDate> values) {
			addCriterion("reserved_date not in", values, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateBetween(LocalDate value1, LocalDate value2) {
			addCriterion("reserved_date between", value1, value2, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andReservedDateNotBetween(LocalDate value1, LocalDate value2) {
			addCriterion("reserved_date not between", value1, value2, "reservedDate");
			return (Criteria) this;
		}

		public Criteria andRoomIdIsNull() {
			addCriterion("room_id is null");
			return (Criteria) this;
		}

		public Criteria andRoomIdIsNotNull() {
			addCriterion("room_id is not null");
			return (Criteria) this;
		}

		public Criteria andRoomIdEqualTo(Integer value) {
			addCriterion("room_id =", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdNotEqualTo(Integer value) {
			addCriterion("room_id <>", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdGreaterThan(Integer value) {
			addCriterion("room_id >", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("room_id >=", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdLessThan(Integer value) {
			addCriterion("room_id <", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdLessThanOrEqualTo(Integer value) {
			addCriterion("room_id <=", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdIn(List<Integer> values) {
			addCriterion("room_id in", values, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdNotIn(List<Integer> values) {
			addCriterion("room_id not in", values, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdBetween(Integer value1, Integer value2) {
			addCriterion("room_id between", value1, value2, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdNotBetween(Integer value1, Integer value2) {
			addCriterion("room_id not between", value1, value2, "roomId");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(String value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(String value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(String value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(String value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(String value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLike(String value) {
			addCriterion("user_id like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotLike(String value) {
			addCriterion("user_id not like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<String> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<String> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(String value1, String value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(String value1, String value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andMemoIsNull() {
			addCriterion("memo is null");
			return (Criteria) this;
		}

		public Criteria andMemoIsNotNull() {
			addCriterion("memo is not null");
			return (Criteria) this;
		}

		public Criteria andMemoEqualTo(String value) {
			addCriterion("memo =", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoNotEqualTo(String value) {
			addCriterion("memo <>", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoGreaterThan(String value) {
			addCriterion("memo >", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoGreaterThanOrEqualTo(String value) {
			addCriterion("memo >=", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoLessThan(String value) {
			addCriterion("memo <", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoLessThanOrEqualTo(String value) {
			addCriterion("memo <=", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoLike(String value) {
			addCriterion("memo like", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoNotLike(String value) {
			addCriterion("memo not like", value, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoIn(List<String> values) {
			addCriterion("memo in", values, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoNotIn(List<String> values) {
			addCriterion("memo not in", values, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoBetween(String value1, String value2) {
			addCriterion("memo between", value1, value2, "memo");
			return (Criteria) this;
		}

		public Criteria andMemoNotBetween(String value1, String value2) {
			addCriterion("memo not between", value1, value2, "memo");
			return (Criteria) this;
		}

		public Criteria andMemberCountIsNull() {
			addCriterion("member_count is null");
			return (Criteria) this;
		}

		public Criteria andMemberCountIsNotNull() {
			addCriterion("member_count is not null");
			return (Criteria) this;
		}

		public Criteria andMemberCountEqualTo(Integer value) {
			addCriterion("member_count =", value, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountNotEqualTo(Integer value) {
			addCriterion("member_count <>", value, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountGreaterThan(Integer value) {
			addCriterion("member_count >", value, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("member_count >=", value, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountLessThan(Integer value) {
			addCriterion("member_count <", value, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountLessThanOrEqualTo(Integer value) {
			addCriterion("member_count <=", value, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountIn(List<Integer> values) {
			addCriterion("member_count in", values, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountNotIn(List<Integer> values) {
			addCriterion("member_count not in", values, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountBetween(Integer value1, Integer value2) {
			addCriterion("member_count between", value1, value2, "memberCount");
			return (Criteria) this;
		}

		public Criteria andMemberCountNotBetween(Integer value1, Integer value2) {
			addCriterion("member_count not between", value1, value2, "memberCount");
			return (Criteria) this;
		}

		public Criteria andRemindDateIsNull() {
			addCriterion("remind_date is null");
			return (Criteria) this;
		}

		public Criteria andRemindDateIsNotNull() {
			addCriterion("remind_date is not null");
			return (Criteria) this;
		}

		public Criteria andRemindDateEqualTo(LocalDate value) {
			addCriterion("remind_date =", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateNotEqualTo(LocalDate value) {
			addCriterion("remind_date <>", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateGreaterThan(LocalDate value) {
			addCriterion("remind_date >", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateGreaterThanOrEqualTo(LocalDate value) {
			addCriterion("remind_date >=", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateLessThan(LocalDate value) {
			addCriterion("remind_date <", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateLessThanOrEqualTo(LocalDate value) {
			addCriterion("remind_date <=", value, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateIn(List<LocalDate> values) {
			addCriterion("remind_date in", values, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateNotIn(List<LocalDate> values) {
			addCriterion("remind_date not in", values, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateBetween(LocalDate value1, LocalDate value2) {
			addCriterion("remind_date between", value1, value2, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindDateNotBetween(LocalDate value1, LocalDate value2) {
			addCriterion("remind_date not between", value1, value2, "remindDate");
			return (Criteria) this;
		}

		public Criteria andRemindTimeIsNull() {
			addCriterion("remind_time is null");
			return (Criteria) this;
		}

		public Criteria andRemindTimeIsNotNull() {
			addCriterion("remind_time is not null");
			return (Criteria) this;
		}

		public Criteria andRemindTimeEqualTo(LocalTime value) {
			addCriterion("remind_time =", value, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeNotEqualTo(LocalTime value) {
			addCriterion("remind_time <>", value, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeGreaterThan(LocalTime value) {
			addCriterion("remind_time >", value, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeGreaterThanOrEqualTo(LocalTime value) {
			addCriterion("remind_time >=", value, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeLessThan(LocalTime value) {
			addCriterion("remind_time <", value, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeLessThanOrEqualTo(LocalTime value) {
			addCriterion("remind_time <=", value, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeIn(List<LocalTime> values) {
			addCriterion("remind_time in", values, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeNotIn(List<LocalTime> values) {
			addCriterion("remind_time not in", values, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeBetween(LocalTime value1, LocalTime value2) {
			addCriterion("remind_time between", value1, value2, "remindTime");
			return (Criteria) this;
		}

		public Criteria andRemindTimeNotBetween(LocalTime value1, LocalTime value2) {
			addCriterion("remind_time not between", value1, value2, "remindTime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table public.reservation
	 * @mbg.generated  Mon Jun 08 17:58:25 JST 2020
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.reservation
     *
     * @mbg.generated do_not_delete_during_merge Sun Jun 07 22:31:33 JST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}