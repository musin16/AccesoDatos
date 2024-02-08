package teatro;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Modelo {
	private final String url = "jdbc:postgresql://localhost:5432/Teatro";
	private final String us = "postgres";
	private final String ps = "postgres";

	private Connection conexion = null;

	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public boolean crearInmueble(Inmueble in) {
		try {
			PreparedStatement pr = conexion
					.prepareStatement("INSERT INTO inmuebles VALUES (default, (?, ?, ?), ?,?,?::text[]);");
			String[] str = in.getExtras().toArray(new String[0]);
			Array inmueble = conexion.createArrayOf("text", str);
			pr.setString(1, in.getDir().getCalle());
			pr.setInt(2, in.getDir().getNumero());
			pr.setInt(3, in.getDir().getCp());
			pr.setInt(4, in.getNumeroHabitaciones());
			pr.setInt(5, in.getNumeroBanios());
			pr.setArray(6, inmueble);
			int filas = pr.executeUpdate();
			if (filas == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Inmueble> obtenerInmueles() {
		ArrayList<Inmueble> in = new ArrayList();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet res = consulta.executeQuery(
					"SELECT codigo, (dir).calle,(dir).numero,(dir).cp ,numhabitaciones, numbanios, extras "
							+ "	FROM inmuebles;");

			while (res.next()) {
				Array ar = res.getArray(7);
				String[] s = (String[]) ar.getArray();
				ArrayList<String> ex = new ArrayList<String>(Arrays.asList(s));
				in.add(new Inmueble(res.getInt(1), new Direccion(res.getString(2), res.getInt(3), res.getInt(4)),
						res.getInt(5), res.getInt(6), ex));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return in;
	}

	public Inmueble obtenerInmuele(int id) {
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"SELECT codigo, (dir).calle,(dir).numero,(dir).cp ,numhabitaciones, numbanios, extras	FROM inmuebles where codigo=?;");
			consulta.setInt(1, id);
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				Array ar = res.getArray(7);
				String[] s = (String[]) ar.getArray();
				ArrayList<String> ex = new ArrayList<String>(Arrays.asList(s));
				return new Inmueble(res.getInt(1), new Direccion(res.getString(2), res.getInt(3), res.getInt(4)),
						res.getInt(5), res.getInt(6), ex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean modificaInmueble(Inmueble in) {
		try {
			PreparedStatement pr = conexion.prepareStatement("Update inmuebles set extras=?::text[]  where codigo=?;");
			String[] str = in.getExtras().toArray(new String[0]);
			Array inmueble = conexion.createArrayOf("text", str);
			pr.setArray(1, inmueble);
			pr.setInt(2, in.getCodigo());
			int filas = pr.executeUpdate();
			if (filas == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Espectaculo obtenerEspectaculo(String title) {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT id, titulo, precio	FROM espectaculo where titulo=?;");
			consulta.setString(1, title);
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				Array ar = res.getArray(7);
				Double[] s = (Double[]) ar.getArray();
				List<Double> ex = new ArrayList<Double>(Arrays.asList(s));
				return new Espectaculo(res.getInt(1), res.getString(2), ex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean crearEspectaculo(Espectaculo es) {
		try {
			PreparedStatement pr = conexion
					.prepareStatement("INSERT INTO espectaculo VALUES (default, ?, ?::float[]);");
			Double[] str = es.getPrecios().toArray(new Double[0]);
			Array espec = conexion.createArrayOf("float", str);
			pr.setString(1, es.getTitulo());
			pr.setArray(2, espec);
			int filas = pr.executeUpdate();
			if (filas == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Espectaculo> obtenerEspectaculos() {
		ArrayList<Espectaculo> espec = new ArrayList<>();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet res = consulta.executeQuery("SELECT id, titulo, precio FROM espectaculo ORDER BY 1 DESC ");
			while (res.next()) {
				Array ar = res.getArray(3); // Obtener el array en la tercera columna (precio)
				if (ar != null) {
					Object obj = ar.getArray();
					System.out.println("Tipo de objeto en el array: " + obj.getClass().getName()); // Imprimir tipo de
																									// objeto
					Double[] s = (Double[]) ar.getArray();
					List<Double> ex = new ArrayList<Double>(Arrays.asList(s));
					espec.add(new Espectaculo(res.getInt(1), res.getString(2), ex));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return espec;
	}

	public Espectaculo obtenerEspectaculo(int num) {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT id, titulo, precio	FROM espectaculo where id=?;");
			consulta.setInt(1, num);
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				Array ar = res.getArray(3);
				Double[] s = (Double[]) ar.getArray();
				List<Double> ex = new ArrayList<Double>(Arrays.asList(s));
				return new Espectaculo(res.getInt(1), res.getString(2), ex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean modificarEspectaculo(Espectaculo es) {
		try {
			PreparedStatement pr = conexion.prepareStatement("UPDATE espectaculo SET precio=? WHERE id=?");
			Double[] str = es.getPrecios().toArray(new Double[0]);
			Array espe = conexion.createArrayOf("float", str);
			pr.setArray(1, espe);
			pr.setInt(2, es.getId());
			int filas = pr.executeUpdate();
			if (filas == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
