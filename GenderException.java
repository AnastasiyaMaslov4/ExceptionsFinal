public class GenderException extends Exception {

    public void GenderException() {
    }

    @Override
    public String getMessage() {
        String msg = "Неверно введен пол";
        return msg;
    }
}
