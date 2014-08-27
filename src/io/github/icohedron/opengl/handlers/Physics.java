package io.github.icohedron.opengl.handlers;

import java.awt.Rectangle;

import io.github.icohedron.opengl.entities.GameObject;

public class Physics {
	
	public static boolean checkCollisions(GameObject go1, GameObject go2) {
		Rectangle r1 = new Rectangle((int) go1.getX(),(int) go1.getY(),(int) go1.getWidth(),(int) go1.getHeight());
		Rectangle r2 = new Rectangle((int) go2.getX(),(int) go2.getY(),(int) go2.getWidth(),(int) go2.getHeight());
	
		return r1.intersects(r2);
	}
}
