package main.java;

import java.awt.*;

public class Player {

	public boolean left, right, up, down;
	
	private Rectangle state;
	
	public Player(int x, int y) {
		state = new Rectangle(x , y, 100, 100);
	}

	public void moveEsquerda() {
		state.setLocation(state.getLocation().x - 50, state.getLocation().y);
	}

	public void moveDireita() {
		state.setLocation(state.getLocation().x + 50, state.getLocation().y);
	}

	public void moveCima() {
		state.setLocation(state.getLocation().x, state.getLocation().y - 50);
	}

	public void moveBaixo() {
		state.setLocation(state.getLocation().x, state.getLocation().y + 50);
	}

	public boolean teveColisao(Rectangle enemy) {
		return state.getBounds().intersects(enemy);
	}

	public void fps() {
		if(left == true) moveEsquerda();
		if(right == true) moveDireita();
		if(up == true) moveCima();
		if(down == true) moveBaixo();

		if(state.getLocation().y < 0) {
			state.setLocation(state.getLocation().x, 0);
		}else if(state.getLocation().y > 620) {
			state.setLocation(state.getLocation().x, 620);
		}

		if(state.getLocation().x < 0) {
			state.setLocation(0, state.getLocation().y);
		}else if(state.getLocation().x > 1180) {
			state.setLocation(1180, state.getLocation().y);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(state.getLocation().x, state.getLocation().y, state.getSize().width, state.getSize().height);
	}
}
