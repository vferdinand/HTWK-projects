package see;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class ImageComponent extends JComponent{
	public void setImage(BufferedImage image) {
		this.image = image;
		setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
		repaint();
		invalidate();
	}
	protected void paintComponent(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, this); 
	}
	private BufferedImage image;
	private static final long serialVersionUID = 1L;
}
