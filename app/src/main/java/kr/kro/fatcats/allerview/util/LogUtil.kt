package kr.kro.fatcats.allerview.util

import android.util.Log

object LogUtil {

    enum class LogType {
        I,
        V,
        E,
        D,
        W
    }

    /*
     * 로그 표시 유무 true : 로그 전체 출력 안함 Level 무시 false : 다 보여줌
     */
    private val debugMode = true
    
    const val DEBUG_LEVEL_0 = 0
    const val DEBUG_LEVEL_1 = 1
    const val DEBUG_LEVEL_2 = 2
    const val DEBUG_LEVEL_3 = 3
    
    private val DEBUG_LEVEL = DEBUG_LEVEL_0

    fun v(level: Int, message: String) {
        if (debugMode) {
           log(level, message , LogType.V)
        }
    }

    fun i(level: Int, message: String) {
        if (debugMode) {
            if (debugMode) {
                log(level, message ,LogType.I)
            }
        }
    }

    fun d(level: Int, message: String) {
        if (debugMode) {
            if (debugMode) {
                log(level, message ,LogType.D)
            }
        }
    }

    fun w(level: Int, message: String) {
        if (debugMode) {
            if (debugMode) {
                log(level, message ,LogType.W)
            }
        }
    }

    fun e(level: Int, message: String) {
        if (debugMode) {
            if (debugMode) {
                log(level, message ,LogType.E)
            }
        }
    }

    private fun log(level : Int, message: String, type : LogType){
        if (level > DEBUG_LEVEL) {
            var tag = ""
            val traceElement = Throwable().stackTrace[1]
            val temp = traceElement.className
            var logTag = ""
            if (temp != null) {
                val lastDotPos = temp.lastIndexOf(".")
                if (lastDotPos + 1 >= 0 && lastDotPos + 1 <= temp.length) {
                    tag = temp.substring(lastDotPos + 1)
                }
            }
            val methodName = traceElement.methodName
            val lineNumber = traceElement.lineNumber
            when(type){
                LogType.V -> { Log.v(logTag, "[$tag] $methodName()[$lineNumber] >> $message") }
                LogType.W -> { Log.w(logTag, "[$tag] $methodName()[$lineNumber] >> $message") }
                LogType.D -> { Log.d(logTag, "[$tag] $methodName()[$lineNumber] >> $message") }
                LogType.I -> { Log.i(logTag, "[$tag] $methodName()[$lineNumber] >> $message") }
                LogType.E -> { Log.e(logTag, "[$tag] $methodName()[$lineNumber] >> $message") }
            }
        }
    }

}