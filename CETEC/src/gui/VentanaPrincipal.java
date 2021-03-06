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

import controlador.Controlador;

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
		
	}
	
	private void close(){
        Controlador.getUnicaInstancia().close();
		System.exit(0);
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
		panelOperarios.activarFoco();
	}
	
	public void setPanelTrabajos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "Trabajos");
		validate();	
		panelTrabajos.activarFoco();
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
		panelMovimientos.activarFoco();
	}
	
	public void setPanelConsultaMovimientos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "ConsultaMovimientos");
		validate();	
		panelConsultaMovimientos.activarFoco();
	}
	
	public void setPanelConsultaProyectos(){
		CardLayout cl=(CardLayout)panelCentral.getLayout();
		cl.show(panelCentral, "ConsultaProyectos");
		validate();	
		panelConsultaProyectos.activarFoco();
	}
	

	public VentanaPrincipal() {	
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
		inicializarPaneles();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamPantalla.height;
		int anchoPantalla = tamPantalla.width;
		setSize(anchoPantalla, alturaPantalla-40 );
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
