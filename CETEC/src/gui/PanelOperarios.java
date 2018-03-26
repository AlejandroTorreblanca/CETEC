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
import java.util.Calendar;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelOperarios extends JPanel implements ActionListener {

	private Controlador controlador;
	private VentanaPrincipal window;
	private JTextField textoCodigo;
	private JTextField textoDirec;
	private JTextField textoPobla1;
	private JTextField textoPobla2;
	private JTextField textoProvi;
	private JTextField textoDni;
	private JTextField textoNombre;
	private JTextField textoTele1;
	private JTextField textoTele2;
	private JTextField textoA�o;
	private JTextField textoPrecio;
	private JTable tabla;
	private ModeloTablaOperario modelo;
	private JScrollPane scrollPane;
	private JButton confirmarButton;
	private JButton cancelarButton;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelOperarios(VentanaPrincipal w) {

		controlador = Controlador.getUnicaInstancia();
		this.window = w;
		JLabel rotuloCodigo = new JLabel("C�digo: ", SwingConstants.CENTER);
		JLabel rotuloDirec = new JLabel("Direcci�n: ", SwingConstants.CENTER);
		JLabel rotuloPobla = new JLabel("Poblaci�n: ", SwingConstants.CENTER);
		JLabel rotuloProv = new JLabel("Provincia: ", SwingConstants.CENTER);
		JLabel rotuloDni = new JLabel("DNI/CIF: ", SwingConstants.CENTER);
		JLabel rotuloTele = new JLabel("Tel�fonos: ", SwingConstants.CENTER);
		JLabel rotuloA�o = new JLabel("A�o de proceso: ", SwingConstants.CENTER);

		DecimalFormat format = new DecimalFormat("0000");
		textoCodigo = new JTextField();
		fixedSize(textoCodigo, 50, 24);
		textoCodigo.setText(format.format(controlador.getIdentificadorOPE()));
		textoNombre = new JTextField();
		fixedSize(textoNombre, 445, 24);
		textoDirec = new JTextField();
		fixedSize(textoDirec, 500, 24);
		textoPobla1 = new JTextField();
		fixedSize(textoPobla1, 50, 24);
		textoPobla2 = new JTextField();
		fixedSize(textoPobla2, 445, 24);
		textoProvi = new JTextField();
		fixedSize(textoProvi, 100, 24);
		textoDni = new JTextField();
		fixedSize(textoDni, 100, 24);
		textoTele1 = new JTextField();
		fixedSize(textoTele1, 100, 24);
		textoTele2 = new JTextField();
		fixedSize(textoTele2, 100, 24);
		textoA�o = new JTextField();
		textoA�o.setEditable(false);
		fixedSize(textoA�o, 50, 24);
		textoPrecio = new JTextField();
		fixedSize(textoPrecio, 95, 24);

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
					textoNombre.requestFocus();
					textoNombre.selectAll();
				}
			}
		});
		
		textoNombre.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoDirec.requestFocus();
					textoDirec.selectAll();
				}
			}
		});

		textoDirec.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoPobla1.requestFocus();
					textoPobla1.selectAll();
				}
			}
		});

		textoPobla1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoPobla2.requestFocus();
					textoPobla2.selectAll();
				}
			}
		});

		textoPobla2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoProvi.requestFocus();
					textoProvi.selectAll();
				}
			}
		});

		textoProvi.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoDni.requestFocus();
					textoDni.selectAll();
				}
			}
		});
		
		textoDni.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoTele1.requestFocus();
					textoTele1.selectAll();
				}
			}
		});

		textoTele1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textoTele2.requestFocus();
					textoTele2.selectAll();
				}
			}
		});
		
		textoTele2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int n=modelo.getRowCount()-1;
					tabla.setRowSelectionInterval(n, n);
					textoPrecio.requestFocus();
				}
			}
		});

		textoPrecio.addKeyListener(new KeyListener() {

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

		confirmarButton = new JButton("Confirmar");
		confirmarButton.setMargin(new Insets(2, 28, 2, 28));
		confirmarButton.addActionListener(this);
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
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(rotuloCodigo);
		panel1.add(Box.createRigidArea(new Dimension(32, 15)));
		panel1.add(textoCodigo);
		panel1.add(Box.createRigidArea(new Dimension(5, 15)));
		panel1.add(textoNombre);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(rotuloDirec);
		panel2.add(Box.createRigidArea(new Dimension(17, 15)));
		panel2.add(textoDirec);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(rotuloPobla);
		panel3.add(Box.createRigidArea(new Dimension(15, 15)));
		panel3.add(textoPobla1);
		panel3.add(Box.createRigidArea(new Dimension(5, 15)));
		panel3.add(textoPobla2);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(rotuloProv);
		panel4.add(Box.createRigidArea(new Dimension(18, 15)));
		panel4.add(textoProvi);
		panel4.add(Box.createRigidArea(new Dimension(50, 15)));
		panel4.add(rotuloDni);
		panel4.add(Box.createRigidArea(new Dimension(10, 15)));
		panel4.add(textoDni);

		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(rotuloTele);
		panel5.add(Box.createRigidArea(new Dimension(15, 15)));
		panel5.add(textoTele1);
		panel5.add(Box.createRigidArea(new Dimension(5, 15)));
		panel5.add(textoTele2);

		modelo = new ModeloTablaOperario();
		tabla = new JTable(modelo);
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(100, 50));
		scrollPane.setMaximumSize(new Dimension(150, 300));
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centrar_datos();
		TableColumn columna = tabla.getColumn("A�o");
		columna.setMaxWidth(50);

		ListSelectionModel cellSelectionModel = tabla.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarTexto();
			}
		});
		inicializarTabla();

		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.add(Box.createRigidArea(new Dimension(110, 15)));
		panel6.add(scrollPane);

		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
		panel7.add(rotuloA�o);
		panel7.add(Box.createRigidArea(new Dimension(15, 15)));
		panel7.add(textoA�o);
		panel7.add(Box.createRigidArea(new Dimension(5, 15)));
		panel7.add(textoPrecio);

		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel8.add(confirmarButton);
		panel8.add(Box.createRigidArea(new Dimension(50, 15)));
		panel8.add(cancelarButton);

		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		panel2.setAlignmentX(LEFT_ALIGNMENT);
		panel3.setAlignmentX(LEFT_ALIGNMENT);
		panel4.setAlignmentX(LEFT_ALIGNMENT);
		panel5.setAlignmentX(LEFT_ALIGNMENT);
		panel6.setAlignmentX(LEFT_ALIGNMENT);
		panel7.setAlignmentX(LEFT_ALIGNMENT);

		panelCentral.add(Box.createRigidArea(new Dimension(50, 50)));
		panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(panel1);
		panelCentral.add(panel2);
		panelCentral.add(panel3);
		panelCentral.add(panel4);
		panelCentral.add(panel5);
		panelCentral.add(panel6);
		panelCentral.add(panel7);

		JPanel pCentral = new JPanel();
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		JPanel pSur = new JPanel();
		JPanel pOeste = new JPanel();

		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentral.setAlignmentY(Component.CENTER_ALIGNMENT);
		pCentral.add(panelCentral);
		pCentral.add(Box.createRigidArea(new Dimension(50, 50)));
		pCentral.add(panel8);

		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE OPERARIOS", SwingConstants.CENTER);
		Font font = new Font("Calibri", Font.BOLD, 40);
		rotuloSuperior.setFont(font);
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

		pSur.add(Box.createRigidArea(new Dimension(50, 50)));
		add(pSur, BorderLayout.SOUTH);

	}

	public void actualizarPlantilla() {
		String str = "OPERARIO='" + textoCodigo.getText() + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCOPE", str);
			if (rs.first()) {
				textoNombre.setText(rs.getString("NOMBRE"));
				textoDirec.setText(rs.getString("DIRECCION"));
				textoPobla1.setText(rs.getString("COD_POSTAL"));
				textoPobla2.setText(rs.getString("POBLACION"));
				textoProvi.setText(rs.getString("PROVINCIA"));
				textoDni.setText(rs.getString("DNI/CIF"));
				textoTele1.setText(rs.getString("TELEFONO_1"));
				textoTele2.setText(rs.getString("TELEFONO_2"));
				vaciarTabla();
				inicializarTabla();
				textoA�o.setText("");
				textoPrecio.setText("");
			} else {
				inicializarPlantilla();
			}
		} catch (SQLException e) {
			new PanelMensaje("Error al actualizar los datos.", "Error", "error");
			e.printStackTrace();
		}
	}

	public void inicializarPlantilla() {
		textoNombre.setText("");
		textoDirec.setText("");
		textoPobla1.setText("");
		textoPobla2.setText("");
		textoProvi.setText("");
		textoDni.setText("");
		textoTele1.setText("");
		textoTele2.setText("");
		textoA�o.setText("");
		textoPrecio.setText("");
		vaciarTabla();
		inicializarTabla();
	}

	public void inicializarTabla() {
		String str = "OPERARIO='" + textoCodigo.getText() + "'";

		try {
			ResultSet rs = controlador.setStatementSelect("CTCPRE", str);
			int a�o = 0;
			while (rs.next()) {
				a�o = rs.getInt("A�O");
				double value1 = Double.parseDouble(rs.getString("PRECIO"));
				modelo.addFila(a�o, String.format("%.2f", value1));
			}
			if (a�o != 0)
				modelo.addFila(a�o + 1, "");
			else {
				Calendar ahoraCal = Calendar.getInstance();
				modelo.addFila(ahoraCal.get(Calendar.YEAR), "");
			}
		} catch (SQLException e) {
			new PanelMensaje("Error al actualizar los datos.", "Error", "error");
			e.printStackTrace();
		}
	}
	
	public void activarFoco() {
		textoCodigo.requestFocus();
		textoCodigo.selectAll();
	}

	public void actualizarTexto() {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			int a�o = modelo.getA�oSeleccionado(filaSeleccionada);
			String precio = modelo.getPrecioSeleccionado(filaSeleccionada);
			textoA�o.setText(Integer.toString(a�o));
			textoPrecio.setText(precio);
		}
	}

	public void vaciarTabla() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}

	public void centrar_datos() {
		for (int col = 0; col < 2; col++) {
			DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
			modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
			tabla.getColumnModel().getColumn(col).setCellRenderer(modelocentrar);
		}
	}

	public boolean comprobarDatos(String operario, String dni) {
		if (operario.isEmpty() || !isNumeric(operario)) {
			new PanelMensaje("C�digo de operario no v�lido.", "Error en los datos", "error");
			return false;
		}
		if(operario.length()!=4){
			new PanelMensaje("El c�digo de operario debe ser de cuatro caracteres.", "Error en los datos", "error");
			return false;
		}
		if (dni.isEmpty()) {
			new PanelMensaje("El operario debe tener DNI.", "Error en los datos", "error");
			return false;
		}
		return true;
	}

	public boolean guardarCambios() {
		// Guardamos en operarios
		String str = "OPERARIO='" + textoCodigo.getText() + "'";
		boolean control = true;
		if (comprobarDatos(textoCodigo.getText(), textoDni.getText()))
			try {
				ResultSet rs = controlador.setStatementSelect("CTCOPE", str);
				if (rs.first()) { // Update
					String str1 = "NOMBRE='" + textoNombre.getText() + "',DIRECCION='" + textoDirec.getText()
							+ "',COD_POSTAL='" + textoPobla1.getText() + "',POBLACION='" + textoPobla2.getText()
							+ "',PROVINCIA='" + textoProvi.getText() + "',`DNI/CIF`='" + textoDni.getText()
							+ "',TELEFONO_1='" + textoTele1.getText() + "',TELEFONO_2='" + textoTele2.getText() + "'";
					controlador.setStatementUpdate("CTCOPE", str1, str);
				} else { // Insert
					String str1 = "(OPERARIO,NOMBRE,DIRECCION,COD_POSTAL,POBLACION,PROVINCIA,"
							+ "`DNI/CIF`,TELEFONO_1,TELEFONO_2)";
					String str2 = "('" + textoCodigo.getText() + "','" + textoNombre.getText() + "','"
							+ textoDirec.getText() + "','" + textoPobla1.getText() + "','" + textoPobla2.getText()
							+ "','" + textoProvi.getText() + "','" + textoDni.getText() + "','" + textoTele1.getText()
							+ "','" + textoTele2.getText() + "')";
					controlador.setStatementInsert("CTCOPE", str1, str2);
				}

				// Guardamos en Precios
				if ((!textoPrecio.getText().isEmpty()) && (!textoA�o.getText().isEmpty())) {
					str = "OPERARIO='" + textoCodigo.getText() + "' AND A�O='" + textoA�o.getText() + "'";
					if (isNumeric(textoPrecio.getText()))
						try {
							rs = controlador.setStatementSelect("CTCPRE", str);
							if (rs.first()) { // Update
								String str1 = "PRECIO='" + textoPrecio.getText().replace(",", ".") + "'";
								controlador.setStatementUpdate("CTCPRE", str1, str);
							} else { // Insert
								if (textoA�o.getText().compareTo("") != 0) {
									String str1 = "(OPERARIO,A�O,PRECIO)";
									String str2 = "('" + textoCodigo.getText() + "','" + textoA�o.getText() + "','"
											+ textoPrecio.getText().replace(",", ".") + "')";
									controlador.setStatementInsert("CTCPRE", str1, str2);
								}
							}
						} catch (SQLException e) {
							new PanelMensaje("Error al guardar los cambios.", "Error", "error");
							e.printStackTrace();
							return false;
						}
					else {
						new PanelMensaje("El precio introducido debe ser num�rico.", "Error en los datos", "error");
						control = false;
					}
				}
				if (control) {
					new PanelMensaje("Cambios guardados con �xito.", "Confirmaci�n de cambios", "info");
					return true;
					}
			} catch (SQLException e) {
				new PanelMensaje("Error al guardar los cambios.", "Error", "error");
				e.printStackTrace();
				return false;
			}
		return true;
	}

	public boolean isNumeric(String cadena) {
		try {
			Float.parseFloat(cadena.replace(",", "."));
			return true;
		} catch (NumberFormatException excepcion) {
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmarButton) {
			if (guardarCambios()) {
				inicializarPlantilla();
				DecimalFormat format = new DecimalFormat("0000");
				textoCodigo.setText(format.format(controlador.getIdentificadorOPE()));
				textoCodigo.requestFocus();
				textoCodigo.selectAll();
			}
		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		}

	}

}
