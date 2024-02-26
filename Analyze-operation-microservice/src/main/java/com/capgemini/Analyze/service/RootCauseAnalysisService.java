package com.capgemini.Analyze.service;

import java.util.List;

import com.capgemini.Analyze.dto.SignatureDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;

public interface RootCauseAnalysisService {
	
	List<DefectMasterEntity> getOpenDefectsBySolutionId(Long solutionId);

}
