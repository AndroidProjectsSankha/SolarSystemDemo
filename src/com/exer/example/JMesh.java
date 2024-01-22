package com.exer.example;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;

public abstract class JMesh {
	protected FloatBuffer mVertexBuffer;
	protected FloatBuffer mColorBuffer;
	protected FloatBuffer mNormalBuffer;
	protected FloatBuffer mTexBuffer;

	protected ShortBuffer mIndexBuffer;

	protected int mNumVertices;
	protected int mNumIndices;

	private boolean bVerexEnabled;
	private boolean bColorEnabled;
	private boolean bNormalEnabled;
	private boolean bTexEnabled;

	JMesh() {
		bVerexEnabled = false;
		bColorEnabled = false;
		bNormalEnabled = false;
		bTexEnabled = false;
	}



	abstract public Exception Construct(GL10 gl);

	abstract public Exception draw(GL10 gl);

	Exception SetVertexBuffer(float Buffer[]) {
		try {
			ByteBuffer bb = ByteBuffer.allocateDirect(Buffer.length * 4);
			bb.order(ByteOrder.nativeOrder());
			mVertexBuffer = bb.asFloatBuffer();
			mVertexBuffer.put(Buffer);
			mVertexBuffer.position(0);

			bVerexEnabled = true;

			// mNumVertices = Buffer.length();
			return null;
		} catch (Exception e) {
			return e;
		}
	}

	Exception SetNormalBuffer(float Buffer[]) {
		try {
			ByteBuffer bb = ByteBuffer.allocateDirect(Buffer.length * 4);
			bb.order(ByteOrder.nativeOrder());
			mNormalBuffer = bb.asFloatBuffer();
			mNormalBuffer.put(Buffer);
			mNormalBuffer.position(0);

			bNormalEnabled = true;

			// mNumVertices = Buffer.length();
			return null;
		} catch (Exception e) {
			return e;
		}
	}

	Exception SetColorBuffer(float Buffer[]) {
		try {
			ByteBuffer bb = ByteBuffer.allocateDirect(Buffer.length * 4);
			bb.order(ByteOrder.nativeOrder());
			mColorBuffer = bb.asFloatBuffer();
			mColorBuffer.put(Buffer);
			mColorBuffer.position(0);

			bColorEnabled = true;

			return null;
		} catch (Exception e) {
			return e;
		}
	}

	Exception SetTextureBuffer(float Buffer[]) {
		try {
			ByteBuffer bb = ByteBuffer.allocateDirect(Buffer.length * 4);
			bb.order(ByteOrder.nativeOrder());
			mTexBuffer = bb.asFloatBuffer();
			mTexBuffer.put(Buffer);
			mTexBuffer.position(0);

			bTexEnabled = true;

			return null;
		} catch (Exception e) {
			return e;
		}
	}

	Exception SetIndexBuffer(short Buffer[]) {
		try {
			ByteBuffer bb = ByteBuffer.allocateDirect(Buffer.length * 2);
			bb.order(ByteOrder.nativeOrder());
			mIndexBuffer = bb.asShortBuffer();
			mIndexBuffer.put(Buffer);
			mIndexBuffer.position(0);

			return null;
		} catch (Exception e) {
			return e;
		}
	}

	public Exception draw(GL10 gl, int mode) {
		try {

			if (bVerexEnabled == true) {
				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
			}
			if (bColorEnabled == true) {
				gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			//	gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
			}

			if (bNormalEnabled == true) {
				gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
				gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
			}

			if (bTexEnabled == true) {
				gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
				gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTexBuffer);

			}

			// Finally Draw the Primitives
			gl.glDrawElements(mode, mNumIndices, GL10.GL_UNSIGNED_SHORT,
					mIndexBuffer);

			if (bVerexEnabled == true) {
				gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			}
			if (bColorEnabled == true) {
				gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
			}

			if (bNormalEnabled == true) {
				gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
			}

			if (bTexEnabled == true) {
				gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			}
			return null;
		} catch (Exception e) {
			return e;
		}
	}

}