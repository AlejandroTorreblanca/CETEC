package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class PanelMovimientos extends JPanel implements ActionListener {

	private JPanel panelCentral;
	private JButton opeButton;
	private JButton concepButton;
	
	public PanelMovimientos(VentanaPrincipal w) {

		PanelMovOpe panelOpe = new PanelMovOpe(w);
		PanelMovConcep panelConcep = new PanelMovConcep(w);
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new CardLayout());
		panelCentral.add(panelOpe, "Operario");
		panelCentral.add(panelConcep, "Concepto");

		opeButton = new JButton("Operario");
		opeButton.setMargin(new Insets(2, 33, 2, 34));
		opeButton.addActionListener(this);
		concepButton = new JButton("Conceptos");
		concepButton.setMargin(new Insets(2, 28, 2, 28));
		concepButton.addActionListener(this);
		
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
		pOeste.add(opeButton);
		pOeste.add(Box.createRigidArea(new Dimension(10, 10)));
		pOeste.add(concepButton);

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



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == opeButton) {
			CardLayout cl=(CardLayout)panelCentral.getLayout();
			cl.show(panelCentral, "Operario");
			validate();	
		}
		else if (e.getSource() == concepButton) {
			CardLayout cl=(CardLayout)panelCentral.getLayout();
			cl.show(panelCentral, "Concepto");
			validate();	
		}
		
	}
		

	

	
	
}
