package com.gabmonteiro.cubegame;

import java.awt.*;

public class Player {

	public boolean left, right, up, down;

	private int speed = 25;
	
	private Rectangle state;
	
	public Player(int x, int y) {
		state = new Rectangle(x , y, 100, 100);
	}

	public void moveEsquerda() {
		state.setLocation(state.getLocation().x - speed, state.getLocation().y);
	}

	public void moveDireita() {
		state.setLocation(state.getLocation().x + speed, state.getLocation().y);
	}

	public void moveCima() {
		state.setLocation(state.getLocation().x, state.getLocation().y - speed);
	}

	public void moveBaixo() {
		state.setLocation(state.getLocation().x, state.getLocation().y + speed);
	}

	public boolean teveColisao(Rectangle enemy) {
		return state.getBounds().intersects(enemy);
	}

	public Rectangle getState() {
		return state;
	}

	public void fps() {
		if(left == true) moveEsquerda();
		if(right == true) moveDireita();
		if(up == true) moveCima();
		if(down == true) moveBaixo();

		if(state.getLocation().y < -100) {
			state.setLocation(state.getLocation().x, 820);
		}else if(state.getLocation().y > 820) {
			state.setLocation(state.getLocation().x, -100);
		}

		if(state.getLocation().x < -100) {
			state.setLocation(1380, state.getLocation().y);
		}else if(state.getLocation().x > 1380) {
			state.setLocation(-100, state.getLocation().y);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(state.getLocation().x, state.getLocation().y, state.getSize().width, state.getSize().height);
	}
}
