package ru.erlikh.groovyprojectwork.entity

import groovy.transform.Canonical
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.time.LocalDateTime

@Entity
@Canonical
@Table(name = "organizations")
class Organization {

    @Id
    @Column(name = "id")
    Long id

    @Column(name = "name")
    String name

    @Column(name = "parent")
    Long parent

    @Column(name = "hash")
    String hash

    @Column(name = "is_deleted")
    Boolean isDeleted

    @Column(name = "insert_timestamp")
    LocalDateTime insertTimestamp

    @Column(name = "modify_timestamp")
    LocalDateTime modifyTimestamp
}
