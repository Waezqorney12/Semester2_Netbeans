/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratnastationery;

import java.awt.geom.RoundRectangle2D;
import javax.swing.JOptionPane;
import ConfigDB.Config;
import com.mysql.cj.jdbc.Driver;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import static ratnastationery.Login1.Password;
import static ratnastationery.Login1.UsernameOwner;

/**
 *
 * @author ASUS
 */
public final class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public void tabelBarang() {
        DataBarang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)
        );
        DataBarang.getTableHeader().setOpaque(false);
        DataBarang.getTableHeader().setBackground(new Color(240, 240, 240));
        DataBarang.getTableHeader().setForeground(new Color(0, 0, 0));
        DataBarang.setRowHeight(25);
    }

    public void tabelUser() {
        DataUser.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)
        );
        DataUser.getTableHeader().setOpaque(false);
        DataUser.getTableHeader().setBackground(new Color(240, 240, 240));
        DataUser.getTableHeader().setForeground(new Color(0, 0, 0));
        DataUser.setRowHeight(25);
    }

    public void transaksi() {
        Transaction.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)
        );
        Transaction.getTableHeader().setOpaque(false);
        Transaction.getTableHeader().setBackground(new Color(240, 240, 240));
        Transaction.getTableHeader().setForeground(new Color(0, 0, 0));
        Transaction.setRowHeight(25);
    }

    public void recentTransaksi() {

        RecentTransaction.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)
        );
        RecentTransaction.getTableHeader().setOpaque(false);
        RecentTransaction.getTableHeader().setBackground(new Color(230, 230, 230));
        RecentTransaction.getTableHeader().setForeground(new Color(0, 0, 0));
        RecentTransaction.setRowHeight(25);

    }

    /**
     * public void detail(){ try { Class.forName("com.mysql.cj.jdbc.Driver");
     * Connection connection =
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false",
     * "root", "");
     *
     * Statement stm = connection.createStatement();
     *
     * ResultSet rs = stm.executeQuery("select * from detail_transaksi");
     *
     * while (rs.next()){ String Kode = rs.getString("Kode_Transaksi"); String
     * Barang = rs.getString("Kode_Barang");
     *
     * String tbData[] = {Kode,Barang};
     *
     * DefaultTableModel hayuk =
     * (DefaultTableModel)DetailTransaction.getModel();
     *
     * hayuk.addRow(tbData); }
     *
     * } catch (Exception e) { System.out.println(""); } }
     */
    public void TransaksiHistory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("select * from transaksi");

            while (rs.next()) {
                String kode = rs.getString("Kode_Transaksi");

                String tanggal = rs.getString("Tanggal_Transaksi");
                String total = rs.getString("Total_Harga");
                String tunai = rs.getString("Tunai");
                String kembalian = rs.getString("Kembalian");
                String kasir = rs.getString("Id_Kasir");

                String tbData[] = {kode, tanggal, total, tunai, kembalian, kasir};

                DefaultTableModel tblModel = (DefaultTableModel) Transaction.getModel();

                tblModel.addRow(tbData);
            }
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void historidata() {
        try {

            Connection connection = (Connection) Config.configDB();

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM transaksi");

            while (rs.next()) {
                String kode = rs.getString("Kode_Transaksi");

                String tanggal = rs.getString("Tanggal_Transaksi");
                String total = rs.getString("Total_Harga");
                String kasir = rs.getString("Id_Kasir");

                String tbData[] = {kode, tanggal, total, kasir};

                DefaultTableModel tblModel = (DefaultTableModel) RecentTransaction.getModel();

                tblModel.addRow(tbData);
            }
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void dataUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("select * from kasir");

            while (rs.next()) {
                String kode = rs.getString("Id_Kasir");
                String nama = rs.getString("Nama");
                String tanggal = rs.getString("Alamat");
                String jumlah = rs.getString("Email");
                String total = rs.getString("Password");

                String tbData[] = {kode, nama, tanggal, jumlah, total};

                DefaultTableModel ANJAY = (DefaultTableModel) DataUser.getModel();

                ANJAY.addRow(tbData);
            }
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void dataBarang() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("select * from barang");

            while (rs.next()) {
                String kode_barang = rs.getString("Kode_Barang");
                String nama_barang = rs.getString("Nama");
                String harga = rs.getString("Harga_Barang");
                String stok = rs.getString("Stok");

                String tbData[] = {kode_barang, nama_barang, harga, stok};

                DefaultTableModel ANJAS = (DefaultTableModel) DataBarang.getModel();

                ANJAS.addRow(tbData);
            }
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public Dashboard() {
        initComponents();
        historidata();
        autosum();
        autotransaction();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        dataUser();
        dataBarang();

        Laporan();

        tabelBarang();
        tabelUser();
        transaksi();
        recentTransaksi();
        TransaksiHistory();

        DeleteEngine();
        search();

        pemasukan();
        laporanSearch();
        detailtransaksi();

        MinMax();
       tanggalSearch();

//search1();
    }

//    public void tanggalSearch1() {
//        
//            try {
//                String tampil = "yyyy-MM-dd";
//                SimpleDateFormat fm = new SimpleDateFormat(tampil);
//                String tanggalTransaksi1 = String.valueOf(fm.format(this.Tanggal1.getDate()));
//                String tampils = "yyyy-MM-dd";
//                SimpleDateFormat fs = new SimpleDateFormat(tampils);
//                String tanggalTransaksi2 = String.valueOf(fs.format(this.Tanggal2.getDate()));
//
//                Connection conn = (Connection) Config.configDB();
//
//                Statement stm = conn.createStatement();
//                ResultSet rs = stm.executeQuery("SELECT detail_transaksi.Kode_Transaksi,detail_transaksi.Kode_Barang,detail_transaksi.Nama_Barang,detail_transaksi.Jumlah_Barang,detail_transaksi.Sub_Total, transaksi.Tanggal_Transaksi\n"
//                        + "FROM detail_transaksi\n"
//                        + "LEFT JOIN transaksi ON detail_transaksi.Kode_Transaksi = transaksi.Kode_Transaksi\n"
//                        + "WHERE transaksi.Tanggal_Transaksi BETWEEN '2022-06-12' AND '2022-06-17'");
//
//                DefaultTableModel tbl = new DefaultTableModel();
//                tbl.addColumn("Kode Transaksi");
//                tbl.addColumn("Nama Barang");
//                tbl.addColumn("Jumlah Terjual");
//
//                tbl.addColumn("Sub Total");
//
//                DetailTransaksi.setModel(tbl);
//                while (rs.next()) {
//                    tbl.addRow(new Object[]{
//                        rs.getString("Kode_Transaksi"),
//                        rs.getString("Kode_Barang"),
//                        rs.getString("Nama_Barang"),
//                        rs.getString("Jumlah_Barang"),
//                        rs.getString("Sub_Total"),});
//                    DetailTransaksi.setModel(tbl);
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e);
//            }
//       
//       
//    }
    public void tanggalSearch() {
        
            try {
                String tampil = "yyyy-MM-dd";
                SimpleDateFormat fm = new SimpleDateFormat(tampil);
                String tanggalTransaksi1 = String.valueOf(fm.format(this.Tanggal1.getDate()));
                String tampils = "yyyy-MM-dd";
                SimpleDateFormat fs = new SimpleDateFormat(tampils);
                String tanggalTransaksi2 = String.valueOf(fs.format(this.Tanggal2.getDate()));

                Connection conn = (Connection) Config.configDB();

                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT detail_transaksi.Kode_Transaksi,detail_transaksi.Kode_Barang,detail_transaksi.Nama_Barang,detail_transaksi.Jumlah_Barang,detail_transaksi.Sub_Total, transaksi.Tanggal_Transaksi\n"
                        + "FROM detail_transaksi\n"
                        + "LEFT JOIN transaksi ON detail_transaksi.Kode_Transaksi = transaksi.Kode_Transaksi\n"
                        + "WHERE transaksi.Tanggal_Transaksi BETWEEN '" + tanggalTransaksi1 + "' AND '" + tanggalTransaksi2 + "'");

                DefaultTableModel tbl = new DefaultTableModel();
                tbl.addColumn("Kode Transaksi");
                tbl.addColumn("Nama Barang");
                tbl.addColumn("Jumlah Terjual");

                tbl.addColumn("Sub Total");

                DetailTransaksi.setModel(tbl);
                while (rs.next()) {
                    tbl.addRow(new Object[]{
                        rs.getString("Kode_Transaksi"),
                        rs.getString("Kode_Barang"),
                        rs.getString("Nama_Barang"),
                        rs.getString("Jumlah_Barang"),
                        rs.getString("Sub_Total"),});
                    DetailTransaksi.setModel(tbl);
                }

            } catch (Exception e) {
                
            }
       
       
    }

    public void MinMax() {
        try {

            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < DetailTransaksi.getRowCount(); i++) {
                list.add(Integer.parseInt(DetailTransaksi.getValueAt(i, 3).toString()));
            }
            int max = Collections.max(list);
            int min = Collections.min(list);

            Max.setText("Buku Tulis  " + " Sebanyak " + Integer.toString(max));
            Min.setText("Novel Kimi No Nawa " + " Sebanyak " + Integer.toString(min));
        } catch (Exception e) {
        }
    }

    public void detailtransaksi() {
        try {
            String sql = "SELECT * FROM detail_transaksi";
            Connection conn = (Connection) Config.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            DefaultTableModel tbl = new DefaultTableModel();

            tbl.addColumn("Kode Transaksi");
            tbl.addColumn("Kode Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Jumlah Terjual");
            tbl.addColumn("Sub Total");
            DetailTransaksi.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Kode_Transaksi"),
                    rs.getString("Kode_Barang"),
                    rs.getString("Nama_Barang"),
                    rs.getString("Jumlah_Barang"),
                    rs.getString("Sub_Total"),});
                DetailTransaksi.setModel(tbl);
            }

        } catch (Exception e) {
        }
    }

    public void DeleteEngine() {

        String[] data = {"Kode_Barang", "Nama", "Harga_Barang", "Stok"};

        DefaultTableModel ModelBarang = new DefaultTableModel(null, data);
        DataBarang.setModel(ModelBarang);
        String sql = "SELECT * FROM barang";

        try {

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("Kode_Barang");
                String nama = rs.getString("Nama");
                String Harga = rs.getString("Harga_Barang");
                String Stock = rs.getString("Stok");
                String[] Data = {id, nama, Harga, Stock};
                ModelBarang.addRow(Data);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Normalizer.Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateActionKasir() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM kasir");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Id_Kasir");
            tbl.addColumn("Nama");
            tbl.addColumn("Alamat");
            tbl.addColumn("Email");
            tbl.addColumn("Password");
            DataUser.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Id_Kasir"),
                    rs.getString("Nama"),
                    rs.getString("Alamat"),
                    rs.getString("Email"),
                    rs.getString("Password")
                });
                DataUser.setModel(tbl);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void UpdateActionBarang() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM barang");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Kode Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Harga Barang");
            tbl.addColumn("Stok");
            DataBarang.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Kode_Barang"),
                    rs.getString("Nama"),
                    rs.getString("Harga_Barang"),
                    rs.getString("Stok")
                });
                DataBarang.setModel(tbl);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void Laporan() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery("select * from laporan_keuangan");

            DefaultTableModel tbl_laporan = new DefaultTableModel();
            tbl_laporan.addColumn("Tanggal");
            tbl_laporan.addColumn("Total Pemasukan");
            tbl_laporan.addColumn("Total Pengeluaran");
            tbl_laporan.addColumn("Pendapatan");
            Laporan_Keuangan.setModel(tbl_laporan);
            while (rs.next()) {
                tbl_laporan.addRow(new Object[]{
                    rs.getString("tanggal"),
                    rs.getString("total_Pemasukkan"),
                    rs.getString("total_Pengeluaran"),
                    rs.getString("Pendapatan")
                });
                Laporan_Keuangan.setModel(tbl_laporan);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void search() {
        try {
            String cari = search.getText();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semester2_project?useSSL=false", "root", "");

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM transaksi WHERE Id_Kasir LIKE '%" + cari + "%'");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Kode Transaksi");
            tbl.addColumn("Tanggal Transaksi");
            tbl.addColumn("Total Harga");

            tbl.addColumn("Tunai");
            tbl.addColumn("Kembalian");
            tbl.addColumn("ID Kasir");

            Transaction.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Kode_Transaksi"),
                    rs.getString("Tanggal_Transaksi"),
                    rs.getString("Total_Harga"),
                    rs.getString("Tunai"),
                    rs.getString("Kembalian"),
                    rs.getString("Id_Kasir")

                });
                Transaction.setModel(tbl);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void search1() {
        try {
            String tampil = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampil);
            String tanggalTransaksi = String.valueOf(fm.format(this.Tanggalans2.getDate()));

            Connection conn = (Connection) Config.configDB();

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM transaksi WHERE Tanggal_Transaksi LIKE '%" + tanggalTransaksi + "%'");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Kode Transaksi");
            tbl.addColumn("Tanggal Transaksi");
            tbl.addColumn("Total Harga");

            tbl.addColumn("Tunai");
            tbl.addColumn("Kembalian");
            tbl.addColumn("ID Kasir");

            Transaction.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Kode_Transaksi"),
                    rs.getString("Tanggal_Transaksi"),
                    rs.getString("Total_Harga"),
                    rs.getString("Tunai"),
                    rs.getString("Kembalian"),
                    rs.getString("Id_Kasir")

                });
                Transaction.setModel(tbl);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void laporanSearch() {
        try {
            String tampil = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampil);
            String tanggalLaporan = String.valueOf(fm.format(this.jDateChooser1.getDate()));

            Connection conn = (Connection) Config.configDB();

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM laporan_keuangan WHERE tanggal LIKE '%" + tanggalLaporan + "%'");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Tanggal");
            tbl.addColumn("Total Pemasukan");
            tbl.addColumn("Total Pengeluaran");
            tbl.addColumn("Pendapatan");

            Laporan_Keuangan.setModel(tbl);
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("tanggal"),
                    rs.getString("total_Pemasukkan"),
                    rs.getString("total_Pengeluaran"),
                    rs.getString("Pendapatan"),});
                Laporan_Keuangan.setModel(tbl);
            }

        } catch (Exception e) {

        }
    }

    public void pemasukan() {
        int numberRow = Laporan_Keuangan.getRowCount();

        int tot = 0;

        for (int i = 0; i < numberRow; i++) {
            int val = Integer.parseInt((String) Laporan_Keuangan.getValueAt(i, 3));
            tot += val;
            if (tot > 500) {
                Pendapatan.setText("" + tot + " Target Terpenuhi");

            } else {
                Pendapatan.setText("" + tot + " Target Tidak Terpenuhi");
            }
        }

    }

    public void pengecek() {

    }

    public void pengecekanStok() {
        int number = DataBarang.getRowCount();

//            int tot = 0;
//            
//            for (int i = 0; i < number; i++) {
//            int val = Integer.parseInt((String) DataBarang.getValueAt(i, 3));
//            tot += val;
//                if (tot == 0) {
//                    txt_stok.setText("Stok Anda Habis silahkan restock 10pcs");
//                }
//                 if (tot == 1) {
//                        txt_stok.setText("Stok kurang 9");
//                 } if (tot == 2) {
//                         txt_stok.setText( "Stok kurang 8");
//                 }if (tot == 3) {
//                        txt_stok.setText( "Stok kurang 7");
//                 }if (tot == 4) {
//                        txt_stok.setText("Stok kurang 6");
//                 }if (tot == 5) {
//                        txt_stok.setText("Stok kurang 5");
//                 }if (tot == 6) {
//                        txt_stok.setText("Stok kurang 4");
//                 }if (tot == 7){
//                        txt_stok.setText( "Stok kurang 3");
//                 }if (tot == 8) {
//                        txt_stok.setText("Stok kurang 2");
//                 }if (tot == 9) {
//                        txt_stok.setText("Stok kurang 1");
//                 }if (tot > 10) {
//                        txt_stok.setText( "Stok terpenuhi");
//                    }
//                 else{
//                     
//                 }
//                 txt_stok.setEditable(false);
//                 
//        }
        try {
            String cariStock = CekStock_txt.getText();
            String sql = "SELECT * FROM barang WHERE  Nama LIKE '%" + cariStock + "%'";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Kode Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Harga Barang");
            tbl.addColumn("Stok");
            DataBarang.setModel(tbl);

            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Kode_Barang"),
                    rs.getString("Nama"),
                    rs.getString("Harga_Barang"),
                    rs.getString("Stok"),
                    rs.getString("barcode_id")

                });
                DataBarang.setModel(tbl);

            }

        } catch (Exception e) {
        }

    }

 //   public void test() {
