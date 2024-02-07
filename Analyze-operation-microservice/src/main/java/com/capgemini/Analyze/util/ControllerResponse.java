package com.capgemini.Analyze.util;

import com.capgemini.Analyze.dto.ResponseDTO;

public class ControllerResponse {
	public static ResponseDTO response(Object o){
		ResponseDTO responseDTO=new ResponseDTO();
		responseDTO.setResponse(o);
		responseDTO.setMessage("Success");
		return responseDTO;
	}
}

