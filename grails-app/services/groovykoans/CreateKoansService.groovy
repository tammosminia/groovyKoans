package groovykoans

import groovyKoans.Chapter
import groovyKoans.Koan

class CreateKoansService {
    def createAll() {
        new Chapter(name: 'Start', koans: [
            new Koan(name: 'The first one is free',
                    explanation: /For every koan you'll get an explanation that will enable you to complete it. You must edit the code so the assertions will be true. When you think you're done press 'try' to evaluate it./,
                    code: /boolean assertion = true
assert assertion, "We start easy. No need to change anything :)"/
            ),
            new Koan(name: 'Change false to true',
                    explanation: /From here on the assertions will start out false. Now it's up to you to pass the tests/,
                    code: /boolean assertion = false
assert assertion, "Change false into true to pass."/
            )
        ]).save(failOnError: true)

        new Chapter(name: 'Booleans', koans: [
            new Koan(name: 'Comparing booleans',
                    explanation: '',
                    code: /boolean predicate2 = true
assert predicate2 == false/
            ),
            new Koan(name: 'Collections as booleans',
                    explanation: 'Empty collections evaluate to false',
                    code: /List list = []
assert list, 'The list needs to be filled'/
            ),
            new Koan(name: 'String truth',
                    explanation: 'Quite intuitively, empty (or null) strings are false.',
                    code: /String s1
String s2 = ''
assert s1, 'Null is not truthy'
assert s2, 'An empty string is not truthy'/
            ),
            new Koan(name: 'Numerical truth',
                    explanation: 'Similar to C code, null or zeros are false. Any other number is true.',
                    code: /int one = 1
int minus = -1
int zero = 0
assert one, 'positive integers are truthy'
assert minus, 'negative integers are truthy'
assert zero, '0 Is not truthy'/
            )
        ]).save(failOnError: true)

        new Chapter(name: 'Java', koans: [
                new Koan(name: 'Java beans',
                        explanation: '''JavaBeans (or POJOs) are simple Java objects with getters (getX) and setters (setX) for its members.
Groovy introduces an easier way to create JavaBeans. They're called GroovyBeans.
Have a read here: http://groovy.codehaus.org/Groovy+Beans''',
                        code: '''public class JavaPerson {
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
}

JavaPerson dries = new JavaPerson()
dries.firstName = 'Dries' //We implicitly call the setter

JavaPerson andre = new JavaPerson(firstName: 'Andre')

assert dries.lastName == 'Roelvink', 'Set his last name in the same way as his first name'
assert andre.lastName == 'Hazes', 'Set his last name during construction'
'''
                ),
                new Koan(name: 'Groovy beans',
                        explanation: '''Groovy introduces an easier way to create JavaBeans. They're called GroovyBeans.
This class is functionally the as the last (java) one.
Have a read here: http://groovy.codehaus.org/Groovy+Beans''',
                        code: '''public class GroovyPerson {
    String firstName
    String lastName
}

def dries = new GroovyPerson()
dries.setFirstName('Dries') //Groovy makes a setter for us

assert dries.lastName == 'Roelvink'
'''
                ),
        ]).save(failOnError: true)


    }
}
