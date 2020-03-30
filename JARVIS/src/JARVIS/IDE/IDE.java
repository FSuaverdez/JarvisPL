package JARVIS.IDE;

import JARVIS.IDE.TextHighlight;
import JARVIS.Translator.Translate;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asus
 */
public class IDE extends javax.swing.JFrame
{

    /**
     * Creates new form Compiler
     */
    private int tabCtr;
    private ArrayList<Files> files;
    private ArrayList<JTextPane> textPanes;
    private ArrayList<TextHighlight> textHiglighter;

    public IDE() throws HeadlessException
    {
        textHiglighter = new ArrayList<>();

        tabCtr = 0;
        textPanes = new ArrayList<>();
        files = new ArrayList<>();
        FlatLaf.install(new FlatIntelliJLaf());
        UIManager.put( "ScrollBar.showButtons", true );
        FlatLaf.updateUI();
        initComponents();
        
        
        this.setLocationRelativeTo(null);
        DarkThemeCheck.setState(false);
        LightThemeCheck.setState(true);
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                checkAllFiles();
            }
        });
    }

    private void checkAllFiles()
    {
        int i = tabCtr;
        int cancel = 0;

        for (i = tabCtr; i > 0; i--)
        {
            int curr = NotepadPane.getSelectedIndex();
            System.out.println(textPanes.get(curr).getText().equals(files.get(curr).getFileContent()));
            if (!textPanes.get(curr).getText().equals(files.get(curr).getFileContent()))
            {
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to save the " + files.get(curr).getFilename() + "?", "WARNING", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                System.out.println(answer);
                switch (answer)
                {
                    case JOptionPane.YES_OPTION:
                        saveFile();
                        files.remove(NotepadPane.getSelectedIndex());
                        textPanes.remove(NotepadPane.getSelectedIndex());
                        textHiglighter.remove(NotepadPane.getSelectedIndex());
                        NotepadPane.remove(NotepadPane.getSelectedIndex());

                        tabCtr--;
                        break;

                    case JOptionPane.NO_OPTION:
                        files.remove(NotepadPane.getSelectedIndex());
                        textPanes.remove(NotepadPane.getSelectedIndex());
                        textHiglighter.remove(NotepadPane.getSelectedIndex());
                        NotepadPane.remove(NotepadPane.getSelectedIndex());

                        tabCtr--;
                        break;

                    case JOptionPane.CANCEL_OPTION:
                        System.out.println("Don't Quit");
                        cancel++;
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        cancel++;
                        break;

                }

            }
            if (cancel > 0)
            {
                break;
            }

        }

        if (cancel == 0 || tabCtr == 0)
        {
            System.out.println(cancel);
            dispose();
        }

    }

    public void saveFile()
    {
        if (files.get(NotepadPane.getSelectedIndex()).isSaved() == true)
        {
            try
            {
                FileWriter fileWriter = new FileWriter(files.get(NotepadPane.getSelectedIndex()).getFileDirectory());
                fileWriter.write(textPanes.get(NotepadPane.getSelectedIndex()).getText());
                fileWriter.close();
            } catch (Exception e)
            {
            }
        }
        else
        {
            FileDialog fileDialog = new FileDialog(IDE.this, "SAVE File", FileDialog.SAVE);
            fileDialog.setFile(files.get(NotepadPane.getSelectedIndex()).getFilename());
            fileDialog.setVisible(true);
            if (fileDialog.getFile() != null)
            {
                try
                {
                    files.get(NotepadPane.getSelectedIndex()).setFileDirectory(fileDialog.getDirectory() + fileDialog.getFile());
                    files.get(NotepadPane.getSelectedIndex()).setFilename(fileDialog.getFile());
                    files.get(NotepadPane.getSelectedIndex()).setFileContent(textPanes.get(NotepadPane.getSelectedIndex()).getText());

                    FileWriter fileWriter = new FileWriter(files.get(NotepadPane.getSelectedIndex()).getFileDirectory());
                    fileWriter.write(textPanes.get(NotepadPane.getSelectedIndex()).getText());
                    files.get(NotepadPane.getSelectedIndex()).Saved();
                    fileWriter.close();
                } catch (Exception e)
                {
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        NotepadPane = new javax.swing.JTabbedPane();
        RunButton = new javax.swing.JButton();
        BuildButton = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        NewFile = new javax.swing.JMenuItem();
        OpenFile = new javax.swing.JMenuItem();
        SaveFile = new javax.swing.JMenuItem();
        CloseFile = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        OptionMenu = new javax.swing.JMenu();
        ThemeChooser = new javax.swing.JMenu();
        LightThemeCheck = new javax.swing.JCheckBoxMenuItem();
        DarkThemeCheck = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("JARVIS IDE");
        setLocationByPlatform(true);

        NotepadPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        RunButton.setText("Run");
        RunButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                RunButtonActionPerformed(evt);
            }
        });

        BuildButton.setText("Build");
        BuildButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BuildButtonActionPerformed(evt);
            }
        });

        FileMenu.setText("File");

        NewFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        NewFile.setText("New");
        NewFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NewFileActionPerformed(evt);
            }
        });
        FileMenu.add(NewFile);

        OpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OpenFile.setText("Open");
        OpenFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                OpenFileActionPerformed(evt);
            }
        });
        FileMenu.add(OpenFile);

        SaveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveFile.setText("Save");
        SaveFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SaveFileActionPerformed(evt);
            }
        });
        FileMenu.add(SaveFile);

        CloseFile.setText("Close");
        CloseFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CloseFileActionPerformed(evt);
            }
        });
        FileMenu.add(CloseFile);

        jMenuBar2.add(FileMenu);

        EditMenu.setText("Edit");
        jMenuBar2.add(EditMenu);

        OptionMenu.setText("Option");

        ThemeChooser.setText("Theme");

        LightThemeCheck.setSelected(true);
        LightThemeCheck.setText("Light Theme");
        LightThemeCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                LightThemeCheckActionPerformed(evt);
            }
        });
        ThemeChooser.add(LightThemeCheck);

        DarkThemeCheck.setSelected(true);
        DarkThemeCheck.setText("Dark Theme");
        DarkThemeCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DarkThemeCheckActionPerformed(evt);
            }
        });
        ThemeChooser.add(DarkThemeCheck);

        OptionMenu.add(ThemeChooser);

        jMenuBar2.add(OptionMenu);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BuildButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1031, Short.MAX_VALUE))
                    .addComponent(NotepadPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RunButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BuildButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NotepadPane, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LightThemeCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_LightThemeCheckActionPerformed
    {//GEN-HEADEREND:event_LightThemeCheckActionPerformed
        // TODO add your handling code here:
        if (LightThemeCheck.getState())
        {
            for (int i = 0; i < tabCtr; i++)
            {
                textHiglighter.get(i).setIsDark(false);
                String temp = textPanes.get(i).getText();
                textPanes.get(i).setDocument(textHiglighter.get(i).getDoc());
                textPanes.get(i).setText(temp);
            }

            DarkThemeCheck.setState(false);
            FlatLaf.install(new FlatIntelliJLaf());
            FlatLaf.updateUI();
        }

        LightThemeCheck.setState(true);
    }//GEN-LAST:event_LightThemeCheckActionPerformed

    private void DarkThemeCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DarkThemeCheckActionPerformed
    {//GEN-HEADEREND:event_DarkThemeCheckActionPerformed
        // TODO add your handling code here:
        if (DarkThemeCheck.getState())
        {

            for (int i = 0; i < tabCtr; i++)
            {
                textHiglighter.get(i).setIsDark(true);
                String temp = textPanes.get(i).getText();
                textPanes.get(i).setDocument(textHiglighter.get(i).getDoc());
                textPanes.get(i).setText(temp);
            }

            LightThemeCheck.setState(false);

            FlatLaf.install(new FlatDarculaLaf());
            FlatLaf.updateUI();
        }
        DarkThemeCheck.setState(true);
    }//GEN-LAST:event_DarkThemeCheckActionPerformed

    private void NewFileActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NewFileActionPerformed
    {//GEN-HEADEREND:event_NewFileActionPerformed
        // TODO add your handling code here:

        String name = JOptionPane.showInputDialog("Enter Program Name: ");

        if (name != null)
        {
            name += ".JARVIS";
            files.add(new Files(name, "", ""));
            textHiglighter.add(new TextHighlight());
            textPanes.add(new JTextPane(textHiglighter.get(tabCtr).getDoc()));
            tabCtr++;
            JScrollPane scrollPane = new JScrollPane(textPanes.get(tabCtr - 1));
            TextLineNumber line = new TextLineNumber(textPanes.get(tabCtr - 1));

            scrollPane.setRowHeaderView(line);
            NotepadPane.addTab(name, null, scrollPane, name);
            NotepadPane.setSelectedIndex(tabCtr - 1);
            textPanes.get(tabCtr - 1).requestFocusInWindow();
        }


    }//GEN-LAST:event_NewFileActionPerformed

    private void RunButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_RunButtonActionPerformed
    {//GEN-HEADEREND:event_RunButtonActionPerformed
        // TODO add your handling code here:
        int curr = NotepadPane.getSelectedIndex();
        try
        {

            String fileName = files.get(curr).getFilename();
            String cd = "cd " + files.get(curr).getFileDirectory().replace(fileName, "");

            String javac = "\njavac " + files.get(curr).getFilename().replace(".JARVIS", ".java");
            String run = "\njava " + files.get(curr).getFilename().replace(".JARVIS", "");
            System.out.println(cd + javac + run);
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"" + cd + "&&" + run + "&& echo. && echo.  && pause  && exit\"");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_RunButtonActionPerformed

    private void BuildButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BuildButtonActionPerformed
    {//GEN-HEADEREND:event_BuildButtonActionPerformed
        // TODO add your handling code here:
        int curr = NotepadPane.getSelectedIndex();
        saveFile();
        String test = textPanes.get(curr).getText();

        Translate t = new Translate(test);
        String translated = t.compile();

        if (translated != null)
        {
            try
            {
                FileWriter fileWriter = new FileWriter(files.get(NotepadPane.getSelectedIndex()).getFileDirectory().replace(".JARVIS", ".java"));
                fileWriter.write(translated);
                fileWriter.close();

                String fileName = files.get(curr).getFilename();
                String fileDirectory = files.get(curr).getFileDirectory().replace(".JARVIS", ".java");
                String cd = "cd " + files.get(curr).getFileDirectory().replace(fileName, "");

                String javac = " javac " + files.get(curr).getFilename().replace(".JARVIS", ".java");
                String delete = " del " + files.get(curr).getFilename().replace(".JARVIS", ".java");
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"" + cd + " && " + javac + " && " + delete + "&& exit\"");

            } catch (Exception e)
            {
            }
        }

    }//GEN-LAST:event_BuildButtonActionPerformed

    private void CloseFileActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_CloseFileActionPerformed
    {//GEN-HEADEREND:event_CloseFileActionPerformed
        // TODO add your handling code here:

        files.remove(NotepadPane.getSelectedIndex());
        textPanes.remove(NotepadPane.getSelectedIndex());
        textHiglighter.remove(NotepadPane.getSelectedIndex());
        NotepadPane.remove(NotepadPane.getSelectedIndex());

        tabCtr--;

    }//GEN-LAST:event_CloseFileActionPerformed

    private void OpenFileActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_OpenFileActionPerformed
    {//GEN-HEADEREND:event_OpenFileActionPerformed
        // TODO add your handling code here:
        FileDialog fileDialog = new FileDialog(IDE.this, "Open File", FileDialog.LOAD);
        fileDialog.setFile("*.JARVIS");
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null)
        {
            String fileName = fileDialog.getFile();;
            String fileContent = "";
            String fileDirectory = fileDialog.getDirectory() + fileDialog.getFile();

            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(fileDirectory));
                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                    fileContent = sb.toString();
                }

                files.add(new Files(fileName, fileContent, fileDirectory));
                files.get(tabCtr).setSaved(true);
                textPanes.add(new JTextPane());
                textHiglighter.add(new TextHighlight());
                textPanes.get(tabCtr).setDocument(textHiglighter.get(tabCtr).getDoc());
                textPanes.get(tabCtr).setText(fileContent);
                JScrollPane scrollPane = new JScrollPane(textPanes.get(tabCtr));
                TextLineNumber lineNumber = new TextLineNumber(textPanes.get(tabCtr));
                scrollPane.setRowHeaderView(lineNumber);
                tabCtr++;
                NotepadPane.addTab(fileDialog.getFile(), null, scrollPane, fileDialog.getFile());
                NotepadPane.setSelectedIndex(tabCtr - 1);
                textPanes.get(tabCtr - 1).requestFocusInWindow();
            } catch (Exception e)
            {

            }

        }
    }//GEN-LAST:event_OpenFileActionPerformed

    private void SaveFileActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SaveFileActionPerformed
    {//GEN-HEADEREND:event_SaveFileActionPerformed
        // TODO add your handling code here:

        if (files.get(NotepadPane.getSelectedIndex()).isSaved() == true)
        {
            try
            {
                FileWriter fileWriter = new FileWriter(files.get(NotepadPane.getSelectedIndex()).getFileDirectory());
                fileWriter.write(textPanes.get(NotepadPane.getSelectedIndex()).getText());
                fileWriter.close();
            } catch (Exception e)
            {
            }
        }
        else
        {
            FileDialog fileDialog = new FileDialog(IDE.this, "SAVE File", FileDialog.SAVE);
            fileDialog.setFile(files.get(NotepadPane.getSelectedIndex()).getFilename());
            fileDialog.setVisible(true);
            if (fileDialog.getFile() != null)
            {
                try
                {
                    files.get(NotepadPane.getSelectedIndex()).setFileDirectory(fileDialog.getDirectory() + fileDialog.getFile());
                    files.get(NotepadPane.getSelectedIndex()).setFilename(fileDialog.getFile());
                    files.get(NotepadPane.getSelectedIndex()).setFileContent(textPanes.get(NotepadPane.getSelectedIndex()).getText());

                    FileWriter fileWriter = new FileWriter(files.get(NotepadPane.getSelectedIndex()).getFileDirectory());
                    fileWriter.write(textPanes.get(NotepadPane.getSelectedIndex()).getText());
                    files.get(NotepadPane.getSelectedIndex()).Saved();
                    fileWriter.close();
                } catch (Exception e)
                {
                }
            }
        }

    }//GEN-LAST:event_SaveFileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

         
      
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new IDE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuildButton;
    private javax.swing.JMenuItem CloseFile;
    private javax.swing.JCheckBoxMenuItem DarkThemeCheck;
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JCheckBoxMenuItem LightThemeCheck;
    private javax.swing.JMenuItem NewFile;
    private javax.swing.JTabbedPane NotepadPane;
    private javax.swing.JMenuItem OpenFile;
    private javax.swing.JMenu OptionMenu;
    private javax.swing.JButton RunButton;
    private javax.swing.JMenuItem SaveFile;
    private javax.swing.JMenu ThemeChooser;
    private javax.swing.JMenuBar jMenuBar2;
    // End of variables declaration//GEN-END:variables
}
