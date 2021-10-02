class Utility {
    companion object {
        fun countChar(target: String, find: String): Int {
            var t = target;
            var count: Int = 0;
            while (t.contains(find)) {
                t = target.replaceFirst(find, "");
                count++;
            }
            Log.i("E");

            return count;
        }
    }
}