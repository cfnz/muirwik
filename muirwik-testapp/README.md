# Muirwik Test App

This is a test app for [Muirwik](https://github.com/cfnz/muirwik).

The app expects the components project to be at the same level as this project
and references it in the webpack.configs and in the build.gradle.kts files.
Note that this will occur if you checkout the git project anyway.

If you want to see how to create an app that does not need the components project
to be present, take a look at [the starter app](https://github.com/cfnz/muirwik-starterapp)
which uses a repository housed on bintray/jcenter to pickup its Muirwik dependencies.

## To Get Started
Make sure you have java, git and the yarn package manager installed (and not the yarn that comes with cmdtest in Ubuntu :-)), 
then the following should work (possibly may also need npm installed):

    git clone https://github.com/cfnz/muirwik.git
    cd muirwik
    ./gradlew :muirwik-testapp:yarn
    ./gradlew :muirwik-testapp:build
    ./gradlew :muirwik-testapp:webpackDevServerOpenBrowser

In Windows it is probably (but have not tried it) more like:

    git clone https://github.com/cfnz/muirwik.git
    cd muirwik/muirwik-testapp
    gradlew.bat :muirwik-testapp:yarn
    gradlew.bat :muirwik-testapp:build
    gradlew.bat :muirwik-testapp:webpackDevServerOpenBrowser

## Contributing
Feedback and contributions are welcome :-). 

