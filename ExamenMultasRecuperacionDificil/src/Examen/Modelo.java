package Examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {

	public ArrayList<Empleados> obtenerEmpleados() {
		ArrayList<Empleados> emp = new ArrayList<Empleados>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("empleados.txt"));
			String linea;
			while ((linea = br.readLine()) != null) {
				String campo[] = linea.split(";");
				Empleados e = new Empleados(Integer.parseInt(campo[0]), campo[1], Boolean.parseBoolean(campo[2]));
				emp.add(e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return emp;

	}

	public Empleados obtenerEmpleado(int id) {
		Empleados resultado = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("empleados.txt"));
			String linea;
			while ((linea = br.readLine()) != null) {
				String campo[] = linea.split(";");
				Empleados e = new Empleados(Integer.parseInt(campo[0]), campo[1], Boolean.parseBoolean(campo[2]));
				if (Integer.parseInt(campo[0]) == id) {
					return e;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public boolean escribirMensaje(Mensaje m) {
		boolean resultado = false;
		RandomAccessFile rw = null;
		try {
			rw = new RandomAccessFile("mensajes.bin", "rw");
			rw.seek(rw.length());
			rw.getFilePointer();
			rw.writeLong(m.getFecha().getTime());
			rw.writeInt(m.getIdEmpleado());
			rw.writeChars(m.getNombre());
			rw.writeChars(m.getMensaje());
			rw.writeBoolean(m.isActivo());

			resultado = true;
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			try {
				if (rw != null) {
					rw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public ArrayList<Mensaje> obtenerMensajes(int id) {
		ArrayList<Mensaje> listMen = new ArrayList<Mensaje>();
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile("mensajes.bin", "r");
			Mensaje m = new Mensaje();

			while (true) {
				r.skipBytes(8);
				int id2 = r.readInt();
				if (id2 == id) {
					r.seek(r.getFilePointer() - 12);
					m.setFecha(new Date(r.readLong()));
					m.setIdEmpleado(r.readInt());
					m.setNombre("");
					for (int i = 0; i < 100; i++) {
						m.setNombre(m.getNombre() + r.readChar());
					}
					String letra = m.getNombre().trim();
					m.setNombre(letra);
					m.setMensaje("");
					for (int i = 0; i < 200; i++) {
						m.setMensaje(m.getMensaje() + r.readChar());
					}
					m.setMensaje(m.getMensaje().trim());
					m.setActivo(r.readBoolean());
					listMen.add(m);
				} else {
					r.skipBytes(601);
				}
			}
		} catch (EOFException E) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listMen;
	}

	public boolean marcarLeidoMensaje(int id) {
		boolean resultado = false;
		RandomAccessFile rw = null;
		try {
			rw = new RandomAccessFile("mensajes.bin", "rw");
			rw.seek(0);
			while (true) {
				rw.skipBytes(8);
				int id2 = rw.readInt();
				if (id2 == id) {
					rw.skipBytes(600);
					resultado = true;
					if (!rw.readBoolean()) {
						rw.seek(rw.getFilePointer() - 1);
						rw.writeBoolean(true);
						System.out.println("Mensaje Leido");
						resultado = true;
					}
				} else {
					rw.skipBytes(601);
				}
				resultado = true;
			}
		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rw != null) {
					rw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public boolean borrarMensajes(int id) {
		boolean resultado = false;
		RandomAccessFile r = null;
		RandomAccessFile rTemp = null;

		try {
			r = new RandomAccessFile("mensajes.bin", "r");
			rTemp = new RandomAccessFile("mensajesTemp.bin", "rw");
			Mensaje m = new Mensaje();

			while (true) {
				r.skipBytes(8);
				int id2 = r.readInt();
				if (id2 != id) {
					r.seek(r.getFilePointer() - 12);
					long rT=rTemp.length();
					long rT2=rTemp.getFilePointer();
					rTemp.writeLong(r.readLong());
					rTemp.writeInt(r.readInt());
					m.setNombre("");
					for (int i = 0; i < 100; i++) {
						m.setNombre(m.getNombre() + r.readChar());
					}
					m.setNombre(m.getNombre());
					m.setMensaje("");
					for (int i = 0; i < 200; i++) {
						m.setMensaje(m.getMensaje() + r.readChar());
					}
					m.setMensaje(m.getMensaje());
					rTemp.writeChars(m.getNombre());
					rTemp.writeChars(m.getMensaje());
					rTemp.writeBoolean(r.readBoolean());
				} else {
					r.skipBytes(601);
				}
			}
		} catch (EOFException E) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) {
					r.close();
				}
				if (rTemp != null) {
					rTemp.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File original = new File("mensajes.bin");
		File remake = new File("mensajesTemp.bin");
		if (original.exists()) {
			if (original.delete()) {
				if (remake.renameTo(original)) {
					resultado = true;
				} else {
					System.out.println("Error al renombrar");
				}
			} else {
				System.out.println("Error,no se ha borrado el fichero");
			}
		} else {
			System.out.println("No existe");
		}
		return resultado;
	}

	public ArrayList<Mensaje> obtenerTodosMensajes() {

		ArrayList<Mensaje> listMen = new ArrayList<Mensaje>();
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile("mensajes.bin", "r");
			Mensaje m = new Mensaje();

			while (true) {
				m.setFecha(new Date(r.readLong()));
				m.setIdEmpleado(r.readInt());
				m.setNombre("");
				for (int i = 0; i < 100; i++) {
					m.setNombre(m.getNombre() + r.readChar());
				}
				String letra = m.getNombre().trim();
				m.setNombre(letra);
				m.setMensaje("");
				for (int i = 0; i < 200; i++) {
					m.setMensaje(m.getMensaje() + r.readChar());
				}
				m.setMensaje(m.getMensaje().trim());
				m.setActivo(r.readBoolean());
				listMen.add(m);
			}

		} catch (EOFException E) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listMen;

	}

	public boolean marshall(int id) {
		boolean resultado=false;
		
		return resultado;
	}

}
