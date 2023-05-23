# **Project About**
## **Name :-** Recipe Management System API
## **Framework :-** Spring
## **Language :-** Java
## **Database** :- MySQL

---
## **Application Properties**
```
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/DP?createDatabaseIfNotExist=true
spring.datasource.username = shirish
spring.datasource.password = pass
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql = true
spring.jpa.properties.hibernate.format_sql = true
spring.jpa.hibernate.ddl-auto = update
```
## **Data Flow**
### **Controller Package**
This Package Handles the HTTP Request from the client.
It contains the **Three** classes
1. UserController
2. RecipeController
3. CommentController

**UserController**
1. **signUp:** This method handles a POST request at the URL /user/signUp. It expects a JSON payload in the request body representing a SignUpRequest object. The method calls the signUp method of the UserService class, passing the SignUpRequest object. It returns a SignUpResponse object representing the response from the UserService.
2. **signIn:** This method handles a POST request at the URL /user/signIn. It expects a JSON payload in the request body representing a SignInRequest object. The method calls the signIn method of the UserService class, passing the SignInRequest object. It returns a SignInResponse object representing the response from the UserService.
3. **signOut:** This method handles a DELETE request at the URL /user/signOut/{email}. It expects the email value to be provided as a path variable. The @PathVariable annotation is used to extract the email value from the URL path. The method calls the signOut method of the UserService class, passing the email value. It returns a ResponseEntity<String> object representing the HTTP response.

**Note:** The code snippet includes an @Autowired annotation on the UserService field, indicating that the UserService instance should be automatically injected into the controller by the Spring framework, assuming the UserService bean is properly configured.

**RecipeController**
1. **addRecipe:** This method handles a POST request at the URL /user/{email}/recipe/add. It expects a JSON payload in the request body representing a Recipe object. The @PathVariable annotation is used to extract the email value from the URL path. The method calls the addRecipe method of the RecipeService class, passing the Recipe object and the email value. It returns a ResponseEntity<String> object, which represents the HTTP response.
2. **removeRecipe:** This method handles a DELETE request at the URL /user/{email}/recipe/remove/{id}. It expects the id and email values to be provided as path variables. The @PathVariable annotation is used to extract the id and email values from the URL path. The method calls the removeRecipe method of the RecipeService class, passing the id and email values. It returns a ResponseEntity<String> object representing the HTTP response. 
3. **getRecipes:** This method handles a GET request at the URL /user/recipe. It does not expect any parameters. The method calls the getAllRecipe method of the RecipeService class, which should return a list of Recipe objects. It returns a ResponseEntity<List<Recipe>> object representing the HTTP response. 
4. **updateRecipe:** This method handles a PUT request at the URL /user/{email}/recipe/update. It expects a JSON payload in the request body representing a Recipe object. The @PathVariable annotation is used to extract the email value from the URL path. The method calls the updateRecipe method of the RecipeService class, passing the Recipe object and the email value. It returns a ResponseEntity<String> object representing the HTTP response.

**Note:** The code snippet includes an @Autowired annotation on the RecipeService field, indicating that the RecipeService instance should be automatically injected into the controller by the Spring framework, assuming the RecipeService bean is properly configured.

**CommentController**
1. **addCommentOnRecipeById:** This method handles a POST request at the URL /user/{email}/add. It expects a JSON payload in the request body representing a Comment object. The @PathVariable annotation is used to extract the email value from the URL path. The method calls the addCommentOnRecipeById method of the CommentService class, passing the Comment object and the email value. It returns a ResponseEntity<String> object representing the HTTP response.

**Note:** The code snippet includes an @Autowired annotation on the CommentService field, indicating that the CommentService instance should be automatically injected into the controller by the Spring framework, assuming the CommentService bean is properly configured.
___
### **dto Package**
A DTO (Data Transfer Object) package is a common practice in software development to organize and manage Data Transfer Objects, which are objects used to transfer data between different layers or components of an application.
1. **SignUpRequest:** A DTO class representing the request data for user sign up. It may contain fields like username, email, password, etc.
2. **SignUpResponse:** A DTO class representing the response data for user sign up. It may contain fields like success status, error messages, user ID, etc.
3. **SignInRequest:** A DTO class representing the request data for user sign in. It may contain fields like email and password.
4. **SignInResponse:** A DTO class representing the response data for user sign in. It may contain fields like success status, error messages, authentication token, user details, etc.
___
### **Service Package**
This is a Java Spring Boot service class that contains methods for performing CRUD (Create, Read, Update, Delete) operations on job objects. It is annotated with **@Service**, which is a Spring stereotype annotation indicating that this class is a service.
This package also consist of **Eight** classes and One Interface.
1. UserService
2. RecipeService
3. CommentService
4. TokenService

