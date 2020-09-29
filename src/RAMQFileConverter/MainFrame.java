package ramqfileconverter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JOptionPane;
import org.xml.sax.SAXException;
import java.util.Properties;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;

/**
 * <p>Title: RAMQFileConverter</p>
 *
 * <p>Description: Application to convert RAMQ reclamation files to XML</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Sebastien Vezina</p>
 *
 * @author Sebastien Vezina: vezinase@gmail.com
 * @version 1.1
 */
class MainFrame extends JFrame {
    JPanel contentPane;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileAddFiles = new JMenuItem();
    JMenuItem jMenuFileClearList = new JMenuItem();
    JMenuItem jMenuFileConvert = new JMenuItem();
    JMenuItem jMenuFileExit = new JMenuItem();

    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpSystemProperties = new JMenuItem();
    JMenuItem jMenuHelpAbout = new JMenuItem();
    JScrollPane jScrollPane1 = new JScrollPane();
    JLabel jLabel1 = new JLabel();
    JTextArea jTextArea1 = new JTextArea();
    JButton jButtonAddFiles = new JButton();
    JButton jButtonClearList = new JButton();
    JButton jButtonConvert = new JButton();

    //JFileChooser fc = new JFileChooser();
    JFileChooser fc = new JFileChooser("C:\\Documents and Settings\\seb\\Desktop\\Dr Robbins\\Project\\Examples");

    java.util.List files = new ArrayList();
    FileConverter fileConverter;
    JMenu jMenu1 = new JMenu();

