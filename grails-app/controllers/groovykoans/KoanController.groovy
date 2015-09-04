package groovykoans

import groovyKoans.Chapter
import groovyKoans.Koan

class KoanController {
    def koanService
    def createKoansService

    def index() {
    }

    def list() {
        [
                chapters: createKoansService.chapters
        ]
    }

    def reload() {
        createKoansService.reload()
    }

    def view(int number, String code) {
        assert number != null
        def koan = createKoansService.getKoan(number)
        assert koan

        def model = [
                koan     : koan,
                code     : koan.code,
                totalKoans : createKoansService.count()
        ]


        if (request.post) {
            def result = koanService.runKoan("${koan.preCode ?: ''}\n${code ?: ''}\n${koan.postCode ?: ''}")
            model << [
                    success  : result.success,
                    exception: result.exception,
                    message  : result.message,
                    output   : result.output,
                    code : code
            ]
        }
        model
    }

    def next(int number) {
        def nextNumber = createKoansService.nextKoan(number)
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
