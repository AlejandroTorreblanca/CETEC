package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private static VentanaPrincipal window;
	private PanelMovimientos panelOperario;

	
	public void inicializarPaneles() {

		panelOperario = new PanelMovimientos();
		add(panelOperario,BorderLayout.CENTER);
	}
	

	public VentanaPrincipal() {	
		inicializarPaneles();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamPantalla.height;
		int anchoPantalla = tamPantalla.width;
		setSize(anchoPantalla, (int) (alturaPantalla-50 ));
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CETEC");
		Image miIcono = miPantalla.getImage("Iconos/logo.jpg");
		setIconImage(miIcono);
		setVisible(true);
		validate();
		setResizable(true);
		
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new VentanaPrincipal();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//Controlador.getUnicaInstancia().close();
			}
		});
	}

}
