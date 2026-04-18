package com.Ai.Ai_Dashboard.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Ai.Ai_Dashboard.service.CsvService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

	private final CsvService csvService;

	public UploadController(CsvService csvService) {
		this.csvService = csvService;
	}


	@PostMapping
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {

		String fileId = csvService.saveCSV(file);

		return Map.of(
				"status", "success",
				"fileId", fileId
				);
	}
}



//new code 
//	@PostMapping
//	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
//
//		String count = csvService.saveCSV(file);
//		
//
//		return Map.of(
//				"status", "success",
//				"message", "File uploaded successfully",
//				"rowsInserted", count
//				);
//	}
//}

