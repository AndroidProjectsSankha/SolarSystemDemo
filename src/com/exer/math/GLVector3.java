package com.exer.math;

public class GLVector3 {
	public float x;
	public float y;
	public float z;

	public GLVector3() {
		x = y = z = 0.0f;
	}

	public GLVector3(float xin, float yin, float zin) {
		x = xin;
		y = yin;
		z = zin;
	}

	public GLVector3(GLVector3 vecIn) {
		x = vecIn.x;
		y = vecIn.y;
		z = vecIn.z;
	}

	public void copyFromVec3(GLVector3 vecIn) {
		x = vecIn.x;
		y = vecIn.y;
		z = vecIn.z;
	}

	public void copyToVec3(GLVector3 vecOut) {
		vecOut.x = x;
		vecOut.y = y;
		vecOut.z = z;
	}

	public GLVector3 add(GLVector3 vec) {
		GLVector3 temp = new GLVector3();
		temp.x = x + vec.x;
		temp.y = y + vec.y;
		temp.z = z + vec.z;
		return temp;
	}

	public GLVector3 subtract(GLVector3 vec) {
		GLVector3 temp = new GLVector3();
		temp.x = x - vec.x;
		temp.y = y - vec.y;
		temp.z = z - vec.z;
		return temp;
	}

	public GLVector3 multiply(float val) {
		GLVector3 temp = new GLVector3();
		temp.x = x * val;
		temp.y = y * val;
		temp.z = z * val;
		return temp;
	}

	public GLVector3 divide(float val) {
		try {

			GLVector3 temp = new GLVector3();
			temp.x = x / val;
			temp.y = y / val;
			temp.z = z / val;
			return temp;
		} catch (Exception e) {
			return this;
		}
	}

	public boolean equalTo(GLVector3 vec) {
		if ((x == vec.x) && (y == vec.y) && (z == vec.z)) {
			return true;
		}
		return false;
	}
}