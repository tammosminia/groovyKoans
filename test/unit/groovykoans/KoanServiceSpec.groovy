package groovykoans

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(KoanService)
class KoanServiceSpec extends Specification {
    void "runKoan - assertion is true"() {
        given:
        def koan = /assert true, 'blablabla'/

        when:
        def result = service.runKoan(koan)

        then:
        result.success
    }

    void "runKoan - assertion is false"() {
        given:
        def koan = /assert false, 'blablabla'/

        when:
        def result = service.runKoan(koan)

        then:
        !result.success
        result.message.startsWith 'blablabla'  //TODO: de onzin hier achter weghalen
    }

    void "runKoan - exception"() {
        def koan = /throw new RuntimeException('oh nee. alles gaat mis!')/

        when:
        def result = service.runKoan(koan)

        then:
        result.exception.message == 'oh nee. alles gaat mis!'
    }

    void "listKoans"() {
        when:
        def koans = service.listKoans()

        then:
        koans.size() == 2
        koans[0] == '1-1'
    }

    void "getKoan - 1-1"() {
        when:
        String koan = service.getKoan('1-1')

        then:
        koan.length() > 50
        koan.contains 'hello'
    }

    @Unroll
    void "run all koans #koanNumber"() {
        given:
        def koan = service.getKoan(koanNumber)

        when:
        def result = service.runKoan(koan)

        then:
        !result.success

        where:
        koanNumber << service.listKoans()
    }

}
