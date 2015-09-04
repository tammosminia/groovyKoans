package groovykoans

class LinesTagLib {
    def lines = { attrs, body ->
        out << body().encodeAsHTML().replace('\n', '<br/>\n')
    }
}
