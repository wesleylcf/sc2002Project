# Hotel Reservation and Payment System
A CLI-based project done for SC2002 Object-Oriented Design and Programming in Y1S2. 

Utilized a MVC design for each slice(package) of the App. Controllers are in charge of routing to different menus(stored in View) and calling the appropriate methods from Model. 

There are also DB classes are used for reading and writing to a .txt files for each section(package) of the app, Data classes which store the local application state and are initialized with what is read from the DB classes. A singleton design was used for DB classes since it makes sense to only construct it once and use the same instance if necessary(admittedly this could be extended to the other types of classes as well)

Finally there is the InputValidator classes which is used to validate input, either returning the string is valid or throwing the appropriate (custom) Exceptions which are handled by ExceptionHandler class.

The entry of the application is the hrps package.
