Corundum
========

Description
-----------------
This project is a Java-based server-side plugin-based modification A.P.I. for Minecraft. Using Mojang's minecraft_server.jar as a resource, it can work like a Minecraft server while allowing user-programmed Java plugins to handle events that occur during the gameplay in order to modify the mechanics of Minecraft. This can be used to do small tasks like preventing the spawning of baby zombies or large, complex tasks such as creating minigames servers like MinePlex, The Shotbow Network, or HyPixel's server.

Installation
--------------
_NOTE: This product is not finished!_

Installing Normally
=========
_NOTE: There is normal download yet!_

 1. If your Java version is less than 7, install your favorite Java 7+ Virtual Machine.

 2. Download the Corundum launcher.jar. _NOTE: There is not currently a Corundum launcher download!_

 3. Run the Corundum launcher.jar with your Java 7+ Virtual Machine.

The Corundum launcher will help you from there!

Building from Source
==========
You can either use the installer under the CorundumInstaller directory here, OR follow these steps.

 1. Download [MCP v9.08](http://www.mediafire.com/download/2czafa60rh4ajhj/mcp908.zip).
 2. Download the source code from this repository.
 3. Go into conf/mcp.conf. Look near the bottom and you should see CmdRecomp. Change all instances of 1.6 with 1.7.
    Optionally, do the same with the line beginning with CmdRecompScala, but that's only for if you want to use
    Scala.
 4. Run MCP's decompile script (decompile.bat for Windows, decompile.sh for *nix-based systems, including Mac).
 5. Extract the Corundum folder from the source code into MCP's "src/minecraft_server" folder.
 6. Run MCP's recompile and reobfuscate scripts.
 7. Package the "Corundum" folder in MCP's "reobf" folder into a new jar called "Corundum.jar".
 8. Delete the "Corundum/launcher" folder from the Corundum.jar.
 9. Add a META_INF folder with a MANIFEST.MF file to the Corundum.jar and designate "Corundum.Corundum" as the main class.
 10. Package the "Corundum/launcher" folder in MCP's "reobf" folder into a new jar called "Corundum launcher.jar". Make sure that inside the jar, the top-level directory is NOT "launcher"; it should be "Corundum" with the "launcher" directory inside that.
 11. Add a META_INF folder with a MANIFEST.MF file to the Corundum launcher.jar and designate "Corundum.launcher.CorundumLauncher" as the main class.
 12. Download the appropriate version of the minecraft_server.jar.
 13. Place the Corundum.jar, Corundum launcher.jar, and minecraft_server.jar in the same directory.
 14. Run the Corundum launcher.jar.

Sorry it's so complicated.
