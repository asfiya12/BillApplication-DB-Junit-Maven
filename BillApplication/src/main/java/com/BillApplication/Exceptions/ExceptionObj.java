package com.BillApplication.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ExceptionObj {
	private Integer statusCode;
	private String message;
	private long timestamp;
}
