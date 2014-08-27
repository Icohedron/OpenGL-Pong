package io.github.icohedron.opengl.states;

import static org.lwjgl.opengl.GL11.glColor3f;
import io.github.icohedron.opengl.Main;
import io.github.icohedron.opengl.entities.Ball;
import io.github.icohedron.opengl.entities.GameObject;
import io.github.icohedron.opengl.entities.Opponent;
import io.github.icohedron.opengl.entities.Player;
import io.github.icohedron.opengl.entities.Wall;
import io.github.icohedron.opengl.handlers.GameStateManager;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class PlayState extends GameState {
	
	private ArrayList<GameObject> objects;
	
	private Ball ball;
	private Player player;
	
//	private int playerScore;
//	private int enemyScore;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
//		playerScore = 0;
//		enemyScore = 0;
		
		objects = new ArrayList<GameObject>();
		
		ball = new Ball(
				Main.WIDTH / 2 - Ball.SIZE / 2,
				Main.HEIGHT / 2 - Ball.SIZE / 2);
		
		player = new Player(Main.WIDTH - Player.WIDTH * 2, Main.HEIGHT / 2 - Player.HEIGHT / 2, ball);
		
		objects.add(ball);
		objects.add(player);
		objects.add(new Opponent(Opponent.WIDTH, Main.HEIGHT / 2 - Opponent.HEIGHT / 2, ball));
		objects.add(new Wall(0, -Wall.STANDARD_SIZE, Main.WIDTH, Wall.STANDARD_SIZE, ball));
		objects.add(new Wall(0, Main.HEIGHT, Main.WIDTH, Wall.STANDARD_SIZE, ball));
	}
	
	@Override
	void handleInput(float delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.move(1, delta);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.move(-1, delta);
		}
	}

	@Override
	public void update(float delta) {
		handleInput(delta);
		
		for (GameObject go : objects) {
			go.update(delta);
		}
		
		if (ball.getX() > Main.WIDTH) {
//			playerScore++;
			ball.resetPosition();
		}
		
		if (ball.getX() < 0) {
//			enemyScore++;
			ball.resetPosition();
		}
	}

	@Override
	public void render() {
		glColor3f(1.0f, 1.0f, 1.0f);
		
		for (GameObject go : objects) {
			go.render();
		}
	}
}
