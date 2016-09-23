package system.monitor.services;

import java.io.File;

/**
 * Created by ppetrovt on 20.09.2016.
 */
public class ServiceCopy {

    private static long size = 0;
    private static long freeSpace = 0;
    private static boolean checkCopy = false;

    public static boolean Copy(File[] selectedFiles, String pathSelectedDisk){
        // Checking the possibility of copying files
        File selectedDisk = new File(pathSelectedDisk);
        freeSpace = selectedDisk.getFreeSpace();
        getDirSize(selectedFiles);
        if (freeSpace > size) checkCopy = true;
        return checkCopy;
    }

    // File size determination
    public static long getDirSize(File[] selectedFiles) {
        size = 0;
        for (int i = 0; i < selectedFiles.length; i++) {
                size = selectedFiles[i].length();
        }
        return size;

    }

    public static long getSize() {
        return size;
    }

    public static void setSize(long size) {
        ServiceCopy.size = size;
    }

    public static boolean getCheckCopy() {
        return checkCopy;
    }

    public static void setCheckCopy(boolean checkCopy) {
        ServiceCopy.checkCopy = checkCopy;
    }

    public static long getFreeSpace() {
        return freeSpace;
    }

    public static void setFreeSpace(long freeSpace) {
        ServiceCopy.freeSpace = freeSpace;
    }
}
