# tekone_inventory
README File for Inventory System

Overview:
The inventory system is a Java-based application designed to manage the inventory and orders for an e-commerce website. The system offers various features, including chart-based reports, product management, order management, customer management, and admin management.

System Requirements:
To run this application, the user must have the following:

Java Development Kit (JDK) 11 or higher
MySQL Server 5.7 or higher
Apache Tomcat 8.5 or higher
Internet Browser
Installation:
To install and run the application, follow these steps:

Clone the repository to your local machine using git clone.
Install the JDK, MySQL Server, and Apache Tomcat on your system.
Import the database schema provided in the "database" folder using MySQL Workbench or any other MySQL management tool.
Configure the database connection in the "db.properties" file located in the "src" folder.
Build the application using Apache Maven by executing the command "mvn clean install".
Deploy the application to the Tomcat server.
Open a web browser and navigate to "http://localhost:{tomcat_port}/inventory-system".
Usage:
After installation, users can access the inventory system by logging in using their credentials. The system offers various functionalities, including:

Product Management: Users can add, modify, or delete products, including product name, description, price, and quantity.
Order Management: Users can view and modify order details, including order number, customer information, and product information.
Customer Management: Users can add, modify, or delete customer details, including name, email, phone, and address.
Admin Management: Users can add, modify, or delete admin credentials.
Reports: Users can view chart-based reports that display the quantity of stock available for each product, the most ordered products, and the customers' orders.
Contributing:
We welcome contributions from the community. Please fork the repository and create a pull request for any feature enhancements, bug fixes, or documentation improvements.

