package groovykoans

import groovyKoans.Chapter
import groovyKoans.Koan

class CreateKoansService {
    void reload() {
        Chapter.list()*.delete()
        createAll()
    }

    void createAll() {
        int numberOfChapters = 0
        int numberOfKoans = 0
        def addChapter = { Chapter chapter ->
            chapter.number = numberOfChapters++
            chapter.koans.each {
                it.number = numberOfKoans++
                it.chapter = chapter
            }
            chapter.save(failOnError: true)
        }

        addChapter(new Chapter(name: 'Introduction',
                links: ['http://groovy.codehaus.org/Documentation', 'http://mrhaki.blogspot.nl/'],
                koans: [
            new Koan(name: 'Introduction',
                    explanation: /We'll start with a few introductory tests to get you familiar with the form.
Each assignment has a short explanation. (this block)/,
                    code: '''//And some code. (this block)
//Press the play button beneath. It will tell you this code runs successfully and you can go on with the next test.''',
            ),
            new Koan(name: 'Code block',
                    explanation: /The code block is made up of three parts. You can only edit the middle part. This is to guide you in the right direction/,
                    preCode: /String s = 'You cannot edit the first part'/,
                    code: /String s2 = 'You can edit the middle part'/,
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
                    postCode: /assert groovy, "Change the value of groovy to true to pass."/
            )
        ]))


        addChapter(new Chapter(name: 'Booleans',
                links: ['http://groovy.codehaus.org/Groovy+Truth'],
                koans: [
            new Koan(name: 'Comparing booleans',
                    explanation: '',
                    preCode: /boolean predicate = true/,
                    postCode: /assert predicate == false/
            ),
            new Koan(name: 'Collections as booleans',
                    explanation: 'Empty collections evaluate to false',
                    preCode: /List list = []/,
                    postCode: /assert list, "The list needs to be filled"/
            ),
            new Koan(name: 'String truth',
                    explanation: 'Quite intuitively, empty (or null) strings are false.',
                    code: /String s1
String s2 = ''/,
                    postCode: /assert s1, "Null is not truthy"
assert s2, "An empty string is not truthy"/
            ),
            new Koan(name: 'Numerical truth',
                    explanation: 'Similar to C code, null or zeros are false. Any other number is true.',
                    code: /int i1 = 1
int i2 = -1
int i3 = 0/,
                    postCode: /assert i1, "positive integers are truthy"
assert i2, "negative integers are truthy"
assert i3, "0 Is not truthy"/
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
truthy.each {
    assert it, "$it is not falsy"
}'''
            ),
        ]))


        addChapter(new Chapter(name: 'Java',
                links: ['http://groovy.codehaus.org/Groovy+Beans'],
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
assert andre.lastName == 'Hazes', "Set his last name during construction"/
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
                        postCode: /assert dries.lastName == 'Roelvink'/
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
assert dries.fullName == 'Dries Roelvink', "make a getter for fullName"/
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
assert variable2 == 'enlightenment', 'Define a new variable'/
            ),
            new Koan(name: 'Runtime typing',
                    explanation: 'Objects do have a type at runtime.',
                    code: /def variable1 = true
def variable2
variable1 = 'The answer to everything'/,
                    postCode: /assert variable1.class.name == 'java.lang.Integer', 'Give variable1 the correct type'
assert variable2 instanceof String, 'Give variable2 the correct type'/
            ),
            new Koan(name: 'Calling a method on a dynamic variable',
                    explanation: /The code compiles and runs, but throws an exception when it tries to call a method that doesn't exist on this type/,
                    code: "def variable = 'fortytwo'",
                    postCode: '''try {
    variable.length()  // Invalid method for Boolean type.
    assert false, 'Change the type of the variable, so the previous statement will throw an exception'
} catch (RuntimeException e) {
    assert true, "We want to throw this exception"
}'''
            ),
        ]))


        addChapter(new Chapter(name: 'Methods',
                links: ['http://groovy.codehaus.org/Extended+Guide+to+Method+Signatures'],
                koans: [
            new Koan(name: 'No return',
                    explanation: 'A method returns the value of the last statement. No need to explicitly use the return keyword',
                    code: /int plus(int i1, int i2) {
	i1 - i2
}/,
                    postCode: /assert plus(1, 2) == 3, 'Improve the plus method'/
            ),
            new Koan(name: 'Dynamic return type',
                    explanation: 'Using the keyword def, we can return any type',
                    code: /def fuzzyPlus(int i1, int i2) {
	i1 + i2
}/,
                    postCode: '''assert fuzzyPlus(1, 2) == 3, "Don't break this"
assert fuzzyPlus(100, 1) == 'too much', "Improve the fuzzyPlus method by using an if/else statement"
'''
            ),
            new Koan(name: 'timesTwo',
                    explanation: 'create a method to double things',
                    postCode: '''assert timesTwo(2) == 4
assert timesTwo('leerdammerkaas') == 'leerdammerkaasleerdammerkaas'
'''
            ),
        ]))


        addChapter(new Chapter(name: 'Strings', koans: [
            new Koan(name: /Many ways to quote a 'String'/,
                    explanation: "Using the ' or \" quotes, you can escape certain characters using \\ ",
                    code: /String s = 'Leerdammerkaas, carpaccio, wat een combinatio'/,
                    postCode: /assert s.contains '\\n' /
            ),
            new Koan(name: 'Many ways to quote a /String/',
                    explanation: "You can also use /. An extra advantage of having multiple ways to quote strings is that you don't always need to escape quotes used in your string ",
                    code: /String s1, s2, s3/,
                    postCode: '''assert s1.contains /'/
assert s2.contains '"'
assert s3.contains '/'
'''
            ),
            new Koan(name: /GString/,
                    explanation: '''When you use " quotes, you can embed expressions in your code using ${expression}''',
                    code: /String name = 'Zanger Rinus'
String greeting/,
                    postCode: /assert greeting == 'Hello Zanger Rinus'/
            ),
            new Koan(name: /Multiline strings/,
                    explanation: "Using 3 ' quotes, your string may span multiple lines",
                    code: /String s = '''Het is tijd, de hoogste tijd,
u wordt bedankt voor weer 'n avond gezelligheid,
dag mevrouw en dag meneer,
u komt hier toch weer.
'''/,
                    postCode: /assert s.count('\\n') == 6 /
            ),
        ]))


        addChapter(new Chapter(name: 'Closures', koans: [
                new Koan(name: /Closures/,
                        explanation: /Closures look a lot like methods, because we can pass parameters and we get a return value. But closures are anonymous. A closure is a piece of code that can be assigned to a variable. Later we can execute the code. Since Java 8 Java has lambda expressions which look a lot like Groovy closures./,
                        code: '''Closure hello = { String name -> println "Hello $name" }
hello('Frans')
Closure plusOne = { int i -> i }
Closure plus = { i1, i2 -> i1 * i2 }''',
                        postCode: /assert plusOne(1) == 2
assert plus(1, 2) == 3/
                ),
                new Koan(name: /Closures.call()/,
                        explanation: /We can call a closure just like a method, or invoke the call method./,
                        code: /def plusOne/,
                        postCode: /assert plusOne(1) == 2
assert plusOne.call(5) == 6/
                ),
                new Koan(name: /Closure without arguments/,
                        explanation: /A closure does not need to have arguments./,
                        code: /def noArg = { 'Closure without arguments.' }/,
                        postCode: /assert noArg() == 'Sieneke'/
                ),
                new Koan(name: /it parameter in closures/,
                        explanation: "If you don't define explicit parameters, you get an implicit parameter called 'it'",
                        code: '''Closure hello = { println "Hello $it" }
hello('Dries')
Closure plusOne = { 5 }''',
                        postCode: /assert plusOne(1) == 2
assert plusOne('Marco') == 'Marco1/
                ),
                new Koan(name: /Closure as method parameter/,
                        explanation: /Because a closure is a variable we can use it as a method parameter./,
                        preCode: /Closure closure = { 2 }/,
                        code: /def timesThree/,
                        postCode: /assert timesThree(closure) == 6
timesThree({ println 'shalalalie'})/
                ),
                new Koan(name: /Closure as last method parameter/,
                        explanation: /Groovy has syntactic sugar. You can put the closure outside the argument list if it's the last argument/,
                        code: /def times/,
                        postCode: /times(2) { println 'shalalala'}
times 3 { println 'shalalalie'}/
                ),
        ]))


        addChapter(new Chapter(name: 'Lists', koans: [
                new Koan(name: 'Defining lists',
                        explanation: 'Defining a list is very easy',
                        preCode: /def empty = []
List<String> singers = ['Frans Bauer', 'Zanger Rinus']/,
                        code: /List numbers/,
                        postCode: /assert numbers.size() == 3, 'Define a list with three numbers'/
                ),
                new Koan(name: 'Adding items',
                        explanation: 'You can add items with the leftShift operator',
                        preCode: /def singers = ['Frans Bauer', 'Zanger Rinus']/,
                        code: /singers << 'Jantje Smit'/,
                        postCode: /assert singers.size() == 5, 'Add two more great singers'/
                ),
                new Koan(name: 'accessing list items by index',
                        explanation: 'You can use a negative index to read from last to first',
                        code: /def numbers'/,
                        postCode: /assert numbers[1] == 3
assert numbers[4] == 7
assert numbers[-1] == 7/
                ),
                new Koan(name: 'Adding lists',
                        explanation: 'You can add or subtract lists using the + and - operator',
                        code: /def list1 = []
def list2 = []/,
                        postCode: /assert list1 - list2 == [1, 2]
assert list1 + list2 == [1, 2, 3, 4]/
                ),
        ]))


        addChapter(new Chapter(name: 'Maps', koans: [
                new Koan(name: 'Defining maps',
                        explanation: '',
                        preCode: /def emptyMap = [:]
Map numbers = [one: 1, 'two':2, (three):3]/,
                        postCode: /assert rhymeWords.size() == 3, 'Define a map with three rhymeWords'
assert rhymeWords['empty'] == 'spaghetti'/
                ),
                new Koan(name: 'Getting/setting map values with get/put',
                        explanation: /Get normally returns null if the key doesn't exist./,
                        preCode: /Man numbers = [1: 'one']/,
                        code: /numbers.put(2, 'two')/,
                        postCode: /assert numbers.get(3) == 'three'/
                ),
                new Koan(name: 'Getting/setting map values with bracket or dot notation',
                        explanation: /Get normally returns null if the key doesn't exist./,
                        preCode: /Man numbers = [one: 1]/,
                        code: /numbers['two'] = 2
numbers.three = 3/,
                        postCode: /assert numbers['four'] == 4
assert numbers.five == 5/
                ),
        ]))


        addChapter(new Chapter(name: 'Collection methods', koans: [
                new Koan(name: 'Each',
                        explanation: 'Groovy adds a lot of extra methods to the Collection API classes. Each loops through all elements',
                        preCode: /def list = [1, 2, 3]/,
                        code: /list.each {
    println it
    assert it != 4
}/,
                ),
                new Koan(name: 'collect',
                        explanation: /With collect you can transform all items in a list./,
                        preCode: /List singers = ['Frans', 'Andre', 'De Havenzangers']/,
                        code: /List stringLengths/,
                        postCode: /assert stringLengths == [5, 5, 15]/
                ),
                new Koan(name: 'shorter collect notation',
                        explanation: /There is a shorthand for collect./,
                        preCode: /List singers = ['Frans', 'Andre', 'De Havenzangers']
List stringLengths = list*.length()/,
                        code: /assert stringLengths = 'What is this?'/,
                ),
                new Koan(name: 'findAll',
                        explanation: /With findAll you can filter out elements./,
                        code: /List singers/,
                        postCode: /assert singers.size() == 2
assert singers.collect { it.length > 5 } == 1/
                ),
                new Koan(name: 'find',
                        explanation: /find just returns the first matching item./,
                        preCode: /List singers = ['Rex Gildo', 'Frans', 'Andre', 'De Havenzangers']/,
                        code: /def firstSingerWithAFiveLetterName = singers.find { false } /,
                        postCode: /assert firstSingerWithAFiveLetterName == 'Frans'/
                ),
        ]))

        addChapter(new Chapter(name: 'Question mark',
                links: ['http://groovy.codehaus.org/Operators#Operators-ElvisOperator(?:)', 'http://mrhaki.blogspot.nl/2009/08/groovy-goodness-elvis-operator.html'],
                koans: [
            new Koan(name: '? operator',
                    explanation: 'You can use the ? operator as shorthand for an if/else construction',
                    preCode: /assert (true ? 1 : 2) == 1
    assert (false ? 1 : 2) == 2/,
                    code: /def isGerman = false/,
                    postCode: /assert (isGerman ? 'Rex Gildo' : 'Andre Hazes') == 'Rex Gildo', "We prefer a German singer for this test"/
            ),
            new Koan(name: 'Elvis operator',  //TODO: oefening om zelf met de elvis te spelen
                    explanation: /The Elvis operator returns the condition if it's truthy, otherwise the righthand expression./,
                    preCode: /assert 1 ?: 2 == 1
    assert 0 ?: 2 == 2/,
                    code: /def name/,
                    postCode: /assert name ?: 'Frans Bauer' == 'Rex Gildo'/
            ),
            new Koan(name: 'nullsafe ? operator',
                    explanation: /With the ? operator, the next method is not evaluated if the lefthand object is null, instead it will return null./,
                    preCode: /Map map = [a: 1]
    assert map.a?.toString() == '1'
    assert map.b?.toString() == null/,
                    code: /def key = 'a'/,
                    postCode: /assert map[key]?.toString() == null/
            ),
        ]))


    }
}
