# Order Notifier System Readme

## Overview

The Order Notifier System is a module designed to manage and notify users about orders, shipments, and cancellations. The system is implemented using Spring Boot and consists of several controllers, services, and models to handle different aspects of the order management process.

## Project Structure

The project is organized into several packages, each serving a specific purpose:

### 1. **orders.controller**

   - **NotificationController.java:** Manages notifications related to order placement, shipment, and cancellation.

   - **OrderController.java:** Handles order-related operations such as placing orders, canceling orders, and managing compound orders.

   - **ProductController.java:** Manages product-related operations, including viewing products, adding new products, and deleting products.

   - **ShoppingCartController.java:** Controls shopping cart operations like adding items, removing products, and displaying the cart.

   - **UserController.java:** Manages user-related operations, including adding users and retrieving user information.

### 2. **orders.Notfications**

   - Contains notification templates for order placement, shipment, and cancellation in both Arabic and English.

### 3. **orders.model**

   - **CompoundOrder.java:** Represents a compound order that may consist of multiple individual orders.

   - **Order.java:** Represents a single order.

   - **Product.java:** Represents a product.

   - **Response.java:** Represents a response from the system.

   - **User.java:** Represents a user.

### 4. **orders.service**

   - **OrderBSL.java:** Provides business logic for order-related operations.

   - **ProductDBImp.java:** Implements product-related database operations.

   - **ShoppingCartImp.java:** Manages shopping cart operations.

   - **UsersImp.java:** Implements user-related operations.

### 5. **orders.queue**

   - **NotificationsQueue.java:** Represents a queue for managing notifications.

### 6. **Readme.md**

   - The readme file providing an overview of the Order Notifier System.

## Usage

1. **OrderController:**

   - **Endpoint:** `/placeOrder/{username}`
      - **Usage:** Place an order for the specified username.
      - **Parameters:** `username` - User's username.

   - **Endpoint:** `/cancelOrder/{username}/{orderId}`
      - **Usage:** Cancel the specified order for the given username.
      - **Parameters:** `username` - User's username, `orderId` - ID of the order to be canceled.

   - **Endpoint:** `/placeOrders`
      - **Usage:** Place multiple orders.
      - **Parameters:** `usernames` - List of usernames for which orders should be placed.

   - **Endpoint:** `/getOrders`
      - **Usage:** Retrieve a list of all orders.

   - **Endpoint:** `/getOrder/{orderId}/{username}`
      - **Usage:** Retrieve details of a specific order for the given username and order ID.
      - **Parameters:** `orderId` - ID of the order, `username` - User's username.

   - **Endpoint:** `/checkout/{username}/{orderId}`
      - **Usage:** Perform checkout for the specified order and username.
      - **Parameters:** `username` - User's username, `orderId` - ID of the order to be checked out.

   - **Endpoint:** `/shipOrder/{username}/{orderId}`
      - **Usage:** Ship the specified order for the given username.
      - **Parameters:** `username` - User's username, `orderId` - ID of the order to be shipped.

   - **Endpoint:** `/cancelOrderShipping/{username}/{orderId}`
      - **Usage:** Cancel the shipping of the specified order for the given username.
      - **Parameters:** `username` - User's username, `orderId` - ID of the order for which shipping should be canceled.

   - **Endpoint:** `/cancelCompoundOrder/{orderId}`
      - **Usage:** Cancel all placements within a compound order.
      - **Parameters:** `orderId` - ID of the compound order to be canceled.

   - **Endpoint:** `/ShipCompoundOrder/{orderId}`
      - **Usage:** Ship all placements within a compound order.
      - **Parameters:** `orderId` - ID of the compound order to be shipped.

   - **Endpoint:** `/cancelCompoundShipping/{orderId}`
      - **Usage:** Cancel the shipping of all placements within a compound order.
      - **Parameters:** `orderId` - ID of the compound order for which shipping should be canceled.

   - **Endpoint:** `/getCompoundOrder/{orderId}`
      - **Usage:** Retrieve details of a compound order.
      - **Parameters:** `orderId` - ID of the compound order.

   - **Endpoint:** `/CheckoutCompoundOrder/{orderId}`
      - **Usage:** Perform checkout for all placements within a compound order.
      - **Parameters:** `orderId` - ID of the compound order to be checked out.

