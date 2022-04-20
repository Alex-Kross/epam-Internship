    Develop an application that parses and processes text.
* You need to create an application that parses the text from
a programming textbook from a file and allows you to perform three different operations with the text.
* General requirements for the project:
The disassembled text must be presented in the form of an object (text) containing,
for example, sentences and code blocks, the sentence may contain the words of the sentence.
The words of a sentence (parts of a sentence) can be, for example, simple words and
punctuation marks. These classes are entity classes and should not be
overloaded with logic methods.
* The parsed text must be restored to its original form, with the
exception of spaces between elements. Spaces and tabs can
be replaced with a single space during parsing.
* Regular expressions should be used to divide the text into sentences and other components.
Do not forget that regular expressions for the application are
literal constants.
* The code that splits the text into its component parts should be designed in
the form of parser classes.
* When developing parsers that parse text, it is necessary to monitor the number
of parser objects being created.
* When implementing a task, you can use Composite and Chain of templates
Responsibility.
* When handling exceptional situations, the application needs to use
the Log4j logger.
* The created application should allow you to implement a group of tasks for working on
the text (the tasks are listed below) without “rewriting” existing code.

Functionality

1) (Task 2) Print all sentences of a given text in ascending
order of the number of words in each of them.
2) (Task 5) In each sentence of the text, swap the first word with the last one without
changing the length of the sentence.
3) (Task 10) There is a text and a list of words. For each word from a given list, find
   how many times it occurs in each sentence, and sort the words in
   descending order of the total number of occurrences.