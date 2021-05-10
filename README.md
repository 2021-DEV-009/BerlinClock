# Berlin Clock

## Description

The Berlin Clock (Mengenlehreclock or Berlin Uhr) is a clock that tells the time using a series of illuminated coloured blocks

The top lamp blinks to show seconds- it is illuminated on even seconds and off on odd seconds.

The next two rows represent hours. The upper row represents 5 hour blocks and is made up of 4 red amps. The lower row represents 1 hour blocks and is also made up of 4 red lamps.

The final two rows represent the minutes. The upper row represents 5 minute blocks, and is made up of 11 lamps- every third lamp is red, the rest are yellow. The bottom row represents 1 minute blocks, and is made up of 4 yellow lamps.

## How to run

To build this app you'll need the [Android SDK](https://developer.android.com/studio/releases/platform-tools).

This app can be build and installed using [Android Studio](https://developer.android.com/studio) or any other IDE that supports Android development.

Without an IDE you can use the `gradlew build` command.

## Design Choices

* The app is split into 3 layers: the domain layer, the application layer, and the presentation layer.
* The presentation layer of the app is made using a MVVM architecture, using using the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture).
* All classes are linked together using the Dependency Injection framework "Hilt".
* Unit Tests are added for all classes except for the UI classes in the presentation layer. These were written with a TDD mindset.

## Future/Possible improvements

* Add UI tests