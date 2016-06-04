/**
 * Author: Lycrios
 * Package: com.example.solaris_game
 * File: Game.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package com.example.solaris_game;

import net.matthewauld.solaris.client.Client;

public class Game extends Client {
	private static final long serialVersionUID = -9142163124484779291L;

	public static void main(String[] args) {
		setGameName("Nogard Online");
		setGameSize(800, 600);
		new Game();
	}

	public Game() {
		LoadingScreen ls = new LoadingScreen(this);
		addScreen(ls);
		ls.setVisible(true);
	}

}
