# BcePlaceCom (Android App)

BcePlaceCom is a native Android application which intended to help the students to find the opportunity(Jobs/Internships) and enhance carrier growth. Entire application is works on the MVVM architecture, Data binding, View Binding and Firebase Database. Contributors are feel free to write the code in "JAVA" as well as "KOTLIN". 

# Basic Features
  - User Sign Up and Login(We will use Firebase to store user Information)
  - Job Section(all job should be shown in recycler view)
  - News Section or Notification Section
  - Job Section Should be visible for only Registered users.
  - User can filter Job with branch name(CSE,ME,CE,ME) and Job type(Internship, Full-Time).
  - If you have any new feature idea create issue with feature tag.


## Building The Code

1. Clone the repository using HTTP: `https://github.com/bcebakhtiyarpur/BcePlaceCom.git`
2. Open Android Studio.
3. Click on 'Open an existing Android Studio project'
4. Browse to the directory where you cloned the project and click OK.
5. Let Android Studio import the project and you are good to go!

## Unit Testing
* Run unit test to check everything is configured.
1. Open BcePlaceCom/app/src/androidTest/java/com.dev.bcepedia.bceplacecom in android studio
3. Run ActivityLaunchTest


## Firebase Configuration
1. Create Project on https:://console.firebase.google.com
1. Download google-services.json in the Project Settings and place it BcePlaceCom/app
2. Add Sign In Methods to Authentication Tab on Firebase
* To use Google Sign In make sure to configure SHA-1 certification
More Info https://firebase.google.com/docs/auth

## How To Contribute

Read [CONTRIBUTION.md](https://github.com/bcebakhtiyarpur/BcePlaceCom/blob/dev/CONTRIBUTION.md)

## Documentation

- **Language**: Kotlin
- **Architecture**: Model View ViewModel (MVVM)
- **Libraries**: 
[Material Components](https://github.com/material-components/material-components-android),
[ShimmerRecyclerView](https://github.com/sharish/ShimmerRecyclerView),
[Glide](https://github.com/bumptech/glide)

- **Learn/Guide**:
[Development_Guide](https://developer.android.com/guide)
