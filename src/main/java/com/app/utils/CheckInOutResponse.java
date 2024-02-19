package com.app.utils;

import com.app.dto.CheckInDto;
import com.app.dto.CheckOutDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckInOutResponse {

	private CheckInDto checkInDto;

	private CheckOutDto checkOutDto;

}
