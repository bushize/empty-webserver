import java.io.File;


public class FileSystem {

	public File file;
	
	public boolean isExisting(String dir) {
		file = new File(dir);
		return file.exists();
	}

	public boolean isDirectory(String dir) {
		file = new File(dir);
		return file.isDirectory();
	}

	public String[] getFiles(String dir) {
		String[] files = null;
		file = new File(dir);
		if(file.exists() && file.isDirectory()){
			files = file.list();
		}
		
		return files;
	}

}
