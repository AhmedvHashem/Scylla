# Scylla Project

## Android Studio setup and plugins

* Code analysis - [[CheckStyle](https://docs.gradle.org/3.3/userguide/checkstyle_plugin.html), [Findbug](https://docs.gradle.org/3.3/userguide/findbugs_plugin.html), [PMD](https://docs.gradle.org/3.3/userguide/pmd_plugin.html)] using QAPlug or Gradle Plugins 
* CheckStyle configs [[Square](https://github.com/square/android-times-square/blob/master/checkstyle.xml), [Google](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml), [Ribot](https://github.com/ribot/android-boilerplate/blob/master/config/quality/checkstyle/checkstyle-config.xml)]

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

* [PMD](https://pmd.github.io/): It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth. See [this project's PMD ruleset](config/quality/pmd/pmd-ruleset.xml).

``` 
./gradlew pmd
```

* [Findbugs](http://findbugs.sourceforge.net/): This tool uses static analysis to find bugs in Java code. Unlike PMD, it uses compiled Java bytecode instead of source code.

```
./gradlew findbugs
```

* [Checkstyle](http://checkstyle.sourceforge.net/): It ensures that the code style follows [our Android code guidelines](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md#2-code-guidelines). See our [checkstyle config file](config/quality/checkstyle/checkstyle-config.xml).

```
./gradlew checkstyle
```
