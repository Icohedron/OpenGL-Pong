package io.github.icohedron.opengl.entities;

import io.github.icohedron.opengl.handlers.Draw;
import io.github.icohedron.opengl.handlers.Physics;

public class Player extends GameObject {

	public static final int WIDTH = 16;
	public static final int HEIGHT = WIDTH * 7;
	
	public static final float SPEED = 4f;
	
	private Ball ball;
	
	public Player(float x, float y, Ball ball) {
		this.x = x;
		this.y = y;
		
		width = WIDTH;
		height = HEIGHT;
		
		this.ball = ball;
	}
	
	public void move(float mag, float delta) {
		y += SPEED * mag * delta;
	}
	
	@Override
	public void update(float delta) {
		if (Physics.checkCollisions(this, ball))
			ball.reverse(getCenterY());
	}

	@Override
	public void render() {
		Draw.rect(x, y, width, height);
	}
}
