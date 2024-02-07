package com.capgemini.Analyze.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "FEATURE_FLAGS")
public class FeatureFlagEntity extends BasicEntity {

    @Id
    @Column(name = "feature_name", nullable = false, unique = true)
    private String featureName;

    @Column(name = "feature_description", length = 512)
    private String featureDescription;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;
    @Column(name = "last_updated_on", nullable = false)
    private LocalDateTime lastUpdatedOn;
    @Column
    private LocalDateTime createdOn;
    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getDescription() {
        return featureDescription;
    }

    public void setDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}
