public class Utility {

    public static int countChar(String target, String find) {
        String t = target;
        int count = 0;
        do {
            if (t.contains(find)) {
                t = target.replaceFirst(find, "");
                count++;
            }
        } while (t.contains(find));

        return count;
    }

}
