package ru.erlikh.groovyprojectwork.service

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.erlikh.groovyprojectwork.dao.EmploymentRepository
import ru.erlikh.groovyprojectwork.entity.Employment
import ru.erlikh.groovyprojectwork.service.interfaces.EntityService

import static ru.erlikh.groovyprojectwork.utils.Utils.getMd5Hash
import static ru.erlikh.groovyprojectwork.utils.Utils.getNowTime

@Service
class EmploymentServiceImpl implements EntityService<Employment> {

    @Value('${employees.filename}')
    private final String employeeFileName

    @Autowired
    private EmploymentRepository employeeRepository

    @Override
    Employment findById(Long id) {
        def optional = employeeRepository.findById(id)
        if (optional.isPresent()) {
            return optional.get()
        } else {
            return null
        }
    }

    @Override
    List<Employment> findAll() {
        return employeeRepository.findAll()
    }

    @Override
    Employment save(Employment employee) {
        return employeeRepository.save(employee)
    }

    @Override
    List<Employment> saveAll(List<Employment> employments) {
        return employeeRepository.saveAll(employments)
    }

    @Override
    void delete(Employment employee) {
        employeeRepository.delete(employee)
    }

    @Override
    void updateData() {
        final URL URL = getClass().getResource("/files/${employeeFileName}")

        def timestampNow = getNowTime()
        def slurper = new JsonSlurper()
        def json = slurper.parseText(URL.text)

        def employeesList = json.results

        def employmentsRespList = [:]

        def employmentsEditedList = []
        def employmentsNewList = []
        def employmentsDeletedList = []

        def employmentsOldList = employeeRepository.findAll()

        employeesList.each { user ->
            user.employee_position_set.each {
                def e1_phone = user.phone_set.findAll {
                    it.phone_type_code == 'E1'
                }.phone_number as String[]
                def m_phone = user.phone_set.findAll {
                    it.phone_type_code == 'M'
                }.phone_number as String[]
                employmentsRespList[it.id] = new Employment(
                        uid: it.id,
                        id: user.id,
                        userName: user.user_name,
                        firstName: user.first_name,
                        lastName: user.last_name,
                        middleName: user.middle_name,
                        employeeNumber: user.employee_number,
                        email: user.email,
                        isDismissed: user.is_dismissed,
                        dateOfBirth: user.date_of_birth,
                        positionId: it.position_id,
                        admManagerEmployeeId: it.adm_manager_employee_id,
                        funcManagerEmployeeId: it.func_manager_employee_id,
                        isBasic: it.is_basic,
                        e1Phone: e1_phone,
                        m1Phone: m_phone,
                        hash: getMd5Hash("""${it.id}|${user.id}|${user.user_name}|${user.first_name}|${user.last_name}|
${user.middle_name}|${user.employee_number}|${user.email}|${user.is_dismissed}|${user.date_of_birth}|${it.position_id}|
${it.adm_manager_employee_id}|${it.func_manager_employee_id}|${it.is_basic}|${e1_phone}|${m_phone}"""),
                        isDeleted: false
                )
                if (employmentsOldList[it.id]) {
                    if (employmentsOldList[it.id].hash != employmentsRespList[it.id].hash) {
                        employmentsRespList[it.id].modifyTimestamp = timestampNow
                        employmentsEditedList << employmentsRespList[it.id]
                    }
                } else {
                    employmentsRespList[it.id].insertTimestamp = timestampNow
                    employmentsRespList[it.id].modifyTimestamp = timestampNow
                    employmentsNewList << employmentsRespList[it.id]
                }
            }
        }

        employmentsOldList.each { k, v ->
            if (!employmentsRespList[k]) {
                v.isDeleted = true
                v.modifyTimestamp = timestampNow
                employmentsDeletedList << v
            }
        }

        if (!employmentsNewList.isEmpty()) {
            saveAll(employmentsNewList)
        }

        if (!employmentsEditedList.isEmpty()) {
            saveAll(employmentsEditedList)
        }

        if (!employmentsDeletedList.isEmpty()) {
            saveAll(employmentsDeletedList)
        }
    }
}
