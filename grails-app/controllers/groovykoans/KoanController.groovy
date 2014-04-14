package groovykoans

import groovyKoans.Chapter
import groovyKoans.Koan

class KoanController {
    def koanService
    def createKoansService

    private reload() {
        Chapter.list()*.delete()
        createKoansService.createAll()
    }

    def index() {
        reload()
        [
                chapters: Chapter.list()
        ]
    }

    def view(int id, String code) {
        def koan = Koan.get(id)
        if(request.post) {
            def result = koanService.runKoan("${koan.preCode ?: ''}\n$code\n${koan.postCode ?: ''}")
            [
                    koan: koan,
                    code: code,
                    success: result.success,
                    exception: result.exception,
                    message: result.message,
                    output: result.output
            ]
        } else {
            [
                    koan: koan,
                    code: koan.code
            ]
        }
    }

    def next(int id) {
        def nextId = id + 1
        redirect(action: 'view', id: nextId)
    }
}
