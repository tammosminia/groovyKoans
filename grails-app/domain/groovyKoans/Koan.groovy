package groovyKoans

class Koan {
    static belongsTo = Chapter
    String name
    String explanation
    String code

    static constraints = {
        name(nullable: false, blank: false, maxSize: 100)
        explanation(nullable: true, maxSize: 1000)
        code(nullable: false, blank: false, maxSize: 10000)
    }
}
