package calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import com.toedter.calendar.JCalendar;

public class PerhitunganHari extends JFrame {
    private JCalendar calendar;
    private JLabel lblHasil;
    private JButton btnHitung;

    public PerhitunganHari() {
        setTitle("Penghitung Hari dalam Bulan");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tambahkan JSpinner dan JComboBox
        JSpinner spnTahun = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 1900, 2100, 1));
        JComboBox<String> cmbBulan = new JComboBox<>(new String[]
        {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" });
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        calendar = new JCalendar();
        panel.add(calendar);

        btnHitung = new JButton("Hitung");
        panel.add(btnHitung);

        lblHasil = new JLabel("Hasil akan ditampilkan di sini.");
        panel.add(lblHasil);

        btnHitung.addActionListener(e -> {
            int tahun = (int) spnTahun.getValue();
            int bulan = cmbBulan.getSelectedIndex() + 1;
            YearMonth yearMonth = YearMonth.of(tahun, bulan);
            int jumlahHari = yearMonth.lengthOfMonth();

            LocalDate firstDayOfMonth = yearMonth.atDay(1);
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
            boolean isLeapYear = yearMonth.isLeapYear();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
            String firstDay = firstDayOfMonth.format(formatter);
            String lastDay = lastDayOfMonth.format(formatter);

            lblHasil.setText("<html>Jumlah hari: " + jumlahHari + "<br>" +
                             "Hari pertama: " + firstDay + "<br>" +
                             "Hari terakhir: " + lastDay + "<br>" +
                             "Tahun kabisat: " + isLeapYear + "</html>");
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PerhitunganHari();
    }
}