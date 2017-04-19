package com.novelbio.portal.base.web;

public class ResultJson {
	boolean state;
	Object result;
	String message;

	public ResultJson() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultJson(boolean state, Object result) {
		super();
		this.state = state;
		this.result = result;
	}

	public ResultJson(boolean state, Object result, String message) {
		super();
		this.state = state;
		this.result = result;
		this.message = message;
	}

	public boolean isState() {
		return state;
	}

	public ResultJson setState(boolean state) {
		this.state = state;
		return this;
	}

	public Object getResult() {
		return result;
	}

	public ResultJson setResult(Object result) {
		this.result = result;
		return this;
	}

	public static ResultJson trueResp() {
		return new ResultJson(true, null);
	}

	public static ResultJson falseResp() {
		return new ResultJson(false, null, "");
	}

	public static ResultJson trueResp(Object result) {
		return new ResultJson(true, result);
	}

	public static ResultJson falseResp(String message) {
		return new ResultJson(false, null, message);
	}
}
