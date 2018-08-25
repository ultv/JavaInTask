package com.itplace;

/**
 * Приложение для создания HTML страницы по заданному шаблону,
 * на основе properties файла.
 * @autor Седов Александр.
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        PropertiesReader properties = new PropertiesReader("config.properties", "%");
        HtmlCreater html = new HtmlCreater(properties, "result.html");
    }
}
