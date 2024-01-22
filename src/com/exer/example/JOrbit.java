package com.exer.example;
import javax.microedition.khronos.opengles.GL10;
import com.exer.Util.Utility;
import com.exer.math.GLColor4;

public class JOrbit extends JMesh {

	private float mDistance;
	private final float NUM_DIVISION = 2;
	private final float TOTLONG = 360.0f;	

	public JOrbit(float distance) {
		mDistance = distance;
	}
	
	@Override
	public Exception Construct(GL10 gl) {
		// TODO Auto-generated method stub

		mNumVertices = (int)(TOTLONG/NUM_DIVISION) ;

		float pVData[] = new float[mNumVertices * 3];
		float colors[] = new float[mNumVertices * 4];

		GLColor4 color = new GLColor4(1.0f, 1.0f, 1.0f, 1.0f);

		float dx,dy,dz;
		int k = 0;
		for ( int theta = 0, index=0; theta < (int)TOTLONG; theta += NUM_DIVISION, index++)
		{
			dx = (float)(Math.cos( Utility.GLToRadian(theta)) * mDistance );
			dy = 0.0f;
			dz = (float)(Math.sin( Utility.GLToRadian(theta)) * mDistance );
			pVData[3 * k + 0] = dx;
			pVData[3 * k + 1] = dy;
			pVData[3 * k + 2] = dz;
			colors[4 * k + 0] = color.r;
			colors[4 * k + 1] = color.g;
			colors[4 * k + 2] = color.b;
			colors[4 * k + 3] = color.a;			
			k++;
		}
		
		mNumIndices = mNumVertices;
		short pIData[] = new short[mNumIndices];
		for (int i = 0; i < mNumIndices; i++) 
			pIData[i] = (short)i ;

		Exception e;
		if ((e = SetVertexBuffer(pVData)) != null) {
			System.out.print("Error Setting Vertex Buffer : " + e.toString());
			return e;
		}

		if ((e = SetColorBuffer(colors)) != null) {
			System.out.print("Error Setting Color Buffer : " + e.toString());
			return e;
		}

		if ((e = SetIndexBuffer(pIData)) != null) {
			System.out.print("Error Setting Index Buffer : " + e.toString());
			return e;
		}
		return null;
	}

	@Override
	public Exception draw(GL10 gl) {
		try {
			gl.glFrontFace(GL10.GL_CCW);
			super.draw(gl, GL10.GL_LINES);
			return null;
		} catch (Exception e) {
			return e;
		}
	}
}
