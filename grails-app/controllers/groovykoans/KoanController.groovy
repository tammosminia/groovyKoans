package groovykoans

class KoanController {
    def koanExecutionService
    def koanService

    def index() {
    }

    def list() {
        [
                chapters: koanService.chapters
        ]
    }

    def reload() {
        koanService.reload()
    }

    def view(int number, String code) {
        assert number != null
        def koan = koanService.getKoan(number)
        assert koan

        def model = [
                koan      : koan,
                code      : koan.code,
                totalKoans: koanService.count()
        ]


        if (request.post) {
            def result = koanExecutionService.runKoan("${koan.preCode ?: ''}\n${code ?: ''}\n${koan.postCode ?: ''}")
            model << [
                    success  : result.success,
                    exception: result.exception,
                    message  : result.message,
                    output   : result.output,
                    code     : code
            ]
        }
        model
    }

    def next(int number) {
        def nextNumber = koanService.nextKoan(number)
        if (nextNumber) {
            redirect(action: 'view', params: [number: nextNumber])
        } else {
            redirect(action: 'end')
        }
    }

    def end() {
        //TODO: uitleg over wat nu. groovy downloaden, grails, commentaar
    }
}
