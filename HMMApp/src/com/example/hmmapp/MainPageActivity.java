package com.example.hmmapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainPageActivity extends Activity {

	String selectedWorkout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		
		//Create the startup page
		CreateStartupPage();
		
	}
	
	private void CreateStartupPage(){
		//set up a click event for the workout listview.
		final ListView workoutList = (ListView) findViewById(R.id.listView_workouts);
		workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
		    @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
		    	//Get the workout that was selected from the list view.
		    	selectedWorkout =(String) (workoutList.getItemAtPosition(position));
		    	
		    	//Remove the start page
		    	findViewById(R.id.startPage).setVisibility(View.GONE);
		    	
		    	//Based on the selection, make the appropriate workout information page visible
		    	CreateWorkoutInformationPage();
		    }
		});
	}
	
	private void CreateWorkoutInformationPage(){
		//If Hill was selected from the list, show the Hill Workout Page
    	if(selectedWorkout.equals("Hill"))
    	{
    		findViewById(R.id.HillWorkoutInfoPage).setVisibility(View.VISIBLE);
    	}
    	//If Steady was selected from the list, show the Steady Workout Page
    	else if(selectedWorkout.equals("Steady"))
    	{
    		findViewById(R.id.SteadyWorkoutInfoPage).setVisibility(View.VISIBLE);
    	}
    	//If Random was selected from the list, show the Random Workout Page
    	else
    	{
    		findViewById(R.id.RandomWorkoutInfoPage).setVisibility(View.VISIBLE);
    	}
    	
    	//get the buttons from each workout information page
    	final Button HillWorkoutStartButton = (Button) findViewById(R.id.btnStartHillWorkout);
    	final Button SteadyWorkoutStartButton = (Button) findViewById(R.id.btnStartSteadyWorkout);
    	final Button RandomWorkoutStartButton = (Button) findViewById(R.id.btnStartRandomWorkout);
    		
    	//Create an on click event to get the input workout information and start the workout
    	View.OnClickListener WorkoutButtonClickListener = new View.OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			//Get information
    			switch (v.getId()){
    				case R.id.btnStartHillWorkout:{
    							
    					//Hide the Hill Workout Info Page and show the Workout Page
    					findViewById(R.id.HillWorkoutInfoPage).setVisibility(View.GONE);
    		            break;
    				}
    				case R.id.btnStartSteadyWorkout:{
    							
    					//Hide the Steady Workout Info Page and show the Workout Page
    					findViewById(R.id.SteadyWorkoutInfoPage).setVisibility(View.GONE);
    		            break;
    				}
    				case R.id.btnStartRandomWorkout:{
    							
    					//Hide the Random Workout Info Page and show the Workout Page
    					findViewById(R.id.RandomWorkoutInfoPage).setVisibility(View.GONE);
    			        break;
    				}
    			}
    			
    			CreateWorkoutPage();    			
    		}
    	};
    			
    	//set the work out event created above as the on click event for the workout start buttons
    	HillWorkoutStartButton.setOnClickListener(WorkoutButtonClickListener);
    	SteadyWorkoutStartButton.setOnClickListener(WorkoutButtonClickListener);
    	RandomWorkoutStartButton.setOnClickListener(WorkoutButtonClickListener);
    	
    	
    	//Create on click listener for back button
    	final ImageButton HillBackButton = (ImageButton) findViewById(R.id.imageButton_Hillback);
    	final ImageButton SteadyBackButton = (ImageButton) findViewById(R.id.imageButton_Steadyback);
    	final ImageButton RandomBackButton = (ImageButton) findViewById(R.id.imageButton_Randomback);
    	View.OnClickListener BackButtonClickListener = new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				//Reset Values
				EditText HillMinBPM = (EditText) findViewById(R.id.editText_minimumBPM);
				HillMinBPM.setText("");
				EditText HillDuration = (EditText) findViewById(R.id.HilleditText_Duration);
				HillDuration.setText("");
				EditText SteadyMinBPM = (EditText) findViewById(R.id.SteadyeditText_BPM);
				SteadyMinBPM.setText("");
				EditText SteadyDuration = (EditText) findViewById(R.id.SteadyEditText_Duration);
				SteadyDuration.setText("");
				EditText RandomDuration = (EditText) findViewById(R.id.RandomEditText_Duration);
				RandomDuration.setText("");
			
				//Change the view
				findViewById(R.id.HillWorkoutInfoPage).setVisibility(View.GONE);
				findViewById(R.id.SteadyWorkoutInfoPage).setVisibility(View.GONE);
				findViewById(R.id.RandomWorkoutInfoPage).setVisibility(View.GONE);
				findViewById(R.id.startPage).setVisibility(View.VISIBLE);
			}
    		
    	};

    	//Set back button on click listeners
    	HillBackButton.setOnClickListener(BackButtonClickListener);
    	SteadyBackButton.setOnClickListener(BackButtonClickListener);
    	RandomBackButton.setOnClickListener(BackButtonClickListener);
	}
	
	private void CreateWorkoutPage(){
		//Show the Workout Page
		findViewById(R.id.WorkoutPage).setVisibility(View.VISIBLE);
				
		//Start the workout timer
		final TextView timerTextView = (TextView)findViewById(R.id.TimerTextView);
		CountDownTimer timer = new CountDownTimer(50000, 1000) {

			public void onTick(long millisUntilFinished) {
				timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
			}
			public void onFinish() {
			}
		}.start();
		
		//Setup on click listener for End Workout button
		final Button EndWorkoutButton = (Button) findViewById(R.id.btnEndWorkout);
		View.OnClickListener EndWorkoutClickListener = new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//Get workout values
				
				//Show Results Page
				findViewById(R.id.ResultsPage).setVisibility(View.VISIBLE);
				findViewById(R.id.WorkoutPage).setVisibility(View.GONE);
				
				CreateResultsPage();
			}
		};
		EndWorkoutButton.setOnClickListener(EndWorkoutClickListener);
	}
	
	private void CreateResultsPage(){
		final Button HomeButton = (Button) findViewById(R.id.btnHome);
		View.OnClickListener HomeClickListener = new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//Get workout values
				
				//Show Results Page
				findViewById(R.id.ResultsPage).setVisibility(View.GONE);
				findViewById(R.id.startPage).setVisibility(View.VISIBLE);
			}
		};
		HomeButton.setOnClickListener(HomeClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

}
