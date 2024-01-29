package com.leavemanagement.LeaveManagement.payload;

import com.leavemanagement.LeaveManagement.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	@NotNull
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=6,max=12,message="Password must be min of 3 chars and max of 10 chars")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
