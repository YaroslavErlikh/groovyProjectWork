package ru.erlikh.groovyprojectwork.service.dtoServices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.erlikh.groovyprojectwork.controller.dto.OrganizationDto
import ru.erlikh.groovyprojectwork.service.interfaces.EntityService

@Service
class OrganizationDtoService {

    @Autowired
    private EntityService organizationServiceImpl

    List<OrganizationDto> findAll() {
        def result = []
        def list = organizationServiceImpl.findAll()
        list.each {
            result << new OrganizationDto(
                    id: it.id,
                    parent: it.parent,
                    name: it.name,
                    hash: it.hash,
                    isDeleted: it.isDeleted,
                    insertTimestamp: it.insertTimestamp,
                    modifyTimestamp: it.modifyTimestamp
            )
        }
        return result
    }

    void updateData() {
        organizationServiceImpl.updateData()
    }
}
