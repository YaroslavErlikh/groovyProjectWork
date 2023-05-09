package ru.erlikh.groovyprojectwork.service.dtoServices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.erlikh.groovyprojectwork.controller.dto.PositionDto
import ru.erlikh.groovyprojectwork.entity.Position
import ru.erlikh.groovyprojectwork.service.interfaces.EntityService

@Service
class PositionDtoService {

    @Autowired
    private EntityService positionServiceImpl

    List<PositionDto> findAll() {
        def result = []
        def list = positionServiceImpl.findAll()
        list.each {
            result << new Position(
                    id: it.id,
                    name: it.name,
                    cbr: it.cbr,
                    organization: it.organization,
                    admParent: it.admParent,
                    funcParent: it.funcParent,
                    code: it.code,
                    equipmentTypeCode: it.equipmentTypeCode,
                    hash: it.hash,
                    isDeleted: it.isDeleted,
                    insertTimestamp: it.insertTimestamp,
                    modifyTimestamp: it.modifyTimestamp
            )
        }
        return result
    }

    void updateData() {
        positionServiceImpl.updateData()
    }
}