**UserService**
It includes functionalities related to user registration, login, and logout.
1. **signUp method:** This method is used to register a new user. It performs the following steps:
   1. Checks if the user already exists in the database based on the email provided. 
   2. Encrypts the password using the MD5 algorithm. 
   3. Creates a new User object with the provided details and saves it in the user repository. 
   4. Returns a response indicating successful registration.
   
2. signIn method: This method is used for user login. It performs the following steps:
   1. Checks if the user exists in the database based on the email provided. 
   2. Encrypts the password provided and compares it with the stored password. 
   3. If the password matches, a new authentication token is created and saved. 
   4. Returns a response indicating successful authentication along with the token.

3. signOut method: This method is used for user logout. It performs the following steps:
   1. Checks if the user exists in the database based on the email provided. 
   2. Validates if the user is logged in by checking if an authentication token exists for the user. 
   3. Removes the authentication token from the token repository. 
   4. Returns a response indicating successful logout.

4. isLoggedIn method: This method checks if a user is currently logged in based on the user ID.
   
**RecipeService**
1. **addRecipe method:** This method is used to add a new recipe. It performs the following steps:
   1. Checks if the user associated with the provided email exists in the database. 
   2. Validates if the user is logged in by checking if an authentication token exists for the user. 
   3. Sets the user as the owner of the recipe. 
   4. Saves the recipe in the recipe repository. 
   5. Returns a response indicating whether the recipe was saved successfully.

2. **removeRecipe method:** This method is used to remove a recipe. It performs the following steps:
   1. Checks if the user associated with the provided email exists in the database. 
   2. Validates if the user is logged in by checking if an authentication token exists for the user. 
   3. Retrieves the recipe by its ID. 
   4. Checks if the logged-in user is the owner of the recipe. 
   5. Deletes the recipe from the recipe repository. 
   6. Returns a response indicating whether the recipe was deleted successfully.

3. **getAllRecipe method:** This method is used to retrieve all recipes. It retrieves all recipes from the recipe repository and returns them as a response. If no recipes are found, a response with the appropriate status is returned.

4. **updateRecipe method:** This method is used to update a recipe. It performs the following steps:
   1. Checks if the user associated with the provided email exists in the database. 
   2. Validates if the user is logged in by checking if an authentication token exists for the user. 
   3. Retrieves the recipe by its ID. 
   4. Checks if the logged-in user is the owner of the recipe. 
   5. Updates the recipe's ingredients and instructions based on the provided recipe object. 
   6. Saves the updated recipe in the recipe repository. 
   7. Returns a response indicating whether the recipe was updated successfully.

**CommentService**
1. **addCommentOnRecipeById method:** This method is used to add a comment to a recipe. It performs the following steps:
   1. Checks if the user associated with the provided email exists in the database. 
   2. Validates if the user is logged in by checking if an authentication token exists for the user. 
   3. Retrieves the recipe by its ID from the recipe repository. 
   4. Checks if the recipe exists. 
   5. Saves the comment in the comment repository. 
   6. Returns a response indicating whether the comment was added successfully.

**TokenService**
1. **save method:** This method is used to save an authentication token. It simply delegates the saving operation to the tokenRepo (presumably a repository interface for managing tokens). 
2. **isPresent method:** This method is used to check if a token is present for a given user ID. It performs the following steps:
   1. Retrieves the token from the tokenRepo based on the user ID. 
   2. Returns true if a token is found, indicating that the user is currently logged in. Otherwise, it returns false.
3. **removeTokenByPatientId method:** This method is used to remove the token for a specific user. It performs the following steps:
   1. Deletes the token from the tokenRepo based on the user ID. 
   2. Returns the number of rows affected by the deletion.
___

### **Repository Package**
This package consist of interfaces. Which defines a set of methods that can be used to interact with the underlying database. The interface extends the JpaRepository interface and specifies the Job entity and the data type of its primary key Integer.
The interface contains several custom finder methods that use the naming convention of Spring Data JPA to automatically generate the queries.
In summary, this code implements a simple REST ful API for managing Job objects with endpoints for performing CRUD operations.
1. IUserRepo
2. IRecipeRepo
3. ICommentRepo
4. ITokenRepo

