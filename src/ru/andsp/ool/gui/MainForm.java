/*
 * The MIT License
 *
 * Copyright 2014 Serg.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ru.andsp.ool.gui;

import static java.awt.EventQueue.invokeLater;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;
import static ru.andsp.ool.OraObjectLoader.load;
import static ru.andsp.ool.SettingHelper.invertTypes;
import static ru.andsp.ool.SettingHelper.write;
import ru.andsp.ool.domain.Setting;

/**
 *
 * @author Serg
 */
public class MainForm extends javax.swing.JFrame {

    private ArrayList<String> leftTypes;
    private ArrayList<String> rightTypes;

    public MainForm() {
        initComponents();
    }

    private void refreshModel() {
        CustomListModel ms = new CustomListModel(rightTypes);
        lSelected.setModel(ms);
        CustomListModel mus = new CustomListModel(leftTypes);
        lUnSelected.setModel(mus);
    }

    public void setSetting(Setting setting) {
        tfDBName.setText(setting.getDbName());
        tfHost.setText(setting.getHost());
        tfOutDir.setText(setting.getOutDir());
        pfPassword.setText(setting.getPassword());
        tfPort.setText(setting.getPort());
        tfUsername.setText(setting.getUsername());
        rightTypes = setting.getTypes();
        leftTypes = invertTypes(setting.getTypes());
        refreshModel();
    }

    private Setting getSetting() {
        Setting s = new Setting();
        s.setClear(Setting.CLEAR);
        s.setDbName(tfDBName.getText());
        s.setHost(tfHost.getText());
        s.setOutDir(tfOutDir.getText());
        s.setPassword(pfPassword.getText());
        s.setPort(tfPort.getText());
        s.setUsername(tfUsername.getText());
        s.setTypes(rightTypes);
        return s;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfHost = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lUnSelected = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lSelected = new javax.swing.JList();
        tfOutDir = new javax.swing.JTextField();
        bSelect = new javax.swing.JButton();
        bUnselect = new javax.swing.JButton();
        tfUsername = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        tfDBName = new javax.swing.JTextField();
        bSaveConfig = new javax.swing.JButton();
        bUpload = new javax.swing.JButton();
        bSelectOutDir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Хост");

        jLabel2.setText("Порт");

        jLabel3.setText("Имя БД");

        jLabel4.setText("Пользователь");

        jLabel5.setText("Пароль");

        jLabel6.setText("Директория для выгрузки");

        lUnSelected.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lUnSelected.setMaximumSize(new java.awt.Dimension(200, 130));
        jScrollPane1.setViewportView(lUnSelected);

        jLabel7.setText("ВЫгружаемые типы (все если ничего не выбрано)");

        jLabel8.setText("Доступные");

        jLabel9.setText("Выбранные");

        lSelected.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lSelected.setMaximumSize(new java.awt.Dimension(200, 130));
        jScrollPane2.setViewportView(lSelected);

        bSelect.setLabel(">");
        bSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelectActionPerformed(evt);
            }
        });

        bUnselect.setLabel("<");
        bUnselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUnselectActionPerformed(evt);
            }
        });

        bSaveConfig.setText("Сохранить настройки");
        bSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveConfigActionPerformed(evt);
            }
        });

        bUpload.setText("Выгрузить");
        bUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUploadActionPerformed(evt);
            }
        });

        bSelectOutDir.setText("...");
        bSelectOutDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelectOutDirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfHost)
                                            .addComponent(tfDBName, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))))
                                .addGap(0, 29, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bUnselect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(tfPort)))
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfOutDir, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bSelectOutDir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bSaveConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addComponent(bUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tfPort))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfOutDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSelectOutDir))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(bSelect)
                        .addGap(18, 18, 18)
                        .addComponent(bUnselect)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bUpload)
                    .addComponent(bSaveConfig))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveConfigActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            write(getSetting(), file.getAbsolutePath());
        }
    }//GEN-LAST:event_bSaveConfigActionPerformed

    private void bUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUploadActionPerformed
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                bUpload.setEnabled(false);
                try {
                    load(getSetting());
                } finally {
                    bUpload.setEnabled(true);
                }
            }
        });
        t.start();
    }//GEN-LAST:event_bUploadActionPerformed

    private void bSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelectActionPerformed
        if (lUnSelected.getSelectedIndex() > -1) {
            moveElementToRight(lUnSelected.getSelectedValue().toString());
        }
    }//GEN-LAST:event_bSelectActionPerformed

    private void bUnselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUnselectActionPerformed
        if (lSelected.getSelectedIndex() > -1) {
            moveElementToLeft(lSelected.getSelectedValue().toString());
        }
    }//GEN-LAST:event_bUnselectActionPerformed

    private void bSelectOutDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelectOutDirActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            tfOutDir.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_bSelectOutDirActionPerformed

    private void moveElementToRight(String elem) {
        leftTypes.remove(elem);
        rightTypes.add(elem);
        refreshModel();
    }

    private void moveElementToLeft(String elem) {
        rightTypes.remove(elem);
        leftTypes.add(elem);
        refreshModel();
    }

    public static void start(final Setting setting) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
        invokeLater(new Runnable() {
            @Override
            public void run() {
                MainForm t = new MainForm();
                t.setVisible(true);
                t.setSetting(setting);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSaveConfig;
    private javax.swing.JButton bSelect;
    private javax.swing.JButton bSelectOutDir;
    private javax.swing.JButton bUnselect;
    private javax.swing.JButton bUpload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lSelected;
    private javax.swing.JList lUnSelected;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfDBName;
    private javax.swing.JTextField tfHost;
    private javax.swing.JTextField tfOutDir;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
