package util;

import core.Main;

import java.util.List;

public class SECRETS {

    private static String getToken() {
        if (Main.Testlauf) {
            try {
                List<String> in = readInTxtFile.Read("../tokenT.txt");
                return in.get(0);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
        try {
           List<String> in = readInTxtFile.Read("../tokenP.txt");
           return in.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } }
    }
    public static String TOKEN =getToken();
}
