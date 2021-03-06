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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import controlador.Controlador;

@SuppressWarnings("serial")
public class PanelConceptos extends JPanel implements ActionListener, KeyListener {

	private VentanaPrincipal window;
	private Controlador controlador;
	private JTextField textoCD;
	private JTextField textoDescrip;
	private JTextField textoPrecio;
	private JTable tabla;
	private ModeloTablaConceptos modelo;
	private JScrollPane scrollPane;
	private JButton confirmarButton;
	private JButton cancelarButton;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelConceptos(VentanaPrincipal w) {

		this.window = w;
		controlador = Controlador.getUnicaInstancia();

		JLabel rotuloGastoPerso = new JLabel(" 00-19 - Gastos de Personal ", SwingConstants.CENTER);
		JLabel rotuloGastoOficina = new JLabel(" 20-99 - Gastos de Oficina    ", SwingConstants.CENTER);
		Font font1 = new Font("Arial", Font.BOLD, 18);
		rotuloGastoPerso.setFont(font1);
		rotuloGastoOficina.setFont(font1);

		textoCD = new JTextField();
		fixedSize(textoCD, 25, 24);
		textoDescrip = new JTextField();
		fixedSize(textoDescrip, 384, 24);
		textoPrecio = new JTextField();
		fixedSize(textoPrecio, 91, 24);

		textoCD.addKeyListener(this);
		textoDescrip.addKeyListener(this);
		textoPrecio.addKeyListener(this);

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

		modelo = new ModeloTablaConceptos();
		tabla = new JTable(modelo);
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(100, 50));
		scrollPane.setMaximumSize(new Dimension(500, 450));
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn columna = tabla.getColumn("CD");
		columna.setMaxWidth(25);
		columna = tabla.getColumn("Precio");
		columna.setMaxWidth(100);

		ListSelectionModel cellSelectionModel = tabla.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarTexto();
			}
		});

		inicializarTabla();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(rotuloGastoPerso);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(rotuloGastoOficina);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(scrollPane);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(confirmarButton);
		panel4.add(Box.createRigidArea(new Dimension(50, 15)));
		panel4.add(cancelarButton);

		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(textoCD);
		panel5.add(textoDescrip);
		panel5.add(textoPrecio);

		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		panel2.setAlignmentX(LEFT_ALIGNMENT);
		panel3.setAlignmentX(LEFT_ALIGNMENT);
		panel4.setAlignmentX(LEFT_ALIGNMENT);
		panel5.setAlignmentX(LEFT_ALIGNMENT);

		panelCentral.add(Box.createRigidArea(new Dimension(20, 20)));
		panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(panel1);
		panelCentral.add(Box.createRigidArea(new Dimension(5, 5)));
		panelCentral.add(panel2);
		panelCentral.add(Box.createRigidArea(new Dimension(5, 5)));
		panelCentral.add(panel3);
		panelCentral.add(panel5);
		panelCentral.add(Box.createRigidArea(new Dimension(20, 20)));
		panelCentral.add(panel4);

		JPanel pCentral = new JPanel();
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		JPanel pSur = new JPanel();
		JPanel pOeste = new JPanel();

		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCentral.add(panelCentral);

		JLabel rotuloSuperior = new JLabel("MANTENIMIENTO DE CONCEPTOS", SwingConstants.CENTER);
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

	public void inicializarTabla() {
		try {
			ResultSet rs = controlador.setStatementSelect("CTCCNP", "");
			String descrip,concepto;
			while (rs.next()) {
				concepto = rs.getString("CONCEPTO");
				double value1 = Double.parseDouble(rs.getString("PRECIO"));
				descrip = rs.getString("DESCRIPCION");
				modelo.addFila(concepto, descrip, String.format("%.2f", value1));
			}
		} catch (SQLException e) {
			new PanelMensaje("Error al actualizar los datos.", "Error", "error");
			e.printStackTrace();
		}

	}

	public void actualizarTexto() {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			String concepto = modelo.getCDSeleccionado(filaSeleccionada);
			String descrip = modelo.getDescripcionSeleccionada(filaSeleccionada);
			String precio = modelo.getPrecioSeleccionado(filaSeleccionada);
			textoCD.setText(concepto);
			textoDescrip.setText(descrip);
			textoPrecio.setText(precio);
		}

	}

	public boolean comprobarDatos(String concepto, String descrip, String precio) {

		if (concepto.isEmpty() || !isNumeric(concepto)) {
			new PanelMensaje("El c�digo del concepto debe ser num�rico.", "Error en los datos", "error");
			return false;
		}
		if (descrip.isEmpty()) {
			new PanelMensaje("Debe introducir una descripci�n del concepto.", "Error en los datos", "error");
			return false;
		}
		if (precio.isEmpty() || !isNumeric(precio)) {
			new PanelMensaje("El precio del concepto debe ser num�rico.", "Error en los datos", "error");

			return false;
		}
		if(Integer.parseInt(concepto)>99 || Integer.parseInt(concepto)<0){
			new PanelMensaje("El c�digo del concepto debe ser una cifra comprendida entre 00 y 99.", "Error en los datos", "error");

			return false;
		}
		if(concepto.length()!=2){
			new PanelMensaje("El c�digo del concepto debe un n�mero de dos cifras.", "Error en los datos", "error");

			return false;
		}
		return true;
	}

	public void guardarCambios() {
		String concepto = textoCD.getText().replace(",", ".");
		String precio = textoPrecio.getText().replace(",", ".");
		if (comprobarDatos(concepto, textoDescrip.getText(), precio)) {
			String descrip = textoDescrip.getText();
			try {
				if (conceptoDisponible(concepto)) {
					String str = "('" + concepto + "','" + descrip + "','" + precio + "')";
					controlador.setStatementInsert("CTCCNP", "(CONCEPTO,DESCRIPCION,PRECIO)", str);
				} else {
					String str1 = "DESCRIPCION='" + descrip + "',PRECIO='" + precio + "'";
					String str2 = "CONCEPTO='" + concepto + "'";
					controlador.setStatementUpdate("CTCCNP", str1, str2);
					new PanelMensaje("Cambios guardados con �xito.", "Confirmaci�n de cambios", "info");
				}
				vaciarTabla();
				inicializarTabla();
				textoDescrip.setText("");
				textoCD.setText("");
				textoPrecio.setText("");

			} catch (SQLException e1) {
				new PanelMensaje("Error al guardar los cambios.", "Error", "error");
				e1.printStackTrace();
			}
		}
	}

	public boolean isNumeric(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException excepcion) {
			return false;
		}
	}

	public boolean conceptoDisponible(String concepto) {
		String str = "CONCEPTO='" + concepto + "'";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCCNP", str);
			if (rs.first())
				return false;
			else
				return true;
		} catch (SQLException e) {
			new PanelMensaje("Error al actualizar los datos.", "Error", "error");
			e.printStackTrace();
		}
		return false;
	}

	public void vaciarTabla() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmarButton) {
			guardarCambios();
		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			guardarCambios();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
