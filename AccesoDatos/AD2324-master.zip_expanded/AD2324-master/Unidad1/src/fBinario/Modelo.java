package fBinario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
	private String nombreFichero; // Fichero binario de asignaturas

	// Fichero de accceso aleatorio para obtener el id de nueva asig
	private String nombreFichConfig = ".config";

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public int obtenerId() {
		// TODO Auto-generated method stub
		// Si el fichero .config existe debe devolver el nº
		// que hay en el fichero más 1 y modificar el nº del
		// fichero con el nuevo valor.
		// Si no existe, devuelve 1 y el fichero se crea con
		// el nº 1.
		int resultado = 1;
		// DEclarar el fichero de acceso aleatorio
		RandomAccessFile fA = null;

		try {
			// Abrir el fichero RandomAccessFile para leer y escribir (rw)
			File f = new File(nombreFichConfig);
			boolean existe = f.exists();
			fA = new RandomAccessFile(nombreFichConfig, "rw");
			// Chequear si existe .config
			if (existe) {
				// Recorrer el fichero y leer el nº
				while (true) {
					// Leer el nº
					resultado = fA.readInt() + 1;
					// Colocamos el apuntador del fichero al principio para sobreescribir el nº
					// Despalazamos hacia atrás(restamos) 4 Bytes
					fA.seek(fA.getFilePointer() - 4);
					fA.writeInt(resultado);
				}
			} else {
				// Escribimos el nuevo nº
				fA.writeInt(resultado);
			}

		} catch (EOFException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (fA != null) {
				try {
					fA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public ArrayList<Asignatura> obtenerAsignatura() {
		ArrayList<Asignatura> resultado = new ArrayList<Asignatura>();
		;
		DataInputStream f = null;
		Asignatura a = null;
		try {
			f = new DataInputStream(new FileInputStream(nombreFichero));
			while (true) {
				a = new Asignatura();
				a.setId(f.readInt());
				a.setNombre(f.readUTF());
				a.setFechaRD(new Date(f.readLong()));
				a.setCreditos(f.readFloat());
				a.setActiva(f.readBoolean());
				resultado.add(a);

			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo inexistente");
		} catch (EOFException e) {

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean crearAsigna(Asignatura as) {
		boolean resultado = false;
		DataOutputStream dt = null;

		try {
			dt = new DataOutputStream(new FileOutputStream(nombreFichero, true));
			dt.writeInt(as.getId());
			dt.writeUTF(as.getNombre());
			dt.writeLong(as.getFechaRD().getTime());
			dt.writeFloat(as.getCreditos());
			dt.writeBoolean(as.isActiva());
			resultado = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if (dt != null) {
				try {
					dt.close();
				} catch (IOException ex) {
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean darBajaAsignatura(int id2) {
		boolean resultado = false;
		try (RandomAccessFile f = new RandomAccessFile(nombreFichero, "rw");) {
			// Mueve el puntero al principio del archivo
			f.seek(0);
			while (buscarId(id2)) {
				// Lee el ID del registro
				int idd = f.readInt();
				f.readUTF();
				f.readLong();
				f.readFloat();
				if (idd == id2) {
					f.writeBoolean(false);
					resultado = true;
				} else {
					f.readBoolean();
				}
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {

		}

		return resultado;
	}

	public boolean borrarAsigPorId(int pedirid) {
		boolean resultado = false;
		if (buscarId(pedirid)) {
			try (RandomAccessFile f = new RandomAccessFile(nombreFichero, "r");
					DataOutputStream d = new DataOutputStream(new FileOutputStream("asignaturas2.bin", true));) {
				f.seek(0);
				while (true) {
					int id = f.readInt();
					if (pedirid == id) {
						f.readUTF();
						f.readLong();
						f.readFloat();
						f.readBoolean();
					} else {
						d.writeInt(id);
						d.writeUTF(f.readUTF());
						d.writeLong(f.readLong());
						d.writeFloat(f.readFloat());
						d.writeBoolean(f.readBoolean());
					}
				}
			} catch (EOFException e) {

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				File archTemp = new File("asignaturas2.bin");
				File ar = new File(nombreFichero);
				if (ar.exists()) {
					if (ar.delete()) {
						System.out.println("Se ha borrado correctamente");
						archTemp.renameTo(ar);
					}

				} else {
					System.out.println("Fichero no existe");
				}
			}
		}
		return resultado;
	}

	private boolean buscarId(int pedirid) {
		try (RandomAccessFile ra = new RandomAccessFile(nombreFichero, "rw")) {
			while (true) {
				if (ra.readInt() == pedirid) {
					return true;
				}
				ra.readUTF();
				ra.readLong();
				ra.readFloat();
				ra.readBoolean();

			}
		} catch (EOFException e) {
			System.out.println("No existe ninguna asignatura con ese id");
		} catch (IOException e) {

		}
		return false;
	}

}
