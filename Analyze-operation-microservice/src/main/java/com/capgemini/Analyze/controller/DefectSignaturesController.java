package com.capgemini.Analyze.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Analyze.annotations.FeatureFlagAnnotation;
import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.dto.SignatureDTO;
import com.capgemini.Analyze.service.DefectSignaturesService;

@RestController
@RequestMapping("/defectsignature")
public class DefectSignaturesController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefectSignaturesController.class);
	
	@Autowired
	private DefectSignaturesService dsService;
	
//	 @Value("${flask.api.python_host}")
//	  private String pythonApiHost;
	 
	 @Value("${flask.api.python_host:default_value}")
	 private String pythonApiHost;

    @FeatureFlagAnnotation(featureName = "Defect Signatures")
	@RequestMapping(value = "/getClosedDefectSignatures/solution/{solutionId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> geClosedDefectSignaturesBySolutionIds(@PathVariable Long solutionId) {
	    List<SignatureDTO> defectsWithSignatureStatus = dsService.getClosedDefectsWithSignatureStatus(solutionId);

	    if (defectsWithSignatureStatus.isEmpty()) {
	        ResponseDTO responseDTO = new ResponseDTO(null, "No closed DefectSignature found for the solution ID: " + solutionId, "Not Found");
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    } else {
	        ResponseDTO responseDTO = new ResponseDTO(defectsWithSignatureStatus, "Closed DefectSignature retrieved successfully", null);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	}
	
	@RequestMapping(value = "/deleteDefectSignatures/solution/{solutionId}/defects", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> deleteDefectSignatureBySolutionIdAndDefectIds(
	        @PathVariable Long solutionId,
	        @RequestBody List<Long> defectIds) {
	    List<Long> failedDeletions = dsService.deleteDefectSignaturesBySolutionIdAndDefectIds(solutionId, defectIds);

	    if (!failedDeletions.isEmpty()) {
	        ResponseDTO responseDTO = new ResponseDTO(false, "Failed to delete DefectSignatures with IDs " + failedDeletions, "Deletion error");
	        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    } else {
	        ResponseDTO responseDTO = new ResponseDTO(true, "Defects with IDs " + defectIds + " deleted DefectSignatures successfully");
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	}
	
	
//    @RequestMapping(value = "/deleteDefectSignatures/solution/{solutionId}/defects", method = RequestMethod.POST)
//    public ResponseEntity<ResponseDTO> deleteDefectSignatureBySolutionIdAndDefectIds(
//            @PathVariable Long solutionId,
//            @RequestBody List<Long> defectIds) {
//        
//
//        String pythonApiUrl = pythonApiHost + "/your-python-endpoint";
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("solutionId", solutionId);
//        requestBody.put("defectIds", defectIds);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> pythonApiResponse = restTemplate.postForEntity(pythonApiUrl, requestBody, String.class);
//
//        if (pythonApiResponse.getStatusCode() != HttpStatus.OK) {
//            ResponseDTO responseDTO = new ResponseDTO(false, "Failed to call Python API", "API error");
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        String pythonResponseBody = pythonApiResponse.getBody();
//        
//        List<Long> failedDeletions = dsService.deleteDefectSignaturesBySolutionIdAndDefectIds(solutionId, defectIds);
//
//        if (!failedDeletions.isEmpty()) {
//            ResponseDTO responseDTO = new ResponseDTO(false, "Failed to delete DefectSignatures with IDs " + failedDeletions, "Deletion error");
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            ResponseDTO responseDTO = new ResponseDTO(true, "Defects with IDs " + defectIds + " deleted DefectSignatures successfully");
//            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//        }
//    }
    
	@RequestMapping(value = "/defects/signature", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> createOrUpdateSignature(
        @RequestBody List<DefectMasterDTO> defectMasterDTOList,
        @RequestParam String embeddingmodel) {
        
        boolean success = dsService.createOrUpdateSignature(defectMasterDTOList, embeddingmodel);

        if (success) {
            ResponseDTO responseDTO = new ResponseDTO("Defect signatures created/updated successfully");
            return ResponseEntity.ok(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO("Failed to create/update defect signatures");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
