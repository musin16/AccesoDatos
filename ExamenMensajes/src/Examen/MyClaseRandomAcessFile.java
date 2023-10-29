package Examen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MyClaseRandomAcessFile  extends RandomAccessFile{

	public MyClaseRandomAcessFile(File file, String mode) throws FileNotFoundException {
		super(file, mode);
		// TODO Auto-generated constructor stub
	}

	public MyClaseRandomAcessFile(String name, String mode) throws FileNotFoundException {
		super(name, mode);
		// TODO Auto-generated constructor stub
	}
	public int skipBytes(int n) throws IOException {
        long pos;
        long len;
        long newpos;
        pos = getFilePointer();
        len = length();
        newpos = pos + n;
        if (newpos > len) {
            newpos = len;
        }
        seek(newpos);

        /* return the actual number of bytes skipped */
        return (int) (newpos - pos);
    }

}
