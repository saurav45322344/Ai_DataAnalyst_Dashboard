package com.Ai.Ai_Dashboard.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Ai.Ai_Dashboard.entity.DataEntity;
import com.Ai.Ai_Dashboard.repository.DataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CsvService {


	@Autowired
	private DataRepository repo;

	private final ObjectMapper mapper = new ObjectMapper();
	
	//extra addon
	 private List<DataEntity> cachedData = new ArrayList<>();
	    private String cachedFileId;

	public String saveCSV(MultipartFile file) {

		try {
			String fileId = UUID.randomUUID().toString();

			BufferedReader reader =
					new BufferedReader(new InputStreamReader(file.getInputStream()));

			String[] headers = reader.readLine().split(",");

			for (int i = 0; i < headers.length; i++) {
				headers[i] = normalize(headers[i]);
			}

			String line;

			while ((line = reader.readLine()) != null) {

				String[] values = line.split(",", -1);

				Map<String, String> row = new HashMap<>();

				for (int i = 0; i < headers.length; i++) {
					String key = headers[i];
					String val = i < values.length ? values[i].trim() : "";
					row.put(key, val);
				}

				DataEntity entity = new DataEntity();
				entity.setFileId(fileId);
				entity.setData(mapper.writeValueAsString(row));

				repo.save(entity);
			}

			return fileId;

		} catch (Exception e) {
			throw new RuntimeException("CSV upload failed");
		}
	}

	public List<DataEntity> getData(String fileId) {
		return repo.findByFileId(fileId);
	
	}
	
	//extra addon
	public List<DataEntity> getCachedData() {
	    return cachedData;
	}

	public String getLatestFileId() {
		DataEntity d = repo.findTopByOrderByIdDesc();
		return d != null ? d.getFileId() : null;
	}

	private String normalize(String s) {
		return s == null ? "" : s.trim().toLowerCase().replace(" ", "_");
	}
}

