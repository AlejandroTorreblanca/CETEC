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

/**
 * Example of using the iText library to work with PDF documents on Java, 
 * lets you create, analyze, modify and maintain documents in this format.
 * Ejemplo de uso de la librería iText para trabajar con documentos PDF en Java, 
 * nos permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org
 */
public class PDFConsultaProyectos {
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    
    private PanelConsultaProyectos w;
    
    private static final String iTextExampleImage = "Iconos/logo.jpg";
    /**
     * We create a PDF document with iText using different elements to learn 
     * to use this library.
     * Creamos un documento PDF con iText usando diferentes elementos para aprender 
     * a usar esta librería.
     * @param pdfNewFile  <code>String</code> 
     *      pdf File we are going to write. 
     *      Fichero pdf en el que vamos a escribir. 
     */
    public void createPDF(PanelConsultaProyectos window) {
    	this.w=window;
        try {
            Document document = new Document(PageSize.A4);
            FileOutputStream archivo;
    		try {
				archivo = new FileOutputStream("ConsultaProyectos.pdf");
				PdfWriter.getInstance(document, archivo);
			} catch (FileNotFoundException e) {
				new PanelMensaje("Error al crear el pdf, asegurese de que el archivo no est� siendo usado por otra aplicaci�n.", "Error", "error");
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
    				FontFactory.getFont("arial",   // fuente
    				15,                            // tama�o
    				Font.BOLD,                   // estilo
    				new BaseColor(23, 31, 100))));   
    		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    		document.add(new Paragraph(
    				"Fechas:  " + d.format(w.getFechaChooser1().getDate()) + " - " + d.format(w.getFechaChooser2().getDate()) + ".", paragraphFont));
    		document.add(new Paragraph("\n\n"));
            
            Integer numColumns = 10;
            PdfPTable table = new PdfPTable(numColumns); 
            table.setTotalWidth(new float[]{ 15,25,150,25,40,100,60,60,60,45 });
            table.setLockedWidth(true);
            PdfPCell columnHeader;
            
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("n",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Cod",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Nombre",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("T.",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			
			phrase.add(new Chunk("Estatus",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			
			phrase.add(new Chunk("Cliente",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Presup �",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Costes �",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Margen �",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Margen %",  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
            
            table.setHeaderRows(1);
            PdfPCell columnCell;
            for (int row = 0; row < w.getModelo().getRowCount() ; row++) {
				phrase = new Phrase();
				phrase.add(new Chunk(Integer.toString(w.getModelo().getNumeroSeleccionado(row)), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getCodSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getNombreSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getTipoSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				if(w.getModelo().getEstatusSeleccionada(row).compareTo("null")==0)
					phrase.add(new Chunk("", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL)));
				else
					phrase.add(new Chunk(w.getModelo().getEstatusSeleccionada(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getClienteSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getPresupSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getCostesSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getMargenSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getMargenPSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
            }
            document.add(table);
            
           
            document.close();
            String rutaArchivo = System.getProperty("user.dir")+"\\"+"ConsultaProyectos.pdf";
            
            try {
				Desktop.getDesktop().open(new File(rutaArchivo));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
//        generatePDFFileIText.createPDF();
//    }
}
