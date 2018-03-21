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
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;
import utilidades.ExcelConsultaProyectos;
import utilidades.PDFConsultaProyectos;

@SuppressWarnings("serial")
public class PanelConsultaProyectos extends JPanel implements ActionListener, KeyListener {

	private VentanaPrincipal window;
	private Controlador controlador;
	private JButton imprimirButton;
	private JButton excelButton;
	private JButton cancelarButton;
	private JDateChooser fechaChooser1;
	private JDateChooser fechaChooser2;
	private JTable tabla;
	private ModeloTablaProyectos modelo;
	private JScrollPane scrollPane;
	private LinkedList<String> registro;
	private JComboBox<String> comboClave;
	private JComboBox<String> comboEstatus;

	private void fixedSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x, y));
		c.setMaximumSize(new Dimension(x, y));
		c.setPreferredSize(new Dimension(x, y));
	}

	public PanelConsultaProyectos(VentanaPrincipal w) {
		this.window = w;
		registro = new LinkedList<String>();
		controlador=Controlador.getUnicaInstancia();

		JLabel rotuloFecha1 = new JLabel("Desde Fecha: ", SwingConstants.CENTER);
		JLabel rotuloFecha2 = new JLabel("Hasta Fecha: ", SwingConstants.CENTER);
		JLabel rotuloEstatus = new JLabel("Estatus: ", SwingConstants.CENTER);
		JLabel rotuloClave = new JLabel("Tipo Trabajo: ", SwingConstants.CENTER);
		
		comboClave = new  JComboBox<>();
		fixedSize(comboClave, 50, 24);
		comboClave.setSelectedIndex(-1);
		comboClave.setEditable(false);
		comboClave.addItem("I");
		comboClave.addItem("P");
		comboClave.addItem("DO");
		comboClave.addItem("");
		comboClave.setSelectedItem("");
		comboClave.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        if(fechaChooser1.getDate()!=null && fechaChooser2.getDate()!=null){
		        	vaciarTabla();
		        	actualizarTabla();
		        }
		    }
		});
		
		comboEstatus = new JComboBox<>();
		fixedSize(comboEstatus, 65, 24);
		comboEstatus.setSelectedIndex(-1);
		comboEstatus.setEditable(false);
		comboEstatus.addItem("Fin");
		comboEstatus.addItem("Curso");
		comboEstatus.addItem("");
		comboEstatus.setSelectedItem("");
		comboEstatus.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        if(fechaChooser1.getDate()!=null && fechaChooser2.getDate()!=null){
		        	vaciarTabla();
		        	actualizarTabla();
		        }
		    }
		});
		
		fechaChooser1 = new JDateChooser();
		fechaChooser1.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser1, 100, 30);
		fechaChooser2 = new JDateChooser();
		fechaChooser2.setDateFormatString("dd/MM/yyyy");
		fixedSize(fechaChooser2, 100, 30);
		fechaChooser1.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							if(fechaChooser2.getDate()!=null){
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
							if(fechaChooser1.getDate()!=null){
					        	vaciarTabla();
					        	actualizarTabla();
					        }
			            }
						
					}
			    });

		fechaChooser2.getDateEditor().getUiComponent().addKeyListener(this);
		fechaChooser1.getDateEditor().getUiComponent().addKeyListener(this);

		modelo = new ModeloTablaProyectos();
		tabla = new JTable(modelo);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setMaximumSize(new Dimension(2500, 500));
		tabla.setCellSelectionEnabled(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn columna = tabla.getColumn("n");
		columna.setMaxWidth(25);
		columna = tabla.getColumn("C�digo");
		columna.setMaxWidth(50);
		columna = tabla.getColumn("Tipo");
		columna.setMaxWidth(40);
		columna = tabla.getColumn("Estatus");
		columna.setMaxWidth(50);
		columna = tabla.getColumn("Presup. �");
		columna.setMaxWidth(120);
		columna = tabla.getColumn("Costes �");
		columna.setMaxWidth(120);
		columna = tabla.getColumn("Margen �");
		columna.setMaxWidth(120);
		columna = tabla.getColumn("Margen %");
		columna.setMaxWidth(110);

		imprimirButton = new JButton("PDF");
		imprimirButton.setMargin(new Insets(2, 28, 2, 28));
		imprimirButton.addActionListener(this);
		cancelarButton = new JButton("Volver");
		cancelarButton.setMargin(new Insets(2, 28, 2, 28));
		cancelarButton.addActionListener(this);
		excelButton = new JButton("Excel");
		excelButton.setMargin(new Insets(2, 28, 2, 28));
		excelButton.addActionListener(this);

		JPanel panelCentral = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setAlignmentX(RIGHT_ALIGNMENT);
		panel1.add(rotuloClave);
		panel1.add(Box.createRigidArea(new Dimension(5, 20)));
		panel1.add(comboClave);
		panel1.add(Box.createRigidArea(new Dimension(50, 20)));
		panel1.add(rotuloFecha1);
		panel1.add(fechaChooser1);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setAlignmentX(RIGHT_ALIGNMENT);
		panel2.add(rotuloEstatus);
		panel2.add(Box.createRigidArea(new Dimension(16, 20)));
		panel2.add(comboEstatus);
		panel2.add(Box.createRigidArea(new Dimension(53, 20)));
		panel2.add(rotuloFecha2);
		panel2.add(fechaChooser2);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.setAlignmentX(RIGHT_ALIGNMENT);
		
		panel3.add(excelButton);
		panel3.add(Box.createRigidArea(new Dimension(50, 15)));
		panel3.add(imprimirButton);
		panel3.add(Box.createRigidArea(new Dimension(50, 15)));
		panel3.add(cancelarButton);
		panel3.add(Box.createRigidArea(new Dimension(160, 15)));

		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.add(Box.createRigidArea(new Dimension(25, 25)));
		panelCentral.add(panel1);
		panelCentral.add(panel2);
		panelCentral.add(Box.createRigidArea(new Dimension(25, 15)));
		panelCentral.add(scrollPane);
		panelCentral.add(Box.createRigidArea(new Dimension(25, 15)));
		panelCentral.add(panel3);

		JPanel pNorte = new JPanel();
		JPanel pOeste = new JPanel();

		JLabel rotuloSuperior = new JLabel("CONSULTA DE PROYECTOS", SwingConstants.CENTER);
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

	public void inicializarDatos() {
		Date fecha = fechaChooser1.getDate();
		if (fecha == null) {
			fecha = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			calendar.add(Calendar.DAY_OF_YEAR, -365);
			fechaChooser1.setDate(calendar.getTime());
		}
		fecha = fechaChooser2.getDate();
		if (fecha == null) {
			fecha = new Date();
			fechaChooser2.setDate(fecha);
		}
		vaciarTabla();
		actualizarTabla();

	}

	public void actualizarTabla() {
		String str1 = "FECHA>=? AND FECHA<=?";
		try {
			ResultSet rs = controlador.setStatementSelect("CTCMOV", str1, fechaChooser1.getDate(),
					fechaChooser2.getDate());
			String trabajo;
			int n = 1;
			while (rs.next()) {
				trabajo = rs.getString("TRABAJO");
				if (!registro.contains(trabajo)) {
					registro.add(trabajo);
					n=a�adirLinea(trabajo,n);
					
				}
			}
			registro = new LinkedList<>();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int a�adirLinea(String trabajo, int n) {
		String str1 = "TRABAJO='" + trabajo + "'";
		String nombre = "", tipo = "", estatus = "", cliente = "";

		try {
			float importe = 0, presup = 0;
			String str = "NRO_TRABAJO='" + trabajo + "'";
			ResultSet rs = controlador.setStatementSelect("CTCTRB", str);
			if (rs.first()) {
				nombre = rs.getString("DENOMINACION");
				tipo = rs.getString("CLAVE_TRABAJO");
				estatus = rs.getString("ESTATUS");
				presup = Float.parseFloat(rs.getString("PRESUPUESTO"));
				cliente = rs.getString("CLIENTE");
			}
			if (filtrar(tipo, estatus)) {
				rs = controlador.setStatementSelect("CTCMOV", str1);
				while (rs.next()) {
					importe += Float.parseFloat(rs.getString("IMPORTE"));
				}
				
				modelo.addFila(n, trabajo, nombre, tipo, estatus, cliente, presup, importe);
				n++;
			}
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean filtrar(String tipo, String estatus){
		String filtroTipo = (String) comboClave.getSelectedItem();
		String filtroEstatus = (String) comboEstatus.getSelectedItem();
		if (filtroEstatus.isEmpty() && filtroTipo.isEmpty())
			return true;
		else {
			if (!filtroEstatus.isEmpty() && filtroEstatus.compareTo(estatus) != 0)
				return false;
			if (!filtroTipo.isEmpty() && filtroTipo.compareTo(tipo) != 0)
				return false;
		}
		return true;
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
	
	public boolean comprobarDatos( Date fecha1, Date fecha2) {
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == excelButton) {
			if (modelo.getRowCount() > 0 && comprobarDatos(fechaChooser1.getDate(), fechaChooser2.getDate())) {
				new ExcelConsultaProyectos(this);
			}
		}
		if (e.getSource() == imprimirButton) {
			if (modelo.getRowCount() > 0 && comprobarDatos(fechaChooser1.getDate(), fechaChooser2.getDate())) {
				PDFConsultaProyectos pDFConsultaMovimientos = new PDFConsultaProyectos();
				pDFConsultaMovimientos.createPDF(this);
			}
		} else if (e.getSource() == cancelarButton) {
			window.setPanelInicial();
		}

	}

	public JDateChooser getFechaChooser1() {
		return fechaChooser1;
	}

	public JDateChooser getFechaChooser2() {
		return fechaChooser2;
	}

	public ModeloTablaProyectos getModelo() {
		return modelo;
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