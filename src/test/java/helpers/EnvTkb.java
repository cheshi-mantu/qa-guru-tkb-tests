package helpers;

public class EnvTkb {
    public final static String
        selenide_remote = System.getProperty("selenide_remote","null"),
        url = System.getProperty("url", "https://www.tinkoff.ru");
}

