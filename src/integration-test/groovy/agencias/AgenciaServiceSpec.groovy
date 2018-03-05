package agencias

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AgenciaServiceSpec extends Specification {

    AgenciaService agenciaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Agencia(...).save(flush: true, failOnError: true)
        //new Agencia(...).save(flush: true, failOnError: true)
        //Agencia agencia = new Agencia(...).save(flush: true, failOnError: true)
        //new Agencia(...).save(flush: true, failOnError: true)
        //new Agencia(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //agencia.id
    }

    void "test get"() {
        setupData()

        expect:
        agenciaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Agencia> agenciaList = agenciaService.list(max: 2, offset: 2)

        then:
        agenciaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        agenciaService.count() == 5
    }

    void "test delete"() {
        Long agenciaId = setupData()

        expect:
        agenciaService.count() == 5

        when:
        agenciaService.delete(agenciaId)
        sessionFactory.currentSession.flush()

        then:
        agenciaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Agencia agencia = new Agencia()
        agenciaService.save(agencia)

        then:
        agencia.id != null
    }
}
