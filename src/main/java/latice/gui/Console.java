package latice.gui;

public class Console {
	//source : https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	
	public static void message(String text) {
		System.out.println(text);
	}

	public static final String SEPARATOR_LINE = "------------------------------------------------------";

	public static void title(String text) {
		message(SEPARATOR_LINE);
		message(text);
		message(SEPARATOR_LINE);
	}

}
