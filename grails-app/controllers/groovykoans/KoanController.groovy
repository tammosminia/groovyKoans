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

    def view(String id, String code) {
        if(code) {
            def result = koanService.runKoan(code)
            [
                    koan: Koan.get(id),
                    code: code,
                    success: result.success,
                    exception: result.exception,
                    message: result.message,
                    output: result.output
            ]
        } else {
            def koan = Koan.get(id)
            [
                    koan: koan,
                    code: koan.code
            ]
        }
    }
}
