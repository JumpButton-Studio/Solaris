/**
 * Author: Lycrios
 * Package: net.matthewauld.solaris.client
 * File: Client.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package net.matthewauld.solaris.client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Client extends Canvas implements Runnable {
	public static final int		CLIENT_HEIGHT		= 720;
	public static final int		CLIENT_WIDTH		= 1280;
	public static final String	GAME_NAME			= "Nogard";
	private static boolean		GAME_RUNNING		= true;
	private static final long	serialVersionUID	= 1965121834424532560L;

	public static void main(String[] args) {
		new Client();
	}

	private JFrame window;

	public Client() {
		// Start Game Window
		window = new JFrame(GAME_NAME + " ~ Solaris Engine");
		window.setSize(CLIENT_WIDTH, CLIENT_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		createBufferStrategy(2);
		// Start Logic & Graphic Loop
		new Thread(this).start();
	}

	public void render(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void run() {

		BufferStrategy bs = getBufferStrategy();
		while (GAME_RUNNING) {
			render((Graphics2D) bs.getDrawGraphics());
			bs.show();

		}
	}
}
