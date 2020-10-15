My own project "Internet shop" 
Project purpose.
Create a simple online store selling mobile phones with the ability to register new users and add new products to the store's catalog. 
Restricting access to pages depending on the role, there are two of them user and admin. 
Saving all information about the user and products in the database. 
The user selects products, adds them to the cart and places an order to purchase the selected products. 
The admin can view information about all users, and the product with the ability to edit and delete them as needed.

Project structure.
Project have N-tier architecture.
There is a Dao level where the operations of accessing the storage are described, at the first stage these were lists, but over time they went over and implemented the database.
There is a service level where the business logic of the project is implemented.
There is a controller layer that is responsible for receiving requests from users, processing and sending responses back.
And the level where the models of our store are located.

Implementation details.
The project was created on Java in IntelliJ IDEA in compliance with the SOLID principles. 
When creating, the following technologies were used: TomCat as a server, 
JSP for displaying pages with information, JDBC for executing queries to the database, 
Workbench MySQL for working with a database and Maven for assembling a project.

Launch guide.
Clone project from this repository.
Download and install TomCat server and add all the necessary dependencies to the pom.xml file required for Java servlets.
Install Workbench MySQL, install the necessary driver to connect to your database and change the driver in the class, your username and password, also specify the url address for connecting to the database. 
Create a schema in your database and using the queries that you will find in the resourses folder in the init_db file, queries to create all the necessary tables.
Run the application, first register as a new user and log in using the data you entered during registration. 
When you enter the site, click on the inject, this will load the information for testing the functionality into the database.
Test all accessible pages for the user. 
To stream the admin functionality, you need to log out of the system and log in with the login and password for the admin (login: admin, password: a), he loaded by pressing the inject button.

Author.
Kostiantyn Vorobiov
https://github.com/KostiantynVorobiov
