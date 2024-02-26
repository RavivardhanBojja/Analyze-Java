package com.capgemini.Analyze.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.SignatureDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.entity.DefectSignatureMasterEntity;
import com.capgemini.Analyze.repository.DefectMasterRepository;
import com.capgemini.Analyze.repository.DefectSignatureMasterRepository;
import com.capgemini.Analyze.service.RootCauseAnalysisService;

@Service
public class RootCauseAnalysisServiceImpl implements RootCauseAnalysisService {
	
	private static final Logger log = LoggerFactory.getLogger(RootCauseAnalysisServiceImpl.class);

    @Autowired
    private DefectMasterRepository defectRepository;
    
    @Autowired
    private DefectSignatureMasterRepository defectSignatureMasterRepository;
   
    
    @Override
    public List<DefectMasterEntity> getOpenDefectsBySolutionId(Long solutionId) {
        return defectRepository.findOpenDefectsBySolutionId(solutionId);
    }
    
}