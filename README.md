Builder Portfolio Management System

-- Introduction:

This project is a console-based Java application built to simulate a simple Builder–Project Manager workflow in a construction portfolio system.

-- The goal of this project is not UI or frameworks, but to demonstrate:
* Clean Java coding practices
* Proper layering (View, Service, DAO, Model)
* Exception handling
* Enum usage
* Session handling
* Unit testing with JUnit
The application supports role-based behavior where the same system behaves differently depending on whether the logged-in user is a Project Manager or a Builder.

-- What This Application Does?
* At a high level, the system allows users to:
* Register and log in
* Be assigned a role (Manager or Builder)
* Perform actions based on their role
* Manage and track construction projects
* Maintain session state while the application is running
All data is stored in memory using Java collections to keep the focus on logic and design rather than database setup.

-- User Roles & Responsibilities

-> Project Manager:
* A Project Manager is responsible for managing projects.
* A manager can:
* Add new projects
* Delete existing projects
* View all projects assigned to them
* View their own profile details
* Log out safely (session cleared)
Each project created by a manager is automatically associated with that manager.

-> Builder
* A Builder is responsible for working on assigned projects.
- A builder can:
* View projects assigned to them
* Update project status (UPCOMING → IN_PROGRESS → COMPLETED)
* View their own profile details
* Log out safely
* Builders cannot create or delete projects — access is strictly controlled by role.

-- Project Status Handling

Project progress is tracked using an enum:
* UPCOMING
* IN_PROGRESS
* COMPLETED
* 
Using an enum ensures:
* Type safety
* Controlled values
* Cleaner logic compared to string constants

Enum behavior is also covered by JUnit tests.

-- Application Flow

1. Application starts from Main
2. MainMenuView is displayed
3. User chooses:
    Register
    Login
    Exit
4. After successful login or registration:
5. Session is created
6. User is redirected to the appropriate menu
7. Role-specific menu actions are shown
8. Logout clears the session and returns control safely

Architecture Overview:
The project follows a layered structure:

-- Session Management:
* A simple session mechanism is implemented using a static Session class.

Responsibilities:
* Store the currently logged-in user
* Provide access across views
* Clear session on logout


-- Exception Handling
* Custom exceptions are used to clearly represent business errors, such as:
* User already exists
* User not found
* Invalid login attempts
This keeps error handling explicit and readable, instead of relying on generic exceptions everywhere.

-- Testing Strategy
JUnit 5 is used for testing core logic.

Tests cover:
* Enum values and behavior
* User registration and login
* Duplicate user scenarios
* Invalid login attempts
* Edge cases like null inputs
* Testing ensures the service layer behaves correctly independent of the UI.

-- How to Run the Project:
1. Clone the repository:
git clone <repository-url>

2. Open the project in IntelliJ IDEA (or any Java IDE)

3. Run:
com.builderportfolio.Main

4. Use the console menus to interact with the system

