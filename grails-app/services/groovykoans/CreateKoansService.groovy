package groovykoans

import groovyKoans.Chapter
import groovyKoans.Koan

class CreateKoansService {
    def createAll() {
        new Chapter(name: 'Start', koans: [
            new Koan(name: 'The first one is free',
                    explanation: /For every koan you'll get an explanation that will enable you to complete it.
You must edit the code so the assertions will be true. To guide you in the right path, only the code where you'll need to make the change is editable.
When you think you're done press 'try' to evaluate it./,
                    code: /boolean assertion = true/,
                    postCode: /assert assertion, "We start easy. No need to change anything :)"/
            ),
            new Koan(name: 'println',
                    explanation: /You can use println to get output/,
                    code: /println 'Very useful to try things out'/,
                    postCode: /assert true, "Continue when you're done printing"/
            ),
            new Koan(name: 'Change false to true',
                    explanation: /From here on the assertions will start out false. Now it's up to you to pass the tests/,
                    preCode: /boolean assertion = false/,
                    postCode: /assert assertion, "Change false into true to pass."/
            )
        ]).save(failOnError: true)
        

        new Chapter(name: 'Booleans', koans: [
            new Koan(name: 'Comparing booleans',
                    explanation: '',
                    preCode: /boolean predicate2 = true/,
                    postCode: /assert predicate2 == false/
            ),
            new Koan(name: 'Collections as booleans',
                    explanation: 'Empty collections evaluate to false',
                    preCode: /List list = []/,
                    postCode: /assert list, "The list needs to be filled"/
            ),
            new Koan(name: 'String truth',
                    explanation: 'Quite intuitively, empty (or null) strings are false.',
                    code: "String s1\nString s2 = ''",
                    postCode: /assert s1, "Null is not truthy"
assert s2, "An empty string is not truthy"/
            ),
            new Koan(name: 'Numerical truth',
                    explanation: 'Similar to C code, null or zeros are false. Any other number is true.',
                    code: /int one = 1
int minus = -1
int zero = 0/,
                    postCode: /assert one, "positive integers are truthy"
assert minus, "negative integers are truthy"
assert zero, "0 Is not truthy"/
            )
        ]).save(failOnError: true)
        

        new Chapter(name: 'Java', koans: [
                new Koan(name: 'Java beans',
                        explanation: '''JavaBeans (or POJOs) are simple Java objects with getters (getX) and setters (setX) for its members.
Groovy introduces an easier way to create JavaBeans. They're called GroovyBeans.
Have a read here: http://groovy.codehaus.org/Groovy+Beans''',
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
This class is functionally the as the last (java) one.
Have a read here: http://groovy.codehaus.org/Groovy+Beans''',
                        preCode: '''public class GroovyPerson {
    String firstName
    String lastName
}

def dries = new GroovyPerson()
dries.setFirstName('Dries') //Groovy makes a setter for us
''',
                        postCode: /assert dries.lastName == 'Roelvink'/
                ),
        ]).save(failOnError: true)


        new Chapter(name: 'Dynamic typing', koans: [
            new Koan(name: 'def',
                    explanation: 'It is not necessary to specify the type of variables. You can use the keyword def. \nNote that it may still be useful to specify the type anyway, to improve readability.',
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
                    explanation: '',
                    code: "def variable = 'fortytwo'",
                    postCode: '''try {
    variable.length()  // Invalid method for Boolean type.
    assert false, 'When you change the type of the variable, the previous statement will throw an exception'
} catch (RuntimeException e) {
    assert true, "The code compiles and runs, but throws an exception when it tries to call a method that doesn't exist on this type"
}'''
            ),
        ]).save(failOnError: true)

        
        new Chapter(name: 'Methods', koans: [
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
assert fuzzyPlus(100, 1) == 'too much', 'Improve the fuzzyPlus method by using an if/else statement'
'''
            ),
        ]).save(failOnError: true)

        
        new Chapter(name: 'Strings', koans: [
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
                    explanation: /When you use " quotes, you can embed expressions in your code using ${expression}/,
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
        ]).save(failOnError: true)

    }
}
