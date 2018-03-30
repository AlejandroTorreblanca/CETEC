package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class PanelMovimientos extends JPanel {

	private JPanel panelCentral;
	
	public PanelMovimientos() {

		PanelOperario panelOpe = new PanelOperario();
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new CardLayout());
		panelCentral.add(panelOpe, "Operario");

		JLabel imagen=new JLabel();
		imagen.setMaximumSize(new Dimension(80, 74));
		JPanel pNorte = new JPanel();
		JPanel pOeste = new JPanel();
		
		String nombre="Iconos/logoMini.jpg";
		String nombre_im=System.getProperty("user.dir")+"\\"+nombre;
		imagen.setIcon(new ImageIcon(nombre_im));
		pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));
		pOeste.setAlignmentY(Component.CENTER_ALIGNMENT);
		pOeste.add(imagen);
		pOeste.add(Box.createRigidArea(new Dimension(25, 200)));
		pOeste.add(Box.createRigidArea(new Dimension(10, 10)));

		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE MOVIMIENTOS", SwingConstants.CENTER);
		Font font = new Font("Calibri", Font.BOLD, 40);
		rotuloSuperior.setFont(font);
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));

		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);
		add(pOeste, BorderLayout.WEST);
		add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.EAST);
		add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);
	}




	

	
	
}
