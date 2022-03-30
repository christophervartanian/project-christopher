# project-christopher-isha-noah-roshan
#### Project Description

Our project is meant to be similar to Apple's Weather App. We plan to be able to show actual and predicted temperatures and precipitation for a given date or date range. 

#### What user stories were completed this iteration?

In this iteration we spent a long time working with a weather API in Java. We spent a while looking at tutorials but were unable to successfully import APIs, especially after having trouble handling a variety of errors. We decided to switch to using a dataset including historical weather data from Honolulu to play around with because we felt we could still manipulate the data in some interesting ways. We have a temperature CSV set up and a precipitation CSV ready to go. In this iteration the user has the ability to input a date from 1940 to 2026 and our program will return the actual low temperature, actual high temperature, predicted low temperature, and the predicted high temperature.

#### What user stories do you intend to complete next iteration?

We will potentially use a weather API which will allow for a lot more user stories. Some other things we plan on implementing are:
* Computing the average temperature over an inputted date range.
* Showing the actual and predicted precipitation for an inputted date.
* Computing the total precipitation over an inputted date range.

#### Is there anything that you implemented but doesn't currently work?

The API we were trying to implement did not end up working. We still have an XML file in because we will try implementing one more time before the next iteration before departing from the idea entirely.

#### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
* javac src/WeatherData.java
* java src/WeatherData

