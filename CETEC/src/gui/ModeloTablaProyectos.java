package gui;
import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaProyectos extends DefaultTableModel {
	private String[] columnNames = { "n","Código","Nombre","Tipo","Estatus","Cliente","Presup. €","Costes €","Margen €","Margen %"};

	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int column) {
	    return columnNames[column];
	}
	
	public void addFila(int n, String cod,String nombre, String tipo, String estatus, String cliente, float presup,float costes){
		Object [] nuevafila=new Object[10];
		
		float margen=presup-costes;
		float margenP=0;
		if(presup!=0)
			margenP=(100*margen)/presup;
			
		DecimalFormat format = new DecimalFormat("###,##0.00");
		nuevafila[0]=n;
		nuevafila[1]=cod;
		if(nombre==null)
			nuevafila[2]="";
		else
			nuevafila[2]=nombre;
		if(tipo==null)
			nuevafila[3]="";
		else
			nuevafila[3]=tipo;
		if(estatus==null)
			nuevafila[4]="";
		else
			nuevafila[4]=estatus;
		if(cliente==null)
			nuevafila[5]="";
		else
			nuevafila[5]=cliente;
		nuevafila[6]=format.format(presup);
		nuevafila[7]=format.format(costes);
		nuevafila[8]=format.format(margen);
		nuevafila[9]=format.format(margenP);
		addRow(nuevafila);
	}
	
	public int getNumeroSeleccionado(int row){
		return (int)getValueAt(row, 0);	
	}
	
	public String getCodSeleccionado(int row){
		return (String)getValueAt(row, 1);	
	}
	
	public String getNombreSeleccionado(int row){
		return (String)getValueAt(row, 2);	
	}
	
	public String getTipoSeleccionado(int row){
		String str=(String)getValueAt(row, 3);
		if(str==null)
			return "";
		return str;	
	}
	
	public String getEstatusSeleccionada(int row){
		return (String)getValueAt(row, 4);	
	}
	
	public String getClienteSeleccionado(int row){
		return (String)getValueAt(row, 5);	
	}
	
	public String getPresupSeleccionado(int row){
		return (String)getValueAt(row, 6);	
	}
	
	public String getCostesSeleccionado(int row){
		return (String)getValueAt(row, 7);	
	}
	
	public String getMargenSeleccionado(int row){
		return (String)getValueAt(row, 8);	
	}
	
	public String getMargenPSeleccionado(int row){
		return (String)getValueAt(row, 9);	
	}
	
	

	
}
