package com.exer.math;

import java.nio.FloatBuffer;

public class GLMatrix16 {

	public float m[][];// = new float[4][4];

	public GLMatrix16() {
		m = new float[4][4];
	}
	public GLMatrix16(float _11, float _12, float _13, float _14, float _21,
			float _22, float _23, float _24, float _31, float _32, float _33,
			float _34, float _41, float _42, float _43, float _44) {
		m = new float[4][4];
		m[0][0] = _11;
		m[0][1] = _12;
		m[0][2] = _13;
		m[0][3] = _14;

		m[1][0] = _21;
		m[1][1] = _22;
		m[1][2] = _23;
		m[1][3] = _24;

		m[2][0] = _31;
		m[2][1] = _32;
		m[2][2] = _33;
		m[2][3] = _34;

		m[3][0] = _41;
		m[3][1] = _42;
		m[3][2] = _43;
		m[3][3] = _44;
	}

	public GLMatrix16(GLMatrix16 mat) {
		m = mat.m;
	}

	public FloatBuffer GetFloatBuffer() {
		FloatBuffer f = FloatBuffer.allocate(m.length * 4);
		// for( int i = 0 ; i < 4 ; i ++)
		// {
		// for( int j = 0 ; j < 4 ; j ++)
		// {
		// f.put(m[j][i]);
		// }
		// }
		f.put(m[0]);
		f.put(m[1]);
		f.put(m[2]);
		f.put(m[3]);
		f.position(0);
		return f;
	}

	public GLMatrix16 add(GLMatrix16 mat) {
		GLMatrix16 temp = new GLMatrix16();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				temp.m[i][j] = m[i][j] + mat.m[i][j];
		return temp;
	}

	public GLMatrix16 subtract(GLMatrix16 mat) {
		GLMatrix16 temp = new GLMatrix16();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				temp.m[i][j] = m[i][j] - mat.m[i][j];
		return temp;
	}

	public void copyFromMat16(GLMatrix16 matIn) {

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				m[i][j] = matIn.m[i][j];
	}

	public void copyToMat16(GLMatrix16 matOut) {

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				matOut.m[i][j] = m[i][j];
	}

	public GLMatrix16 multiply(float val) {
		GLMatrix16 temp = new GLMatrix16();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				temp.m[i][j] = m[i][j] * val;
		return temp;
	}

	public GLMatrix16 multiply(GLMatrix16 matIn) {
		GLMatrix16 mat = new GLMatrix16();

		int j = 0;
		for (int i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				mat.m[i][j] = 0.0f;
				for (int k = 0; k < 4; k++)
					mat.m[i][j] += this.m[i][k] * matIn.m[k][j];
			}
		}

		return mat;
	}

	public GLMatrix16 divide(float val) {
		if (val > 0.0f) {
			GLMatrix16 temp = new GLMatrix16();
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					temp.m[i][j] = m[i][j] / val;
			return temp;
		} else
			return this;
	}

	public boolean equalTo(GLMatrix16 matIn) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.m[i][j] != matIn.m[i][j])
					return false;
			}
		}

		return true;
	}
}