# To-Do List Application
This project is a full-stack to-do list management application, featuring both front-end and back-end components. Users can create, edit, delete, and view tasks with real-time updates on the front end.

## Tech Stack
Frontend: HTML, CSS, JavaScript
Backend: Spring Boot (Java), Spring Web
Database: MySQL (custom repository layer, no JPA used)
Dependency Management: Maven

## Installation and Setup
After clone the project
Database Setup: Create a MySQL database named to_do_list, and configure the connection details in: src\main\java\com\brianlin\to_do_list\repository\TaskRepository.

## Run the Application:
Use ./mvnw spring-boot:run to run the application locally.

## API Endpoints
### Add Task
POST /api/tasks: Adds a new task with details like title, description, and deadline.

### Get Tasks
GET /api/tasks: Retrieves all to-do tasks.

### Edit Task
PUT /api/tasks/{id}: Updates the details of a specific task.

### Delete Task
DELETE /api/tasks/{id}: Deletes a specific task.

## Frontend Features
The user interface provides full CRUD operations for tasks, with real-time updates via JavaScript to manage daily tasks efficiently.

## Project Structure
src/main/java/com/brianlin/to_do_list: Backend code, including model, controllers, service layer, and custom repository.
src/main/resources/static: Frontend static resources (HTML, CSS, JavaScript).
