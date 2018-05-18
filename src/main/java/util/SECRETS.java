package util;

import java.util.List;

/**
 * Aufbau tokenP.txt
 *
 * discord_token //0
 * twitter_consumerKey (API Key) //1
 * twitter_consumerSecret (API Secret) //2
 * twitter_accessToken //3
 * twitter_accessTokenSecret //4
 */

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

    private static String getTwitterApiKey() {
        try {
            List<String> in = readInTxtFile.Read("../tokenP.txt");
            return in.get(1);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getTwitterApiSecret() {
        try {
            List<String> in = readInTxtFile.Read("../tokenP.txt");
            return in.get(2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getTwitterAccessToken() {
        try {
            List<String> in = readInTxtFile.Read("../tokenP.txt");
            return in.get(3);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getTwitterAccessTokenSecret() {
        try {
            List<String> in = readInTxtFile.Read("../tokenP.txt");
            return in.get(4);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String TOKEN = getToken();
    public static String TWITTER_API_KEY = getTwitterApiKey();
    public static String TWITTER_API_SECRET = getTwitterApiSecret();
    public static String TWITTER_ACCESS_TOKEN = getTwitterAccessToken();
    public static String TWITTER_ACCESS_TOKEN_SECRET = getTwitterAccessTokenSecret();
}
