package ru.erlikh.groovyprojectwork.service

import org.mockito.Mock
import ru.erlikh.groovyprojectwork.dao.OrganizationRepository
import ru.erlikh.groovyprojectwork.entity.Organization
import spock.lang.Specification

import java.time.LocalDateTime

class OrganizationServiceImplTest extends Specification {

    @Mock
    private OrganizationServiceImpl service

    private Organization organization

    def setup() {
        OrganizationRepository organizationRepository = Stub(OrganizationRepository)

        def time = LocalDateTime.now()
        organization = new Organization(
                id: 81,
                name: "Группа компаний exampleКом",
                parent: null,
                isDeleted: false,
                insertTimestamp: time,
                modifyTimestamp: time
        )

        List<Organization> list = []
        list << organization

        Optional<Organization> opt = Optional.of(organization)

        organizationRepository.save(organization) >> organization
        organizationRepository.findById(81) >> opt
        organizationRepository.findAll() >> list
        organizationRepository.saveAll() >> list
        service = new OrganizationServiceImpl(organizationRepository: organizationRepository)
    }

    def "Save"() {
        given:
        service.save(organization)
        def positions = service.findAll()
        def emp = service.findById(81)

        expect:
        positions.size() == 1
        positions.get(0).id == 81
        emp.id == 81
    }

    def "SaveAll"() {
        given:
        def positions = []
        positions << organization
        service.saveAll(positions)
        def findAll = service.findAll()

        expect:
        findAll.size() == 1
        findAll.get(0).id == 81
    }
}
