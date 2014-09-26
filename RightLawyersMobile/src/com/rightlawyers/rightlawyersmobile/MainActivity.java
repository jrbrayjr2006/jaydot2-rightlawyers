package com.rightlawyers.rightlawyersmobile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.rightlawers.rightlawyersmobile.R;
import com.rightlawyers.rightlawyersmobile.helper.EmailHelper;
import com.rightlawyers.rightlawyersmobile.helper.UtilHelper;
import com.rightlawyers.rightlawyersmobile.to.AccidentTO;


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
    public void call() {
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
        
        private static final int BUTTON_SPACING = 80;
        
        private static final int FIELD_SPACING = 80;
        
        private static final int REQUEST_IMAGE_CAPTURE = 1;
        
        /**
         * Transfer object for data
         */
        AccidentTO ato = new AccidentTO();
        
        String  emailSubjectText;
        
        String emailBodyText;
        
        Button photoButton;
        
        boolean photoFlag = false;
        
        Uri photoCaptureUri;
        
        ImageView photoThumb;
        
        File photoFile;
        
        File photoDir;
        
        String photoFileName;
        
        String mCurrentPhotoPath;
        
        /**
         * List to hold dynamically genrated fields
         */
        List<EditText> allFields;
        
        EmailHelper emailHelper = new EmailHelper();

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
        @Override
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
        	String[] painLogFields = getResources().getStringArray(R.array.medical_pain_log);
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
        		emailSubjectText = getResources().getString(R.string.traffic_ticket);
        		photoFlag = true;
        		break;
        	case 3:
        		fields = util.concat(generalFields,carAccidentFields);
        		emailSubjectText = getResources().getString(R.string.accident_report);
        		photoFlag = true;
        		break;
        	case 4:
        		fields = util.concat(generalFields,carAccidentPropertyDamageClaimFields);
        		emailSubjectText = getResources().getString(R.string.property_damage_claim);
        		break;
        	case 5:
        		fields = util.concat(generalFields,carAccidentPoliceReport);
        		emailSubjectText = getResources().getString(R.string.police_report);
        		break;
        	case 6:
        		fields = util.concat(generalFields,familyLawFields);
        		emailSubjectText = getResources().getString(R.string.family_law);
        		break;
        	case 7:
        		fields = util.concat(generalFields,painLogFields);
        		emailSubjectText = getResources().getString(R.string.medical_pain_log); //TODO
        		break;
        	case 8:
        		fields = generalFields;
        		emailSubjectText = getResources().getString(R.string.important_documents); //TODO
        		photoFlag = true;
        		break;
        	default:
        		emailSubjectText = "Client Inquiry";
        	}
        	
        	int i = 0;
        	int positionY = 10;
        	int positionX = 20;
        	
        	// all fields generated
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
	            positionY = positionY + FIELD_SPACING;
	            fragmentLayout.addView(fieldEditText);
        	}
        	
        	// generate photo button only on certain forms
        	if(photoFlag == true) {
        		photoButton = generatePhotoButton(++i);
        		positionY = positionY + BUTTON_SPACING;
        		photoButton.setY(positionY);
        		photoButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						takePicture();
					}});
        		fragmentLayout.addView(photoButton);
        		
        		photoThumb = new ImageView(getActivity());
        		photoThumb.setLayoutParams(new LayoutParams(50,50));  //TODO test
        		photoThumb.setImageResource(R.drawable.camera_icon);
        		positionY = positionY + 90;
        		photoThumb.setY(positionY);
        		photoThumb.setX(positionX);
        		
        		fragmentLayout.addView(photoThumb);
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
						
						String name = allFields.get(0).getText().toString(); //field can be retrieve from array
						String phone = allFields.get(1).getText().toString();
						String email = allFields.get(2).getText().toString();
						
						ato.fullname = name;
						ato.phone = phone;
						ato.email = email;
						
						// get proper screen field values based on screen id
						int screenId = (Integer)getArguments().get(ARG_SECTION_NUMBER);
						switch(screenId) {
			        	case 1:
			        		Log.v("Default Option", "Splash screen...");
			        		break;
			        	case 2:
			        		emailSubjectText = getResources().getString(R.string.traffic_ticket);
			        		ato.citationNo = allFields.get(3).getText().toString();
			        		ato.cityCounty = allFields.get(4).getText().toString();
			        		ato.courtDate = allFields.get(5).getText().toString();
			        		emailBodyText = emailHelper.buildEmailBodyTrafficTicket(ato);
			        		break;
			        	case 3:
			        		emailSubjectText = getResources().getString(R.string.accident_report);
			        		ato.yourMake = allFields.get(3).getText().toString();
			        		ato.yourModel = allFields.get(4).getText().toString();
			        		emailBodyText = emailHelper.buildEmailBodyCarAccident(ato);
			        		break;
			        	case 4:
			        		emailSubjectText = ""; //TODO
			        		break;
			        	case 5:
			        		emailSubjectText = getResources().getString(R.string.property_damage_claim);
			        		
			        		emailBodyText = emailHelper.buildEmailBodyPropertyDamage(ato);
			        		break;
			        	case 6:
			        		emailSubjectText = getResources().getString(R.string.police_report);
			        		
			        		emailBodyText = emailHelper.buildEmailBodyPoliceReport(ato);
			        		break;
			        	case 7:
			        		emailSubjectText = getResources().getString(R.string.family_law);
			        		
			        		emailBodyText = emailHelper.buildEmailBodyFamilyLaw(ato);
			        		break;
						}
						
						
						
						sendEmail();
						sendEmail(emailSubjectText,emailBodyText);
						
						Toast.makeText(getActivity(), emailSubjectText, Toast.LENGTH_SHORT).show();
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
						try {
				            Intent callIntent = new Intent(Intent.ACTION_CALL);
				            callIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.rightlawyers_phone)));
				            startActivity(callIntent);
				        } catch (ActivityNotFoundException e) {
				            Log.e("", getResources().getString(R.string.call_failed_message), e);
				        }
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
	            	
	            	// open law firm website
					@Override
					public void onClick(View v) {
						String url = "http://" + getResources().getString(R.string.website_url);
				    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				    	startActivity(browserIntent);
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
						Intent sendIntent = new Intent();
						sendIntent.setAction(Intent.ACTION_SEND);
						sendIntent.putExtra(Intent.EXTRA_TEXT, "Go to Right Lawyers");
						sendIntent.setType("text/plain");
						startActivity(sendIntent);
					}
	            	
	            });
	            fragmentLayout.addView(shareButton);
        	}
        	
        }
        
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
        	super.onActivityResult(requestCode, resultCode, data);
        	if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        		Bundle extras = data.getExtras();
                //Bitmap imageBitmap = (Bitmap) extras.get("data");

	        	Uri contentUri = photoCaptureUri;
			    BitmapFactory.Options options = new BitmapFactory.Options();
			    options.inSampleSize = 4;
			    	
			    Bitmap imageBitmap = BitmapFactory.decodeFile( contentUri.getPath(), options );
			    Cursor cursor = getActivity().getContentResolver().query(data.getData(), null, null, null, null);
				cursor.moveToFirst();  //if not doing this, 01-22 19:17:04.564: ERROR/AndroidRuntime(26264): Caused by: android.database.CursorIndexOutOfBoundsException: Index -1 requested, with a size of 1
				int idx = cursor.getColumnIndex(ImageColumns.DATA);
				String fileSrc = cursor.getString(idx);
				photoCaptureUri=contentUri;
				if(photoThumb != null) {
                	photoThumb.setImageBitmap(imageBitmap);
                }
        	}
        }
        
        /**
         * send form data via email
         */
        private void sendEmail() {
        	Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("text/plain");
			//emailIntent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.rightlawyers_info_email));
			//TODO Remove test email
			// email addresses should be in a string array
			List<String> emailAddresses = new ArrayList<String>();
			emailAddresses.add(getResources().getString(R.string.test_email));
			emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.test_email)});
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubjectText);
			//TODO complete method
			String body = "get email body";
			emailIntent.putExtra(Intent.EXTRA_TEXT, body);
			
			startActivityForResult(Intent.createChooser(emailIntent, "Send Report"), 1000);
        }
        
        /**
         * <pre>
         * Create an email and send it with the incident report information
         * </pre>
         * @param emailSubject
         * @param emailBody
         */
        private void sendEmail(String emailSubject, String emailBody) {
        	Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("text/plain");
			List<String> emailAddresses = new ArrayList<String>();
			emailAddresses.add(getResources().getString(R.string.test_email));
			//emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.rightlawyers_info_email)});
			emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.test_email)});  //TODO remove when testing is done
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
			if(emailBody == null) {
				emailBody = "No email body available";
			}
			emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
			
			startActivityForResult(Intent.createChooser(emailIntent, "Send Report"), 1000);
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
        
        /**
         * 
         * @return
         */
        private Button generatePhotoButton(int id) {
        	Button photoButton = new Button(getActivity());
        	photoButton.setText(getResources().getString(R.string.new_photo));
        	photoButton.setId(id);
        	photoButton.setBackgroundColor(getResources().getColor(R.color.grey));
        	photoButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        	//photoButton.setBackground(getResources().getDrawable(R.drawable.camera_icon));
        	//TODO add camera icon to button
        	return photoButton;
        }
        
        /**
         * Pass in list of EditText objects and extract values
         * @param _fields
         */
        private void fieldMapper(List<EditText> _fields) {
        	//TODO make general
        	int i = 0;
        	_fields.get(i);
        }
        
        /**
         * 
         */
        private void takePicture() {
        	Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        	
        	// Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

        	
        	createDirectoryForPictures();
        	
        	//photoFile = new File(photoDir, photoFileName);
    	    this.photoCaptureUri=Uri.fromFile(photoFile);
        	if (photoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
        
        /**
         * Create the local storage area for photos
         */
        private void createDirectoryForPictures()
    	{
    	    photoDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getResources().getString(R.string.photo_storage_directory));
    	    if (!photoDir.exists())
    	    {
    	        photoDir.mkdir();
    	    }
    	}
        
        private File createImageFile() throws IOException {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getResources().getString(R.string.photo_storage_directory));
            File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = "file:" + image.getAbsolutePath();
            photoFileName = imageFileName;
            return image;
        }
        	
    }

}
