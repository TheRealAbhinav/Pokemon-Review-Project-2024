# Pokemon-Review-Project-2024
A simple CRUD application to add, delete, modify and/or fetch a pokemon from database.
Along with additional functionaltiy to add/delete/fetch/create multiple reviews for a single pokemon.

# Dependencies Used
![Alt text](<Screenshot 2024-01-26 at 8.05.43 PM.png>)

# Tech used
- Spring boot 3
- Java 17
- MySQl 8
- Git and Github
- Postman

# REST APIs
## Pokemons
- [GET] http://localhost:8080/api/pokemons - Will return all pokemons
- [GET] http://localhost:8080/api/pokemons/page?pageNo=0&pageSize=5 - Will return all pokemons in Pagination format
- [GET] http://localhost:8080/api/pokemon/1 - Will return pokemon of id 1
- [POST] http://localhost:8080/api/pokemon - Will add a pokemon to the DB
- [PUT] http://localhost:8080/api/pokemon/1 - Will update the pokemon with id 1 with the given update
- [DELETE] http://localhost:8080/api/pokemon/1 - Will remove the pokemon with id 1

## Reviews
- [POST] http://localhost:8080/api/pokemons/1/review - Will create a new review for pokemon with id 1
- [GET] http://localhost:8080/api/pokemons/1/reviews - Will return all the reviews of pokemon with id 1
- [GET] http://localhost:8080/api/pokemons/reviews/1 - Will return the review with id 1
- [GET] http://localhost:8080/api/pokemons/1/reviews/2 - Will return the review with id 2 of pokemon with id 1
- [PUT] http://localhost:8080/api/pokemons/1/reviews/2 - Will perform an update on review of id 2 in pokemon with id 1
- [DELETE] http://localhost:8080/api/pokemons/1/reviews/2 - Will remove review of id 2 in pokemon with id 1

# Swagger
- http://localhost:8080/swagger-ui/index.html#/
![alt text](<Screenshot 2024-02-05 at 6.03.29 PM.png>)