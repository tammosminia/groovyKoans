package groovykoans

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.util.logging.Log
import groovyKoans.Chapter
import groovyKoans.Koan
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(KoanService)
@Mock([Koan, Chapter])
@Log
class KoanServiceGroovyShellSpec extends Specification {

    void "run All Koans Without Security Exceptions"() {
        given:

        def successCount = 0;
        CreateKoansService createKoansService = new CreateKoansService();
        createKoansService.createAll()


        when:
        Koan.list().findAll { it.code != null }.each { koan ->
            println koan;
            def result = service.runKoan("""
                    ${koan.preCode}
                    ${koan.code}
                    ${koan.postCode}
            """)
            if (result.exception == null || !(result.exception instanceof java.lang.SecurityException)) {
                log.info "Executed koan ${koan.name} #${koan.id} with success"
                successCount++;
            } else {
                fail "Could not executed Koan: ${result}"
            }
        }

        then:
        successCount > 30
    }

}
