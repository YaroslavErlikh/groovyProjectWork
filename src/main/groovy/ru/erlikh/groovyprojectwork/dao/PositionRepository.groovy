package ru.erlikh.groovyprojectwork.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.erlikh.groovyprojectwork.entity.Position

@Repository
interface PositionRepository extends JpaRepository<Position, Long> {

}