
public class Logger {

	private static StringBuilder logs = new StringBuilder();
	
	public String getLogs() {
		return logs.toString();
	}

	public void log(String string) {
		logs.append(string + "\n");
	}

}
