package com.capgemini.Analyze.serviceImpl;

import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.repository.DefectMasterRepository;
import com.capgemini.Analyze.service.DefectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DefectServiceImpl implements DefectService {
	
	private static final Logger log = LoggerFactory.getLogger(DefectServiceImpl.class);

    @Autowired
    private DefectMasterRepository defectRepository;

    
    @Override
    public List<DefectMasterEntity> getAllDefectsBySolutionId(Long solutionId) {
        return defectRepository.findBySolutionId(solutionId);
    }
 

    @Override
    @Transactional
    public List<Long> deleteDefectsBySolutionIdAndDefectIds(Long solutionId, List<Long> defectIds) {
        List<Long> failedDeletions = new ArrayList<>();

        for (Long defectId : defectIds) {
            try {
                DefectMasterEntity defect = defectRepository.findBySolutionIdAndBugId(solutionId, defectId);
                if (defect != null) {
                    defectRepository.delete(defect);
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
    public ResponseEntity<ResponseDTO> saveDefectswithOverwriteExisting(List<DefectMasterDTO> defectDTOs) {
        try {
            for (DefectMasterDTO defectDTO : defectDTOs) {
                DefectMasterEntity existingDefect = defectRepository.findBySolutionIdAndBugId(defectDTO.getSolutionId(), defectDTO.getBugId());

                if (existingDefect != null) {
                    updateExistingDefect(existingDefect, defectDTO);
                } else {
                    DefectMasterEntity newDefect = createNewDefect(defectDTO);
                    defectRepository.save(newDefect);
                }
            }
            return new ResponseEntity<>(new ResponseDTO(true, "Defects saved successfully.", null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseDTO(false, "Failed to save defects.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void updateExistingDefect(DefectMasterEntity existingDefect, DefectMasterDTO defectDTO) {
        if (defectDTO.getBugId() != null) {
            existingDefect.setBugId(defectDTO.getBugId());
        }
        existingDefect.setTitle(defectDTO.getTitle());
        existingDefect.setState(defectDTO.getState());
        existingDefect.setReason(defectDTO.getReason());
        existingDefect.setArea(defectDTO.getArea());
        existingDefect.setIterationPath(defectDTO.getIterationPath());
        existingDefect.setAssignedTo(defectDTO.getAssignedTo());
        existingDefect.setComment(defectDTO.getComment());
        existingDefect.setCreatedBy(defectDTO.getCreatedBy());
        existingDefect.setCreatedDate(convertStringToDate(defectDTO.getCreatedDate()));
        existingDefect.setClosedBy(defectDTO.getClosedBy());
        existingDefect.setClosedDate(convertStringToDate(defectDTO.getClosedDate()));
        existingDefect.setClosingComment(defectDTO.getClosingComment());
        existingDefect.setReproSteps(defectDTO.getReproSteps());
        existingDefect.setSeverity(defectDTO.getSeverity());
        existingDefect.setStoryPoints(defectDTO.getStoryPoints());
        existingDefect.setEfforts(defectDTO.getEfforts());
        existingDefect.setParentFeatureId(defectDTO.getParentFeatureId());
        existingDefect.setParentFeatureTitle(defectDTO.getParentFeatureTitle());

        defectRepository.save(existingDefect);
    }

    private DefectMasterEntity createNewDefect(DefectMasterDTO defectDTO) {
        DefectMasterEntity newDefect = new DefectMasterEntity();
        newDefect.setSolutionId(defectDTO.getSolutionId());
        newDefect.setBugId(defectDTO.getBugId());
        newDefect.setTitle(defectDTO.getTitle());
        newDefect.setState(defectDTO.getState());
        newDefect.setReason(defectDTO.getReason());
        newDefect.setArea(defectDTO.getArea());
        newDefect.setIterationPath(defectDTO.getIterationPath());
        newDefect.setAssignedTo(defectDTO.getAssignedTo());
        newDefect.setComment(defectDTO.getComment());
        newDefect.setCreatedBy(defectDTO.getCreatedBy());
        newDefect.setCreatedDate(convertStringToDate(defectDTO.getCreatedDate()));
        newDefect.setClosedBy(defectDTO.getClosedBy());
        newDefect.setClosedDate(convertStringToDate(defectDTO.getClosedDate()));
        newDefect.setClosingComment(defectDTO.getClosingComment());
        newDefect.setReproSteps(defectDTO.getReproSteps());
        newDefect.setSeverity(defectDTO.getSeverity());
        newDefect.setStoryPoints(defectDTO.getStoryPoints());
        newDefect.setEfforts(defectDTO.getEfforts());
        newDefect.setParentFeatureId(defectDTO.getParentFeatureId());
        newDefect.setParentFeatureTitle(defectDTO.getParentFeatureTitle());

        return newDefect;
    }
  
    private Date convertStringToDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null; 
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    @Override
    public ResponseEntity<ResponseDTO> saveDefects(List<DefectMasterDTO> defectDTOs) {
        try {
            for (DefectMasterDTO defectDTO : defectDTOs) {
                DefectMasterEntity existingDefect = defectRepository.findBySolutionIdAndBugId(defectDTO.getSolutionId(), defectDTO.getBugId());

                if (existingDefect == null) {
                    DefectMasterEntity newDefect = createNewDefect(defectDTO);
                    defectRepository.save(newDefect);
                } else {
                	log.info("Defect with Solution ID: {} and Bug ID: {} already exists. Skipping...", defectDTO.getSolutionId(), defectDTO.getBugId());
                }
            }
            return new ResponseEntity<>(new ResponseDTO(true, "Defects saved successfully.", null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseDTO(false, "Failed to save defects.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @Override
    public List<DefectMasterEntity> getClosedDefectsBySolutionId(Long solutionId) {
        return defectRepository.findClosedDefectsBySolutionId(solutionId);
    }
    
    
}