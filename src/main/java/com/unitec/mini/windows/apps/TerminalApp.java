/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.logic.KeyConstantsNowAllowed;
import com.unitec.mini.windows.logic.ShellCommandExecutor;
import com.unitec.mini.windows.logic.UserManager.User;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.text.DefaultCaret;

public class TerminalApp extends javax.swing.JInternalFrame  implements AppInterface{
    private User authUser;
    String username = "admin";
    private static int caretBlinkRate = 500;

    private ShellCommandExecutor commandExecutor;
    
    public TerminalApp() {
        initComponents();
        setComponents();
    }

    public void setComponents(){
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_terminal_20.png"));
        this.setFrameIcon(appIcon);

        textCommandArea.setBackground(new Color(0, 0, 0, 60));
        textCommandArea.setLineWrap(true);
        textCommandArea.setWrapStyleWord(true);
        textCommandArea.setEditable(false);
        
        try {   
            if (authUser != null) {
                username = authUser.getUsername();
            }

            String userPath = "/src/main/users" + File.separator + username;
            String projectDir = System.getProperty("user.dir") + userPath;
            File userRootdir = new File(projectDir);

            commandExecutor = new ShellCommandExecutor(this, textCommandArea, userRootdir);
        } catch (Exception e) {
            System.out.println("folder not found. close app?");
        }
        
        DefaultCaret caret = (DefaultCaret) textCommandArea.getCaret();
        caret.setBlinkRate(caretBlinkRate);
    }
    
    public void setAuthenticatedUser(User loggedUser){
        authUser = loggedUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textCommandArea = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Terminal");
        setOpaque(false);

        jPanel_Main.setBackground(new Color(0, 0, 0, 60));

        textCommandArea.setEditable(false);
        textCommandArea.setBackground(new java.awt.Color(0, 0, 0));
        textCommandArea.setColumns(20);
        textCommandArea.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        textCommandArea.setForeground(new java.awt.Color(255, 255, 255));
        textCommandArea.setLineWrap(true);
        textCommandArea.setRows(5);
        textCommandArea.setCaretColor(new java.awt.Color(255, 255, 255));
        textCommandArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textCommandAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textCommandArea);

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_Main, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textCommandAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCommandAreaKeyPressed

        if(KeyConstantsNowAllowed.exists(evt.getKeyCode())){
            return;
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            String text = textCommandArea.getText();
            if (text.charAt(text.length() - 1) == '>') {
                return;
            }

            textCommandArea.setText(text.substring(0, text.length() - 1));
            return;
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Get all text from JTextarea
            // Get the last line staring after caret 
            String text = textCommandArea.getText();
            int lastIndex = text.lastIndexOf('>');
            if (lastIndex != -1 && lastIndex < text.length() - 1) {
                commandExecutor.executeCommand(text.substring(lastIndex + 1).trim());
            }
            return;
        }

        String newText = textCommandArea.getText();
        newText += evt.getKeyChar();

        textCommandArea.setText(newText);
    }//GEN-LAST:event_textCommandAreaKeyPressed
    
    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textCommandArea;
    // End of variables declaration//GEN-END:variables
}