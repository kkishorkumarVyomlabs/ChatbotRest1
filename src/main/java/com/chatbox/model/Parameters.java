package com.chatbox.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameters {
    private String cardNo;
	private String otp;

    @Override
    public String toString()
    {
        return "ClassPojo [OTP = "+otp+", CardNo = "+cardNo+"]";
    }

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
