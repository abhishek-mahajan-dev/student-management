# Student Management Application

This is a simple web application built with Spring Boot (Java) for the backend and plain HTML, CSS, and JavaScript for the frontend. It allows users to add, view, and manage student records, including their subjects, marks, calculated average marks, and grades. The application features persistent storage using local file I/O (JSON) and a search functionality to filter students by name.

---

## Features

* **Add New Student**: Register new students with a unique ID, name, and multiple subjects with associated marks.

* **View All Students**: Display a table of all existing student records.

* **Calculate Performance**: Automatically computes the average marks and assigns a letter grade (A, B, C, D, F) for each student based on their subject marks.

* **Persistent Storage**: All student data is saved to a `students.txt` file (in JSON format) on the server, ensuring data is not lost when the application restarts.

* **Search Students**: Filter the student list in real-time by typing a student's name in the search bar.

* **Sort by Performance**: Sort the student list in ascending or descending order based on their average marks.

* **Enhanced User Feedback**: Uses a custom Bootstrap modal for displaying messages and validation errors instead of native browser `alert()` pop-ups.

---

## Technologies Used

### Backend

* **Spring Boot**: Framework for building robust, stand-alone, production-ready Spring applications.

* **Java 17**: The programming language.

* **Maven**: Build automation tool for dependency management and project lifecycle.

* **Gson**: Google's JSON library for converting Java objects to JSON and vice-versa.

* **SLF4J/Logback**: For structured logging within the application.

### Frontend

* **HTML5**: Structure of the web pages.

* **CSS3**: Styling of the web pages, including responsive design.

* **JavaScript (ES6+)**: Frontend interactivity, API calls, and DOM manipulation.

* **Bootstrap 5.3**: Responsive CSS framework for modern UI components and styling.

---

## Setup and Running

To get this application up and running on your local machine, follow these steps:

### Prerequisites

* **Java Development Kit (JDK) 17 or higher**: Make sure you have JDK 17 installed and configured.

* **Maven 3.6.x or higher**: Ensure Maven is installed and accessible from your command line.

* **Git (Optional but Recommended)**: For cloning the repository.

* **A Web Browser**: Chrome, Firefox, Edge, etc.

### Running the Application

1.  **Clone the Repository (if applicable) or Navigate to Project:**
    If you've received this project as a ZIP, extract it. If it's in a Git repository, clone it:

    ```bash
    git clone <repository-url>
    cd studentapp
    ```

    Replace `<repository-url>` with the actual URL.

2.  **Build the Project:**
    Open your terminal or command prompt, navigate to the `studentapp` root directory (where `pom.xml` is located), and run the Maven build command:

    ```bash
    mvn clean install
    ```

    This command compiles the Java code, runs tests, and packages the application into an executable JAR file.

3.  **Run the Spring Boot Application:**
    After a successful build, you can run the application using the Spring Boot Maven plugin:

    ```bash
    mvn spring-boot:run
    ```

    Alternatively, you can run the generated JAR file:

    ```bash
    java -jar target/studentapp-0.0.1-SNAPSHOT.jar
    ```

    (Note: The exact JAR name might vary based on your `pom.xml` configuration, typically `your-artifactid-version.jar`).

4.  **Access the Application in Your Browser:**
    Once the application starts (you'll see messages in your console indicating Tomcat initialization on port 8080), open your web browser and go to:

    ```
    http://localhost:8080
    ```

    You should now see the Student Management System's web interface. You can add new students, view existing ones, search, and sort. Student data will be saved to `students.txt` in your project's root directory.

---
