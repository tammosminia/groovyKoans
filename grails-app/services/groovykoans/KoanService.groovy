package groovykoans

import grails.transaction.Transactional
import groovyKoans.Koan

@Transactional
class KoanService {

    def runKoan(String koan) {
        Binding binding = new Binding()
        def out = new ByteArrayOutputStream()
        binding.setProperty("out", new PrintStream(out))
        GroovyShell shell = new GroovyShell(binding)
        try {
            shell.evaluate(koan)
            [success: true, output: out.toString()]
        } catch (AssertionError e) {
            [success: false, message: e.message, output: out.toString()]
        } catch (Exception e) {
            [success: false, exception: e, message: e.message, output: out.toString()]
        }
    }

    //returns null if this was the last koan
    Integer nextKoan(int number) {
        def koan = Koan.findByNumber(number + 1)
        koan?.number
    }


}
