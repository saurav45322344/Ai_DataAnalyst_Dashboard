package com.Ai.Ai_Dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ai.Ai_Dashboard.dto.ChartResponse;
import com.Ai.Ai_Dashboard.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
	
	
@Autowired
    private  AnalyticsService service;

   

    // Example:
    // /api/analytics?groupBy=month&value=sales

    @GetMapping
    public List<ChartResponse> getChart(
            @RequestParam String groupBy,
            @RequestParam String value
    ) {
        return service.groupByField(groupBy, value);
    }
}


