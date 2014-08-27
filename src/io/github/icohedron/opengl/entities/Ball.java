package io.github.icohedron.opengl.entities;

import io.github.icohedron.opengl.handlers.Draw;

public class Ball extends GameObject {

	public static final int SIZE = 16;
	public static final float MAX_SPEEDX = 4f;
	public static final float MAX_SPEEDY = 4f;
	public static final float DAMPING = 0.05f;
	
	private float velX;
	private float velY;
	
	private float startX;
	private float startY;
	
	public Ball(float x, float y) {
		this.x = x;
		this.y = y;
		
		startX = x;
		startY = y;
		
		width = SIZE;
		height = SIZE;
		
		velX = -MAX_SPEEDX;
		velY = 0;
	}
	
	public void reverse(float center) {
		velX *= -1;
		velY += (getCenterY() - center) * DAMPING;
		
		if (velY > MAX_SPEEDY)
			velY = MAX_SPEEDY;
		if (velY < -MAX_SPEEDY)
			velY = -MAX_SPEEDY;
	}
	
	public void reverseY() {
		velY *= -1;
	}
	
	public void resetPosition() {
		x = startX;
		y = startY;
		
		velY = 0;
		velX *= -1;
	}
	
	@Override
	public void update(float delta) {
		x += velX * delta;
		y += velY * delta;
	}

	@Override
	public void render() {
		Draw.rect(x, y, width, height);
	}
}
