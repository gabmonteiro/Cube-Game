package com.gabmonteiro.cubegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

    private Rectangle state;

    public Enemy(int x, int y) {
        state = new Rectangle(x, y, 100, 100);
    }

    public Rectangle getState() {
        return state;
    }

    public boolean teveColisaoComOutroInimigo(Rectangle enemy) {
        return state.getBounds().intersects(enemy);
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(state.getLocation().x, state.getLocation().y, state.getSize().width, state.getSize().height);
    }
}
