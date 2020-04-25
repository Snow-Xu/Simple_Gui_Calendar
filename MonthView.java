import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthView extends JFrame implements ChangeListener {

    // private int curMonth;

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final int WIDTH;
    private final int HEIGHT;
    private final int dayLabelWidth;
    private Calendar today = new GregorianCalendar();

    private JPanel monthPanel;

    public MonthView() {
        
        WIDTH = 140;
        HEIGHT = WIDTH / 7 * 5;
        dayLabelWidth = WIDTH / 7;
        monthPanel = createMonthPanel(today.get(Calendar.YEAR), today.get(Calendar.MONTH));
    }
    
    public void resetToday(Calendar today){
        this.today = today;
        monthPanel = createMonthPanel(today.get(Calendar.YEAR), today.get(Calendar.MONTH));
        System.out.println("from resetToday: " + today.getTime());
        
        
    }

    public JPanel getMonthPanel() {
        return monthPanel;
    }

    private JPanel createMonthPanel(int year, int month) {

        Calendar _cal = new GregorianCalendar();
        _cal.set(Calendar.MONTH, month);
        _cal.set(Calendar.YEAR, year);
        _cal.set(Calendar.DAY_OF_MONTH, 1);
        int days_of_month = _cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int first_day_of_week_inMonth = _cal.get(Calendar.DAY_OF_WEEK);

        // General Panel
        JPanel monthPan = new JPanel(new BorderLayout(1, 1));

        // Create Event Button
        JButton createButton = new JButton("CREATE");
        createButton.setBackground(Color.red);
        createButton.setForeground(Color.white);

        // Text for current month
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        JLabel monthText = new JLabel(monthName[month] + " " + year, 10);

        // different columns and rows according to days of the month
        int layoutRows;
        int layoutColums = 7;
        if (first_day_of_week_inMonth > 6 && days_of_month > 29) {
            layoutRows = 7;
        } else {
            layoutRows = 6;
        }

        // month calendar view
        JPanel curMonthPanel = new JPanel(new GridLayout(layoutRows, layoutColums, 2, 2));
        curMonthPanel.setBorder(new LineBorder(Color.WHITE, 1));

        // first line draw Name of days:
        String s[] = { "S", "M", "T", "W", "T", "F", "S" };
        for (int i = 0; i < 7; i++) {

            JLabel label = new JLabel(s[i]);
            curMonthPanel.add(label);
        }

        // draw month panel
        // draw dummy days
        for (int i = 1; i < first_day_of_week_inMonth; i++) {
            // empty day label
            JLabel label = new JLabel("", new DayLabel(), JLabel.CENTER);
            curMonthPanel.add(label);
        }

        // draw actual days
        for (int i = _cal.getActualMinimum(Calendar.DAY_OF_MONTH); i <= days_of_month; i++) {
            JLabel label = new JLabel("", new DayLabel(i), JLabel.CENTER);
            if(i == today.get(Calendar.DAY_OF_MONTH)){
                label.setBorder(new LineBorder(Color.GRAY));
            }
            // label.addMouseListener(mouseListener)
            curMonthPanel.add(label);
        }

        // draw rest dummy days
        _cal.set(Calendar.DATE, days_of_month);
        int dumStarts = _cal.get(Calendar.DAY_OF_WEEK);
        int ndummyDays = 7 - dumStarts;
        for (int i = 0; i < ndummyDays; i++) {
            JLabel label = new JLabel("", new DayLabel(), JLabel.CENTER);
            curMonthPanel.add(label);
        }
        monthPan.add(createButton, BorderLayout.NORTH);
        monthPan.add(monthText, BorderLayout.CENTER);
        monthPan.add(curMonthPanel, BorderLayout.SOUTH);
        return monthPan;
    }

    // implement the representation of a day icon
    private class DayLabel implements Icon {
        private int dayIndex;
        private String dayName;

        public DayLabel() {
            dayIndex = 0;
            dayName = "";
        }

        public DayLabel(int dayIndex) {
            this.dayIndex = dayIndex;
            this.dayName = "1";
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            RoundRectangle2D dayIcon = new RoundRectangle2D.Double(0, 0, dayLabelWidth, dayLabelWidth, 0, 0);

            g2.setColor(Color.white);
            g2.fill(dayIcon);

            g2.setColor(Color.gray);
            if (dayIndex == 0) {
                g2.drawString(dayName, 2, dayLabelWidth - 1);
            } else {
                g2.drawString(String.valueOf(dayIndex), 2, dayLabelWidth - 1);
            }
        }

        @Override
        public int getIconWidth() {
            return dayLabelWidth;
        }

        @Override
        public int getIconHeight() {
            return dayLabelWidth;
        }

    }

    public Dimension preferredLayoutSize(Container parent) {
        return minimumLayoutSize(parent);
    }

    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override 
    public void stateChanged( ChangeEvent e) {
        repaint();
    }

 

}