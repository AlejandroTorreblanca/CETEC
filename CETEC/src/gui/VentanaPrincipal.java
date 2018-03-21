package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private static VentanaPrincipal window;
	private PanelInicial panelInicial;
	private PanelTrabajos panelTrabajos;
	private PanelOperarios panelOperarios;
	private PanelMovimientos panelMovimientos;
	private PanelCoeficientes panelCoeficientes;
	private PanelConceptos panelConceptos;
	private PanelConsultaMovimientos panelConsultaMovimientos;
	private PanelConsultaProyectos panelConsultaProyectos;
	private JPanel panelCentral;

	
	private void inicializarPaneles() {
		panelTrabajos = new PanelTrabajos(this);
		panelOperarios=new PanelOperarios(this);
		panelInicial = new PanelInicial(this);
		panelConsultaMovimientos = new PanelConsultaMovimientos(this);
		panelCoeficientes = new PanelCoeficientes(this);
		panelConceptos = new PanelConceptos(this);
		panelCentral = new JPanel();
		panelMovimientos= new PanelMovimientos(this);
		panelConsultaProyectos=new PanelConsultaProyectos(this);
		
		panelCentral.setLayout(new CardLayout());
		panelCentral.add(panelInicial, "Inicial");
		panelCentral.add(panelTrabajos, "Trabajos");
		panelCentral.add(panelOperarios, "Operarios");
		panelCentral.add(panelConsultaMovimientos, "ConsultaMovimientos");
		panelCentral.add(panelCoeficientes, "Coeficientes");
		panelCentral.add(panelConceptos, "Conceptos");
		panelCentral.add(panelMovimientos, "Movimientos");
		panelCentral.add(panelConsultaProyectos, "ConsultaProyectos");
		panelCentral.setAlignmentX(CENTER_ALIGNMENT);
		panelCentral.setAlignmentY(CENTER_ALIGNMENT);
		
		new BoxLayout(this, BoxLayout.Y_AXIS);
		add(Box.createRigidArea(new Dimension(100, 100)));
		add(panelCentral);
		
//		JPanel p0 = new JPanel();
//		p0.add(Box.createRigidArea(new Dimension(50, 50)));
//		add(p0, BorderLayout.NORTH);
//		JPanel p1 = new JPanel();
//		p1.add(Box.createRigidArea(new Dimension(100, 100)));
//		add(p1, BorderLayout.WEST);
//		add(panelCentral,BorderLayout.CENTER);
//		JPanel p2 = new JPanel();
//		p2.add(Box.createRigidArea(new Dimension(100, 100)));
//		add(p2, BorderLayout.EAST);
//		JPanel p3 = new JPanel();
//		p3.add(Box.createRigidArea(new Dimension(50, 50)));
//		add(p3, BorderLayout.SOUTH);
		
	}
	
	public void setPanelInicial(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Inicial");
		validate();	
	}
	
	public void setPanelOperarios(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Operarios");
		validate();	
	}
	
	public void setPanelTrabajos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Trabajos");
		validate();	
	}
	
	public void setPanelCoeficientes(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Coeficientes");
		validate();	
	}
	
	public void setPanelCoenceptos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Conceptos");
		validate();	
	}
	
	public void setPanelMovimientos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Movimientos");
		validate();	
	}
	
	public void setPanelConsultaMovimientos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "ConsultaMovimientos");
		validate();	
	}
	
	public void setPanelConsultaProyectos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "ConsultaProyectos");
		validate();	
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
