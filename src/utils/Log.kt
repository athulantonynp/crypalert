package utils

object Log {
    const val ANSI_RESET = "\u001B[0m"
    const val ANSI_BLACK = "\u001B[30m"
    const val ANSI_RED = "\u001B[31m"
    const val ANSI_GREEN = "\u001B[32m"
    const val ANSI_YELLOW = "\u001B[33m"
    const val ANSI_BLUE = "\u001B[34m"
    const val ANSI_PURPLE = "\u001B[35m"
    const val ANSI_CYAN = "\u001B[36m"
    const val ANSI_WHITE = "\u001B[37m"

    //info
    fun i(message: String) {
        println(ANSI_GREEN + " : " + message + ANSI_RESET)
    }

    //error
    fun e(message: String?) {
        println(ANSI_RED + " : " + message + ANSI_RESET)
    }

    //debug
    fun d(message: String) {
        println(ANSI_BLUE + " : " + message + ANSI_RESET)
    }

    //warning
    fun w(message: String) {
        println(ANSI_YELLOW + " : " + message + ANSI_RESET)
    }
}