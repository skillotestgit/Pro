package com.company;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Arrays;

public class FileHelpers {

    public static void takeScreenshot(WebDriver driver, ITestResult result) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String testName = result.getMethod().getMethodName();
        long timestamp = Instant.now().getEpochSecond();
        FileUtils.copyFile(scrFile, new File(Configuration.readScreenshotDirectory() + testName + "_" + timestamp + ".png"));
    }

    public static void cleanUpDirectory() throws IOException {
        Path path = Path.of(Configuration.readScreenshotDirectory());
        FileUtils.cleanDirectory(path.toFile());
    }

    public static boolean filesEqual(File file1, File file2){
        boolean equals = false;

        try {
            equals =  FileUtils.contentEquals(file1, file2);
            return equals;
        } catch (IOException e) {
            e.printStackTrace();
            return equals;
        }
    }

    public static String getNewestFileNameFromDirectory(String sdir){
        File dir = new File(sdir);
        if (dir.isDirectory()) {
            File[] dirFiles = dir.listFiles((FileFilter) FileFilterUtils.fileFileFilter());
            if (dirFiles != null && dirFiles.length > 0) {
                Arrays.sort(dirFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                return dirFiles[0].getName();
            }
        }

        return null;
    }

}

