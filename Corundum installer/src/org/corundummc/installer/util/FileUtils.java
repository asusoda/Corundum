package org.corundummc.installer.util;

import org.corundummc.installer.Main;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Utilities for files.
 */
public class FileUtils {
    public static File downloadFile(URL fileLocation, File outDir, String fileName) {
        try {
            //Bit of an experiment for me as the other downloading code appears to not want to work. I don't usually
            //use NIO, but if it's this easy to download a file using it I'll be using it a LOT more in the future.
            File out = new File(outDir, fileName);
            Main.verbose("Constructing byte channel!");
            ReadableByteChannel byteChannel = Channels.newChannel(fileLocation.openStream());
            FileOutputStream outputStream = new FileOutputStream(out);
            Main.verbose("Beginning to transfer bytes!");
            outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            Main.verbose("Finished downloading a file!");
            return out;
        } catch (IOException e) {
            Main.verbose("Failure downloading " + fileLocation + "!");
            e.printStackTrace();
        }

        throw new RuntimeException("Unable to download " + fileLocation);
    }

    public static void copyFile(File from, File to) {
        try {
            Scanner fromScanner = new Scanner(new FileInputStream(from));
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(to)));

            while (fromScanner.hasNextByte()) {
                out.print(fromScanner.nextByte());
            }

            out.close();
            fromScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void extractFilesFromZip(File in, File out) {
        try {
            ZipFile inFile = new ZipFile(in);
            Enumeration<? extends ZipEntry> enumZip = inFile.entries();

            while (enumZip.hasMoreElements()) {
                ZipEntry next = enumZip.nextElement();
                ZipInputStream inStream = new ZipInputStream(inFile.getInputStream(next));
                Main.verbose("Constructing Zip channel!");
                ReadableByteChannel zipInChannel = Channels.newChannel(inStream);
                FileOutputStream outStream = new FileOutputStream(out);
                Main.verbose("Transferring from zip channel to output!");
                outStream.getChannel().transferFrom(zipInChannel, 0, Long.MAX_VALUE);

                inStream.close();
                outStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
