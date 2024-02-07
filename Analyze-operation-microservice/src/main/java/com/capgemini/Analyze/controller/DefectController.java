package com.capgemini.Analyze.controller;

import com.capgemini.Analyze.annotations.FeatureFlagAnnotation;
import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/defects")
public class DefectController {

    @Autowired
    private DefectService defectService;
    

    @FeatureFlagAnnotation(featureName = "defects")
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

	@RequestMapping(value = "/deleteDefects/solution/{solutionId}/defects", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> deleteDefectsBySolutionIdAndDefectIds(@PathVariable Long solutionId, @RequestParam List<Long> defectIds) {
        List<Long> failedDeletions = defectService.deleteDefectsBySolutionIdAndDefectIds(solutionId, defectIds);

        if (!failedDeletions.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO(false, "Failed to delete defects with IDs " + failedDeletions, "Deletion error");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            ResponseDTO responseDTO = new ResponseDTO(true, "Defects with IDs " + defectIds + " deleted successfully");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

}
