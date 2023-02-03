# Library App
Java application that simulates a library.

## Programming language and technologies used
- Java
- Eclipse
- SQLite

## Description
Users must log in to access the library. If they are an admin, they are provided with admin features or else they are provided with user features. <br/>
Users are able to do the following:
- Borrow books.
- Return books.<br/>

Admin users are able to do the following: 
- Add new books.
- Remove existing books.
- Update existing books.
- View library members.
- Modify members' information
- Delete members.

## Implemented features
### Login
When a user enters the wrong login information (wrong username or password), a message will be displayed above the login form.

### Services file
An additional layer of validation that sets descriptive messages for admin (whether the transaction was successful or not).

### Validation
- Books that are borrowed (unavailable) cannot be borrowed by another user.
- Books that are available (not borrowed) cannot be returned.
- Books that are returned must be returned by the user who borrowed it.

## Future considerations
Implement validation and hash passwords for login.

## Credits
Created by Sharmaine Bajala.
