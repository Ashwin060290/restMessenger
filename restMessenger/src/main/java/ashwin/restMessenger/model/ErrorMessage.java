package ashwin.restMessenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errorMsg;
	private int errorCode;
	private String doc;
	
	
	public ErrorMessage() {
	}
	
	public ErrorMessage(String errorMsg, int errorCode, String doc) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
		this.doc = doc;
	}
	
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}

	
}
