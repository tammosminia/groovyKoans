class BootStrap {

    def koanService

    def init = { servletContext ->
        koanService.createAll()
    }

    def destroy = {
    }
}
