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
                chapters: Chapter.list()
        ]
    }

    def reload() {
        createKoansService.reload()
    }

    def view(int number, String code) {
        assert number != null
        def koan = Koan.findByNumber(number)
        assert koan
        def model = [
                koan: koan,
                code: koan.code,
                totalKoans : Koan.count()
        ]
        if (request.post) {
            def result = koanService.runKoan("${koan.preCode ?: ''}\n$code\n${koan.postCode ?: ''}")
            model << [
                    success  : result.success,
                    exception: result.exception,
                    message  : result.message,
                    output   : result.output
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
