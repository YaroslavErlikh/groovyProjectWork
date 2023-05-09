package ru.erlikh.groovyprojectwork.controller.dto

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class EmploymentDto {

    Long uid
    Long id
    String userName
    String firstName
    String lastName
    String middleName
    String employeeNumber
    String email
    Boolean isDismissed
    String dateOfBirth
    Integer positionId
    Integer admManagerEmployeeId
    Integer funcManagerEmployeeId
    Boolean isBasic
    String e1Phone
    String m1Phone
    String hash
    Boolean isDeleted
    LocalDateTime insertTimestamp
    LocalDateTime modifyTimestamp
}
