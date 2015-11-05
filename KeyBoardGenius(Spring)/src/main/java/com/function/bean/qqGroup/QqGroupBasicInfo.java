package com.function.bean.qqGroup;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 基本信息
 * @author zqy
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QqGroupBasicInfo {

	private String code;
	
	private QqGroupProfile data;
	
	private String default1;
	
	private String message;
	
	private String subcode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public QqGroupProfile getData() {
		return data;
	}

	public void setData(QqGroupProfile data) {
		this.data = data;
	}

	public String getDefault1() {
		return default1;
	}

	public void setDefault1(String default1) {
		this.default1 = default1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
}
