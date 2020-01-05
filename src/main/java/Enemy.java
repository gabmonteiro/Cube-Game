package main.java;

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

    public void moveEsquerda() {
        state.setLocation(state.getLocation().x - 10, state.getLocation().y);
    }

    public void moveDireita() {
        state.setLocation(state.getLocation().x + 10, state.getLocation().y);
    }

    public void moveCima() {
        state.setLocation(state.getLocation().x, state.getLocation().y - 10);
    }

    public void moveBaixo() {
        state.setLocation(state.getLocation().x, state.getLocation().y + 10);
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(state.getLocation().x, state.getLocation().y, state.getSize().width, state.getSize().height);

        //if (posicaoCima) {
            moveCima();
        //}
    }
}
