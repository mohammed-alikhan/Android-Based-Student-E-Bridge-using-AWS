package com.coign.student_ebridge;

import java.util.ArrayList;

import android.annotation.SuppressLint;
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

public class MainActivity extends Activity {
	ArrayList<String> al = new ArrayList<String>();
	EditText name, pass;
	String sname, spass;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_main);
		
		final Spinner sp = (Spinner) findViewById(R.id.spinner1);
		al.add("Admin");
		al.add("Student");
		al.add("Faculty");
		sp.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, al));
		name = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		Button login = (Button) findViewById(R.id.button1);
		Button forgot = (Button) findViewById(R.id.button2);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String spin = sp.getSelectedItem().toString();
				sname = name.getText().toString().trim();
				spass = pass.getText().toString().trim();

				if (spin.equals("Admin")) {
					if (sname.equals("admin") && spass.equals("admin")) {
						Intent it = new Intent(MainActivity.this, Admin.class);
						startActivity(it);

					} else {
						Toast.makeText(getApplicationContext(),
								"Invalid Credentials", Toast.LENGTH_SHORT)
								.show();
					}
				} else if (spin.equals("Student")) {
					if (!sname.equals("") && (!spass.equals(""))) {

						Student_Registration slist = new Student_Registration();
						String ver = slist.loginVerify(sname, spass);
						if (ver.equals("Sorry")) {
							Toast.makeText(getBaseContext(),
									"Invalid Credentials", Toast.LENGTH_SHORT)
									.show();
						} else {
							System.out.println("@@@@@@@@@@@@@##$$$" + ver);
							String[] cc = ver.split("@");
							String branch = cc[0].trim();
							String id = cc[1].trim();
							String year = cc[2].trim();
							String sem = cc[3].trim();
							System.out.println("PPPPPPrrrrrrriiiiinnnnttt"
									+ branch + id + year + sem);
							Intent it = new Intent(MainActivity.this,
									Student_Main.class);
							it.putExtra("branch", branch);
							it.putExtra("id", id);
							it.putExtra("year", year);
							it.putExtra("sem", sem);
							it.putExtra("sname", sname);
							startActivity(it);

						}
					}
				} else if (spin.equals("Faculty")) {
					if (!sname.equals("") && (!spass.equals(""))) {
						Add_FacultyList flist = new Add_FacultyList();
						String ver = flist.loginVerify(sname, spass);
						if (ver.equals("Sorry")) {
							Toast.makeText(getBaseContext(),
									"Invalid Credentials", Toast.LENGTH_SHORT)
									.show();
						} else {
							System.out.println("@@@@@@@@@@@@@##$$$" + ver);
							String[] cc = ver.split("@");
							String branch = cc[0].trim();
							Intent it = new Intent(MainActivity.this,
									Faculty_Main.class);
							it.putExtra("fname", sname);
							it.putExtra("branch", ver);
							System.out.println("bbbbbrrrraaaaannnccchhhh0   "+ver);
							startActivity(it);

						}
					}

				}

				/*
				 * else { Toast.makeText(getApplicationContext(),
				 * "please enter all fields", 90).show(); }
				 */

			}
		});
		forgot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent it=new Intent(MainActivity.this,ResetActivity.class);
			startActivity(it);
			finish();
			}
		});
	}


}
