# Country Flags App 🌍
------------------
Country Flags App is an Android application that allows users to explore different countries, view their flags, and get more information about each country, including its capital, region, language, and currency. The app fetches country data from a remote **API** and stores it locally using the **Room Database** for offline access.

## Technologies Used 🚀🔧

- **Kotlin**: The primary language for Android development.
- **Jetpack Libraries**:
  - **[Room](https://developer.android.com/topic/libraries/architecture/room)**: Local database for storing country data offline.
  - **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)**: To observe changes in the data and update the UI accordingly.
  - **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**: Manages UI-related data lifecycle-consciously, ensuring that data survives configuration changes like screen rotations.
  - **[Navigation Component](https://developer.android.com/guide/navigation)**: For managing in-app navigation between fragments and simplifying the back-stack management.
  - **[Fragment](https://developer.android.com/guide/fragments)**: The app is designed with **fragments** to display different UI sections. The country list and details are presented in separate fragments.
  - **[ViewBinding](https://developer.android.com/topic/libraries/view-binding)**: Simplifies binding views by eliminating the need for `findViewById`. It’s used for safely accessing UI elements.
  - **[Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)**: Lifecycle components are used to manage the app’s lifecycle, ensuring UI updates only happen when fragments or activities are in an appropriate state (e.g., in the foreground).
- **[Retrofit2](https://square.github.io/retrofit/)**: A type-safe HTTP client for making network requests to fetch country data.
  
- **[Coroutines](https://developer.android.com/kotlin/coroutines)**: For asynchronous programming and background tasks.
- **[Glide](https://github.com/bumptech/glide)**: Used for loading country flag images from URLs into ImageViews.
- **[Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**: For dependency injection to manage the app's dependencies.
- **[Material Components](https://material.io/develop/android/docs/getting-started)**: For consistent UI components across the app.
- **[ConstraintLayout](https://developer.android.com/reference/android/widget/ConstraintLayout)**: For flexible and responsive UI design.
- **[RxJava](https://github.com/ReactiveX/RxJava)**: For handling asynchronous operations and managing complex streams (optional for future updates).

## Preview 🎞
----------
![CountryFlagsApp2025-01-1222-46-09-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/5ed02710-a810-4cce-953a-84517349eac1)

## Screenshots 📸
------------

| **Image 1** | **Image 2** |
|-------------|-------------|
|![CountryFlagsApp 12 01 2025 22_47_50](https://github.com/user-attachments/assets/d1747195-644d-44d3-9c37-3c7df6137b94)) |![CountryFlagsApp 12 01 2025 22_48_47](https://github.com/user-attachments/assets/4dc83152-85d3-42f4-b347-8e317f833369)) |
| **Image 3** |
|![CountryFlagsApp 12 01 2025 22_48_47](https://github.com/user-attachments/assets/4dc83152-85d3-42f4-b347-8e317f833369)) |

