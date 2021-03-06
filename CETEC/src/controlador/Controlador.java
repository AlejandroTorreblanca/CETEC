package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import baseDatos.ConnectBD;
import gui.PanelMensaje;

public class Controlador {

	private static Controlador unicaInstancia;
	private final ConnectBD con;

	private Controlador() {
		this.con = new ConnectBD();
		try {
			this.crearConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public void crearConexion() throws SQLException {
		this.con.EstablecerConexion();
	}

	public ResultSet setStatementSelect(String str1, String str2) throws SQLException {
		String str;
		if (str2.isEmpty())
			str = "SELECT * FROM " + str1;
		else
			str = "SELECT * FROM " + str1 + " WHERE " + str2;

		ResultSet rs;
		//System.out.println(
		rs = this.con.ejecutarQuery(str);
		return rs;
	}
	
	public ResultSet setStatementSelect(String str1, String str2, Date fechaIni, Date fechaFin) throws SQLException {
		String str = "SELECT * FROM " + str1 + " WHERE " + str2;
		ResultSet rs;
		//System.out.println(
		rs = this.con.ejecutarQuery(str,fechaIni,fechaFin);
		return rs;
	}

	public int getIdentificadorMOV() {
		String str = "SELECT MAX(MOVIMIENTO) FROM CTCMOV";
		ResultSet rs;
		try {
			rs = this.con.ejecutarQuery(str);
			if(rs.first()){
				System.out.println(rs.getInt("C1"));
				return rs.getInt("C1")+1;
			}
			
		} catch (SQLException e) {
			new PanelMensaje("Fallo al buscar el identificador del movimiento.\n"+e, "Error", "error");
			e.printStackTrace();
		}
		
		return -1;
	}

	public int getIdentificadorTRB() {
		String str = "SELECT MAX(NRO_TRABAJO) FROM CTCTRB WHERE CLAVE_TRABAJO!='OF'";
		ResultSet rs;
		try {
			rs = this.con.ejecutarQuery(str);
			if(rs.first()){
				return rs.getInt("C1")+1;
			}
		} catch (SQLException e) {
			new PanelMensaje("Fallo al buscar el identificador del trabajo.\n"+e, "Error", "error");
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int getIdentificadorOPE() {
		String str = "SELECT MAX(OPERARIO) FROM CTCOPE";
		ResultSet rs;
		try {
			rs = this.con.ejecutarQuery(str);
			if(rs.first()){
				return rs.getInt("C1")+1;
			}
		} catch (SQLException e) {
			new PanelMensaje("Fallo al buscar el identificador del operario.\n"+e, "Error", "error");
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void setStatementInsert(String str1, String str2, String str3) throws SQLException {
		String str = "INSERT INTO " + str1 + " " + str2 + " VALUES " + str3;
		//System.out.println(
		this.con.ejecutarUpdate(str);

	}

	public void setStatementInsert(String str1, String str2, String str3, Date fecha) throws SQLException {
		String str = "INSERT INTO " + str1 + " " + str2 + " VALUES " + str3;
		//System.out.println(
		this.con.ejecutarUpdate(str, fecha);

	}

	public void setStatementUpdate(String str1, String str2, String str3) throws SQLException {
		String str = "UPDATE " + str1 + " SET " + str2 + " WHERE " + str3;
		//System.out.println(
		this.con.ejecutarUpdate(str);

	}

	public void setStatementUpdate(String str1, String str2, String str3, Date fecha) throws SQLException {
		String str = "UPDATE " + str1 + " SET " + str2 + " WHERE " + str3;
		//System.out.println(
		this.con.ejecutarUpdate(str, fecha);

	}


	public void ejecutarUpdate(PreparedStatement ps) throws SQLException {
		con.ejecutarUpdate(ps);
	}

	public void close() {
		con.close();
	}

}
