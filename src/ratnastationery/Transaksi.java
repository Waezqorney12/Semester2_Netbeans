/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratnastationery;

import ConfigDB.Config;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import sun.security.krb5.internal.Krb5;

/**
 *
 * @author ASUS
 */
public class Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Transaksi
     */
    public void DataBarang() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM barang");

            while (rs.next()) {
                String Kode = rs.getString("Kode_Barang");
                String Nama = rs.getString("Nama");
                String Harga = rs.getString("Harga_Barang");
                String Stok = rs.getString("Stok");

                String asep[] = {Kode, Nama, Harga, Stok};

                DefaultTableModel anjayani = (DefaultTableModel) DataBarangTransaksi.getModel();

                anjayani.addRow(asep);
            }

        } catch (Exception e) {
            System.out.println("");
        }

    }

    public void combobox() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("select Id_Kasir from kasir");

            while (rs.next()) {
                String name = rs.getString("Id_kasir");
                /**
                 * IDKasir.addItem(name);
                 *
                 */
            }
            connection.close();
        } catch (Exception e) {
        }
    }

    public void searchengine() {

        try {
            String cari = txt_cari.getText();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM barang WHERE  barcode_id LIKE '%" + cari +  "%'");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Kode Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Harga Barang");
            tbl.addColumn("Stok");

            DataBarangTransaksi.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Kode_Barang"),
                    rs.getString("Nama"),
                    rs.getString("Harga_Barang"),
                    rs.getString("Stok"),
                    rs.getString("barcode_id")

                });
                DataBarangTransaksi.setModel(tbl);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void tanggal() {
        Calendar kal = new GregorianCalendar();
        int tahun = kal.get(Calendar.YEAR);
        int bulan = kal.get(Calendar.MONTH) + 1;
        int tgl = kal.get(Calendar.DAY_OF_MONTH);
        String tanggals = tahun + "-" + bulan + "-" + tgl;

        tanggal.setText(tanggals);

    }

    public Transaksi() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        DataBarang();
        combobox();
        tanggal();
        

        IDKasir();

        harga();
        auto();
       
    }

    /**
     * public void auto(){ try { Connection connection =
     * (Connection)Config.configDB(); Statement stm =
     * connection.createStatement(); String sql = "SELECT
     * MAX(RIGHT(Kode_Transaksi,4)) as no from transaksi"; ResultSet rs =
     * stm.executeQuery(sql);
     *
     * if (rs.next()) { String no,no1_plus; int p; no =
     * Integer.toString(rs.getInt(1) + 1); p = no.length(); no1_plus = ""; for
     * (int i = 1; i < 4-p; i++) { no1_plus = no1_plus + "0"; }
     * KodeTransaksi.setText("ID" +no1_plus + no); }
     *
     * } catch (Exception e) { } }
     */
    
    public void auto(){
        try {
            String sql = "SELECT MAX(Kode_Transaksi) AS kode FROM transaksi";
            
            Connection conn = (Connection)Config.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                Object[] obj = new Object[1];
                obj[0] = rs.getString("kode");
                if (obj[0] == null) {
                    obj[0] = "K0000";
                    
                }
                String str_kd = (String) obj[0];
                String kd = str_kd.substring(1,5);
                int int_code = Integer.parseInt(kd);
                int_code++;
                
                String a = String.format("%04d", int_code);
                String b = "K" + a;
                KodeTransaksi.setText(b);
                KodeTransaksi.setEditable(false);
            }
            
        } catch (Exception e) {
        }
    }
    public void IDKasir() {
        try {
            Connection connection = (Connection) Config.configDB();

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("select Id_Kasir from kasir");

            while (rs.next()) {
                String name = rs.getString("Id_Kasir");
                id_kasir.addItem(name);
            }
            connection.close();
        } catch (Exception e) {
        }
    }

    public void harga() {
        Integer sum = 0;

        for (int i = 0; i < Pesanan.getRowCount(); i++) {
            sum = sum + Integer.parseInt(Pesanan.getValueAt(i, 4).toString());
        }

        Total_Harga.setText(Integer.toString(sum));
        Integer cTotal1 = (Integer.parseInt(Total_Harga.getText()));

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
        jPanel3 = new javax.swing.JPanel();
        roundPanel1 = new ratnastationery.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        roundPanel3 = new ratnastationery.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Pesanan = new javax.swing.JTable();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel4 = new ratnastationery.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        Cari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataBarangTransaksi = new javax.swing.JTable();
        btn_CheckOut = new ratnastationery.Asep2();
        btn_CheckOut1 = new ratnastationery.Asep2();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        id_kasir = new javax.swing.JComboBox<>();
        tanggal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        KodeTransaksi = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        Total_Harga = new javax.swing.JTextField();
        Harga = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Qty = new javax.swing.JTextField();
        Stok = new javax.swing.JLabel();
        Date = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        KodeBarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Kembalian = new javax.swing.JTextField();
        Tunai = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JumlahStok = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Nama_Barang = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(243, 240, 251));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(243, 240, 251));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setForeground(new java.awt.Color(102, 31, 176));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DATA BARANG");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 610, 50));

        roundPanel3.setForeground(new java.awt.Color(255, 255, 255));

        Pesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksi", "Kode Barang", "Nama Barang", "Jumlah", "Harga"
            }
        ));
        jScrollPane1.setViewportView(Pesanan);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(roundPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 580, 320));

        kGradientPanel1.setkEndColor(new java.awt.Color(51, 0, 51));
        kGradientPanel1.setkStartColor(new java.awt.Color(102, 31, 176));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_go_back_30px_1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("STXihei", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ratna Stationery");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Selamat Melayani");

        Asep.setFont(new java.awt.Font("STXihei", 1, 16)); // NOI18N
        Asep.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Asep, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(55, 55, 55))))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Asep, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, kGradientPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 70));

        roundPanel4.setForeground(new java.awt.Color(51, 0, 51));
        roundPanel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DATA PESANAN");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap(230, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 580, 50));

        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        jPanel3.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 300, 30));

        Cari.setText("Cari");
        Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariActionPerformed(evt);
            }
        });
        jPanel3.add(Cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 100, 30));

        jScrollPane2.setAlignmentX(5.0F);
        jScrollPane2.setAlignmentY(5.0F);
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        DataBarangTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Barang", "Stok"
            }
        ));
        DataBarangTransaksi.setAlignmentX(5.0F);
        DataBarangTransaksi.setAlignmentY(5.0F);
        DataBarangTransaksi.setFocusable(false);
        DataBarangTransaksi.setGridColor(new java.awt.Color(0, 0, 255));
        DataBarangTransaksi.setIntercellSpacing(new java.awt.Dimension(0, 0));
        DataBarangTransaksi.setRequestFocusEnabled(false);
        DataBarangTransaksi.setRowHeight(25);
        DataBarangTransaksi.setShowHorizontalLines(false);
        DataBarangTransaksi.setShowVerticalLines(false);
        DataBarangTransaksi.getTableHeader().setReorderingAllowed(false);
        DataBarangTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataBarangTransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataBarangTransaksi);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 610, 320));

        btn_CheckOut.setText("Delete");
        btn_CheckOut.setBorderColor(new java.awt.Color(255, 0, 51));
        btn_CheckOut.setColor(new java.awt.Color(255, 0, 102));
        btn_CheckOut.setColorClick(new java.awt.Color(255, 0, 102));
        btn_CheckOut.setColorOver(new java.awt.Color(204, 0, 51));
        btn_CheckOut.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_CheckOut.setRadius(35);
        btn_CheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CheckOutActionPerformed(evt);
            }
        });
        jPanel3.add(btn_CheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 730, 130, 40));

        btn_CheckOut1.setForeground(new java.awt.Color(255, 255, 255));
        btn_CheckOut1.setText("Check Out");
        btn_CheckOut1.setBorderColor(new java.awt.Color(102, 102, 255));
        btn_CheckOut1.setColor(new java.awt.Color(102, 102, 255));
        btn_CheckOut1.setColorClick(new java.awt.Color(102, 102, 255));
        btn_CheckOut1.setColorOver(new java.awt.Color(51, 0, 153));
        btn_CheckOut1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_CheckOut1.setRadius(35);
        btn_CheckOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CheckOut1ActionPerformed(evt);
            }
        });
        jPanel3.add(btn_CheckOut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 730, 130, 40));

        jLabel8.setText("Tanggal");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("ID Kasir");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 90, -1, -1));

        jPanel3.add(id_kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 90, 160, -1));
        jPanel3.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 80, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Kode Transaksi");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, -1));
        jPanel3.add(KodeTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, 140, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, null));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Total ");

        Total_Harga.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Total_Harga.setBorder(null);
        Total_Harga.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        Total_Harga.setSelectedTextColor(new java.awt.Color(240, 240, 240));
        Total_Harga.setSelectionColor(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Total_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Total_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 550, 580, 100));
        jPanel3.add(Harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, 120, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Tanggal ");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, -1, 40));

        Qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QtyActionPerformed(evt);
            }
        });
        jPanel3.add(Qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, 120, -1));

        Stok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Stok.setText("Stok");
        jPanel3.add(Stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, -1, 40));
        jPanel3.add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 140, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Tunai");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, -1, 40));

        KodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KodeBarangActionPerformed(evt);
            }
        });
        jPanel3.add(KodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 140, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Kode Barang");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 570, -1, -1));
        jPanel3.add(Kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 610, 160, -1));

        Tunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TunaiActionPerformed(evt);
            }
        });
        jPanel3.add(Tunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, 160, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Kembali");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 600, -1, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Harga");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, -1, 40));

        JumlahStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahStokActionPerformed(evt);
            }
        });
        jPanel3.add(JumlahStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 130, 160, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Qty");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nama");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 610, -1, -1));
        jPanel3.add(Nama_Barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 610, 140, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1310, 800));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Dashboard().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariActionPerformed
        // TODO add your handling code here:
        searchengine();
       
    }//GEN-LAST:event_CariActionPerformed

    private void btn_CheckOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CheckOut1ActionPerformed
        // TODO add your handling code here:
        java.sql.Connection konek = null;
        DefaultTableModel pesan = (DefaultTableModel) Pesanan.getModel();
        String tampil = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampil);
        String tanggal = String.valueOf(fm.format(this.Date.getDate()));

        String Kode_Transaksi, Kode_Barang, Jumlah_Barang, harga, Nama_Barang;
        try {

            String sql = "INSERT INTO transaksi VALUES ('" + KodeTransaksi.getText() 
                    + "','" + tanggal + "','" + Total_Harga.getText() + "','" + Tunai.getText() + "','" + Kembalian.getText() + "','" + id_kasir.getSelectedItem() + "')";

            Connection conn = (Connection) Config.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.execute();

            for (int i = 0; i < Pesanan.getRowCount(); i++) {
                Kode_Transaksi = Pesanan.getValueAt(i, 0).toString();
                Kode_Barang = Pesanan.getValueAt(i, 1).toString();
                Nama_Barang = Pesanan.getValueAt(i, 2).toString();
                Jumlah_Barang = Pesanan.getValueAt(i, 3).toString();
                harga = Pesanan.getValueAt(i, 4).toString();

                String sqll = "INSERT INTO detail_transaksi(Kode_Transaksi,Kode_Barang,Nama_Barang,Jumlah_Barang,Sub_Total) VALUES  ('" + Kode_Transaksi + "','"
                        + Kode_Barang + "','" + Nama_Barang + "','" + Jumlah_Barang + "','" + harga + "')";
                PreparedStatement pstl = conn.prepareStatement(sqll);
                pstl.execute();

            }
            JOptionPane.showMessageDialog(null, "Data Tersimpan");

            pesan.setRowCount(0);

            this.setVisible(false);
            new Dashboard().setVisible(true);
            try {
                String jdbcDrive = "com.mysql.cj.jdbc.Driver";
                Class.forName(jdbcDrive);

                String url = "jdbc:mysql://localhost:3306/semester2_project";
                String user = "root";
                String password = "";

                konek = DriverManager.getConnection(url, user, password);
                Statement stm = konek.createStatement();

                String report = ("D:\\TugasGithub\\FileWaez\\GitHub\\Tugas\\RatnaStationery\\src\\ratnastationery\\Report\\Struk.jrxml");
                HashMap hash = new HashMap();

                hash.put("Kode", KodeTransaksi.getText());
                JasperReport JRpt = JasperCompileManager.compileReport(report);
                JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, conn);
                JasperViewer.viewReport(JPrint, false);
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btn_CheckOut1ActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void DataBarangTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataBarangTransaksiMouseClicked
        // TODO add your handling code here:

        int number = DataBarangTransaksi.getSelectedRow();

        KodeBarang.setText(DataBarangTransaksi.getValueAt(number, 0).toString());
        Harga.setText(DataBarangTransaksi.getValueAt(number, 2).toString());
        Nama_Barang.setText(DataBarangTransaksi.getValueAt(number, 1).toString());
        JumlahStok.setText(DataBarangTransaksi.getValueAt(number, 3).toString());

    }//GEN-LAST:event_DataBarangTransaksiMouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_cariKeyReleased

    private void btn_CheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CheckOutActionPerformed
        // TODO add your handling code here:

        DefaultTableModel delete = (DefaultTableModel) Pesanan.getModel();

        int remove = Pesanan.getSelectedRow();
        if (remove >= 0) {
            delete.removeRow(remove);
            Total_Harga.setText("");
            harga();
        } else {
        }
    }//GEN-LAST:event_btn_CheckOutActionPerformed

    private void QtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QtyActionPerformed
        // TODO add your handling code here:
       
        
       
        DefaultTableModel asep = (DefaultTableModel) Pesanan.getModel();
        DefaultTableModel asep1 = (DefaultTableModel) DataBarangTransaksi.getModel();

        int stok, jumlahbeli, totalbayar, harga;
        jumlahbeli = Integer.parseInt(Qty.getText());
        harga = Integer.parseInt(Harga.getText());
        stok = Integer.parseInt(JumlahStok.getText());
       
        totalbayar = (jumlahbeli * harga);
        Harga.setText("" + totalbayar);

        if (jumlahbeli > stok) {
            JOptionPane.showMessageDialog(this, "Stok melibihi batas");
            KodeBarang.setText("");
            Qty.setText("");
            Harga.setText("");
            JumlahStok.setText("");
            Nama_Barang.setText("");
            
        } else {
            String data[] = {KodeTransaksi.getText(), KodeBarang.getText(),Nama_Barang.getText(), Qty.getText(), Harga.getText()};
            asep.addRow(data);

            KodeBarang.setText("");
            Qty.setText("");
            Harga.setText("");
            JumlahStok.setText("");
            Nama_Barang.setText("");
        }

        harga();
    }//GEN-LAST:event_QtyActionPerformed

    private void KodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeBarangActionPerformed

    private void TunaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TunaiActionPerformed
        // TODO add your handling code here:
        int pembayaran, kembalian, total;

        pembayaran = Integer.parseInt(Tunai.getText());
        kembalian = Integer.parseInt(Total_Harga.getText());

        total = pembayaran - kembalian;

        Kembalian.setText(Integer.toString(total));
        
        if (pembayaran < kembalian) {
            Tunai.setText("");
            Kembalian.setText("");
           JOptionPane.showMessageDialog(this, "uang kurang,periksa kembali jumlah uang");
        }
    }//GEN-LAST:event_TunaiActionPerformed

    private void JumlahStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahStokActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JLabel Asep = new javax.swing.JLabel();
    private javax.swing.JButton Cari;
    private javax.swing.JTable DataBarangTransaksi;
    private com.toedter.calendar.JDateChooser Date;
    private javax.swing.JTextField Harga;
    private javax.swing.JTextField JumlahStok;
    private javax.swing.JTextField Kembalian;
    private javax.swing.JTextField KodeBarang;
    private javax.swing.JTextField KodeTransaksi;
    private javax.swing.JTextField Nama_Barang;
    private javax.swing.JTable Pesanan;
    private javax.swing.JTextField Qty;
    private javax.swing.JLabel Stok;
    private javax.swing.JTextField Total_Harga;
    private javax.swing.JTextField Tunai;
    private ratnastationery.Asep2 btn_CheckOut;
    private ratnastationery.Asep2 btn_CheckOut1;
    private javax.swing.JComboBox<String> id_kasir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private ratnastationery.RoundPanel roundPanel1;
    private ratnastationery.RoundPanel roundPanel3;
    private ratnastationery.RoundPanel roundPanel4;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
