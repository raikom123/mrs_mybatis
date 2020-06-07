package mrs.domain.model.mybatis;

import java.io.Serializable;

public class Usr implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr.user_id
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr.first_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr.last_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr.password
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr.role_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table usr
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr.user_id
     *
     * @return the value of usr.user_id
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr.user_id
     *
     * @param userId the value for usr.user_id
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr.first_name
     *
     * @return the value of usr.first_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr.first_name
     *
     * @param firstName the value for usr.first_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr.last_name
     *
     * @return the value of usr.last_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr.last_name
     *
     * @param lastName the value for usr.last_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr.password
     *
     * @return the value of usr.password
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr.password
     *
     * @param password the value for usr.password
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr.role_name
     *
     * @return the value of usr.role_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr.role_name
     *
     * @param roleName the value for usr.role_name
     *
     * @mbg.generated Sun Jun 07 22:31:33 JST 2020
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}