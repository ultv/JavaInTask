
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Класс извлечения данных из properties файла.
 * @author Седов Александр.
 */
public class PropertiesReader {

    private String fio = "Нет данных";
    private String dob = "Нет данных";
    private String phone = "Нет данных";
    private String email = "Нет данных";
    private String skype = "Нет данных";
    private String avatar = "Нет данных";
    private String[] target = {"Нет данных"};
    private String[] expirience = {"Нет данных"};
    private String[] education = {"Нет данных"};
    private String[] certificates = {"Нет данных"};
    private String[] skills = {"Нет данных"};
    private String[] exemple = {"Нет данных"};

    /**
     * Конструктор класса.
     * @param fileName - принимает имя properties файла.
     * @param delimiter - принимает разделитель, используемый при записи
     *                  нескольких "значений"  для одного "ключа".
     */
    public PropertiesReader(String fileName, String delimiter){

        Properties config = initConfig(fileName);

        fio = validateProperty(config, "FIO");
        dob = validateProperty(config, "DOB");
        phone = validateProperty(config, "phone");
        email = validateProperty(config, "email");
        skype = validateProperty(config, "skype");
        avatar = validateProperty(config, "avatar");

        target = validateProperty(config, "target").split(delimiter);
        expirience = validateProperty(config, "expirience").split(delimiter);
        education = validateProperty(config, "education").split(delimiter);
        certificates = validateProperty(config, "certificates").split(delimiter);
        skills = validateProperty(config, "skills").split(delimiter);
        exemple = validateProperty(config, "exemple").split(delimiter);
    }

    public String getFio() { return this.fio; }

    public String getDob() { return this.dob; }

    public String getPhone() { return this.phone; }

    public String getEmail() { return this.email; }

    public String getSkype() { return this.skype; }

    public String getAvatar() { return this.avatar; }

    public String[] getTarget() { return this.target; }

    public String[] getExpirience() { return this.expirience; }

    public String[] getEducation() { return this.education; }

    public String[] getCertificates() { return this.certificates; }

    public String[] getSkills() { return this.skills; }

    public String[] getExemple() { return this.exemple; }

    /**
     * Метод загрузки данных из properties файла.
     * @param fileName - принимает имя properties файла.
     * @return - объект с загруженными значениями.
     */
    public Properties initConfig(String fileName) {

        Properties config = new Properties();

        try (FileInputStream file = new FileInputStream(fileName);
                InputStreamReader input = new InputStreamReader(file, "UTF-8")) {

            config.load(input);

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return config;
    }

    /**
     * Метод проверки наличия требуемой пары "ключ-значение" в properties файле.
     * @param config - принимает объект со значениями.
     * @param key - принимает "ключ".
     * @return - возвращает текущее или дефолтное значение.
     */
    public String validateProperty(Properties config, String key) {

        String value = config.getProperty(key);

        if ((value != null) && (!value.isEmpty()))
            return value;
        else return "Нет данных";
    }
}
