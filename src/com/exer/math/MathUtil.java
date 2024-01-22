package com.exer.math;

public class MathUtil {

	// *****************************************************
	// ***************** Matrix Operations *****************
	// *****************************************************
	public static GLMatrix16 GLMatrixMultiply(GLMatrix16 pOut, GLMatrix16 pM1,
			GLMatrix16 pM2) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		int j = 0;
		for (int i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				mat.m[i][j] = 0.0f;
				for (int k = 0; k < 4; k++)
					mat.m[i][j] += pM1.m[i][k] * pM2.m[k][j];

			}
		}
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixIdentity(GLMatrix16 pOut) {
		GLMatrix16 mat = new GLMatrix16();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == j)
					mat.m[i][j] = 1.0f;
				else
					mat.m[i][j] = 0.0f;
			}
		}
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static boolean GLMatrixIsIdentity(GLMatrix16 pM) {
		float f1 = 1.0f;
		float f0 = 0.0f;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (i == j && pM.m[i][j] != f1)
					return false;
				if (pM.m[i][j] != f0)
					return false;
			}
		return true;
	}

	public static GLMatrix16 GLMatrixTranspose(GLMatrix16 pOut, GLMatrix16 pM) {
		GLMatrix16 mat = new GLMatrix16();
		mat = pM;
		float temp;
		int j = 0;
		for (int i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				if (i != j) {
					temp = mat.m[i][j];
					mat.m[i][j] = mat.m[j][i];
					mat.m[j][i] = temp;
				}
			}
		}
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixTranslation(GLMatrix16 pOut, float x,
			float y, float z) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		mat.m[3][0] = x;
		mat.m[3][1] = y;
		mat.m[3][2] = z;
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixScaling(GLMatrix16 pOut, float sx,
			float sy, float sz) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		mat.m[0][0] = sx;
		mat.m[1][1] = sy;
		mat.m[2][2] = sz;
		pOut.copyFromMat16(mat);

		return pOut;
	}

	public static GLMatrix16 GLMatrixRotationAxis(GLMatrix16 pOut,
			GLVector3 pV, float angle) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		float cosTheta = (float) Math.cos(angle);
		float sinTheta = (float) Math.sin(angle);
		mat.m[0][0] = 1.0f + (1.0f - cosTheta) * ((pV.x * pV.x) - 1.0f);
		mat.m[0][1] = -pV.z * sinTheta + (1.0f - cosTheta) * pV.x * pV.y;
		mat.m[0][2] = pV.y * sinTheta + (1.0f - cosTheta) * pV.x * pV.z;

		mat.m[1][0] = pV.z * sinTheta + (1.0f - cosTheta) * pV.x * pV.y;
		mat.m[1][1] = 1.0f + (1.0f - cosTheta) * (pV.y * pV.y - 1.0f);
		mat.m[1][2] = -pV.x * sinTheta + (1.0f - cosTheta) * pV.y * pV.z;

		mat.m[2][0] = -pV.y * sinTheta + (1.0f - cosTheta) * pV.x * pV.z;
		mat.m[2][1] = pV.x * sinTheta + (1.0f - cosTheta) * pV.y * pV.z;
		mat.m[2][2] = 1.0f + (1.0f - cosTheta) * (pV.z * pV.z - 1.0f);
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixRotationX(GLMatrix16 pOut, float angle) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		float cosTheta = (float) Math.cos(angle);
		float sinTheta = (float) Math.sin(angle);
		mat.m[1][1] = cosTheta;
		mat.m[1][2] = sinTheta;
		mat.m[2][1] = -sinTheta;
		mat.m[2][2] = cosTheta;
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixRotationY(GLMatrix16 pOut, float angle) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		float cosTheta = (float) Math.cos(angle);
		float sinTheta = (float) Math.sin(angle);
		mat.m[0][0] = cosTheta;
		mat.m[0][2] = -sinTheta;
		mat.m[2][0] = sinTheta;
		mat.m[2][2] = cosTheta;
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixRotationZ(GLMatrix16 pOut, float angle) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixIdentity(mat);
		float cosTheta = (float) Math.cos(angle);
		float sinTheta = (float) Math.sin(angle);
		mat.m[1][0] = cosTheta;
		mat.m[1][1] = sinTheta;
		mat.m[2][0] = -sinTheta;
		mat.m[2][1] = cosTheta;
		pOut.copyFromMat16(mat);
		return pOut;
	}

	public static GLMatrix16 GLMatrixInverse(GLMatrix16 pOut,
			float determinent, GLMatrix16 pM) {
		// Not Complete
		return null;
	}

	// O=T(M1*M2)
	public static GLMatrix16 GLMatrixMultiplyTranspose(GLMatrix16 pOut,
			GLMatrix16 pM1, GLMatrix16 pM2) {
		GLMatrix16 mat = new GLMatrix16();
		GLMatrixMultiply(mat, pM1, pM2);
		GLMatrixTranspose(mat, pOut);
		pOut.copyFromMat16(mat);
		return pOut;
	}

	// *****************************************************
	// ***************** Vector Operations *****************
	// *****************************************************

	public static GLVector3 GLVec3Add(GLVector3 pOut, GLVector3 pV1,
			GLVector3 pV2) {
		pOut.x = pV1.x + pV2.x;
		pOut.y = pV1.y + pV2.y;
		pOut.z = pV1.z + pV2.z;
		return pOut;
	}

	public static GLVector3 GLVec3Subtract(GLVector3 pOut, GLVector3 pV1,
			GLVector3 pV2) {
		pOut.x = pV1.x - pV2.x;
		pOut.y = pV1.y - pV2.y;
		pOut.z = pV1.z - pV2.z;
		return pOut;
	}

	public static GLVector3 GLVec3Cross(GLVector3 pOut, GLVector3 pV1,
			GLVector3 pV2) {
		GLVector3 v = new GLVector3();
		v.x = pV1.y * pV2.z - pV1.z * pV2.y;
		v.y = pV1.z * pV2.x - pV1.x * pV2.z;
		v.z = pV1.x * pV2.y - pV1.y * pV2.x;
		pOut.copyFromVec3(v);

		// GLVector3 v = new GLVector3();
		/*
		 * pOut.x = pV1.y * pV2.z - pV1.z * pV2.y; pOut.y = pV1.z * pV2.x -
		 * pV1.x * pV2.z; pOut.z = pV1.x * pV2.y - pV1.y * pV2.x; pOut = v;
		 */
		return pOut;

	}

	public static float GLVec3Dot(GLVector3 pV1, GLVector3 pV2) {
		return pV1.x * pV2.x + pV1.y * pV2.y + pV1.z * pV2.z;
	}

	public static float GLVec3Length(GLVector3 pV) {
		return ((float) Math.sqrt(pV.x * pV.x + pV.y * pV.y + pV.z * pV.z));
	}

	public static GLVector3 GLVec3Normalize(GLVector3 pOut, GLVector3 pV) {
		float len = GLVec3Length(pV);
		if (len == 0.0f)
			return pV;

		pOut.x = pV.x / len;
		pOut.y = pV.y / len;
		pOut.z = pV.z / len;
		return pOut;
	}

	public static float GLVec4Length(GLVector4 pV) {
		return ((float) Math.sqrt(pV.x * pV.x + pV.y * pV.y + pV.z * pV.z
				+ pV.w * pV.w));
	}

	public static GLVector4 GLVec4Normalize(GLVector4 pOut, GLVector4 pV) {
		float len = GLVec4Length(pV);
		if (len == 0.0f)
			return pV;

		pOut.x = pV.x / len;
		pOut.y = pV.y / len;
		pOut.z = pV.z / len;
		pOut.w = pV.z / len;
		return pOut;
	}

	// GLVec3TransformNormal transforms a normal
	// using the transpose of the inverse of the given matrix
	public static GLVector3 GLVec3TransformNormal(GLVector3 pOut, GLVector3 pV,
			GLMatrix16 pM) {
		GLVector3 v = new GLVector3();
		v.x = pV.x * pM.m[0][0] + pV.y * pM.m[1][0] + pV.z * pM.m[2][0];
		v.y = pV.x * pM.m[0][1] + pV.y * pM.m[1][1] + pV.z * pM.m[2][1];
		v.z = pV.x * pM.m[0][2] + pV.y * pM.m[1][2] + pV.z * pM.m[2][2];
		pOut.copyFromVec3(v);
		return pOut;
	}

	// GLVec3TransformCoord transforms a point/coordinate/vertex-position
	// by directly transforming it with the given matrix
	public static GLVector3 GLVec3TransformCoord(GLVector3 pOut, GLVector3 pV,
			GLMatrix16 pM) {
		GLVector3 v = new GLVector3();
		v.x = pV.x * pM.m[0][0] + pV.y * pM.m[1][0] + pV.z * pM.m[2][0]
				+ pM.m[3][0];
		v.y = pV.x * pM.m[0][1] + pV.y * pM.m[1][1] + pV.z * pM.m[2][1]
				+ pM.m[3][1];
		v.z = pV.x * pM.m[0][2] + pV.y * pM.m[1][2] + pV.z * pM.m[2][2]
				+ pM.m[3][2];
		pOut.copyFromVec3(v);
		return pOut;
	}

	public static GLVector3 GLVec3Scale(GLVector3 pOut, GLVector3 pV, float s) {
		pOut.x = pV.x * s;
		pOut.y = pV.y * s;
		pOut.z = pV.z * s;
		return pOut;
	}
}