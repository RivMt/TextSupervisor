public class Utility {

    public static int countChar(String target, String find) {
        String t = target;
        int count = 0;
        while (t.contains(find)) {
            t = target.replaceFirst(find, "");
            count++;
        }
        Log.i("E");

        return count;
    }

}
