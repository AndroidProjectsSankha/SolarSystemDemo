package com.exer.math;
public class GLColor4 {
	public float r, g, b, a;

	public GLColor4() {
		this.r = 0.0f;
		this.g = 0.0f;
		this.b = this.a = 1.0f;
	}

	public GLColor4(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
}
