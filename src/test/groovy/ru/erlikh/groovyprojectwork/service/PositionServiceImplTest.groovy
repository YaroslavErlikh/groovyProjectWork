package ru.erlikh.groovyprojectwork.service

import org.mockito.Mock
import ru.erlikh.groovyprojectwork.dao.PositionRepository
import ru.erlikh.groovyprojectwork.entity.Position
import spock.lang.Specification

import java.time.LocalDateTime

class PositionServiceImplTest extends Specification {

    @Mock
    private PositionServiceImpl service

    private Position position

    def setup() {
        PositionRepository positionRepository = Stub(PositionRepository)

        def time = LocalDateTime.now()
        position = new Position(
                id: 628449,
                name: "05400.0026.Специалист Бюро Пропусков.813682",
                cbr: null,
                organization: 30496,
                admParent: 358322,
                funcParent: null,
                code: null,
                equipmentTypeCode: null,
                isDeleted: false,
                insertTimestamp: time,
                modifyTimestamp: time
        )

        List<Position> list = []
        list << position

        Optional<Position> opt = Optional.of(position)

        positionRepository.save(position) >> position
        positionRepository.findById(628449) >> opt
        positionRepository.findAll() >> list
        positionRepository.saveAll() >> list
        service = new PositionServiceImpl(positionRepository: positionRepository)
    }

    def "Save"() {
        given:
        service.save(position)
        def positions = service.findAll()
        def emp = service.findById(628449)

        expect:
        positions.size() == 1
        positions.get(0).id == 628449
        emp.id == 628449
    }

    def "SaveAll"() {
        given:
        def positions = []
        positions << position
        service.saveAll(positions)
        def findAll = service.findAll()

        expect:
        findAll.size() == 1
        findAll.get(0).id == 628449
    }
}
