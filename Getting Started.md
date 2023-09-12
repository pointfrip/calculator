Hello,

I created an app for a Pointfree Interpreter in Kotlin. \
It should be able to be operated like a calculator with functional programming options via the keyboard.

The screenshot looks like this:

![calculator-image](https://raw.githubusercontent.com/pointfrip/calculator/main/pixel2bimage.png)

Each input line is executed with CALC and the result is then displayed below the button line. \
The other buttons are behind **CALC** - **Composition** - **Round brackets** - **Square brackets** - **Step left** - **Step right** \
In the menu there are also the items **Clear** to reset to the initialization state, \
**Insert result above** - **Load External** - **Copy Input**

The Pointfree language has an unusual syntax and is typically processed **right-to-left**. \
It's practically all **infix notation** with functions or brackets in between. \
A [Quickinfo.pdf](https://github.com/pointfrip/calculator/blob/main/quickinfo-en.pdf) roughly shows the data types and functions/operators of the language.

As for technologies, I used Kotlin IDE Community as the implementation language and Android Studio (Kotlin) for the app/APK.

### I will give a few examples of pointfree interpreter technology

With **name == function term** a function term is given a name, e.g.:

    sum == (+ \)
    -->  "sum == (+ \) _s"

and with the CALC button the calculator accepts the definition and displays the compilation as a string. \
New Line:

    iota ° 10
    -->  (1 ; 2 ; 3 ; 4 ; 5 ; 6 ; 7 ; 8 ; 9 ; 10 ;)

iota creates a list of real numbers with argument 10. \
The composition is the operator.

    ((id * id) aa) ° iota ° 10
    -->  (1 ; 4 ; 9 ; 16 ; 25 ; 36 ; 49 ; 64 ; 81 ; 100 ;)

the aa-operator calculates the square of each element in the list. \
id is the identity function.

    sum ° ((id * id) aa) ° iota ° 10
    -->  385

With the sum of squares we have now programmed a nice pipeline.

Download options are available via [heise download](https://www.heise.de/download/product/fp-trivia) in the Android department. (Virus checked by heise) \
Or via [Github](https://github.com/pointfrip/calculator/blob/main/apk/debug/app-debug.apk) (APK), the source code for the app is also on Github: [pointfrip/calculator/src](https://github.com/pointfrip/calculator/tree/main/src)

Best wishes and have fun with the app, \
metazip

