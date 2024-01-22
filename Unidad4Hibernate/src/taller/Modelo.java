package taller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

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

	public Usuario obtenerUsuario(String us, String ps) {
		try {
			Query consulta = conexion.createQuery("from Usuario where usuario=?1 and ps=sha2(?2,512)");
			consulta.setParameter(1, us);
			consulta.setParameter(2, ps);
			List<Usuario> r = consulta.getResultList();
			if (r.size() == 1) {
				return r.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario obtenerUsuario(String dni) {
		try {
			Query consulta = conexion.createQuery("from Usuario where usuario=?1");
			consulta.setParameter(1, dni);
			List<Usuario> r = consulta.getResultList();
			if (r.size() == 1) {
				return r.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean crearUsuario(Usuario u) {
		EntityTransaction transaction = null;
		try {
			transaction = conexion.getTransaction();
			transaction.begin();
			conexion.persist(u);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public ArrayList<Vehiculo> obtenerVehiculos() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
			Query consulta = conexion.createQuery("from Vehiculo");
			List<Vehiculo> veh = consulta.getResultList();
			vehiculos = (ArrayList<Vehiculo>) veh;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculos;
	}

	public Vehiculo obtenerVehiculo(String m) {
		try {
			return conexion.find(Vehiculo.class, m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean crearVehiculo(Vehiculo v) {
		EntityTransaction transaction = null;
		try {
			transaction = conexion.getTransaction();
			transaction.begin();
			conexion.persist(v);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean crearReparacion(Reparacion r) {
		EntityTransaction transaction = null;
		try {
			transaction = conexion.getTransaction();
			transaction.begin();
			conexion.persist(r);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public Reparacion obtenerReparacion(int idR) {
		try {
			return conexion.find(Reparacion.class, idR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Reparacion> obtenerReparaciones() {
		ArrayList<Reparacion> resultado = new ArrayList<Reparacion>();
		try {
			Query consulta = conexion.createQuery("FROM  Reparacion");
			resultado = (ArrayList<Reparacion>) consulta.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean pagarReparacion(Reparacion r) {
		EntityTransaction t = null;
		try {
			t = conexion.getTransaction();
			t.begin();
			r.setTotal(r.getHoras() * r.getPrecioH());
			for (PiezaReparacion pr : r.getPiezareparaciones()) {
				r.setTotal(r.getTotal() + pr.getCantidad() * pr.getPrecio());
			}
			r.setFechaPago(new Date());
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Object[]> obtenerVentasMes(int id) {
		try {
			Query q = conexion.createQuery("FROM Reparacion WHERE ");
			return (ArrayList<Object[]>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Pieza obtenerPieza(int idPieza) {
		try {
			return conexion.find(Pieza.class, idPieza);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Pieza> obtenerPiezas() {
		try {
			Query q = conexion.createQuery("from Pieza");
			return (ArrayList<Pieza>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Pieza>();
	}

	public boolean modificar() {
		EntityTransaction t = null;
		try {
			t = conexion.getTransaction();
			t.begin();
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean cambiarPs(Usuario u) {
		EntityTransaction t = null;
		try {
			t = conexion.getTransaction();
			t.begin();
			t.commit();
			conexion.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Reparacion> obtenerReparaciones(String usuario) {
		try {
			Query e = conexion.createQuery("FROM Reparacion where vehiculo.propietario=?1");
			e.setParameter(1, usuario);
			return (ArrayList<Reparacion>) e.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Reparacion>();
	}
}