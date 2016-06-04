/**
 * Author: Lycrios
 * Package: com.example.solaris_game
 * File: LoadingScreen.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package com.example.solaris_game;

import java.awt.Color;
import java.awt.Graphics2D;

import net.matthewauld.solaris.client.Client;
import net.matthewauld.solaris.client.screen.Screen;

/**
 *
 */
public class LoadingScreen extends Screen {

	/**
	 * @param c
	 */
	public LoadingScreen(Client c) {
		super(c);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * net.matthewauld.solaris.client.screen.Screen#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		int w = 400;
		int h = 35;
		int x = (getWidth() / 2) - (w / 2);
		int y = (getHeight() / 2) - (h / 2);
		g.drawRect(x, y, w, h);
	}

}
