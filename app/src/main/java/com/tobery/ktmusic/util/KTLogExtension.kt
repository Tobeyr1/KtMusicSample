package com.tobery.ktmusic.util

import android.util.Log
import com.google.gson.Gson

fun Any.printLog() {
    if (this is Throwable) {
        LogTool.w(this.javaClass.simpleName, message, this)
    } else {
        LogTool.i(this.javaClass.simpleName, toString())
    }
}

fun Any.toJsonString(): String {
    return Gson().toJson(this)
}

object LogTool {
    private var enableLog = true
    fun v(tag: String?, msg: String?) {
        var message = msg
        if (enableLog && tag != null && message != null) {
            val segmentSize = 3 * 1024
            val length = message.length.toLong()
            if (length > segmentSize) {
                while (message!!.length > segmentSize) {
                    val logContent = message.substring(0, segmentSize)
                    message = message.replace(logContent, "")
                    Log.v(tag, logContent)
                }
            }
            Log.v(tag, message)
        }
    }

    fun v(tag: String?, message: String?, tr: Throwable?) {
        var msg = message
        if (enableLog && tag != null && msg != null && tr != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.v(tag, logContent, tr)
                }
            }
            Log.v(tag, msg, tr)
        }
    }

    fun d(tag: String?, message: String?) {
        var msg = message
        if (enableLog && tag != null && msg != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.d(tag, logContent)
                }
            }
            Log.d(tag, msg)
        }
    }

    fun d(tag: String?, message: String?, tr: Throwable?) {
        var msg = message
        if (enableLog && tag != null && msg != null && tr != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.d(tag, logContent, tr)
                }
            }
            Log.d(tag, msg, tr)
        }
    }

    fun i(tag: String?, message: String?) {
        var msg = message
        if (enableLog && tag != null && msg != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.i(tag, logContent)
                }
            }
            Log.i(tag, msg)
        }
    }

    fun i(tag: String?, message: String?, tr: Throwable?) {
        var msg = message
        if (enableLog && tag != null && msg != null && tr != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.i(tag, logContent, tr)
                }
            }
            Log.i(tag, msg, tr)
        }
    }

    fun w(tag: String?, message: String?) {
        var msg = message
        if (enableLog && tag != null && msg != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.w(tag, logContent)
                }
            }
            Log.w(tag, msg)
        }
    }

    fun w(tag: String?, message: String?, tr: Throwable?) {
        var msg = message
        if (enableLog && tag != null && msg != null && tr != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.w(tag, logContent, tr)
                }
            }
            Log.w(tag, msg, tr)
        }
    }

    fun e(tag: String?, message: String?) {
        var msg = message
        if (enableLog && tag != null && msg != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.e(tag, logContent)
                }
            }
            Log.e(tag, msg)
        }
    }

    fun e(tag: String?, message: String?, tr: Throwable?) {
        var msg = message
        if (enableLog && tag != null && msg != null && tr != null) {
            val segmentSize = 3 * 1024
            val length = msg.length.toLong()
            if (length > segmentSize) {
                while (msg!!.length > segmentSize) {
                    val logContent = msg.substring(0, segmentSize)
                    msg = msg.replace(logContent, "")
                    Log.e(tag, logContent, tr)
                }
            }
            Log.e(tag, msg, tr)
        }
    }
}