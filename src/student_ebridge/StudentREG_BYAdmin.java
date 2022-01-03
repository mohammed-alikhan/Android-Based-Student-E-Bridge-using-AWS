package com.coign.student_ebridge;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentREG_BYAdmin extends Activity {
	ArrayList<String> al1 = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	ArrayList<String> al3= new ArrayList<String>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_student_reg__byadmin);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		//spinner1 for year
		final Spinner year=(Spinner)findViewById(R.id.spinner1);
		al1.add("Select Year");

		al1.add("I");
		al1.add("II");
		al1.add("III");
		al1.add("IV");
		year.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, al1));
		
		final Spinner sem=(Spinner)findViewById(R.id.spinner2);
	al2.add("Select SEM");
		al2.add("I");
		al2.add("II");
		sem.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, al2));
		
		final Spinner branch=(Spinner)findViewById(R.id.spinner3);
		al3.add("Select BRANCH");
		al3.add("CSE");
		al3.add("IT");
		al3.add("ECE");
		al3.add("EEE");
		al3.add("MECH");
		al3.add("CIVIL");
		branch.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, al3));
		
		final EditText stuname=(EditText)findViewById(R.id.editText1);
		final EditText hall=(EditText)findViewById(R.id.editText2);
		final EditText email=(EditText)findViewById(R.id.editText3);
		final EditText phn=(EditText)findViewById(R.id.editText4);
		Button save=(Button)findViewById(R.id.button1);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String syear=year.getSelectedItem().toString().trim();
				String ssem=sem.getSelectedItem().toString().trim();
				String sbranch=branch.getSelectedItem().toString().trim();
				String sstuname=stuname.getText().toString().trim();
				String shall=hall.getText().toString().trim();
				String semail=email.getText().toString().trim();
				String sphn=phn.getText().toString().trim();
				if(!syear.equals("") && !ssem.equals("") && !sbranch.equals("") && !sstuname.equals("")
					&& !shall.equals("") && !semail.equals("") && !sphn.equals("")){
					
					Student_Registration slist=new Student_Registration();
					slist.createDomain();
					slist.AddToTable(syear,ssem,sbranch,sstuname,shall,semail,sphn);
		Toast.makeText(getBaseContext(), "Registration completed",Toast.LENGTH_LONG).show();
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(sphn, null, "Registration successfull Yours "+"/n"+"hallticket number : "+shall+"/n"+"Password : 1234", null, null);
		Intent it=new Intent(StudentREG_BYAdmin.this, Admin.class);
		startActivity(it);
		finish();
					
				} else {
					
			Toast.makeText(getApplicationContext(),"please enter all fields", Toast.LENGTH_SHORT).show(); 
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_student_reg__byadmin, menu);
		return true;
	}

}
