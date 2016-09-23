package system.monitor.views;

import system.monitor.services.ServiceCopy;
import system.monitor.services.ServiceHDD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MonitorCopy extends JFrame {

    private static File selectedFile;
    private static File[] selectedFiles;
    private static String pathSelectedFile;
    private static String selectedDisk;
    private static String maskName;
    private static FileFilter fileFilter;

    public MonitorCopy() {

        setPreferredSize(new Dimension(400, 250));
        setTitle("System Monitor - check copy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        final JLabel dir  = new JLabel(" ");
        JLabel messageName = new JLabel("Enter the name or file mask:");
        final JLabel name = new JLabel(" ");
        JLabel messageDisk = new JLabel("Select disk:");

        JTextField textField = new JFormattedTextField();
        textField.setText(maskName);

        final JLabel pathSelectedDIR  = new JLabel(" ");
        final JLabel checkMessage = new JLabel(" ");
        final JLabel totalAmountFiles = new JLabel(" ");
        final JLabel freeSopeSelectedDisk = new JLabel(" ");

        JButton back = new JButton("Back");

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                selectedDisk = (String)box.getSelectedItem();
            }
        };

        JButton showDialogButton = new JButton("Selected catalog");
        showDialogButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                maskName = ".jar";
                JFileChooser dialog = new JFileChooser();
                dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                dialog.setAcceptAllFileFilterUsed(false);
                dialog.showOpenDialog(dir);
                dialog.setFileFilter(new FileFilterNM(maskName,""));

                selectedFiles = dialog.getSelectedFiles();
                pathSelectedFile = selectedFile.getPath().toString();
                pathSelectedDIR.setText(selectedFile.getPath());

                setVisible(true);
            }
        });

        JButton check = new JButton("Check");
        check.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                ServiceCopy.Copy(selectedFiles, selectedDisk);

                totalAmountFiles.setText("The total amount of files: " + ServiceCopy.getDirSize(selectedFiles)/1024 + "kb");
                if (ServiceCopy.getCheckCopy()){
                    checkMessage.setText("Copying is possible");
                }else checkMessage.setText("Copying is not possible");
                freeSopeSelectedDisk.setText("Free space HDD: " + ServiceCopy.getFreeSpace()/1024/1024 + "gb");
                setVisible(true);
            }
        });


        ServiceHDD.getListHDD();
        JComboBox listHDD = new JComboBox(ServiceHDD.getListHDD());
        listHDD.setEditable(false);
        listHDD.addActionListener(actionListener);

        Container c = this.getContentPane();
        c.setLayout(new GridLayout(5,1,1,2));
        c.add(showDialogButton);
        c.add(pathSelectedDIR);
        c.add(messageName);
        c.add(textField);
        c.add(messageDisk);
        c.add(listHDD);
        c.add(check);
        c.add(checkMessage);
        c.add(totalAmountFiles);
        c.add(freeSopeSelectedDisk);
    }

    public static void main(String[] args) {
        MonitorCopy app = new MonitorCopy();
        app.setVisible(true);
        app.pack();
    }

}

class FileFilterNM extends javax.swing.filechooser.FileFilter{
    String ext,description;

    FileFilterNM(String ext, String description) {
        this.ext = ext;
    }

    public boolean accept(File f) {
        if(f != null) {
            if(f.isDirectory()) {
                return true;
            }
            return f.toString().endsWith(ext);
        }
        return false;
    }

    public String getDescription() {
        return description;
    }
}
