/**
 * Author: Lycrios
 * Package: net.matthewauld.solaris.client.screen
 * File: Screen.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package net.matthewauld.solaris.client.screen;

import java.awt.Graphics2D;

import net.matthewauld.solaris.client.Client;

public abstract class Screen {
	private Client	c;
	private int		height	= 0;
	private boolean	isVisible;
	private int		width	= 0;

	public Screen(Client c) {
		this.c = c;
		this.width = c.getWidth();
		this.height = c.getHeight();
	}

	public int getHeight() {
		this.height = c.getHeight();
		return this.height;
	}

	public int getWidth() {
		this.width = c.getWidth();
		return this.width;
	}

	public boolean isVisible() {
		return this.isVisible;
	}

	public abstract void render(Graphics2D g);

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
