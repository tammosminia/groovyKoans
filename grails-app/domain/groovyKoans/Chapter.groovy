package groovyKoans

class Chapter {
    int number
    String name
    List<Koan> koans

    static hasMany = [koans: Koan, links: String]
}
