import java.io.File;


public class Directory {

	public File file;
	
	public boolean isExisting(String dir) {
		file = new File(dir);		
		return file.exists();
	}

	public boolean isDirectory(String dir) {
		file = new File(dir);
		return file.isDirectory();
	}

}
