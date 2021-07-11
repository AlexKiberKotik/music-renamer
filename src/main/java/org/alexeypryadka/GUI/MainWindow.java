package org.alexeypryadka.GUI;

import org.alexeypryadka.logics.RenameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

    private JFrame mainWindow = null;
    private JFileChooser chooser = null;
    private JButton choiceBtn = null;
    private JButton okBtn = null;
    private JTextField replaceField = null;
    private JTextField newField = null;


    private String folderPath = null;
    private String toReplace = null;
    private String asReplacement = null;

    private final RenameManager renameManager = new RenameManager();

    public  MainWindow(){
        initializationComponent();
        windowListener();
    }
    private void initializationComponent(){
        this.mainWindow = new JFrame("Rename music");
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = mainWindow.getContentPane();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        contentPane.setLayout(layout);

        this.choiceBtn =  new JButton("Open Folder");
        this.chooser = new JFileChooser();
        this.replaceField = new JTextField("replacement words",15 );
        this.newField = new JTextField("new words",15 );
        this.okBtn = new JButton("Ok");

        this.replaceField.setFont(new Font("Dialog", Font.PLAIN, 14));
        this.newField.setFont(new Font("Dialog", Font.PLAIN, 14));


    }
    private void windowListener(){
        // replaceField
        this.replaceField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                replaceField.setText("");
            }
        });
        //newField
        this.newField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newField.setText("");
            }
        });
        //choiceBtn
        this.choiceBtn.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                chooser.setDialogTitle("choice directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showOpenDialog(MainWindow.this);
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                folderPath = chooser.getSelectedFile().getAbsolutePath();
            }
        });
        //okBtn
        okBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toReplace = replaceField.getText();
                asReplacement = newField.getText();
                renameManager.renameFilesOfDirectory(getFolderPath() + "\\", getToReplace(), getAsReplacement());
                 if (getFolderPath() == null){
                     JOptionPane.showMessageDialog(null, "error! select directory!");
                 }
                 else if(renameManager.isEmpty()) {
                     JOptionPane.showMessageDialog(null, "this directory is empty!");
                 }else if (renameManager.isRename()){
                    JOptionPane.showMessageDialog(null, "renaming completed!");
                 }
            }
        });
    }
    public void showWindow(){
        mainWindow.add(choiceBtn);
        mainWindow.add(replaceField);
        mainWindow.add(newField);
        mainWindow.add(okBtn);
        mainWindow.setPreferredSize(new Dimension(550, 80));
        mainWindow.setResizable(false);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    public String getToReplace() {
        return toReplace;
    }

    public String getAsReplacement() {
        return asReplacement;
    }
    public String getFolderPath() {
        return folderPath;
    }


}
