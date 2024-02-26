package com.capgemini.Analyze.dto;

public class DeleteModelDTO {
    private String api_engine_model;
    private String llm_name;
    private String associated_organization;
    private String key;


    public String getApi_engine_model() {
        return api_engine_model;
    }
    public void setApi_engine_model(String api_engine_model) {
        this.api_engine_model = api_engine_model;
    }
    public String getLlm_name() {
        return llm_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLlm_name(String llm_name) {
        this.llm_name = llm_name;
    }
    public String getAssociated_organization() {
        return associated_organization;
    }
    public void setAssociated_organization(String associated_organization) {
        this.associated_organization = associated_organization;
    }


    public String getLlmName() {
        return llm_name + "_" + api_engine_model + "_" + associated_organization;
    }

    
}
