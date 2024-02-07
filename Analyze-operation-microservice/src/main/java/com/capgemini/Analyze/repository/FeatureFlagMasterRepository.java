package com.capgemini.Analyze.repository;

import com.capgemini.Analyze.entity.FeatureFlagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureFlagMasterRepository extends JpaRepository<FeatureFlagEntity, String> {
   
}
