package ru.erlikh.groovyprojectwork.service

import org.mockito.Mock
import ru.erlikh.groovyprojectwork.dao.EmploymentRepository
import ru.erlikh.groovyprojectwork.entity.Employment
import spock.lang.Specification

import java.time.LocalDateTime

class EmploymentServiceImplTest extends Specification {

    @Mock
    private EmploymentServiceImpl service

    private Employment employment

    def setup() {
        EmploymentRepository employmentRepository = Stub(EmploymentRepository)

        def time = LocalDateTime.now()
        employment = new Employment(
                uid: 86,
                id: 86,
                userName: "SYARTSEVA",
                firstName: "Светлана",
                lastName: "Ярцева",
                middleName: "Валентиновна",
                employeeNumber: "45419",
                email: "SYartseva@vlg.beeline.ru",
                isDismissed: false,
                dateOfBirth: "1973-03-19",
                positionId: 1389177,
                admManagerEmployeeId: 171195,
                funcManagerEmployeeId: 171195,
                isBasic: true,
                e1Phone: null,
                m1Phone: "79033150021",
                isDeleted: false,
                insertTimestamp: time,
                modifyTimestamp: time
        )

        List<Employment> list = []
        list << employment

        Optional<Employment> opt = Optional.of(employment)

        employmentRepository.save(employment) >> employment
        employmentRepository.findById(86) >> opt
        employmentRepository.findAll() >> list
        employmentRepository.saveAll() >> list
        service = new EmploymentServiceImpl(employeeRepository: employmentRepository)
    }

    def "Save"() {
        given:
        service.save(employment)
        def employments = service.findAll()
        def emp = service.findById(86)

        expect:
        employments.size() == 1
        employments.get(0).uid == 86
        emp.uid == 86
    }

    def "SaveAll"() {
        given:
        def employments = []
        employments << employment
        service.saveAll(employments)
        def findAll = service.findAll()

        expect:
        findAll.size() == 1
        findAll.get(0).uid == 86
    }
}
