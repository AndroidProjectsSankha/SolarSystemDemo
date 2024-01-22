package com.exer.math;

public class GLVector4 {
	public float x;
	public float y;
	public float z;
	public float w;

	public GLVector4() {
		x = y = z = w = 0.0f;
	}

	public GLVector4(float xin, float yin, float zin, float win) {
		x = xin;
		y = yin;
		z = zin;
		w = win;
	}

	public GLVector4(GLVector4 vecIn) {
		x = vecIn.x;
		y = vecIn.y;
		z = vecIn.z;
		w = vecIn.w;
	}

	public GLVector4(GLVector3 vecIn, float wIn) {
		x = vecIn.x;
		y = vecIn.y;
		z = vecIn.z;
		w = wIn;
	}

	public void copyFromVec4(GLVector4 vecIn) {
		x = vecIn.x;
		y = vecIn.y;
		z = vecIn.z;
		w = vecIn.w;
	}

	public void copyToVec4(GLVector4 vecOut) {
		vecOut.x = x;
		vecOut.y = y;
		vecOut.z = z;
		vecOut.w = w;
	}

	public GLVector4 add(GLVector4 vec) {
		GLVector4 temp = new GLVector4();
		temp.x = x + vec.x;
		temp.y = y + vec.y;
		temp.z = z + vec.z;
		temp.w = w + vec.w;
		return temp;
	}

	public GLVector4 subtract(GLVector4 vec) {
		GLVector4 temp = new GLVector4();
		temp.x = x - vec.x;
		temp.y = y - vec.y;
		temp.z = z - vec.z;
		temp.w = w - vec.w;
		return temp;
	}

	public GLVector4 multiply(float val) {
		GLVector4 temp = new GLVector4();
		temp.x = x * val;
		temp.y = y * val;
		temp.z = z * val;
		temp.w = w * val;
		return temp;
	}

	public GLVector4 divide(float val) {
		try {

			GLVector4 temp = new GLVector4();
			temp.x = x / val;
			temp.y = y / val;
			temp.z = z / val;
			temp.w = w / val;
			return temp;
		} catch (Exception e) {
			return this;
		}
	}

	public boolean equalTo(GLVector4 vec) {
		if ((x == vec.x) && (y == vec.y) && (z == vec.z) && (w == vec.w)) {
			return true;
		}
		return false;
	}
}