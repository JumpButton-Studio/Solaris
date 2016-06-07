/**
 * Author: Lycrios
 * Package: net.matthewauld.solaris.client
 * File: Asset.java
 * Created: Jun 3, 2016
 * Copyrighted 2016 (c)
 * ALL RIGHTS RESERVED
 */
package net.matthewauld.solaris.client.assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 */
public class Asset {
	private String			asset_path	= "";
	private AssetType		at;
	private BufferedImage	bi;
	private boolean			isLoaded	= false;

	public Asset(AssetType asset_type, String asset_path) {
		this.at = asset_type;
		this.asset_path = asset_path;
	}

	public Object getImageAsset() {
		if (at == AssetType.GRAPHIC) {
			return this.bi;
		}
		return null;
	}

	public boolean isLoaded() {
		return this.isLoaded;
	}

	public void processAsset() throws IOException {
		if (at == AssetType.GRAPHIC) {
			bi = ImageIO.read(new File(asset_path));
		}

		this.isLoaded = true;
	}
}
