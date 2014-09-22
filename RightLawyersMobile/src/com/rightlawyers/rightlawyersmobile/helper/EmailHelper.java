/**
 * 
 */
package com.rightlawyers.rightlawyersmobile.helper;

import com.rightlawyers.rightlawyersmobile.to.AccidentTO;

/**
 * @author james_r_bray
 *
 */
public class EmailHelper {
	
	public EmailHelper() {}
	
	/**
	 * Constructs the body of the email message
	 * @return
	 */
	public String buildEmailBodyTrafficTicket(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		
		//TODO add logic to support building email body
		body.append("Traffic Ticket\n\n");
    	body.append(buildEmailBodyBasicInfo(ato));
    	body.append("\nOther Name: " + ato.otherName);
    	body.append("\nOther Email:" + ato.otherEmail);
    	body.append("\nOther Phone:" + ato.otherPhone);
    	body.append("\n\nCar Info\n\n");
    	body.append("Your Make: " + ato.yourMake);
    	body.append("\nYour Model: " + ato.yourModel);
    	body.append("\nYour License/Year: " + ato.yourLicense);
    	body.append("\nOther Make: " + ato.otherMake);
    	body.append("\nOther Model: " + ato.otherModel);
    	body.append("\nOther License/Year: " + ato.otherLicense);
    	body.append("\n\nPolice Info:\n\n");
    	body.append("Officer Name: " + ato.officerName);
    	body.append("\nReport Number: " + ato.reportNumber);
    	body.append("\nCity/County: " + ato.cityCounty);
    	body.append("\n\nNotes\n\n" + ato.notes);
    	
		return body.toString();
	}
	
	public String buildEmailBodyCarAccident(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Accident Report\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		//TODO add email body content
		
		return body.toString();
	}
	
	public String buildEmailBodyPropertyDamage(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Accident Report\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		//TODO add email body content
		
		return body.toString();
	}
	
	public String buildEmailBodyPoliceReport(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Accident Report\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		//TODO add email body content
		
		return body.toString();
	}
	
	public String buildEmailBodyFamilyLaw(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Family Law\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		//TODO add email body content
		
		return body.toString();
	}
	
	public String buildEmailBodyMedicalPainLog(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Medical Pain Log\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		//TODO add email body content
		
		return body.toString();
	}
	
	public String buildEmailBodyImportantDocs(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Important Documents\n\n");
		//TODO add email body content
		
		return body.toString();
	}
	
	private String buildEmailBodyBasicInfo(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Your Name: " + ato.fullname);
    	body.append("\nYour Email: " + ato.email);
    	body.append("\nYour Phone: " + ato.phone);
		//TODO add email body content
		
		return body.toString();
	}

}
