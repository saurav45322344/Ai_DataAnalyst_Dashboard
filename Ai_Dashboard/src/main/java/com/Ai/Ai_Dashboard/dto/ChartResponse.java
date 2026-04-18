package com.Ai.Ai_Dashboard.dto;

import lombok.Data;

@Data
public class ChartResponse {

	
	private String label;
    private Double value;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public ChartResponse(String label, Double value) {
		super();
		this.label = label;
		this.value = value;
	}
	public ChartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
