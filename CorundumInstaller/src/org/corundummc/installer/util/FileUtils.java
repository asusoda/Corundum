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
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Unable to download " + fileLocation);

        /*try {
            Main.verbose("Downloading file from " + fileLocation);
            File downloadedFile = new File(outDir, fileName);

            if (!downloadedFile.exists()) {
                downloadedFile.createNewFile();
                Main.verbose("Created download file.");
            }
            else if (downloadedFile.exists()) {
                downloadedFile.delete();
                downloadedFile.createNewFile();
                Main.verbose("Cleared download file.");
            }

            if (outDir.isDirectory()) {
                HttpURLConnection downloadUrlConnection = (HttpURLConnection) fileLocation.openConnection();
                Main.verbose("Established HttpURLConnection.");
                //A Scanner is an easier way to read a stream than the while ((var = stream.read()) != -1) method,
                //in my opinion. It's also neater.
                Scanner streamScanner = new Scanner(downloadUrlConnection.getInputStream());
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadedFile));
                int downloadedBytesCount = 0;

                while (streamScanner.hasNextByte()) {
                    byte nextByte = streamScanner.nextByte();
                    outputStream.write(nextByte);
                    downloadedBytesCount++;
                    Main.verbose("Got byte " + nextByte);
                }

                Main.verbose("Finished downloading " + fileLocation + ", downloaded " + downloadedBytesCount + " bytes.");
            }

            return downloadedFile;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        throw new RuntimeException("Error downloading a file!");*/
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
                FileOutputStream outStream = new FileOutputStream(out);

                Scanner inStreamScanner = new Scanner(inStream);

                while (inStreamScanner.hasNextByte()) {
                    outStream.write(inStreamScanner.nextByte());
                }

                inStream.close();
                outStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
