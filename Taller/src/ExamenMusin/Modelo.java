package ExamenMusin;

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
import java.util.Map;

public class Modelo {
	private final String url = "jdbc:postgresql://localhost:5432/Taller";
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

	public ArrayList<Vehiculo> obtenerVehiculos() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet res = consulta.executeQuery(
					"Select matricula,(propietario).nif,(propietario).nombre, (propietario).telefono From vehiculos");
			while (res.next()) {
				vehiculos.add(new Vehiculo(res.getString(1),
						new DatosPropietario(res.getString(2), res.getString(3), res.getString(4))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehiculos;
	}

	public Vehiculo obtenerVehiculo(String matricula) {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("Select matricula,(propietario).nif,(propietario).nombre, (propietario).telefono"
							+ " From vehiculos where matricula=?");
			consulta.setString(1, matricula);
			ResultSet res = consulta.executeQuery();
			if (res.next()) {
				return new Vehiculo(res.getString(1),
						new DatosPropietario(res.getString(2), res.getString(3), res.getString(4)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean crearRevision(Revision revision) {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("Insert into revisiones Values(default,?,?,0,array[?]::text[])");
			consulta.setString(1, revision.getVehiculo());
			consulta.setDate(2, new Date(revision.getFecha().getTime()));
			String[] vals = revision.getActuaciones().toArray(new String[0]);
			Array ar = conexion.createArrayOf("text", vals);
			consulta.setArray(3, ar);
			int filas = consulta.executeUpdate();
			if (filas == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Revision> obtenerRevisiones() {
		ArrayList<Revision> revisiones = new ArrayList();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet res = consulta.executeQuery("Select id,vehiculo,fecha From revisiones");
			while (res.next()) {
				revisiones.add(new Revision(res.getInt(1), res.getString(2), res.getDate(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return revisiones;
	}

	public Revision obtenerRevision(int id) {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("Select id,vehiculo,fecha,horas,actuaciones From revisiones where id=?");
			consulta.setInt(1, id);
			ResultSet res = consulta.executeQuery();
			if (res.next()) {
				Array arra = res.getArray(5);
				if (arra != null) {
					String[] actuacion = (String[]) arra.getArray();
					ArrayList<String> actuaciones = new ArrayList<String>();
					for (String act : actuacion) {
						actuaciones.add(act);
					}
					return new Revision(res.getInt(1), res.getString(2), res.getDate(3), res.getInt(4), actuaciones);
				} else {
					return new Revision(res.getInt(1), res.getString(2), res.getDate(3), res.getInt(4),
							new ArrayList<String>());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Revision> obtenerRevision(String matricula) {
		ArrayList<Revision> rev = new ArrayList<Revision>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("Select id,vehiculo,fecha,horas,actuaciones From revisiones where vehiculo=?");
			consulta.setString(1, matricula);
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				Array arra = res.getArray(5);
				if (arra != null) {
					String[] actuacion = (String[]) arra.getArray();
					ArrayList<String> actuaciones = new ArrayList<String>();
					for (String act : actuacion) {
						actuaciones.add(act);
					}
					rev.add(new Revision(res.getInt(1), res.getString(2), res.getDate(3), res.getInt(4), actuaciones));
				} else {
					rev.add(new Revision(res.getInt(1), res.getString(2), res.getDate(3), res.getInt(4),
							new ArrayList<String>()));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rev;
	}

	public boolean modificarRevision(Revision revision) {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("Update revisiones SET actuaciones=?::text[],horas=? where id=?");
			String[] arrayactuaciones = revision.getActuaciones().toArray(new String[0]);
			Array arActuaciones = conexion.createArrayOf("text", arrayactuaciones);
			consulta.setArray(1, arActuaciones);
			consulta.setInt(2, revision.getHoras());
			consulta.setInt(3, revision.getId());
			int fila = consulta.executeUpdate();
			if (fila == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean borrarVehiculo(Vehiculo veh, ArrayList<Revision> revisiones) {

		try {
			PreparedStatement consulta = null;
			conexion.setAutoCommit(false);
			int fila = 0;
			for (Revision revision : revisiones) {
				consulta = conexion.prepareStatement("Delete FROM revisiones  where id=?");
				consulta.setInt(1, revision.getId());
				fila += consulta.executeUpdate();
			}

			if (fila == revisiones.size()) {
				consulta = conexion.prepareStatement("Delete FROM vehiculos  where matricula=?");
				consulta.setString(1, veh.getMatricula());
				int filaV = consulta.executeUpdate();
				if (filaV == 1) {
					conexion.commit();
					return true;
				} else {
					conexion.rollback();
				}
			} else {
				conexion.rollback();
			}

		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	public boolean borrarVehiculo(Vehiculo veh) {
		try {
			conexion.setAutoCommit(false);
			PreparedStatement consulta = conexion.prepareStatement("Delete FROM vehiculos  where matricula=?");
			consulta.setString(1, veh.getMatricula());
			int filaV = consulta.executeUpdate();
			if (filaV == 1) {
				conexion.commit();
				return true;
			} else {
				conexion.rollback();
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

}
