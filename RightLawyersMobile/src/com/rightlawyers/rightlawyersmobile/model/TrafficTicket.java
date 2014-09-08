package com.rightlawyers.rightlawyersmobile.model;

import java.util.Date;

/**
 * <pre>
 * This will be used in a future 1.1+ release
 * </pre>
 * @author james_r_bray
 * @version 0.1
 *
 */
public class TrafficTicket {
	
	private String firstName;
	private String lastName;
	private String cellPhone;
	private String email;
	private String citationNumber;
	private String municipality;
	private Date courtDate;
	private Byte[] photo;
	
	public TrafficTicket() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCitationNumber() {
		return citationNumber;
	}

	public void setCitationNumber(String citationNumber) {
		this.citationNumber = citationNumber;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public Date getCourtDate() {
		return courtDate;
	}

	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}

	public Byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}
	
}
