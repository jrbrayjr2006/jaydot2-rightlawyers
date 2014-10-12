package com.rightlawyers.rightlawyersmobile.helper;

import java.io.File;

import android.os.Environment;

/**
 * <pre>
 * This library is was originally developed as Google sample code
 * </pre>
 *
 */
public final class BaseAlbumDirFactory extends AlbumStorageDirFactory {

	// Standard storage location for digital camera files
	private static final String CAMERA_DIR = "/dcim/";

	@Override
	public File getAlbumStorageDir(String albumName) {
		return new File (
				Environment.getExternalStorageDirectory()
				+ CAMERA_DIR
				+ albumName
		);
	}
}
