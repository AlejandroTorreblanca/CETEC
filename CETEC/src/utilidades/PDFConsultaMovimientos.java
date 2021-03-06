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
import java.text.DecimalFormat;

public class PDFConsultaMovimientos {
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL);

	private static final Font subcategoryFont = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

	private PanelConsultaMovimientos w;

	private static final String iTextExampleImage = "Iconos/logo.jpg";

	public void createPDF(PanelConsultaMovimientos window) {
		this.w = window;
		try {
			Document document = new Document(PageSize.A4);
			;
			FileOutputStream archivo;
			try {
				archivo = new FileOutputStream("ConsultaMovimientos.pdf");
				PdfWriter.getInstance(document, archivo);
			} catch (FileNotFoundException e) {
				new PanelMensaje(
						"Error al crear el pdf, asegurese de que el archivo no est� siendo usado por otra aplicaci�n.",
						"Error", "error");
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
				System.out.println("Image BadElementException" + ex);
			} catch (IOException ex) {
				System.out.println("Image IOException " + ex);
			}
			document.add(new Paragraph(w.getTextoTrabajo().getText() + " - " + w.getTextoNombre().getText(),
					FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, new BaseColor(23, 31, 100)))); // estilo

			document.add(new Paragraph("Cliente: " + w.buscarCliente(), paragraphFont));
			document.add(new Paragraph("\n\n"));

			Integer numColumns = 7;
			PdfPTable table = new PdfPTable(numColumns);
			table.setTotalWidth(new float[] { 65, 50, 25, 200, 35, 70, 70 });
			table.setLockedWidth(true);
			PdfPCell columnHeader;
			table.setHorizontalAlignment(Element.ALIGN_RIGHT);

			BaseColor color = new BaseColor(191, 191, 191);
			Phrase phrase = new Phrase();
			phrase.add(new Chunk("Fecha", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Op.", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("C.", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Descripci�n", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Horas", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Precio �", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Importe �", new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);

			table.setHeaderRows(1);
			PdfPCell columnCell;
			for (int row = 0; row < w.getModelo().getRowCount(); row++) {
				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getFechaSeleccionada(row),
						new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);

				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getOperarioSeleccionado(row),
						new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);

				phrase = new Phrase();
				if (w.getModelo().getOperarioSeleccionado(row).compareTo("null") == 0)
					phrase.add(new Chunk("", new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				else
					phrase.add(new Chunk(w.getModelo().getConceptoSeleccionado(row),
							new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);

				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getDescripSeleccionada(row),
						new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);

				DecimalFormat format = new DecimalFormat("###,##0.00");
				phrase = new Phrase();
				phrase.add(new Chunk(format.format(w.getModelo().getHoraSeleccionada(row)),
						new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);

				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getPrecioSeleccionado(row),
						new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);

				phrase = new Phrase();
				phrase.add(new Chunk(w.getModelo().getCantidadSeleccionado(row),
						new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
				columnCell = new PdfPCell(phrase);
				columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
				columnCell.setBorder(0);
				table.addCell(columnCell);
			}
			
			
			document.add(table);

			numColumns = 3;
			table = new PdfPTable(numColumns);
			table.setTotalWidth(new float[] { 80, 80, 80 });
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_RIGHT);

			phrase = new Phrase();
			phrase.add(new Chunk("", new Font(Font.FontFamily.COURIER, 11, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Personal", new Font(Font.FontFamily.COURIER, 9, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			phrase = new Phrase();
			phrase.add(new Chunk("Oficina", new Font(Font.FontFamily.COURIER, 9, Font.BOLD)));
			columnHeader = new PdfPCell(phrase);
			columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
			columnHeader.setBorder(0);
			columnHeader.setBackgroundColor(color);
			table.addCell(columnHeader);
			table.setHeaderRows(1);

			phrase = new Phrase();
			phrase.add(new Chunk("Costes �", new Font(Font.FontFamily.COURIER, 9, Font.BOLD)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(
					new Chunk(w.getTextoCostesPersonal().getText(), new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(
					new Chunk(w.getTextoCostesOficina().getText(), new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(new Chunk("Coeficientes", new Font(Font.FontFamily.COURIER, 9, Font.BOLD)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(
					new Chunk(w.getTextoCoefPersonal().getText(), new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(new Chunk(w.getTextoCoefOficina().getText(), new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(new Chunk("Total �", new Font(Font.FontFamily.COURIER, 9, Font.BOLD)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(
					new Chunk(w.getTextoPersonalTotal().getText(), new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			phrase = new Phrase();
			phrase.add(
					new Chunk(w.getTextoOficinaTotal().getText(), new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
			columnCell = new PdfPCell(phrase);
			columnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			columnCell.setVerticalAlignment(Element.ALIGN_CENTER);
			columnCell.setBorder(0);
			table.addCell(columnCell);

			document.add(new Paragraph("\n\n"));

			document.add(table);
			document.add(new Paragraph("\n"));
			Paragraph p = new Paragraph("N�mero de horas: " + w.getTextoHoras().getText() + "h", subcategoryFont);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			p = new Paragraph("Total Proyecto: " + w.getTextoTotalProyecto().getText() + " �", subcategoryFont);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.close();

			String rutaArchivo = System.getProperty("user.dir") + "\\" + "ConsultaMovimientos.pdf";

			try {
				Desktop.getDesktop().open(new File(rutaArchivo));
			} catch (IOException e) {
				new PanelMensaje("Error en la creaci�n del documento PDF.\n" + e, "Error en los datos", "error");
				e.printStackTrace();
			}
			System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}
}
