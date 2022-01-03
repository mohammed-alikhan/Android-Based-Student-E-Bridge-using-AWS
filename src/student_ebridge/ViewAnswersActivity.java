package com.coign.student_ebridge;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewAnswersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_view_answers);
		Bundle b=getIntent().getExtras();
		String aa=b.getString("ques");
		Questions_domain11 qlist=new Questions_domain11();
		qlist.createDomain();
		String list = qlist.getsquestionsaa(aa);
		//int len = list.size();
		String ss[]=list.split("@");
		final String aa1=ss[0].trim();
		final String aa2=ss[1].trim();
		final String aa3=ss[2].trim();
		System.out.println("@@@@@@@@##########   "+aa1  +aa2   +aa3);
		TextView tv=(TextView)findViewById(R.id.textView2);
		TextView t2=(TextView)findViewById(R.id.textView4);
		Button bt=(Button)findViewById(R.id.button1);
		tv.setText(aa1);
		t2.setText(aa2);
		bt.setText(aa3);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("$#$%#4545454353453%$#$"+aa3);
				if(aa1.trim().equals("Image")){
				Intent it=new Intent(ViewAnswersActivity.this,ImagevActivity.class);
				it.putExtra("imagename", aa3);
				it.putExtra("bucketname", "/AndroidAmazon/Ebridge/");
			    startActivity(it);
			    }
				else if(aa1.trim().equals("Video"))
				{
					Intent it=new Intent(ViewAnswersActivity.this,VideoActivity.class);
					it.putExtra("imagename", aa3);
					it.putExtra("bucketname", "/AndroidAmazon/Ebridge/");
				    startActivity(it);}
				}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_view_answers, menu);
		return true;
	}

}
