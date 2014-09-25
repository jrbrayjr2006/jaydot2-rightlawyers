/**
 * 
 */
package com.rightlawyers.rightlawyersmobile.to;

import java.util.ArrayList;
import java.util.LinkedList;

import android.net.Uri;

/**
 * @author james_r_bray
 *
 */
public class AccidentTO {
	
	// General fields
	public String fullname = "";
	public String email = "";
	public String phone = "";
	
	// Traffic Ticket fields
	public String citationNo = "";
	public String cityCounty = "";
	public String courtDate = "";
	
	
	public String otherName = "";
	public String otherPhone = "";
	public String otherEmail = "";
	public String yourMake = "";
	public String yourModel = "";
	public String yourLicense = "";
	public String otherMake = "";
	public String otherModel = "";
	public String otherLicense = "";
	public String officerName = "";
	public String reportNumber = "";
	
	public String notes = "";
	
	public ArrayList<Uri> yourCarImages;
	public ArrayList<Uri> otherCarImages;

	/**
	 * 
	 */
	public AccidentTO() {
		// TODO Auto-generated constructor stub
		yourCarImages = new ArrayList<Uri>();
		otherCarImages = new ArrayList<Uri>();
	}
	
	public AccidentTO(LinkedList<String> data) {
		Object[] values = data.toArray();
		for(String val: data) {
			
		}
	}

}
