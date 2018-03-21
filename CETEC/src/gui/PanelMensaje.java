package gui;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PanelMensaje extends JOptionPane {

	public PanelMensaje(String mnj,String cabecera, String Opción)
	{
		
//		setLocation(window.anchoPantalla/2, window.alturaPantalla/2);
		switch (Opción) {
		case "error":
			JOptionPane.showMessageDialog(this, mnj, cabecera,JOptionPane.ERROR_MESSAGE);
			break;
		case "info":
			JOptionPane.showMessageDialog(this, mnj, cabecera,JOptionPane.INFORMATION_MESSAGE);
		break;
		case "cancelar":
			JOptionPane.showMessageDialog(this, mnj, cabecera,JOptionPane.CANCEL_OPTION);
		break;
		default:
			break;
		}
	}
}
