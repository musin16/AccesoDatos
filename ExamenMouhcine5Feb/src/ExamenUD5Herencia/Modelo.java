package ExamenUD5Herencia;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Modelo {
	private final String url = "jdbc:postgresql://localhost:5432/hospital";
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

	public Object obtener(int nss) {
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"Select id,nombre,(datos).telefono,(datos).email,nss,historia From paciente where nss=?");
			consulta.setInt(1, nss);
			ResultSet res = consulta.executeQuery();
			if (res.next()) {
				Array array = res.getArray(6);
				if (array != null) {
					String[][] campos = (String[][]) array.getArray();
					ArrayList<String[]> historial = new ArrayList<>(Arrays.asList(campos));
//					return new Object(res.getInt(1), res.getString(2),
//							new Contacto(res.getString(3), res.getString(4)), nss, historial);
				} else {
//					return new Paciente(res.getInt(1), res.getString(2),
//							new Contacto(res.getString(3), res.getString(4)), nss, new ArrayList<String[]>());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public boolean crear(Paciente pac) {
//
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("Insert into paciente values(default,?,(?,?),?,null)");
//			consulta.setString(1, pac.get);
//			consulta.setString(2, pac.get));
//			consulta.setString(3, pac.get));
//			consulta.setInt(4, pac.getNss());
//			int fila = consulta.executeUpdate();
//			if (fila == 1) {
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
//	public ArrayList<Object> obtenerPersonas() {
//		ArrayList<Object> personas = new ArrayList<Object>();
//		try {
//			Statement consulta = conexion.createStatement();
//			ResultSet res = consulta.executeQuery("Select id,nombre,(datos).email,(datos).telefono from persona");
//			while (res.next()) {
//				personas.add(
//						new Object(res.getInt(1), res.getString(2), new Contacto(res.getString(3), res.getString(4))));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return personas;
//	}
	
/*	public boolean registrarDiagnostico(Consulta c) {
		// TODO Auto-generated method stub
		boolean resultado = false;		
		try {
			conexion.setAutoCommit(false);
			PreparedStatement consulta = conexion.prepareStatement(
					"update consulta set diagnostico = ? where id = ?");
			consulta.setString(1, c.getDiagnostico());
			consulta.setInt(2, c.getId());
			int r = consulta.executeUpdate();
			if(r==1) {
				//Actualizar el array del paciente
				//Si el paciente no tiene historial, acutalizamos el campo
				//historia con un nuevo array de un elemento
				if(c.getPaciente().getHistoria().isEmpty()) {
					consulta = conexion.prepareStatement(
							"update paciente set historia = array[array[?,?]] where id = ?");
					consulta.setString(1, new java.util.Date().toString());
					consulta.setString(2, c.getDiagnostico());
					consulta.setInt(3, c.getPaciente().getId());
					r = consulta.executeUpdate();
					if(r==1) {
						conexion.commit();
					}
					else {
						conexion.rollback();
					}
				}
				else {
					//Si el paciente tiene historial, acutalizamos el campo
					//historia concatenando al array un nuevo elemento
					consulta = conexion.prepareStatement(
							"update paciente set historia = "
							+ "array_cat(historia,array[?,?]::text[][]) where id = ?");
					consulta.setString(1, new java.util.Date().toString());
					consulta.setString(2, c.getDiagnostico());
					consulta.setInt(3, c.getPaciente().getId());
					r = consulta.executeUpdate();
					if(r==1) {
						conexion.commit();
					}
					else {
						conexion.rollback();
					}
				}
				
				
			}
			else {
				conexion.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultado;
	}
	*/
}