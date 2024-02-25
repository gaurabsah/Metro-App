package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.QRTicketDto;
import com.app.service.impl.QrCodeGeneratorService;

@RestController
@RequestMapping("/qr")
public class QrCodeController {

	@Autowired
	private QrCodeGeneratorService qrCodeGeneratorService;

	@PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public byte[] generateQrCode(@RequestBody QRTicketDto content) throws IOException {
		int width = 200; // Adjust the desired width of the QR code
		int height = 200; // Adjust the desired height of the QR code
		return qrCodeGeneratorService.generateQrCodeImage(content, width, height);
	}
}