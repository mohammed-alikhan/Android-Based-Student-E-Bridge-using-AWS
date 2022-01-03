package com.coign.student_ebridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Student_Queries extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_student__queries);
		Bundle bb=getIntent().getExtras();
		final String s5=bb.getString("sname");
		final String s1=bb.getString("branch");
		final String s2=bb.getString("id");
		final String s3=bb.getString("year");
		final String s4=bb.getString("sem");
		final String s6=bb.getString("sub");

		Button ques=(Button)findViewById(R.id.button1);
		ques.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(Student_Queries.this, Student_PostQuestions.class);
				it.putExtra("branch", s1);
				it.putExtra("id", s2);
				it.putExtra("year", s3);
				it.putExtra("sem", s4);
				it.putExtra("sname", s5);
				it.putExtra("sub", s6);

				startActivity(it);
				
				
			}
		});
		
		Button ans=(Button)findViewById(R.id.button2);
		ans.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(Student_Queries.this, Student_viewAnswers.class);
				it.putExtra("branch", s1);
				it.putExtra("id", s2);
				it.putExtra("year", s3);
				it.putExtra("sem", s4);
				it.putExtra("sname", s5);
				it.putExtra("sub", s6);

				startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_student__queries, menu);
		return true;
	}

}
