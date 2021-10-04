class Log {

    companion object {
        
        private const val isDebug = true
        
        //With TAG
        fun v(tag: String, message: String) {
            println("($tag): $message");
        }

        fun i(tag: String, message: String) {
            println("[주의] ($tag): $message");
        }
        
        fun d(tag: String, message: String) {
            if (isDebug) {
                println("[디버그] ($tag): $message");
            }
        }

        fun w(tag: String, message: String) {
            println("[경고] ($tag): $message");
        }

        fun e(tag: String, message: String) {
            println("[오류] ($tag): $message");
        }
        
        //With number
        fun v(line: Int, message: String) {
            println("$line: $message");
        }

        fun i(line: Int, message: String) {
            println("[주의] $line: $message");
        }
        
        fun d(line: Int, message: String) {
            if (isDebug) {
                println("[디버그] $line: $message");
            }
        }

        fun w(line: Int, message: String) {
            println("[경고] $line: $message");
        }

        fun e(line: Int, message: String) {
            println("[오류] $line: $message");
        }

        //Only message
        fun v(message: String) {
            println(message);
        }

        fun i(message: String) {
            println("[주의] $message");
        }
        
        fun d(message: String) {
            if (isDebug) {
                println("[디버그] $message");
            }
        }

        fun w(message: String) {
            println("[경고] $message");
        }

        fun e(message: String) {
            println("[오류] $message");
        }
    }

}
