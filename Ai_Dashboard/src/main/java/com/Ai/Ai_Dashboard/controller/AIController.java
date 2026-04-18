package com.Ai.Ai_Dashboard.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Ai.Ai_Dashboard.entity.DataEntity;
import com.Ai.Ai_Dashboard.service.ChartService;
import com.Ai.Ai_Dashboard.service.CsvService;
import com.Ai.Ai_Dashboard.service.OllamaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {



	@Autowired
	private OllamaService aiService;
	@Autowired
	private ChartService chartService;

	@Autowired
	private CsvService csvService;

	private final ObjectMapper mapper = new ObjectMapper();


	@PostMapping("/chart")
	public ResponseEntity<?> chat(@RequestBody Map<String, String> req) {

		try {
			String query = req.get("query");

			String fileId = csvService.getLatestFileId();

			List<DataEntity> dataList = csvService.getCachedData();

			if (dataList == null || dataList.isEmpty()) {
				dataList = csvService.getData(fileId);
			}

			dataList = dataList.stream().limit(500).toList();

			if (dataList.isEmpty()) {
				return ResponseEntity.badRequest()
						.body(Map.of("error", "No data available"));
			}

			Map<String, Object> sample =
					mapper.readValue(dataList.get(0).getData(), Map.class);

			Set<String> columns = sample.keySet();
			Map<String, String> intent =
					aiService.extractIntent(query, columns);

			String columnHint = intent.getOrDefault("columnHint", "");

			String xField = resolveBestColumn(query, columns, columnHint);

			System.out.println("Selected column: " + xField);

			Map<String, Object> chart =
					chartService.buildChart(fileId, xField);


			String summary = aiService.generateSummary(dataList);


			return ResponseEntity.ok(Map.of(
					"type", "bar",
					"data", chart.get("data"),
					"summary", summary
					));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(Map.of("error", e.getMessage()));
		}
	}
	private String resolveBestColumn(
			String query,
			Set<String> cols,
			String hint
			) {

		if (hint != null && cols.contains(hint)) {
			return hint;
		}

		String q = query.toLowerCase();


		for (String c : cols) {
			if (q.contains(c.replace("_", " "))) {
				return c;
			}
		}

		return cols.stream()
				.filter(c -> !c.contains("id"))
				.filter(c -> !c.contains("email"))
				.filter(c -> !c.contains("phone"))
				.filter(c -> !c.contains("account"))
				.findFirst()
				.orElse(cols.iterator().next());
	}
}







//@PostMapping("/chart")
//public ResponseEntity<?> chat(@RequestBody Map<String, String> req) {
//
//	try {
//
//		String query = req.get("query");
//
//		String fileId = csvService.getLatestFileId();
//		List<DataEntity> dataList = csvService.getData(fileId);
//
//		if (dataList.isEmpty()) {
//			return ResponseEntity.badRequest().body("No data uploaded");
//		}
//
//		Map<String, Object> sample =
//				mapper.readValue(dataList.get(0).getData(), Map.class);
//
//		Set<String> columns = sample.keySet();
//
//		Map<String, String> intent =
//				aiService.extractIntent(query, columns);
//
//		String type = intent.getOrDefault("type", "chart");
//		String columnHint = intent.getOrDefault("columnHint", "");
//
//		if ("summary".equalsIgnoreCase(type) ||
//				query.toLowerCase().contains("summary")) {
//
//			String summary = aiService.generateSummary(dataList);
//
//			return ResponseEntity.ok(Map.of(
//					"type", "summary",
//					"summary", summary
//					));
//		}
//
//		if ("insight".equalsIgnoreCase(type) ||
//				query.toLowerCase().contains("insight")) {
//
//			String insight = aiService.generateInsight(dataList);
//
//			return ResponseEntity.ok(Map.of(
//					"type", "insight",
//					"insight", insight
//					));
//		}
//
//		String xField = resolveBestColumn(query, columns, columnHint);
//
//		Map<String, Object> chart =
//				chartService.buildChart(fileId, xField);
//
//		return ResponseEntity.ok(chart);
//
//	} catch (Exception e) {
//		return ResponseEntity.internalServerError()
//				.body(e.getMessage());
//	}
//}
//
//private String resolveBestColumn(
//		String query,
//		Set<String> cols,
//		String hint
//		) {
//
//	if (hint != null && cols.contains(hint)) {
//		return hint;
//	}
//
//	String q = query.toLowerCase();
//
//	for (String c : cols) {
//		if (q.contains(c.replace("_", " "))) {
//			return c;
//		}
//	}
//
//	return cols.stream()
//			.filter(c -> !c.contains("id")
//					&& !c.contains("phone")
//					&& !c.contains("email"))
//			.findFirst()
//			.orElse(cols.iterator().next());
//}
//}

