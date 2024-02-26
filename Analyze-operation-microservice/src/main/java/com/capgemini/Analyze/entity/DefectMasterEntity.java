package com.capgemini.Analyze.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "defects_master")
public class DefectMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bug_id", nullable = false)
    private Long bugId;

    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private String state;

    @Column(name = "reason")
    private String reason;

    @Column(name = "area")
    private String area;

    @Column(name = "iteration_path")
    private String iterationPath;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "closed_by")
    private String closedBy;

    @Column(name = "closed_date")
    private Date closedDate;

    @Column(name = "closing_comment", columnDefinition = "TEXT")
    private String closingComment;

    @Column(name = "repro_steps", columnDefinition = "TEXT")
    private String reproSteps;

    @Column(name = "severity")
    private String severity;

    @Column(name = "story_points")
    private Long storyPoints;

    @Column(name = "efforts")
    private Long efforts;

    @Column(name = "parent_feature_id")
    private Long parentFeatureId;

    @Column(name = "parent_feature_title")
    private String parentFeatureTitle;

    @Column(name = "solution_id", nullable = false)
    private Long solutionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
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
		return efforts;
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
