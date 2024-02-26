package com.capgemini.Analyze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.Analyze.entity.DefectSignatureMasterEntity;

public interface DefectSignatureMasterRepository extends JpaRepository<DefectSignatureMasterEntity, Long> {

	@Query("SELECT d FROM DefectSignatureMasterEntity d WHERE d.bugId = :bugId AND d.solutionId = :solutionId")
    DefectSignatureMasterEntity findByBugIdAndSolutionId(@Param("bugId") Long bugId, @Param("solutionId") Long solutionId);

}
