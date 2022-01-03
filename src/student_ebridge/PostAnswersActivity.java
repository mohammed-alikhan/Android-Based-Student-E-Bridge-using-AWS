package com.coign.student_ebridge;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PostAnswersActivity extends Activity {
ArrayList<String> al=new ArrayList<String>();
private static final int PICKFILE_RESULT_CODE = 1;
Button bt;
private static final int SELECT_PICTURE = 1;
EditText et, et2, et3;
private Calendar cal;
private int day;
private int month;
private int year;
private String selectedImagePath;
ProgressDialog dialog;
boolean flag;
File file;
String FilePath,name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_post_answers);
		Bundle b=getIntent().getExtras();
		final String ques=b.getString("ques");
		dialog = new ProgressDialog(this);
		dialog.setCancelable(true);
		dialog.setMessage("Uploading Image Plz Wait...");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		al.add("Select");
		al.add("Image");
		al.add("Video");
		final Spinner sp=(Spinner)findViewById(R.id.spinner1);
		sp.setAdapter(new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al));
		 bt=(Button)findViewById(R.id.button1);
		 final EditText et=(EditText)findViewById(R.id.editText1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String type=sp.getSelectedItem().toString();
				if(type.equals("Select")){
					Toast.makeText(getApplicationContext(), "Please Select Any Type",100).show();
				}else{
				if(type.equals("Image")){
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
	       startActivityForResult(intent,PICKFILE_RESULT_CODE);
			}else{
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("video/*");
	       startActivityForResult(intent,PICKFILE_RESULT_CODE);
			}}}
		});
		Button bt2=(Button)findViewById(R.id.button2);
		bt2.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String type=sp.getSelectedItem().toString();
				 name=bt.getText().toString();
				 String aaa=et.getText().toString();
				 System.out.println("%$%#$#$"+name);
				if(type.equals("Select")){
					Toast.makeText(getApplicationContext(), "Please Select Any Type",100).show();
				}else{
				
					Questions_domain qlist=new Questions_domain();				
					qlist.createDomain();
					qlist.AddToTable1(type,ques,name,aaa);

				uploading(FilePath);
				Toast.makeText(getApplicationContext(), "Upload Successfully",
						90).show();
				Intent it = new Intent(PostAnswersActivity.this,
						MainActivity.class);
				startActivity(it);
			}}
		});
	}

	private void uploading(String selectedImagePath) {
				// TODO Auto-generated method stub
				// S3.createFolder("Crime");
				dialog.cancel();
				dialog.show();

				Log.d("file", GetbucketName.bucketName + selectedImagePath);
				// final int size=GettingFiles.updateSize1(file);

				final Thread newThred = new Thread() {
					public void run() {

						try {

							flag = S3.createObjectForBucket("AndroidAmazon",
									"Ebridge" + "/" + name, file);
							// insertDB(str);
							System.out.println("flag for image is-----------"
									+ flag);
							// FileOutputStream
							// fos=openFileOutput("awsincoign2",Context.MODE_PRIVATE);
							// GettingFiles.updateFile(fos,size);
							dialog.dismiss();
							// finish();
						} catch (Throwable e) {
							e.printStackTrace();

						}

					}
				};
				newThred.start();
				Thread t2 = new Thread() {
					public void run() {
						try {
							newThred.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}

					}
				};
				t2.start();

			}

		
		
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();
				selectedImagePath = getPath(selectedImageUri);
				file = new File(selectedImagePath);
				System.out.println(file.getName());
				name = file.getName();
				bt.setText(name);

			}
		}
	}
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
