package com.leavemanagement.LeaveManagement.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusDto {
	@NotNull
	private String status;
	private String comment;
}