All the Interfaces extends JpaRepository.
___

### **Model Package**
All the class present in this package will be Present in the Database as Table.
This package consist of Four class :
1. User
2. Recipe
3. Comment
4. AuthenticationToken

All the common annotation in these classes are :
1. **@Data annotation:** This annotation is part of the Lombok library and is used to generate boilerplate code for the entity class, such as getters, setters, equals(), hashCode(), and toString() methods.
2. **@NoArgsConstructor annotation:** This annotation is from Lombok and generates a no-argument constructor for the entity class.
3. **@AllArgsConstructor annotation:** This annotation is from Lombok and generates an all-argument constructor for the entity class.
4. **@Entity annotation:** This JPA (Java Persistence API) annotation indicates that the class is an entity and should be mapped to a database table.
5. **@Id annotation:** This JPA annotation is used to mark the primary key field of the entity.
6. **@GeneratedValue annotation:** This JPA annotation specifies the generation strategy for the primary key. In this case, GenerationType.IDENTITY is used, which indicates that the primary key values are automatically generated by the database.

**User**
1. **private Integer id:** This field represents the primary key of the User entity.
2. **private String name:** This field represents the name of the user.
3. **@Email annotation:** This Javax Validation annotation is used to ensure that the email field follows a valid email format.
4. **private String email:** This field represents the email address of the user.
5. **private String password:** This field represents the password of the user.
6. **private LocalDate registeredDate:** This field represents the registered date of the user. It uses the LocalDate class from Java's date and time API.

**Recipe**
1. **private String name:** This field represents the name of the recipe.
2. **private String ingredients:** This field represents the ingredients of the recipe.
3. **private String instructions:** This field represents the instructions for preparing the recipe.
4. **@ManyToOne annotation:** This JPA annotation establishes a many-to-one relationship between the Recipe entity and the User entity.
5. **@JsonIgnore annotation:** This annotation is from the Jackson library and is used to ignore the user field during JSON serialization and deserialization. It prevents circular referencing issues.
6. **private User user:** This field represents the user who created the recipe. It is annotated with @ManyToOne to establish the relationship between the Recipe and User entities.

**Comment**
1. **private String text:** This field represents the text of the comment.
2. **@ManyToOne annotation:** This JPA annotation establishes a many-to-one relationship between the Comment entity and the Recipe entity.
3. **fetch = FetchType.LAZY attribute:** This attribute specifies that the associated Recipe entity should be loaded lazily when accessing the recipe field of a Comment entity.
4. **@JoinColumn(name = "recipe_id") annotation:** This annotation specifies the column used to join the Comment entity with the Recipe entity. In this case, the column name is "recipe_id".
5. **private Recipe recipe:** This field represents the recipe to which the comment is associated. It is annotated with @ManyToOne to establish the relationship between the Comment and Recipe entities.

**AuthenticationToken**
1. **private String token:** This field represents the authentication token generated for the user.
2. **@OneToOne annotation:** This JPA annotation establishes a one-to-one relationship between the AuthenticationToken entity and the User entity.
3. **@JoinColumn(nullable = false) annotation:** This annotation specifies the foreign key column used to join the AuthenticationToken entity with the User entity. The nullable = false attribute indicates that the User reference must always be present.
4. **private User user:** This field represents the user associated with the authentication token. It is annotated with @OneToOne to establish the one-to-one relationship between the AuthenticationToken and User entities.
5. **public AuthenticationToken(User user):** This is a constructor that takes a User object as a parameter. It generates a random UUID token and assigns it to the token field. The provided User object is assigned to the user field.

### **Summery Of Project**
The Recipe Management System API is a Spring Boot-based application that allows users to store and manage recipes. The API supports CRUD (Create, Read, Update, Delete) operations on recipes, as well as the ability for users to add comments to recipes and search for recipes based on various criteria.
The project includes the necessary annotations and relationships between the entities to establish the database mappings. It leverages Spring Boot's autoconfiguration and dependency injection capabilities to manage dependencies and simplify development.
To use the Recipe Management System API, you can make HTTP requests to the exposed endpoints for performing CRUD operations on recipes, adding comments, and managing user authentication.
This project serves as a foundation for building a Recipe Management System, and you can further enhance it by adding additional features such as user profile management, recipe categorization, and advanced search functionality.
