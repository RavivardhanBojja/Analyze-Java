package com.capgemini.Analyze.dto;

public class PersonalAccessTokenDTO {

    private Long id;
    private String key;
    private Long solutionId;
    private Long userId;  
    private String azureADOOrganization;
    private String azureADOProject;
    private String azureADOUrl;
    private String jiraProject;
    private String jiraUrl;
    private String jiraUsername;
    private String jiraApitoken;
    private String xrayurl;
    private String clientId;
	private String clientSecret;
	private Long WorkitemId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(Long solutionId) {
		this.solutionId = solutionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAzureADOOrganization() {
		return azureADOOrganization;
	}
	public void setAzureADOOrganization(String azureADOOrganization) {
		this.azureADOOrganization = azureADOOrganization;
	}
	public String getAzureADOProject() {
		return azureADOProject;
	}
	public void setAzureADOProject(String azureADOProject) {
		this.azureADOProject = azureADOProject;
	}
	public String getAzureADOUrl() {
		return azureADOUrl;
	}
	public void setAzureADOUrl(String azureADOUrl) {
		this.azureADOUrl = azureADOUrl;
	}
	public String getJiraProject() {
		return jiraProject;
	}
	public void setJiraProject(String jiraProject) {
		this.jiraProject = jiraProject;
	}
	public String getJiraUrl() {
		return jiraUrl;
	}
	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}
	public String getJiraUsername() {
		return jiraUsername;
	}
	public void setJiraUsername(String jiraUsername) {
		this.jiraUsername = jiraUsername;
	}
	public String getJiraApitoken() {
		return jiraApitoken;
	}
	public void setJiraApitoken(String jiraApitoken) {
		this.jiraApitoken = jiraApitoken;
	}
	public String getXrayurl() {
		return xrayurl;
	}
	public void setXrayurl(String xrayurl) {
		this.xrayurl = xrayurl;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public Long getWorkitemId() {
		return WorkitemId;
	}
	public void setWorkitemId(Long workitemId) {
		WorkitemId = workitemId;
	}
	
	

	
}