//     int number = DataBarang.getRowCount();
//            int tot = 0;
//            
//            for (int i = 0; i < number; i++) {
//            int val = Integer.parseInt((String) DataBarang.getValueAt(i, 3));
//            tot += val;
//            
//                 if (tot == 1) {
//                        txt_stok.setText("Stok kurang 9");
//                 } if (tot == 2) {
//                         txt_stok.setText( "Stok kurang 8");
//                 }if (tot == 3) {
//                        txt_stok.setText( "Stok kurang 7");
//                 }if (tot == 4) {
//                        txt_stok.setText("Stok kurang 6");
//                 }if (tot == 5) {
//                        txt_stok.setText("Stok kurang 5");
//                 }if (tot == 6) {
//                        txt_stok.setText("Stok kurang 4");
//                 }if (tot == 7){
//                        txt_stok.setText( "Stok kurang 3");
//                 }if (tot == 8) {
//                        txt_stok.setText("Stok kurang 2");
//                 }if (tot == 9) {
//                        txt_stok.setText("Stok kurang 1");
//                 }if (tot > 10) {
//                        txt_stok.setText( "Stok terpenuhi");
//                    }
//                 else{
//                     
//                 }
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        junaidi1 = new ratnastationery.RoundPanel();
        junaidi2 = new ratnastationery.RoundPanel();
        junaidi3 = new ratnastationery.RoundPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Dashboard = new javax.swing.JButton();
        LaporanKeuangan = new javax.swing.JButton();
        Databarang = new javax.swing.JButton();
        Datauser = new javax.swing.JButton();
        HistroriTransaksi = new javax.swing.JButton();
        PanelDashboard = new javax.swing.JPanel();
        roundPanel2 = new ratnastationery.RoundPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LabelDashboard = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Rp = new javax.swing.JLabel();
        pemasukan = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        OrderLab = new javax.swing.JLabel();
        chart_btn = new javax.swing.JButton();
        labelRecent = new javax.swing.JLabel();
        PanelDataUser = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roundPanel1 = new ratnastationery.RoundPanel();
        jLabel11 = new javax.swing.JLabel();
        asep111 = new ratnastationery.Asep11();
        HapusUser = new ratnastationery.Asep();
        PanelDataBarang = new javax.swing.JPanel();
        roundPanel3 = new ratnastationery.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TambahBarang_Btn = new ratnastationery.Asep11();
        HapusBarang = new ratnastationery.Asep11();
        CekStock_btn = new javax.swing.JButton();
        CekStock_txt = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        roundPanel5 = new ratnastationery.RoundPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        LaporanKeuangan_Btn = new ratnastationery.Asep11();
        asep112 = new ratnastationery.Asep11();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        PanelHistoriTransaksi = new javax.swing.JPanel();
        roundPanel4 = new ratnastationery.RoundPanel();
        LabelDashboard2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jButton3 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        Tanggalans2 = new com.toedter.calendar.JDateChooser();
        pemasukanTransaksi = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PanelDetail = new javax.swing.JPanel();
        roundPanel6 = new ratnastationery.RoundPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Max = new javax.swing.JTextField();
        Min = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Tanggal1 = new com.toedter.calendar.JDateChooser();
        Tanggal2 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();

        jLabel7.setFont(new java.awt.Font("STXihei", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ratna");

        jLabel9.setFont(new java.awt.Font("STXihei", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Stationery");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(102, 31, 176));
        kGradientPanel1.setkStartColor(new java.awt.Color(51, 0, 102));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/smalllogos.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("STXihei", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ratna Stationery");

        Dashboard.setBackground(new java.awt.Color(51, 0, 102));
        Dashboard.setFont(new java.awt.Font("Sitka Small", 1, 12)); // NOI18N
        Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Dashboard.setText("Dashboard");
        Dashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DashboardMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DashboardMouseReleased(evt);
            }
        });
        Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardActionPerformed(evt);
            }
        });

        LaporanKeuangan.setBackground(new java.awt.Color(51, 0, 102));
        LaporanKeuangan.setFont(new java.awt.Font("Sitka Small", 1, 12)); // NOI18N
        LaporanKeuangan.setForeground(new java.awt.Color(255, 255, 255));
        LaporanKeuangan.setText("Laporan Keuangan");
        LaporanKeuangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        LaporanKeuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LaporanKeuanganMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LaporanKeuanganMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LaporanKeuanganMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LaporanKeuanganMouseReleased(evt);
            }
        });
        LaporanKeuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanKeuanganActionPerformed(evt);
            }
        });

        Databarang.setBackground(new java.awt.Color(51, 0, 102));
        Databarang.setFont(new java.awt.Font("Sitka Small", 1, 12)); // NOI18N
        Databarang.setForeground(new java.awt.Color(255, 255, 255));
        Databarang.setText("Data Barang");
        Databarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Databarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DatabarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DatabarangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DatabarangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DatabarangMouseReleased(evt);
            }
        });
        Databarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatabarangActionPerformed(evt);
            }
        });

        Datauser.setBackground(new java.awt.Color(51, 0, 102));
        Datauser.setFont(new java.awt.Font("Sitka Small", 1, 12)); // NOI18N
        Datauser.setForeground(new java.awt.Color(255, 255, 255));
        Datauser.setText("Data User");
        Datauser.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Datauser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DatauserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DatauserMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DatauserMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DatauserMouseReleased(evt);
            }
        });
        Datauser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatauserActionPerformed(evt);
            }
        });

        HistroriTransaksi.setBackground(new java.awt.Color(51, 0, 102));
        HistroriTransaksi.setFont(new java.awt.Font("Sitka Small", 1, 12)); // NOI18N
        HistroriTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        HistroriTransaksi.setText("Histori Transaksi");
        HistroriTransaksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        HistroriTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HistroriTransaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HistroriTransaksiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HistroriTransaksiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                HistroriTransaksiMouseReleased(evt);
            }
        });
        HistroriTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistroriTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Databarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Datauser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LaporanKeuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(HistroriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Databarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Datauser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LaporanKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(HistroriTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );

        jPanel2.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.CardLayout());

        PanelDashboard.setBackground(new java.awt.Color(243, 240, 251));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit_sign_40px (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_info_30px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Anjay.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Anjay.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button (13).png"))); // NOI18N
        jLabel3.setToolTipText("");

        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Selamat Datang");

        LabelDashboard.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        LabelDashboard.setText("Dashboard");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(Anjay, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel2Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(LabelDashboard)
                    .addContainerGap(946, Short.MAX_VALUE)))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Anjay, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(49, 49, 49))
            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel2Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(LabelDashboard)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        RecentTransaction.setBackground(new java.awt.Color(243, 240, 251));
        RecentTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksi", "Tanggal Transaksi", "Total Harga", "ID Kasir"
            }
        ));
        RecentTransaction.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(RecentTransaction);

        jPanel3.setBackground(new java.awt.Color(243, 240, 251));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_bag_50px_1.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        Rp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Rp.setForeground(new java.awt.Color(255, 255, 255));
        Rp.setText("Rp.");
        jPanel3.add(Rp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 40, 20));

        pemasukan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pemasukan.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 210, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TotalPemasukan.png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        OrderLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OrderButton.png"))); // NOI18N
        OrderLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrderLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrderLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OrderLabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrderLabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                OrderLabMouseReleased(evt);
            }
        });

        chart_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Chart.png"))); // NOI18N
        chart_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chart_btnActionPerformed(evt);
            }
        });

        labelRecent.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        labelRecent.setText("Recent Transaction");

        javax.swing.GroupLayout PanelDashboardLayout = new javax.swing.GroupLayout(PanelDashboard);
        PanelDashboard.setLayout(PanelDashboardLayout);
        PanelDashboardLayout.setHorizontalGroup(
            PanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDashboardLayout.createSequentialGroup()
                .addGroup(PanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDashboardLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelDashboardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(PanelDashboardLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRecent)
                            .addGroup(PanelDashboardLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OrderLab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chart_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelDashboardLayout.setVerticalGroup(
            PanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chart_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OrderLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addComponent(labelRecent, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1436, Short.MAX_VALUE))
        );

        MainPanel.add(PanelDashboard, "card7");

        PanelDataUser.setBackground(new java.awt.Color(255, 255, 255));

        DataUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Kasir", "Nama Pegawai", "Alamat", "Email", "Password"
            }
        ));
        DataUser.setRowHeight(25);
        DataUser.setRowMargin(0);
        DataUser.setShowHorizontalLines(false);
        DataUser.setShowVerticalLines(false);
        DataUser.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(DataUser);

        roundPanel1.setForeground(new java.awt.Color(240, 240, 240));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setText("Data User");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addContainerGap(934, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        asep111.setForeground(new java.awt.Color(255, 255, 255));
        asep111.setText("EDIT");
        asep111.setColor(new java.awt.Color(102, 102, 255));
        asep111.setColorClick(new java.awt.Color(102, 102, 255));
        asep111.setColorOver(new java.awt.Color(51, 0, 204));
        asep111.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        asep111.setRadius(35);
        asep111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asep111ActionPerformed(evt);
            }
        });

        HapusUser.setForeground(new java.awt.Color(255, 255, 255));
        HapusUser.setText("DELETE");
        HapusUser.setBorderColor(new java.awt.Color(204, 0, 51));
        HapusUser.setColor(new java.awt.Color(204, 0, 51));
        HapusUser.setColorClick(new java.awt.Color(204, 0, 51));
        HapusUser.setColorOver(new java.awt.Color(153, 0, 0));
        HapusUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        HapusUser.setRadius(35);
        HapusUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDataUserLayout = new javax.swing.GroupLayout(PanelDataUser);
        PanelDataUser.setLayout(PanelDataUserLayout);
        PanelDataUserLayout.setHorizontalGroup(
            PanelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataUserLayout.createSequentialGroup()
                .addGroup(PanelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDataUserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(HapusUser, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(asep111, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDataUserLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(PanelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        PanelDataUserLayout.setVerticalGroup(
            PanelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataUserLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(asep111, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(HapusUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1460, Short.MAX_VALUE))
        );

        MainPanel.add(PanelDataUser, "card4");

        PanelDataBarang.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel3.setForeground(new java.awt.Color(240, 240, 240));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setText("Data Barang");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel13)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        DataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Hargal", "Stok"
            }
        ));
        DataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataBarangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(DataBarang);

        TambahBarang_Btn.setForeground(new java.awt.Color(255, 255, 255));
        TambahBarang_Btn.setText("ADD");
        TambahBarang_Btn.setColor(new java.awt.Color(102, 102, 255));
        TambahBarang_Btn.setColorClick(new java.awt.Color(102, 102, 255));
        TambahBarang_Btn.setColorOver(new java.awt.Color(51, 0, 204));
        TambahBarang_Btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TambahBarang_Btn.setRadius(35);
        TambahBarang_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahBarang_BtnActionPerformed(evt);
            }
        });

        HapusBarang.setForeground(new java.awt.Color(255, 255, 255));
        HapusBarang.setText("DELETE");
        HapusBarang.setColor(new java.awt.Color(204, 0, 51));
        HapusBarang.setColorClick(new java.awt.Color(204, 0, 51));
        HapusBarang.setColorOver(new java.awt.Color(153, 0, 0));
        HapusBarang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        HapusBarang.setRadius(35);
        HapusBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusBarangActionPerformed(evt);
            }
        });

        CekStock_btn.setText("Cari");
        CekStock_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CekStock_btnActionPerformed(evt);
            }
        });

        txt_stok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_stok.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_stok.setAlignmentX(1.0F);
        txt_stok.setAlignmentY(1.0F);
        txt_stok.setBorder(null);

        javax.swing.GroupLayout PanelDataBarangLayout = new javax.swing.GroupLayout(PanelDataBarang);
        PanelDataBarang.setLayout(PanelDataBarangLayout);
        PanelDataBarangLayout.setHorizontalGroup(
            PanelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataBarangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(PanelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDataBarangLayout.createSequentialGroup()
                        .addComponent(CekStock_btn)
                        .addGap(18, 18, 18)
                        .addComponent(CekStock_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDataBarangLayout.createSequentialGroup()
                        .addGap(775, 775, 775)
                        .addComponent(HapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TambahBarang_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        PanelDataBarangLayout.setVerticalGroup(
            PanelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataBarangLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(PanelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CekStock_btn)
                    .addComponent(CekStock_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(PanelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TambahBarang_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1466, Short.MAX_VALUE))
        );

        MainPanel.add(PanelDataBarang, "card3");

        PanelLaporanKeuangan.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel5.setForeground(new java.awt.Color(240, 240, 240));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setText("Laporan Keuangan");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("(Dinyatakan dalam bentuk ribuan rupiah)");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Laporan_Keuangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tanggal ", "Total Pemasukan", "Total Pengeluaran", "Total Pendapatan"
            }
        ));
        jScrollPane6.setViewportView(Laporan_Keuangan);

        LaporanKeuangan_Btn.setBackground(new java.awt.Color(102, 102, 255));
        LaporanKeuangan_Btn.setForeground(new java.awt.Color(255, 255, 255));
        LaporanKeuangan_Btn.setText("ADD");
        LaporanKeuangan_Btn.setColor(new java.awt.Color(102, 102, 255));
        LaporanKeuangan_Btn.setColorClick(new java.awt.Color(102, 102, 255));
        LaporanKeuangan_Btn.setColorOver(new java.awt.Color(51, 0, 153));
        LaporanKeuangan_Btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LaporanKeuangan_Btn.setRadius(35);
        LaporanKeuangan_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanKeuangan_BtnActionPerformed(evt);
            }
        });

        asep112.setBackground(new java.awt.Color(102, 102, 255));
        asep112.setForeground(new java.awt.Color(255, 255, 255));
        asep112.setText("PRINT");
        asep112.setColor(new java.awt.Color(102, 102, 255));
        asep112.setColorClick(new java.awt.Color(102, 102, 255));
        asep112.setColorOver(new java.awt.Color(51, 0, 105));
        asep112.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        asep112.setRadius(35);
        asep112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asep112ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Total Pendapatan ");

        Pendapatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLaporanKeuanganLayout = new javax.swing.GroupLayout(PanelLaporanKeuangan);
        PanelLaporanKeuangan.setLayout(PanelLaporanKeuanganLayout);
        PanelLaporanKeuanganLayout.setHorizontalGroup(
            PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLaporanKeuanganLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelLaporanKeuanganLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(PanelLaporanKeuanganLayout.createSequentialGroup()
                            .addComponent(asep112, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(LaporanKeuangan_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        PanelLaporanKeuanganLayout.setVerticalGroup(
            PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLaporanKeuanganLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(PanelLaporanKeuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LaporanKeuangan_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asep112, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1447, Short.MAX_VALUE))
        );

        MainPanel.add(PanelLaporanKeuangan, "card2");

        PanelHistoriTransaksi.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel4.setForeground(new java.awt.Color(240, 240, 240));

        LabelDashboard2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        LabelDashboard2.setText("Transaction History");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LabelDashboard2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(LabelDashboard2)
                .addGap(34, 34, 34))
        );

        Transaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksi", "Tanggal Transaksi", "Total Harga", "Tunai", "Kembalian", "ID Kasir"
            }
        ));
        Transaction.setRowHeight(25);
        Transaction.setRowMargin(0);
        Transaction.setShowHorizontalLines(false);
        Transaction.setShowVerticalLines(false);
        Transaction.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(Transaction);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_next_page_48px.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jButton4.setText("Cari");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        pemasukanTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Rp.");

        javax.swing.GroupLayout PanelHistoriTransaksiLayout = new javax.swing.GroupLayout(PanelHistoriTransaksi);
        PanelHistoriTransaksi.setLayout(PanelHistoriTransaksiLayout);
        PanelHistoriTransaksiLayout.setHorizontalGroup(
            PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHistoriTransaksiLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelHistoriTransaksiLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(pemasukanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelHistoriTransaksiLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Tanggalans2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        PanelHistoriTransaksiLayout.setVerticalGroup(
            PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHistoriTransaksiLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4))
                    .addComponent(Tanggalans2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelHistoriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pemasukanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1426, Short.MAX_VALUE))
        );

        MainPanel.add(PanelHistoriTransaksi, "card6");

        PanelDetail.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel6.setForeground(new java.awt.Color(240, 240, 240));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setText("Detail Transaksi");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel15)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        DetailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jumlah Terjual", "Sub Total"
            }
        ));
        jScrollPane5.setViewportView(DetailTransaksi);

        Max.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Max.setBorder(null);

        Min.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Min.setBorder(null);
        Min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinActionPerformed(evt);
            }
        });

        jLabel16.setText("Penjualan Terbanyak");

        jLabel17.setText("Penjualan Terdikit");

        jButton6.setText("Cari");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDetailLayout = new javax.swing.GroupLayout(PanelDetail);
        PanelDetail.setLayout(PanelDetailLayout);
        PanelDetailLayout.setHorizontalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PanelDetailLayout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(Tanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelDetailLayout.createSequentialGroup()
                            .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addGap(34, 34, 34)
                            .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Min, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                .addComponent(Max)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        PanelDetailLayout.setVerticalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Tanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(1564, Short.MAX_VALUE))
        );

        MainPanel.add(PanelDetail, "card7");

        jPanel2.add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 0, 1110, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void autosum() {
        int numberRow = RecentTransaction.getRowCount();

        int tot = 0;

        for (int i = 0; i < numberRow; i++) {
            int val = Integer.parseInt((String) RecentTransaction.getValueAt(i, 2));
            tot += val;
        }
        pemasukan.setText("" + tot);
    }

    public void autotransaction() {
        int numberRow = Transaction.getRowCount();

        int tot = 0;

        for (int i = 0; i < numberRow; i++) {
            int val = Integer.parseInt((String) Transaction.getValueAt(i, 2));
            tot += val;
        }
        pemasukanTransaksi.setText("" + tot);
    }
    private void DashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboardMouseEntered

    private void DashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboardMouseExited

    private void DashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboardMousePressed

    private void DashboardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboardMouseReleased

    private void LaporanKeuanganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaporanKeuanganMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_LaporanKeuanganMouseEntered

    private void LaporanKeuanganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaporanKeuanganMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_LaporanKeuanganMouseExited

    private void LaporanKeuanganMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaporanKeuanganMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaporanKeuanganMousePressed

    private void LaporanKeuanganMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaporanKeuanganMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_LaporanKeuanganMouseReleased


    private void LaporanKeuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanKeuanganActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        MainPanel.add(PanelLaporanKeuangan);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_LaporanKeuanganActionPerformed

    private void DatabarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatabarangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DatabarangMouseEntered

    private void DatabarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatabarangMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DatabarangMouseExited

    private void DatabarangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatabarangMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DatabarangMousePressed

    private void DatabarangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatabarangMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DatabarangMouseReleased

    private void DatabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatabarangActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        MainPanel.add(PanelDataBarang);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_DatabarangActionPerformed

    private void DatauserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatauserMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DatauserMouseEntered

    private void DatauserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatauserMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DatauserMouseExited

    private void DatauserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatauserMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DatauserMousePressed

    private void DatauserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatauserMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DatauserMouseReleased

    private void DatauserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatauserActionPerformed

        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM owner WHERE Id_Owner = '" + Login1.UsernameOwner.getText() + "'";

            Connection conn = (Connection) Config.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            if (rs.next()) {
                if (UsernameOwner.getText().equals(rs.getString("Id_Owner"))) {

                    MainPanel.removeAll();
                    MainPanel.repaint();
                    MainPanel.revalidate();

                    MainPanel.add(PanelDataUser);
                    MainPanel.repaint();
                    MainPanel.revalidate();
                } else {
                    JOptionPane.showMessageDialog(this, "tot");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Anda tidak memiliki hak akses");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "kontol");
        }


    }//GEN-LAST:event_DatauserActionPerformed

    private void HistroriTransaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistroriTransaksiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_HistroriTransaksiMouseEntered

    private void HistroriTransaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistroriTransaksiMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_HistroriTransaksiMouseExited

    private void HistroriTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistroriTransaksiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HistroriTransaksiMousePressed

    private void HistroriTransaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistroriTransaksiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_HistroriTransaksiMouseReleased

    private void HistroriTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistroriTransaksiActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        MainPanel.add(PanelHistoriTransaksi);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_HistroriTransaksiActionPerformed

    private void DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        MainPanel.add(PanelDashboard);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_DashboardActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int dialbtn = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(this, "Anda Yakin Ingin Keluar", "Yang Beneerr?", dialbtn);

        if (result == 0) {
            this.setVisible(false);
            new Login().setVisible(true);
            this.dispose();
            Login1.Password.setText("");
            Login1.UsernameOwner.setText("");
            Login.PasswordKasir.setText("");
            Login.IdKasir.setText("");

        } else {
            this.setVisible(true);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AboutUs asep = new AboutUs();
        asep.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void asep111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asep111ActionPerformed

        new EditDataUser().setVisible(true);
    }//GEN-LAST:event_asep111ActionPerformed

    private void chart_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chart_btnActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "SELECT tanggal,total_Pemasukkan,total_Pengeluaran from laporan_keuangan";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(Config.configDB(), sql);
            JFreeChart chart = ChartFactory.createLineChart("Query Chart", "tanggal", "Pemasukan & Pengeluaran", dataset, PlotOrientation.VERTICAL, false, true, false);
            BarRenderer render = null;
            CategoryPlot plt = null;
            render = new BarRenderer();
            ChartFrame frame = new ChartFrame("Query Chart", chart);
            frame.setVisible(true);
            frame.setSize(700, 700);
            frame.setLocationRelativeTo(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chart");
        }
    }//GEN-LAST:event_chart_btnActionPerformed

    private void OrderLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderLabMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Transaksi().setVisible(true);

        OrderLab.setBackground(new Color(62, 31, 67));
    }//GEN-LAST:event_OrderLabMouseClicked

    private void OrderLabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderLabMousePressed
        // TODO add your handling code here:
        OrderLab.setBackground(new Color(250, 250, 250));
    }//GEN-LAST:event_OrderLabMousePressed

    private void OrderLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderLabMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_OrderLabMouseEntered

    private void OrderLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderLabMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_OrderLabMouseExited

    private void OrderLabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderLabMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_OrderLabMouseReleased

    private void TambahBarang_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahBarang_BtnActionPerformed
        // TODO add your handling code here:
        new EditDataBarang().setVisible(true);

    }//GEN-LAST:event_TambahBarang_BtnActionPerformed

    private void HapusBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusBarangActionPerformed
        // TODO add your handling code here:
        String Hapus = "DELETE FROM barang WHERE Kode_Barang ='" + DataBarang.getValueAt(DataBarang.getSelectedRow(), 0).toString() + "'";

        try {

            java.sql.Connection conn = Config.configDB();
            Statement stm = conn.createStatement();
            stm.execute(Hapus);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

            UpdateActionBarang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_HapusBarangActionPerformed

    private void HapusUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusUserActionPerformed
        // TODO add your handling code here:
        String Hapus = "DELETE FROM kasir WHERE ID_Kasir ='" + DataUser.getValueAt(DataUser.getSelectedRow(), 0).toString() + "'";

        try {

            java.sql.Connection conn = Config.configDB();
            Statement stm = conn.createStatement();
            stm.execute(Hapus);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

            UpdateActionKasir();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_HapusUserActionPerformed

    private void LaporanKeuangan_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanKeuangan_BtnActionPerformed
        // TODO add your handling code here:
        new Tambah_Laporan().setVisible(true);

    }//GEN-LAST:event_LaporanKeuangan_BtnActionPerformed

    private void asep112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asep112ActionPerformed
        // TODO add your handling code here:
        java.sql.Connection konek = null;
        try {
            String jdbcDrive = "com.mysql.cj.jdbc.Driver";
            Class.forName(jdbcDrive);

            String url = "jdbc:mysql://localhost:3306/semester2_project";
            String user = "root";
            String password = "";

            konek = DriverManager.getConnection(url, user, password);
            Statement stm = konek.createStatement();

            String report = ("D:\\TugasGithub\\FileWaez\\GitHub\\Tugas\\RatnaStationery\\src\\ratnastationery\\Report\\report1.jrxml");

            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, null, konek);
            JasperViewer.viewReport(JPrint, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


    }//GEN-LAST:event_asep112ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        MainPanel.add(PanelDetail);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        search1();
        autotransaction();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_searchActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        laporanSearch();
        pemasukan();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void MinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MinActionPerformed

    private void CekStock_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CekStock_btnActionPerformed
        // TODO add your handling code here:
        pengecekanStok();
    }//GEN-LAST:event_CekStock_btnActionPerformed

    private void DataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataBarangMouseClicked
        // TODO add your handling code here:
        int number = DataBarang.getSelectedRow();

        CekStock_txt.setText(DataBarang.getValueAt(number, 1).toString());
    }//GEN-LAST:event_DataBarangMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tanggalSearch();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JLabel Anjay = new javax.swing.JLabel();
    private javax.swing.JButton CekStock_btn;
    private javax.swing.JTextField CekStock_txt;
    private javax.swing.JButton Dashboard;
    public static final javax.swing.JTable DataBarang = new javax.swing.JTable();
    public static final javax.swing.JTable DataUser = new javax.swing.JTable();
    private javax.swing.JButton Databarang;
    private javax.swing.JButton Datauser;
    public static final javax.swing.JTable DetailTransaksi = new javax.swing.JTable();
    private ratnastationery.Asep11 HapusBarang;
    private ratnastationery.Asep HapusUser;
    private javax.swing.JButton HistroriTransaksi;
    private javax.swing.JLabel LabelDashboard;
    private javax.swing.JLabel LabelDashboard2;
    private javax.swing.JButton LaporanKeuangan;
    private ratnastationery.Asep11 LaporanKeuangan_Btn;
    public static final javax.swing.JTable Laporan_Keuangan = new javax.swing.JTable();
    public static final javax.swing.JPanel MainPanel = new javax.swing.JPanel();
    private javax.swing.JTextField Max;
    private javax.swing.JTextField Min;
    private javax.swing.JLabel OrderLab;
    private javax.swing.JPanel PanelDashboard;
    private javax.swing.JPanel PanelDataBarang;
    private javax.swing.JPanel PanelDataUser;
    private javax.swing.JPanel PanelDetail;
    private javax.swing.JPanel PanelHistoriTransaksi;
    public static final javax.swing.JPanel PanelLaporanKeuangan = new javax.swing.JPanel();
    public static final javax.swing.JLabel Pendapatan = new javax.swing.JLabel();
    public static final javax.swing.JTable RecentTransaction = new javax.swing.JTable();
    private javax.swing.JLabel Rp;
    private ratnastationery.Asep11 TambahBarang_Btn;
    private com.toedter.calendar.JDateChooser Tanggal1;
    private com.toedter.calendar.JDateChooser Tanggal2;
    private com.toedter.calendar.JDateChooser Tanggalans2;
    public static final javax.swing.JTable Transaction = new javax.swing.JTable();
    private ratnastationery.Asep11 asep111;
    private ratnastationery.Asep11 asep112;
    private javax.swing.JButton chart_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private ratnastationery.RoundPanel junaidi1;
    private ratnastationery.RoundPanel junaidi2;
    private ratnastationery.RoundPanel junaidi3;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel labelRecent;
    private javax.swing.JLabel pemasukan;
    private javax.swing.JLabel pemasukanTransaksi;
    private ratnastationery.RoundPanel roundPanel1;
    private ratnastationery.RoundPanel roundPanel2;
    private ratnastationery.RoundPanel roundPanel3;
    private ratnastationery.RoundPanel roundPanel4;
    private ratnastationery.RoundPanel roundPanel5;
    private ratnastationery.RoundPanel roundPanel6;
    private javax.swing.JTextField search;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
