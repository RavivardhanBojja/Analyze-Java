package com.capgemini.Analyze.controller;

import com.capgemini.Analyze.annotations.FeatureFlagAnnotation;
import com.capgemini.Analyze.dto.AzureADOBugMasterDTO;
import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.service.AzureADOService;
import com.capgemini.Analyze.service.DefectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/defects")
public class DefectController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefectController.class);
	

    @Autowired
    private DefectService defectService;
    
    @Autowired
    private AzureADOService azureADOService;
    

    @FeatureFlagAnnotation(featureName = "Defect Knowledge Management")
	@RequestMapping(value = "/getAllDefects/solution/{solutionId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getAllDefectsBySolutionId(@PathVariable Long solutionId) {
        List<DefectMasterEntity> defects = defectService.getAllDefectsBySolutionId(solutionId);
        
        if (defects.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO(null, "No defects found for the solution ID: " + solutionId, "Not Found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        } else {
            ResponseDTO responseDTO = new ResponseDTO(defects, "Defects retrieved successfully",null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deleteDefects/solution/{solutionId}/defects", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> deleteDefectsBySolutionIdAndDefectIds(
        @PathVariable Long solutionId, 
        @RequestBody List<Long> defectIds) {
        List<Long> failedDeletions = defectService.deleteDefectsBySolutionIdAndDefectIds(solutionId, defectIds);

        if (!failedDeletions.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO(false, "Failed to delete defects with IDs " + failedDeletions, "Deletion error");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            ResponseDTO responseDTO = new ResponseDTO(true, "Defects with IDs " + defectIds + " deleted successfully");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

	
	@RequestMapping(value = "/saveAllDefectsBysolutionId", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveDefects(@RequestBody List<DefectMasterDTO> defectDTOs) {
        return defectService.saveDefects(defectDTOs);
    }
	
	@RequestMapping(value = "/saveAllExistingDefectsBysolutionId", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveDefectswithOverwriteExisting(@RequestBody List<DefectMasterDTO> defectDTOs) {
        return defectService.saveDefectswithOverwriteExisting(defectDTOs);
    }
	
	@RequestMapping(value = "/getBugInfoAzureADO", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> getBugInfoAzureADO(@RequestBody AzureADOBugMasterDTO azureADOBugMasterDTO) {
	    try {
	        List<DefectMasterDTO> defects = azureADOService.getBugInfoAzureADO(azureADOBugMasterDTO);
	        if (defects.isEmpty()) {
	            ResponseDTO responseDTO = new ResponseDTO(null, "No defects found");
	            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
	        } else {
	            ResponseDTO responseDTO = new ResponseDTO(defects, "Defects retrieved successfully");
	            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        ResponseDTO responseDTO = new ResponseDTO(null, "An error occurred while fetching defects", e.getMessage());
	        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@RequestMapping(value = "/getClosedDefects/solution/{solutionId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> geClosedDefectsBySolutionId(@PathVariable Long solutionId) {
        List<DefectMasterEntity> defects = defectService.getClosedDefectsBySolutionId(solutionId);
        
        if (defects.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO(null, "No closed defects found for the solution ID: " + solutionId, "Not Found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        } else {
            ResponseDTO responseDTO = new ResponseDTO(defects, "Closed Defects retrieved successfully",null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }




}