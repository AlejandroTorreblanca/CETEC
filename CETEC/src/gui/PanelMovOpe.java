package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelMovOpe extends JPanel implements ActionListener {

	private VentanaPrincipal window;
	private Controlador controlador;
	private JButton confirmarButton;
	private JButton cancelarButton;
	private JButton borrarButton;
	private JTextField textoOperario;
	private JDateChooser fechaChooser1;
	private JDateChooser fechaChooser2;
	private JTextField textoNombre;
	private JTextField xMov;
	private JTextField xTraba;
	private JDateChooser xFecha;
	private JTextField xNombre;
	private JTextField xHoras;
	private JTextField xNombreTrabajo;
	private JTable tabla;
	private ModeloTablaMovimientosOpe modelo;
	private JScrollPane scrollPane;
	private int movMax;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelMovOpe(VentanaPrincipal w) {

		this.window = w;
		controlador = Controlador.getUnicaInstancia();
		JLabel rotuloOperario = new JLabel("Operario: ", SwingConstants.CENTER);
		JLabel rotuloFecha1 = new JLabel("Desde Fecha: ", SwingConstants.CENTER);
		JLabel rotuloFecha2 = new JLabel("Hasta Fecha: ", SwingConstants.CENTER);
		textoOperario = new JTextField("");
		fixedSize(textoOperario, 50, 30);
		textoNombre = new JTextField("");
		textoNombre.setEditable(false);
		fixedSize(textoNombre, 400, 30);
		fechaChooser1 = new JDateChooser();
		fechaChooser1.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser1, 100, 30);
		fechaChooser2 = new JDateChooser();
		fechaChooser2.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser2, 100, 30);
		xMov = new JTextField("");
		xMov.setEditable(false);
		fixedSize(xMov, 75, 30);
		xTraba = new JTextField("");
		fixedSize(xTraba, 70, 30);
		xFecha = new JDateChooser();
		xFecha.setDateFormatString("dd/MM/yyyy");
		fixedSize(xFecha, 100, 30);
		xNombre = new JTextField("");
		xNombre.setEditable(false);
		xNombre.setMaximumSize(new Dimension(955, 30));
		xNombre.setMinimumSize(new Dimension(300, 30));
		xHoras = new JTextField("");
		fixedSize(xHoras, 50, 30);
		xNombreTrabajo = new JTextField("");
		xNombreTrabajo.setEditable(false);
		fixedSize(xNombreTrabajo, 400, 30);

		textoOperario.getDocument().addDocumentListener(new DocumentListener() {
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
				if (textoOperario.getText().length() == 4) {
					inicializarDatos();
					if (fechaChooser2.getDate() != null && fechaChooser1.getDate() != null) {
						vaciarTabla();
						actualizarTabla();
					}
				}
			}
		});
		textoOperario.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int n = textoOperario.getSelectionStart() - textoOperario.getSelectionEnd();
				if (textoOperario.getText().length() == 4 && n == 0) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int res = buscarNombres();
					if (res != -1) {
						Date fecha = fechaChooser1.getDate();
						SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
						if (fecha == null) {
							fecha = new Date();
							String fechaaux = d.format(fecha);
							try {
								fechaChooser1.setDate(d.parse(fechaaux));
							} catch (ParseException e1) {
								new PanelMensaje("Error al cargar los datos", "Error", "error");
								e1.printStackTrace();
							}
						}
						fechaChooser1.requestFocusInWindow();
					} else
						new PanelMensaje("Operario introducido no encontrado.", "Error en los datos", "error");
				}
			}
		});

		fechaChooser1.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {
					if (fechaChooser2.getDate() != null && fechaChooser1.getDate() != null) {
						vaciarTabla();
						actualizarTabla();

					}
				}

			}
		});
		fechaChooser2.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {
					if (fechaChooser2.getDate() != null && fechaChooser1.getDate() != null) {
						vaciarTabla();
						actualizarTabla();
					}
				}

			}
		});

		xMov.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					movMax = controlador.getIdentificadorMOV();
					xMov.setText(Integer.toString(movMax));
					xTraba.setText("");
					xHoras.setText("");
					xNombreTrabajo.setText("");
					buscarNombres();
					Date fecha = new Date();
					SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					String fechaaux = d.format(fecha);
					try {
						xFecha.setDate(d.parse(fechaaux));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		xTraba.getDocument().addDocumentListener(new DocumentListener() {
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
				if (xTraba.getText().length() == 4) {
					int res = buscarNombres();
					if (res == -2)
						new PanelMensaje("Trabajo introducido no encontrado.", "Error en los datos", "error");
				}
			}
		});

		fechaChooser1.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					Date fecha = fechaChooser2.getDate();
					if (fecha == null) {
						fecha = new Date();
						String fechaaux = d.format(fecha);
						try {
							fechaChooser2.setDate(d.parse(fechaaux));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					fechaChooser2.requestFocusInWindow();
				}
			}
		});

		fechaChooser2.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Date fecha = fechaChooser2.getDate();
					SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					if (fecha == null) {
						fecha = new Date();
						String fechaaux = d.format(fecha);
						try {
							fechaChooser2.setDate(d.parse(fechaaux));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					buscarNombres();
					vaciarTabla();
					actualizarTabla();
					xTraba.requestFocus();
					xTraba.selectAll();
				}
			}
		});

		xTraba.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int n = xTraba.getSelectionStart() - xTraba.getSelectionEnd();
				if (xTraba.getText().length() == 4 && n == 0) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int res = buscarNombres();
					if (res != -2)
						xFecha.requestFocusInWindow();
					else
						new PanelMensaje("Trabajo introducido no encontrado.", "Error en los datos", "error");
					
				}
			}
		});

		xFecha.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (xHoras.getText().isEmpty()) {
						xHoras.setText("1");
					}
					xHoras.requestFocus();
					xHoras.selectAll();
					
				}
			}
		});

		xHoras.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirmarButton.doClick();
					textoOperario.requestFocus();
					textoOperario.selectAll();
				}
			}
		});

		modelo = new ModeloTablaMovimientosOpe();
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
		columna = tabla.getColumn("Trabajo");
		columna.setMaxWidth(70);
		columna = tabla.getColumn("N�mero");
		columna.setMaxWidth(100);

		ListSelectionModel cellSelectionModel = tabla.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarTexto();
			}
		});

		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 28, 2, 28));
		confirmarButton.addActionListener(this);
		cancelarButton = new JButton("Volver");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);
		borrarButton = new JButton("Limpiar");
		borrarButton.setMargin(new Insets(2, 28, 2, 28));
		borrarButton.addActionListener(this);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setAlignmentX(RIGHT_ALIGNMENT);
		panel1.add(rotuloOperario);
		panel1.add(textoOperario);
		panel1.add(textoNombre);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setAlignmentX(RIGHT_ALIGNMENT);
		panel2.add(rotuloFecha1);
		panel2.add(fechaChooser1);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.setAlignmentX(RIGHT_ALIGNMENT);
		panel3.add(rotuloFecha2);
		panel3.add(fechaChooser2);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.setAlignmentX(RIGHT_ALIGNMENT);
		panel4.add(xMov);
		panel4.add(xTraba);
		panel4.add(xFecha);
		panel4.add(xNombre);
		panel4.add(xHoras);

		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.setAlignmentX(RIGHT_ALIGNMENT);
		panel5.add(xNombreTrabajo);

		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.setAlignmentX(RIGHT_ALIGNMENT);
		panel6.add(Box.createRigidArea(new Dimension(160, 15)));
		panel6.add(borrarButton);
		panel6.add(Box.createRigidArea(new Dimension(50, 15)));
		panel6.add(confirmarButton);
		panel6.add(Box.createRigidArea(new Dimension(50, 15)));
		panel6.add(cancelarButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(50, 50)));
		add(panel1);
		add(panel2);
		add(panel3);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(panel4);
		add(panel5);
		add(Box.createRigidArea(new Dimension(50, 15)));
		add(panel6);

	}

	public String buscarTrabajador(String codigo) {
		String str = "OPERARIO='" + codigo + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCOPE", str);
			if (rs.first())
				return rs.getString("NOMBRE");
		} catch (SQLException e) {
			new PanelMensaje("Error en el acceso a la base de datos.\n"+e, "Error en los datos", "error");
			e.printStackTrace();
		}
		return "";
	}

	public String buscarTrabajo(String codigo) {
		String str = "NRO_TRABAJO='" + codigo + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCTRB", str);
			if (rs.first())
				return rs.getString("DENOMINACION");
		} catch (SQLException e) {
			new PanelMensaje("Error en el acceso a la base de datos.\n"+e, "Error en los datos", "error");
			e.printStackTrace();
		}
		return "";
	}

	public void actualizarTexto() {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			String numero = modelo.getNumeroSeleccionado(filaSeleccionada);
			String trabajo = modelo.getTrabajoSeleccionado(filaSeleccionada);
			String fecha = modelo.getFechaSeleccionado(filaSeleccionada);
			String nombre = modelo.getNombreSeleccionado(filaSeleccionada);
			int horas = modelo.getHorasSeleccionado(filaSeleccionada);
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			xMov.setText(numero);
			xTraba.setText(trabajo);
			xNombreTrabajo.setText(buscarTrabajo(trabajo));
			try {
				xFecha.setDate(d.parse(fecha));
			} catch (ParseException e) {
				new PanelMensaje("Error en el acceso a la base de datos.\n"+e, "Error en los datos", "error");
				e.printStackTrace();
			}
			xNombre.setText(nombre);
			xHoras.setText(Integer.toString(horas));
		}
	}

	public int buscarNombres() {
		String nombre = buscarTrabajador(textoOperario.getText());
		if (nombre.isEmpty()) {
			return -1;
		} else {
			xNombre.setText(nombre);
			textoNombre.setText(nombre);
		}

		if (!xTraba.getText().isEmpty()) {
			String trabajo = buscarTrabajo(xTraba.getText());
			if (trabajo.isEmpty()) {
				return -2;
			} else
				xNombreTrabajo.setText(trabajo);
		}
		else return -2;

		return 0;
	}

	public void inicializarDatos() {
		int res = buscarNombres();
		if (res != -1) {
			movMax = controlador.getIdentificadorMOV();
			xMov.setText(Integer.toString(movMax));
			Date fecha = new Date();
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			String fechaaux = d.format(fecha);
			try {
				xFecha.setDate(d.parse(fechaaux));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else
			new PanelMensaje("Operario introducido no encontrado.", "Error en los datos", "error");

	}

	public void actualizarTabla() {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		String str1 = "OPERARIO='" + textoOperario.getText() + "' AND FECHA>=? AND FECHA<=?";
		if (!(fechaChooser1.getDate() == null) && !(fechaChooser2.getDate() == null))
			try {
				ResultSet rs = controlador.setStatementSelect("CTCMOV", str1, fechaChooser1.getDate(),
						fechaChooser2.getDate());
				String operario, nombre, concepto;
				int horas, codigo;
				Date fecha;
				while (rs.next()) {
					codigo = rs.getInt("MOVIMIENTO");
					operario = rs.getString("TRABAJO");
					fecha = rs.getDate("FECHA");
					nombre = rs.getString("DESCRIPCION");
					horas = rs.getInt("HORAS");
					concepto = rs.getString("CONCEPTO");
					if ((concepto == null))
						modelo.addFila(Integer.toString(codigo), operario, d.format(fecha), nombre, horas);
					else if (concepto.isEmpty())
						modelo.addFila(Integer.toString(codigo), operario, d.format(fecha), nombre, horas);
				}

			} catch (SQLException e) {
				new PanelMensaje("Error en el acceso a la base de datos.\n"+e, "Error en los datos", "error");
				e.printStackTrace();
			}
	}

	public void activarFoco() {
		textoOperario.requestFocus();
		textoOperario.selectAll();
	}

	public boolean guardarCambios() {
		// Guardamos en operarios
		String str = "MOVIMIENTO='" + xMov.getText() + "'";

		if (!xMov.getText().isEmpty())
		{
			try {
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
				ResultSet rs = controlador.setStatementSelect("CTCMOV", str);
				float precio = buscarPrecio(textoOperario.getText());

				if (rs.first()) { // Update
					if (precio != -1) {
						float importe = Integer.parseInt(xHoras.getText()) * precio;
						String str1 = "TRABAJO='" + xTraba.getText() + "',FECHA=? ,OPERARIO='" + textoOperario.getText()
								+ "',HORAS='" + xHoras.getText() + "',DESCRIPCION='"
								+ buscarTrabajador(textoOperario.getText()) + "',PRECIO='" + Float.toString(precio)
								+ "',IMPORTE='" + Float.toString(importe) + "'";
						if (xFecha.getDate() != null)
							try {
								String fecha = d.format(xFecha.getDate());
								controlador.setStatementUpdate("CTCMOV", str1, str, d.parse(fecha));
							} catch (ParseException e) {
								e.printStackTrace();
								return false;
							}
						else {
							new PanelMensaje("La fecha introducida no es v�lida ", "Error en los datos", "error");
							return false;
						}
					} else {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						new PanelMensaje("El usuario introducido no tiene asignado un precio para el a�o "
								+ Integer.toString(calendar.get(Calendar.YEAR)), "Error en los datos", "error");
						return false;
					}
				} else { // Insert
					if (precio != -1) {
						float importe = Integer.parseInt(xHoras.getText()) * precio;
						String str1 = "(MOVIMIENTO,TRABAJO,FECHA,OPERARIO,HORAS,DESCRIPCION,PRECIO,IMPORTE)";
						String str2 = "('" + xMov.getText() + "','" + xTraba.getText() + "',?,'"
								+ textoOperario.getText() + "','" + xHoras.getText() + "','"
								+ buscarTrabajador(textoOperario.getText()) + "','" + Float.toString(precio) + "','"
								+ Float.toString(importe) + "')";
						if (xFecha.getDate() != null)
							try {
								String fecha = d.format(xFecha.getDate());
								controlador.setStatementInsert("CTCMOV", str1, str2, d.parse(fecha));
							} catch (ParseException e) {
								e.printStackTrace();
								return false;
							}
						else {
							new PanelMensaje("La fecha introducida no es v�lida ", "Error en los datos", "error");
							return false;
						}

					} else {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						new PanelMensaje("El usuario introducido no tiene asignado un precio para el a�o "
								+ Integer.toString(calendar.get(Calendar.YEAR)), "Error en los datos", "error");
						return false;
					}
				}

			} catch (SQLException e) {
				new PanelMensaje("Error en el acceso a la base de datos.\n"+e, "Error en los datos", "error");
				e.printStackTrace();
				return false;
			}
		}
		else{
			new PanelMensaje("Identificador de movimiento no v�lido.", "Error en los datos", "error");
			return false;
		}
		return true;
	}

	public boolean comprobarDatos(String operario, String trabajo, Date fecha, String horas) {
		if (buscarTrabajador(operario).isEmpty()) {
			new PanelMensaje("Operario introducido no encontrado.", "Error en los datos", "error");
			return false;
		}
		if (buscarTrabajo(trabajo).isEmpty()) {
			new PanelMensaje("Trabajo introducido no encontrado.", "Error en los datos", "error");
			return false;
		}
		if (fecha == null) {
			new PanelMensaje("Fecha introducida no v�lida.", "Error en los datos", "error");
			return false;
		}
		if (horas.isEmpty() || !isNumeric(horas)) {
			new PanelMensaje("Horas introducidas no v�lidas.", "Error en los datos", "error");
			return false;
		}
		return true;
	}

	public boolean isNumeric(String cadena) {
		try {
			Float.parseFloat(cadena);
			return true;
		} catch (NumberFormatException excepcion) {
			return false;
		}
	}

	public float buscarPrecio(String operario) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String str = "A�O='" + Integer.toString(calendar.get(Calendar.YEAR)) + "' AND OPERARIO='" + operario + "'";

		try {
			ResultSet rs = controlador.setStatementSelect("CTCPRE", str);
			if (rs.first())
				return rs.getFloat("PRECIO");
			else
				return -1;
		} catch (SQLException e) {
			new PanelMensaje("Error en el acceso a la base de datos.\n"+e, "Error en los datos", "error");
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmarButton) {
			if (comprobarDatos(textoOperario.getText(), xTraba.getText(), xFecha.getDate(), xHoras.getText())) {
				if (guardarCambios())
				{
					if ((!(fechaChooser1.getDate() == null) && !(fechaChooser2.getDate() == null))) {
						if (modelo.getRowCount() > 0) {
							vaciarTabla();
							actualizarTabla();
						} else {
							SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
							vaciarTabla();
							modelo.addFila(xMov.getText(), xTraba.getText(), d.format(xFecha.getDate()),
									textoNombre.getText(), Integer.parseInt(xHoras.getText()));
						}
						xMov.setText(Integer.toString(controlador.getIdentificadorMOV()));
						xTraba.setText("");
						xNombreTrabajo.setText("");
						xHoras.setText("");
						Date fecha = new Date();
						SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
						String fechaaux = d.format(fecha);
						try {
							xFecha.setDate(d.parse(fechaaux));
						} catch (ParseException e2) {
							new PanelMensaje("Error en el acceso a la base de datos.\n"+e2, "Error en los datos", "error");
							e2.printStackTrace();
						}
					}
				}
				else
					new PanelMensaje("Error al guardar los datos", "Error", "error");
			}
		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		} else if (e.getSource() == borrarButton) {
			vaciarTabla();
			textoNombre.setText("");
			textoOperario.setText("");
			textoOperario.requestFocus();
			fechaChooser1.setDate(null);
			fechaChooser2.setDate(null);
			xFecha.setDate(null);
			xMov.setText("");
			xHoras.setText("");
			xNombre.setText("");
			xTraba.setText("");
			xNombreTrabajo.setText("");
		}

	}

	public void vaciarTabla() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}

}
