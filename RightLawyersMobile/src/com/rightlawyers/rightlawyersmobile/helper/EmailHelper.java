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
	public String buildEmailBody(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		
		//TODO add logic to support building email body
		body.append("Accident Report\n\n");
    	body.append("Your Name: " + ato.fullname);
    	body.append("\nYour Email: " + ato.email);
    	body.append("\nYour Phone: " + ato.phone);
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

}
