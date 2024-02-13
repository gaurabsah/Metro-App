package com.app.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.QRTicketDto;
import com.app.exception.ResourcesNotFoundException;
import com.app.model.Fare;
import com.app.model.QRTicket;
import com.app.repository.FareRepository;
import com.app.repository.QRTicketRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QrCodeGeneratorService {

	@Autowired
	private FareRepository fareRepository;

	@Autowired
	private QRTicketRepository qrTicketRepository;

	public byte[] generateQrCodeImage(QRTicketDto content, int width, int height) throws IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		Map<EncodeHintType, Object> hintsMap = new HashMap<>();
		hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix;
		try {

			Boolean sourceLoc = fareRepository.existsBySourceLoc(content.getSourceLoc());
			Boolean destinationLoc = fareRepository.existsByDestinationLoc(content.getDestinationLoc());

			if (!sourceLoc || !destinationLoc) {
				if (!sourceLoc)
					throw new ResourcesNotFoundException("Location Not Found " + content.getSourceLoc());

				throw new ResourcesNotFoundException("Location Not Found " + content.getDestinationLoc());
			}

			Fare findBySourceLocAndDestinationLoc = fareRepository
					.findBySourceLocAndDestinationLoc(content.getSourceLoc(), content.getDestinationLoc());

			bitMatrix = qrCodeWriter.encode(
					content.builder()
					.sourceLoc(findBySourceLocAndDestinationLoc.getSourceLoc())
					.destinationLoc(findBySourceLocAndDestinationLoc.getDestinationLoc())
					.creationDate(LocalDateTime.now())
					.validityDays(LocalDateTime.now().plusDays(1L))
					.build().toString(),
					BarcodeFormat.QR_CODE, width, height, hintsMap);
			QRTicket qrTicket = new QRTicket();
			qrTicket.setId(UUID.randomUUID().toString());
			qrTicket.setDestinationLoc(findBySourceLocAndDestinationLoc.getDestinationLoc());
			qrTicket.setSourceLoc(findBySourceLocAndDestinationLoc.getSourceLoc());
			qrTicket.setQuantity(content.getQuantity());
			qrTicket.setCreationDate(content.getCreationDate());
			qrTicket.setValidityDays(content.getValidityDays());
			qrTicketRepository.save(qrTicket);
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate QR code image.", e);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BufferedImage qrImage = toBufferedImage(bitMatrix);
		try {
			ImageIO.write(qrImage, "png", outputStream);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write QR code image to output stream.", e);
		}

		return outputStream.toByteArray();
	}

	private BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (matrix.get(x, y)) {
					graphics.fillRect(x, y, 1, 1);
				}
			}
		}
		return image;
	}
}