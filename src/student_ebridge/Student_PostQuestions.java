package com.coign.student_ebridge;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Student_PostQuestions extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_student__post_questions);
		Bundle bb=getIntent().getExtras();
		final String sbranch=bb.getString("branch");
		final String sid=bb.getString("id");
		final String syear=bb.getString("year");
		final String ssem=bb.getString("sem");
		final String sname=bb.getString("sname");
		final String sub=bb.getString("sub");

		final EditText et=(EditText)findViewById(R.id.editText1);
		Button post=(Button)findViewById(R.id.button1);
		post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String sques=et.getText().toString().trim();
				Questions_domain qlist=new Questions_domain();
				qlist.createDomain();
				qlist.AddToTable(sques,sbranch,sid,syear,ssem,sname,sub);
	Toast.makeText(getBaseContext(), "Request Sent",Toast.LENGTH_LONG).show();
	finish();
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_student__post_questions, menu);
		return true;
	}

}
