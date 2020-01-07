package com.gabmonteiro.cubegame;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static Thread thread;
	public static JFrame frame;
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	public static boolean isRunning = false;
	public static int enemiesDie;
	public static SongUtil songUtil = new SongUtil();
	
	public static Player player = new Player(350, 350);

	public List<Enemy> enemies = new ArrayList<Enemy>();
	public List<Bullet> bullets = new ArrayList<Bullet>();
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Main() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frameConfig();
		addKeyListener(this);
	}
	

	public void frameConfig() {
		frame = new JFrame("Cube");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		System.out.println("Rodanu");
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void fps() {
		player.fps();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);

		for (Enemy enemy : enemies) {
			enemy.render(g);
		}

		for (Bullet bullet : bullets) {
			bullet.render(g);
		}

		g.setFont(new Font("Arial", 20, 20));
		g.setColor(Color.white);
		g.drawString("Kills: "+enemiesDie, 15, 30);

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH, HEIGHT, null);
		bs.show();
	}


	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();



		while(isRunning) {
			requestFocus();
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;

			if(System.currentTimeMillis() - timer >= 1 * 1000) {
				geraNovoInimigo();
			}

			if(delta >= 1) {
				fps();
				render();
				frames++;
				delta--;
		 	}

			removerInimigosComColisaoNoJogador();
			removeBalaSeSairDoMapa();
		
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}
		}
	}

	private void geraNovoInimigo() {
		int randomX = (int)(Math.random() * 1180);
		int randomY = (int)(Math.random() * 620);
		Enemy randomEnemy = new Enemy(randomX, randomY);
		enemies.add(randomEnemy);
		//songUtil.playRespawnEnemy();
	}

	private void removerInimigosComColisaoNoJogador() {
		Iterator<Enemy> iterator = enemies.iterator();
		while(iterator.hasNext()) {
			if (player.teveColisao(iterator.next().getState())) {
				iterator.remove();
				enemiesDie++;
				songUtil.playKillEnemy();
			}
		}
	}

	public void removeBalaSeSairDoMapa() {
		Iterator<Bullet> iterator = bullets.iterator();
		while(iterator.hasNext()) {
			Bullet bullet = iterator.next();
			if(bullet.getY() < -25) {
				removeBalaSeSairDoMapa();
			} else if(bullet.getY() > (HEIGHT + 25)) {
				removeBalaSeSairDoMapa();
			}

			if(bullet.getX() < -25) {
				removeBalaSeSairDoMapa();
			} else if(bullet.getX() > (WIDTH + 25)) {
				removeBalaSeSairDoMapa();
			}
		}
	}

	private void atiraCima() {
        int newX = (int) (player.getState().x + (player.getState().getWidth() / 2) - (Bullet.WIDTH / 2));
        int newY = player.getState().y;
        geraBala(newX, newY, DirecaoBala.CIMA);
	}

	private void atiraBaixo() {
        int newX = (int) (player.getState().x + (player.getState().getWidth() / 2) - (Bullet.WIDTH / 2));
        int newY = (int) (player.getState().y + player.getState().getHeight() - Bullet.HEIGHT);
        geraBala(newX, newY, DirecaoBala.BAIXO);
	}

	private void atiraEsquerda() {
        int newX = player.getState().x;
        int newY = (int) (player.getState().y + (player.getState().getHeight() /2) - (Bullet.HEIGHT / 2));
        geraBala(newX,newY, DirecaoBala.ESQUERDA);
	}

	private void atiraDireita() {
        int newX = (int) (player.getState().x + (player.getState().getWidth() - Bullet.WIDTH));
        int newY = (int) (player.getState().y + (player.getState().getHeight() / 2) - (Bullet.HEIGHT /2));
        geraBala(newX, newY, DirecaoBala.DIREITA);
	}

	private void geraBala(int x, int y, DirecaoBala direcaoBala) {
		Bullet bullet = new Bullet(x,y, direcaoBala);
		bullets.add(bullet);
	}


	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}


		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
		    atiraCima();
        }

		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
		    atiraBaixo();
        }

		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
		    atiraDireita();
        }

		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
		    atiraEsquerda();
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} else if(arg0.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}

		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} else if (arg0.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
	}


	public void keyTyped(KeyEvent arg0) {
		
	}
}
