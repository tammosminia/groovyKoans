package groovykoans

import grails.test.mixin.TestFor
import groovy.util.logging.Log
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(KoanExecutionService)
@Log
class KoanExecutionServiceGroovyShellSpec extends Specification {
    void "run All Koans Without Security Exceptions"() {
        given:
        def successCount = 0;
        KoanService createKoansService = new KoanService();
        createKoansService.createAll()


        when:
        createKoansService.koans.findAll { it.code != null }.each { koan ->
            println koan.name
            def result = service.runKoan("""
                    ${koan.preCode}
                    ${koan.solution}
                    ${koan.postCode}
            """)
            if (result.exception == null){//} || !(result.exception instanceof java.lang.SecurityException)) {
                log.info "Executed koan ${koan.name} with success"
                successCount++;
            } else {
                fail "Could not execute Koan: ${result}"
            }
        }

        then:
        successCount > 30
    }

}
