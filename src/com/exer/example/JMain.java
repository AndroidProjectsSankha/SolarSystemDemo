package com.exer.example;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class JMain extends Activity {
	   
	   private GLSurfaceView glView;   
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      glView = new GLSurfaceView(this);           
	      glView.setRenderer(new GLRenderer(this)); // Use a custom renderer
	      this.setContentView(glView);    
	      // This activity sets to GLSurfaceView
	   }
}