package ru.erlikh.groovyprojectwork.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.erlikh.groovyprojectwork.entity.Organization

@Repository
interface OrganizationRepository extends JpaRepository<Organization, Long> {

}