    public MainFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        this.setResizable(false);
        setSize(new Dimension(356, 431));
        setTitle("RAMQ File Converter");
        jMenuFile.setText("File");
        jMenuFileAddFiles.setText("Add files");
        jMenuFileClearList.setText("Clear list");
        jMenuFileConvert.setText("Convert");
        jMenuFileExit.setText("Exit");
        jMenuFileAddFiles.addActionListener(new
                                            MainFrame_jMenuFileAddFiles_ActionAdapter(this));
        jMenuFileClearList.addActionListener(new
                                             MainFrame_jMenuFileClearList_ActionAdapter(this));
        jMenuFileConvert.addActionListener(new
                                           MainFrame_jMenuFileConvert_ActionAdapter(this));
        jMenuFileExit.addActionListener(new
                                        MainFrame_jMenuFileExit_ActionAdapter(this));
        jMenuHelp.setText("Help");
        jMenuHelpSystemProperties.setText("System Info");
        jMenuHelpSystemProperties.addActionListener(new
                                         MainFrame_jMenuHelpSystemProperties_ActionAdapter(this));
        jMenuHelpAbout.setText("About");
        jMenuHelpAbout.addActionListener(new
                                         MainFrame_jMenuHelpAbout_ActionAdapter(this));
        jScrollPane1.setBounds(new Rectangle(10, 32, 226, 253));
        jLabel1.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 14));
        jLabel1.setText("List of files to convert");
        jLabel1.setBounds(new Rectangle(56, 12, 140, 14));
        jButtonAddFiles.setBounds(new Rectangle(251, 40, 84, 23));
        jButtonAddFiles.setBorder(BorderFactory.createRaisedBevelBorder());
        jButtonAddFiles.setText("Add files");
        jButtonAddFiles.addActionListener(new
                MainFrame_jButtonAddFiles_actionAdapter(this));
        jButtonClearList.setBounds(new Rectangle(252, 73, 83, 23));
        jButtonClearList.setBorder(BorderFactory.createRaisedBevelBorder());
        jButtonClearList.setText("Clear list");
        jButtonClearList.addActionListener(new
                MainFrame_jButtonClearList_actionAdapter(this));
        jButtonConvert.setBounds(new Rectangle(252, 106, 83, 23));
        jButtonConvert.setBorder(BorderFactory.createRaisedBevelBorder());
        jButtonConvert.setText("Convert");
        jButtonConvert.addActionListener(new
                                         MainFrame_jButtonConvert_actionAdapter(this));
        fc.setMultiSelectionEnabled(true);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 14));
        jTextArea1.setEditable(false);
        jMenuBar1.add(jMenuFile);
        jMenuFile.add(jMenuFileAddFiles);
        jMenuFile.add(jMenuFileClearList);
        jMenuFile.add(jMenuFileConvert);
        jMenuFile.add(jMenuFileExit);
        jMenuBar1.add(jMenuHelp);
        jMenuHelp.add(jMenuHelpSystemProperties);
        jMenuHelp.add(jMenuHelpAbout);
        contentPane.add(jScrollPane1);
        jScrollPane1.getViewport().add(jTextArea1);
        contentPane.add(jLabel1);
        contentPane.add(jButtonAddFiles);
        contentPane.add(jButtonClearList);
        contentPane.add(jButtonConvert);
        setJMenuBar(jMenuBar1);

        try {
            fileConverter = new FileConverter();
        }
        catch(SAXNotRecognizedException e) {
            JOptionPane.showMessageDialog(this,
                                          "SAXNotRecognizedException: Unable to create xmlValidator\n"+
                                          "Possible cause:\nA Java version >= 1.5 is needed to run this application\n"+
                                          "Your current Java version is "+System.getProperties().getProperty("java.version"),
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch(SAXNotSupportedException e) {
            JOptionPane.showMessageDialog(this,
                                          "SAXNotSupportedException: Unable to create xmlValidator\n"+
                                          "Possible cause:\nA Java version >= 1.5 is needed to run this application\n"+
                                          "Your current Java version is "+System.getProperties().getProperty("java.version"),
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch(SAXException e) {
            JOptionPane.showMessageDialog(this,
                                          "SAXException: Unable to create xmlValidator\n"+
                                          "Possible cause:\nA Java version >= 1.5 is needed to run this application\n"+
                                          "Your current Java version is "+System.getProperties().getProperty("java.version"),
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    void popSystemProperties(){

        Properties p = System.getProperties();
        StringBuffer sb = new StringBuffer("System Properties:\n\n");
        sb.append("os.name="+p.getProperty("os.name")+"\n");
        sb.append("os.version="+p.getProperty("os.version")+"\n");
        sb.append("os.arch="+p.getProperty("os.arch")+"\n\n");
        sb.append("java.version="+p.getProperty("java.version")+"\n");
        sb.append("java.vendor="+p.getProperty("java.vendor")+"\n");
        sb.append("java.home="+p.getProperty("java.home")+"\n\n");
        sb.append("java.vm.specification.version="+p.getProperty("java.vm.specification.version")+"\n");
        sb.append("java.vm.specification.vendor="+p.getProperty("java.vm.specification.vendor")+"\n");
        sb.append("java.vm.specification.name="+p.getProperty("java.vm.specification.name")+"\n");
        sb.append("java.vm.version="+p.getProperty("java.vm.version")+"\n");
        sb.append("java.vm.vendor="+p.getProperty("java.vm.vendor")+"\n");
        sb.append("java.vm.name="+p.getProperty("java.vm.name")+"\n");

        JOptionPane.showMessageDialog(this,
                              sb.toString(),
                              "System Properties",
                              JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * File | Exit action performed.
     *
     * @param actionEvent ActionEvent
     */
    void jMenuFileExit_actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Help | About action performed.
     *
     * @param actionEvent ActionEvent
     */
    void jMenuHelpAbout_actionPerformed(ActionEvent actionEvent) {
        MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.pack();
        dlg.setVisible(true);
    }

    public void jButtonAddFiles_actionPerformed(ActionEvent e) {
        File[] selectedFiles;
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            if(files.size() == 0) {
                jTextArea1.setText("");
            }

            selectedFiles = fc.getSelectedFiles();
            for (int i = 0; i < selectedFiles.length; i++) {
                files.add(selectedFiles[i]);
                jTextArea1.append(selectedFiles[i].getName() + "\n");
            }
        }
    }

    public void jButtonClearList_actionPerformed(ActionEvent e) {
        jTextArea1.setText("");
        files.clear();
    }

    public void jButtonConvert_actionPerformed(ActionEvent e) {

        if (files.size() == 0) {
            JOptionPane.showMessageDialog(this,
                                          "No files to convert, please add files to convert in the list.",
                                          "Warning",
                                          JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String resultName = fileConverter.convert(files);
                JOptionPane.showMessageDialog(this,
                                              "The conversion process was successfull.\n",
                                              "Conversion successfull",
                                              JOptionPane.INFORMATION_MESSAGE);
                jTextArea1.setText("");
                jTextArea1.append("Conversion successfull ...\n");
                jTextArea1.append("Result file named: "+resultName);
                files.clear();
            }
            catch (FileFormatException ex) {
                String filename = ex.getFileName();
                String info = ex.getInfo();
                JOptionPane.showMessageDialog(this,
                                              "File format error with file <"+filename+">\n"+
                                              info,
                                              "Conversion Failed",
                                              JOptionPane.WARNING_MESSAGE);
                jTextArea1.setText("");
                jTextArea1.append("Conversion failed !\n");
                files.clear();
            }
            catch(PathException ex) {
                 JOptionPane.showMessageDialog(this,
                                              "All files to be converted must be in the same directory.\n"+
                                              "Please try again",
                                              "Warning",
                                              JOptionPane.WARNING_MESSAGE);
                 jTextArea1.setText("");
                 jTextArea1.append("Conversion failed !\n");
                 files.clear();
            }
            catch(ControlFileException ex) {
                 JOptionPane.showMessageDialog(this,
                                              ex.getInfo()+"\nPlease try again",
                                              "Warning",
                                              JOptionPane.WARNING_MESSAGE);
                 jTextArea1.setText("");
                 jTextArea1.append("Conversion failed !\n");
                 files.clear();
            }
            catch(UnexistingDFileException ex) {
                 JOptionPane.showMessageDialog(this,
                                              ex.getInfo()+"\nPlease try again",
                                              "Warning",
                                              JOptionPane.WARNING_MESSAGE);
                 jTextArea1.setText("");
                 jTextArea1.append("Conversion failed !\n");
                 files.clear();
            }
            catch(DFilesCountException ex) {
                JOptionPane.showMessageDialog(this,
                                              ex.getInfo()+"\nPlease try again",
                                              "Warning",
                                              JOptionPane.WARNING_MESSAGE);
                jTextArea1.setText("");
                jTextArea1.append("Conversion failed !\n");
                files.clear();
            }
            catch(InvalidEntryException ex) {
                JOptionPane.showMessageDialog(this,
                                              ex.getInfo(),
                                              "Error",
                                              JOptionPane.ERROR_MESSAGE);
                jTextArea1.setText("");
                jTextArea1.append("Conversion failed !\n");
                files.clear();
            }
            catch(SAXException ex){

                String errorMess = (ex.getMessage() != null) ? ex.getMessage() :"";

                JOptionPane.showMessageDialog(this,
                                              "One of the generated XML files failed validation.\n"+
                                              "The files to convert could contain values that don't "+
                                              "respect the new specifications and constraints of the RAMQ.\n"+
                                              errorMess,
                                              "Error",
                                              JOptionPane.ERROR_MESSAGE);
                jTextArea1.setText("");
                jTextArea1.append("Conversion failed !\n");
                files.clear();
                System.err.println(ex);
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this,
                                              "The conversion process failed !\n"+ex.getMessage(),
                                              "Error",
                                              JOptionPane.ERROR_MESSAGE);
                jTextArea1.setText("");
                jTextArea1.append("Conversion failed !\n");
                files.clear();
                System.err.println(ex);
            }
        }
    }
}

class MainFrame_jButtonConvert_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jButtonConvert_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonConvert_actionPerformed(e);
    }
}


class MainFrame_jButtonClearList_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jButtonClearList_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonClearList_actionPerformed(e);
    }
}


class MainFrame_jButtonAddFiles_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jButtonAddFiles_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonAddFiles_actionPerformed(e);
    }
}

class MainFrame_jMenuFileAddFiles_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuFileAddFiles_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonAddFiles_actionPerformed(e);
    }
}

class MainFrame_jMenuFileClearList_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuFileClearList_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonClearList_actionPerformed(e);
    }
}

class MainFrame_jMenuFileConvert_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuFileConvert_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonConvert_actionPerformed(e);
    }
}


class MainFrame_jMenuFileExit_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuFileExit_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.jMenuFileExit_actionPerformed(actionEvent);
    }
}


class MainFrame_jMenuHelpAbout_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuHelpAbout_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.jMenuHelpAbout_actionPerformed(actionEvent);
    }
}

class MainFrame_jMenuHelpSystemProperties_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuHelpSystemProperties_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        //adaptee.jMenuHelpSystemProperties_actionPerformed(actionEvent);
        adaptee.popSystemProperties();
    }
}
