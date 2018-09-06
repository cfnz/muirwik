# Muirwik

Welcome to Muirwik.

Muirwik gets it name from being a [Material UI](https://material-ui.com/) [React](https://reactjs.org/) wrapper written 
in [Kotlin](https://kotlinlang.org/).

For more information, see the above links (particularly [Material UI](https://material-ui.com/) as its documentation 
will be important in terms of figuring out how this works.

Also see the [Kotlin Wrappers](https://github.com/JetBrains/kotlin-wrappers) page, as this project uses most of the
wrappers there too.

The [first issue](https://github.com/cfnz/muirwik/issues/1) holds a couple of screenshots (not because of any issue, 
just didn't know where else to put them :-)).

## About
I started this off as a process to learn Kotlin. I wanted to do some web development and
in the past used things like Vaadin and before that a small amount of facelets and jsf. 

Watching [David Ford's KotlinConf Videos](https://www.youtube.com/watch?v=FDOECr-sT6U) got me started down the Kotlin 
javascript and React trail... I have learnt lots of things along the way (and still have much more to learn!).

By reading the above, you will note that I am no Kotlin, javascript (and by extension, node or webpack) expert... there
has been lots of leanings along the way, and I no doubt have done things in not the most perfect way.

However, what this is is a working multi-module Kotlin DSL gradle build that wraps quite a large javascript material 
design library. It provides a working demo app (see [screen shots](https://github.com/cfnz/muirwik/issues/1)) and is quite a good starting point for real 
applications... (at least I think so). Quite a few hours were spent just trying to make the basic development workflow
work.

## To Get Started
    git clone https://github.com/cfnz/muirwik.git
    cd muirwik/testapp
    gradle yarn
    gradle build
    gradle webpackDevServerOpenBrowser

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

