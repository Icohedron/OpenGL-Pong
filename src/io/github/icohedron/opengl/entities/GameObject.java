package io.github.icohedron.opengl.entities;

public abstract class GameObject {

	protected float x, y;
	protected float width, height;
	
	public abstract void update(float delta);
	public abstract void render();
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getCenterY() {
		return height / 2 + y;
	}
}
