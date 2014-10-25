package org.corundummc.installer.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Utillities for files.
 */
public class FileUtils {
    public static File downloadFile(URL fileLocation, File outDir, String fileName) {
        try {
            File downloadedFile = new File(outDir, fileName);

            if (!downloadedFile.exists()) {
                downloadedFile.createNewFile();
            }

            if (downloadedFile.exists()) {
                downloadedFile.delete();
                downloadedFile.createNewFile();
            }

            if (outDir.isDirectory()) {
                HttpURLConnection downloadUrlConnection = (HttpURLConnection) fileLocation.openConnection();
                //A Scanner is an easier way to read a stream than the while ((var = stream.read()) != -1) method,
                //in my opinion. It's also neater.
                Scanner streamScanner = new Scanner(downloadUrlConnection.getInputStream());
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadedFile));

                while (streamScanner.hasNextByte()) {
                    outputStream.write(streamScanner.nextByte());
                }
            }

            return downloadedFile;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        throw new RuntimeException("Error downloading a file!");
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
