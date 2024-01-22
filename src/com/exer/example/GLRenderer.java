package com.exer.example;

import java.util.Date;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.exer.example.R;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import com.exer.example.JObjectRenderer;
import com.exer.math.GLVector3;

public class GLRenderer implements GLSurfaceView.Renderer {

	// Reference to Context, used to create / refer project resource data
	Context mContext;

	// Time Variables	
	long mfPrevTime = 0;
	int mFramePerSec = 0;
	float mfElapsedTime = 0.0f;

	// Declare a Reference
	final int mNumPlanets = 10;
	JMesh mSphere;
	JObjectRenderer mPlanets[];
	JOrbit mOrbit;
	JObjectRenderer mOrbits[];

	public GLRenderer(Context context) {

		mContext = context;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glShadeModel(GL10.GL_SMOOTH);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepthf(5.0f);

		// Load/Construct the Scene/Objects to Render
		LoadScene(gl);

		// Set Up Lights & Materials
		SetupLightsAndMaterials(gl);
	}

	public void onSurfaceChanged(GL10 gl, int w, int h) {

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, w, h);
		GLU.gluPerspective(gl, 45.0f, ((float) w) / h, 1.0f, 200.0f);
	}
	
	private void LoadScene(GL10 gl) {
		
		mPlanets = new JObjectRenderer[mNumPlanets];
		mOrbits = new JObjectRenderer[mNumPlanets];

		// Call The Constructor
		mSphere = new JSphereTexture(1.0f, R.drawable.yellowtex, mContext);
		mOrbit = new JOrbit(1.0f);
		
		// Construct the Sphere. .i.e Calculate Vertices & Indices
		mSphere.Construct(gl);
		mOrbit.Construct(gl);

		// Now Call Planet Renderer Objects
		float scalePlanet[] = { 5.0f, 1.0f, 1.5f, 2.5f, 4.5f, 4.0f, 2.3f, 1.5f, 2.0f, 0.75f };
		float transPlanet[] = { 0.0001f, 10.0f, 15.0f, 20.0f, 30.0f, 40.0f,	50.0f, 60.0f, 70.0f, 80.0f, 90.0f };
		float rotSpeedPlanet[] = { 0.01f, -2.0f, 2.5f, 3.5f, 2.5f, -6.5f,-2.5f, 1.3f, 2.3f, 1.5f, 1.0f };
		float revSpeedPlanet[] = { 0.01f, -2.0f, 2.5f, 3.5f, 2.5f, -6.5f, -2.5f, 1.3f, 2.3f, 1.5f, 1.0f };

		for (int i = 0; i < mNumPlanets; i++) {
			mPlanets[i] = new JObjectRenderer(mSphere, scalePlanet[i],
					revSpeedPlanet[i], rotSpeedPlanet[i]);
			mOrbits[i] = new JObjectRenderer(mOrbit, transPlanet[i],
					revSpeedPlanet[i], rotSpeedPlanet[i]);

			mPlanets[i].SetTranslation(new GLVector3(transPlanet[i], 0, 0));
		}

		// Get the time While the Application started
		mfPrevTime = new Date().getTime();
	}

	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		float zPos = (15 * mNumPlanets) + 1;
		GLU.gluLookAt(gl, 0, 0, zPos, 0, 0, 0, 0, 1, 0);

		// Calculate elapsed time, i.e time taken to render each frame
		CalculateElapsedTime();

		// Update the object positions
		UpdateFrames();

		// Rotate the Entire Scene to View From the TOP ( comment the below line to look forward, if not required )
		gl.glRotatef(90.0f, 1, 0, 0);

		for (int i = 0; i < mNumPlanets; i++) {

			// Draw Orbits WithOut Light
			gl.glPushMatrix();
			mOrbits[i].draw(gl);
			gl.glPopMatrix();

			// Draw Orbits With Lights i.e SunLight
			gl.glPushMatrix();
			gl.glEnable(GL10.GL_LIGHT0);
			gl.glEnable(GL10.GL_LIGHTING);
			mPlanets[i].draw(gl);
			gl.glDisable(GL10.GL_LIGHT0);
			gl.glDisable(GL10.GL_LIGHTING);
			gl.glPopMatrix();
		}
	}

	void CalculateElapsedTime() {
		long CurrTime = new Date().getTime();
		mfElapsedTime = (CurrTime - mfPrevTime) / 1000.0f;
		mfPrevTime = CurrTime;
	}

	private void UpdateFrames() {
		// Update Planet Positions
		for (int i = 0; i < mNumPlanets; i++) {
			mPlanets[i].UpdatePosition(mfElapsedTime);
			mOrbits[i].UpdatePosition(mfElapsedTime);
		}
	}

	private void SetupLightsAndMaterials(GL10 gl) {

		float lightAmbient[] = new float[] { 0.2f, 0.3f, 0.6f, 1.0f };
		float lightDiffuse[] = new float[] { 1.0f, 1.0f, 0.0f, 1.0f };
		float[] lightPos = new float[] { -0.577f, -0.577f, -0.577f, 0 };

		float matAmbient[] = new float[] { 1.0f, 0.0f, 0.0f, 1.0f };
		float matDiffuse[] = new float[] { 0.6f, 0.6f, 0.6f, 1.0f };

		gl.glEnable(GL10.GL_LIGHT0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse, 0);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
	}
}
