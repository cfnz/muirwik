# Muirwik

Welcome to Muirwik.

Muirwik gets it name from being a [Material UI](https://material-ui.com/) [React](https://reactjs.org/) wrapper written 
in [Kotlin](https://kotlinlang.org/).

Note: Lots of breaking changes in [latest release](https://github.com/cfnz/muirwik/wiki/Release-Notes).

For more information, see the above links (particularly [Material UI](https://material-ui.com/) as its documentation 
will be important in terms of figuring out how this works.

Also see the [Kotlin Wrappers](https://github.com/JetBrains/kotlin-wrappers) page, as this project uses most of the
wrappers there too.

A couple of [screenshots](https://github.com/cfnz/muirwik/wiki) have been added
to the wiki page.

## IR-Compiler
At time of writing using the IR-Compiler with the [Kotlin Wrappers](https://github.com/JetBrains/kotlin-wrappers), 
particularly the  React Wrappers, has some [issues](https://kotlinlang.slack.com/archives/C0B8L3U69/p1585318146030900) 
(or at least requires some extra code to make things work properly).

It seems that the Kotlin JS team may be changing how things work related to these issues, so I 
have created a different branch to target the IR Compiler for now.

The two main branches are:
* master - targeting the current "legacy" compiler
* IR-Compiler - targeting the new (currently alpha) compiler (note, still todo)

Any updates to master will be merged to the IR-Compiler, and eventually, once the IR Compiler is
out of alpha (or maybe beta) the IR-Compiler branch will be merged back to master and deleted.


## To Get Started

### The starter App
A good place to get started is to look at the separate [starter app project](https://github.com/cfnz/muirwik-starterapp) which is a minimal
example to get you going. You don't even need to clone/use this repository at all, the starter app is all you need to use the components.

### Adding Muirwik as a dependency
Add the Muirwik dependency to your own app as follows:
(Note: in Kotlin 1.4.0 and earlier, the kotlin-styled version was 1.0.0 instead of 5.2.0)
```
repositories {
    ...
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    ...
    implementation("org.jetbrains", "kotlin-styled", "5.2.0-$kotlinJsVersion")
    implementation("com.ccfraser.muirwik:muirwik-components:0.6.2")
}
```

### Compile Muirwik
If you want to get the source of Muirwik and compile locally, you can do the following.

Note that the muirwik-testapp is a good source of information on how to use the components.

Make sure you have java, git and the yarn package manager installed (and not the yarn that comes with cmdtest in Ubuntu :-)), 
then the following should work (possibly may also need npm installed):

    git clone https://github.com/cfnz/muirwik.git
    cd muirwik
    ./gradlew :muirwik-testapp:build
    ./gradlew :muirwik-testapp:run

In Windows, it is probably (but have not tried it) more like:

    git clone https://github.com/cfnz/muirwik.git
    cd muirwik/muirwik-testapp
    gradlew.bat :muirwik-testapp:build
    gradlew.bat :muirwik-testapp:run

Note that I have not taken the time to make the demo app perfect. Some components could be
laid out better. To see what is possible, see the [Material UI](https://material-ui.com/) demo.


## Background
I started this off as a process to learn Kotlin. I wanted to do some web development and
in the past used things like Vaadin and before that a small amount of facelets and jsf. 

Watching [David Ford's KotlinConf Videos](https://www.youtube.com/watch?v=FDOECr-sT6U) got me started down the Kotlin 
javascript and React trail... I have learnt lots of things along the way (and still have much more to learn!).

By reading the above, you will note that I am no Kotlin, javascript (and by extension, node or webpack) expert... there
has been lots of leanings along the way, and I no doubt have done things in not the most perfect way.

Switching from create-react-kotlin-app to using Gradle, yarn/npm and webpack directly also added to the learning
curve but helped in the long run. This was inspired after watching [Gaetan Zoritchak's talk](https://www.youtube.com/watch?v=1Pu0TYJJ2Tw). 

However, what this is, is a working multi-module Kotlin DSL gradle build that wraps quite a large javascript material 
design library. It provides a working demo app (see [screenshots](https://github.com/cfnz/muirwik/issues/1)) and starer
app which is quite a good starting point for real applications... (at least I think so). Quite a few hours were spent 
just trying to make the basic development workflow work.

## Todo
Well, lots really, but as mentioned, it is in a working state as it is...

### Tests
There are none, zip, zero, naught. The Material UI framework has them, but other than the test app, which is for user
based testing and experimentation, there is nothing else. I am not familiar with any javascript testing framework, so 
the only way I have tested thus far is with the demo app.

### State Management
State management via Redux (or something) is something I have been meaning to look into. In the test app, most of 
state is in local vars rather than in State objects. I tried both, but saw no real benefit in the state objects rather 
than state vars. It didn't seem to help with hot module reloading either. It didn't seem to help with anything much. 
Perhaps, with Redux it might all be quite different... it would be nice if Hot Module Reloading worked with state (as 
seen in various React videos) and maybe it would with Redux, but I have not gone down that track yet.

Talking of HMR, I have it enabled in the development workflow... even without reloading of current state, it does
reload the app better than without it. 

## Contributing
Feedback and contributions are welcome :-). 

