package com.Ai.Ai_Dashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Ai.Ai_Dashboard.dto.ChartResponse;
import com.Ai.Ai_Dashboard.entity.DataEntity;
import com.Ai.Ai_Dashboard.repository.DataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnalyticsService {

//	@Autowired
//	private  DataRepository repository;
//	
//	@Autowired
//	private AnalyticsService analyticsService;
	
    //private final ObjectMapper mapper = new ObjectMapper();

	   private final DataRepository repository;
	   private final ObjectMapper mapper = new ObjectMapper();

	    public AnalyticsService(DataRepository repository) {
	        this.repository = repository;
	    }
	

    public List<ChartResponse> groupByField(String groupField, String valueField) {

        List<DataEntity> dataList = repository.findAll();
        Map<String, Double> result = new HashMap<>();

        try {
            for (DataEntity entity : dataList) {

                Map<String, Object> row = mapper.readValue(entity.getData(), Map.class);

                String key = row.get(groupField).toString();
                Double value = Double.parseDouble(row.get(valueField).toString());

                result.put(key, result.getOrDefault(key, 0.0) + value);
            }

        } catch (Exception e) {
            throw new RuntimeException("Data processing error");
        }

        List<ChartResponse> response = new ArrayList<>();

        for (Map.Entry<String, Double> entry : result.entrySet()) {
            response.add(new ChartResponse(entry.getKey(), entry.getValue()));
        }

        return response;
    }
	
}
