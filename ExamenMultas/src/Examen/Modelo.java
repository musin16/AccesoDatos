package Examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Modelo {

	public Radar unmarshaller(String xml) {
		Radar r = null;
		try {

			JAXBContext um = JAXBContext.newInstance(Radar.class);
			Unmarshaller um2 = um.createUnmarshaller();
			Radar r2 = (Radar) um2.unmarshal(new File(xml));
			r = r2;
		} catch (JAXBException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;
	}

	public Matriculas obtenerMatricula(String matr) {
		Matriculas resultado = null;
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile("multas.bin", "r");
			while (true) {

				Matriculas mat = new Matriculas();
				mat.setMatricula("");
				for (int i = 0; i < 7; i++) {
					mat.setMatricula(mat.getMatricula() + r.readChar());
				}
				if (mat.getMatricula().equalsIgnoreCase(matr)) {
					mat.setNumMultas(r.readInt());
					mat.setImporteRecaudado(r.readFloat());
					mat.setFecha(r.readLong());
					return mat;
				} else {
					r.skipBytes(16);
				}
			}
		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean escribirFichero(Matriculas matOfi) {
		boolean resultado = false;
		RandomAccessFile rw = null;
		try {
			rw = new RandomAccessFile("multas.bin", "rw");
			rw.seek(rw.length());
			StringBuffer br = new StringBuffer(matOfi.getMatricula());
			br.setLength(7);
			rw.writeChars(br.toString());
			rw.writeInt(matOfi.getNumMultas());
			rw.writeFloat(matOfi.getImporteRecaudado());
			rw.writeLong(matOfi.getFecha());
			resultado = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (rw != null) {
				try {
					rw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public ArrayList<Matriculas> obtenerMatriculas() {

		ArrayList<Matriculas> resultado = new ArrayList<Matriculas>();
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile("multas.bin", "r");
			while (true) {

				Matriculas mat = new Matriculas();
				mat.setMatricula("");
				for (int i = 0; i < 7; i++) {
					mat.setMatricula(mat.getMatricula() + r.readChar());
				}
				mat.setNumMultas(r.readInt());
				mat.setImporteRecaudado(r.readFloat());
				mat.setFecha(r.readLong());
				resultado.add(mat);
			}
		} catch (EOFException E) {
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;

	}

	public boolean borrarMatricula(Matriculas m) {
		boolean resultado = false;
		RandomAccessFile rwTemp = null;
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile("multas.bin", "r");
			rwTemp = new RandomAccessFile("multasTemp.bin", "rw");
			while (true) {

				Matriculas mat = new Matriculas();
				mat.setMatricula("");
				for (int i = 0; i < 7; i++) {
					mat.setMatricula(mat.getMatricula() + r.readChar());
				}
				if (mat.getMatricula().equalsIgnoreCase(m.getMatricula())) {
					r.skipBytes(16);
				} else {
					r.seek(r.getFilePointer() - 14);
					byte[] bt = new byte[30];
					r.read(bt);
					rwTemp.write(bt);
				}
			}
		} catch (EOFException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (r != null) {
					r.close();
				}
				if (rwTemp != null) {
					rwTemp.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		File f = new File("multas.bin");
		File fTemp = new File("multasTemp.bin");
		if (f.delete()) {
			if (fTemp.renameTo(f)) {
				resultado = true;
			} else {
				System.out.println("ERROR AL RENOMBRAR");
			}
		} else {
			System.out.println("Fichero borrado correctamente");
		}
		return resultado;
	}

	public ArrayList<RadarMovil> obtenerMultasRadarMovil() {
		ArrayList<RadarMovil> rmov = new ArrayList<RadarMovil>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Multas.txt"));
			String linea = "";
			while ((linea = br.readLine()) != null) {
				String[] campo = linea.split(";");
				RadarMovil m = new RadarMovil();
				m.setPuntoKM(campo[0]);
				m.setKm(Integer.parseInt(campo[1]));
				m.setMatricula(campo[2]);
				m.setHora(new Date());
				m.setVelocidadCaptada(Integer.parseInt(campo[4]));
				rmov.add(m);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rmov;
	}

	public boolean modificarFichero(Matriculas matOfi) {
		boolean resultado = false;
		RandomAccessFile rw = null;
		try {
			rw = new RandomAccessFile("multas.bin", "rw");
			StringBuffer br = new StringBuffer(matOfi.getMatricula());
			br.setLength(7);
			while (true) {
				Matriculas mat = new Matriculas();
				mat.setMatricula("");
				for (int i = 0; i < 7; i++) {
					mat.setMatricula(mat.getMatricula() + rw.readChar());
				}
				String matricula = mat.getMatricula();
				if (matricula.equalsIgnoreCase(matOfi.getMatricula())) {
					rw.seek(rw.getFilePointer() - 14);
					rw.writeChars(matricula);
					rw.writeInt(matOfi.getNumMultas());
					rw.writeFloat(matOfi.getImporteRecaudado());
					rw.writeLong(matOfi.getFecha());
				} else {
					rw.skipBytes(16);
				}
				resultado = true;
			}
		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (rw != null) {
				try {
					rw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

}
