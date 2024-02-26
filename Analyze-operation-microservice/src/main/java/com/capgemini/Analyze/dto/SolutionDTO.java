package com.capgemini.Analyze.dto;

public class SolutionDTO {

    private String datamodelName;  
    private String identifier;
    private long solutionId;
    private String applicationName;  
    private String shortDescription;
    private String owner;
    private String applicationType;
    private String architectureType;  
    private long portfolioId;  
    private String cmdbInfoUrl;
    private String testEnvironmentUrl;
    private String llmName;
    private PersonalAccessTokenDTO personalAccessTokenDTO;
    
	public String getDatamodelName() {
		return datamodelName;
	}
	public void setDatamodelName(String datamodelName) {
		this.datamodelName = datamodelName;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public long getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(long solutionId) {
		this.solutionId = solutionId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getArchitectureType() {
		return architectureType;
	}
	public void setArchitectureType(String architectureType) {
		this.architectureType = architectureType;
	}
	public long getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getCmdbInfoUrl() {
		return cmdbInfoUrl;
	}
	public void setCmdbInfoUrl(String cmdbInfoUrl) {
		this.cmdbInfoUrl = cmdbInfoUrl;
	}
	public String getTestEnvironmentUrl() {
		return testEnvironmentUrl;
	}
	public void setTestEnvironmentUrl(String testEnvironmentUrl) {
		this.testEnvironmentUrl = testEnvironmentUrl;
	}
	public String getLlmName() {
		return llmName;
	}
	public void setLlmName(String llmName) {
		this.llmName = llmName;
	}
	public PersonalAccessTokenDTO getPersonalAccessTokenDTO() {
		return personalAccessTokenDTO;
	}
	public void setPersonalAccessTokenDTO(PersonalAccessTokenDTO personalAccessTokenDTO) {
		this.personalAccessTokenDTO = personalAccessTokenDTO;
	}
	
	   
	}
