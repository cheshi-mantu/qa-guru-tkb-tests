package helpers;

public class EnvTkb {
    public final static String
        selenoid_url = System.getProperty("selenoid_url",null),
        url = System.getProperty("url", "https://www.tinkoff.ru");
}

