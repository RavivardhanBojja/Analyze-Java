package com.capgemini.Analyze.serviceImpl;

import com.capgemini.Analyze.dto.FeatureFlagDTO;
import com.capgemini.Analyze.entity.FeatureFlagEntity;
import com.capgemini.Analyze.repository.FeatureFlagMasterRepository;
import com.capgemini.Analyze.service.FeatureFlagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureFlagServiceImpl implements FeatureFlagService {

    private final FeatureFlagMasterRepository featureFlagRepository;

    public FeatureFlagServiceImpl(FeatureFlagMasterRepository featureFlagRepository) {
        this.featureFlagRepository = featureFlagRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public FeatureFlagDTO getFeatureFlag(String featureName) {
        return featureFlagRepository.findById(featureName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public FeatureFlagDTO createOrUpdateFeatureFlag(FeatureFlagDTO featureFlagDTO) {
        FeatureFlagEntity featureFlag = convertToEntity(featureFlagDTO);
        featureFlag.setLastUpdatedBy(featureFlagDTO.getLastUpdatedBy());
        featureFlag.setCreatedOn(featureFlagDTO.getCreatedOn());
        featureFlag.setDescription(featureFlagDTO.getFeatureDescription());
        featureFlag.setCreatedOn(LocalDateTime.now());

        featureFlag = featureFlagRepository.save(featureFlag);
        return convertToDTO(featureFlag);
    }

    @Override
    @Transactional
    public void deleteFeatureFlag(String featureName) {
        featureFlagRepository.deleteById(featureName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeatureFlagDTO> getAllFeatureFlags() {
        return featureFlagRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void enableFeature(String featureName, String updatedBy) {
        featureFlagRepository.findById(featureName).ifPresent(flag -> {
            flag.setEnabled(true);
            flag.setLastUpdatedOn(LocalDateTime.now());
            flag.setLastUpdatedBy(updatedBy);
            featureFlagRepository.save(flag);
        });
    }

    @Override
    @Transactional
    public void disableFeature(String featureName, String updatedBy) {
        featureFlagRepository.findById(featureName).ifPresent(flag -> {
            flag.setEnabled(false);
            flag.setLastUpdatedOn(LocalDateTime.now());
            flag.setLastUpdatedBy(updatedBy);
            featureFlagRepository.save(flag);
        });
    }

    private FeatureFlagDTO convertToDTO(FeatureFlagEntity featureFlag) {
        FeatureFlagDTO dto = new FeatureFlagDTO();
        dto.setFeatureName(featureFlag.getFeatureName());
        dto.setEnabled(featureFlag.isEnabled());
        dto.setLastUpdatedOn(featureFlag.getLastUpdatedOn());
        dto.setLastUpdatedBy(featureFlag.getLastUpdatedBy());
        dto.setFeatureDescription(featureFlag.getDescription());
        dto.setCreatedOn(featureFlag.getCreatedOn());
        return dto;
    }

    private FeatureFlagEntity convertToEntity(FeatureFlagDTO dto) {
        FeatureFlagEntity featureFlag = new FeatureFlagEntity();
        featureFlag.setFeatureName(dto.getFeatureName());
        featureFlag.setDescription(dto.getFeatureDescription());
        featureFlag.setCreatedOn(dto.getCreatedOn() != null ? dto.getCreatedOn() : LocalDateTime.now());
        featureFlag.setEnabled(dto.isEnabled());
        featureFlag.setLastUpdatedOn(dto.getLastUpdatedOn() != null ? dto.getLastUpdatedOn() : LocalDateTime.now());
        
        return featureFlag;
    }
}
