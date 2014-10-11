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
	
	public EmailHelper() {
		//
	}
	
	/**
	 * Constructs the body of the email message
	 * @return
	 */
	public String buildEmailBodyTrafficTicket(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		
		body.append("Traffic Ticket\n\n");
    	body.append(buildEmailBodyBasicInfo(ato));
    	//body.append("\nOther Name: " + ato.otherName);
    	//body.append("\nOther Email: " + ato.otherEmail);
    	//body.append("\nOther Phone: " + ato.otherPhone);
    	body.append("\n\nTicket Information\n\n");
    	body.append("\nCitation Number: " + ato.citationNo);
    	body.append("\nCity or County of Court: " + ato.cityCounty);
    	body.append("\nCourt Date: " + ato.courtDate);
    	body.append("\nReferred by: " + ato.howReferred);
    	
		return body.toString();
	}
	
	public String buildEmailBodyCarAccident(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Accident Report\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		body.append("Your Vehicle Make: " + ato.yourMake);
    	body.append("\nYour Vehicle Model: " + ato.yourModel);
    	body.append("\nOther Name: " + ato.otherName);
    	body.append("\nOther Email: " + ato.otherEmail);
    	body.append("\nOther Phone: " + ato.otherPhone);
		return body.toString();
	}
	 
	/**
	 * Builds the message body for property damage claim email forms
	 * @param ato
	 * @return
	 */
	public String buildEmailBodyPropertyDamage(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Accident Report - Property Damage\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		body.append("\nYour Insurance Company: ");
		body.append(ato.yourInsuranceCompany);
		body.append("\nYour Policy Number: ");
		body.append(ato.yourPolicyNumber);
		body.append("\nOther Insurance Company: ");
		body.append(ato.otherInsuranceCompany);
		body.append("\nOther Policy Number: ");
		body.append(ato.otherPolicyNumber);
		
		return body.toString();
	}
	
	public String buildEmailBodyPoliceReport(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Accident Report - Police Report\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		body.append("\nPolice Department");
		//TODO add email body content
		body.append("\nPolice Department: ");
		body.append(ato.policeDepartment);
		body.append("\nPolice Report Number: ");
		body.append(ato.reportNumber);
		
		return body.toString();
	}
	
	public String buildEmailBodyFamilyLaw(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Family Law\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		body.append("\n");
		body.append("\nYears Married: ");
		body.append(ato.yearsMarried);
		body.append("\nNumber of Children: ");
		body.append(ato.numberOfChildren);
		body.append("\nOwn Home? ");
		body.append(ato.ownHome);
		body.append("\nCurrently Living Together? ");
		body.append(ato.currentlyLivingTogether);
		body.append("\nIRA / Pension / 401(k): ");
		body.append(ato.iraPension401k);
		body.append("\nCombined Incomes: ");
		body.append(ato.combinedIncomes);
		
		return body.toString();
	}
	
	public String buildEmailBodyMedicalPainLog(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Medical Pain Log\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		body.append("\n");
		body.append("\nPain Description 1: ");
		body.append(ato.painDescription1);
		body.append("\nPain Description 2: ");
		body.append(ato.painDescription2);
		body.append("\nPain Description 3: ");
		body.append(ato.painDescription3);
		body.append("\nPain Description 4: ");
		body.append(ato.painDescription4);
		body.append("\nPain Description 5: ");
		body.append(ato.painDescription5);
		
		return body.toString();
	}
	
	public String buildEmailBodyImportantDocs(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Important Documents\n\n");
		body.append(buildEmailBodyBasicInfo(ato));
		
		return body.toString();
	}
	
	private String buildEmailBodyBasicInfo(AccidentTO ato) {
		StringBuffer body = new StringBuffer();
		body.append("Your Name: " + ato.fullname);
    	body.append("\nYour Email: " + ato.email);
    	body.append("\nYour Phone: " + ato.phone);
		body.append("\n\n");
		
		return body.toString();
	}

}
