package U5_ExamenHerbolario.src;

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

public class Modelo {
	private final String url = "jdbc:postgresql://postgresqldam.ciffjlql7jtm.us-east-1.rds.amazonaws.com/herbolario";
	private final String us = "postgres";
	private final String ps = "postgres";

	private Connection conexion = null;

	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public boolean crearProducto(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("insert into producto values (default,?,(?,?,?),array[]::int[])");
			consulta.setString(1, p.getNombre());
			consulta.setInt(2, p.getDatosNutricion().getKcal());
			consulta.setInt(3, p.getDatosNutricion().getGrasas());
			consulta.setInt(4, p.getDatosNutricion().getHidratos());
			int r = consulta.executeUpdate();
			if (r == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Producto> obtenerProductos() {
		ArrayList<Producto> resultado = new ArrayList();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery(
					"select codigo, nombre, (info).kcal, " + "(info).grasa, (info).hidratos, precios from Producto");
			while (r.next()) {
				ArrayList<Integer> arrayPrecios = new ArrayList();
				Array p = r.getArray(6);
				Integer[] precios = (Integer[]) p.getArray();
				for (int i = 0; i < precios.length; i++) {
					arrayPrecios.add(precios[i]);
				}

				resultado.add(new Producto(r.getInt(1), r.getString(2), new Info(r.getInt(3), r.getInt(4), r.getInt(5)),
						arrayPrecios));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Producto obtenerProducto(int idP) {
		Producto resultado = null;

		try {
			PreparedStatement consulta = conexion.prepareStatement("select codigo, nombre, (info).kcal, "
					+ "					+ (info).grasa, (info).hidratos, precios " + "from Producto where codigo = ?");
			consulta.setInt(1, idP);
			ResultSet r = consulta.executeQuery();
			if (r.next()) {
				Array p = r.getArray(6);
				Integer[] precios = (Integer[]) p.getArray();
				ArrayList<Integer> arrayPrecios = new ArrayList<>(Arrays.asList(precios));
				resultado = new Producto(r.getInt(1), r.getString(2), new Info(r.getInt(3), r.getInt(4), r.getInt(5)),
						arrayPrecios);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearPrecio(Producto p, int nuevo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"update producto set precios = array_cat(precios,array[?]::int[]) " + "where codigo = ?");
			consulta.setInt(1, nuevo);
			consulta.setInt(2, p.getCodigo());
			int r = consulta.executeUpdate();
			if (r == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearVenta(Venta venta) {
		try {
			PreparedStatement consult = conexion.prepareStatement("Insert into venta VALUES (DEFAULT,?,?,?,?)");
			consult.setDate(1, new Date(venta.getFecha().getTime()));
			consult.setInt(2, venta.getProducto());
			consult.setInt(3, venta.getCantidad());
			consult.setInt(4, venta.getPrecio());
			if (consult.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Venta> estadisticasVenta() {
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		try {
			PreparedStatement consult = conexion
					.prepareStatement("Select producto,sum(cantidad) from venta group by producto");
			ResultSet rs = consult.executeQuery();
			while (rs.next()) {
				ventas.add(new Venta(rs.getInt(1), rs.getInt(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ventas;
	}

}
