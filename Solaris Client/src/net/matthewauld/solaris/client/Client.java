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
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import net.matthewauld.solaris.client.assets.Asset;
import net.matthewauld.solaris.client.assets.AssetType;
import net.matthewauld.solaris.client.screen.LoadingScreen;
import net.matthewauld.solaris.client.screen.Screen;
import net.matthewauld.solaris.common.Log;

public abstract class Client extends Canvas implements Runnable {
	private static Color		BASE_COLOR			= Color.CYAN;
	public static int			CLIENT_HEIGHT		= 720;
	public static int			CLIENT_WIDTH		= 1280;
	public static String		GAME_NAME			= "EXAMPLE GAME";
	private static boolean		GAME_RUNNING		= true;
	private static final long	serialVersionUID	= 1965121834424532560L;

	public static void setBaseColor(Color base) {
		BASE_COLOR = base;
	}

	public static void setGameName(String gameName) {
		GAME_NAME = gameName;
	}

	public static void setGameSize(int width, int height) {
		CLIENT_WIDTH = width;
		CLIENT_HEIGHT = height;
	}

	private ArrayList<Asset>	assets	= new ArrayList<Asset>();

	protected LoadingScreen		loadingScreen;
	private ArrayList<Screen>	screens	= new ArrayList<Screen>();
	private BufferStrategy		strategy;
	private JFrame				window;

	public Client() {
		loadingScreen = new LoadingScreen(this);
		loadingScreen.setVisible(true);
		addScreen(loadingScreen);
		startGame();
	}

	public void addAsset(AssetType at, String assetURL) {
		assets.add(new Asset(at, assetURL));
	}

	public void addScreen(Screen s) {
		screens.add(s);
	}

	/**
	 * Do all graphic rendering here.
	 *
	 * @param g
	 */
	public abstract void render(Graphics2D g);

	private void renderSystem(Graphics2D g) {
		g.setColor(BASE_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (Screen s : screens) {
			if (s.isVisible()) {
				s.render(g);
			}
		}

		render(g);
	}

	@Override
	public void run() {
		strategy = getBufferStrategy();
		long fpsWait = (long) (1.0 / 60 * 1000);
		main: while (GAME_RUNNING) {
			long renderStart = System.nanoTime();
			updateGame();
			// Update Graphics
			do {
				Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
				if (!GAME_RUNNING) {
					break main;
				}
				renderSystem(g);
			} while (!updateScreen());

			// Better do some FPS limiting here
			long renderTime = (System.nanoTime() - renderStart) / 1000000;
			try {
				Thread.sleep(Math.max(0, fpsWait - renderTime));
			} catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
			renderTime = (System.nanoTime() - renderStart) / 1000000;
			window.setTitle(GAME_NAME + " - FPS: " + (1000 / renderTime));
		}

		window.dispose();
		System.exit(-1);
	}

	private void startGame() {
		window = new JFrame(GAME_NAME + " ~ Solaris Engine");
		window.setSize(CLIENT_WIDTH, CLIENT_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setLocationRelativeTo(null);
		window.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Log.dumpToFile(new File("Log.log"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		window.setVisible(true);
		createBufferStrategy(2);
		// Start Logic & Graphic Loop
		new Thread(this).start();
	}

	public abstract void updateGame();

	private boolean updateScreen() {
		try {
			strategy.show();
			Toolkit.getDefaultToolkit().sync();
			return (!strategy.contentsLost());

		} catch (NullPointerException e) {
			return true;

		} catch (IllegalStateException e) {
			return true;
		}
	}
}