2. **ProductController:**

   - **Endpoint:** `/makeMenu`
      - **Usage:** Create a menu of products.

   - **Endpoint:** `/products`
      - **Usage:** View all products.

   - **Endpoint:** `/getProduct/{name}`
      - **Usage:** Retrieve details of a specific product.
      - **Parameters:** `name` - Name of the product.

   - **Endpoint:** `/addProduct`
      - **Usage:** Add a new product.
      - **Parameters:** Product details in the request body.

   - **Endpoint:** `/deleteProduct/{name}`
      - **Usage:** Delete a product.
      - **Parameters:** `name` - Name of the product to be deleted.

3. **ShoppingCartController:**

   - **Endpoint:** `/addToCart/{username}/{productName}/{quantity}`
      - **Usage:** Add a product to the user's shopping cart.
      - **Parameters:** `username` - User's username, `productName` - Name of the product, `quantity` - Quantity of the product.

   - **Endpoint:** `/removeProduct/{username}/{productName}`
      - **Usage:** Remove a product from the user's shopping cart.
      - **Parameters:** `username` - User's username, `productName` - Name of the product to be removed.

   - **Endpoint:** `/displayCart/{username}`
      - **Usage:** Display the contents of the user's shopping cart.
      - **Parameters:** `username` - User's username.

4. **UserController:**

   - **Endpoint:** `/addUser`
      - **Usage:** Add a new user.
      - **Parameters:** User details in the request body.

   - **Endpoint:** `/get/{username}`
      - **Usage:** Retrieve details of a specific user.
      - **Parameters:** `username` - User's username.
    
5. **NotificationController:**

   - **Endpoint:** `/sendPlacementNotificationEmailAr/{username}/{email}`
      - **Usage:** Send order placement notification email in Arabic.
      - **Parameters:** `username` - User's username, `email` - User's email.

   - **Endpoint:** `/sendPlacementNotificationSmsAr/{username}/{phoneNumber}`
      - **Usage:** Send order placement notification SMS in Arabic.
      - **Parameters:** `username` - User's username, `phoneNumber` - User's phone number.

   - **Endpoint:** `/sendShipmentNotificationEmailAr/{username}/{email}`
      - **Usage:** Send order shipment notification email in Arabic.
      - **Parameters:** `username` - User's username, `email` - User's email.

   - **Endpoint:** `/sendShipmentNotificationSmsAr/{username}/{phoneNumber}`
      - **Usage:** Send order shipment notification SMS in Arabic.
      - **Parameters:** `username` - User's username, `phoneNumber` - User's phone number.

   - **Endpoint:** `/sendCancelPlacementNotificationEmailAr/{username}/{email}`
      - **Usage:** Send cancel placement notification email in Arabic.
      - **Parameters:** `username` - User's username, `email` - User's email.

   - **Endpoint:** `/sendCancelPlacementNotificationSmsAr/{username}/{phoneNumber}`
      - **Usage:** Send cancel placement notification SMS in Arabic.
      - **Parameters:** `username` - User's username, `phoneNumber` - User's phone number.

   - **Endpoint:** `/sendCancelShipmentNotificationEmailAr/{username}/{email}`
      - **Usage:** Send cancel shipment notification email in Arabic.
      - **Parameters:** `username` - User's username, `email` - User's email.

   - **Endpoint:** `/sendCancelShipmentNotificationSmsAr/{username}/{phoneNumber}`
      - **Usage:** Send cancel shipment notification SMS in Arabic.
      - **Parameters:** `username` - User's username, `phoneNumber` - User's phone number.

   - **Endpoint:** `/getMostUsedTemplate`
      - **Usage:** Retrieve the most used notification template.


## Dependencies

The project uses Spring Boot and relies on the following dependencies:

- **Spring Web:** For building web applications.
- **Lombok:** To reduce boilerplate code in the models.
- **Spring Data JPA:** For data access using the Java Persistence API.
- **Spring Boot DevTools:** For automatic application restarts during development.

## Getting Started

1. Clone the repository.
2. Open the project in your preferred Java IDE.
3. Configure the application properties as needed.
4. Run the application.

## Contributors

- Youssef Zakaria
- Maram Wael
- Tareq Mahfouz
- Zeyad Elnaggar


Feel free to contribute, report issues, or suggest improvements!
