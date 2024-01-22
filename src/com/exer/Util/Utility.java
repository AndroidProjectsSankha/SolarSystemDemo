package com.exer.Util;

//import java.lang.reflect.Array;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.exer.math.GLColor4;
import com.exer.math.GLVector3;
import com.exer.math.GLVector4;

public class Utility {

	static final float GL_PI = 3.14f;

	public static Exception SetFloatBufferSize3(FloatBuffer Buffer,
			GLVector3 vecIn) {
		try {
			Buffer.put(vecIn.x);
			Buffer.put(vecIn.y);
			Buffer.put(vecIn.z);
			Buffer.position(0);

			return null;
		} catch (Exception e) {
			return e;
		}
	}

	public static Exception SetFloatBufferSize4(FloatBuffer Buffer,
			GLVector4 col) {
		try {
			Buffer.put(col.x);
			Buffer.put(col.y);
			Buffer.put(col.z);
			Buffer.put(col.w);
			Buffer.position(0);

			return null;
		} catch (Exception e) {
			return e;
		}
	}

	public static Exception SetFloatBufferSize4(FloatBuffer Buffer, GLColor4 col) {
		try {
			Buffer.put(col.r);
			Buffer.put(col.g);
			Buffer.put(col.b);
			Buffer.put(col.a);
			Buffer.position(0);

			return null;
		} catch (Exception e) {
			return e;
		}
	}

	public static Exception SetLookAt(GL10 gl, GLVector3 eyePos,
			GLVector3 lookAt, GLVector3 upVec) {
		try {
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			GLU.gluLookAt(gl, eyePos.x, eyePos.y, eyePos.z, lookAt.x, lookAt.y,
					lookAt.z, upVec.x, upVec.y, upVec.z);
			return null;
		} catch (Exception e) {
			return e;
		}

	}

	public static double GLToRadian(float degree) {
		double res = (degree * GL_PI) / 180.0f;
		return res;
	}

	public static double GLToDegree(float radian) {
		double res = (radian * 180.0f) / GL_PI;
		return res;
	}

	// Implementation of SizeOf()

	// private static final int SZ_REF = 4;

	public static int sizeof(boolean b) {
		return 1;
	}

	public static int sizeof(byte b) {
		return 1;
	}

	public static int sizeof(char c) {
		return 2;
	}

	public static int sizeof(short s) {
		return 2;
	}

	public static int sizeof(int i) {
		return 4;
	}

	public static int sizeof(long l) {
		return 8;
	}

	public static int sizeof(float f) {
		return 4;
	}

	public static int sizeof(double d) {
		return 8;
	}

	// private static int sizeof(Class c)
	// {
	// Field flds[] = c.getDeclaredFields();
	// int sz = 0;
	//
	// for (int i = 0; i < flds.length; i++) {
	// Field f = flds[i];
	// if (!c.isInterface() &&
	// (f.getModifiers() & Modifier.STATIC) != 0)
	// continue;
	// sz += size_prim(f.getType());
	// }
	//
	// if (c.getSuperclass() != null)
	// sz += sizeof(c.getSuperclass());
	//
	// Class cv[] = c.getInterfaces();
	// for (int i = 0; i < cv.length; i++)
	// sz += sizeof(cv[i]);
	//
	// return sz;
	// }
	//     
	// private static int size_prim(Class t)
	// {
	// if (t == Boolean.TYPE)
	// return 1;
	// else if (t == Byte.TYPE)
	// return 1;
	// else if (t == Character.TYPE)
	// return 2;
	// else if (t == Short.TYPE)
	// return 2;
	// else if (t == Integer.TYPE)
	// return 4;
	// else if (t == Long.TYPE)
	// return 8;
	// else if (t == Float.TYPE)
	// return 4;
	// else if (t == Double.TYPE)
	// return 8;
	// else if (t == Void.TYPE)
	// return 0;
	// else
	// return SZ_REF;
	// }
	//
	// private static int sizeof(Object obj, Class c)
	// {
	// Class ct = c.getComponentType();
	// int len = Array.getLength(obj);
	//
	// if (ct.isPrimitive()) {
	// return len * size_prim(ct);
	// }
	// else {
	// int sz = 0;
	// for (int i = 0; i < len; i++) {
	// sz += SZ_REF;
	// Object obj2 = Array.get(obj, i);
	// if (obj2 == null)
	// continue;
	// Class c2 = obj2.getClass();
	// if (!c2.isArray())
	// continue;
	// sz += sizeof(obj2, c2);
	// }
	// return sz;
	// }
	// }
	//
	// public static int sizeof(Object obj)
	// {
	// if (obj == null)
	// return 0;
	//
	// Class c = obj.getClass();
	//
	// if (c.isArray())
	// return sizeof(obj, c);
	// else
	// return sizeof(c);
	// }

}