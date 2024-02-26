package com.capgemini.Analyze.service;

import java.util.List;

import com.capgemini.Analyze.dto.AzureADOBugMasterDTO;
import com.capgemini.Analyze.dto.DefectMasterDTO;

public interface AzureADOService {

	public List<DefectMasterDTO> getBugInfoAzureADO(AzureADOBugMasterDTO azureADOBugMasterDTO);


}
