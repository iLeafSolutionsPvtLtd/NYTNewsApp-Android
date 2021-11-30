# New York Time Article

Demo application to show most popular news feeds on New York Times.

* List of articles are shown in home screen.
* Details  are shown on Detail Screen while tapping forward button on each article. 
* Navigation drawer to  navigate screens. 
* Following API is used for loading data in the app,
http://api.nytimes.com/svc/mostpopular/v2/viewed/{section}/{period}.json?api-key='sample-key'.


### Architecture:
Project is developed in kotlin programming  language.Architecture used in project is MVVM with coroutine Flow api .

### Libraries Used

- [Jetpack](https://developer.android.com/jetpack) - A suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
- [Retrofit](https://square.github.io/retrofit/) - Networking library which makes web API integration.
- [Glide](https://bumptech.github.io/glide/) - It is a fast and efficient open source media management and image loading framework for Android .
- [Hilt](https://insert-koin.io/) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)-The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- [Material Design support libraries](https://material.io/develop/android/docs/getting-started) - Modern UI designing library for modern apps.


## Installation

* Cloning this repository and import the project in Android Studio by moving to File->new->import project
* After the gradle sync complete, connect a physical device or use Android emulator. 
* Run the app by clicking on the play button on top bar, or by pressing ctrl+R buttons.

### Running The Tests 

Follow the steps to get test case reports:
* Move to test packages in Android Studio (Java test packages)
* Select the package by right clicking, select more run option then select 'run Tests in package with coverage'
* Test results will be shown after the executions are finished.

# MIT License

Copyright 2021

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
