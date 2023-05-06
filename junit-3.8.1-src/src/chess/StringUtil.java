package chess;

public class StringUtil {
	public static String NEWLINE = System.getProperty("line.separator");
	//public static String NEWLINE ="\r\n";
	public static String appendNewLine(String line) {
	
		StringBuilder builder = new StringBuilder();
		builder.append(line);
		builder.append(NEWLINE);
		return builder.toString();
	}
	public static int countChars(String input, char ch) {
		int i;
		int count;
		for (i = 0, count = 0; i < input.length(); i++)
				if (input.charAt(i) == ch)
					count++;
		return count;
	}
	public static boolean isPalindrome(String string) {
		for
			(int forward = 0, backward = string.length() - 1;
			forward < string.length();
			forward++, backward--)
			if (string.charAt(forward) != string.charAt(backward))
				return false;
		return true;		
	}
}
