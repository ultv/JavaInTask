package com.itplace;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

/**
 * Класс формирования и записи html-шаблона в файл.
 * @autor Седов Александр.
 */
public class HtmlCreater {

    private StringBuilder header = new StringBuilder("<!DOCTYPE html>" +
                            "<html lang=\"en\">" +
                                "<head>" +
                                    "<meta charset=\"UTF-8\">" +
                                    "<!-- Bootstrap CSS -->" +
                                    "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\"" +
                                    "integrity=\"sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB\" crossorigin=\"anonymous\">" +
                                    "<title>Тестовое задание Java. Выполнил: Седов.А.П.</title>" +
                                "</head>");

    private StringBuilder body = new StringBuilder("<body>" +
                                "<div class=\"row justify-content-center\">" +
                                    "<div class=\"col\">" +
                                        "<h3 class=\"text-center\">Резюме</h3>" +
                                        "<p class=\"text-center\"><b>на должность Java-стажер</b></p>" +
                                    "</div>" +
                                "</div>");

    private StringBuilder foother = new StringBuilder("</body></html>");

    /**
     * Конструктор класса.
     * @param config - принимает объект с данными.
     * @param fileName - принимает имя файла для записи.
     */
    public HtmlCreater(PropertiesReader config, String fileName) {

        body.append(createInfo(config));
        body.append(createBlock("Цель", config.getTarget(), false));
        body.append(createBlock("Опыт", config.getExpirience(), false));
        body.append(createBlock("Образование", config.getEducation(), false));
        body.append(createBlock("Доп. образование и курсы", config.getCertificates(), false));
        body.append(createBlock("Навыки", config.getSkills(), false));
        body.append(createBlock("Примеры кода", config.getExemple(), true));

        writeHtml(fileName);
    }

    /**
     * Метод формирования html-блока "информация".
     * @param config - принимает объект с данными.
     * @return - возвражает сформированный html-блок.
     */
    public String createInfo(PropertiesReader config) {

        return "<div class=\"row justify-content-center\">" +
                    "<div class=\"col-md-2\">" +
                        "<div class=\"row\"><b>ФИО:</b></div>" +
                        "<div class=\"row\"><b>Дата рождения:</b></div>" +
                        "<div class=\"row\"><b>Телефон:</b></div>" +
                        "<div class=\"row\"><b>e-mail:</b></div>" +
                        "<div class=\"row\"><b>Skype:</b></div>" +
                    "</div>" +
                    "<div class=\"col-md-3\">" +
                        "<div class=\"row\">" + config.getFio() + "</div>" +
                        "<div class=\"row\">" + config.getDob() + "</div>" +
                        "<div class=\"row\">" + config.getPhone() + "</div>" +
                        "<div class=\"row\">" + config.getEmail() + "</div>" +
                        "<div class=\"row\">" + config.getSkype() + "</div>" +
                    "</div>" +
                    "<div class=\"col-md-5\">" +
                        "<img src =\"" + config.getAvatar() +  "\" width = \"300\" alt = \"Фото\">" +
                    "</div>" +
                "</div>";
    }

    /**
     * Метод формирования html-блоков.
     * @param name - принимает название блока.
     * @param keys - принимает массив значений.
     * @param link - принимает флаг гиперссылки.
     * @return
     */
    public String createBlock(String name, String[] keys, boolean link) {

        String mult = "";

        if (link)
            formatLink(keys);

        if (keys.length > 0)
            mult = formatString(keys);

        return  "<div class=\"row justify-content-center\">" +
                    "<div class=\"col-md-11\">" +
                        "<b>" + name + ":</b>" +
                            "<p>" + mult + "</p>" +
                        "</div>" +
                    "</div>";
    }

    /**
     * Метод формирования гиперссылок.
     * @param keys - принимает массив значений.
     */
    public void formatLink(String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != "Нет данных")
                keys[i] = "<a href=" + keys[i] + ">Репозиторий на GitHub</a>";
        }
    }

    /**
     * Метод формирования строки из массива значений.
     * @param keys - принимает массив значений.
     * @return - возвращает строку значений с разделителями.
     */
    public String formatString(String [] keys) {
        String mult = "";

        for (int i = 0; i < keys.length; i++) {
            mult += keys[i] + "<br>";
        }

        return mult;
    }

    /**
     * Метод записи данных в html файл.
     * @param fileName - имя создаваемого файла.
     */
    public void writeHtml(String fileName) {

        try (FileOutputStream file = new FileOutputStream(fileName);
                OutputStreamWriter output = new OutputStreamWriter(file, "UTF-8")) {

            output.write(header.toString());
            output.write(body.toString());
            output.write(foother.toString());

            output.flush();

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }
}
