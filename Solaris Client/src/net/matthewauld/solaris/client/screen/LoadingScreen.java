/**
 * Author: Lycrios
 * Package: net.matthewauld.solaris.client.screen
 * File: LoadingScreen.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package net.matthewauld.solaris.client.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import net.matthewauld.solaris.client.Client;

/**
 *
 */
public class LoadingScreen extends Screen implements Runnable {
	private static Thread loadingThread;

	/**
	 * @param c
	 */
	public LoadingScreen(Client c) {
		super(c);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		int w = 400;
		int h = 35;
		int x = (getWidth() / 2) - (w / 2);
		int y = (getHeight() / 2) - (h / 2);
		g.drawRect(x, y, w, h);

	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() throws Exception {
		if (loadingThread == null) {
			loadingThread = new Thread(this);
			loadingThread.start();
		} else {
			throw new Exception("Loading Screen Thread Already Started");
		}
	}

}
