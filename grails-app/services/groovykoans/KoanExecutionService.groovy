package groovykoans

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

class KoanExecutionService {
    final CompilerConfiguration compilerConfiguration

    public KoanExecutionService() {
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
