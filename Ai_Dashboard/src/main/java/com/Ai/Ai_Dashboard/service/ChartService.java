package com.Ai.Ai_Dashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ai.Ai_Dashboard.entity.DataEntity;
import com.Ai.Ai_Dashboard.repository.DataRepository;

import tools.jackson.databind.ObjectMapper;

@Service
public class ChartService {


	@Autowired
	private DataRepository repository;

	private final ObjectMapper mapper = new ObjectMapper();

	public Map<String, Object> buildChart(
			String fileId,
			String xField
			) {

		List<DataEntity> dataList = repository.findByFileId(fileId);

		Map<String, Double> agg = new HashMap<>();

		for (DataEntity e : dataList) {

			try {
				Map<String, Object> row =
						mapper.readValue(e.getData(), Map.class);

				Object x = row.get(xField);

				if (x == null) continue;

				String key = x.toString();

				agg.put(key, agg.getOrDefault(key, 0.0) + 1);

			} catch (Exception ignored) {}
		}

		List<Map<String, Object>> chart = new ArrayList<>();

		for (var entry : agg.entrySet()) {
			chart.add(Map.of(
					"x", entry.getKey(),
					"y", entry.getValue()
					));
		}

		return Map.of(
				"type", "bar",
				"data", chart
				);
	}
}

