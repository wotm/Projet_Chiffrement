import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;



public class FileFactory {
	
	public static byte [] extractionDonneesFichier(String path) {
		try {
			File f = new File(path);
			BufferedInputStream reader = new BufferedInputStream(new FileInputStream(f));
			ArrayList<Byte> datas = extractionDonneesFichier(reader);
			reader.close();
			return ListToArrayByte(datas);
		}catch (Exception e) {
			e.printStackTrace();
			return new byte [0];
		}
	}
		

	public static ArrayList<Byte> extractionDonneesFichier(BufferedInputStream reader) {
		int length = 1000;
		ArrayList<Byte> datas = new ArrayList<Byte>();
		byte [] tmp = new byte[length];
		int i = 0;
		try {
			while((i = reader.read(tmp)) > 0) {
				for (int y = 0; y <i; y++) {
					byte b = tmp[y];
					datas.add(b);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return datas;
	}
	
	public static boolean ecritureDonneesFichier(String path, byte [] datas) {
		try {
			File f = new File(path);
			BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(f));
			ecritureDonneesFichier(writer, datas);
			writer.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ecritureDonneesFichier(BufferedOutputStream writer, byte [] datas) {
		try {
			writer.write(datas);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	private static byte[] ListToArrayByte(ArrayList<Byte> datas) {
		byte [] result = new byte[datas.size()];
		for (int i = 0; i< result.length; i++) {
			result[i] = datas.get(i);
		}
		return result;
	}
}
