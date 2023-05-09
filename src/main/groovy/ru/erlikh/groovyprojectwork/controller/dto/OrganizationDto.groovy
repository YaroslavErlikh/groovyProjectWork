package ru.erlikh.groovyprojectwork.controller.dto

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class OrganizationDto {

    Long id
    Integer parent
    String name
    String hash
    Boolean isDeleted
    LocalDateTime insertTimestamp
    LocalDateTime modifyTimestamp
}
