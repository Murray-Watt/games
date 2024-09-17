Exceptions can be cited for most of the guidelines sited here.
Team harmony, incremental improvements, and demonstrating 
the benefits of best practices are more important than rigid adoption. 
The exception to this opinion is when practices 
are mandated by contractual obligations or legal requirements.

# Documentation
This project may prioritize documentation over working code, which could potentially violate the principle of favoring 
working code over documentation. This principle is not anti-documentation but rather opposes vaporware. One should have 
sufficient documentation for the project without burdening it with excessive maintenance.

This is a list of documentation prioritized (first pass) by need, and does not include project management 
documentation such as threat modeling and retrospectives.

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
 