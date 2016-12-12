

Open API

1. Yelp API
In our project application, we combinatively use Yelp Search and Business API. In order to obtain search results as String in JSON type, we've improved the API. The API take 3 arguments from user input: search term(e.g food name), location(e.g address, zipcode etc) and result limitation. The Search API first search businesses satisfying above conditions. Then, we parse results strings into JSON object. It is easily to get business's id in JSON object and remove uninsterested fields in return results. After that, business'id is applied on Business API for searching specific info returning in type of String. 