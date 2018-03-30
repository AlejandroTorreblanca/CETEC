package baseDatos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import java.sql.SQLException;
 
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
 

 
public class ConnectBD {
 
    private Connection conexion;
    private Statement sentencia;
    private final String nombre_bd;
    private final String usuarioBD;
    private final String passwordBD;
 
    public ConnectBD(){
 
       // this.nombre_bd="C:\\Users\\Sandra\\Documents\\NetBeansProjects\\BD_Access\\Prueba_Java.accdb";
        String nombreBD="CETECbd.accdb";
        this.nombre_bd=System.getProperty("user.dir").replace("\\", "\\\\")+"\\\\"+nombreBD;
        this.usuarioBD="";
        this.passwordBD="";
    }
 
    public boolean EstablecerConexion() throws SQLException{
        try{
            conexion=DriverManager.getConnection("jdbc:ucanaccess://"+this.nombre_bd,this.usuarioBD,this.passwordBD);
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null,"Error al realizar la conexion "+e);
             return false;
        }
        try {
            this.sentencia=this.conexion.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
 
                    ResultSet.CONCUR_READ_ONLY);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos.\n "+e);
            return false;
        }
        return true;
 
    }
    public ResultSet ejecutarQuery( String sql) throws SQLException{
        ResultSet rs;
        rs = this.sentencia.executeQuery(sql);
        return rs;
    }
    
	public ResultSet ejecutarQuery(String sql, Date fechaIni, Date fechaFin) throws SQLException {
		ResultSet rs;
		PreparedStatement ps = conexion.prepareStatement(sql);
		ps.setTimestamp(1, new Timestamp(fechaIni.getTime()));
		ps.setTimestamp(2, new Timestamp(fechaFin.getTime()));
		rs = ps.executeQuery();
		ps.close();
		return rs;
	}
    
    public void ejecutarUpdate(String sql) throws SQLException{
    	this.sentencia.executeUpdate(sql);
    }
    
	public void ejecutarUpdate(String sql, Date fecha) throws SQLException {
		if (fecha == null) {
			java.util.Date utilDate = new java.util.Date();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(utilDate.getTime()));
			ps.executeUpdate();
			ps.close();
		}
		else{
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(fecha.getTime()));
			ps.executeUpdate();
			ps.close();
		}
	}
    
    public void getStatement() throws SQLException{
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(new Date());
//			String str = "SELECT * FROM CTCMOV M,CTCPRE P WHERE M.OPERARIO=P.OPERARIO AND P.AÑO='"+Integer.toString(calendar.get(Calendar.YEAR))+"'";
    		String str = "SELECT * FROM CTCMOV  WHERE OPERARIO>='0034'";
			
    		ResultSet rs;
			System.out.println(str);
			rs=this.sentencia.executeQuery(str);
	        while(rs.next())
	        {
	        	System.out.println(rs.getString("MOVIMIENTO")+" "+rs.getString("OPERARIO"));
	        	
	        }
    }
    
    public void ejecutarUpdate(PreparedStatement ps) throws SQLException
    {
    	ps.executeUpdate();
    }
    
    public Connection getConnection(){
    	return this.conexion;
    }
    
    public void close(){
    	try {
			this.sentencia.close();
			this.conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
