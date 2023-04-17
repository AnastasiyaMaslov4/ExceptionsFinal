public class BirthdayException extends Exception {
    @Override
    public String getMessage() {
        String msg = "Неверно введена дата рождения. Допустима строка формата dd.mm.yyyy";
        return msg;
    }

}
