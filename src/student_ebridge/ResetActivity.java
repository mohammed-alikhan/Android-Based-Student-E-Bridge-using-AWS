package com.coign.student_ebridge;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ResetActivity extends Activity {
	ArrayList<String> al = new ArrayList<String>();
	EditText name, pass;
	String sname, spass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_reset);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		final Spinner sp = (Spinner) findViewById(R.id.spinner1);
		al.add("Admin");
		al.add("Student");
		al.add("Faculty");
		sp.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, al));
		name = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		Button login = (Button) findViewById(R.id.button1);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String spin = sp.getSelectedItem().toString();
				sname = name.getText().toString().trim();
				spass = pass.getText().toString().trim();

			
			if (spin.equals("Student")) {
					if (!sname.equals("") && (!spass.equals(""))) {

						Student_Registration slist=new Student_Registration();
						slist.createDomain();
						slist.AddToTable2(sname,spass);
							
							Intent it = new Intent(ResetActivity.this,
									MainActivity.class);
						
							startActivity(it);

						
					}
				} else if (spin.equals("Faculty")) {
					if (!sname.equals("") && (!spass.equals(""))) {
						Add_FacultyList addlist=new Add_FacultyList();
						addlist.createDomain();
						addlist.AddToTable2(sname,spass);
							Toast.makeText(getBaseContext(),
									"Your Pasword is Reset", Toast.LENGTH_SHORT)
									.show();
					
							
							Intent it = new Intent(ResetActivity.this,
									MainActivity.class);
						
							startActivity(it);
							finish();

						}
					}

				

				/*
				 * else { Toast.makeText(getApplicationContext(),
				 * "please enter all fields", 90).show(); }
				 */

			}
		});
		
	}

	

}
