/**
 * 
 */
package org.openframework.common.rest.vo;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author Java Developer
 *
 */
public class UserVO extends BaseVO {

	private Long id;

	//@Digits(integer=8, fraction=0, message = "Invalid admNo") // this validation annotation is not working
	@Range(min = 20000000, max = 20999999, message= "field value should be between 20000000 and 20999999")
	@NumberFormat(style = Style.NUMBER)
	private Long admissionNo;

	@NotNull(message = "first name is required")
	@Length(min=1, max=50, message="first name length should be between 5 and 50 characters")
	private String firstName;
	@Length(max=50, message="length should not be more than 50 characters")
	private String lastName;
	private char[] password;
	@NotNull(message = "gender is required")
	private String gender;
	@Email(message="email should be an valid email")
	@Length(max=50, message="length should not be more than 50 characters")
	private String email;
	@Length(max=50, message="length should not be more than 50 characters")
	private String mobile;
	@Length(max=50, message="length should not be more than 50 characters")
	private String phone;
	@Length(max=500, message="length should not be more than 500 characters")
	private String description;

	private String status;
	@NotNull(message = "role is required")
	private String role;

	private Map<String, Object> otherData;

	public Long getId() {
		return id;
	}
	public void setId(Long userID) {
		this.id = userID;
	}
	public Long getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(Long admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desscription) {
		this.description = desscription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Map<String, Object> getOtherData() {
		return otherData;
	}
	public void setOtherData(Map<String, Object> otherData) {
		this.otherData = otherData;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+"[id="+this.getId()+", email="+getEmail()+"]";
	}
}
