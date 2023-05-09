package ru.erlikh.groovyprojectwork.service

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.erlikh.groovyprojectwork.dao.PositionRepository
import ru.erlikh.groovyprojectwork.entity.Position
import ru.erlikh.groovyprojectwork.service.interfaces.EntityService

import static ru.erlikh.groovyprojectwork.utils.Utils.getMd5Hash
import static ru.erlikh.groovyprojectwork.utils.Utils.getNowTime

@Service
class PositionServiceImpl implements EntityService<Position> {

    @Value('${position.filename}')
    private final String positionFileName

    @Autowired
    private PositionRepository positionRepository

    @Override
    Position findById(Long id) {
        def optional = positionRepository.findById(id)
        if (optional.isPresent()) {
            return optional.get()
        } else {
            return null
        }
    }

    @Override
    List<Position> findAll() {
        return positionRepository.findAll()
    }

    @Override
    Position save(Position position) {
        return positionRepository.save(position)
    }

    @Override
    List<Position> saveAll(List<Position> positions) {
        return positionRepository.saveAll(positions)
    }

    @Override
    void delete(Position position) {
        positionRepository.delete(position)
    }

    @Override
    void updateData() {
        final URL URL = getClass().getResource("/files/${positionFileName}")

        def timestampNow = getNowTime()
        def slurper = new JsonSlurper()
        def json = slurper.parseText(URL.text)

        def positionList = json.results

        def positionRespList = [:]

        def positionEditedList = []
        def positionNewList = []
        def positionDeletedList = []

        def positionOldList = positionRepository.findAll()

        positionList.each {
            positionRespList[it.id] = new Position(
                    id: it.id,
                    name: it.name,
                    cbr: it.cbr,
                    organization: it.organization,
                    admParent: it.adm_parent,
                    funcParent: it.func_parent,
                    code: it.code,
                    equipmentTypeCode: it.equipment_type_code,
                    hash: getMd5Hash("${it.id}|${it.parent}|${it.name}"),
                    isDeleted: false
            )
            if (positionOldList[it.id]) {
                if (positionOldList[it.id].hash != positionRespList[it.id].hash) {
                    positionRespList[it.id].modifyTimestamp = timestampNow
                    positionEditedList << positionRespList[it.id]
                }
            } else {
                positionRespList[it.id].insertTimestamp = timestampNow
                positionRespList[it.id].modifyTimestamp = timestampNow
                positionNewList << positionRespList[it.id]
            }
        }


        positionOldList.each { k, v ->
            if (!positionRespList[k]) {
                v.isDeleted = true
                v.modifyTimestamp = timestampNow
                positionDeletedList << v
            }
        }

        if (!positionNewList.isEmpty()) {
            saveAll(positionNewList)
        }

        if (!positionEditedList.isEmpty()) {
            saveAll(positionEditedList)
        }

        if (!positionDeletedList.isEmpty()) {
            saveAll(positionDeletedList)
        }
    }
}
