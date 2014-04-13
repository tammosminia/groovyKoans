package groovykoans

class KoanController {
    def koanService

    def index() {
        [
                koans: koanService.listKoans()
        ]
    }

    def view(String id, String koan) {
        if(koan) {
            def result = koanService.runKoan(koan)
            [
                    id: id,
                    koan: koan,
                    success: result.success,
                    exception: result.exception,
                    message: result.message
            ]
        } else {
            [
                    id: id,
                    koan: koanService.getKoan(id)
            ]
        }
    }
}
