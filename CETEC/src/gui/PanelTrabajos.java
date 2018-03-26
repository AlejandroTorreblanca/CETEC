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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelTrabajos extends JPanel implements ActionListener {

	private Controlador controlador;
	private VentanaPrincipal window;
	private JTextField textoCodigo;
	private JComboBox<String> comboClave;
	private JTextField textoDeno;
	private JTextArea textoDescrip;
	private JTextField textoCliente;
	private JTextField textoTrabajador;
	private JTextField textoNombre;
	private JTextField textoPresup;
	private JComboBox<String> comboCob;
	private JComboBox<String> comboFra;
	private JTextArea textoObs;
	private JComboBox<String> comboEstatus;
	private JDateChooser fechaChooser1;
	private JButton confirmarButton;
	private JButton cancelarButton;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelTrabajos(VentanaPrincipal w) {

		this.window = w;
		this.controlador = Controlador.getUnicaInstancia();
		JLabel rotuloCodigo = new JLabel("C�digo: ", SwingConstants.CENTER);
		JLabel rotuloClave = new JLabel("Tipo Trabajo: ", SwingConstants.CENTER);
		JLabel rotuloFecha = new JLabel("Fecha de Contrato: ", SwingConstants.CENTER);
		JLabel rotuloDeno = new JLabel("Denominaci�n: ", SwingConstants.CENTER);
		JLabel rotuloDescrip = new JLabel("Descripci�n: ", SwingConstants.CENTER);
		JLabel rotuloCliente = new JLabel("Cliente: ", SwingConstants.CENTER);
		JLabel rotuloTrabajador = new JLabel("Trabajador: ", SwingConstants.CENTER);
		JLabel rotuloPresupuesto = new JLabel("Presupuesto: ", SwingConstants.CENTER);
		JLabel rotuloCob = new JLabel("Cob: ", SwingConstants.CENTER);
		JLabel rotuloFra = new JLabel("Fra: ", SwingConstants.CENTER);
		JLabel rotuloObs = new JLabel("Observaciones: ", SwingConstants.CENTER);
		JLabel rotuloEstatus = new JLabel("Estatus: ", SwingConstants.CENTER);

		DecimalFormat format = new DecimalFormat("0000");
		textoCodigo = new JTextField();
		fixedSize(textoCodigo, 50, 24);
		textoCodigo.setText(format.format(controlador.getIdentificadorTRB()));
		comboClave = new JComboBox<>();
		fixedSize(comboClave, 50, 24);
		comboClave.setSelectedIndex(-1);
		comboClave.setEditable(false);
		comboClave.addItem("I");
		comboClave.addItem("P");
		comboClave.addItem("DO");
		comboClave.addItem("");
		comboClave.setSelectedItem("");
		fechaChooser1 = new JDateChooser();
		fechaChooser1.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser1, 100, 24);
		textoDeno = new JTextField();
		fixedSize(textoDeno, 500, 24);
		textoDescrip = new JTextArea();
		textoDescrip.setBorder(BorderFactory.createLineBorder(Color.gray));
		textoDescrip.setLineWrap(true);
		textoCliente = new JTextField();
		fixedSize(textoCliente, 500, 24);
		textoTrabajador = new JTextField();
		fixedSize(textoTrabajador, 50, 24);
		textoNombre = new JTextField();
		textoNombre.setEditable(false);
		fixedSize(textoNombre, 450, 24);
		comboEstatus = new JComboBox<>();
		fixedSize(comboEstatus, 68, 24);
		comboEstatus.setSelectedIndex(-1);
		comboEstatus.setEditable(false);
		comboEstatus.addItem("Curso");
		comboEstatus.addItem("Fin");
		comboEstatus.addItem("");
		comboEstatus.setSelectedItem("");
		textoPresup = new JTextField();
		fixedSize(textoPresup, 100, 24);
		comboCob = new JComboBox<>();
		comboCob.setSelectedIndex(-1);
		comboCob.setEditable(false);
		comboCob.addItem("S");
		comboCob.addItem("N");
		comboCob.addItem("");
		comboCob.setSelectedItem("");
		fixedSize(comboCob, 50, 24);
		comboFra = new JComboBox<>();
		comboFra.setSelectedIndex(-1);
		comboFra.setEditable(false);
		comboFra.addItem("S");
		comboFra.addItem("N");
		comboFra.addItem("");
		comboFra.setSelectedItem("");
		fixedSize(comboFra, 50, 24);
		textoObs = new JTextArea();
		textoObs.setBorder(BorderFactory.createLineBorder(Color.gray));
		textoObs.setLineWrap(true);

		textoCodigo.getDocument().addDocumentListener(new DocumentListener() {
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
				if (textoCodigo.getText().length() == 4)
					actualizarPlantilla();
			}
		});

		textoCodigo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboClave.requestFocusInWindow();
					comboClave.showPopup();
				}
			}
		});
		
		comboClave.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(fechaChooser1.getDate()==null)
						fechaChooser1.setDate(new Date());
					fechaChooser1.requestFocusInWindow();
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
					textoDeno.requestFocus();
					textoDeno.selectAll();
				}
			}
		});

		textoDeno.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoDescrip.requestFocus();
					textoDescrip.selectAll();
				}
			}
		});

		textoDescrip.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoCliente.requestFocus();
					textoCliente.selectAll();
				}
			}
		});

		

		textoCliente.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoTrabajador.requestFocus();
					textoTrabajador.selectAll();
				}
			}
		});

		textoTrabajador.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboEstatus.requestFocusInWindow();
					comboEstatus.showPopup();
				}
			}
		});
		
		comboEstatus.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoPresup.requestFocus();
					textoPresup.selectAll();
				}
			}
		});

		textoPresup.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboCob.requestFocusInWindow();
					comboCob.showPopup();
				}
			}
		});
		
		comboCob.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboFra.requestFocusInWindow();
					comboFra.showPopup();
				}
			}
		});
		
		comboFra.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoObs.requestFocus();
					textoObs.selectAll();
				}
			}
		});

		textoObs.addKeyListener(new KeyListener() {

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
				}
			}
		});

		textoTrabajador.getDocument().addDocumentListener(new DocumentListener() {
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
				if (textoTrabajador.getText().length() == 4) {
					if (isNumeric(textoTrabajador.getText())) {
						String operario = buscarTrabajador(textoTrabajador.getText());
						if (operario.isEmpty())
							new PanelMensaje("El operario seleccionado no est� registrado.", "Error en los datos",
									"error");
						else
							textoNombre.setText(operario);
					} else
						new PanelMensaje("El c�digo del operario debe ser num�rico.", "Error en los datos", "error");

				}
			}
		});

		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 28, 2, 28));
		confirmarButton.addActionListener(this);
		cancelarButton = new JButton("Volver");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);

		JScrollPane scrollDesc = new JScrollPane(textoDescrip);
		JScrollPane scrollObs = new JScrollPane(textoObs);
		scrollObs.setMaximumSize(new Dimension(500, 500));
		scrollDesc.setMaximumSize(new Dimension(500, 500));

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
		panel1.add(rotuloCodigo);
		panel1.add(Box.createRigidArea(new Dimension(62, 15)));
		panel1.add(textoCodigo);
		panel1.add(Box.createRigidArea(new Dimension(15, 15)));
		panel1.add(rotuloClave);
		panel1.add(Box.createRigidArea(new Dimension(15, 15)));
		panel1.add(comboClave);
		panel1.add(Box.createRigidArea(new Dimension(62, 15)));
		panel1.add(rotuloFecha);
		panel1.add(Box.createRigidArea(new Dimension(15, 15)));
		panel1.add(fechaChooser1);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(rotuloDeno);
		panel2.add(Box.createRigidArea(new Dimension(20, 15)));
		panel2.add(textoDeno);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(rotuloDescrip);
		panel3.add(Box.createRigidArea(new Dimension(33, 15)));
		panel3.add(scrollDesc);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(rotuloCliente);
		panel4.add(Box.createRigidArea(new Dimension(62, 15)));
		panel4.add(textoCliente);

		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(rotuloTrabajador);
		panel5.add(Box.createRigidArea(new Dimension(39, 15)));
		panel5.add(textoTrabajador);
		panel5.add(textoNombre);

		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.add(rotuloPresupuesto);
		panel6.add(Box.createRigidArea(new Dimension(28, 15)));
		panel6.add(textoPresup);
		panel6.add(Box.createRigidArea(new Dimension(50, 15)));
		panel6.add(rotuloCob);
		panel6.add(Box.createRigidArea(new Dimension(11, 15)));
		panel6.add(comboCob);
		panel6.add(Box.createRigidArea(new Dimension(50, 15)));
		panel6.add(rotuloFra);
		panel6.add(Box.createRigidArea(new Dimension(11, 15)));
		panel6.add(comboFra);

		panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));
		panel10.add(rotuloEstatus);
		panel10.add(Box.createRigidArea(new Dimension(58, 15)));
		panel10.add(comboEstatus);

		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel8.add(rotuloObs);
		panel8.add(Box.createRigidArea(new Dimension(15, 15)));
		panel8.add(scrollObs);

		panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));
		panel9.add(confirmarButton);
		panel9.add(Box.createRigidArea(new Dimension(50, 15)));
		panel9.add(cancelarButton);

		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		panel2.setAlignmentX(LEFT_ALIGNMENT);
		panel3.setAlignmentX(LEFT_ALIGNMENT);
		panel4.setAlignmentX(LEFT_ALIGNMENT);
		panel5.setAlignmentX(LEFT_ALIGNMENT);
		panel6.setAlignmentX(LEFT_ALIGNMENT);
		panel8.setAlignmentX(LEFT_ALIGNMENT);
		panel10.setAlignmentX(LEFT_ALIGNMENT);

		panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(Box.createRigidArea(new Dimension(50, 50)));
		panelCentral.add(panel1);
		panelCentral.add(panel2);
		panelCentral.add(panel3);
		panelCentral.add(panel4);
		panelCentral.add(panel5);
		panelCentral.add(panel10);
		panelCentral.add(panel6);
		panelCentral.add(panel8);

		JPanel pCentral = new JPanel();
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		JPanel pSur = new JPanel();
		JPanel pOeste = new JPanel();

		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentral.add(panelCentral);
		pCentral.add(Box.createRigidArea(new Dimension(25, 25)));
		pCentral.add(panel9);

		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE TRABAJOS", SwingConstants.CENTER);
		Font font = new Font("Calibri", Font.BOLD, 40);
		rotuloSuperior.setFont(font);
		pNorte.setMaximumSize(new Dimension(700, 100));
		pNorte.setAlignmentX(Component.CENTER_ALIGNMENT);
		pNorte.add(rotuloSuperior);
		pNorte.setBorder(BorderFactory.createLineBorder(Color.black));

		setLayout(new BorderLayout(10, 10));
		add(pNorte, BorderLayout.NORTH);

		JLabel imagen = new JLabel();
		imagen.setMaximumSize(new Dimension(80, 74));
		String nombre = "Iconos/logoMini.jpg";
		String nombre_im = System.getProperty("user.dir") + "\\" + nombre;
		imagen.setIcon(new ImageIcon(nombre_im));
		pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));
		pOeste.setAlignmentY(Component.LEFT_ALIGNMENT);
		pOeste.add(imagen);
		pOeste.add(Box.createRigidArea(new Dimension(25, 200)));
		add(pOeste, BorderLayout.WEST);

		add(pCentral, BorderLayout.CENTER);

		pEste.add(Box.createRigidArea(new Dimension(100, 100)));
		add(pEste, BorderLayout.EAST);

		pSur.add(Box.createRigidArea(new Dimension(10, 10)));
		add(pSur, BorderLayout.SOUTH);
	}

	public boolean comprobarDatos(String trabajo) {
		if (trabajo.isEmpty() || !isNumeric(trabajo)) {
			new PanelMensaje("C�digo de trabajo no v�lido.", "Error en los datos", "error");
			return false;
		}
		if (trabajo.length() != 4) {
			new PanelMensaje("El c�digo de operario debe ser de cuatro caracteres.", "Error en los datos", "error");
			return false;
		}
		return true;
	}

	public boolean guardarCambios() {
		String str = "NRO_TRABAJO='" + textoCodigo.getText() + "'";
		if (comprobarDatos(textoCodigo.getText()))
			try {
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
				ResultSet rs = controlador.setStatementSelect("CTCTRB", str);
				if (rs.first()) { // Update
					String str1 = "CLAVE_TRABAJO='" + comboClave.getSelectedItem() + "',FECHA_CONTRATO=?,DENOMINACION='"
							+ textoDeno.getText() + "',DESCRIPCION='" + textoDescrip.getText() + "',CLIENTE='"
							+ textoCliente.getText() + "',TRABAJADOR='" + textoTrabajador.getText() + "',PRESUPUESTO='"
							+ textoPresup.getText().replace(",", ".") + "',FACTURADO='" + comboFra.getSelectedItem()
							+ "',COBRADO='" + comboCob.getSelectedItem() + "',OBSERVACIONES='" + textoObs.getText()
							+ "',ESTATUS='" + comboEstatus.getSelectedItem() + "'";
					String str2 = "NRO_TRABAJO='" + textoCodigo.getText() + "'";
					if (fechaChooser1.getDate() != null)
						try {
							String fecha = d.format(fechaChooser1.getDate());
							if (validarFecha(fecha)) {
								System.out.println(fecha);
								controlador.setStatementUpdate("CTCTRB", str1, str2, d.parse(fecha));
								new PanelMensaje("Cambios guardados con �xito.", "Confirmaci�n de cambios", "info");
								return true;
							} else {
								new PanelMensaje("La fecha introducida no es v�lida ", "Error en los datos", "error");
								return false;
							}
						} catch (ParseException e) {
							new PanelMensaje("Error.", "Error de guardado", "error");
							e.printStackTrace();
							return false;
						}
					else {
						new PanelMensaje("La fecha introducida no es v�lida ", "Error en los datos", "error");
						return false;
					}
				} else { // Insert
					String str1 = "(NRO_TRABAJO,CLAVE_TRABAJO,FECHA_CONTRATO,DENOMINACION,DESCRIPCION,CLIENTE,"
							+ "TRABAJADOR,PRESUPUESTO,FACTURADO,COBRADO,OBSERVACIONES,ESTATUS)";
					String str2 = "('" + textoCodigo.getText() + "','" + comboClave.getSelectedItem() + "', ? ,'"
							+ textoDeno.getText() + "','" + textoDescrip.getText() + "','" + textoCliente.getText()
							+ "','" + textoTrabajador.getText() + "','" + textoPresup.getText().replace(",", ".")
							+ "','" + comboFra.getSelectedItem() + "','" + comboCob.getSelectedItem() + "','"
							+ textoObs.getText() + "','" + comboEstatus.getSelectedItem() + "')";
					if (fechaChooser1.getDate() != null)
						try {
							String fecha = d.format(fechaChooser1.getDate());
							if (validarFecha(fecha)) {
								controlador.setStatementInsert("CTCTRB", str1, str2, d.parse(fecha));
								new PanelMensaje("Cambios guardados con �xito.", "Confirmaci�n de cambios", "info");
								return true;
							} else {
								new PanelMensaje("La fecha introducida no es v�lida ", "Error en los datos", "error");
								return false;
							}
						} catch (ParseException e) {
							new PanelMensaje("Error al guardar los datos.", "Error de guardado", "error");
							e.printStackTrace();
							return false;
						}
					else {
						new PanelMensaje("La fecha introducida no es v�lida ", "Error en los datos", "error");
						return false;
					}

				}
			} catch (SQLException e) {
				new PanelMensaje("Error al guardar los datos.", "Error", "error");
				e.printStackTrace();
				return false;
			}
		return true;
	}

	public void actualizarPlantilla() {
		if (isNumeric(textoCodigo.getText())) {
			String str = "NRO_TRABAJO='" + textoCodigo.getText() + "'";
			try {
				ResultSet rs = controlador.setStatementSelect("CTCTRB", str);
				if (rs.first()) {
					double value2 = Double.parseDouble(rs.getString("PRESUPUESTO"));

					comboClave.setSelectedItem(rs.getString("CLAVE_TRABAJO"));
					textoDeno.setText(rs.getString("DENOMINACION"));
					textoDescrip.setText(rs.getString("DESCRIPCION"));
					textoCliente.setText(rs.getString("CLIENTE"));
					textoTrabajador.setText(rs.getString("TRABAJADOR"));
					textoNombre.setText(buscarTrabajador(rs.getString("TRABAJADOR")));
					textoPresup.setText(String.format("%.2f", value2));
					comboFra.setSelectedItem(rs.getString("FACTURADO"));
					comboCob.setSelectedItem(rs.getString("COBRADO"));
					textoObs.setText(rs.getString("OBSERVACIONES"));
					comboEstatus.setSelectedItem(rs.getString("ESTATUS"));
					fechaChooser1.setDate(rs.getDate("FECHA_CONTRATO"));
				} else {
					comboClave.setSelectedItem("");
					textoDeno.setText("");
					textoDescrip.setText("");
					textoCliente.setText("");
					textoTrabajador.setText("");
					textoNombre.setText("");
					textoPresup.setText("");
					comboFra.setSelectedItem("");
					comboCob.setSelectedItem("");
					textoObs.setText("");
					comboEstatus.setSelectedItem("");
					fechaChooser1.setDate(null);
				}
			} catch (SQLException e) {
				new PanelMensaje("Error al actualizar los datos.", "Error", "error");
				e.printStackTrace();
			}
		} else
			new PanelMensaje("El c�digo del trabajo debe ser num�rico.", "Error en los datos", "error");
	}

	public static boolean validarFecha(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public void activarFoco() {
		textoCodigo.requestFocus();
		textoCodigo.selectAll();
	}

	public String buscarTrabajador(String codigo) {
		String str = "OPERARIO='" + codigo + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCOPE", str);
			if (rs.first())
				return rs.getString("NOMBRE");
		} catch (SQLException e) {
			new PanelMensaje("Error al actualizar los datos.", "Error", "error");
			e.printStackTrace();
		}
		return "";
	}

	public boolean isNumeric(String cadena) {
		try {
			Float.parseFloat(cadena);
			return true;
		} catch (NumberFormatException excepcion) {
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmarButton) {

			String nombre = buscarTrabajador(textoTrabajador.getText());
			if (nombre.isEmpty())
				new PanelMensaje("El operario introducido no ha sido registrado.", "Error en los datos", "error");
			else {
				textoNombre.setText(nombre);
				if(guardarCambios()){
					DecimalFormat format = new DecimalFormat("0000");
					textoCodigo.setText(format.format(controlador.getIdentificadorTRB()));
					textoCodigo.requestFocus();
					textoCodigo.selectAll();
					comboClave.setSelectedItem("");
					comboCob.setSelectedItem("");
					comboEstatus.setSelectedItem("");
					comboFra.setSelectedItem("");
					fechaChooser1.setCalendar(null);
					textoDeno.setText("");
					textoDescrip.setText("");
					textoCliente.setText("");
					textoTrabajador.setText("");
					textoNombre.setText("");
					textoPresup.setText("");
					textoObs.setText("");
				}
			}

		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		}

	}

}
