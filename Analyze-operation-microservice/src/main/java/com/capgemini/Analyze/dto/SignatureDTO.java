package com.capgemini.Analyze.dto;

public class SignatureDTO {

    private DefectMasterDTO defectMasterDTO;
    private boolean signature;
    
    
	public DefectMasterDTO getDefectMasterDTO() {
		return defectMasterDTO;
	}
	public void setDefectMasterDTO(DefectMasterDTO defectMasterDTO) {
		this.defectMasterDTO = defectMasterDTO;
	}
	public boolean isSignature() {
		return signature;
	}
	public void setSignature(boolean signature) {
		this.signature = signature;
	}
	
	
  
}