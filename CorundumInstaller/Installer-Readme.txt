This installer is for building Corundum and using it for developing plugins/just setting up Corundum easily for the Corundum
developers without the 14 steps required to make it work, which involves editing a config file for MCP,
manually downloading a lot of stuff, moving stuff around, etc. It does not, however, have a plugin easy build
script like things such as Forge does.

This installer's original code was largely made by one of Corundum's devs, Niadel. By the time you read this, however, there may have
been drastic changes to fix things or to comply to MCP structure changes.

Building the installer:
    Simply run gradlew build and look in build/libs. If gradlew doesn't work for you, download Gradle, install it,
    and run gradle build. If you don't want to use Gradle, just use the non-gradle buildscripts (non-recommended,
    Gradle SHOULD work).
The MANIFEST.mf is merely for the non-gradle build script.