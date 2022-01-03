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

public class QueriesList extends Activity {
	ArrayList<String> al=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_queries_list);
		Bundle bb=getIntent().getExtras();
		String ss1=bb.getString("branch");
		String ss2=bb.getString("year");
		String ss3=bb.getString("sem");
		String ss4=bb.getString("sub");
		final ListView lv=(ListView)findViewById(R.id.listView1);
		Questions_domain qlist=new Questions_domain();
		qlist.createDomain();
		List<Others> list = qlist.getsquestions(ss1,ss2,ss3,ss4);
		int len = list.size();

		for (int i = 0; i < len; i++) {

			Others oo = list.get(i);
		String	imagename = oo.getName();

			
			al.add(imagename);
			
			System.out.println("image names       " + oo.getName()
					+ "===========");
		}
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al));
		lv.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				String ques=lv.getItemAtPosition(arg2).toString();
				Intent it=new Intent(QueriesList.this,PostAnswersActivity.class);
				it.putExtra("ques",ques);
				startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_queries_list, menu);
		return true;
	}

}
