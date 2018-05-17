package util;

import java.util.List;

public class SECRETS {

    private static String getToken() {
        try {
           List<String> in = readInTxtFile.Read("../tokenP.txt");
           return in.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String TOKEN =getToken();
}
