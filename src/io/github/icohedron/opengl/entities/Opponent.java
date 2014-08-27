package io.github.icohedron.opengl.entities;

import io.github.icohedron.opengl.handlers.Draw;
import io.github.icohedron.opengl.handlers.Physics;

public class Opponent extends GameObject {

	public static final int WIDTH = 16;
	public static final int HEIGHT = WIDTH * 7;
	public static final float MAX_SPEEDY = 8f;
	public static final float DAMPING = 0.05f;
	
	private Ball ball;
	
	public Opponent(float x, float y, Ball ball) {
		this.x = x;
		this.y = y;
		
		width = WIDTH;
		height = HEIGHT;
		
		this.ball = ball;
	}
	
	@Override
	public void update(float delta) {
		if (Physics.checkCollisions(this, ball))
			ball.reverse(getCenterY());
		
		float speed = (ball.getCenterY() - getCenterY()) * DAMPING;
		
		if (speed > MAX_SPEEDY)
			speed = MAX_SPEEDY;
		if (speed < -MAX_SPEEDY)
			speed = -MAX_SPEEDY;
		
		y += speed * delta;
	}

	@Override
	public void render() {
		Draw.rect(x, y, width, height);
	}
}
