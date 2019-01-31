# Muirwik Test App

This is a test app for [Muirwik](https://github.com/cfnz/muirwik).

The app expects the components project to be at the same level as this project
and references it in the webpack.configs and in the build.gradle.kts files.

If you want to see how to create an app that does not need the components project
to be present, take a look at [the starter app](https://github.com/cfnz/muirwik/tree/master/starterapp)
which uses a repository housed on jcenter to pickup its Muirwik dependencies.

## To Get Started
Make sure you have gradle and the yarn package manager installed (and not the yarn that comes with cmdtest in Ubuntu :-)), 
then the following should work:

    git clone https://github.com/cfnz/muirwik.git
    cd muirwik/testapp
    gradle yarn
    gradle build
    gradle webpackDevServerOpenBrowser

## Contributing
Feedback and contributions are welcome :-). 
