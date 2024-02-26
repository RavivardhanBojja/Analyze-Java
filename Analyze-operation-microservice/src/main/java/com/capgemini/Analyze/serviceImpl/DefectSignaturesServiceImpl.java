package com.capgemini.Analyze.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;


import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.SignatureDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.entity.DefectSignatureMasterEntity;
import com.capgemini.Analyze.repository.DefectMasterRepository;
import com.capgemini.Analyze.repository.DefectSignatureMasterRepository;
import com.capgemini.Analyze.service.DefectSignaturesService;

@Service
public class DefectSignaturesServiceImpl implements DefectSignaturesService{
	
	private static final Logger log = LoggerFactory.getLogger(DefectSignaturesServiceImpl.class);

	@Autowired
    private DefectMasterRepository defectRepository;
    
    @Autowired
    private DefectSignatureMasterRepository defectSignatureMasterRepository;
    
//    @Value("${flask.api.python_host}")
//	 private String pythonApiHost;
    
    @Value("${flask.api.python_host:default_value}")
    private String pythonApiHost;

	
    @Override
    public List<SignatureDTO> getClosedDefectsWithSignatureStatus(Long solutionId) {
        List<DefectMasterEntity> closedDefects = defectRepository.findClosedDefectsBySolutionId(solutionId);
        List<SignatureDTO> defectsWithSignatureStatus = new ArrayList<>();

        for (DefectMasterEntity defect : closedDefects) {
            SignatureDTO signatureDTO = new SignatureDTO();
            DefectMasterDTO defectMasterDTO = convertToDto(defect);
            DefectSignatureMasterEntity defectSignature = defectSignatureMasterRepository.findByBugIdAndSolutionId(defect.getBugId(), defect.getSolutionId());

            signatureDTO.setDefectMasterDTO(defectMasterDTO);
            signatureDTO.setSignature(defectSignature != null);
            
            defectsWithSignatureStatus.add(signatureDTO);
        }

        return defectsWithSignatureStatus;
    }

    private DefectMasterDTO convertToDto(DefectMasterEntity defectEntity) {
        DefectMasterDTO defectDto = new DefectMasterDTO();
        defectDto.setBugId(defectEntity.getBugId());
        defectDto.setTitle(defectEntity.getTitle());
        defectDto.setState(defectEntity.getState());
        defectDto.setReason(defectEntity.getReason());
        defectDto.setArea(defectEntity.getArea());
        defectDto.setIterationPath(defectEntity.getIterationPath());
        defectDto.setAssignedTo(defectEntity.getAssignedTo());
        defectDto.setComment(defectEntity.getComment());
        defectDto.setCreatedBy(defectEntity.getCreatedBy());
        defectDto.setCreatedDate(formatDate(defectEntity.getCreatedDate()));
        defectDto.setClosedBy(defectEntity.getClosedBy());
        defectDto.setClosedDate(formatDate(defectEntity.getClosedDate()));
        defectDto.setClosingComment(defectEntity.getClosingComment());
        defectDto.setReproSteps(defectEntity.getReproSteps());
        defectDto.setSeverity(defectEntity.getSeverity());
        defectDto.setStoryPoints(defectEntity.getStoryPoints());
        defectDto.setEfforts(defectEntity.getEfforts());
        defectDto.setParentFeatureId(defectEntity.getParentFeatureId());
        defectDto.setParentFeatureTitle(defectEntity.getParentFeatureTitle());
        defectDto.setSolutionId(defectEntity.getSolutionId());

        return defectDto;
    }

    private static String formatDate(Date date) {
        return date != null ? date.toString() : null;
    }
    
    
    @Override
    @Transactional
    public List<Long> deleteDefectSignaturesBySolutionIdAndDefectIds(Long solutionId, List<Long> defectIds) {
        List<Long> failedDeletions = new ArrayList<>();

        for (Long defectId : defectIds) {
            try {
                DefectSignatureMasterEntity defectSignature = defectSignatureMasterRepository.findByBugIdAndSolutionId(defectId, solutionId);
                if (defectSignature != null) {
                	defectSignatureMasterRepository.delete(defectSignature);
                } else {
                    failedDeletions.add(defectId);
                }
            } catch (Exception e) {
                failedDeletions.add(defectId);
            }
        }
        return failedDeletions;
    }
    
    @Override
    @Transactional
    public boolean createOrUpdateSignature(List<DefectMasterDTO> defectMasterDTOList, String embeddingmodel) {
        try {
            List<SignatureDTO> signatureDTOList = callPythonApi(defectMasterDTOList, embeddingmodel);

            if (signatureDTOList != null && !signatureDTOList.isEmpty() && defectMasterDTOList.size() == signatureDTOList.size()) {
                for (int i = 0; i < defectMasterDTOList.size(); i++) {
                    DefectMasterDTO defectMasterDTO = defectMasterDTOList.get(i);
                    SignatureDTO signatureDTO = signatureDTOList.get(i);

                    DefectSignatureMasterEntity existingDefectSignature = defectSignatureMasterRepository.findByBugIdAndSolutionId(
                            defectMasterDTO.getBugId(), defectMasterDTO.getSolutionId());

                    if (existingDefectSignature != null) {
                        existingDefectSignature.setSignature(signatureDTO.isSignature());
                        defectSignatureMasterRepository.save(existingDefectSignature);
                    } else {
                        DefectSignatureMasterEntity newDefectSignature = new DefectSignatureMasterEntity();
                        newDefectSignature.setBugId(defectMasterDTO.getBugId());
                        newDefectSignature.setSolutionId(defectMasterDTO.getSolutionId());
                        newDefectSignature.setSignature(signatureDTO.isSignature());
                        defectSignatureMasterRepository.save(newDefectSignature);
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<SignatureDTO> callPythonApi(List<DefectMasterDTO> defectMasterDTOList, String embeddingmodel) {
        String pythonApiUrl = pythonApiHost + "/api/large_language_model/useEmbeddings";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("defectMasterDTOList", defectMasterDTOList);
        requestBody.put("embeddingmodel", embeddingmodel);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<SignatureDTO[]> responseEntity = restTemplate.postForEntity(pythonApiUrl, requestEntity, SignatureDTO[].class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(responseEntity.getBody());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}