package com.gabmonteiro.cubegame;

import java.awt.*;

public class Bullet {

    public static int width = 25;
    public static int height = 25;

    private Rectangle state;
    private DirecaoBala direcaoBala;

    public Bullet(int x, int y) {
        state = new Rectangle(x, y, width, height);
    }

    private int getX() {
        return state.getLocation().x;
    }

    private int getY() {
        return state.getLocation().y;
    }

    private int getWidth() {
        return state.getSize().width;
    }

    private int getHeight() {
        return state.getSize().height;
    }

    private void moveEsquerda() {
        state.setLocation(getX() - 10, getY());
    }

    private void moveDireita() {
        state.setLocation(getX() + 10, getY());
    }

    private void moveCima() {
        state.setLocation(getX(), getY() - 10);
    }

    private void moveBaixo() {
        state.setLocation(getX(), getY() + 10);
    }

    public Rectangle getState() {
        return state;
    }

   public void render(Graphics g) {
        g.setColor(Color.red);
       g.fillRect(getX(), getY(), getWidth(),getHeight());

       state.setLocation(getX() + 20, getY());
    }
}
