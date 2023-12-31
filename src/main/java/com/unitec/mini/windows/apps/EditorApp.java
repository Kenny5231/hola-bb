package com.unitec.mini.windows.apps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

public class EditorApp extends javax.swing.JInternalFrame implements AppInterface, ActionListener {

    JTextPane textPane;
    JScrollPane scrollPane;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;
    JButton fontColorBTN;
    JComboBox fontBox;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    public EditorApp() {
        initComponents();
        setComponents();
    }

    public void setComponents() {
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_editor_20.png"));
        this.setFrameIcon(appIcon);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Editor de Texto");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());

        textPane = new JTextPane();
        textPane.setFont(new Font("Arial", Font.PLAIN, 20));

        scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Size:");

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                StyledDocument doc = textPane.getStyledDocument();
                int start = textPane.getSelectionStart();
                int end = textPane.getSelectionEnd();

                SimpleAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setFontSize(attrs, (int) fontSizeSpinner.getValue());

                doc.setCharacterAttributes(start, end, attrs, false);

            }

        });

        //boldButton=new JButton("B");
        //boldButton.addActionListener(this);
        //italicButton=new JButton("I");
        //italicButton.addActionListener(this);
        fontColorBTN = new JButton("Color");
        fontColorBTN.addActionListener(this);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox(fonts);
        fontBox.addActionListener(this);
        fontBox.setSelectedItem("Arial");

        //menu bar------------------
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        //menu bar------------------

        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorBTN);
        this.add(fontBox);
        //this.add(boldButton);
        //this.add(italicButton);
        this.add(scrollPane);
        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Editor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //color
        if (e.getSource() == fontColorBTN) {
            StyledDocument doc = textPane.getStyledDocument();
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            SimpleAttributeSet attrs = new SimpleAttributeSet();
            JColorChooser colorChooser = new JColorChooser();
            Color color = colorChooser.showDialog(null, "", Color.black);
            StyleConstants.setForeground(attrs, color);

            doc.setCharacterAttributes(start, end, attrs, false);

        }

        //get font
        if (e.getSource() == fontBox) {
            StyledDocument doc = textPane.getStyledDocument();
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setFontFamily(attrs, (String) fontBox.getSelectedItem());
            doc.setCharacterAttributes(start, end, attrs, false);
        }

        //Bold
        /*
        if(e.getSource()==boldButton){
            StyledDocument doc=textPane.getStyledDocument();
            int start=textPane.getSelectionStart();
            int end= textPane.getSelectionEnd();
            
            SimpleAttributeSet attrs= new SimpleAttributeSet();
            StyleConstants.setBold(attrs, !StyleConstants.isBold(attrs));
            
            doc.setCharacterAttributes(start, end, attrs, false);
        }
         */
        //Italic
        /*
        if(e.getSource()==italicButton){
            StyledDocument doc=textPane.getStyledDocument();
            int start=textPane.getSelectionStart();
            int end= textPane.getSelectionEnd();
            
            SimpleAttributeSet attrs= new SimpleAttributeSet();
            StyleConstants.setItalic(attrs, !StyleConstants.isItalic(attrs));
            
            doc.setCharacterAttributes(start, end, attrs, false);
        }
         */
        if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        textPane.setText("");
                        textPane.getStyledDocument().remove(0, textPane.getStyledDocument().getLength());

                        int totalStyles = Integer.parseInt(fileIn.nextLine());

                        for (int i = 0; i < totalStyles; i++) {
                            String text = fileIn.nextLine();

                            Color textColor = Color.decode(fileIn.nextLine());

                            String fontFamily = fileIn.nextLine();

                            int fontSize = Integer.parseInt(fileIn.nextLine());

                            SimpleAttributeSet attrs = new SimpleAttributeSet();
                            StyleConstants.setForeground(attrs, textColor);
                            StyleConstants.setFontFamily(attrs, fontFamily);
                            StyleConstants.setFontSize(attrs, fontSize);

                            int offset = textPane.getStyledDocument().getLength();
                            textPane.getStyledDocument().insertString(offset, text, attrs);
                        }
                    }
                } catch (FileNotFoundException | BadLocationException ex) {
                    ex.printStackTrace();
                } finally {
                    if (fileIn != null) {
                        fileIn.close();
                    }
                }
            }
        }

        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut = new PrintWriter(file);

                    int totalStyles = textPane.getStyledDocument().getLength();
                    fileOut.println(totalStyles);

                    for (int i = 0; i < totalStyles; i++) {
                        Element element = textPane.getStyledDocument().getCharacterElement(i);
                        AttributeSet attrs = element.getAttributes();

                        // Save text
                        String text = textPane.getStyledDocument().getText(i, 1);
                        fileOut.println(text);

                        // Save color
                        Color textColor = StyleConstants.getForeground(attrs);
                        fileOut.println(String.format("#%06X", textColor.getRGB() & 0xFFFFFF));

                        // Save font
                        String fontFamily = StyleConstants.getFontFamily(attrs);
                        fileOut.println(fontFamily);

                        // Save size
                        int fontSize = StyleConstants.getFontSize(attrs);
                        fileOut.println(fontSize);
                    }
                } catch (FileNotFoundException | BadLocationException ex) {
                    ex.printStackTrace();
                } finally {
                    if (fileOut != null) {
                        fileOut.close();
                    }
                }
            }
        }

        if (e.getSource() == exitItem) {
            this.closeFrame();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
