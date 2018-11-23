package app.common.application.dto;

import app.common.application.enumeration.RequestBodyType;

public class RequestBaseDto {
	protected RequestBodyType requestBodyType;

	public RequestBodyType getRequestBodyType() {
		return requestBodyType;
	}

	public void setRequestBodyType(RequestBodyType requestBodyType) {
		this.requestBodyType = requestBodyType;
	}
}
