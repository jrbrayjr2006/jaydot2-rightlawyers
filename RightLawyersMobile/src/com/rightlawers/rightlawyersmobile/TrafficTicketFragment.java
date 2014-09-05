/**
 * 
 */
package com.rightlawers.rightlawyersmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rightlawyers.rightlawyersmobile.model.TrafficTicket;

/**
 * @author james_r_bray
 *
 */
public class TrafficTicketFragment extends Fragment {
	
	protected TrafficTicket mTrafficTicket;
	public EditText txtName;
	public EditText txtPhone;
	public EditText txtEmail;
	public EditText txtCitation;
	public EditText txtCourtDate;
	public TextView txtWhichOne;
	public EditText txtCity;
	public String ticketType;
	public ImageButton imgCitation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_traffic_ticket, parent, false);
		
		//TODO  add controls here
		
		return v;
	}

}
