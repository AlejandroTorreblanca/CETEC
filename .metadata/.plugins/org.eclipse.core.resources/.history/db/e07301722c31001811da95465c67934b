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

import gui.PanelConsultaMovimientos;
import gui.PanelMensaje;

import java.awt.Desktop;
import java.io.*;
import java.text.SimpleDateFormat; 

/**
 * Example of using the iText library to work with PDF documents on Java, 
 * lets you create, analyze, modify and maintain documents in this format.
 * Ejemplo de uso de la librerÃ­a iText para trabajar con documentos PDF en Java, 
 * nos permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org
 */
public class PDFConsultaMovimientos {
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
        
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    private PanelConsultaMovimientos w;
    
    private static final String iTextExampleImage = "Iconos/logo.jpg";
    /**
     * We create a PDF document with iText using different elements to learn 
     * to use this library.
     * Creamos un documento PDF con iText usando diferentes elementos para aprender 
     * a usar esta librerÃ­a.
     * @param pdfNewFile  <code>String</code> 
     *      pdf File we are going to write. 
     *      Fichero pdf en el que vamos a escribir. 
     */
    public void createPDF(PanelConsultaMovimientos window) {
    	this.w=window;
        try {
            Document document = new Document(PageSize.A4);;
            FileOutputStream archivo;
    		try {
				archivo = new FileOutputStream("ConsultaMovimientos.pdf");
				PdfWriter.getInstance(document, archivo);
			} catch (FileNotFoundException e) {
				new PanelMensaje("Error al crear el pdf, asegurese de que el archivo no está siendo usado por otra aplicación.", "Error", "error");
			}
            
            document.open();
            
            document.addTitle("Consulta Movimientos");
            document.addSubject("Consulta Movimientos");
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
//            BaseFont bf;
//            Font font;
//            bf = BaseFont.createFont(, , BaseFont.EMBEDDED);
            document.add(new Paragraph("CONSULTA DE MOVIMIENTOS",FontFactory.getFont("calibri",22,Font.BOLD, new BaseColor(23, 31, 100))));                    // estilo
    				 
            document.add(new Paragraph("Código del trabajo: " + w.getTextoTrabajo().getText() + ".", paragraphFont));
            document.add(new Paragraph("Parámetros de búsqueda:", paragraphFont));
            document.add(new Paragraph(
    				"Códigos:  " + w.getTextoOperarioIni().getText() + " - " + w.getTextoOperarioFin().getText() + ".",paragraphFont));
    		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    		document.add(new Paragraph(
    				"Fechas:  " + d.format(w.getFechaChooser1().getDate()) + " - " + d.format(w.getFechaChooser2().getDate()) + ".", paragraphFont));
    		document.add(new Paragraph("\n\n"));
            
            Integer numColumns = 7;
            PdfPTable table = new PdfPTable(numColumns); 
            table.setTotalWidth(new float[]{ 65,50,25,200,25,70,70 });
            table.setLockedWidth(true);
            PdfPCell columnHeader;
            
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("Fecha",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Op.",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("C.",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Descripción",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("H.",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Precio €",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Importe €",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
            
            table.setHeaderRows(1);
            PdfPCell columnCell;
            for (int row = 0; row < w.getModelo().getRowCount() ; row++) {
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getFechaSeleccionada(row), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getOperarioSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				if(w.getModelo().getOperarioSeleccionado(row).compareTo("null")==0)
					phrase.add(new Chunk("", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				else
					phrase.add(new Chunk(w.getModelo().getConceptoSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getDescripSeleccionada(row), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(Integer.toString(w.getModelo().getHoraSeleccionada(row)), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getPrecioSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
				
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getCantidadSeleccionado(row), new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnCell);
            }
            document.add(table);
            
            numColumns = 3;
            table = new PdfPTable(numColumns); 
            table.setTotalWidth(new float[]{ 80,80,80});
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            phrase = new Phrase();
            phrase.add(new Chunk("",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
            phrase = new Phrase();
            phrase.add(new Chunk("Personal",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Oficina",  new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			table.setHeaderRows(1);

			phrase = new Phrase();
			phrase.add(new Chunk("Costes €",
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoCostesPersonal().getText(),
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoCostesOficina().getText(),
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk("Coeficientes",
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoCoefPersonal().getText(),
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoCoefOficina().getText(),
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk("Total €",
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);
			
			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoPersonalTotal().getText(),
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoOficinaTotal().getText(),
					new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnCell);
			
			document.add(new Paragraph("\n\n"));
            
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Total Horas: " + w.getTextoHoras().getText() + ".", subcategoryFont));
			document.add(new Paragraph("Total Proyecto: " + w.getTextoTotalProyecto().getText() + " €.", subcategoryFont));
            document.close();
            
            String rutaArchivo = System.getProperty("user.dir")+"\\"+"ConsultaMovimientos.pdf";
            
            try {
				Desktop.getDesktop().open(new File(rutaArchivo));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Your PDF file has been generated!(Â¡Se ha generado tu hoja PDF!");
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
