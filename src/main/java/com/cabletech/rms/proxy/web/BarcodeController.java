package com.cabletech.rms.proxy.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 生成条形码
 * @author YuLeyuan
 * 
 */
@Controller
public class BarcodeController {
	private static final BarcodeFormat DEFAULT_BARCODE_FORMAT = BarcodeFormat.QR_CODE;
	private static final String DEFAULT_IMAGE_FORMAT = "PNG";
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;

	/**
	 * @param content content
	 * @param response response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/barcode/encode.do", method = RequestMethod.GET)
	public final String encode(
			@RequestParam(value = "content", required = true) String content,
            HttpServletResponse response)
			throws Exception {
		BarcodeFormat barcodeFormat = DEFAULT_BARCODE_FORMAT;
		String imageFormat = DEFAULT_IMAGE_FORMAT;
		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;

		MultiFormatWriter barcodeWriter = new MultiFormatWriter();
		BitMatrix matrix = barcodeWriter.encode(content, barcodeFormat, width,
				height);
		// MatrixToImageWriter.writeToFile(matrix, imageFormat, new
		// File(outFileString));
		OutputStream out=response.getOutputStream();
		MatrixToImageWriter.writeToStream(matrix, imageFormat,out);
		out.flush();
		out.close();
		return null;
	}
}
