package com.Ai.Ai_Dashboard.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Ai.Ai_Dashboard.entity.DataEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class OllamaService {


	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper mapper = new ObjectMapper();


	public Map<String, String> extractIntent(String query, Set<String> columns) {

		try {

			String prompt =
					"You are a BI Copilot.\n" +
							"Classify query into ONLY one:\n" +
							"chart | summary | insight\n\n" +
							"Return ONLY JSON:\n" +
							"{ \"type\": \"chart|summary|insight\", \"operation\": \"count|sum|avg\", \"columnHint\": \"best column\" }\n\n" +
							"Columns: " + columns + "\n" +
							"Query: " + query;

			Map req = Map.of(
					"model", "phi3",
					"prompt", prompt,
					"stream", false
					);

			Map res = restTemplate.postForObject(
					"http://localhost:11434/api/generate",
					req,
					Map.class
					);

			String raw = res.get("response").toString()
					.replace("```json", "")
					.replace("```", "")
					.trim();

			int start = raw.indexOf("{");
			int end = raw.lastIndexOf("}");

			if (start != -1 && end != -1) {
				raw = raw.substring(start, end + 1);
			}

			return mapper.readValue(raw, Map.class);

		} catch (Exception e) {

			return Map.of(
					"type", "chart",
					"operation", "count",
					"columnHint", ""
					);
		}
	}

	public String generateSummary(List<DataEntity> dataList) {

		try {

			String sample = dataList.stream()
					.limit(20)
					.map(DataEntity::getData)
					.toList()
					.toString();

			String prompt =
					"You are a senior data analyst.\n" +
							"Write a 2–3 line business summary of this dataset.\n" +
							"Focus on patterns, distribution, and insights.\n\n" +
							"Data:\n" + sample;

			Map res = restTemplate.postForObject(
					"http://localhost:11434/api/generate",
					Map.of("model", "phi3", "prompt", prompt, "stream", false),
					Map.class
					);

			return res.get("response").toString();

		} catch (Exception e) {
			return "Summary not available";
		}
	}

	public String generateInsight(List<DataEntity> dataList) {

		try {

			String sample = dataList.stream()
					.limit(20)
					.map(DataEntity::getData)
					.toList()
					.toString();

			String prompt =
					"You are a BI expert.\n" +
							"Give 2–3 line deep insight (trend, anomaly, pattern).\n\n" +
							"Data:\n" + sample;

			Map res = restTemplate.postForObject(
					"http://localhost:11434/api/generate",
					Map.of("model", "phi3", "prompt", prompt, "stream", false),
					Map.class
					);

			return res.get("response").toString();

		} catch (Exception e) {
			return "Insight not available";
		}
	}
}
	
