package ru.erlikh.groovyprojectwork.entity

import groovy.transform.Canonical
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.time.LocalDateTime

@Entity
@Canonical
@Table(name = "employments")
class Employment {

    @Id
    @Column(name = "uid")
    Long uid

    @Column(name = "id")
    Long id

    @Column(name = "user_name")
    String userName

    @Column(name = "first_name")
    String firstName

    @Column(name = "last_name")
    String lastName

    @Column(name = "middle_name")
    String middleName

    @Column(name = "employee_number")
    String employeeNumber

    @Column(name = "email")
    String email

    @Column(name = "is_dismissed")
    Boolean isDismissed

    @Column(name = "date_of_birth")
    String dateOfBirth

    @Column(name = "position_id")
    Integer positionId

    @Column(name = "adm_manager_employee_id")
    Integer admManagerEmployeeId

    @Column(name = "func_manager_employee_id")
    Integer funcManagerEmployeeId

    @Column(name = "is_basic")
    Boolean isBasic

    @Column(name = "e1_phone")
    String[] e1Phone

    @Column(name = "m1_phone")
    String[] m1Phone

    @Column(name = "hash")
    String hash

    @Column(name = "is_deleted")
    Boolean isDeleted

    @Column(name = "insert_timestamp")
    LocalDateTime insertTimestamp

    @Column(name = "modify_timestamp")
    LocalDateTime modifyTimestamp
}
