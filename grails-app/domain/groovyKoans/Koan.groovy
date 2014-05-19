package groovyKoans

class Koan {
    static belongsTo = Chapter
    String name
    String explanation
    String preCode
    String code
    String postCode
    static hasMany = [links: String]

    static constraints = {
        name(nullable: false, blank: false, maxSize: 100)
        explanation(nullable: true, maxSize: 1000)
        preCode(nullable: true, blank: true, maxSize: 10000)
        code(nullable: true, blank: true, maxSize: 10000)
        postCode(nullable: true, blank: true, maxSize: 10000)
    }

}
