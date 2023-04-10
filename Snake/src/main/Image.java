package main;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Image 
{
	public Image() {}
	
	public BufferedImage loader(String filePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));
		}
		catch(IOException e) {
			System.err.println(e);
		}
		
		return img;
	}
}