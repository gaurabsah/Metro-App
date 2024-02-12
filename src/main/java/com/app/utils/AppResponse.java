package com.app.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppResponse {
	
	private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;

}
