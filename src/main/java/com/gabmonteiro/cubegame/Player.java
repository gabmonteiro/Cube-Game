package com.gabmonteiro.cubegame;

import java.awt.*;

public class Player {

	public boolean left, right, up, down;

	private int speed = 15;
	
	private Rectangle state;

	public int getX() {
		return state.getLocation().x;
	}

	public int getY() {
		return state.getLocation().y;
	}
	
	public Player(int x, int y) {
		state = new Rectangle(x , y, 100, 100);
	}

	public void moveEsquerda() {
		state.setLocation(getX() - speed, getY());
	}

	public void moveDireita() {
		state.setLocation(getX() + speed, getY());
	}

	public void moveCima() {
		state.setLocation(getX(), getY() - speed);
	}

	public void moveBaixo() {
		state.setLocation(getX(), getY() + speed);
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

		if(getY() < -100) {
			state.setLocation(getX(), 820);
		}else if(getY() > 820) {
			state.setLocation(getX(), -100);
		}

		if(getX() < -100) {
			state.setLocation(1380, getY());
		}else if(getX() > 1380) {
			state.setLocation(-100, getY());
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), state.getSize().width, state.getSize().height);
	}
}
