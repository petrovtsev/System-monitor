package system.monitor.views;

import system.monitor.services.ServiceHDD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorHDD extends JFrame {

    private Color barColor;

    public MonitorHDD() {

        // window settings
        setPreferredSize(new Dimension(400, 200));
        setTitle("System Monitor - HDD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // declaring elements
        final JLabel freeSpace = new JLabel(" ");
        final JLabel totalSpace = new JLabel(" ");
        JLabel percentUsableSpace = new JLabel("Percent usable space:");
        JLabel message  = new JLabel("Select disk:");
        JButton update = new JButton("update");
        JButton back = new JButton("back");

        // view percent usable space selected HDD
        final JProgressBar barPercetUsableSpace = new JProgressBar();
        barPercetUsableSpace.setStringPainted(true);
        barPercetUsableSpace.setMinimum(0);
        barPercetUsableSpace.setMaximum(100);
        barPercetUsableSpace.setValue((int)ServiceHDD.getPercentUsableSpace());
        barPercetUsableSpace.setForeground(barColor);

        // information output
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                ServiceHDD.checkSpaceHDD((String)box.getSelectedItem());
                freeSpace.setText("Free space HDD:" + ServiceHDD.getFreeSpace()/1024/1024/1024 + "gb");
                totalSpace.setText("Total space HDD:" + ServiceHDD.getTotalSpace()/1024/1024/1024 + "gb");
                barPercetUsableSpace.setValue((int)ServiceHDD.getPercentUsableSpace());
                barPercetUsableSpace.setForeground(setColor(ServiceHDD.getPercentUsableSpace()));
            }
        };

        // disk selection
        ServiceHDD.getListHDD();
        JComboBox listHDD = new JComboBox(ServiceHDD.getListHDD());
        listHDD.setEditable(false);
        listHDD.addActionListener(actionListener);

        // deployment of elements
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(4,1,1,2));
        c.add(message);
        c.add(listHDD);
        c.add(totalSpace);
        c.add(freeSpace);
        c.add(percentUsableSpace);
        c.add(barPercetUsableSpace);
        c.add(update);
        c.add(back);
    }

    // color definition
    public Color setColor(double percent){
        if (percent < 10){barColor = new Color(0,255,0);} else
        if (percent > 10 & percent < 70){barColor = new Color(0,0,255);} else
        if (percent > 70){barColor = new Color(255,0,0);}
        return barColor;
    }

    // start app
    public static void main(String[] args) {
        MonitorHDD app = new MonitorHDD();
        app.setVisible(true);
        app.pack();
    }
}
