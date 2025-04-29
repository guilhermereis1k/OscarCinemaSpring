# OscarCinemaSpring

## Description: 
A cinema application made with Spring Boot, ReactJs Postgre server, you can insert movies and users to create sessions that can be ordered by users, also considering things like type of exhibition, seats selected, session number and if the movie is dubbed or not. 

| Request Methods | Path                           | Method                    | What it does |
|-----------------|--------------------------------|---------------------------|--------------|
| POST            | `/create`                      | `insertMovie()`           | Creates a new movie from a `Movie` object. |
| GET             | `/`                            | `findAllMovies()`         | Returns all registered movies. |
| GET             | `/search/id/{id}`             | `findMovieById()`         | Searches for a movie by `id`. |
| DELETE          | `/delete/{id}`                | `removeMovie()`           | Removes a movie by `id`. |
| PATCH           | `/change/{id}`                | `changeMovie()`           | Updates movie details (name, image, description, minimum age). |

| Request Methods | Path                           | Method                    | What it does |
|-----------------|--------------------------------|---------------------------|--------------|
| POST            | `/create`                      | `insertOrder()`           | Creates a new order by associating a user, session, and seats. |
| GET             | `/id/{id}`                     | `findOrderById()`         | Returns a specific order by `id`. |
| DELETE          | `/delete/{id}`                | `removeOrderById()`       | Removes an order by `id`. |
| PATCH           | `/change/{id}`                | `changeOrder()`           | Updates seats, user, or session of the order. |

| Request Methods | Path                               | Method                      | What it does |
|-----------------|------------------------------------|-----------------------------|--------------|
| POST            | `/create`                          | `insertSession()`           | Creates a new session associated with a movie. |
| GET             | `/`                                | `findAllSessions()`         | Returns all registered sessions. |
| GET             | `/id/{id}`                         | `findSessionById()`         | Returns a specific session by `id`. |
| GET             | `/users/{sessionId}`               | `findUsersBySessionId()`    | Returns the list of users with orders in that session. |
| GET             | `/movie/{movieId}`                 | `findSessionsByMovieId()`   | Lists all sessions associated with a movie. |
| DELETE          | `/delete/{id}`                    | `deleteSession()`           | Removes a session by `id`. |
| PATCH           | `/change/{id}`                    | `changeSession()`           | Updates session details such as room, time, projection type, dubbing, and movie. |

| Request Methods | Path                                    | Method                    | What it does |
|-----------------|-----------------------------------------|---------------------------|--------------|
| POST            | `/create`                               | `insertUser()`            | Creates a new user based on a DTO. |
| GET             | `/search/id/{id}`                       | `findUserById()`          | Returns a user by `id`. |
| GET             | `/search/documentId/{documentId}`       | `findUserByDocumentId()`  | Returns a user by `documentId`. |
| DELETE          | `/delete/{id}`                          | `removeUser()`            | Removes a user by `id`. |
| PATCH           | `/change/{id}`                          | `changeUser()`            | Updates user details such as name and document. |
