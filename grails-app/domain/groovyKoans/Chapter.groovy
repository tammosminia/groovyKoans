package groovyKoans

class Chapter {
    String name
    List<Koan> koans

    static hasMany = [koans: Koan]
}
