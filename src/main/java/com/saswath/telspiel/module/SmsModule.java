package com.saswath.telspiel.module;

public class SmsModule {

	private String dest;
	private int otp;

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "SmsModule [dest=" + dest + ", otp=" + otp + "]";
	}

}
