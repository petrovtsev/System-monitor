package system.monitor.services;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ppetrovt on 20.09.2016.
 */
public class ServiceRAM {
    private static List<String> usList = new LinkedList<String>();
    private static long freeRAM;
    private static long totalRAM;
    private static long occupiedVirtualMemory;
    private static long totalVirtualMemory;
    private static long freeVirtualMemory;
    private static long percentFreeRAM;
    private static long percentFreeVirtualMemory;

    // Information on RAM
    public static void RAM() {
        receivingData();
        freeRAM = Long.parseLong(usList.get(1));
        totalRAM = Long.parseLong(usList.get(6));
        occupiedVirtualMemory = Long.parseLong(usList.get(0));
        totalVirtualMemory = Long.parseLong(usList.get(2));
        freeVirtualMemory = totalVirtualMemory - occupiedVirtualMemory;

        percentFreeRAM = freeRAM * 100 / totalRAM;
        percentFreeVirtualMemory = freeVirtualMemory * 100 / totalVirtualMemory;
    }

    // Receiving data
    private static void receivingData() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (Modifier.isPublic(method.getModifiers())) {
                Object value;
                try {
                    value = method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = e;
                }
                usList.add(value.toString());
            }
        }
    }

    public static List<String> getUsList() {
        return usList;
    }

    public static void setUsList(List<String> usList) {
        ServiceRAM.usList = usList;
    }

    public static long getFreeRAM() {
        return freeRAM;
    }

    public static void setFreeRAM(long freeRAM) {
        ServiceRAM.freeRAM = freeRAM;
    }

    public static long getTotalRAM() {
        return totalRAM;
    }

    public static void setTotalRAM(long totalRAM) {
        ServiceRAM.totalRAM = totalRAM;
    }

    public static long getOccupiedVirtualMemory() {
        return occupiedVirtualMemory;
    }

    public static void setOccupiedVirtualMemory(long occupiedVirtualMemory) {
        ServiceRAM.occupiedVirtualMemory = occupiedVirtualMemory;
    }

    public static long getTotalVirtualMemory() {
        return totalVirtualMemory;
    }

    public static void setTotalVirtualMemory(long totalVirtualMemory) {
        ServiceRAM.totalVirtualMemory = totalVirtualMemory;
    }

    public static long getFreeVirtualMemory() {
        return freeVirtualMemory;
    }

    public static void setFreeVirtualMemory(long freeVirtualMemory) {
        ServiceRAM.freeVirtualMemory = freeVirtualMemory;
    }

    public static double getPercentFreeRAM() {
        return percentFreeRAM;
    }

    public static void setPercentFreeRAM(long percentFreeRAM) {
        ServiceRAM.percentFreeRAM = percentFreeRAM;
    }

    public static double getPercentFreeVirtualMemory() {
        return percentFreeVirtualMemory;
    }

    public static void setPercentFreeVirtualMemory(long percentFreeVirtualMemory) {
        ServiceRAM.percentFreeVirtualMemory = percentFreeVirtualMemory;
    }
}
