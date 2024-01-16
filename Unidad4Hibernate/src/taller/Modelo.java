package taller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Modelo {
	private EntityManager conexion = null;

	public Modelo() {
		try {
			conexion = Persistence.createEntityManagerFactory("Taller").createEntityManager();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}
	}

	public EntityManager getConexion() {
		return conexion;
	}

	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}

	public void cerrar() {
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}