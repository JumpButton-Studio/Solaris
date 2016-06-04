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

public abstract class Screen {
	private boolean isVisible;

	public boolean isVisible() {
		return this.isVisible;
	}

	public abstract void render(Graphics2D g);

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
