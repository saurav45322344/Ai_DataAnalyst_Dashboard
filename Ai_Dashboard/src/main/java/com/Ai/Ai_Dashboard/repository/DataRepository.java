package com.Ai.Ai_Dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ai.Ai_Dashboard.entity.DataEntity;


@Repository
public interface DataRepository  extends JpaRepository<DataEntity, Long>{

	List<DataEntity> findByFileId(String fileId);

	DataEntity findTopByOrderByIdDesc();
}




	//	   @Query(value = "SELECT * FROM data_entity WHERE file_id = :fileId", nativeQuery = true)
	//	    List<DataEntity> findByFileId(@Param("fileId") String fileId);


