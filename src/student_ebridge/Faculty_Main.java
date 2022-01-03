package com.coign.student_ebridge;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Faculty_Main extends Activity {
	ArrayList<String> al1=new ArrayList<String>();
	ArrayList<String> al2=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_faculty__main);
		Bundle bb=getIntent().getExtras();
		final String s1=bb.getString("fname").trim();
		final String s2=bb.getString("branch").trim();
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD    "+s1+"     "+s2);
		final EditText subj=(EditText)findViewById(R.id.editText1);
		final Spinner year=(Spinner)findViewById(R.id.spinner1);
		al1.add("Select YEAR");
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
		sem.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String ssem=sem.getSelectedItem().toString().trim();
				String syear=year.getSelectedItem().toString().trim();
				if(ssem.equals("Select YEAR") && syear.equals("Select SEM") ){
					Toast.makeText(getApplicationContext(), "Please select Any one", 90).show();
				}else{
				Add_FacultyList faculty=new Add_FacultyList();
				
				String sub=faculty.getdataa(s1,s2,syear,ssem);
				if(sub.equals("Sorry")){
					Toast.makeText(getApplicationContext(), "Sorry You Don't have Subject in this year", Toast.LENGTH_SHORT).show();
				}else{
				subj.setText(sub);
				
				}
			}}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button done=(Button)findViewById(R.id.button1);
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			String syear=year.getSelectedItem().toString().trim();
			String ssem=sem.getSelectedItem().toString().trim();
			String ssub=subj.getText().toString().trim();
			Intent it=new Intent(Faculty_Main.this,QueriesList.class);
			
			it.putExtra("branch", s2);
			it.putExtra("year",syear);
			it.putExtra("sem",ssem);
			it.putExtra("sub",ssub);
			startActivity(it);
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_faculty__main, menu);
		return true;
	}

}
