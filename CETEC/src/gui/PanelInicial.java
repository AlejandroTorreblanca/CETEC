package gui;

import java.awt.BorderLayout;
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
public class PanelInicial extends JPanel implements ActionListener{

	private VentanaPrincipal window;
	private JButton operariosButton;
	private JButton trabajosButton;
	private JButton conceptosButton;
	private JButton coeficientesButton;
	private JButton movimientosButton;
	private JButton consultaMovButton;
	private JButton consultaProyecButton;
	
	public PanelInicial(VentanaPrincipal w) {
		
		this.window=w;
		trabajosButton = new JButton("Mantenimiento de Trabajos");
		trabajosButton.setMargin(new Insets(2, 85, 2, 87));
		trabajosButton.addActionListener(this);
		
		operariosButton = new JButton("Mantenimiento de Operarios");
		operariosButton.setMargin(new Insets(2, 85, 2, 80));
		operariosButton.addActionListener(this);
		
		conceptosButton = new JButton("Mantenimiento de Conceptos");
		conceptosButton.setMargin(new Insets(2, 80, 2, 81));
		conceptosButton.addActionListener(this);
		
		coeficientesButton = new JButton("Mantenimiento de Coeficientes");
		coeficientesButton.setMargin(new Insets(2, 75, 2, 76));
		coeficientesButton.addActionListener(this);
		
		movimientosButton = new JButton("Mantenimiento de Movimientos");
		movimientosButton.setMargin(new Insets(2, 75, 2, 75));
		movimientosButton.addActionListener(this);
		
		consultaMovButton = new JButton("Consulta de Movimientos");
		consultaMovButton.setMargin(new Insets(2, 92, 2, 92));
		consultaMovButton.addActionListener(this);
		
		consultaProyecButton = new JButton("Consulta de Proyectos");
		consultaProyecButton.setMargin(new Insets(2, 100, 2, 99));
		consultaProyecButton.addActionListener(this);
		
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		JPanel pCentro = new JPanel();
		JPanel pSur = new JPanel();
		JPanel pOeste = new JPanel();
		JPanel pCentral = new JPanel();
		
		JLabel rotuloSuperior = new JLabel("MENU DE OPCIONES", SwingConstants.CENTER);
		Font font = new Font("Calibri", Font.BOLD, 40);
		rotuloSuperior.setFont(font);
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.setMaximumSize(new Dimension(700, 100));
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));
		
		pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
		pCentro.setAlignmentY(Component.CENTER_ALIGNMENT);
		pCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentro.add(trabajosButton); 
		pCentro.add(Box.createRigidArea(new Dimension(15, 15)));
		pCentro.add(operariosButton);
		pCentro.add(Box.createRigidArea(new Dimension(15, 15)));
		pCentro.add(conceptosButton); 
		pCentro.add(Box.createRigidArea(new Dimension(15, 15)));
		pCentro.add(coeficientesButton); 
		pCentro.add(Box.createRigidArea(new Dimension(15, 15)));
		pCentro.add(movimientosButton); 
		pCentro.add(Box.createRigidArea(new Dimension(15, 15)));
		pCentro.add(consultaMovButton);
		pCentro.add(Box.createRigidArea(new Dimension(15, 15)));
		pCentro.add(consultaProyecButton);
		
		JPanel pCentralaux = new JPanel();
		pCentralaux.setLayout(new BoxLayout(pCentralaux, BoxLayout.X_AXIS));
		JLabel imagen=new JLabel();
		imagen.setMaximumSize(new Dimension(500, 333));
		String nombre="Iconos/fotoCETEC.jpg";
		String nombre_im=System.getProperty("user.dir")+"\\"+nombre;
		imagen.setIcon(new ImageIcon(nombre_im));
		pCentralaux.add(pCentro);
		pCentralaux.add(Box.createRigidArea(new Dimension(50, 50)));
		pCentralaux.add(imagen);
		
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentral.setAlignmentY(Component.CENTER_ALIGNMENT);
		pCentral.add(Box.createRigidArea(new Dimension(150, 150)));
		pCentral.add(pCentralaux);
		
		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);

		JLabel logo=new JLabel();
		logo.setMaximumSize(new Dimension(80, 74));
		nombre="Iconos/logoMini.jpg";
		nombre_im=System.getProperty("user.dir")+"\\"+nombre;
		logo.setIcon(new ImageIcon(nombre_im));
		pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));
		pOeste.setAlignmentY(Component.LEFT_ALIGNMENT);
		pOeste.add(logo);
		pOeste.add(Box.createRigidArea(new Dimension(25, 200)));
		add(pOeste, BorderLayout.WEST);
		
		add(pCentral,BorderLayout.CENTER);
		
		pEste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pEste, BorderLayout.EAST);
		
		pSur.add(Box.createRigidArea(new Dimension(50, 50)));
		add(pSur, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== operariosButton){
			window.setPanelOperarios();
		}
		else if (e.getSource()== trabajosButton){
			window.setPanelTrabajos();
		}
		else if (e.getSource()== conceptosButton){
			window.setPanelCoenceptos();
		}
		else if (e.getSource()== coeficientesButton){
			window.setPanelCoeficientes();
		}
		else if (e.getSource()== movimientosButton){
			window.setPanelMovimientos();
		}
		else if (e.getSource()== consultaMovButton){
			window.setPanelConsultaMovimientos();
		}
		else if (e.getSource()== consultaProyecButton){
			window.setPanelConsultaProyectos();
		}
	}
}
