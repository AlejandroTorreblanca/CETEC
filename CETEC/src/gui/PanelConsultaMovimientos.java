package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import com.toedter.calendar.JDateChooser;

import controlador.Controlador;
import utilidades.PDFConsultaMovimientos;

@SuppressWarnings("serial")
public class PanelConsultaMovimientos extends JPanel implements ActionListener, KeyListener{

	private VentanaPrincipal window;
	private Controlador controlador;
	private JButton imprimirButton;
	private JButton cancelarButton;
	private JTextField textoTrabajo;
	private JTextField textoOperarioIni;
	private JTextField textoOperarioFin;
	private JTextField textoPersonalTotal;
	private JTextField textoOficinaTotal;
	private JTextField textoHoras;
	private JTextField textoTotalProyecto;
	private JTextField textoCostesPersonal;
	private JTextField textoCostesOficina;
	
	public JTextField getTextoTrabajo() {
		return textoTrabajo;
	}

	public JTextField getTextoOperarioIni() {
		return textoOperarioIni;
	}

	public JTextField getTextoOperarioFin() {
		return textoOperarioFin;
	}

	public JTextField getTextoPersonalTotal() {
		return textoPersonalTotal;
	}

	public JTextField getTextoOficinaTotal() {
		return textoOficinaTotal;
	}

	public JTextField getTextoHoras() {
		return textoHoras;
	}

	public JTextField getTextoTotalProyecto() {
		return textoTotalProyecto;
	}

	public JTextField getTextoCostesPersonal() {
		return textoCostesPersonal;
	}

	public JTextField getTextoCostesOficina() {
		return textoCostesOficina;
	}

	public JTextField getTextoCoefPersonal() {
		return textoCoefPersonal;
	}

	public JTextField getTextoCoefOficina() {
		return textoCoefOficina;
	}

	public JDateChooser getFechaChooser1() {
		return fechaChooser1;
	}

	public JDateChooser getFechaChooser2() {
		return fechaChooser2;
	}

	public JTextField getTextoNombre() {
		return textoNombre;
	}

	public ModeloTablaConsultas getModelo() {
		return modelo;
	}

