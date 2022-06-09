/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package p20_responsi;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabulous
 */
public class Transaksi_Pinjam extends javax.swing.JFrame {
    Connection conn;
    Statement stm;
            String url = "jdbc:mysql://localhost/pbo_pinjammobil";
            String user = "root";
            String pass = "";

    /**
     * Creates new form Transaksi_Pinjam
     */
    public Transaksi_Pinjam() throws SQLException {
        initComponents();
        kontrol_atas(false);
        tampil_data_pinjam();
    }
    
    private void tampil_data_pinjam() throws SQLException {
        DefaultTableModel tmodel = new DefaultTableModel();
        
        tmodel.addColumn("NAMA PELANGGAN");
        tmodel.addColumn("MERK MOBIL");
        tmodel.addColumn("JENIS");
        tmodel.addColumn("JUMLAH");
        tmodel.addColumn("BIAYA SEWA");
        tmodel.addColumn("TOTAL BIAYA");
        
        jTable1.setModel(tmodel);
        
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        stm = conn.createStatement();
        ResultSet res = stm .executeQuery("SELECT * FROM peminjaman");
        
        while (res.next()) {
            tmodel.addRow(new Object[]{
                res.getString("nama_pel"),
                res.getString("merk_mobil"),
                res.getString("jenis"),
                res.getString("jumlah"),
                res.getString("biaya_sewa"),
                res.getString("total_biaya")
            });
        }
    }

    void kontrol_atas(boolean x) {
        xkdmobil.setEnabled(x);
        xmerkmobil.setEnabled(x);
        xjenis.setEnabled(x);
        xjmlmobil.setEnabled(x);
        xbiayasewa.setEnabled(x);
        xkdcus.setEnabled(x);
        xnmcus.setEnabled(x);
        xalmtcus.setEnabled(x);
        xtotbiaya.setEnabled(x);
    }
    
    private void simpan_data() throws SQLException {
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO peminjaman"
                + " (kode_pel, nama_pel, alamat_pel, kode_mobil, merk_mobil, jenis, jumlah, biaya_sewa, total_biaya)"
                + " VALUES ('"+xkdcus.getText()+"', '"+xnmcus.getText()+"', '"+xalmtcus.getText()+"', '"+xkdmobil.getText()+"',"
                        + " '"+xmerkmobil.getText()+"', '"+xjenis.getSelectedItem()+"', '"+xjmlmobil.getText()+"', '"+xbiayasewa.getText()+"',"
                        + " '"+xtotbiaya.getText()+"')");
        tampil_data_pinjam();
        
