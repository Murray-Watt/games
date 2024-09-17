# About Documentation

What is the correct amount of documentation?

Back when most projects were done in a waterfall fashion, a great deal of time was 
spent creating and editing documentation. If a project's direction changed or 
it was canceled due to a lack of progress, a lot of person-hours were lost.

These days, with Agile methodologies, we prioritize working code and create 
just enough documentation to serve the project's needs and facilitate communication, 
but no more.

Since this project is intended to demonstrate technology and design, 
it will be documentation-heavy. The following is a rough guide to how much 
documentation one should create under different circumstances.

Note that team harmony and getting buy-in from the team is more important 
than any one person's opinion.

This revised version maintains the essence of your message while improving 
the readability and clarity of the text.

## All Projects
- Well-written TDD tests providing coverage for expected code functionality
- Simple and well-written code
- No dead or commented-out code, except for code under development in current stories

## Team Projects and Production code
- High-level design document for the MVP or Epic(s) being implemented
- Internal Domain Model (High-level)
- JavaDoc for public classes, all interfaces, and public methods (not covered by an interface)

## Large Projects and with a Range of Skill Levels
- UML Structural documentation
- Comments for any code that junior team members may find confusing
- "One-page" discussions for non-trivial stories

## API to be Consumed by Other Teams 
- API Specification (endpoints and HTTP methods) with examples

## Teams Working with Third-Party Developers, Health care, Banking, etc.
- UML Use Case Diagrams
- UML Sequence Diagrams 
- UML Activity Diagrams
- ERD / Data Dictionary

# Comments
Dead code, when left in a project, can create confusion. It is better to maintain a changelog or revert to an older version if needed.

Comments should not simply reiterate what the code does. Instead, they should provide additional insights, clarify complex logic, or explain the reasoning behind certain decisions.
 