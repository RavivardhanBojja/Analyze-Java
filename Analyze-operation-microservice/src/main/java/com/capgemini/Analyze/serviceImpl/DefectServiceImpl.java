package com.capgemini.Analyze.serviceImpl;

import com.capgemini.Analyze.entity.DefectMasterEntity;
import com.capgemini.Analyze.repository.DefectMasterRepository;
import com.capgemini.Analyze.service.DefectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefectServiceImpl implements DefectService {

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
}
