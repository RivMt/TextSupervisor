public class Log {

    public static void i(String TAG, String message) {
        System.out.println(TAG+"/ "+message);
    }

    public static void w(String TAG, String message) {
        System.out.println("[경고] "+TAG+"/ "+message);
    }

    public static void e(String TAG, String message) {
        System.out.println("[오류] "+TAG+"/ "+message);
    }

}
