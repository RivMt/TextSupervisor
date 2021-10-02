class Log {

    companion object {
        fun v(line: Int, message: String) {
            println("$line: $message");
        }

        fun i(line: Int, message: String) {
            println("[주의] $line: $message");
        }

        fun w(line: Int, message: String) {
            println("[경고] $line: $message");
        }

        fun e(line: Int, message: String) {
            println("[오류] $line: $message");
        }

        fun v(message: String) {
            println(message);
        }

        fun i(message: String) {
            println("[주의] $message");
        }

        fun w(message: String) {
            println("[경고] $message");
        }

        fun e(message: String) {
            println("[오류] $message");
        }
    }

}
