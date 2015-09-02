package groovykoans

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

class KoanService {
    CreateKoansService createKoansService
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

    private def createCompilerConfiguration() {
        final SecureASTCustomizer secure = new SecureASTCustomizer()
        secure.with {
            closuresAllowed = true
            methodDefinitionAllowed = true

            importsWhitelist = []
            staticImportsWhitelist = []
            staticStarImportsWhitelist = []

//            TODO: Zou je bepaalde tokens niet willen accepteren?
//            tokensWhitelist = [
//                    Types.PLUS,
//                    Types.MINUS,
//                    Types.MULTIPLY,
//                    Types.DIVIDE,
//                    Types.MOD,
//                    Types.POWER,
//                    Types.PLUS_PLUS,
//                    Types.MINUS_MINUS,
//                    Types.COMPARE_EQUAL,
//                    Types.COMPARE_NOT_EQUAL,
//                    Types.COMPARE_LESS_THAN,
//                    Types.COMPARE_LESS_THAN_EQUAL,
//                    Types.COMPARE_GREATER_THAN,
//                    Types.COMPARE_GREATER_THAN_EQUAL,
//                    Types.EQUAL,
//                    Types.KEYWORD_INSTANCEOF,
//                    Types.LEFT_SHIFT,
//                    Types.LEFT_SQUARE_BRACKET,
//                    Types.RIGHT_SQUARE_BRACKET
//            ].asImmutable()


//            TODO: Dit zorgt er voor dat ik zelf gedefinieerde classes niet kan instantieren
//            constantTypesClassesWhiteList = [
//                    Integer,
//                    Float,
//                    Long,
//                    Double,
//                    BigDecimal,
//                    Object,
//                    String,
//                    Boolean,
//                    boolean,
//                    int,
//                    List
//            ].asImmutable()

            receiversClassesBlackList = [System] //TODO: Alle classes opsommen waar je methodes op mag aanroepen? Hoe stel je zelfgedefinieerde classes vrij?

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
