# Efficient Coding Standards

With modern IDEs, debates about coding standards are less frequent.

Using the default standards is a sufficient starting point and may be sufficient for the lifetime of the project.

Code should match the ambient style, which is easily achieved by starting with the default coding standards for the language as specified by the IDE. Changes or additions can be made with a custom template, and that template can be checked in with the project.

If a person has specific suggestions on coding standards that may improve a project, they can be discussed with the team, making a case for changing or adding a standard.

Here are some examples of standards worth discussing:
- No (strong) warnings in any completed story.
- When to change a warning to weak or turn a warning into an error.
- In languages that support it, use named parameters.

The above examples clearly impact code quality, but again, it is important to convince people by demonstrating the merit of the approach when there is no higher priority work needing to be done.

Examples of standards that can be considered minutiae include:
- Allow or disallow multiple declarations per line.
- The number of terms allowed until one breaks apart the expression.

Although one may have an opinion, it is not worth slowing down project work to discuss minutiae. 
Investigating the cause of an "Expression always false warning" is more important than debating whether "int totalLines, currentLine = 0;" should be allowed.

