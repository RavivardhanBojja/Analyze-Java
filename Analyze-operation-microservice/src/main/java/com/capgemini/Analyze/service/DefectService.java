package com.capgemini.Analyze.service;

import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface DefectService {

    List<DefectMasterEntity> getAllDefectsBySolutionId(Long solutionId);
	
    List<Long> deleteDefectsBySolutionIdAndDefectIds(Long solutionId, List<Long> defectIds);
    
	ResponseEntity<ResponseDTO> saveDefects(List<DefectMasterDTO> defects);

	List<DefectMasterEntity> getClosedDefectsBySolutionId(Long solutionId);

	ResponseEntity<ResponseDTO> saveDefectswithOverwriteExisting(List<DefectMasterDTO> defectDTOs);
}
