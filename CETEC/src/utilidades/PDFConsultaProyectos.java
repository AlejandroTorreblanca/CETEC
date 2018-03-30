package utilidades;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gui.PanelConsultaProyectos;
import gui.PanelMensaje;

import java.awt.Desktop;
import java.io.*;
import java.text.SimpleDateFormat; 

public class PDFConsultaProyectos {
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.COURIER, 10, Font.NORMAL);
    
    private PanelConsultaProyectos w;
    
    private static final String iTextExampleImage = "Iconos/logo.jpg";
    public void createPDF(PanelConsultaProyectos window) {
    	this.w=window;
        try {
            Document document = new Document(PageSize.A4.rotate());
            FileOutputStream archivo;
    		try {
				archivo = new FileOutputStream("ConsultaProyectos.pdf");
				PdfWriter.getInstance(document, archivo);
			} catch (FileNotFoundException e) {
				new PanelMensaje("Error al crear el pdf, asegurese de que el archivo no está siendo usado por otra aplicación.", "Error", "error");
			}
            
            document.open();
            
            document.addTitle("Consulta Proyectos");
            document.addSubject("Consulta Proyectos");
            document.addKeywords("Java, PDF, Consulta");
            document.addAuthor("CETEC");
            document.addCreator("CETEC");
            
            Image image;
            try {
                image = Image.getInstance(iTextExampleImage);  
                image.scaleToFit(50, 50);
                document.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
            document.add(new Paragraph("CONSULTA DE PROYECTOS",
    				FontFactory.getFont(FontFactory.COURIER,   // fuente
    				15,                            // tamaño
    				Font.BOLD,                   // estilo
    				new BaseColor(23, 31, 100))));   
    		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    		document.add(new Paragraph(
    				"Proyectos con movimientos entre las fechas:  " + d.format(w.getFechaChooser1().getDate()) + " - " + d.format(w.getFechaChooser2().getDate()) + ".", paragraphFont));
    		document.add(new Paragraph("\n\n"));
            
            Integer numColumns = 10;
            PdfPTable table = new PdfPTable(numColumns); 
            table.setTotalWidth(new float[]{ 20,35,160,30,50,110,80,80,80,45 });
            table.setLockedWidth(true);
            PdfPCell columnHeader;
            
            BaseColor color = new BaseColor(191, 191, 191);
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("n",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setMinimumHeight(18f);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Cod",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Nombre",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("T.",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			
			phrase.add(new Chunk("Estatus",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			
			phrase.add(new Chunk("Cliente",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Presup €",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Costes €",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Margen €",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Margen %",  new Font(Font.FontFamily.COURIER, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
            
            table.setHeaderRows(1);
            PdfPCell columnCell;
            int n= w.getModelo().getRowCount();
            for (int row = 0; row < n-1 ; row++) {
				phrase = new Phrase();
				phrase.add(new Chunk(Integer.toString(w.getModelo().getNumeroSeleccionado(row)), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				columnCell.setMinimumHeight(24f);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getCodSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getNombreSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getTipoSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				if(w.getModelo().getEstatusSeleccionada(row)==null)
					phrase.add(new Chunk("", new Font(Font.FontFamily.COURIER, 8, Font.NORMAL)));
				else
					phrase.add(new Chunk(w.getModelo().getEstatusSeleccionada(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getClienteSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getPresupSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getCostesSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getMargenSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getMargenPSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(columnCell);
            }
            int row=n-1;
            phrase = new Phrase();
			phrase.add(new Chunk(Integer.toString(w.getModelo().getNumeroSeleccionado(row)), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			columnCell.setMinimumHeight(24f);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getCodSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getNombreSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getTipoSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			if(w.getModelo().getEstatusSeleccionada(row).compareTo("null")==0)
				phrase.add(new Chunk("", new Font(Font.FontFamily.COURIER, 8, Font.NORMAL)));
			else
				phrase.add(new Chunk(w.getModelo().getEstatusSeleccionada(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getClienteSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getPresupSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getCostesSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getMargenSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getModelo().getMargenPSeleccionado(row), new Font(Font.FontFamily.COURIER, 7, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			columnCell.setBackgroundColor(color);
			table.addCell(columnCell);
            
            document.add(table);
            
           
            document.close();
            String rutaArchivo = System.getProperty("user.dir")+"\\"+"ConsultaProyectos.pdf";
            
            try {
				Desktop.getDesktop().open(new File(rutaArchivo));
			} catch (IOException e) {
				new PanelMensaje("Error en la creación del documento PDF.\n"+e, "Error en los datos", "error");
				e.printStackTrace();
			}
            System.out.println("Your PDF file has been generated!(Â¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }
}
