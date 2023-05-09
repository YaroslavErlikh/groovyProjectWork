package ru.erlikh.groovyprojectwork.utils

import org.apache.commons.codec.digest.DigestUtils

import java.time.LocalDateTime

class Utils {

    static String getMd5Hash(String str) {
        return DigestUtils.md5Hex(str)
    }

    static LocalDateTime getNowTime() {
        return LocalDateTime.now()
    }
}
