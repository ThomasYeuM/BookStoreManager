package util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageUtils {
	
	
	public JLabel getImage(String filePath) throws IOException {
		Image image = ImageIO.read(new File(filePath));
		return new JLabel(new ImageIcon(image));
	}
}
