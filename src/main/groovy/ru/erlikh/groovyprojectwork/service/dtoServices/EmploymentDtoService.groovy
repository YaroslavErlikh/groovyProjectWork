package ru.erlikh.groovyprojectwork.service.dtoServices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.erlikh.groovyprojectwork.controller.dto.EmploymentDto
import ru.erlikh.groovyprojectwork.service.interfaces.EntityService

@Service
class EmploymentDtoService {

    @Autowired
    private EntityService employmentServiceImpl

    List<EmploymentDto> findAll() {
        def result = []
        def list = employmentServiceImpl.findAll()
        list.each { employment ->
            result << new EmploymentDto(
                    uid: employment.uid,
                    id: employment.id,
                    userName: employment.userName,
                    firstName: employment.firstName,
                    lastName: employment.lastName,
                    middleName: employment.middleName,
                    employeeNumber: employment.employeeNumber,
                    email: employment.email,
                    isDismissed: employment.isDismissed,
                    dateOfBirth: employment.dateOfBirth,
                    positionId: employment.positionId,
                    admManagerEmployeeId: employment.admManagerEmployeeId,
                    funcManagerEmployeeId: employment.funcManagerEmployeeId,
                    isBasic: employment.isBasic,
                    e1Phone: employment.e1Phone,
                    m1Phone: employment.m1Phone,
                    hash: employment.hash,
                    isDeleted: employment.isDeleted,
                    insertTimestamp: employment.insertTimestamp,
                    modifyTimestamp: employment.modifyTimestamp
            )
        }
        return result
    }

    void updateData() {
        employmentServiceImpl.updateData()
    }
}
