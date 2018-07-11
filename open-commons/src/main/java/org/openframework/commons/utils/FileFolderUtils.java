package org.openframework.commons.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

public class FileFolderUtils {

	public static Properties loadPropFromFile(File innerFile) {
		Properties props = new Properties();
		try {
			props.load(new FileReader(innerFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static File[] getMessagePropertyFileList(String path) {

		final File dir = new File(path);
		return dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});
	}

}
