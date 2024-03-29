🍳 PharmaInventoryManagement

Title: "Pharma Inventory Management System with Hardcoded Role-Based Access Control (RBAC)"

Description:
Our project is an Inventory Management System engineered to facilitate operations related to inventory, customers, sellers, and orders, with the added feature of Hardcoded Role-Based Access Control (RBAC). In this system, roles such as admin, manager, and staff have been hardcoded into the application to determine access and permissions for users. With hardcoded RBAC, specific functionalities are assigned to each role, ensuring that only authorized personnel can perform actions like product management, order processing, and customer account maintenance. This system offers a straightforward approach to secure data access and management, making it suitable for smaller-scale applications and projects where a more dynamic RBAC system may not be necessary.

Purpose
This application was developed as a final project during my training period This is initial module developed by me and looking forward to add additional modules and integrate them with this. This project helped me familiarize with the issues that occur in real-time projects such as maintaining consistent database schema, variable names, libraries versions etc.

Related Informationa about the system.

1. Customer Class
Customers interact with the system directly in order to place order, modify order, get bill. Customer has to registered to place an order. Information is stored in DataBase. The process of order taking starts from customers registering in the website for placing order and then the other series of events begin.

2. Seller
Sellers have different kinds of Medicines/Pharmaceuticals stored against their unique SellerId/SellerEmailId. He has authority to add several products he wants to sell. Moreover, A seller could also purchase as per his needs so inorder to place order. He can add/remove only the products against his SellerId. Seller Information is also stored in Database.

3. Inventory
This could only be accessed by Registered Sellers/Admin in the system. It's like a warehouse comprising of differnet types of Pharma Products where the sellers procure/store their items.

4. Product
This is the master data to be fed in to the system. Sellers can only have different kinds of Medicines/Pharmaceuticals that are available in the Product DataBase. Only the admin has authority to add new products.

5. Admin
Admin’s job is to manage the inventory, product, customer, seller information in the system.

How to Run:
In order to have a look at the code files and understand the working, simply download this repository and open in Spring Tool Suite/ Eclipse IDE/ IntelliJ and import to the downloaded project and run it. Upon loading, hit the respective urls on PostMan to test different functionalities.
