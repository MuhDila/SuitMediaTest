# SuitMediaTest

This Android application, SuitMediaTest, is designed with a user-friendly interface and features three distinct screens for a seamless user experience.

## Screenshots

![Screenshot 1](https://res.cloudinary.com/dhadtef9h/image/upload/v1703864965/Suitmedia_Test_on5tb0.png)

## Features

### 1. First Screen

- The first screen includes two input fields for name and a sentence.
- Users can check if the entered sentence is a palindrome by clicking the "Check" button.
- A dialog will appear with the message "isPalindrome" if the sentence is a palindrome and "not palindrome" otherwise.
- The "Next" button navigates to the Second Screen.

### 2. Second Screen

- The second screen welcomes users with a static label.
- It displays two dynamic labels for the name entered on the First Screen and the selected user's name.
- A button labeled "Choose a User" allows users to proceed to the Third Screen.

### 3. Third Screen

- The third screen features a list or table view of users.
- Data is fetched from the Regres.in API, including email, first_name, last_name, and avatar.
- Pull-to-refresh functionality is implemented, allowing users to refresh the list.
- Loading the next page occurs when scrolling to the bottom of the list.
- An empty state is prepared if no data is available.
- Clicking on a user item updates the selected user's name on the Second Screen without navigating to a new screen.

## Technologies Used

- Kotlin
- Android Jetpack
- MVVM Architecture
- Retrofit
- Paging 3
- Dialogs

## Installation

To run SuitMediaTest on your local environment, follow these steps:

1. Clone this repository: `git clone https://github.com/MuhDila/SuitMediaTest.git`
2. Open the project in Android Studio.
3. Build and run the app on your device or emulator.

## Credits

SuitMediaTest is a creation of Muhammad Dila. Feel free to reach out for any inquiries or suggestions.
