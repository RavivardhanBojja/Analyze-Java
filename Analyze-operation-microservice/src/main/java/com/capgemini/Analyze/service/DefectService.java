package com.capgemini.Analyze.service;

import com.capgemini.Analyze.entity.DefectMasterEntity;

import java.util.List;

public interface DefectService {

    List<DefectMasterEntity> getAllDefectsBySolutionId(Long solutionId);
	
    List<Long> deleteDefectsBySolutionIdAndDefectIds(Long solutionId, List<Long> defectIds);
}