        if ( pstm.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Simpan Sukses", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Simpan Gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            pstm.close();
        }
    }
    
    private void ubah_data() throws SQLException {
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        PreparedStatement pstm = conn.prepareStatement("UPDATE peminjaman SET "
                + "nama_pel = '"+xnmcus.getText()+"', alamat_pel = '"+xalmtcus.getText()+"', nama_pel = '"+xnmcus.getText()+"', kode_mobil = '"+xkdmobil.getText()+"',"
                + "merk_mobil = '"+xmerkmobil.getText()+"', jenis = '"+xjenis.getSelectedItem()+"', jumlah = '"+xjmlmobil.getText()+"', biaya_sewa = '"+xbiayasewa.getText()+"',"
                + "total_biaya = '"+xtotbiaya.getText()+"' WHERE kode_pel = '"+xkdcus.getText()+"'");
        tampil_data_pinjam();
        
        if ( pstm.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Ubah Data Sukses", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Ubah Data Gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            pstm.close();
        }
    }
    
    private void kosongkan_input() {
        xkdmobil.setText("");
        xmerkmobil.setText("");
        xjmlmobil.setText("");
        xbiayasewa.setText("");
        xkdcus.setText("");
        xnmcus.setText("");
        xalmtcus.setText("");
        xtotbiaya.setText("");
        
        xkdmobil.requestFocus();
    }
    
    private void hapus_data_pinjam() throws SQLException {
        try {
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stm = conn.createStatement();
            boolean hapus = stm.execute("DELETE FROM peminjaman");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Kesalahan " + err);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        xmerkmobil = new javax.swing.JTextField();
        xkdmobil = new javax.swing.JTextField();
        xjenis = new javax.swing.JComboBox<>();
        xjmlmobil = new javax.swing.JTextField();
        xbiayasewa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        xkdcus = new javax.swing.JTextField();
        xnmcus = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        xalmtcus = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        xtotbiaya = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnInput = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("PENDATAAN MOBIL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(254, 254, 254))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("KODE MOBIL");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("MERK MOBIL");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("JENIS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("JUMLAH");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("BIAYA SEWA");

        xkdmobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xkdmobilActionPerformed(evt);
            }
        });
        xkdmobil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                xkdmobilKeyPressed(evt);
            }
        });

        xjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SUV", "MPV", "Sedan", "Sport", "Off-road", "Truck" }));

        xjmlmobil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                xjmlmobilKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("KODE PELANGGAN");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("NAMA PELANGGAN");

        xkdcus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xkdcusActionPerformed(evt);
            }
        });
        xkdcus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                xkdcusKeyPressed(evt);
            }
        });

        xnmcus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xnmcusActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("ALAMAT PELANGGAN");

        xalmtcus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xalmtcusActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("TOTAL BIAYA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(xbiayasewa, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(xkdmobil, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(xmerkmobil)
                    .addComponent(xjmlmobil, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(xjenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(xkdcus, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(xnmcus)
                            .addComponent(xalmtcus))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(38, 38, 38)
                        .addComponent(xtotbiaya, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(xkdmobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(xkdcus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(xmerkmobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(xnmcus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(xjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(xalmtcus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(xjmlmobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(xbiayasewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(xtotbiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 204));

        btnInput.setBackground(new java.awt.Color(51, 51, 51));
        btnInput.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInput.setForeground(new java.awt.Color(255, 255, 255));
        btnInput.setText("INPUT");
        btnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(51, 51, 51));
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(51, 51, 51));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(51, 51, 51));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(51, 51, 51));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnInput)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addGap(52, 52, 52))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void xkdmobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xkdmobilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xkdmobilActionPerformed

    private void xkdcusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xkdcusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xkdcusActionPerformed

    private void xnmcusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xnmcusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xnmcusActionPerformed

    private void xalmtcusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xalmtcusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xalmtcusActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputActionPerformed
        // TODO add your handling code here:
        kontrol_atas(true);
        xkdmobil.requestFocus();
    }//GEN-LAST:event_btnInputActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            // TODO add your handling code here:
            simpan_data();
            kosongkan_input();
            tampil_data_pinjam();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi_Pinjam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void xkdmobilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_xkdmobilKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(xkdmobil.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Kode Mobil Tidak Boleh Kosong !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    conn = (Connection) DriverManager.getConnection(url, user, pass);
                    stm = conn.createStatement();
                    ResultSet res = stm .executeQuery("SELECT * FROM mobil WHERE kode_mobil = '"+xkdmobil.getText()+"' ");
                    if (res.next()) {
                        xmerkmobil.setText(res.getString("merk_mobil"));
                        xbiayasewa.setText(res.getString("harga"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Transaksi_Pinjam.class.getName()).log(Level.SEVERE, null, ex);
                }

                xkdcus.requestFocus();
            }
        }
    }//GEN-LAST:event_xkdmobilKeyPressed

    private void xkdcusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_xkdcusKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(xkdmobil.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Kode Pelanggan Tidak Boleh Kosong !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    conn = (Connection) DriverManager.getConnection(url, user, pass);
                    stm = conn.createStatement();
                    ResultSet res = stm .executeQuery("SELECT * FROM pelanggan WHERE kode_pel = '"+xkdcus.getText()+"' ");
                    if (res.next()) {
                        xnmcus.setText(res.getString("nama_pel"));
                        xalmtcus.setText(res.getString("alamat_pel"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Transaksi_Pinjam.class.getName()).log(Level.SEVERE, null, ex);
                }

                xkdcus.requestFocus();
            }
        }
    }//GEN-LAST:event_xkdcusKeyPressed

    private void xjmlmobilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_xjmlmobilKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(xjmlmobil.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Jumlah Sewa Mobil Tidak Boleh Kosong !", 
                        "Error !", JOptionPane.WARNING_MESSAGE);
            } else {
                int hrg_sewa = Integer.parseInt(this.xbiayasewa.getText());
                int jml_sewa = Integer.parseInt(this.xjmlmobil.getText());

                int tot_biaya;
                tot_biaya = (hrg_sewa * jml_sewa);
                xtotbiaya.setText("" + tot_biaya);
                
            }
        }
    }//GEN-LAST:event_xjmlmobilKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            hapus_data_pinjam();
            tampil_data_pinjam();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi_Pinjam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            ubah_data();
            kosongkan_input();
            tampil_data_pinjam();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi_Pinjam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi_Pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Transaksi_Pinjam().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Transaksi_Pinjam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInput;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField xalmtcus;
    private javax.swing.JTextField xbiayasewa;
    private javax.swing.JComboBox<String> xjenis;
    private javax.swing.JTextField xjmlmobil;
    private javax.swing.JTextField xkdcus;
    private javax.swing.JTextField xkdmobil;
    private javax.swing.JTextField xmerkmobil;
    private javax.swing.JTextField xnmcus;
    private javax.swing.JTextField xtotbiaya;
    // End of variables declaration//GEN-END:variables
}
