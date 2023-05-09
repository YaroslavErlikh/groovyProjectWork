package ru.erlikh.groovyprojectwork.controller.dto

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class PositionDto {

    Long id
    String name
    String cbr
    Integer organization
    Integer admParent
    Integer funcParent
    String code
    String equipmentTypeCode
    String hash
    Boolean isDeleted
    LocalDateTime insertTimestamp
    LocalDateTime modifyTimestamp
}
