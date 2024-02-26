package com.capgemini.Analyze.dto;

public class AzureADOBugMasterDTO {
	private String orgName;
	private String projectName;
	private String pat;
	private Long SolutionId;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	public Long getSolutionId() {
		return SolutionId;
	}
	public void setSolutionId(Long solutionId) {
		SolutionId = solutionId;
	}
	
	

}
