package com.twitter.interview.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The user object is used to capture communicate user details")
public class UserVo {

	private String email;

	@ApiModelProperty(notes = "The minimum length is 2 and maximum length is 25")
	@Size(min = 2, max = 25)
	private String firstname;

	@ApiModelProperty(notes = "The minimum length is 2 and maximum length is 25")
	@Size(min = 2, max = 25)
	private String lastname;

	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}