	private JTextField textoCoefPersonal;
	private JTextField textoCoefOficina;
	private JDateChooser fechaChooser1;
	private JDateChooser fechaChooser2;
	private JTextField textoNombre;
	private JTable tabla;
	private ModeloTablaConsultas modelo;
	private JScrollPane scrollPane;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelConsultaMovimientos(VentanaPrincipal w) {

		this.window = w;
		controlador = Controlador.getUnicaInstancia();
		JLabel rotuloTrabajo = new JLabel("Cod. Trabajo: ", SwingConstants.CENTER);
		JLabel rotuloOperarioIni = new JLabel("Desde Operario: ", SwingConstants.CENTER);
		JLabel rotuloOperarioFin = new JLabel("Hasta Operario: ", SwingConstants.CENTER);
		JLabel rotuloFecha1 = new JLabel("Desde Fecha: ", SwingConstants.CENTER);
		JLabel rotuloFecha2 = new JLabel("Hasta Fecha: ", SwingConstants.CENTER);
		JLabel rotuloTotal = new JLabel("Total(�): ", SwingConstants.CENTER);
		JLabel rotuloTotalProyecto = new JLabel("Total Proyecto(�): ", SwingConstants.CENTER);
		JLabel rotuloPersonal = new JLabel("Personal ", SwingConstants.CENTER);
		JLabel rotuloCoef = new JLabel("Coeficientes: ", SwingConstants.CENTER);
		JLabel rotuloCostes = new JLabel("Costes(�): ", SwingConstants.CENTER);
		JLabel rotuloHoras = new JLabel("Total Horas: ", SwingConstants.CENTER);
		JLabel rotuloOficina = new JLabel("Oficina        ", SwingConstants.CENTER);
		
		textoTrabajo = new JTextField("");
		fixedSize(textoTrabajo, 50, 30);
		textoOperarioIni = new JTextField("");
		fixedSize(textoOperarioIni, 50, 30);
		textoOperarioFin = new JTextField("");
		fixedSize(textoOperarioFin, 50, 30);
		textoNombre = new JTextField("");
		textoNombre.setEditable(false);
		fixedSize(textoNombre, 400, 30);
		
		textoCostesPersonal = new JTextField("");
		fixedSize(textoCostesPersonal, 100, 30);
		textoCostesPersonal.setEditable(false);
		textoCostesPersonal.setHorizontalAlignment(JTextField.RIGHT);
		textoCostesOficina = new JTextField("");
		fixedSize(textoCostesOficina, 100, 30);
		textoCostesOficina.setEditable(false);
		textoCostesOficina.setHorizontalAlignment(JTextField.RIGHT);
		textoCoefOficina = new JTextField("");
		fixedSize(textoCoefOficina, 100, 30);
		textoCoefOficina.setEditable(false);
		textoCoefOficina.setHorizontalAlignment(JTextField.RIGHT);
		textoCoefPersonal = new JTextField("");
		fixedSize(textoCoefPersonal, 100, 30);
		textoCoefPersonal.setEditable(false);
		textoCoefPersonal.setHorizontalAlignment(JTextField.RIGHT);
		textoPersonalTotal = new JTextField("");
		fixedSize(textoPersonalTotal, 100, 30);
		textoPersonalTotal.setEditable(false);
		textoPersonalTotal.setHorizontalAlignment(JTextField.RIGHT);
		textoOficinaTotal = new JTextField("");
		fixedSize(textoOficinaTotal, 100, 30);
		textoOficinaTotal.setEditable(false);
		textoOficinaTotal.setHorizontalAlignment(JTextField.RIGHT);
		textoTotalProyecto = new JTextField("");
		fixedSize(textoTotalProyecto, 200, 30);
		textoTotalProyecto.setEditable(false);
		textoTotalProyecto.setHorizontalAlignment(JTextField.RIGHT);
		textoHoras = new JTextField("");
		fixedSize(textoHoras, 50, 30);
		textoHoras.setEditable(false);
		textoHoras.setHorizontalAlignment(JTextField.RIGHT);
		
		fechaChooser1 = new JDateChooser();
		fechaChooser1.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser1, 100, 30);
		fechaChooser2 = new JDateChooser();
		fechaChooser2.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser2, 100, 30);

		textoTrabajo.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     if(textoTrabajo.getText().length()==4)
				    	 inicializarDatos();
				  }
				});
		textoOperarioFin.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				if (textoOperarioFin.getText().length() == 4 && fechaChooser1.getDate() != null
						&& fechaChooser2.getDate() != null && !textoOperarioIni.getText().isEmpty()) {
					vaciarTabla();
					actualizarTabla();
				}
			}
		});
		textoOperarioIni.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     if(textoOperarioIni.getText().length()==4 && fechaChooser1.getDate()!=null && fechaChooser2.getDate()!=null  && !textoOperarioFin.getText().isEmpty()){
				    	vaciarTabla();
				     	actualizarTabla();
				  }
				  }
				});
		
		textoTrabajo.addKeyListener(this);
		textoOperarioIni.addKeyListener(this);
		textoOperarioFin.addKeyListener(this);
		fechaChooser2.getDateEditor().getUiComponent().addKeyListener(this);
		fechaChooser1.getDateEditor().getUiComponent().addKeyListener(this);
		fechaChooser1.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							if(fechaChooser2.getDate()!=null  && !textoOperarioFin.getText().isEmpty() &&  !textoOperarioIni.getText().isEmpty()){
								vaciarTabla();
						     	actualizarTabla();
						     	
					        }
			            }
						
					}
			    });
		fechaChooser2.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							if(fechaChooser2.getDate()!=null && !textoOperarioFin.getText().isEmpty() &&  !textoOperarioIni.getText().isEmpty()){
								vaciarTabla();
						     	actualizarTabla();
					        }
			            }
						
					}
			    });

		modelo = new ModeloTablaConsultas();
		tabla = new JTable(modelo);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setMaximumSize(new Dimension(2500, 500));
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn columna = tabla.getColumn("Horas");
		columna.setMaxWidth(50);
		columna = tabla.getColumn("Fecha");
		columna.setMaxWidth(100);
		columna = tabla.getColumn("Operario");
		columna.setMaxWidth(70);
		columna = tabla.getColumn("Precio");
		columna.setMaxWidth(100);
		columna = tabla.getColumn("Importe");
		columna.setMaxWidth(100);
		columna = tabla.getColumn("Concepto");
		columna.setMaxWidth(75);

		imprimirButton = new JButton("PDF");
		imprimirButton.setMargin(new Insets(2, 28, 2, 28));
		imprimirButton.addActionListener(this);
		cancelarButton = new JButton("Volver");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);

		JPanel panelCentral = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel();
		

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setAlignmentX(RIGHT_ALIGNMENT);
		panel1.add(rotuloTrabajo);
		panel1.add(textoTrabajo);
		panel1.add(textoNombre);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setAlignmentX(RIGHT_ALIGNMENT);
		panel2.add(rotuloOperarioIni);
		panel2.add(textoOperarioIni);
		panel2.add(Box.createRigidArea(new Dimension(25, 30)));
		panel2.add(rotuloFecha1);
		panel2.add(fechaChooser1);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.setAlignmentX(RIGHT_ALIGNMENT);
		panel3.add(rotuloOperarioFin);
		panel3.add(textoOperarioFin);
		panel3.add(Box.createRigidArea(new Dimension(28, 30)));
		panel3.add(rotuloFecha2);
		panel3.add(fechaChooser2);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.setAlignmentX(RIGHT_ALIGNMENT);
		panel4.add(rotuloPersonal);
		panel4.add(Box.createRigidArea(new Dimension(60, 15)));
		panel4.add(rotuloOficina);
		
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.setAlignmentX(RIGHT_ALIGNMENT);
		panel5.add(rotuloCostes);
		panel5.add(textoCostesPersonal);
		panel5.add(textoCostesOficina);
		
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.setAlignmentX(RIGHT_ALIGNMENT);
		panel6.add(rotuloHoras);
		panel6.add(textoHoras);
		panel6.add(Box.createRigidArea(new Dimension(75, 20)));
		panel6.add(rotuloCoef);
		panel6.add(textoCoefPersonal);
		panel6.add(textoCoefOficina);

		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel8.setAlignmentX(RIGHT_ALIGNMENT);
		panel8.add(Box.createRigidArea(new Dimension(50, 15)));
		panel8.add(rotuloTotal);
		panel8.add(textoPersonalTotal);
		panel8.add(textoOficinaTotal);
		
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));
		panel9.setAlignmentX(RIGHT_ALIGNMENT);
		panel9.add(Box.createRigidArea(new Dimension(50, 15)));
		panel9.add(rotuloTotalProyecto);
		panel9.add(textoTotalProyecto);
		
		panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));
		panel10.setAlignmentX(RIGHT_ALIGNMENT);
		panel10.add(Box.createRigidArea(new Dimension(160, 15)));
		panel10.add(imprimirButton);
		panel10.add(Box.createRigidArea(new Dimension(50, 15)));
		panel10.add(cancelarButton);
		
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.add(Box.createRigidArea(new Dimension(25, 25)));
		panelCentral.add(panel1);
		panelCentral.add(panel2);
		panelCentral.add(panel3);
		panelCentral.add(Box.createRigidArea(new Dimension(25, 15)));
		panelCentral.add(scrollPane);
		panelCentral.add(Box.createRigidArea(new Dimension(25, 15)));
		panelCentral.add(panel4);
		panelCentral.add(panel5);
		panelCentral.add(panel6);
		panelCentral.add(panel8);
		panelCentral.add(Box.createRigidArea(new Dimension(50, 15)));
		panelCentral.add(panel9);
		panelCentral.add(Box.createRigidArea(new Dimension(50, 15)));
		panelCentral.add(panel10);
		
		JPanel pNorte = new JPanel();
		JPanel pOeste = new JPanel();

		JLabel rotuloSuperior = new JLabel("CONSULTA DE MOVIMIENTOS", SwingConstants.CENTER);
		Font font = new Font("Calibri", Font.BOLD, 40);
		rotuloSuperior.setFont(font);
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));

		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);
		JLabel imagen=new JLabel();
		imagen.setMaximumSize(new Dimension(80, 74));
		String nombre="Iconos/logoMini.jpg";
		String nombre_im=System.getProperty("user.dir")+"\\"+nombre;
		imagen.setIcon(new ImageIcon(nombre_im));
		pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));
		pOeste.setAlignmentY(Component.LEFT_ALIGNMENT);
		pOeste.add(imagen);
		pOeste.add(Box.createRigidArea(new Dimension(25, 200)));
		add(pOeste, BorderLayout.WEST);
		add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
		add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);
	}

	public String buscarTrabajo(String codigo) {
		String str = "NRO_TRABAJO='" + codigo + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCTRB", str);
			if (rs.first())
				return rs.getString("DENOMINACION");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public void inicializarDatos() {
		String nombre = buscarTrabajo(textoTrabajo.getText());
		if (!nombre.isEmpty()) {
			textoNombre.setText(nombre);
			if(textoOperarioIni.getText().isEmpty())
				textoOperarioIni.setText("0000");
			if(textoOperarioFin.getText().isEmpty())
				textoOperarioFin.setText("9999");
			Date fecha=fechaChooser1.getDate();
			if(fecha==null){
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
				try {
					fecha = d.parse("01/01/1980");
					fechaChooser1.setDate(fecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fecha=fechaChooser2.getDate();
			if(fecha==null){
				fecha = new Date();
				fechaChooser2.setDate(fecha);
			}
			vaciarTabla();
			actualizarTabla();
		} else
			new PanelMensaje("Trabajo introducido no encontrado.", "Error en los datos", "error");
	}
	
	public void activarFoco() {
		textoTrabajo.requestFocus();
		textoTrabajo.selectAll();
	}

	public void actualizarTabla() {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		String str1 = "TRABAJO='" + textoTrabajo.getText() + "' AND OPERARIO>='" + textoOperarioIni.getText()
				+ "' AND OPERARIO<='" + textoOperarioFin.getText() +"' AND FECHA>=? AND FECHA<=?";
		if(comprobarDatos(textoTrabajo.getText(), textoOperarioIni.getText(), textoOperarioFin.getText(), fechaChooser1.getDate(), fechaChooser2.getDate()))
		try {
			ResultSet rs = controlador.setStatementSelect("CTCMOV", str1, fechaChooser1.getDate(),
					fechaChooser2.getDate());
			String operario,nombre,concepto;
			int horas,horasTotales=0;
			float precio,importe,totalPer=0,totalOfi=0;
			Date fecha;
			
			while (rs.next()) {
				concepto= rs.getString("CONCEPTO");
				operario = rs.getString("OPERARIO");
				fecha = rs.getDate("FECHA");
				nombre = rs.getString("DESCRIPCION");
				horas = rs.getInt("HORAS");
				precio = rs.getFloat("PRECIO");
				importe = rs.getFloat("IMPORTE");

				if (concepto == null) {
					totalPer += importe;
					horasTotales += horas;
				} else {
					if (concepto.isEmpty())
					{
						totalPer += importe;
						horasTotales += horas;
					}
					else
						totalOfi += importe;
				}
				
				modelo.addFila(d.format(fecha), operario, concepto, nombre, horas,  precio,
						importe);
			}
			rs = buscarCoef();
			if (rs.first()) {
				float coefOfi = rs.getFloat("OFICINA");
				float coefPer = rs.getFloat("PERSONAL");
				DecimalFormat format = new DecimalFormat("###,###.00");
				textoHoras.setText(Integer.toString(horasTotales));
				textoCostesOficina.setText(format.format(totalOfi));
				textoCostesPersonal.setText(format.format(totalPer));
				textoCoefOficina.setText(format.format(coefOfi));
				textoCoefPersonal.setText(format.format(coefPer));

				textoPersonalTotal.setText(format.format(totalPer * coefPer));
				textoOficinaTotal.setText(format.format(totalOfi * coefOfi));

				textoTotalProyecto.setText(format.format(totalOfi * coefOfi + totalPer * coefPer));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet buscarCoef() throws SQLException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String str = "A�O='" + Integer.toString(calendar.get(Calendar.YEAR)) + "'";
		return controlador.setStatementSelect("CTCCOE", str);
		
	}
	
	public boolean comprobarDatos(String trabajo, String operario1, String operario2, Date fecha1, Date fecha2) {
		if (trabajo.isEmpty() || !isNumeric(trabajo)) {
			new PanelMensaje("C�digo de trabajo no v�lido.", "Error en los datos", "error");
			return false;
		}
		if (operario1.isEmpty() || !isNumeric(operario1)) {
			new PanelMensaje("C�digo de operario inicial no v�lido.", "Error en los datos", "error");
			return false;
		}
		if (operario2.isEmpty() || !isNumeric(operario2)) {
			new PanelMensaje("C�digo de operario final no v�lido.", "Error en los datos", "error");
			return false;
		}
		if (fecha1==null) {
			new PanelMensaje("Fecha inicial no v�lida.", "Error en los datos", "error");
			return false;
		}
		if (fecha2==null) {
			new PanelMensaje("Fecha final no v�lida.", "Error en los datos", "error");
			return false;
		}
		return true;
	}
	
	public boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena.replace(",", "."));
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == imprimirButton) {
			if (modelo.getRowCount() > 0 && comprobarDatos(textoTrabajo.getText(), textoOperarioIni.getText(), textoOperarioFin.getText(), fechaChooser1.getDate(), fechaChooser2.getDate())) {
				PDFConsultaMovimientos pDFConsultaMovimientos = new PDFConsultaMovimientos();
				pDFConsultaMovimientos.createPDF(this);
			}
		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		}

	}

	public void vaciarTabla() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			inicializarDatos();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
		

		
		
		
}

