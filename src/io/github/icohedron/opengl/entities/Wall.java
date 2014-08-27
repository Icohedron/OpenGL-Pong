package io.github.icohedron.opengl.entities;

import io.github.icohedron.opengl.handlers.Draw;
import io.github.icohedron.opengl.handlers.Physics;

public class Wall extends GameObject {

	public static final int STANDARD_SIZE = 16;
	
	private Ball ball;
	
	public Wall(float x, float y, float width, float height, Ball ball) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.ball = ball;
	}
	
	@Override
	public void update(float delta) {
		if (Physics.checkCollisions(this, ball))
			ball.reverseY();
	}

	@Override
	public void render() {
		Draw.rect(x, y, width, height);
	}
}
