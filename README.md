# EduSys: Student and Teacher Management REST API

### Overview
The EduSys REST API is a modular backend service designed to manage educational entities, specifically Students, Teachers, and Subjects. It was built to demonstrate proficiency in creating a modern, relational, and validated Spring Boot application using Spring Data JPA for robust data persistence and relationship management.

The system allows full CRUD operations on all entities and implements complex OneToMany and ManyToMany relationships, including a specific endpoint for student enrollment.

### Core Features & Demonstrated Concept
| Concept | Implementation Details |
| :---: | :--- |
| Spring Boot / REST API | Uses `@RestController` and `@RequestMapping` to expose functional, resource-based endpoints (e.g., `/api/v1/students`). |
| Dependency Injection | `@Autowired` (implicitly via constructor) is used to inject Service and Repository layers into Controllers, promoting decoupled architecture. |
| Object-Relational Mapping | Uses Spring Data JPA and Hibernate to map Java Entities (`Student`, `Teacher`, `Subject`) to database tables. |
| Relational Modeling | Implemented ManyToOne (Subject → Teacher) and ManyToMany (Student ↔ Subject) relationships using `@ManyToOne` and `@ManyToMany` annotations. |
| Data Transfer Objects (DTOs) | Uses separate DTOs (`StudentDTO`, `TeacherDTO`, & `SubjectDTO`) for data input/output, separating the API contract from the JPA Entities. |
| Data Validation | Uses Jakarta Validation annotations (`@NotBlank`, `@Email`, `@NotNull`) on DTOs combined with `@Valid` in the Controllers for declarative input checks. |
| Model Mapping | Employs the ModelMapper library to efficiently map data between DTOs and JPA Entities, reducing boilerplate code. |
| Development Database | Uses an in-memory H2 Database for rapid development and testing; configured via `application.properties` with `ddl-auto=create-drop.` |

### Getting Started

#### Prerequisites
- Java Development Kit (JDK) 17+

- Apache Maven

- Postman or curl (for API testing

#### Build & Run
1. Clone the repository:
```bash
git clone https://github.com/samnjue/EduSys
cd edusys
```

2. Build the project (downloads dependencies):
```bash
mvn clean install
```

3. Run the application using the Spring Boot Maven plugin:
```bash
mvn spring-boot:run
```
4. The application will start on the port configured in `application.properties`.

### API Usage and Endpoints
The base URL for the API is `http://localhost:8800/api/v1/`.
| Resource | HTTP Method | Endpoint | Description |
| :---: | :---: | :---: | :---: |
| Student | `POST` | `/students` | Creates a new student record. |
| Student | `GET` | `/students/{id}` | Retrieves a student by ID. |
| Teacher | `POST` | `/teachers` | Creates a new teacher record. |
| Teacher | `GET` | `/teachers` | Retrieves all teachers. |
| Subject | `POST` | `/subjects` | Creates a new subject and assigns a Teacher ID. |
| Subject | `GET` | `/subjects/1` | Retrieves Subject ID 1, including enrolled student IDs. |
| Enrollment | `GET` | `/subjects/{subjectId}/enroll/{studentId}` | Business Logic: Links an existing Student to an existing Subject (ManyToMany). |

#### Example Enrollment Command (PowerShell)
```bash
Invoke-RestMethod -Method POST -Uri http://localhost:8800/api/v1/subjects/1/enroll/1
```

### Author
- Sammy Njue
- Built as a practical demonstration of modern Spring Boot, REST API design, and Relational Database principles.
