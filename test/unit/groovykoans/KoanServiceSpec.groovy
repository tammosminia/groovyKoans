package groovykoans

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(KoanService)
class KoanServiceSpec extends Specification {
    def "CreateAll"() {
        when:
        service.createAll()

        then:
        service.koans.eachWithIndex { koan, index ->
            assert koan.number == index
        }
    }
}
