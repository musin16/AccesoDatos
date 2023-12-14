package ExamenMusin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Modelo {

	private static final String us = "root";
	private static final String ps = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/mensajes";
	private String usuario = us;
	private String password = ps;
	private Connection conexion = null;

	public Modelo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(URL, usuario, "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public int getEmpleado(int numEmp, String dni) {
		try (CallableStatement call = conexion.prepareCall("{? = call login(?, sha2(?, 0))}")) {
			call.setInt(2, numEmp);
			call.setString(3, dni);
			call.registerOutParameter(1, Types.INTEGER);
			call.executeUpdate();
			return call.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
}
