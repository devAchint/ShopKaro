# ShopKaro

Shopkaro is an e-commerce app that utilizes modern Android technologies, including Jetpack Compose, MVVM architecture, Retrofit, and Hilt. The app allows users to authenticate, view products, add them to the cart, and place orders using Firebase Authentication and Firebase Realtime Database. The products are fetched from the Fakestore API.

## Features
1. Authentication using Firebase Authentication with email and password.
2. Dependency injection with Hilt.
3. Fetches products using the Fakestore API.
4. Users can add products to the cart, which is stored in Firebase Realtime Database.
5. Users can place orders by adding shipping details and choosing a payment method.
6. Utilizes nested navigation and a bottom bar.
7. Fully created with Jetpack Compose.


## Screenshot

|                                                                                                                         |                                                                                                               |                                                                                                                |
|-------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| ![login](https://github.com/user-attachments/assets/147360a5-b685-4c84-afbf-78f4e7eae6fc)                               | ![signup](https://github.com/user-attachments/assets/2545a0d9-3005-4356-9720-d4830b9152ed)                    | ![home](https://github.com/user-attachments/assets/77eb7c2b-c491-4d0d-8579-bf2e9c5e6bb1)                       |
| ![product detail](https://github.com/user-attachments/assets/043f2c34-16d2-49d4-9359-d390a2fdfa19)                      | ![cart](https://github.com/user-attachments/assets/00f77a08-8890-47bd-abb4-9b0f0d1a4676)                      | ![payment](https://github.com/user-attachments/assets/3ba88061-798b-4329-b4c8-dfa09dcb3336)                    |
| ![order placed](https://github.com/user-attachments/assets/e41361bb-3ea5-4323-8f29-877281e71877)                        | ![profiile](https://github.com/user-attachments/assets/98078322-540d-4cb3-bd32-96a02a81bd1f)                  | ![order detail](https://github.com/user-attachments/assets/8088a35b-0f8c-489e-9627-2e8b16f2c866)                |



## Package Structure

* [`data`](app/src/main/java/com/example/shopkaro/data): Responsible for producing data. Contains API, repository, and model classes.
* [`di`](app/src/main/java/com/example/shopkaro/di): Hilt modules.
* [`ui`](app/src/main/java/com/example/shopkaro/ui): UI layer of the app.
    * `nav`: Contains app navigation and destinations.
    * `screens`: Contains UI components.
    * `theme`: Material3 theme.
* [`utils`](app/src/main/java/com/example/shopkaro/utils): Utility classes used across the app.


## Build With

[Kotlin](https://kotlinlang.org/):
As the programming language.

[Jetpack Compose](https://developer.android.com/jetpack/compose) :
To build UI.

[Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation) :
For navigation between screens.

[Retrofit](https://square.github.io/retrofit/):
Used for making network requests to fetch product data from the Fakestore API.

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) :
For injecting dependencies.

[Coil](https://coil-kt.github.io/coil/compose/) :
To load image asynchronously.

## Navigation structure

This app uses a nested navigation structure with multiple NavGraphs to manage different sections of the app efficiently. Each section, such as authentication, product listing, and order management, has its own NavGraph, allowing for clear separation of concerns and modular navigation flows.

If you have any suggestions for improving this navigation structure or would like to contribute, feel free to share your ideas.

![Screenshot 2024-08-31 204803](https://github.com/user-attachments/assets/2a1b3ad4-874d-4576-aad0-89363ee4d3e6)

## Installation

Simple clone this app and open in Android Studio.

### Firebase Integration

Follow these steps to link Firebase to this app for authentication
and storing cart and order data using Realtime Database:

1. Set up Firebase in your project by following the official Firebase setup guide.
2. Download the google-services.json file from your Firebase project.
3. Place the google-services.json file in the app/ directory of your project.

