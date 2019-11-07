package plataforma1.util;


public class StringUtils {

    public static String stripNonPrintable(String src) {
        if (src == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char ch = src.charAt(i);
            if (ch > 31 || ch == 9) {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }
}
