package com.coign.student_ebridge;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_Faculty extends Activity {
	ArrayList<String> al1=new ArrayList<String>();
	ArrayList<String> al2=new ArrayList<String>();
	ArrayList<String> al3=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_add__faculty);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		final EditText fname=(EditText)findViewById(R.id.editText1);
		final EditText fid=(EditText)findViewById(R.id.editText2);
		final EditText fsub=(EditText)findViewById(R.id.editText3);
		
		final Spinner year=(Spinner)findViewById(R.id.spinner1);
		al1.add("Select Year");
		al1.add("I");
		al1.add("II");
		al1.add("III");
		al1.add("IV");
		year.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al1));
		
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
		
		Button save=(Button)findViewById(R.id.button1);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s1=fname.getText().toString().trim();
				String s2=fid.getText().toString().trim();
				String s3=fsub.getText().toString().trim();
				String s4=year.getSelectedItem().toString().trim();
				String s5=sem.getSelectedItem().toString().trim();
				String s6=branch.getSelectedItem().toString().trim();
				
				if (!s1.equals("") && !s2.equals("") && !s3.equals("") && !s4.equals("") && !s5.equals("") && !s5.equals("") && !s6.equals("")) {
					Add_FacultyList addlist=new Add_FacultyList();
					addlist.createDomain();
					addlist.AddToTable(s1,s2,s3,s4,s5,s6);
		Toast.makeText(getBaseContext(), "Registration completed",Toast.LENGTH_LONG).show();
		Intent it=new Intent(Add_Faculty.this, Admin.class);
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
		getMenuInflater().inflate(R.menu.activity_add__faculty, menu);
		return true;
	}

}
