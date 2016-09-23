package system.monitor.views;
import system.monitor.services.ServiceRAM;

import javax.swing.*;
import java.awt.*;

public class MonitorRAM extends JFrame {
    private JLabel FreeRAM;
    private JLabel FreeVirtualRAM;
    private JLabel PercentFreeRAM;
    private JLabel PercentFreeVirtualRAM;
    private JButton update;
    private JButton back;

    private Color barColor;


    public MonitorRAM() {
        setPreferredSize(new Dimension(400, 200));
        setResizable(false);
        setTitle("System Monitor - RAM");
        //Container c = getContentPane();
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(4,1,1,2));

        ServiceRAM.RAM();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
     /* Подготавливаем компоненты объекта  */
        FreeRAM = new JLabel("Free RAM:" + ServiceRAM.getFreeRAM()/1024/1024 + "mb");
        FreeVirtualRAM = new JLabel("Free virtual RAM:" + ServiceRAM.getFreeVirtualMemory()/1024/1024 + "mb");
        PercentFreeRAM = new JLabel("Percent free RAM:" + ServiceRAM.getPercentFreeRAM() + "%");
        PercentFreeVirtualRAM = new JLabel("Percent free virtual RAM:" + ServiceRAM.getPercentFreeVirtualMemory() + "%");
        update = new JButton("Update");
        back = new JButton("Back");

        JProgressBar barPercetFreeRAM = new JProgressBar();
        barPercetFreeRAM.setStringPainted(true);
        barPercetFreeRAM.setMinimum(0);
        barPercetFreeRAM.setMaximum(100);
        barPercetFreeRAM.setValue((int)ServiceRAM.getPercentFreeRAM());
        barPercetFreeRAM.setForeground(setColor(ServiceRAM.getPercentFreeRAM()));

        JProgressBar barPercetFreeVirtualRAM = new JProgressBar();
        barPercetFreeVirtualRAM.setStringPainted(true);
        barPercetFreeVirtualRAM.setMinimum(0);
        barPercetFreeVirtualRAM.setMaximum(100);
        barPercetFreeVirtualRAM.setValue((int)ServiceRAM.getPercentFreeVirtualMemory());
        barPercetFreeVirtualRAM.setForeground(setColor(ServiceRAM.getPercentFreeVirtualMemory()));

        c.add(FreeRAM);
        c.add(FreeVirtualRAM);
        c.add(PercentFreeRAM);
        c.add(PercentFreeVirtualRAM);
        c.add(barPercetFreeRAM);
        c.add(barPercetFreeVirtualRAM);
        c.add(update);
        c.add(back);
    }

    private void updateLabel() {
        FreeRAM.setText("Free RAM:" + ServiceRAM.getFreeRAM()/1024/1024 + "mb");
        FreeVirtualRAM.setText("Free virtual RAM:" + ServiceRAM.getFreeVirtualMemory()/1024/1024 + "mb");
        PercentFreeRAM.setText("Percent free RAM:" + ServiceRAM.getPercentFreeRAM() + "%");
        PercentFreeVirtualRAM.setText("Percent free virtual RAM:" + ServiceRAM.getPercentFreeVirtualMemory() + "%");
    }
    public static void main(String[] args) {
        MonitorRAM app = new MonitorRAM();
        app.setVisible(true);
        app.pack(); /* Эта команда подбирает оптимальный размер в зависимости от содержимого окна  */
    }

    /**
     * Если % от 0 до 10, то выводить зеленым
     если % более 10, но не более 70, то синим
     если % более 70, то красным
     */
    public Color setColor(double percent){
        if (percent < 10){barColor = new Color(0,255,0);} else
        if (percent > 10 & percent < 70){barColor = new Color(0,0,255);} else
        if (percent > 70){barColor = new Color(255,0,0);}
        return barColor;
    }
}
