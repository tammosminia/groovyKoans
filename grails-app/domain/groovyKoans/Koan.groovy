package groovyKoans

class Koan {
    static belongsTo = [chapter: Chapter]
    int number
    String name
    String explanation
    String preCode
    String code
    String postCode
    String solution

    static constraints = {
        number(nullable: false)
        name(nullable: false, blank: false, maxSize: 100)
        explanation(nullable: true, maxSize: 1000)
        preCode(nullable: true, blank: true, maxSize: 10000)
        code(nullable: true, blank: true, maxSize: 10000)
        postCode(nullable: true, blank: true, maxSize: 10000)
        solution(nullable: true, blank: true, maxSize: 10000)
    }

}
