# Muirwik

Welcome to Muirwik - but note that Muirwik is going to be retired (see below) 

Muirwik gets it name from being a [Material UI](https://material-ui.com/) [React](https://reactjs.org/) wrapper written 
in [Kotlin](https://kotlinlang.org/).

For more information, see the above links (particularly [Material UI](https://material-ui.com/) as its documentation 
will be important in terms of figuring out how this works.

Also see the [Kotlin Wrappers](https://github.com/JetBrains/kotlin-wrappers) page, as this project uses most of the
wrappers there.

A couple of [screenshots](https://github.com/cfnz/muirwik/wiki) have been added to the wiki page.

There are two versions of Muirwik, one for Material UI 4 and one for Material UI (or MUI) 5.
The branch for Material UI 4 is named MaterialUI-4 and will be Muirwik version 0.9.x and lower.
Version 5 was in a separate branch, but has now been merged into master and will be Muirwik 
version 0.10.x and beyond.

See notes below on MUI version 5, but also see the next note about Muirwik retirement.

## NOTE: Muirwik Probable Retirement
It has been a good couple of years, I have learnt lots related to Kotlin, JS, Material UI, now MUI, and the Kotlin react 
wrappers. The Kotlin wrappers and the Material UI project have been updating fast, and I have not kept fully up.

The [latest change](https://github.com/JetBrains/kotlin-wrappers/blob/d0ef539948e49f297720fd5f8d1bb848ceb62c5d/CHANGELOG.md)
spells the future. Any code written using attrs (likely all of Muirwik apps) will need to be modified or use the legacy
version of the wrappers. This seems like a good place to pause and choose the best way forward.

There now is a better supported and faster developed version of a MUI wrapper which is now part
of the kotlin wrapper project itself. 

Anyone new (or old) to Muirwik should check out [kotlin-mui](https://github.com/JetBrains/kotlin-wrappers/tree/master/kotlin-mui).

Kotlin-mui started of quite type unsafe, for example, lots of props were strings rather than enums,
and it didn't immediately appeal. However, just over a week or so ago (at time of writing), props now appear to be moving
to typed versions, not all yet, but things seem to be developing quite fast. Muirwik tried to be quite type safe,
introducing new types if required and using function parameters to enforce required props. The kotlin-mui wrappers 
are more strictly aligned to the MUI project js (since they are an automated build from the js source, that makes sense).
However, unlike the js IDE support, you don't get prompted for required props, for example, in Kotlin code. So it still has 
its pros and cons.

However, because of the speed of development and support, I am probably going to head that way myself, and
put in any major new effort over there rather than what would seem a rather futile effort of updating Muirwik to the
now non legacy version of the Kotlin wrappers. This is particularly because if we want to use the non legacy version, 
it would mean lots of code changes in existing apps... so may as well change it to the new kotlin-mui
wrappers at the same time.

Muirwik version 0.10.1 has been migrated to the latest legacy kotlin wrapper at time of writing but I don't see it migrating
to the new non-legacy version unless someone wants to take that on (which given the kotlin-mui wrapper project mentioned, 
would seem unlikely).


## Material UI (or MUI) Version 5 Notes
Version 5 of MUI has some breaking changes.

Version 0.10.x of MUIRWIK has lots of breaking changes.

A brief summary is as follows:
* The move to Material UI version 5 (or MUI 5 as it is called now) created some breaking changes.
* Since there was already breaking changes, I took the opportunity to tidy things up resulting in more breaking changes.
* The "m" prefix convention on components has gone. For example mCard is now card
* The "M" prefix on Props and Enums has also gone, so MButtonColor is now ButtonColor
* Some components that were in sub-packages have been moved out to the main components package for consistency
* The parameter Convention has changed. The old MUIRWIK had very large function param lists with lots of defaults.
This made it a bit hard in the IDE to see what was going on. It also made more work when creating the wrappers.
Version 0.5 has gone to a much more limited set of params, with only required props and those props which are almost
always used. Other props are accessible by attrs.*
* Props are more type safe. By using property delegates as well getter and setters it is possible to type
the Props and have them converted to the proper types when set. The old version relied on the function
parameters to do the conversion in the wrapping function. Now all Props should have the correct type.

This will cause some pain if upgrading from the old version. Some depreciated functions remain to help ease the conversion.

Some thought was put into should the component names be capitalized like they are in JSX and Compose, however 
the decision was made to stick to lowercase letters for function names. This is more a Kotlin coding convention. In Compose,
the composables are annotated and the IDE is then happy with the naming convention. In normal functions, the IDE
(with default settings) complains about functions starting with an uppercase character. Another reason is all the 
reference applications seen seems to use lowercase names for function components. All the RBuilder functions seem
to start with lowercase characters. So we have gone for lowercase function names by convention.

Muirwik tries to be a more typesafe Kotlin wrapper to MUI rather than a straight copy of the JSX components of MUI.

## IR-Compiler
At time of writing using the IR-Compiler with the [Kotlin Wrappers](https://github.com/JetBrains/kotlin-wrappers), 
particularly the  React Wrappers, has some [issues](https://kotlinlang.slack.com/archives/C0B8L3U69/p1585318146030900) 
(or at least requires some extra code to make things work properly).

These changes were added to the IR-Compiler branch, and have now been merged into master. 
In other words, the master branch now works with the IR Compiler and with the Legacy compiler, so the IR branch is now no longer needed.

At time of writing, the IR compiler is still alpha and does not yet support incremental compilation. 
For this reason, during development, it is faster (in terms of edit/rebuild/view result cycle) to
use the legacy compiler. 

The version release to Maven Central will be built with js(BOTH). Projects using Muirwik should therefore be
able to use js(LEGACY) or js(IR).


## To Get Started

### The starter App
A good place to get started is to look at the separate [starter app project](https://github.com/cfnz/muirwik-starterapp) which is a minimal
example to get you going. You don't even need to clone/use this repository at all, the starter app is all you need to use the components.

### Adding Muirwik as a dependency
Add the Muirwik dependency to your own app as follows:
(Note: in Kotlin 1.4.0 and earlier, the kotlin-styled version was 1.0.0 instead of that shown below)
```
repositories {
    ...
    mavenCentral()
}

dependencies {
    ...
    implementation("org.jetbrains", "kotlin-styled", "5.3.3-$kotlinJsVersion")
    implementation("com.ccfraser.muirwik:muirwik-components:0.10.1")
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
    cd muirwik
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
design library. It provides a working demo app (see [screenshots](https://github.com/cfnz/muirwik/issues/1)) and starter
app which is quite a good starting point for real applications... (at least I think so). Quite a few hours were spent 
just trying to make the basic development workflow work.

## Todo
Not much unless you want to move to the new non-legacy version of the kotlin react wrappers... (as mentioned above)

### Tests
There are none, zip, zero, naught. The Material UI framework has them, but other than the test app, which is for user
based testing and experimentation, there is nothing else. I am not familiar with any javascript testing framework, so 
the only way I have tested thus far is with the demo app.

### State Management
State management via Redux (or something) is something I have been meaning to look into. In the test app, most of the
state is in local vars rather than in State objects. I tried both, but saw no real benefit in the state objects rather 
than state vars. It didn't seem to help with hot module reloading either. It didn't seem to help with anything much. 
Perhaps, with Redux it might all be quite different... it would be nice if Hot Module Reloading worked with state (as 
seen in various React videos) and maybe it would with Redux, but I have not gone down that track yet.

Talking of HMR, I have it enabled in the development workflow... even without reloading of current state, it does
reload the app better than without it. 

## Contributing
Feedback and contributions are welcome :-). 

