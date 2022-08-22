# RecipeService

*Recipe Service* is an Spring Boot Application which allows users to manage their favourite recipes. It allows adding, updating, removing and fetching recipes.

## Building
Application was built using Java 11, Spring Boot v2.7.2 and Maven 3.2.0.

## Running the application
Run the following commands:

- mvn clean install (Build's the Application)
- mvn spring-boot:run (Run's the application)

Resource endpoints:
-------------------
Use API tools like Post Man to test the end point's 

**Adding Recipe**:

`POST` `http://localhost:7080/recipes/addRecipe`

Request Body : 
```
{
    "dishType": "vegetarian",
    "noOfServings":4,
    "ingredients":{
        "include": "potatoes",
        "exclude": "salmon"
    },
    "recipeInstructions":"cook in oven"
}
```
**Fetching Recipes**:

`GET` `http://localhost:7080/recipes/fetchRecipes`

Request Body : Send empty request body `{}` to fetch all recipes
```
{
    "dishType": "vegetarian", 
    "noOfServings":4,
    "ingredients":{
        "include":"potatoes"
    },
    "recipeInstructions":"oven"
}
```

**Updating Recipe**:

`PUT` `http://localhost:7080/recipes/updateRecipe/{recipeId}`

Query params:
- recipeId- **1** 

Request Body :
```
{
    "recipeId": 1,
    "dishType": "vegetarian",
    "noOfServings":4,
    "ingredients":{
        "include":"potatoes"
    },
    "recipeInstructions":"oven"
}
```
**Removing Recipe**:

`DELETE` `http://localhost:7080/recipes/removeRecipe/{recipeId}`

Query params:
- recipeId- **1** 