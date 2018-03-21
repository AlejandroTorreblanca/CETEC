package gui;
import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaConsultas extends DefaultTableModel {
	private String[] columnNames = { "Fecha","Operario","Concepto","Descripción","Horas","Precio","Importe"};

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
	
	public void addFila(String fecha, String operario,String concepto, String descrip, int h, float precio, float cantidad){
		Object [] nuevafila=new Object[7];
		DecimalFormat format = new DecimalFormat("###,##0.00");
		nuevafila[0]=fecha;
		nuevafila[1]=operario;
		nuevafila[2]=concepto;
		nuevafila[3]=descrip;
		nuevafila[4]=h;
		nuevafila[5]=format.format(precio);
		nuevafila[6]=format.format(cantidad);
		addRow(nuevafila);
	}
	
	public String getFechaSeleccionada(int row){
		return (String)getValueAt(row, 0);	
	}
	
	public String getOperarioSeleccionado(int row){
		return (String)getValueAt(row, 1);	
	}
	
	public String getConceptoSeleccionado(int row){
		String str=(String)getValueAt(row, 2);
		if(str==null)
			return "";
		return str;	
	}
	
	public String getDescripSeleccionada(int row){
		return (String)getValueAt(row, 3);	
	}
	
	public int getHoraSeleccionada(int row){
		return (int)getValueAt(row, 4);	
	}
	
	public String getPrecioSeleccionado(int row){
		return (String)getValueAt(row, 5);	
	}
	
	public String getCantidadSeleccionado(int row){
		return (String)getValueAt(row, 6);	
	}

	
}
