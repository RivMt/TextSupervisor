package io.rivmt.utils.utility

class Log {

    companion object {
        
        //0: All, 1: Over Info, 2: Over Debug, 3: Over warning
        private const val logLevel = 1
        
        //Message
        fun m(message: String) {
            println("$message")
        }
        
        fun h(message: String) {
            print("$message")
        }
        
        //With TAG
        fun v(tag: String, message: String) {
            if (logLevel <= 0) {
                println("($tag): $message");
            }
        }

        fun i(tag: String, message: String) {
            if (logLevel <= 1) {
                println("[주의] ($tag): $message");
            }
        }
        
        fun d(tag: String, message: String) {
            if (logLevel <= 2) {
                println("[디버그] ($tag): $message");
            }
        }

        fun w(tag: String, message: String) {
            if (logLevel <= 3) {
                println("[경고] ($tag): $message");
            }
        }

        fun e(tag: String, message: String) {
            println("[오류] ($tag): $message");
        }
        
        //With number
        fun v(line: Int, message: String) {
            if (logLevel <= 0) {
                println("$line: $message");
            }
        }

        fun i(line: Int, message: String) {
            if (logLevel <= 1) {
                println("[주의] $line: $message");
            }
        }
        
        fun d(line: Int, message: String) {
            if (logLevel <= 2) {
                println("[디버그] $line: $message");
            }
        }

        fun w(line: Int, message: String) {
            if (logLevel <= 3) {
                println("[경고] $line: $message");
            }
        }

        fun e(line: Int, message: String) {
            println("[오류] $line: $message");
        }

        //Only log
        fun v(message: String) {
            if (logLevel <= 0) {
                println(message);
            }
        }

        fun i(message: String) {
            if (logLevel <= 1) {
                println("[주의] $message");
            }
        }
        
        fun d(message: String) {
            if (logLevel <= 2) {
                println("[디버그] $message");
            }
        }

        fun w(message: String) {
            if (logLevel <= 3) {
                println("[경고] $message");
            }
        }

        fun e(message: String) {
            println("[오류] $message");
        }
    }

}
