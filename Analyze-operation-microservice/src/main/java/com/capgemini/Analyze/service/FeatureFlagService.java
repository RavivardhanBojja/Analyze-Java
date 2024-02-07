
package com.capgemini.Analyze.service;

import com.capgemini.Analyze.dto.FeatureFlagDTO;
import java.util.List;

public interface FeatureFlagService {
    FeatureFlagDTO getFeatureFlag(String featureName);

    FeatureFlagDTO createOrUpdateFeatureFlag(FeatureFlagDTO featureFlagDTO);

    void deleteFeatureFlag(String featureName);

    List<FeatureFlagDTO> getAllFeatureFlags();

    void enableFeature(String featureName, String updatedBy);

    void disableFeature(String featureName, String updatedBy);
}
