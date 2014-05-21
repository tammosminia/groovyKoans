package groovykoans

import grails.test.spock.IntegrationSpec
import groovyKoans.Koan

class CreateKoansServiceIntegrationSpec extends IntegrationSpec {
    def "CreateAll"() {
        when:
        new CreateKoansService().createAll()

        then:
        Koan.list().size() > 30
    }

}
