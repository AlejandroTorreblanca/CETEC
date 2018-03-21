package gui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTablaConceptos extends DefaultTableModel {
	private String[] columnNames = { "CD", "Descripcion del Concepto","Precio"};

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public void addFila(String cd, String descripcion,String precio) {
		Object[] nuevafila = new Object[3];
		nuevafila[0] = cd;
		nuevafila[1] = descripcion;
		nuevafila[2] = precio;
		addRow(nuevafila);
	}

	public String getCDSeleccionado(int row) {
		return (String) getValueAt(row, 0);
	}
	
	public String getDescripcionSeleccionada(int row) {
		return (String) getValueAt(row, 1);
	}
	
	public String getPrecioSeleccionado(int row) {
		return (String) getValueAt(row, 2);
	}

}

