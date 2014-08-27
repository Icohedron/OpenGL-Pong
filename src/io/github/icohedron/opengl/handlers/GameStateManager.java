package io.github.icohedron.opengl.handlers;

import io.github.icohedron.opengl.states.GameState;
import io.github.icohedron.opengl.states.PlayState;

import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> gamestates;
	
	public final short PLAY = 2;
	
	public GameStateManager() {
		gamestates = new Stack<GameState>();
		gamestates.push(getState(PLAY));
	}
	
	public void update(float delta) {
		gamestates.peek().update(delta);
	}
	
	public void render() {
		gamestates.peek().render();
	}
	
	public void setState(short state) {
		gamestates.pop();
		gamestates.push(getState(state));
	}
	
	private GameState getState(short state) {
		if (state == 2) return new PlayState(this);
		return null;
	}
}
