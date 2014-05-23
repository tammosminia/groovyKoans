package groovykoans

import grails.transaction.Transactional
import groovyKoans.Koan
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer
import org.codehaus.groovy.syntax.Types



@Transactional
class KoanService {


    final CompilerConfiguration compilerConfiguration

    public KoanService() {
          compilerConfiguration = createCompilerConfiguration()

    }


    def runKoan(String koan) {

        def out = new ByteArrayOutputStream()

        GroovyShell shell = createSecuredGroovyShell(out)
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

    private def createCompilerConfiguration() {
        final SecureASTCustomizer secure = new SecureASTCustomizer()
        secure.with {
            closuresAllowed = true
            methodDefinitionAllowed = true

            importsWhitelist = []
            staticImportsWhitelist = []
            staticStarImportsWhitelist = []

            tokensWhitelist = [
                    Types.PLUS,
                    Types.MINUS,
                    Types.MULTIPLY,
                    Types.DIVIDE,
                    Types.MOD,
                    Types.POWER,
                    Types.PLUS_PLUS,
                    Types.MINUS_MINUS,
                    Types.COMPARE_EQUAL,
                    Types.COMPARE_NOT_EQUAL,
                    Types.COMPARE_LESS_THAN,
                    Types.COMPARE_LESS_THAN_EQUAL,
                    Types.COMPARE_GREATER_THAN,
                    Types.COMPARE_GREATER_THAN_EQUAL,
                    Types.EQUAL,

            ].asImmutable()


            constantTypesClassesWhiteList = [
                    Integer,
                    Float,
                    Long,
                    Double,
                    BigDecimal,
                    Object,
                    String,
                    Boolean,
                    boolean,
                    int,
                    List
            ].asImmutable()

            receiversClassesBlackList = [] //TODO: Alle classes opsommen waar je methodes op mag aanroepen?

        }

        def result  = new CompilerConfiguration()
        result.addCompilationCustomizers(secure)

        result
    }

    private def createSecuredGroovyShell(OutputStream out) {
        def binding = new Binding();
        binding.setProperty("out", new PrintStream(out))
        new GroovyShell(binding, compilerConfiguration)
    }


}
