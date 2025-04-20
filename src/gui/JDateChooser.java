package gui;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;

public class JDateChooser extends JPanel {
    private final JSpinner daySpinner;
    private final JSpinner monthSpinner;
    private final JSpinner yearSpinner;
    
    public JDateChooser() {
        setLayout(new FlowLayout());
        
        SpinnerNumberModel dayModel = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerNumberModel monthModel = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerNumberModel yearModel = new SpinnerNumberModel(LocalDate.now().getYear(), 2000, 2100, 1);
        
        daySpinner = new JSpinner(dayModel);
        monthSpinner = new JSpinner(monthModel);
        yearSpinner = new JSpinner(yearModel);
        
        add(new JLabel("Day:"));
        add(daySpinner);
        add(new JLabel("Month:"));
        add(monthSpinner);
        add(new JLabel("Year:"));
        add(yearSpinner);
    }
    
    public void setDate(Date date) {
        if (date != null) {
            LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            daySpinner.setValue(localDate.getDayOfMonth());
            monthSpinner.setValue(localDate.getMonthValue());
            yearSpinner.setValue(localDate.getYear());
        }
    }
    
    public Date getDate() {
        int day = (Integer) daySpinner.getValue();
        int month = (Integer) monthSpinner.getValue();
        int year = (Integer) yearSpinner.getValue();
        
        LocalDate localDate = LocalDate.of(year, month, day);
        return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
} 