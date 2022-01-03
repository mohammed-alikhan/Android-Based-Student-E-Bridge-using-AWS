package com.coign.student_ebridge;

import java.net.URL;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_video);
		Bundle extras = this.getIntent().getExtras();
		String bucketName = extras.getString("bucketname");
		String objectName = extras.getString("imagename").trim();
		VideoView videoView = (VideoView) findViewById(R.id.videoView1);
		// AWS credentials from Constants.java
		AWSCredentials myCredentials = new BasicAWSCredentials(Constants.ACCESS_KEY_ID,Constants.SECRET_KEY);
		AmazonS3 s3client = new AmazonS3Client(myCredentials);
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
				GetbucketName.bucketName,  GetbucketName.getFoldername()+"/"+objectName.trim());
		URL objectURL = s3client.generatePresignedUrl(request);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		MediaController mediaCtrl = new MediaController(this);
		mediaCtrl.setMediaPlayer(videoView);
		videoView.setMediaController(mediaCtrl);
		Uri clip = Uri.parse(objectURL.toString());
		videoView.setVideoURI(clip);
		videoView.requestFocus();
		videoView.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_video, menu);
		return true;
	}

}
