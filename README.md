Product Viewer - Android MVVM App
Overview
This is an Android application built to demonstrate proficiency in modern Android development practices. The app fetches data for two different product categories (Smartphones and Laptops) from the public DummyJSON API simultaneously. It displays these products in separate, toggleable lists and allows the user to view detailed information for each product.
The project is built entirely with Jetpack Compose for the UI and follows a strict MVVM architecture.
Features Implemented
Home Screen: Fetches and displays two separate lists of products from the API.
Simultaneous API Calls: Utilizes RxKotlin's Single.zip operator to execute both network requests concurrently, ensuring an efficient and fast data loading experience.
Toggleable Lists: A Material 3 TabRow on the home screen allows users to seamlessly switch between the "Smartphones" and "Laptops" lists.
Details Screen: A dedicated screen shows detailed information, including a larger image, full description, title, and price of any selected product.
Loading State: A professional shimmer effect is displayed while the data is being fetched, providing a better user experience than a simple progress bar.
Graceful Error Handling: The app handles potential network errors (e.g., no internet connection) by displaying a clear error message to the user, preventing any application crashes.
MVVM Architecture: The project strictly follows the Model-View-ViewModel architecture pattern, resulting in a clean, scalable, and highly testable codebase.
Dependency Injection: Implements Koin for dependency injection, which decouples components and simplifies the management of dependencies like Retrofit, Repositories, and ViewModels.
Architecture and Tech Stack
Architecture: MVVM (Model-View-ViewModel)
UI Toolkit: Jetpack Compose
Asynchronous Programming: RxKotlin & RxAndroid
Networking: Retrofit & Gson
Dependency Injection: Koin
Image Loading: Coil for Compose
Navigation: Navigation Compose
UI Effects: Shimmer for Compose
Challenges Faced & Experience
This project was an excellent opportunity to apply and solidify key Android development concepts. Several challenges were encountered and overcome during the development process:
Build & Compiler Errors: A significant initial challenge was a BackendException from the Kotlin compiler. After extensive debugging, the root cause was identified as a version mismatch between the Kotlin plugin, the Jetpack Compose Compiler, and the Compose libraries. Aligning these versions in the build.gradle.kts file and performing a deep clean of the project resolved this critical issue.
Koin Initialization Crash: The application would crash on startup with a KoinApplication has not been started error. This taught me the crucial lesson that creating a custom Application class for initializing Koin is not enough; it must also be explicitly registered in the AndroidManifest.xml using the android:name attribute.
Passing Data via Navigation Routes: When passing product details to the details screen, special characters in the title and description were breaking the navigation route. The solution was to properly URL-encode all string parameters before constructing the navigation route and URL-decode them upon arrival at the destination composable.
Overall, this assignment was a valuable, hands-on experience that reinforced the importance of a solid architecture, meticulous dependency management, and robust error handling in building high-quality Android applications.
Assumptions Made
The application assumes the user has an active internet connection to fetch data from the API.
The DummyJSON API endpoints are assumed to be stable, available, and will return data in the expected JSON format.
