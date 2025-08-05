# Kotlin Room Database CRUD System for Menu Items
## Project Description
This project demonstrates a complete CRUD (Create, Read, Update, Delete) system for managing menu items using Kotlin and Room Database with Android Architecture Components. The application allows users to perform all basic database operations with proper validation checks.

### Features
1. Room Database Implementation
* Entity class for MenuItem with fields: id, code, name, salePrice, cost, inactive status
* DAO interface with CRUD operations
* Database class with singleton pattern
2. MVVM Architecture
* Repository layer to handle data operations
* ViewModel to manage UI-related data
* LiveData for observable data changes
3. UI Components
* RecyclerView to display menu items
* Floating Action Button for adding new items
* Edit and Delete functionality via RecyclerView buttons
* Dialog boxes for add/edit operations
4. Validation
* Name duplication check
* Input validation for required fields
* Price validation
5. Additional Features
* Proper error handling
* Clean UI/UX
* Responsive design

### Technical Stack
* Kotlin
* View Binding
* Android Jetpack Components:
  - Room Database
  - ViewModel
  - LiveData
  - RecyclerView
  - Material Design Components

### How to Use
1. Adding a Menu Item
* Click the Floating Action Button
* Fill in the details in the dialog
* System will validate inputs and check for duplicate names
* Save to add the item
2. Editing a Menu Item
* Click the edit button on the item card
* Modify the details in the dialog
* Save changes
3. Deleting a Menu Item
* Click the delete button on the item card
* Confirm deletion
4. Viewing Menu Items
* All items are displayed in the RecyclerView
* Scroll to see all items

### Setup Instructions
1. Clone the repository
2. Open in Android Studio
3. Build and run the project

### Future Enhancements
1. Add search functionality
2. Implement sorting/filtering options
3. Add category support for menu items
4. Include image support for items
5. Implement backup/restore functionality

This project serves as a good example of modern Android development with Kotlin, Room Database, and MVVM architecture. It demonstrates proper separation of concerns and follows Android best practices.
