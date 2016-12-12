User Manual

After you launch this application in eclipse:

New User:
As a new user, you need to sign up first. User only needs to fill in username and password then click signup button. User will get a “username already taken” message indicating signup failure because no identical user name is allowed. User needs to change username. If user signs up successfully, user can try to login to this application.

Old User:
As a old user, you need to type in your username and password and then click login button. User will get a “login failure” message indicating the password and username combination is invalid.


After you login:
You will be redirected to search page. Left side bar is current user’s search history, listed from latest to oldest order, arranged by search item — search results  format. Users who never make a search before will have “No Search History” in this field. Up-left corner will show who is the current user.

In the “Find” field, you can type in the item you want to search and in the “Near” field, you can type in the location you want to search. “Search Limit” dropdown button allows you to limit your search results. “Find Now!” button will triggered the search if it is pressed.

After you search something, search results will be shown to search result field without any order. And a star bar will show at each line according to the rating of each search results. But now, the previously-grey-out “Sort By” button will be valid. In the dropdown button, user can choose sort those results by rating or total reviews.

If you want to look at their location more carefully, you can click “Show Map” button to trigger a new frame page where a jxbrowser and google map are embedded. Your search results will be marked in google maps. Opening google map page will not close search page. So current user can make multiple searches without re-login.





Open API

1. Yelp API
In our project application, we combinatively use Yelp Search and Business API. In order to obtain search results as String in JSON type, we've improved the API. The API take 3 arguments from user input: search term(e.g food name), location(e.g address, zip code etc) and result limitation. The Search API first search businesses satisfying above conditions. Then, we parse results strings into JSON object. It is easily to get business's id in JSON object and remove uninterested fields in return results. After that, business’s id is applied on Business API for searching specific info returning in type of String. 


2. Google Maps API
In our project application, we use Google Maps API to show the geographic information corresponding to search results from users. We pre-downloaded a map.html and use it as a basic map canvas. And we add a “show map” button in our search page. If this button is pressed by users, two things happen. First, an array list of JSON obj(search results) is passed to GoogleMap class and a new Frame will be triggered and inside this frame, a JXBrowser is embedded into this frame and the JXBrowser is responsible for showing a google Map webpage. According to the array list of JSON obj, we set our view to the center of current city and set markers on the maps according to the locations of results.



How to acquire and use this project:

Download this project from github. Unzip the zip file if you use Download Zip option. And open your Eclipse and try to import an existing project using this download folder. In this project, we used many external libraries, including json libraries, google-maps jar and many jxbrowser jars. In order to make user’s life easier, we put all the jars into a jars folder. And we build path to this local folder so that user need not worry about jars anymore. If jars are not properly downloaded (highly unlikely), user has to build path by themselves. 

Another thing to notice is JXBrowser we use now is a trial version. The license is only effective for 30 days. So after 30 days, in order to use this application, user needs to apply for a new trial license for JXBrowser and replace license.jar in the jars folder.


Structure of this project:

src folder contains all the java code. It has five parts: GUI, JSONParse, User, yelpAPI and Sort packages. 
user info folder contains all the confidential info and each user’s search history.
jars folder contains all the jars file this project requires.
pictures folder contains all the pictures this project requires.



