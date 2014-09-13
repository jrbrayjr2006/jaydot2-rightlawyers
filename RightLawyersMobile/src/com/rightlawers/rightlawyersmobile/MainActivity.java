package com.rightlawers.rightlawyersmobile;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.rightlawyers.rightlawyersmobile.helper.UtilHelper;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        
        
        FragmentManager fragmentManager = getFragmentManager();
        Fragment splashScreenFragment = fragmentManager.findFragmentById(R.id.splash_fragment);
        if(splashScreenFragment == null) {
        	splashScreenFragment = new SplashScreenFragment();
        	fragmentManager.beginTransaction().add(R.id.container, splashScreenFragment).commit();
        }
        setTitle("Right Lawyers");

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }
    
    /**
     * Get text based on the index of the navigation array
     * @param number
     */
    public void onSectionAttached(int number) {
    	int index = number - 1;
    	mTitle = getResources().getStringArray(R.array.navigation_items)[index];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Call law firm
     */
    private void call() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.rightlawyers_phone)));
            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("", getResources().getString(R.string.call_failed_message), e);
        }
    }
    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        
        private static final int BUTTON_SPACING = 60;
        
        String  emailSubjectText;
        
        /**
         * List to hold dynamically genrated fields
         */
        List<EditText> allFields;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
        
        /**
         * Create fields on fragment
         */
        public void onActivityCreated(Bundle savedInstanceState) {
        	super.onActivityCreated(savedInstanceState);
        	UtilHelper util = new UtilHelper();
        	int screenHeight = getDeviceScreenHeight();
        	FrameLayout fragmentLayout = (FrameLayout)getActivity().findViewById(R.id.container);
        	String[] generalFields = getResources().getStringArray(R.array.general_field_name_items);
        	String[] trafficTicketFields = getResources().getStringArray(R.array.traffic_ticket_items);
        	String[] carAccidentFields = getResources().getStringArray(R.array.car_accident);
        	String[] carAccidentPropertyDamageClaimFields = getResources().getStringArray(R.array.property_damage_claim);
        	String[] carAccidentPoliceReport = getResources().getStringArray(R.array.police_report);
        	String[] familyLawFields = getResources().getStringArray(R.array.family_law_items);
        	// get the index of the screen
        	int screenId = (Integer)this.getArguments().get(ARG_SECTION_NUMBER);
        	// create array of common and screen specific fields
        	String[] fields = {};
        	
        	switch(screenId) {
        	case 1:
        		Log.v("Default Option", "Splash screen...");
        		break;
        	case 2:
        		fields = util.concat(generalFields,trafficTicketFields);
        		emailSubjectText = ""; //TODO
        		break;
        	case 3:
        		fields = util.concat(generalFields,carAccidentFields);
        		emailSubjectText = getResources().getString(R.string.accident_report);
        		break;
        	case 4:
        		fields = util.concat(generalFields,carAccidentFields);
        		emailSubjectText = ""; //TODO
        		break;
        	case 5:
        		fields = util.concat(generalFields,carAccidentPropertyDamageClaimFields);
        		emailSubjectText = ""; //TODO
        		break;
        	case 6:
        		fields = util.concat(generalFields,carAccidentPoliceReport);
        		emailSubjectText = ""; //TODO
        		break;
        	case 7:
        		fields = util.concat(generalFields,familyLawFields);
        		emailSubjectText = ""; //TODO
        		break;
        	default:
        		emailSubjectText = "";
        		//fields = generalFields;
        	}
        	
        	// create array of common and screen specific fields
        	//String[] fields = util.concat(generalFields,trafficTicketFields);
        	
        	int i = 0;
        	int positionY = 10;
        	allFields = new ArrayList<EditText>();
        	// loop through fields
        	for(String field: fields) {
        		i++;
	        	EditText fieldEditText = new EditText(getActivity());
	        	allFields.add(fieldEditText);
	            fieldEditText.setId(i);
	            fieldEditText.setHint(field); // get field hint from xml
	            fieldEditText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	            fieldEditText.setY(positionY);
	            fieldEditText.setBackgroundColor(getResources().getColor(R.color.grey));
	            float customAlpha = Float.valueOf("0.75");
	            fieldEditText.setAlpha(customAlpha);
	            positionY = positionY + 50;
	            fragmentLayout.addView(fieldEditText);
        	}
            
            // create submission button
        	if(screenId > 1) {
	            Button submitButton = new Button(getActivity());
	            submitButton.setId(i + 1);
	            submitButton.setText(getResources().getString(R.string.submit_button_text));
	            submitButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	            int y = (int) (getDeviceScreenHeight() - (screenHeight * 0.25));  //TODO make 200 a calculated value
	            submitButton.setY(y);
	            submitButton.setOnClickListener(new OnClickListener() {
	
					@Override
					public void onClick(View v) {
						//sendEmail();  //TODO uncomment this
						String name = allFields.get(0).getText().toString(); //field can be retrieve from array
						Toast.makeText(getActivity(), name + " submitted form.", Toast.LENGTH_SHORT).show();
					}
	            	
	            });
	            fragmentLayout.addView(submitButton);
        	} else {
        		int verticalPosition = screenHeight/2;
        		// generate call attorney button
        		Button callButton = new Button(getActivity());
	            callButton.setId(i + 1);
	            callButton.setText(getResources().getString(R.string.call_attorney));
	            callButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	            int y = verticalPosition;
	            callButton.setY(verticalPosition);
	            callButton.setOnClickListener(new OnClickListener() {
	
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "Calling attorney.", Toast.LENGTH_SHORT).show();
						
					}
	            	
	            });
	            fragmentLayout.addView(callButton);
	            
	            //generate our website button
	            Button websiteButton = new Button(getActivity());
	            websiteButton.setId(i + 1);
	            websiteButton.setText(getResources().getString(R.string.website_btn_title));
	            websiteButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	            verticalPosition = verticalPosition + BUTTON_SPACING;
	            websiteButton.setY(verticalPosition);
	            websiteButton.setOnClickListener(new OnClickListener() {
	
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "Go To Website....", Toast.LENGTH_SHORT).show();
						
					}
	            	
	            });
	            fragmentLayout.addView(websiteButton);
	            
	            //generate share app button
	            Button shareButton = new Button(getActivity());
	            shareButton.setId(i + 1);
	            shareButton.setText(getResources().getString(R.string.share_app));
	            shareButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	            verticalPosition = verticalPosition + BUTTON_SPACING;
	            shareButton.setY(verticalPosition);
	            shareButton.setOnClickListener(new OnClickListener() {
	
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "Sharing app...", Toast.LENGTH_SHORT).show();
						
					}
	            	
	            });
	            fragmentLayout.addView(shareButton);
        	}
        	
        }
        
        /**
         * send form data via email
         */
        private void sendEmail() {
        	Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("text/plain");
			emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubjectText);
			//TODO complete method
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
        
        /**
         * Get the screen height in order to calculate the position of view objects
         * @return
         */
        private int getDeviceScreenHeight() {
        	Display display = getActivity().getWindowManager().getDefaultDisplay();
        	//DisplayMetrics metrics = new DisplayMetrics();
        	Point size = new Point();
        	display.getSize(size);
        	return size.y;
        }
    }

}
