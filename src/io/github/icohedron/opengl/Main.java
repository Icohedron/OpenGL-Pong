package io.github.icohedron.opengl;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import io.github.icohedron.opengl.handlers.GameStateManager;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main implements Runnable {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private final String title = "OpenGL";
	
	private boolean running = false;
	private Thread thread;
	
	private GameStateManager gsm;
	
	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		
		gsm = new GameStateManager();
	}
	
	public void init() {
		glClearColor(0f, 0f, 0f, 1.0f);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		glDisable(GL_DEPTH_TEST);
	}
	
	@Override
	public void run() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(title);
			Display.setResizable(false);
			Display.create();
			
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		long lastTimer = System.nanoTime();
		float delta = 0;
		double nsPerTick = 1000000000 / 60D;
		
		double timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		init();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTimer) / nsPerTick;
			lastTimer = now;
			
			while (delta > 1) {
				update(delta);
				updates++;
				delta--;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			glClear(GL_COLOR_BUFFER_BIT);
			render();
			frames++;
			Display.update();
			
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				Display.setTitle(title + " | " + updates + " ups, "+ frames + " fps");
				frames = 0;
				updates = 0;
			}
			
			if (Display.isCloseRequested()) running = false;
		}
		
		Display.destroy();
		Keyboard.destroy();
	}
	
	private void update(float delta) {
		gsm.update(delta);
	}
	
	private void render() {
		gsm.render();
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}
