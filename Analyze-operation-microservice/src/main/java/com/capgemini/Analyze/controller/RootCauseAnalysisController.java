package com.capgemini.Analyze.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.dto.SignatureDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.service.DefectService;
import com.capgemini.Analyze.service.RootCauseAnalysisService;

@RestController
@RequestMapping("/RAC")
public class RootCauseAnalysisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RootCauseAnalysisController.class);
	
	  @Autowired
	  private RootCauseAnalysisService RACService;
	
	@RequestMapping(value = "/getOpenDefects/solution/{solutionId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getOpenDefectsBySolutionId(@PathVariable Long solutionId) {
        List<DefectMasterEntity> defects = RACService.getOpenDefectsBySolutionId(solutionId);
        
        if (defects.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO(null, "No open defects found for the solution ID: " + solutionId, "Not Found");
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        } else {
            ResponseDTO responseDTO = new ResponseDTO(defects, "Open Defects retrieved successfully",null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }
	

}