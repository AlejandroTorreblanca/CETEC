package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Imagen extends JPanel {

	public Imagen() {
		this.setSize(50, 50); // se selecciona el tamaño del panel
	}


	public void paint(Graphics grafico) {
		Dimension height = getSize();
		String nombre="Iconos/logoMini.jpg";
		String nombre_im=System.getProperty("user.dir")+"\\"+nombre;
		ImageIcon Img = new ImageIcon(nombre_im);

		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);

		setOpaque(false);
		super.paintComponent(grafico);
	}
}
