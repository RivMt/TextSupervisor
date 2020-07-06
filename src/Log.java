public class Log {

    public static void v(int line, String message) {
        System.out.println(line +": "+message);
    }

    public static void i(int line, String message) {
        System.out.println("[주의] "+ line +": "+message);
    }

    public static void w(int line, String message) {
        System.out.println("[경고] "+ line +": "+message);
    }

    public static void e(int line, String message) {
        System.out.println("[오류] "+ line +": "+message);
    }

    public static void v(String message) {
        System.out.println(message);
    }

    public static void i(String message) {
        System.out.println("[주의] "+message);
    }

    public static void w(String message) {
        System.out.println("[경고] "+message);
    }

    public static void e(String message) {
        System.out.println("[오류] "+message);
    }

}
