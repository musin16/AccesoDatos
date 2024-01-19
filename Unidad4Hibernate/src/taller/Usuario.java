package taller;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String usuario;
	@Column(nullable = false)
	private String perfil;
	@Column(nullable = false)
	private String ps;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	List<Reparacion> registros = new ArrayList<Reparacion>();

	public Usuario() {

	}

	public Usuario(int id, String usuario, String perfil, String ps, List<Reparacion> registros) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.perfil = perfil;
		this.ps = ps;
		this.registros = registros;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<Reparacion> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Reparacion> registros) {
		this.registros = registros;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", perfil=" + perfil + "]";
	}

}
