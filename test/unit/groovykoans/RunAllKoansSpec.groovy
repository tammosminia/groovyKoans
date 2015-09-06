package groovykoans

import grails.test.mixin.TestFor
import groovy.util.logging.Log
import spock.lang.Specification

@TestFor(KoanExecutionService)
@Log
class RunAllKoansSpec extends Specification {
    void "run All Koans and see if the solution is correct"() {
        given:
        def successCount = 0;
        KoanService createKoansService = new KoanService();
        createKoansService.createAll()

        when:
        createKoansService.koans.each { koan ->
            println koan.name
            def result = service.runKoan("""
                    ${koan.preCode}
                    ${koan.solution ?: ''}
                    ${koan.postCode}
            """)
            if (result.exception == null && result.success) {
                log.info "Executed koan ${koan.name} with success"
                successCount++;
            } else {
                fail "Failure in Koan: ${result}"
            }
        }

        then:
        successCount == createKoansService.koans.size()
    }

}
