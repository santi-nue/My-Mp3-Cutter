package com.example.mymp3cutter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends Activity {
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		imageView=(ImageView)findViewById(R.id.splash_img);
		Animation animation=AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.anim_splash_screen);
		imageView.setAnimation(animation);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					if(ContextCompat.checkSelfPermission(SplashScreenActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
						Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(intent);
					}else{
						Intent intent=new Intent(SplashScreenActivity.this,RequestPermissionActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(intent);
					}
				} else{
					Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
				}
//				if(ContextCompat.checkSelfPermission(SplashScreenActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
//					Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
//					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//					startActivity(intent);
//				}else{
//					Intent intent=new Intent(SplashScreenActivity.this,RequestPermissionActivity.class);
//					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//					startActivity(intent);
//				}
			}
		},3000);
	}
}
