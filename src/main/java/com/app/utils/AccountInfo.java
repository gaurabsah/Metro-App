package com.app.utils;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountInfo {

	private String accountName;
	private String accountNumber;
	private String accountBalance;

}
