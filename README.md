# My own project "Internet shop" 

## Project purpose.
To create a simple online store selling mobile phones with the ability to register new users and add new products to the store's catalog. 
Access to pages  is restricted depending on the website visitor's role; there are two roles: user and admin. 
All information about the user and products is saved in the database. 
The user selects products, adds them to the cart and places an order to purchase the selected products. 
The admin can view information about all users and the product with the ability to edit and delete them as needed.


## Project structure.
The project has N-tier architecture.
There is a Dao level which defines the operations for accessing the storage. At first, the storage was implemented as lists, and, at a later stage in project development, access to a MySQL database was implemented.
There is a service level where the business logic of the project is implemented.
There is a controller layer that is responsible for receiving requests from users, processing and sending responses back.
The presentation layer, which we implemented with JSP.

## Implementation details.
The project was created on Java in IntelliJ IDEA in compliance with the SOLID principles. 
The technologies used in the project are as follows: 
- TomCat as the server;
- JSP for displaying web pages;
- JDBC for executing queries to the database; 
- MySQL Workbench for work with the database;
- Maven for assembling the project.

## Launch guide.
1. Clone the project from this repository.
2. Download and install the Tomcat server and add all the necessary dependencies for Java servlets to the `pom.xml` file.
3. Install MySQL Workbench, in class `ConnectionUtil` install the necessary driver to connect to your database and change the driver in the class, insert your username and password, and specify the url address for connecting to the database. 
4. Create a schema in your database using the queries that you will find in the resources folder in the `init_db` file. When you run the application, first register as a new user and log in using the data you entered during registration. When you log on to the site, click on the `Inject` button, which will load the data for testing the website functionality into the database.
5. Test all pages accessible for the user. 
6. To try out the admin functionality, you need to log out of the system and log in with the admin login and password (login: _admin_, password: _a_). The admin profile will have been created when you clicked on the inject button.
## Author.
[Kostiantyn Vorobiov](https://github.com/KostiantynVorobiov)