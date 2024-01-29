package com.leavemanagement.LeaveManagement.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	
	@NotNull
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=6,max=12,message="Password must be min of 3 chars and max of 10 chars")
	private String password;

}
