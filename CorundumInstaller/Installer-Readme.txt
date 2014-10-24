This installer is for building Corundum and using it for developing plugins/just setting up Corundum easily for the Corundum
developers without the 14 steps required to make it work, which involves editing a config file for MCP,
manually downloading a lot of stuff, moving stuff around, etc. It does not, however, have a plugin easy build
script like things such as Forge does.

This installer's original code was largely made by one of Corundum's devs, Niadel. By the time you read this, however, there may have
been drastic changes to fix things or to comply to MCP structure changes.

Building the installer:
    Simply run gradlew build and look in build/libs. If gradlew doesn't work for you, download Gradle, install it,
    and run gradle build. If dependencies don't work for you... Well... I may include a simple non-gradle build script.
    However, due to potential licensing issues, you'll have to download zip4j 1.3.2 manually. You can find it here:
    http://mvnrepository.com/artifact/net.lingala.zip4j/zip4j/1.3.2. Just download the jar to this directory and
    run the .bat/sh