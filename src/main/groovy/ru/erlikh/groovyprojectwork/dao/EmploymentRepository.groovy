package ru.erlikh.groovyprojectwork.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.erlikh.groovyprojectwork.entity.Employment

@Repository
interface EmploymentRepository extends JpaRepository<Employment, Long> {
}
