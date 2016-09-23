package system.monitor.services;

import java.io.File;
import java.util.List;

/**
 * Created by ppetrovt on 20.09.2016.
 */
public class ServiceHDD {

    private static String nameDisk;
    private static String[] listDisk;
    private static long totalSpace;
    private static long freeSpace;
    private static long percentUsableSpace;

    // Assessment of available disk space
    public static void checkSpaceHDD(String pathDisk){
        File selectedDisk = new File(pathDisk);
        nameDisk = selectedDisk.getName();
        totalSpace = selectedDisk.getTotalSpace();
        freeSpace = selectedDisk.getFreeSpace();
        if (selectedDisk.getFreeSpace() != 0) {
            percentUsableSpace = 100 - (selectedDisk.getFreeSpace() * 100 / totalSpace);
        }else percentUsableSpace = 0;

    }

    public static String[] getListHDD(){
        listDisk = new String[File.listRoots().length];
        File[] listDisks = File.listRoots();

        for (int i = 0; i < File.listRoots().length; i++)
            listDisk[i] = File.listRoots()[i].getAbsolutePath();
        return listDisk;
    }

    public static String getNameDisk() {
        return nameDisk;
    }

    public static void setNameDisk(String nameDisk) {
        ServiceHDD.nameDisk = nameDisk;
    }

    public static long getTotalSpace() {
        return totalSpace;
    }

    public static void setTotalSpace(long totalSpace) {
        ServiceHDD.totalSpace = totalSpace;
    }

    public static long getFreeSpace() {
        return freeSpace;
    }

    public static void setFreeSpace(long freeSpace) {
        ServiceHDD.freeSpace = freeSpace;
    }

    public static String[] getListDisk() {
        return listDisk;
    }

    public static void setListDisk(String[] listDisk) {
        ServiceHDD.listDisk = listDisk;
    }

    public static long getPercentUsableSpace() {
        return percentUsableSpace;
    }

    public static void setPercentUsableSpace(long percentUsableSpace) {
        ServiceHDD.percentUsableSpace = percentUsableSpace;
    }
}
