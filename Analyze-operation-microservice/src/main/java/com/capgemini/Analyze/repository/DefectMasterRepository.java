package com.capgemini.Analyze.repository;

import com.capgemini.Analyze.entity.DefectMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectMasterRepository extends JpaRepository<DefectMasterEntity, Long> {

    @Query("SELECT d FROM DefectMasterEntity d WHERE d.solutionId = :solutionId")
    List<DefectMasterEntity> findBySolutionId(Long solutionId);

    @Query("SELECT d FROM DefectMasterEntity d WHERE d.solutionId = :solutionId AND d.bugId = :defectId")
    DefectMasterEntity findBySolutionIdAndBugId(Long solutionId, Long defectId);
}
