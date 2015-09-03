package groovykoans

import groovyKoans.Chapter
import groovyKoans.Koan
import groovyKoans.Link

class CreateKoansService {
    def chapters = new ArrayList<Chapter>()
    def koans = new ArrayList<Koan>()

    Koan getKoan(int number) {
        koans.get(number)
    }

    //returns null if this was the last koan
    Integer nextKoan(int number) {
        if (number >= koans.size()) {
            null
        } else {
            number + 1
        }
    }

    void reload() {
        chapters = new ArrayList<Chapter>()
        koans = new ArrayList<Koan>()
        createAll()
    }

    private void addChapter(Chapter chapter) {
        chapters.add(chapter)
        chapter.koans.each { koan ->
            koan.chapter = chapter
            koan.number = koans.size()
            koans.add(koan)
        }
    }

    void createAll() {
        addChapter(new Chapter(name: 'Introduction',
                links: [
                        new Link(name: 'Groovy documentation', url: 'http://groovy-lang.org/documentation.html'),
                        new Link(name: 'Groovy Goodness', url: 'http://mrhaki.blogspot.nl/search/label/Groovy%3AGoodness')
                ],
                koans: [
            new Koan(name: 'Introduction',
                    explanation: /We'll start with a few introductory tests to get you familiar with the form.
Each assignment has a short explanation. (this block)/,
                    code: '''//And some code. (this block)
//Press the play button beneath. It will tell you this code runs successfully and you can go on with the next test.''',
            ),
            new Koan(name: 'Code block',
                    explanation: /The code block is made up of three parts. You can only edit the second part. This is to guide you in the right direction/,
                    preCode: /String s = 'You cannot edit this line.'/,
                    code: /String s2 = 'You can edit this line.'/,
                    postCode: /assert true, 'The last part usually contains assertions.'/
            ),
            new Koan(name: 'println',
                    explanation: /You can use println to get output./,
                    code: /println 'Very useful to try things out'/,
            ),
            new Koan(name: 'documentation',
                    explanation: /You can click on the links to read more documentation/,
                    postCode: /assert true, "Continue when you've clicked at a documentation link"/
            ),
            new Koan(name: 'Change false to true',
                    explanation: /From here on the assertions will start out false. Now it's up to you to pass the tests/,
                    code: /boolean groovy = false/,
                    postCode: /assert groovy, "Change the value of groovy to true to pass."/,
                    solution: /boolean groovy = true/
            )
        ]))

                    //TODO: asBoolean method
        addChapter(new Chapter(name: 'Booleans',
                links: [new Link(name: 'Groovy truth', url: 'http://groovy.codehaus.org/Groovy+Truth')],
                koans: [
            new Koan(name: 'Comparing booleans',
                    explanation: '',
                    preCode: /boolean predicate = true/,
                    postCode: /assert predicate == false/,
                    solution: /predicate = false/
            ),
            new Koan(name: 'Collections as booleans',
                    explanation: 'Empty collections evaluate to false',
                    preCode: /List list = []/,
                    postCode: /assert list, "The list needs to be filled"/,
                    solution: /list = [1]/
            ),
            new Koan(name: 'String truth',
                    explanation: 'Quite intuitively, empty (or null) strings are false.',
                    code: /String s1
String s2 = ''/,
                    postCode: /assert s1, "Null is not truthy"
assert s2, "An empty string is not truthy"/,
                    solution: /String s1 = 'a'
String s2 = 'a'/
            ),
            new Koan(name: 'Numerical truth',
                    explanation: 'Similar to C code, null or zeros are false. Any other number is true.',
                    preCode: /int one = 1
int negative = -1
int zero = 0/,
                    code: /assert one, "positive integers are truthy"
assert negative, "negative integers are truthy"
assert zero, "0 Is not truthy"/,
                    solution: /assert !zero, "0 Is not truthy"/
            ),
            new Koan(name: 'truth',
                    explanation: 'Assign some truthy and some falsy values.',
                    code: /List truthy
List falsy/,
                    postCode: '''//Don't worry. We'll explain how the code beneath works later.
assert truthy.toSet().size() > 2, "Assign 3 different truthy values"
truthy.each {
    assert it, "$it is not truthy"
}
assert falsy.toSet().size() > 2, "Assign 3 different falsy values"
falsy.each {
    assert !it, "$it is not falsy"
}''',
                    solution: /List truthy = [1, true, 'bla']
List falsy = [0, false, null]/
            ),
        ]))


        addChapter(new Chapter(name: 'Java',
                links: [new Link(name: 'Groovy beans', url: 'http://groovy.codehaus.org/Groovy+Beans')],
                koans: [
                new Koan(name: 'Java beans',
                        explanation: /JavaBeans (or POJOs) are simple Java objects with getters (getX) and setters (setX) for its members./,
                        preCode: '''public class JavaPerson {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        println "Setting last name to $lastName"
        this.lastName = lastName;
    }
}''',
                        code: '''JavaPerson dries = new JavaPerson()
dries.firstName = 'Dries' //We implicitly call the setter

JavaPerson andre = new JavaPerson(firstName: 'Andre')
''',
                        postCode: /assert dries.lastName == 'Roelvink', "Set his last name in the same way as his first name"
assert andre.lastName == 'Hazes', "Set his last name during construction"/,
                        solution: /JavaPerson dries = new JavaPerson()
dries.firstName = 'Dries'
dries.lastName = 'Roelvink'

JavaPerson andre = new JavaPerson(firstName: 'Andre', lastName: 'Hazes')/
                ),
                new Koan(name: 'Groovy beans',
                        explanation: '''Groovy introduces an easier way to create JavaBeans. They're called GroovyBeans.
This class is functionally the as the last (java) one.''',
                        preCode: '''public class GroovyPerson {
    String firstName
    String lastName
}

def dries = new GroovyPerson()
dries.setFirstName('Dries') //Groovy makes a setter for us
''',
                        postCode: /assert dries.lastName == 'Roelvink'/,
                        solution: /dries.lastName = 'Roelvink'/
                ),
                new Koan(name: 'Groovy beans',
                        explanation: /Even though groovy makes a setter for you, you can override it to change the behaviour./,
                        code: '''public class GroovyPerson {
    String firstName
    String lastName

    void setFirstName(String value) {
        firstName = value.capitalize()
    }
}
''',
                        postCode: /def dries = new GroovyPerson(firstName: 'dries', lastName: 'roelvink')
assert dries.lastName == 'Roelvink', "make a setter for lastName"
assert dries.fullName == 'Dries Roelvink', "make a getter for fullName"/,
                        solution: /public class GroovyPerson {
    String firstName
    String lastName

    void setFirstName(String value) {
        firstName = value.capitalize()
    }

    void setLastName(String value) {
        lastName = value.capitalize()
    }

    String getFullName() {
        firstName + ' ' + lastName
    }
}/
                ),
        ]))


        addChapter(new Chapter(name: 'Dynamic typing',
                links: [],
                koans: [
            new Koan(name: 'def',
                    explanation: /It is not necessary to specify the type of variables. You can use the keyword def.
Note that it may still be useful to specify the type anyway, to improve readability./,
                    code: /def variable1 = true
variable1 = 'The answer to everything'/,
                    postCode: /assert variable1 == 42, 'variable1 has no type, so it may be anything'
assert variable2 == 'enlightenment', 'Define a new variable'/,
                    solution: /def variable1 = 42
def variable2 = 'enlightenment'/
            ),
            new Koan(name: 'Runtime typing',
                    explanation: 'Objects do have a type at runtime.',
                    code: /def variable1 = true
println variable1.class.name
def variable2
variable1 = 'The answer to everything'
println variable1.class.name/,
                    postCode: /assert variable1.class.name == 'java.lang.Integer', 'Give variable1 the correct type'
assert variable2 instanceof String, 'Give variable2 the correct type'/,
                    solution: /def variable1 = 42
def variable2 = 'bla'/
            ),
            new Koan(name: 'Calling a method on a dynamic variable',
                    explanation: /The code compiles and runs, but throws an exception when it tries to call a method that doesn't exist on this type/,
                    code: "def variable = 'fortytwo'",
                    postCode: '''try {
    variable.length()  // Invalid method for Boolean type.
    assert false, 'Change the type of the variable, so the previous statement will throw an exception'
} catch (RuntimeException e) {
    assert true, "We want to throw this exception"
}''',
                    solution: /def variable = 1/
            ),
        ]))


        //TODO: variable length parameters
        //TODO: spread operator method(*list)
        addChapter(new Chapter(name: 'Methods',
                links: [new Link(name: 'Method signatures', url: 'http://groovy.codehaus.org/Extended+Guide+to+Method+Signatures')],
                koans: [
            new Koan(name: 'No return',
                    explanation: 'A method returns the value of the last statement. No need to explicitly use the return keyword',
                    code: /int plus(int i1, int i2) {
	i1 - i2
}/,
                    postCode: /assert plus(1, 2) == 3, 'Improve the plus method'/,
                    solution: /int plus(int i1, int i2) {
    i1 + i2
}/
            ),
            new Koan(name: 'Dynamic return type',
                    explanation: 'Using the keyword def, we can return any type',
                    code: /def fuzzyPlus(int i1, int i2) {
	i1 + i2
}/,
                    postCode: '''assert fuzzyPlus(1, 2) == 3, "Don't break this"
assert fuzzyPlus(100, 1) == 'too much', "Improve the fuzzyPlus method by using an if/else statement"
''',
                    solution: /def fuzzyPlus(int i1, int i2) {
    def result = i1 + i2
    result > 10 ? 'too much' : result
}/
            ),
            new Koan(name: 'timesTwo',
                    explanation: 'create a method to double things',
                    postCode: '''assert timesTwo(2) == 4
assert timesTwo('leerdammerkaas') == 'leerdammerkaasleerdammerkaas'
''',
                    solution: /def timesTwo(iets) {
  iets + iets
}/
            ),
            new Koan(name: 'methods without braces',
                    explanation: /You don't need to put braces around method parameters/,
                    code: /println("Deze brief bind ik vast aan m'n vlieger")
println "Tot zij hem ontvangt, zij die ik mis"/,
            ),
            new Koan(name: 'method parameters with default values',
                    explanation: /Method parameters can have default values/,
                    preCode: /def add(value, toAdd = 1) {
    value + toAdd
}
assert add(3) == 4/,
                    postCode: /assert greet('Zangeres zonder naam') == 'hello Zangeres zonder naam', "Define the greet method"
assert greet() == 'hello stranger', 'Let the greet method have a default parameter.'/,
                    solution: '''def greet(name = 'stranger') {
    "hello $name"
}'''
            ),
        ]))


        addChapter(new Chapter(name: 'Strings', koans: [
            new Koan(name: /Many ways to quote a 'String'/,
                    explanation: "Using the ' or \" quotes, you can escape certain characters using \\ ",
                    code: /String s = 'Leerdammerkaas, carpaccio, wat een combinatio'/,
                    postCode: /println s
assert s.contains('\n') /,
                    solution: /String s = 'Leerdammerkaas, carpaccio, \nwat een combinatio'/
            ),
            new Koan(name: 'Many ways to quote a /String/',
                    explanation: "You can also use /. An extra advantage of having multiple ways to quote strings is that you don't always need to escape quotes used in your string ",
                    code: /String s1, s2, s3/,
                    postCode: '''assert s1.contains(/'/)
assert s2.contains('"')
assert s3.contains('/')
''',
                    solution: '''String s1 = "'", s2 = /"/, s3 = '/' '''
            ),
            new Koan(name: /GString/,
                    explanation: '''When you use " quotes, you can embed expressions in your code using ${expression}''',
                    code: /String name = 'Zanger Rinus'
String greeting/,
                    postCode: /assert greeting == 'Hello Zanger Rinus'/,
                    solution: '''String name = 'Zanger Rinus'
String greeting = "Hello $name" '''
            ),
            new Koan(name: /Multiline strings/,
                    explanation: "Using 3 ' quotes, your string may span multiple lines",
                    code: /String s = '''Het is tijd, de hoogste tijd,
u wordt bedankt voor weer 'n avond gezelligheid,
dag mevrouw en dag meneer,
u komt hier toch weer.
'''/,
                    postCode: /assert s.count('\n') == 6, 'Add some new lines to the song.'/,
                    solution: /String s = '''Het is tijd, de hoogste tijd,
u wordt bedankt voor weer 'n avond gezelligheid,
dag mevrouw en dag meneer,
u komt hier toch weer.


'''/
            ),
        ]))


        //TODO: closure with a default value
        //TODO: transforming a method into a closure:  def closure = this.&methodName
        //TODO: curry method
        addChapter(new Chapter(name: 'Closures',
                links: [new Link(name: 'Closures', url: 'http://groovy.codehaus.org/Closures')],
                koans: [
                new Koan(name: /Closures/,
                        explanation: /Closures look a lot like methods, because we can pass parameters and we get a return value. But closures are anonymous. A closure is a piece of code that can be assigned to a variable. Later we can execute the code. Since Java 8 Java has lambda expressions which look a lot like Groovy closures./,
                        code: '''Closure hello = { String name -> println "Hello $name" }
hello('Frans')
Closure times = { i1, i2 -> i1 * i2 }''',
                        postCode: /assert plusOne(1) == 2
assert plus(1, 2) == 3/,
                        solution: /Closure plusOne = { it + 1 }
Closure plus = { a, b -> a + b }/
                ),
                new Koan(name: /Closures.call()/,
                        explanation: /We can call a closure just like a method, or invoke the call method./,
                        code: /def plusOne/,
                        postCode: /assert plusOne(1) == 2
assert plusOne.call(5) == 6/,
                        solution: /def plusOne = { it + 1}/
                ),
                new Koan(name: /Closure without arguments/,
                        explanation: /A closure does not need to have arguments./,
                        code: /def noArg = { 'Closure without arguments.' }/,
                        postCode: /assert noArg() == 'Sieneke'/,
                        solution: /def noArg = { 'Sieneke' }/
                ),
                new Koan(name: /it parameter in closures/,
                        explanation: "If you don't define explicit parameters, you get an implicit parameter called 'it'",
                        code: '''Closure hello = { println "Hello $it" }
hello('Dries')
Closure plusOne = { 5 }''',
                        postCode: /assert plusOne(1) == 2
assert plusOne('Marco') == 'Marco1'/,
                        solution: /Closure plusOne = { it + 1 }/
                ),
                new Koan(name: /Closure as method parameter/,
                        explanation: /Because a closure is a variable we can use it as a method parameter./,
                        code: /def runThreeTimes(Closure closure) {
}/,
                        postCode: /runThreeTimes({ println 'shalalalie'})/,
                        solution: /def runThreeTimes(Closure closure) {
  closure.run()
  closure.run()
  closure.run()
}/
                ),
                new Koan(name: /Closure as last method parameter/,
                        explanation: /Groovy has syntactic sugar. You can put the closure outside the argument list if it's the last argument. Notice how all the ways to call the method are valid.
Create a method that runs a closure x times./,
                        code: /def times(int amount, Closure closure) {
}/,
                        postCode: /times(2) { println 'shalalala'}
times 2, { println 'shalalalie'}
times(1, { println 'shalalalie'})/,
                        solution: /def times = { int number, Closure c ->
  number.times { c() }
}/
                ),
                new Koan(name: /Bound parameters/,
                        explanation: /Closures may refer to variables not listed in their parameter list. Such variables are referred to as "free" variables. They are "bound" to variables within the scope where they are defined./,
                        code: /def times/,
                        postCode: /int runAmount = 0
Closure run = { runAmount++ }
threeTimes(run)
assert runAmount == 3/,
                        solution: /def threeTimes = { Closure c ->
  3.times { c() }
}/
                ),
                new Koan(name: /Default parameters/,
                        explanation: /Closure parameters can have default values./,
                        code: '''def greet = { greeting, name -> "${greeting} ${name}" }''',
                        postCode: /assert greet('Roy') == 'hello Roy'/,
                        solution: '''def greet = { greeting = 'hello', name -> "${greeting} ${name}" }'''
                ),
        ]))


        addChapter(new Chapter(name: 'Lists', koans: [
                new Koan(name: 'Defining lists',
                        explanation: 'Defining a list is very easy',
                        preCode: /def empty = []
List<String> singers = ['Frans Bauer', 'Zanger Rinus']/,
                        code: /List numbers/,
                        postCode: /assert numbers.size() == 3, 'Define a list with three numbers'/,
                        solution: /List numbers = [1,2,3]/
                ),
                new Koan(name: 'Adding items',
                        explanation: 'You can add items with the leftShift operator',
                        preCode: /def singers = ['Frans Bauer', 'Zanger Rinus']/,
                        code: /singers << 'Jantje Smit'/,
                        postCode: /assert singers.size() == 5, 'Add two more great singers'/,
                        solution: /singers << 'Jantje Smit'
singers << 'Gerard Joling'
singers << 'Andre Hazes'/
                ),
                new Koan(name: 'accessing list items by index',
                        explanation: 'You can use a negative index to read from last to first',
                        code: /def numbers'/,
                        postCode: /assert numbers[1] == 3
assert numbers[4] == 9
assert numbers[-2] == 7/,
                        solution: /def numbers = [1, 3, 5, 7, 9]/
                ),
                new Koan(name: 'Adding lists',
                        explanation: 'You can add or subtract lists using the + and - operator',
                        code: /def list1 = []
def list2 = []/,
                        postCode: /assert list1 - list2 == [1, 2]
assert list1 + list2 == [1, 2, 3, 4]/,
                        solution: /def list1 = [1, 2]
def list2 = [3, 4]/
                ),
        ]))


        addChapter(new Chapter(name: 'Maps', koans: [
                new Koan(name: 'Defining maps',
                        explanation: '',
                        preCode: /def emptyMap = [:]
def three = 3
Map numbers = [one: 1, 'two':2, (three):3]/,
                        postCode: /assert rhymeWords.size() == 3, 'Define a map with three rhymeWords'
assert rhymeWords['empty'] == 'spaghetti'/,
                        solution: /def rhymeWords = [empty: 'spaghetti', nasi: 'spaghetti', bami: 'spaghetti']/
                ),
                new Koan(name: 'Getting/setting map values with get/put',
                        explanation: /Get normally returns null if the key doesn't exist./,
                        preCode: /Map numbers = [1: 'one']/,
                        code: /numbers.put(2, 'two')/,
                        postCode: /assert numbers.get(3) == 'three'/,
                        solution: /numbers.put(3, 'three')/
                ),
                new Koan(name: 'Getting/setting map values with bracket or dot notation',
                        explanation: /Get normally returns null if the key doesn't exist./,
                        preCode: /Map numbers = [one: 1]/,
                        code: /numbers['two'] = 2
numbers.three = 3/,
                        postCode: /assert numbers['four'] == 4
assert numbers.five == 5/,
                        solution: /numbers['four'] = 4
numbers.five = 5/
                ),
        ]))


        //TODO: eachWithIndex
        //TODO: groupBy
        addChapter(new Chapter(name: 'Collection methods',
                links: [new Link(name: 'Collections', url: 'http://groovy.codehaus.org/Collections')],
                koans: [
                new Koan(name: 'Each',
                        explanation: 'Groovy adds a lot of extra methods to the Collection API classes. Each loops through all elements',
                        preCode: /def list = [1, 2, 3]/,
                        code: /list.each {
    println it
}/,
                ),
                new Koan(name: 'collect',
                        explanation: /With collect you can transform all items in a list./,
                        preCode: /List singers = ['Frans', 'Andre', 'De Havenzangers']/,
                        code: /List stringLengths/,
                        postCode: /assert stringLengths == [5, 5, 15]/,
                        solution: /List stringLengths = singers.collect { it.length() }/
                ),
                new Koan(name: 'shorter collect notation',
                        explanation: /There is a shorthand for collect./,
                        preCode: /List singers = ['Frans', 'Andre', 'De Havenzangers']
List stringLengths = singers*.length()/,
                        code: /assert stringLengths == 'What is this?'
def lowercase/,
                        postCode: /assert lowercase == ['frans', 'andre', 'de havenzangers'], 'Use the * notation to transform the list to lowercase'/,
                        solution: /assert stringLengths == [5, 5, 15]
def lowercase = singers*.toLowerCase()/
                ),
                new Koan(name: 'findAll',
                        explanation: /With findAll you can filter out elements./,
                        preCode: /List singers = ['Frans', 'Andre', 'De Havenzangers', 'Rex Gildo', 'Zanger Rinus']/,
                        code: /def singersWithAFiveLetterName/,
                        postCode: /assert singersWithAFiveLetterName.size() == 2/,
                        solution: /def singersWithAFiveLetterName = singers.findAll { it.length() == 5 }/
                ),
                new Koan(name: 'find',
                        explanation: /find just returns the first matching item./,
                        preCode: /List singers = ['Rex Gildo', 'Frans', 'Andre', 'De Havenzangers']/,
                        code: /def firstSingerWithAFiveLetterName = singers.find { false } /,
                        postCode: /assert firstSingerWithAFiveLetterName == 'Frans'/,
                        solution: /def firstSingerWithAFiveLetterName = singers.find { it.length() == 5 }/
                ),
                new Koan(name: 'groupBy',
                        explanation: /GroupBy creates a map./,
                        preCode: /class Singer {
    String name
    String country
}

List singers = [
    new Singer(name: 'Roy Donders', country: 'BE'),
    new Singer(name: 'Andre Hazes', country: 'NL'),
    new Singer(name: 'Eddy Wally', country: 'BE'),
    new Singer(name: 'Frans Bauer', country: 'NL'),
    new Singer(name: 'Rex Gildo', country: 'DE'),
]/,
                        code: /def firstSingerWithAFiveLetterName = singers.find { false } /,
                        postCode: /assert firstSingerWithAFiveLetterName == 'Frans'/,
                        solution: /def firstSingerWithAFiveLetterName = singers.find { it.name.length() == 5 }/
                ),
        ]))

        addChapter(new Chapter(name: 'Question mark',
                links: [new Link(name: 'Elvis operator', url: 'http://groovy.codehaus.org/Operators#Operators-ElvisOperator(?:)'),
                        new Link(name: 'Explanation by mr Haki', url: 'http://mrhaki.blogspot.nl/2009/08/groovy-goodness-elvis-operator.html')],
                koans: [
            new Koan(name: '? operator',
                    explanation: 'You can use the ? operator as shorthand for an if/else construction',
                    preCode: /assert (true ? 1 : 2) == 1
    assert (false ? 1 : 2) == 2/,
                    code: /def isGerman = false/,
                    postCode: /assert (isGerman ? 'Rex Gildo' : 'Andre Hazes') == 'Rex Gildo', "We prefer a German singer for this test"
assert numberAppraiser(3) == 'low', "Make a function that returns 'low' for numbers under 10 and 'high' for higher numbers."
assert numberAppraiser(46) == 'high'/,
                    solution: /def isGerman = true
def numberAppraiser(number) {
  number < 10 ? 'low' : 'high'
}/
            ),
            new Koan(name: 'Elvis operator',
                    explanation: /The Elvis operator returns the condition if it's truthy, otherwise the righthand expression./,
                    preCode: /assert 1 ?: 2 == 1
assert 0 ?: 2 == 2/,
                    code: /def name
def greatSinger(String suggestion = null) {
}/,
                    postCode: /assert name ?: 'Frans Bauer' == 'Rex Gildo'
assert greatSinger('Andre Hazes') == 'Andre Hazes', "Complete the greatSinger method, so it returns the suggestion, or defaults to 'Zanger Rinus'"
assert greatSinger() == 'Zanger Rinus'/,
                    solution: /def name = 'Rex Gildo'
def greatSinger(String suggestion = null) {
    suggestion ?: 'Zanger Rinus'
}/
            ),
            new Koan(name: 'nullsafe ? operator',
                    explanation: /With the ? operator, the next method is not evaluated if the lefthand object is null, instead it will return null./,
                    preCode: /def a
assert a?.toString() == null/,
                    code: / /,
                    postCode: /assert lower('HEB JE EVEN VOOR MIJ') == 'heb je even voor mij', "define the lower method, so it lowers the incoming string and doesn't crash on null."
assert lower(null) == null/,
                    solution: /def lower(String s) {
  s?.toLowerCase()
}/
            ),
        ]))

        addChapter(new Chapter(name: 'Regular expressions',
                links: [new Link(name: 'Using regular expressions in Groovy', url: 'http://groovy.codehaus.org/Regular+Expressions'),
                        new Link(name: 'Javadoc', url: 'http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html')],
                koans: [
                        new Koan(name: 'Pattern',
                                explanation: '''~ creates a Pattern from String.
You could also use ' or " quotes to define the string, but / is best because you don't have to escape \\s ''',
                                code: '''def pattern = ~/\\d/''',
                                postCode: /assert pattern instanceof java.util.regex.Pattern, "This is the same as Pattern.compile"
assert pattern.matcher('oliebollenkraam').matches(), "Make a pattern that matches oliebollenkraam"/,
                                solution: '''def pattern = ~/\\w/''',
                        ),
                        new Koan(name: 'Matcher',
                                explanation: /With =~ you create a matcher. When it has at least one match, it's truthy/,
                                preCode: 'def matcher = "123" =~ /\\d/',
                                code: /def song/,
                                postCode: '''assert matcher
assert song =~ /(la){3,}+/, "compose a catchy song"''',
                                solution: /def song = "lalala"/
                        ),
                        new Koan(name: 'matches',
                                explanation: /==~ tests if String matches the pattern. The difference from =~ is that ==~ has to match the whole string. /,
                                code: /def goodSongRegex/,
                                postCode: /assert !('shalalalie 123' ==~ goodSongRegex), 'A good song does not contain numbers'
assert 'Ze gelooft in mij' ==~ goodSongRegex/,
                                solution: '''def goodSongRegex = /[^\\d]*/'''
                        ),
                ]))

        //TODO: switch
        //TODO: range
        //TODO: spaceship operator
        //TODO: operator overloading
        //TODO: use  use(TimeCategory) { field.after(today + 5.year) }
        //TODO: xml  + url.text
        //TODO: AST @Delegate @Singleton @EqualsAndHashCode

    }
}
