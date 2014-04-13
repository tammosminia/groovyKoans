package groovykoans

import grails.transaction.Transactional

@Transactional
class KoanService {

    List<String> listKoans() {
        [
                '1-1',
                '1-2'
        ]
    }

    String getKoan(String koanNumber) {
        getClass().getResource("/koans/koan${koanNumber}").text
    }

    def runKoan(String koan) {
//        List checks = []
        Binding binding = new Binding()
//        binding.setVariable("check", { boolean result, String message ->
//            checks << [result: result, message: message]
//        })
        GroovyShell shell = new GroovyShell(binding)
        try {
            shell.evaluate(koan)
            [success: true]
        } catch (AssertionError e) {
            [success: false, message: e.message]
        } catch (Exception e) {
            [success: false, exception: e, message: e.message]
        }
//        checks
    }

}
