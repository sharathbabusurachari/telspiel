package com.saswath.telspiel.module;

public class Telspiel {

	private String username;
	private String dest;
	private String apikey;
	private String signature;
	private String msgtype;
	private String msgtxt;
	private String entityid;
	private String templateid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgtxt() {
		return msgtxt;
	}

	public void setMsgtxt(String msgtxt) {
		this.msgtxt = msgtxt;
	}

	public String getEntityid() {
		return entityid;
	}

	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	@Override
	public String toString() {
		return "Telspiel [username=" + username + ", dest=" + dest + ", apikey=" + apikey + ", signature=" + signature
				+ ", msgtype=" + msgtype + ", msgtxt=" + msgtxt + ", entityid=" + entityid + ", templateid="
				+ templateid + "]";
	}

}
