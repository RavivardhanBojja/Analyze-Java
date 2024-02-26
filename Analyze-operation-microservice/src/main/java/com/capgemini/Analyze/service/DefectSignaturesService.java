package com.capgemini.Analyze.service;

import java.util.List;

import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.SignatureDTO;

public interface DefectSignaturesService {

	List<SignatureDTO> getClosedDefectsWithSignatureStatus(Long solutionId);

	List<Long> deleteDefectSignaturesBySolutionIdAndDefectIds(Long solutionId, List<Long> defectIds);

	boolean createOrUpdateSignature(List<DefectMasterDTO> defectMasterDTOList, String embeddingmodel);

}
