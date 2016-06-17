/**
 * Author: Lycrios
 * Package: com.example.solaris_game
 * File: Game.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package com.example.solaris_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import net.matthewauld.solaris.client.Client;
import net.matthewauld.solaris.common.Log;

public class Game extends Client {
	private static final long	serialVersionUID	= -9142163124484779291L;

	public static void main(String[] args) {
		setGameName("Nogard Online");
		setGameSize(800, 600);
		new Game();
	}

	public Game() {
		Log.TRACE();
		Log.trace("Trace Label");
		Log.debug("Debug Label");
		Log.info("Info Label");
		Log.warn("Warning Label");
		Log.error("Error Label");
		try {
			this.loadingScreen.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int gg = (int) (Math.random() * 255);
		int rr = (int) (Math.random() * 255);
		int bb = (int) (Math.random() * 255);
		g.setColor(new Color(rr, gg, bb));
		g.fillArc(0, 0, 100, 100, 0, 360);
	}

	@Override
	public void updateGame() {
		Log.trace("tick");
	}
}
