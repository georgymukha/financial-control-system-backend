package kz.mukha.fcs.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggerUtil {
    fun <T> getLogger(clazz: Class<T>): Logger {
        return LoggerFactory.getLogger(clazz)
    }
}