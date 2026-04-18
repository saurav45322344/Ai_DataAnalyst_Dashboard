package com.Ai.Ai_Dashboard.entity;

import java.util.List;



public class QueryResponse {

	 private String summary;
	    private Double total;
	    private List<DataRecord> data;
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public Double getTotal() {
			return total;
		}
		public void setTotal(Double total) {
			this.total = total;
		}
		public List<DataRecord> getData() {
			return data;
		}
		public void setData(List<DataRecord> data) {
			this.data = data;
		}
		public QueryResponse(String summary, Double total, List<DataRecord> data) {
			super();
			this.summary = summary;
			this.total = total;
			this.data = data;
		}
		public QueryResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	
}
