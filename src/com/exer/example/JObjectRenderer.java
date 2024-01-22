package com.exer.example;

import javax.microedition.khronos.opengles.GL10;

import com.exer.math.GLMatrix16;
import com.exer.math.GLVector3;
import com.exer.math.MathUtil;

public class JObjectRenderer {

	private JMesh mMesh;
	
	private GLMatrix16 mWorldMat;
	private GLMatrix16 mScaleMat;
	private GLMatrix16 mRotMat;
	private GLMatrix16 mRevMat;
	private GLMatrix16 mTranslateMat;
	
	private float mfRevolutionTime;
	private float mfRotationTime;

	private float mfCurrRotAngle;
	private float mfCurrRevAngle;
	
	private float TIMEFACTOR = 10.0f;
	
	
	public JObjectRenderer ( JMesh mesh, float scale, float revTime, float rotTime )
	{
		mMesh = mesh;
		
		mfRevolutionTime = revTime;
		mfRotationTime = rotTime;
		
		mWorldMat = new GLMatrix16();
		mTranslateMat = new GLMatrix16();
		mScaleMat = new GLMatrix16();
		mRotMat = new GLMatrix16();
		mRevMat = new GLMatrix16();
		

		MathUtil.GLMatrixIdentity(mWorldMat);
		MathUtil.GLMatrixIdentity(mTranslateMat);	
		MathUtil.GLMatrixIdentity(mRotMat);	
		MathUtil.GLMatrixIdentity(mRevMat);	

		MathUtil.GLMatrixIdentity(mScaleMat);
		MathUtil.GLMatrixScaling(mScaleMat, scale, scale, scale);
	}
	
	public void SetWorldMatrix ( GLMatrix16 worldMat)
	{
		MathUtil.GLMatrixIdentity(mWorldMat);		
		mWorldMat.copyFromMat16( worldMat );
	}
		
	public void SetTranslation ( GLVector3 trans )
	{
		MathUtil.GLMatrixIdentity(mTranslateMat);		
		MathUtil.GLMatrixTranslation(mTranslateMat, trans.x, trans.y, trans.z);
	}

	public Exception draw(GL10 gl) {

		gl.glMultMatrixf(mWorldMat.GetFloatBuffer());
		return mMesh.draw(gl);
	}
	
	public void UpdatePosition (float elapsedTime)
	{
		float dRot = 360.0f / ( TIMEFACTOR * mfRotationTime );
		mfCurrRotAngle += ( dRot * elapsedTime );
		
		if ( mfCurrRotAngle >= 360.0f ) 	mfCurrRotAngle -= 360.0f;
		
		// Rotation
		MathUtil.GLMatrixRotationY( mRotMat, (float)Math.toRadians( mfCurrRotAngle  ) );
		
		// Revolution
		float dAngle = 360.0f / ( TIMEFACTOR * mfRevolutionTime );
		mfCurrRevAngle +=  ( dAngle * elapsedTime );
		if ( mfCurrRevAngle >= 360.0f ) mfCurrRevAngle -= 360.0f;
		MathUtil.GLMatrixRotationY( mRevMat, (float)Math.toRadians( mfCurrRevAngle  ) );

					
		// Update World Matrix
		MathUtil.GLMatrixIdentity(mWorldMat);
		
		MathUtil.GLMatrixMultiply(mWorldMat, mWorldMat, mRotMat );
		MathUtil.GLMatrixMultiply(mWorldMat, mWorldMat, mTranslateMat );
		MathUtil.GLMatrixMultiply(mWorldMat, mWorldMat, mRevMat );
		MathUtil.GLMatrixMultiply(mWorldMat, mWorldMat, mScaleMat );				
	}
	
}
