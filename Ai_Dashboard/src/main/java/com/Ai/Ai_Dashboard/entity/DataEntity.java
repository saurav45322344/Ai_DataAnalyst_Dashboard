package com.Ai.Ai_Dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class DataEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String data;

	
	 @Column
	    private String fileId;


	 public Long getId() {
		 return id;
	 }


	 public void setId(Long id) {
		 this.id = id;
	 }


	 public String getData() {
		 return data;
	 }


	 public void setData(String data) {
		 this.data = data;
	 }


	 public String getFileId() {
		 return fileId;
	 }


	 public void setFileId(String fileId) {
		 this.fileId = fileId;
	 }


	 public DataEntity(Long id, String data, String fileId) {
		super();
		this.id = id;
		this.data = data;
		this.fileId = fileId;
	 }


	 public DataEntity() {
		super();
		// TODO Auto-generated constructor stub
	 } 
	 

}