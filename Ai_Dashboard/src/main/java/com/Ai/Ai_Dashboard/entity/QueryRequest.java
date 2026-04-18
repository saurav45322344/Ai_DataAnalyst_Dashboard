package com.Ai.Ai_Dashboard.entity;


public class QueryRequest {

	 private String question;

	 public String getQuestion() {
		 return question;
	 }

	 public void setQuestion(String question) {
		 this.question = question;
	 }

	 public QueryRequest(String question) {
		super();
		this.question = question;
	 }

	 public QueryRequest() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 
}
