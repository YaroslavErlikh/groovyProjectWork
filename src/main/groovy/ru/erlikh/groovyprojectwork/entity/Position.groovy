package ru.erlikh.groovyprojectwork.entity

import groovy.transform.Canonical
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.time.LocalDateTime

@Entity
@Canonical
@Table(name = "positions")
class Position {

    @Id
    @Column(name = "id")
    Long id

    @Column(name = "name")
    String name

    @Column(name = "cbr")
    Integer cbr

    @Column(name = "organization")
    Long organization

    @Column(name = "adm_parent")
    Long admParent

    @Column(name = "func_parent")
    Long funcParent

    @Column(name = "code")
    String code

    @Column(name = "equipment_type_code")
    String equipmentTypeCode

    @Column(name = "hash")
    String hash

    @Column(name = "is_deleted")
    Boolean isDeleted

    @Column(name = "insert_timestamp")
    LocalDateTime insertTimestamp

    @Column(name = "modify_timestamp")
    LocalDateTime modifyTimestamp
}
