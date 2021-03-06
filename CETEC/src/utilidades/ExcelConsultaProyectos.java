package utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import gui.PanelConsultaProyectos;
import gui.PanelMensaje;

public class ExcelConsultaProyectos {

	public ExcelConsultaProyectos(PanelConsultaProyectos w) {
		String rutaArchivo = System.getProperty("user.dir")+"\\"+"ConsultaProyectos.xls";
		File archivoXLS = new File(rutaArchivo);
		if (archivoXLS.exists())
			archivoXLS.delete();
		try {
			archivoXLS.createNewFile();
		} catch (IOException e) {
			new PanelMensaje("Error en la creaci�n del documento Excel.\n"+e, "Error en los datos", "error");
			e.printStackTrace();
		}
		
		try {
			Workbook libro = new HSSFWorkbook();
			FileOutputStream archivo = new FileOutputStream(archivoXLS);
			Sheet hoja = libro.createSheet("Hoja1");
			
			int columnas = 100;
			Row [] c = new Row[columnas];
			Cell [] f = new Cell[columnas];
			c[0] = hoja.createRow(0);
			f[2] = c[0].createCell(2);
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			f[2].setCellValue("Proyectos con movimientos entre las fechas:  " + d.format(w.getFechaChooser1().getDate()) + " - " + d.format(w.getFechaChooser2().getDate()) + ".");
    		
			
			c[2] = hoja.createRow(2);
			for (int i = 0; i < columnas; i++) {
				f[i] = c[2].createCell(i);
				hoja.autoSizeColumn(i);
			}
			
			f[0].setCellValue("n");
			f[1].setCellValue("C�digo");
			f[2].setCellValue("Nombre");
			f[3].setCellValue("Tipo");
			f[4].setCellValue("Estatus");
			f[5].setCellValue("Cliente");
			f[6].setCellValue("Presup. �");
			f[7].setCellValue("Costes �");
			f[8].setCellValue("Margen �");
			f[9].setCellValue("Margen %");
			
			for (int i = 3; i < w.getModelo().getRowCount()+3; i++) {
				c[i] = hoja.createRow(i);

				f[0] = c[i].createCell(0);
				f[0].setCellValue(w.getModelo().getNumeroSeleccionado(i - 3));

				f[1] = c[i].createCell(1);
				f[1].setCellValue(w.getModelo().getCodSeleccionado(i - 3));
				
				f[2] = c[i].createCell(2);
				f[2].setCellValue(w.getModelo().getNombreSeleccionado(i - 3));
				
				f[3] = c[i].createCell(3);
				f[3].setCellValue(w.getModelo().getTipoSeleccionado(i - 3));
				
				f[4] = c[i].createCell(4);
				f[4].setCellValue(w.getModelo().getEstatusSeleccionada(i - 3));
				
				f[5] = c[i].createCell(5);
				f[5].setCellValue(w.getModelo().getClienteSeleccionado(i - 3));
				
				f[6] = c[i].createCell(6);
				f[6].setCellValue(Float.parseFloat(w.getModelo().getPresupSeleccionado(i - 3).replace(".", "").replace(",", ".")));
				
				f[7] = c[i].createCell(7);
				f[7].setCellValue(Float.parseFloat(w.getModelo().getCostesSeleccionado(i - 3).replace(".", "").replace(",", ".")));
				
				f[8] = c[i].createCell(8);
				f[8].setCellValue(Float.parseFloat(w.getModelo().getMargenSeleccionado(i - 3).replace(".", "").replace(",", ".")));
				
				f[9] = c[i].createCell(9);
				f[9].setCellValue(Float.parseFloat(w.getModelo().getMargenPSeleccionado(i - 3).replace(".", "").replace(",", ".")));

			}
			for (int i = 0; i < columnas; i++) {
				hoja.autoSizeColumn(i);
			}
			
			try {
				libro.write(archivo);
				archivo.close();
				libro.close();
		        Desktop.getDesktop().open(new File(rutaArchivo));
			} catch (IOException e) {
				new PanelMensaje("Error en la creaci�n del documento Excel.\n"+e, "Error en los datos", "error");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			new PanelMensaje("Error al crear el Excel, asegurese de que el archivo no est� siendo utilizado.", "Error", "error");
			e.printStackTrace();
		}
		
		
		
	}
}





















