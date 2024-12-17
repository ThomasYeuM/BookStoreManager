package util;

import java.io.File;

public class GetFilePath {
	public static String getAbsoluteFilePath() {
		File file = new File("");
        String currentDirectory = file.getAbsolutePath();
        return currentDirectory;
	}
}
