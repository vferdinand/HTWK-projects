package see;

import java.io.*;
import java.io.FileFilter;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Random;

public class See {
	private static void insertFirstFree(TitleImage bild) 
		throws Exception
	{
		int stop, p;
		stop = p = zufall.nextInt(64);
		boolean notInserted = true;
		while (notInserted) {
			if (auswahl[p] == null) {
				auswahl[p] = bild;
				notInserted = false;
			} else {
				p = (p+1) & 0x3F;
				if (p == stop) throw new Exception();
			}
		
		}
	}
	public static void fischeAussetzen() {
		File f , bilderDir;
		File[] ls;
		String dir = "";
		String filename;
		String token;
		f = new File(".");
		try {
			dir = f.getCanonicalPath() + File.separator +"Bilder" + File.separator;
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		bilderDir = new File(dir);
		ls = bilderDir.listFiles(new FileFilter() {
										public boolean accept(File f) {
											return (f.isFile() && f.getName().endsWith(".jpg"));
										}
	  	  							}
			); 
		baseImages = new TitleImage[ls.length];
		for (int i = 0; i < ls.length; i++){
			baseImages[i] = new TitleImage();
		}
		for (int i = 0; i < ls.length; i++) {
			try {
				baseImages[i].bild = ImageIO.read(ls[i]);
			} catch(IOException e) {
				System.out.println(e.toString());
			}
			filename = ls[i].getName();
			Scanner scanner = new Scanner(filename).useDelimiter("[_\\.]");
			token = scanner.next();
			baseImages[i].anzahl = Integer.valueOf(token);
			if (scanner.next().equals("g")) {
				token = scanner.next();
				baseImages[i].title = "Gotcha! " + token + " gefangen";
				baseImages[i].fish = true;
			} else {
				token = scanner.next();
				baseImages[i].title = "Ooops.. " + token + " gefangen";
				baseImages[i].fish = false;
			}
			for (int k = 0; k < baseImages[i].anzahl; k++) {
				try {
					insertFirstFree(baseImages[i]);
				} catch (Exception e) {
					break;
				}
			}
		}
	}
	private static JFrame getWindow(BufferedImage image) {
		if (window != null) {
			dasBild.setImage(image);
			return window;
		} else {
			window = new JFrame();
			window.setSize(500,350);
			window.setLayout(new FlowLayout(FlowLayout.CENTER));
			dasBild.setImage(image);
			window.add(dasBild);			
			//bild.setUndecorated(true);
			return (window);
		}
	}
	public static boolean catchFish(boolean test) {
		TitleImage bild = auswahl[zufall.nextInt(64)];
		if (!test) {
			JFrame wnd = getWindow(bild.bild);
			//wnd.setSize(new Dimension((bild.bild.getWidth()+10), bild.bild.getHeight()+ 40));
			wnd.setTitle(bild.title);
			wnd.setAlwaysOnTop(true);
			wnd.setVisible(true);
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			wnd.setVisible(false);
		} else {
			System.out.println(bild.title);
		}
		return bild.fish;
	}
	private static TitleImage[] auswahl = new TitleImage[64];
	private static TitleImage[] baseImages;
	private static Random zufall = new Random();
	private static JFrame window = null;
	private static ImageComponent dasBild = new ImageComponent();
	public static void dispose() {
		window.dispose();
	}
}
