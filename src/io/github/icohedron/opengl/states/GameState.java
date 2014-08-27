package io.github.icohedron.opengl.states;

import io.github.icohedron.opengl.handlers.GameStateManager;

public abstract class GameState {

	private GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	abstract void handleInput(float delta);
	
	public abstract void update(float delta);
	public abstract void render();
}
