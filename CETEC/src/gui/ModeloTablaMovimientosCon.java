package gui;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaMovimientosCon extends DefaultTableModel {
	private String[] columnNames = { "Número","Trabajo","Fecha","Concepto","Descripción","Cant."};

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
	
	public void addFila(String numero,String trabajo, String fecha,String concepto, String descrip,int cantidad){
		Object [] nuevafila=new Object[6];
		nuevafila[0]=numero;
		nuevafila[1]=trabajo;
		nuevafila[2]=fecha;
		nuevafila[3]=concepto;
		nuevafila[4]=descrip;
		nuevafila[5]=cantidad;
		addRow(nuevafila);
	}
	
	public String getNumeroSeleccionado(int row){
		return (String)getValueAt(row, 0);	
	}

	public String getTrabajoSeleccionado(int row){
		return (String)getValueAt(row, 1);	
	}
	
	public String getFechaSeleccionada(int row){
		return (String)getValueAt(row, 2);	
	}
	
	public String getConceptoSeleccionado(int row){
		String str=(String)getValueAt(row, 3);
		if(str==null)
			return "";
		return str;	
	}
	
	public String getDescripSeleccionada(int row){
		return (String)getValueAt(row, 4);	
	}
	
	public int getCantidadSeleccionada(int row){
		return (int)getValueAt(row, 5);	
	}
	
	
	
}

