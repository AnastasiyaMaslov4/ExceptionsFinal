
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/** Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки

дата_рождения - строка формата dd.mm.yyyy

номер_телефона - целое беззнаковое число без форматирования

пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
*/

public class FinalTask {

    public static void enterData() throws GenderException, BirthdayException, IOException {
        String regexBirthday = "\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d";
        String regexTel = "^\\d{7,11}$";
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите фамилию, имя, отчество, дату рождения, номер телефона, пол через пробел");
        String data = scan.nextLine();
        String [] dataArr = data.split(" ");
        scan.close();
        String birthday = "";
        String tel = "";
        String gender = "";
        if(validateData(dataArr) == -1) System.out.println("Введено меньше данных");
        else if (validateData(dataArr) == -2) System.out.println("Введено больше данных");
        else{
        for (int i = 0; i < dataArr.length; i++) {
            if(Pattern.matches(regexTel, dataArr[i])) {
                tel = dataArr[i];
                System.out.println("Tel: " + tel);
            }
            else if(Pattern.matches(regexBirthday, dataArr[i])) {
                birthday = dataArr[i];
                if (!validateBirthday(birthday)) throw new BirthdayException();
                System.out.println("Birth: " + birthday);
            }
            else if(dataArr[i].length() == 1) {
                if (dataArr[i].equals("f") | dataArr[i].equals("m")) {
                gender = dataArr[i];
                System.out.println(gender);
                }
                else throw new GenderException();
            }
            else {
                sb.append(dataArr[i] + " ");
            }
        }
        String[] fullName = String.valueOf(sb).split(" ");
        String lastName = fullName[0];
        String firstName = fullName[1];
        String patromymic = fullName[2];
        System.out.println(firstName + lastName + patromymic);
        String fileName = lastName + ".txt";
        StringBuilder buildData = new StringBuilder();
        buildData.append(lastName + " ");
        buildData.append(firstName + " ");
        buildData.append(patromymic + " ");
        buildData.append(birthday + " ");
        buildData.append(tel + " ");
        buildData.append(gender);
        String finalData = buildData.toString();
        writeData(finalData, fileName);



    }

    }

    public static int validateData(String[] arr) {
        if(arr.length < 6) return -1;
        else if(arr.length > 6) return -2;
        else return 0;
    }

    public static boolean validateBirthday(String data) {
        String [] birthdayArr = data.split(".");
        if (Integer.parseInt(birthdayArr[0]) > 31) {
            return false;
        }
        else if(Integer.parseInt(birthdayArr[1]) > 12) {
            return false;
        }
        else if(Integer.parseInt(birthdayArr[2]) > 2023) {
            return false;
        }
        return true;
    }

        public static void writeData(String data, String fileName) throws IOException {
            try {
            File file = new File("C://" + fileName);
            if (file.exists()) {
                FileWriter fw = new FileWriter(file, true);
                fw.write(data);
                fw.append("\n");
                fw.close();
            }
            else {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                fw.write(data);
                fw.append("\n");
                fw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            
        }
    

    public static void main(String[] args) throws Exception {
        try {
            enterData();
        } catch (Exception e) {
           e.getMessage();
        }
       
    }
    
}
