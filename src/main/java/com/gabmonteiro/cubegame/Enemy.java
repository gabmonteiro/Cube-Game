package com.gabmonteiro.cubegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

public class Enemy {

    private Rectangle state;

    public Enemy(int x, int y) {
        state = new Rectangle(x, y, 100, 100);
    }

    public Rectangle getState() {
        return this.state;
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(state.getLocation().x, state.getLocation().y, state.getSize().width, state.getSize().height);
    }
}
