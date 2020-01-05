package com.gabmonteiro.cubegame;

import java.awt.*;

public class Bullet {

    public int width = 100;
    public int height = 100;

    public Main main;

    public Rectangle state;

    public Bullet(int x, int y) {
        state = new Rectangle(x, y, width, height);
    }
    public void fps() {
        if(state.getLocation().y < -100) {
            main.removeSeABalaSairDoMapa();
        }else if(state.getLocation().y > 820) {
            main.removeSeABalaSairDoMapa();
        }

        if(state.getLocation().x < -100) {
            main.removeSeABalaSairDoMapa();
        }else if(state.getLocation().x > 1380) {
            main.removeSeABalaSairDoMapa();
        }
    }

   public void render(Graphics g) {
        g.setColor(Color.red);
       g.fillRect(state.getLocation().x, state.getLocation().y, state.getSize().width, state.getSize().height);
    }
}
