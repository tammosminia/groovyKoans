class BootStrap {

    def createKoansService

    def init = { servletContext ->
        createKoansService.createAll()
    }
    def destroy = {
    }
}
