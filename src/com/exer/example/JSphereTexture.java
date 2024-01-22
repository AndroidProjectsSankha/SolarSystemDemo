package com.exer.example;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.exer.example.JTexture;

import com.exer.Util.Utility; //import com.exer.math.GLColor4;
import com.exer.math.GLVector3;
import com.exer.math.MathUtil;

public class JSphereTexture extends JMesh {

	private float mRadius;
	private final float NUMLATITUDE = 15.0f;
	private final float NUMLONGITUDE = (NUMLATITUDE * 2.0f);

	// Ensures Texture
	private JTexture mTexture;
	// Resource Id should be supplied from res->drawable
	private int mTexResourceID;
	// Resource will be extracted from context
	private Context mContext;

	// An Planet has an Orbit
	// private JOrbit mOrbit;

	public JSphereTexture(float radius) {
		mRadius = radius;
		// mOrbit = new JOrbit(radius);
	}

	public JSphereTexture(float radius, int texResourceID, Context context) {
		mRadius = radius;
		// mOrbit = new JOrbit(radius);

		mTexResourceID = texResourceID;
		mContext = context;
	}

	@Override
	public Exception Construct(GL10 gl) {
		// TODO Auto-generated method stub

		// Construct the Orbit Also
		// Exception e = mOrbit.Construct(gl);
		// if ( e != null ) return e;

		mNumVertices = (int) ((NUMLATITUDE + 1) * (NUMLONGITUDE + 1));

		float pVData[] = new float[mNumVertices * 3];
		float normals[] = new float[mNumVertices * 3];
		float uvs[] = new float[mNumVertices * 2];
		float invU = 1.0f / NUMLONGITUDE;
		float invV = 1.0f / NUMLATITUDE;
		int ui = 0, vi = 0; // Texcoord in each vertex pos

		/*
		 * // Colors will come from Texture.. So Commenting it float colors[] =
		 * new float[mNumVertices * 4]; GLColor4 color = new GLColor4(1.0f,
		 * 0.0f, 1.0f, 1.0f);
		 */

		float ltShift = 180.0f / NUMLATITUDE;
		float lgShift = 360.0f / NUMLONGITUDE;

		int k = 0;
		for (int latitude = -90; latitude <= 90; latitude += (int) ltShift) {
			float currentRadius = mRadius
					* (float) (Math.cos(Utility.GLToRadian(latitude)));
			float currentY = mRadius
					* (float) (Math.sin(Utility.GLToRadian(latitude)));

			// Compute TexCoord in x dir
			ui = 0;

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

				/*
				 * // Colors will come from Texture.. So Commenting it
				 * 
				 * colors[4 * k + 0] = color.r; colors[4 * k + 1] = color.g;
				 * colors[4 * k + 2] = color.b; colors[4 * k + 3] = color.a;
				 */

				// Fill the TexCoord (u,v)
				uvs[2 * k + 0] = invU * ui; // BMP
				uvs[2 * k + 1] = 1 - invV * vi;
				
				ui++;
				k++;
			}
			// Compute TexCoord in y dir
			vi++;
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
			// for Degenerated triangle
			j = 0;
			pIData[k++] = (short) ((i) * (NUMLONGITUDE + 1) + (j));
			pIData[k++] = (short) ((i + 1) * (NUMLONGITUDE + 1) + j);
		}

		Exception e;
		if ((e = SetVertexBuffer(pVData)) != null) {
			System.out.print("Error Setting Vertex Buffer : " + e.toString());
			return e;
		}

		if ((e = SetNormalBuffer(normals)) != null) {
			System.out.print("Error Setting Normal Buffer : " + e.toString());
			return e;
		}

		/*
		 * // Colors will come from Texture.. So Commenting it if ((e =
		 * SetColorBuffer(colors)) != null) {
		 * System.out.print("Error Setting Color Buffer : " + e.toString());
		 * return e; }
		 */

		if ((e = SetTextureBuffer(uvs)) != null) {
			System.out.print("Error Setting Texture Buffer : " + e.toString());
			return e;
		}

		if ((e = SetIndexBuffer(pIData)) != null) {
			System.out.print("Error Setting Index Buffer : " + e.toString());
			return e;
		}

		// Create and Load Texture
		mTexture = new JTexture(mContext, mTexResourceID);
		if ((e = mTexture.GenerateTex(gl)) != null) {
			System.out.print("Error Generating Texture : " + e.toString());
			return e;
		}

		return null;
	}

	@Override
	public Exception draw(GL10 gl) {
		try {
			gl.glFrontFace(GL10.GL_CCW);
			// mOrbit.draw(gl);
			// Enable Tex2D before rendering
			gl.glPushMatrix();
			gl.glEnable(GL10.GL_TEXTURE_2D);
			mTexture.SetTexture(gl);
			super.draw(gl, GL10.GL_TRIANGLE_STRIP);
			// Disable Tex2D after rendering
			gl.glDisable(GL10.GL_TEXTURE_2D);
			gl.glPopMatrix();
			return null;
		} catch (Exception e) {
			return e;
		}
	}
}
