package com.gabmonteiro.cubegame;

import java.awt.*;

public class Bullet {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    private Rectangle state;
    private DirecaoBala direcaoBala;
    private int speed = 20;

    public Bullet(int x, int y, DirecaoBala direcaoBala) {
        this.state = new Rectangle(x, y, WIDTH, HEIGHT);
        this.direcaoBala = direcaoBala;
    }

    public int getX() {
        return state.getLocation().x;
    }

    public int getY() {
        return state.getLocation().y;
    }

    private int getWidth() {
        return state.getSize().width;
    }

    private int getHeight() {
        return state.getSize().height;
    }

    private void moveEsquerda() {
        state.setLocation(getX() - speed, getY());
    }

    private void moveDireita() {
        state.setLocation(getX() + speed, getY());
    }

    private void moveCima() {
        state.setLocation(getX(), getY() - speed);
    }

    private void moveBaixo() {
        state.setLocation(getX(), getY() + speed);
    }

    public boolean teveColisao(Rectangle bullet) {
        return state.getBounds().intersects(bullet);
    }

    public Rectangle getState() {
        return state;
    }

   public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(),getHeight());

        if(direcaoBala == DirecaoBala.CIMA) {
            moveCima();
        }
        if(direcaoBala == DirecaoBala.BAIXO) {
            moveBaixo();
        }
        if(direcaoBala == DirecaoBala.ESQUERDA) {
            moveEsquerda();
        }
        if(direcaoBala == DirecaoBala.DIREITA) {
            moveDireita();
        }
    }
}
