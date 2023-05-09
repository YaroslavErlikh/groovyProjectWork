package ru.erlikh.groovyprojectwork.service

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.erlikh.groovyprojectwork.dao.OrganizationRepository
import ru.erlikh.groovyprojectwork.entity.Organization
import ru.erlikh.groovyprojectwork.service.interfaces.EntityService

import static ru.erlikh.groovyprojectwork.utils.Utils.getMd5Hash
import static ru.erlikh.groovyprojectwork.utils.Utils.getNowTime

@Service
class OrganizationServiceImpl implements EntityService<Organization> {

    @Value('${organization.filename}')
    private final String organizationFileName

    @Autowired
    private OrganizationRepository organizationRepository

    @Override
    Organization findById(Long id) {
        def optional = organizationRepository.findById(id)
        if (optional.isPresent()) {
            return optional.get()
        } else {
            return null
        }
    }

    @Override
    List<Organization> findAll() {
        return organizationRepository.findAll()
    }

    @Override
    Organization save(Organization organization) {
        return organizationRepository.save(organization)
    }

    @Override
    List<Organization> saveAll(List<Organization> organizations) {
        return organizationRepository.saveAll(organizations)
    }

    @Override
    void delete(Organization organization) {
        organizationRepository.delete(organization)
    }

    @Override
    void updateData() {
        final URL URL = getClass().getResource("/files/${organizationFileName}")

        def timestampNow = getNowTime()
        def slurper = new JsonSlurper()
        def json = slurper.parseText(URL.text)

        def organizationList = json.results

        def organizationRespList = [:]

        def organizationEditedList = []
        def organizationNewList = []
        def organizationDeletedList = []

        def organizationOldList = organizationRepository.findAll()

        organizationList.each {
            organizationRespList[it.id] = new Organization(
                    id: it.id,
                    parent: it.parent,
                    name: it.name,
                    hash: getMd5Hash("${it.id}|${it.parent}|${it.name}"),
                    isDeleted: false
            )
            if (organizationOldList[it.id]) {
                if (organizationOldList[it.id].hash != organizationRespList[it.id].hash) {
                    organizationRespList[it.id].modifyTimestamp = timestampNow
                    organizationEditedList << organizationRespList[it.id]
                }
            } else {
                organizationRespList[it.id].insertTimestamp = timestampNow
                organizationRespList[it.id].modifyTimestamp = timestampNow
                organizationNewList << organizationRespList[it.id]
            }
        }


        organizationOldList.each { k, v ->
            if (!organizationRespList[k]) {
                v.isDeleted = true
                v.modifyTimestamp = timestampNow
                organizationDeletedList << v
            }
        }

        if (!organizationNewList.isEmpty()) {
            saveAll(organizationNewList)
        }

        if (!organizationEditedList.isEmpty()) {
            saveAll(organizationEditedList)
        }

        if (!organizationDeletedList.isEmpty()) {
            saveAll(organizationDeletedList)
        }
    }
}
