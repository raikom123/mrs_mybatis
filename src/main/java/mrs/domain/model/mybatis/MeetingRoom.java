package mrs.domain.model.mybatis;

import java.io.Serializable;

public class MeetingRoom implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.meeting_room.room_id
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private Integer roomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.meeting_room.room_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private String roomName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.meeting_room
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.meeting_room.room_id
     *
     * @return the value of public.meeting_room.room_id
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.meeting_room.room_id
     *
     * @param roomId the value for public.meeting_room.room_id
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.meeting_room.room_name
     *
     * @return the value of public.meeting_room.room_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.meeting_room.room_name
     *
     * @param roomName the value for public.meeting_room.room_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}