package com.coign.student_ebridge;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Student_Main extends Activity {
	ArrayList<String> al=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_student__main);
		final ListView lv=(ListView)findViewById(R.id.listView1);
		Bundle b=getIntent().getExtras();
		final String s5=b.getString("sname");
		final String s1=b.getString("branch");
		final String s2=b.getString("id");
		final String s3=b.getString("year");
		final String s4=b.getString("sem");
		System.out.println("SSiiivaaaaaaaaaaaaaaaaaaaaaa"+s1+s2+s3+s4);
		Add_FacultyList flist=new Add_FacultyList();
		flist.createDomain();

		List<Others> list = flist.getAllValues(s3,s1,s4);
		int len = list.size();

		for (int i = 0; i < len; i++) {

			Others oo = list.get(i);
		String	imagename = oo.getName();

			
			al.add(imagename);
			
			System.out.println("image names       " + oo.getName()
					+ "===========");
		}	
		ArrayAdapter<String> adp=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);
		lv.setAdapter(adp);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String sub=lv.getItemAtPosition(arg2).toString();
				Intent it=new Intent(Student_Main.this, Student_Queries.class);
				it.putExtra("branch", s1);
				it.putExtra("id", s2);
				it.putExtra("year", s3);
				it.putExtra("sem", s4);
				it.putExtra("sname", s5);
				it.putExtra("sub", sub);

				startActivity(it);
				
			}
		});
		/*lv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent it=new Intent(Student_Main.this, Student_Queries.class);
				it.putExtra("branch", s1);
				it.putExtra("id", s2);
				it.putExtra("year", s3);
				it.putExtra("sem", s4);
				startActivity(it);
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_student__main, menu);
		return true;
	}

}
