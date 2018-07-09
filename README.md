# Scylla Project

[![Build Status](https://travis-ci.org/AhmedNTS/Scylla.svg?branch=master)](https://travis-ci.org/AhmedNTS/Scylla)

## Libraries
- Support libraries
- Design and CardView (optional)
- [RxJava2](https://github.com/ReactiveX/RxJava) and [RxAndroid2](https://github.com/ReactiveX/RxAndroid) 
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Timber](https://github.com/JakeWharton/timber)
- [Picasso](https://github.com/square/picasso) (optional)
- [Glide](https://github.com/bumptech/glide) (optional)
- [Stetho](https://github.com/facebook/stetho)
- [LeakCanary](https://github.com/square/leakcanary)

## Android Studio setup and plugins

* Code analysis - [[CheckStyle](https://docs.gradle.org/3.3/userguide/checkstyle_plugin.html), [Findbug](https://docs.gradle.org/3.3/userguide/findbugs_plugin.html), [PMD](https://docs.gradle.org/3.3/userguide/pmd_plugin.html)] using Gradle Plugins and [QAPlug](https://qaplug.com/download/) as GUI
* CheckStyle configs [[Square](https://github.com/square/android-times-square/blob/master/checkstyle.xml), [Google](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml), [Ribot](https://github.com/ribot/android-boilerplate/blob/master/config/quality/checkstyle/checkstyle-config.xml)]
* 

## Coding colour and styles

* https://github.com/AhmedNTS/AndroidStudio-VisualStudio-ColourScheme
* https://github.com/AhmedNTS/java-code-styles

## Code Quality

This project integrates a combination of unit tests and code analysis tools. 

### Tests

To run **unit** tests on your machine:

``` 
./gradlew test
``` 

### Code Analysis tools 

The following code analysis tools are set up on this project:

* [PMD](https://pmd.github.io/): It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth. See [this project's PMD ruleset](config/pmd/pmd-ruleset.xml).

``` 
./gradlew pmd
```

* [Findbugs](http://findbugs.sourceforge.net/): This tool uses static analysis to find bugs in Java code. Unlike PMD, it uses compiled Java bytecode instead of source code.

```
./gradlew findbugs
```

* [Checkstyle](http://checkstyle.sourceforge.net/): See our [checkstyle config file](config/checkstyle/square_checks.xml).

```
./gradlew checkstyle
```
