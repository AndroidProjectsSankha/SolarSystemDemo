package com.exer.example;
import javax.microedition.khronos.opengles.GL10;
import com.exer.Util.Utility;
import com.exer.math.GLColor4;
import com.exer.math.GLVector3;
import com.exer.math.MathUtil;

public class JSphereColor extends JMesh {

	private float mRadius;
	private final float NUMLATITUDE = 15.0f;
	private final float NUMLONGITUDE = (NUMLATITUDE * 2.0f);
	
	// An Planet has an Orbit
	private JOrbit mOrbit;

	public JSphereColor(float radius) {
		mRadius = radius;
		mOrbit = new JOrbit(radius);
	}
	
	@Override
	public Exception Construct(GL10 gl) {
		// TODO Auto-generated method stub
		
		// Construct the Orbit Also
		Exception e = mOrbit.Construct(gl);
		if ( e != null ) return e;

		mNumVertices = (int) ((NUMLATITUDE + 1) * (NUMLONGITUDE + 1));

		float pVData[] = new float[mNumVertices * 3];
		float normals[] = new float[mNumVertices * 3];
		float colors[] = new float[mNumVertices * 4];

		float ltShift = 180.0f / NUMLATITUDE;
		float lgShift = 360.0f / NUMLONGITUDE;

		GLColor4 color = new GLColor4(1.0f, 0.0f, 1.0f, 1.0f);
		int k = 0;
		for (int latitude = -90; latitude <= 90; latitude += (int) ltShift) {
			float currentRadius = mRadius
					* (float) (Math.cos(Utility.GLToRadian(latitude)));
			float currentY = mRadius
					* (float) (Math.sin(Utility.GLToRadian(latitude)));
			for (int longitude = 0; longitude <= 360; longitude += (int) lgShift) {
				// Get Current Index
				// k = (latitude + 90) * 360 + longitude;
				pVData[3 * k + 0] = currentRadius
						* (float) (Math.cos(Utility.GLToRadian(longitude)));
				pVData[3 * k + 1] = currentY;
				pVData[3 * k + 2] = currentRadius
						* (float) Math.sin(Utility.GLToRadian(longitude));

				GLVector3 temp = new GLVector3(pVData[3 * k + 0],
						pVData[3 * k + 1], pVData[3 * k + 2]);// - mOrigin;
				MathUtil.GLVec3Normalize(temp, temp);
				normals[3 * k + 0] = temp.x;
				normals[3 * k + 1] = temp.y;
				normals[3 * k + 2] = temp.z;

				// if ( latitude % 2 == 0 )
				// color.g = color.b = 1.0f;
				// else color.g = color.b= 0.0f;

				colors[4 * k + 0] = color.r;
				colors[4 * k + 1] = color.g;
				colors[4 * k + 2] = color.b;
				colors[4 * k + 3] = color.a;
				k++;

			}
		}

		mNumIndices = (int) (NUMLATITUDE * ((NUMLONGITUDE + 1) * 2 + 2));
		short pIData[] = new short[mNumIndices];
		k = 0;
		for (int i = 0; i < NUMLATITUDE; i++) {
			int j;
			for (j = 0; j <= NUMLONGITUDE; j++) {
				pIData[k++] = (short) (i * (NUMLONGITUDE + 1) + j);
				pIData[k++] = (short) ((i + 1) * (NUMLONGITUDE + 1) + j);
			}
			// for degenarated triangle
			j = 0;
			pIData[k++] = (short) ((i) * (NUMLONGITUDE + 1 ) + (j));
			pIData[k++] = (short) ((i + 1) * (NUMLONGITUDE + 1 ) + j);
		}

		//Exception e;
		if ((e = SetVertexBuffer(pVData)) != null) {
			System.out.print("Error Setting Vertex Buffer : " + e.toString());
			return e;
		}

		if ((e = SetNormalBuffer(normals)) != null) {
			System.out.print("Error Setting Normal Buffer : " + e.toString());
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
			mOrbit.draw(gl);
			super.draw(gl, GL10.GL_TRIANGLE_STRIP);
			return null;
		} catch (Exception e) {
			return e;
		}
	}
}
