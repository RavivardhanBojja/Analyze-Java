package com.capgemini.Analyze.dto;

public class DefectMasterDTO {

    private Long bugId;
    private String title;
    private String state;
    private String reason;
    private String area;
    private String iterationPath;
    private String assignedTo;
    private String comment;
    private String createdBy;
    private String createdDate;
    private String closedBy;
    private String closedDate;
    private String closingComment;
    private String reproSteps;
    private String severity;
    private Long storyPoints;
    private Long efforts;
    private Long parentFeatureId;
    private String parentFeatureTitle;
    private Long solutionId;
    
	public Long getBugId() {
		return bugId;
	}
	public void setBugId(Long bugId) {
		this.bugId = bugId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIterationPath() {
		return iterationPath;
	}
	public void setIterationPath(String iterationPath) {
		this.iterationPath = iterationPath;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getClosingComment() {
		return closingComment;
	}
	public void setClosingComment(String closingComment) {
		this.closingComment = closingComment;
	}
	public String getReproSteps() {
		return reproSteps;
	}
	public void setReproSteps(String reproSteps) {
		this.reproSteps = reproSteps;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Long getStoryPoints() {
		return storyPoints;
	}
	public void setStoryPoints(Long storyPoints) {
		this.storyPoints = storyPoints;
	}
	public Long getEfforts() {
        return efforts != null ? efforts : 0L; 
    }

    public void setEfforts(Long efforts) {
        this.efforts = efforts;
    }
	public Long getParentFeatureId() {
		return parentFeatureId;
	}
	public void setParentFeatureId(Long parentFeatureId) {
		this.parentFeatureId = parentFeatureId;
	}
	public String getParentFeatureTitle() {
		return parentFeatureTitle;
	}
	public void setParentFeatureTitle(String parentFeatureTitle) {
		this.parentFeatureTitle = parentFeatureTitle;
	}
	public Long getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(Long solutionId) {
		this.solutionId = solutionId;
	}


}
