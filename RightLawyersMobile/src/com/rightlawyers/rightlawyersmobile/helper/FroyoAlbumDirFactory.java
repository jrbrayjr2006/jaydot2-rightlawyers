package com.rightlawyers.rightlawyersmobile.helper;

import java.io.File;

import android.os.Environment;

/**
 * <pre>
 * This library is was originally developed as Google sample code
 * </pre>
 *
 */
public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

	@Override
	public File getAlbumStorageDir(String albumName) {
		// TODO Auto-generated method stub
		return new File(
		  Environment.getExternalStoragePublicDirectory(
		    Environment.DIRECTORY_PICTURES
		  ), 
		  albumName
		);
	}
}
