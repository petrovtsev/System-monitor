package system.monitor;

import system.monitor.services.ServiceCopy;
import system.monitor.services.ServiceHDD;
import system.monitor.services.ServiceRAM;

import java.io.File;

/**
 * Created by ppetrovt on 20.09.2016.
 */
public class Test {

    public static void main(String[] args) {

        // test Copy
        System.out.println("********************************\n" +
                "Start test copy service.");
        ServiceCopy serviceCopy = new ServiceCopy();
        String path = "c:/12";
        File selectedFile = new File(path);
        File[] selectedFiles = new File[1];
        selectedFiles[0] = selectedFile;
        String pathDisk = "c:/";
        ServiceCopy.Copy(selectedFiles,pathDisk);
        if (ServiceCopy.getCheckCopy()){
            System.out.println("Copy is possible!");
        } else System.out.println("Can not be copied!");
        System.out.println("size - " + ServiceCopy.getSize()/1024 + "kb\nfree space - "
                + ServiceCopy.getFreeSpace()/1024/1024/1024);

        // test HDD
        System.out.println("\n********************************\n" +
                "Start test HDD service.");
        String selectDisk = "c:/";
        ServiceHDD.checkSpaceHDD(selectDisk);
        System.out.println("Free space HDD - " + ServiceHDD.getFreeSpace() + " byte" +
                "\nTotal space HDD - " + ServiceHDD.getTotalSpace() + " byte");

        // test RAM
        System.out.println("\n********************************\n" +
                "Start test HDD service.");
        ServiceRAM.RAM();
        System.out.println("Free RAM - " + ServiceRAM.getFreeRAM()/1024/1024 + "mb" +
                "\nPercent free RAM - " + ServiceRAM.getPercentFreeRAM() + "%" +
                "\nFree virtual RAM - " + ServiceRAM.getFreeVirtualMemory()/1024/1024 + "mb" +
                "\nPercent free virtual RAM - " + ServiceRAM.getPercentFreeVirtualMemory() + "%");
    }


}
