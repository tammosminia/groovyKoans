package groovykoans

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(CreateKoansService)
class CreateKoansServiceSpec extends Specification {
    def "CreateAll"() {
        when:
        service.createAll()

        then:
        service.koans.eachWithIndex { koan, index ->
            assert koan.number == index
        }
    }
}
