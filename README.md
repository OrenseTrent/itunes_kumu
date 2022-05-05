# AU Movies
Show the AU movies using itunes web service api.
You can search movies in AU and clicking an item will redirect you to it's details, containing a movie preview, movie title, author, price, genre, release date, and a long description regarding the movie.


# MVC - MVVM architecture
The app uses a mix of MVC and MVVM architecture written in Java. I chose this architecture since everything is plug and play, and is written and enhanced by my team and I. You can take a look for yourself.
Code base is understandable, everything is done so developers can easily integrate and develop their apps.

I will transition into full MVVM architecture once I learn more about the tech stack, and since it is recommended by google.


# APIs used on the app
- https://itunes.apple.com/search
- https://itunes.apple.com/lookup

Retrofit library is used to read and parse JSON response.

# Room Persistence
The app stores data on a local database using Room.
Data is added to a local database and will be used when the device has on internet connection(has an error displaying saved data)

Documentation regarding the api: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching




