package pbo_uas_12720;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Check_out extends javax.swing.JFrame {
    int Pendapatan_keuangan, sub_total;
    int kamarA_terisi, kamarB_terisi, kamarC_terisi;
    int kamarA_kosong, kamarB_kosong, kamarC_kosong;
    String kelas;
    
    Connection conn;
    Statement stm;
            String url = "jdbc:mysql://localhost/uas_hotel_12720";
            String user = "root";
            String pass = "";

    public Check_out() throws SQLException {
        initComponents();
        input_tamu(false);
        input_checkout(false);
        hapus_temporary_checkout();
        getDataLaporan();
        tampil_data();
    }
    
    void input_tamu(boolean x) {
        kodeTamu.setEnabled(x);
        namaTamu.setEnabled(x);
        usiaTamu.setEnabled(x);
        jenisKelamin.setEnabled(x);
    }
    
    void input_checkout(boolean x) {
        kodeKamar.setEnabled(x);
        kelasKamar.setEnabled(x);
        posisiKamar.setEnabled(x);
        hargaKamar.setEnabled(x);
        fasilitas.setEnabled(x);
        tglCheckout.setEnabled(x);
        jamCheckout.setEnabled(x);
        lamaInap.setEnabled(x);
        biayaTotal.setEnabled(x);
    }
    
    private void getDataLaporan() throws SQLException {
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        stm = conn.createStatement();
        ResultSet res = stm .executeQuery("SELECT * FROM laporan");
        
        if (res.next()) {
        kamarA_terisi = res.getInt("kamarA_terisi");
        kamarB_terisi = res.getInt("kamarB_terisi");
        kamarC_terisi = res.getInt("kamarC_terisi");
        
        kamarA_kosong = res.getInt("kamarA_kosong");
        kamarB_kosong = res.getInt("kamarB_kosong");
        kamarC_kosong = res.getInt("kamarC_kosong");
        
        Pendapatan_keuangan = res.getInt("Pendapatan_keuangan");
        
        setTextLaporan();
            }
    }
    
    private void setTextLaporan() throws SQLException {
        kamarAterisi.setText("" + kamarA_terisi);
        kamarBterisi.setText("" + kamarB_terisi);
        kamarCterisi.setText("" + kamarC_terisi);

        kamarAkosong.setText("" + kamarA_kosong);
        kamarBkosong.setText("" + kamarB_kosong);
        kamarCkosong.setText("" + kamarC_kosong);
    }
    
    private void updateDataLaporan() throws SQLException {
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        PreparedStatement pstm = conn.prepareStatement("UPDATE laporan SET "
                + "kamarA_terisi = '"+kamarAterisi.getText()+"', kamarB_terisi = '"+kamarBterisi.getText()+"', kamarC_terisi = '"+kamarCterisi.getText()+"', kamarA_kosong = '"+kamarAkosong.getText()+"',"
                + "kamarB_kosong = '"+kamarBkosong.getText()+"', kamarC_kosong = '"+kamarCkosong.getText()+"', Pendapatan_keuangan = '"+Pendapatan_keuangan+"' WHERE 1");
        
        if ( pstm.executeUpdate() > 0) {
            
        }
    }
    
    private void tampil_data() throws SQLException {
        DefaultTableModel tmodel = new DefaultTableModel();
        
        tmodel.addColumn("KODE TAMU");
        tmodel.addColumn("NAMA TAMU");
        tmodel.addColumn("TANGGAL CHECK-OUT");
        tmodel.addColumn("JAM CHECK-OUT");
        tmodel.addColumn("KODE KAMAR");
        tmodel.addColumn("LAMA INAP");
        tmodel.addColumn("BIAYA");
        
        tempCheckout.setModel(tmodel);
        
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        stm = conn.createStatement();
        ResultSet res = stm .executeQuery("SELECT * FROM temporary_checkout");
        
        while (res.next()) {
            tmodel.addRow(new Object[]{
                res.getString("Kode_tamu"),
                res.getString("Nama_tamu"),
                res.getString("Tgl_checkout"),
                res.getString("Jam_checkout"),
                res.getString("Kode_kamar"),
                res.getString("Lama_inap"),
                res.getString("Harga")
            });
        }
    }
    
    private void kosongkan_input() {
        kodeTamu.setText("");
        namaTamu.setText("");
        usiaTamu.setText("");
        
        kodeKamar.setText("");
        kelasKamar.setText("");
        hargaKamar.setText("");
        fasilitas.setText("");
        jamCheckout.setText("HH/MM/SS");
        lamaInap.setText("");
        biayaTotal.setText("");
        
        kodeTamu.requestFocus();
    }
    
    private void simpan_temporary_checkout() throws SQLException {
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO temporary_checkout"
                + " (Kode_tamu, Nama_tamu, Jns_kel, Usia, Tgl_checkout, Jam_checkout, Kode_kamar, Kelas_kamar, Harga, Lama_inap, fasilitas, Posisi_kamar)"
                + " VALUES ('"+kodeTamu.getText()+"', '"+namaTamu.getText()+"', '"+jenisKelamin.getSelectedItem()+"', '"+usiaTamu.getText()+"',"
                        + " '"+((JTextField)tglCheckout.getDateEditor().getUiComponent()).getText()+"', '"+jamCheckout.getText()+"', '"+kodeKamar.getText()+"', '"+kelasKamar.getText()+"',"
                        + " '"+biayaTotal.getText()+"', '"+lamaInap.getText()+"', '"+fasilitas.getText()+"', '"+posisiKamar.getSelectedItem()+"')");
        tampil_data();
        
        if ( pstm.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Simpan Sukses", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Simpan Gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            pstm.close();
        }
    }
    
    private void ubah_temporary_checkout() throws SQLException {
        conn = (Connection) DriverManager.getConnection(url, user, pass);
        PreparedStatement pstm = conn.prepareStatement("UPDATE temporary_checkout SET "
                + "Kode_tamu = '"+kodeTamu.getText()+"', Nama_tamu = '"+namaTamu.getText()+"', Jns_kel = '"+jenisKelamin.getSelectedItem()+"', Usia = '"+usiaTamu.getText()+"',"
                + "Tgl_checkout = '"+((JTextField)tglCheckout.getDateEditor().getUiComponent()).getText()+"', Jam_checkout = '"+jamCheckout.getText()+"', Kode_kamar = '"+kodeKamar.getText()+"', Kelas_kamar = '"+kelasKamar.getText()+"',"
                + "Harga = '"+biayaTotal.getText()+"', Lama_inap = '"+lamaInap.getText()+"', fasilitas = '"+fasilitas.getText()+"', Posisi_kamar = '"+posisiKamar.getSelectedItem()+"' WHERE Kode_tamu = '"+kodeTamu.getText()+"' AND Kode_kamar = '"+kodeKamar.getText()+"'");
        
        if ( pstm.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Ubah Data Sukses", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
    private void hapus_temporary_checkout() throws SQLException {
        try {
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stm = conn.createStatement();
            boolean hapusTempCheckout = stm.execute("DELETE FROM temporary_checkout");
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

        panelBackground = new javax.swing.JPanel();
        judulCheckin = new javax.swing.JLabel();
        panelTamu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        kodeTamu = new javax.swing.JTextField();
        namaTamu = new javax.swing.JTextField();
        usiaTamu = new javax.swing.JTextField();
        jenisKelamin = new javax.swing.JComboBox<>();
        panelCheckout = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        kodeKamar = new javax.swing.JTextField();
        kelasKamar = new javax.swing.JTextField();
        hargaKamar = new javax.swing.JTextField();
        fasilitas = new javax.swing.JTextField();
        lamaInap = new javax.swing.JTextField();
        biayaTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jamCheckout = new javax.swing.JTextField();
        tglCheckout = new com.toedter.calendar.JDateChooser();
        posisiKamar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tempCheckout = new javax.swing.JTable();
        panelButton = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnInpuit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        panelLaporan = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        kamarAterisi = new javax.swing.JTextField();
        kamarBterisi = new javax.swing.JTextField();
        kamarCterisi = new javax.swing.JTextField();
        kamarAkosong = new javax.swing.JTextField();
        kamarBkosong = new javax.swing.JTextField();
        kamarCkosong = new javax.swing.JTextField();
        subTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBackground.setBackground(new java.awt.Color(255, 51, 255));
        panelBackground.setPreferredSize(new java.awt.Dimension(911, 794));

        judulCheckin.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        judulCheckin.setText("Check Out Hotel Aditya");

        panelTamu.setBackground(new java.awt.Color(255, 255, 255));
        panelTamu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Kode Tamu");
        panelTamu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nama");
        panelTamu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("L/P");
        panelTamu.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Umur");
        panelTamu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, -1, -1));

        kodeTamu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        kodeTamu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodeTamuKeyPressed(evt);
            }
        });
        panelTamu.add(kodeTamu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 170, -1));

        namaTamu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        namaTamu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTamuActionPerformed(evt);
            }
        });
        panelTamu.add(namaTamu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 290, -1));

        usiaTamu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usiaTamu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usiaTamuKeyPressed(evt);
            }
        });
        panelTamu.add(usiaTamu, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 60, -1));

        jenisKelamin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));
        panelTamu.add(jenisKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 50, -1));

        panelCheckout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Kode Kamar");
        panelCheckout.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Kelas Kamar");
        panelCheckout.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Harga");
        panelCheckout.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Fasilitas");
        panelCheckout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tanggal Check-out");
        panelCheckout.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Jam Check-out");
        panelCheckout.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Lama Inap");
        panelCheckout.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Biaya Total");
        panelCheckout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, -1, -1));

        kodeKamar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        kodeKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodeKamarKeyPressed(evt);
            }
        });
        panelCheckout.add(kodeKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        kelasKamar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelCheckout.add(kelasKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 60, -1));

        hargaKamar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelCheckout.add(hargaKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 120, -1));

        fasilitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelCheckout.add(fasilitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 280, -1));
        panelCheckout.add(lamaInap, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 80, -1));

        biayaTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                biayaTotalKeyPressed(evt);
            }
        });
        panelCheckout.add(biayaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 210, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("/ malam");
        panelCheckout.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("malam");
        panelCheckout.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Posisi Kamar");
        panelCheckout.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jamCheckout.setText("HH:MM:SS");
        jamCheckout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamCheckoutKeyPressed(evt);
            }
        });
        panelCheckout.add(jamCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 80, -1));

        tglCheckout.setDateFormatString("yyyy-MM-dd");
        panelCheckout.add(tglCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 210, -1));

        posisiKamar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        posisiKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lantai 1", "Lantai 2", "Lantai 3" }));
        panelCheckout.add(posisiKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        tempCheckout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tempCheckout.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tempCheckout);

        panelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        panelButton.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 90, 40));

        btnInpuit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInpuit.setText("INPUT");
        btnInpuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInpuitActionPerformed(evt);
            }
        });
        panelButton.add(btnInpuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 40));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        panelButton.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 100, 40));

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        panelButton.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 100, 40));

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        panelButton.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 100, 40));

        panelLaporan.setBackground(new java.awt.Color(255, 255, 255));
        panelLaporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Kamar A Terisi");
        panelLaporan.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Kamar B Terisi");
        panelLaporan.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Kamar C Terisi");
        panelLaporan.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Kamar A Kosong");
        panelLaporan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Kamar B Kosong");
        panelLaporan.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Kamar C Kosong");
        panelLaporan.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Sub-Total");
        panelLaporan.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        kamarAterisi.setEditable(false);
        kamarAterisi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(kamarAterisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 40, -1));

        kamarBterisi.setEditable(false);
        kamarBterisi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(kamarBterisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 40, -1));

        kamarCterisi.setEditable(false);
        kamarCterisi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(kamarCterisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 40, -1));

        kamarAkosong.setEditable(false);
        kamarAkosong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(kamarAkosong, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 40, -1));

        kamarBkosong.setEditable(false);
        kamarBkosong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(kamarBkosong, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 40, -1));

        kamarCkosong.setEditable(false);
        kamarCkosong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(kamarCkosong, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 40, -1));

        subTotal.setEditable(false);
        subTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelLaporan.add(subTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 250, -1));

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(judulCheckin)
                .addGap(225, 225, 225))
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCheckout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addComponent(panelLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTamu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulCheckin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTamu, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCheckout, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jamCheckoutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamCheckoutKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            biayaTotal.requestFocus();
        }
    }//GEN-LAST:event_jamCheckoutKeyPressed

    private void btnInpuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInpuitActionPerformed
        // TODO add your handling code here:
        input_tamu(true);
        
        kodeTamu.requestFocus();
    }//GEN-LAST:event_btnInpuitActionPerformed

    private void kodeTamuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeTamuKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if( kodeTamu.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Error: Kode Tamu Tidak Boleh Kosong !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
            input_checkout(false);
            
            } else {
                try {
                    conn = (Connection) DriverManager.getConnection(url, user, pass);
                    stm = conn.createStatement();
                    ResultSet res = stm .executeQuery("SELECT * FROM tamu WHERE Kode_tamu = '"+kodeTamu.getText()+"' ");
                    if (res.next()) {
                        namaTamu.setText(res.getString("Nama_tamu"));
                        jenisKelamin.setSelectedItem(res.getString("Jns_kel"));
                        usiaTamu.setText(res.getString("Usia"));
                        
                        usiaTamu.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Data tamu tidak ditemukan di dalam Database !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        }
    }//GEN-LAST:event_kodeTamuKeyPressed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int reply =JOptionPane.showConfirmDialog(
                null, 
                "Apakah Anda yakin ingin keluar aplikasi ?", 
                "Konfirmasi Keluar Aplikasi", 
                JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void namaTamuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTamuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTamuActionPerformed

    private void usiaTamuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usiaTamuKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if( kodeTamu.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Error: Kode Tamu Tidak Boleh Kosong !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
            input_checkout(false);
            
            } else {
                try {
                    conn = (Connection) DriverManager.getConnection(url, user, pass);
                    stm = conn.createStatement();
                    ResultSet res = stm .executeQuery("SELECT * FROM cek_in WHERE Kode_tamu = '"+kodeTamu.getText()+"' ");
                    if (res.next()) {
                        kodeKamar.setText(res.getString("Kode_kamar"));
                        posisiKamar.setSelectedItem(res.getString("Kode_kamar"));
                        lamaInap.setText(res.getString("Lama_inap"));
                        biayaTotal.setText(res.getString("Harga"));
                        

                        input_checkout(true);
                        kodeKamar.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Data Check-in tamu tidak ditemukan di dalam Database !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        }
    }//GEN-LAST:event_usiaTamuKeyPressed

    private void kodeKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKamarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if( kodeKamar.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Error: Kode Kamar Tidak Boleh Kosong !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);    
            } else {
                try {
                    conn = (Connection) DriverManager.getConnection(url, user, pass);
                    stm = conn.createStatement();
                    ResultSet res = stm .executeQuery("SELECT * FROM kamar_terisi WHERE Kode_kamar = '"+kodeKamar.getText()+"' ");
                    if (res.next()) {
                        kelasKamar.setText(res.getString("Kelas"));
                        posisiKamar.setSelectedItem(res.getString("Posisi_kamar"));
                        hargaKamar.setText(res.getString("Harga"));
                        fasilitas.setText(res.getString("Fasilitas"));
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Kamar tidak ditemukan atau telah dibooking !", 
                    "Error !", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        }
    }//GEN-LAST:event_kodeKamarKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            ResultSet res = stm .executeQuery("SELECT * FROM temporary_checkout");
            
            while (res.next()) {
                PreparedStatement pstm = conn.prepareStatement("INSERT INTO cek_out"
                + " (Kode_tamu, Tgl_checkout, Jam_checkout, Kode_kamar, Lama_inap)"
                + " VALUES ('"+res.getString("kode_tamu")+"', '"+res.getString("Tgl_checkout")+"', '"+res.getString("Jam_checkout")+"', '"+res.getString("Kode_kamar")+"', '"+res.getString("Lama_inap")+"')");
                
                if ( !(pstm.executeUpdate() > 0) ) {
                    JOptionPane.showMessageDialog(this, "Simpan ke Table Cek in Gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    pstm.close();
                }
                
                stm = conn.createStatement();
                boolean tambahKamarKosong = stm.execute("INSERT INTO kamar SELECT * FROM kamar_terisi WHERE Kode_kamar = '"+res.getString("Kode_kamar")+"'");
                boolean hapusKamarTerisi = stm.execute("DELETE FROM kamar_terisi WHERE Kode_kamar = '"+res.getString("Kode_kamar")+"'");  
                boolean hapusCheckin = stm.execute("DELETE FROM cek_in WHERE Kode_tamu = '"+res.getString("Kode_tamu")+"' AND Kode_kamar = '"+res.getString("Kode_kamar")+"'");  
            }
            
            sub_total = 0;

            hapus_temporary_checkout();
            tampil_data();
            kosongkan_input();
            input_checkout(false);
            updateDataLaporan();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void biayaTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_biayaTotalKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                sub_total += Integer.parseInt(this.biayaTotal.getText());
                subTotal.setText("" + sub_total);
                
                kelas = kelasKamar.getText();
                switch (kelas) {
                    case "A":
                        if(kamarA_terisi == 0) {
                            JOptionPane.showMessageDialog(this, "Tidak ada Kamar A yang masih dibooking", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kamarA_terisi -= 1;
                            kamarA_kosong += 1;
                            setTextLaporan();
                        }
                        
                        break;
                    case "B":
                        if(kamarB_terisi == 0) {
                            JOptionPane.showMessageDialog(this, "Tidak ada Kamar B yang masih dibooking", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kamarB_terisi -= 1;
                            kamarB_kosong += 1;
                            setTextLaporan();
                        }
                        break;
                    case "C":
                        if(kamarC_terisi == 0) {
                            JOptionPane.showMessageDialog(this, "Tidak ada Kamar C yang masih dibooking", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kamarC_terisi -= 1;
                            kamarC_kosong += 1;
                            setTextLaporan();
                        }
                        break;
                    default:
                        break;
                }
                
                simpan_temporary_checkout();
                kosongkan_input();
                tampil_data();
                kodeTamu.requestFocus();
                
                input_checkout(false);
            } catch (SQLException ex) {
                Logger.getLogger(Check_in.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_biayaTotalKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            if( kodeTamu.getText().isEmpty() || kodeKamar.getText().isEmpty() || kelasKamar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Kode Tamu dan Kode Kamar tidak boleh kosong !", 
                        "Error !", JOptionPane.WARNING_MESSAGE);
            } else {
                conn = (Connection) DriverManager.getConnection(url, user, pass);
                stm = conn.createStatement();
                PreparedStatement pstm = conn.prepareStatement("DELETE FROM temporary_checkout WHERE Kode_tamu = '"+kodeTamu.getText()+"' AND Kode_kamar = '"+kodeKamar.getText()+"'");
        
        if ( pstm.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        
        kelas = kelasKamar.getText();
                switch (kelas) {
                    case "A":
                        if(kamarA_kosong == 0) {
                            JOptionPane.showMessageDialog(this, "Tidak ada Kamar A yang masih kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kamarA_terisi += 1;
                            kamarA_kosong -= 1;
                            setTextLaporan();
                        }
                        
                        break;
                    case "B":
                        if(kamarB_kosong == 0) {
                            JOptionPane.showMessageDialog(this, "Tidak ada Kamar B yang masih kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kamarB_terisi += 1;
                            kamarB_kosong -= 1;
                            setTextLaporan();
                        }
                        break;
                    case "C":
                        if(kamarC_kosong == 0) {
                            JOptionPane.showMessageDialog(this, "Tidak ada Kamar C yang masih kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kamarC_terisi += 1;
                            kamarC_kosong -= 1;
                            setTextLaporan();
                        }
                        break;
                    default:
                        break;
                }
        tampil_data();
        kosongkan_input();
        input_checkout(false);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Kesalahan " + err);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            if( kodeTamu.getText().isEmpty() || kodeKamar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Kode Tamu dan Kode Kamar tidak boleh kosong !", 
                        "Error !", JOptionPane.WARNING_MESSAGE);
            }
            ubah_temporary_checkout();
            tampil_data();
            kosongkan_input();
            input_checkout(false);
            
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Kesalahan " + err);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(Check_out.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Check_out.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Check_out.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Check_out.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Check_out().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Check_out.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField biayaTotal;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInpuit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField fasilitas;
    private javax.swing.JTextField hargaKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jamCheckout;
    private javax.swing.JComboBox<String> jenisKelamin;
    private javax.swing.JLabel judulCheckin;
    private javax.swing.JTextField kamarAkosong;
    private javax.swing.JTextField kamarAterisi;
    private javax.swing.JTextField kamarBkosong;
    private javax.swing.JTextField kamarBterisi;
    private javax.swing.JTextField kamarCkosong;
    private javax.swing.JTextField kamarCterisi;
    private javax.swing.JTextField kelasKamar;
    private javax.swing.JTextField kodeKamar;
    private javax.swing.JTextField kodeTamu;
    private javax.swing.JTextField lamaInap;
    private javax.swing.JTextField namaTamu;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelCheckout;
    private javax.swing.JPanel panelLaporan;
    private javax.swing.JPanel panelTamu;
    private javax.swing.JComboBox<String> posisiKamar;
    private javax.swing.JTextField subTotal;
    private javax.swing.JTable tempCheckout;
    private com.toedter.calendar.JDateChooser tglCheckout;
    private javax.swing.JTextField usiaTamu;
    // End of variables declaration//GEN-END:variables
}
