import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarView extends JFrame implements ActionListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 265;
    private MonthView monthCal = new MonthView();
    private Calendar today = new GregorianCalendar();
    private JLabel label;
    private eventPanel eventPane;

    public CalendarView() {
        super("Calendar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(2, 2));

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel topLeftPanel = new JPanel();

        JButton back = new JButton("<");
        label = new JLabel(String.valueOf(today.getTime()));

        topLeftPanel.add(back);
        topLeftPanel.add(label);

        topPanel.add(topLeftPanel, BorderLayout.WEST);
        // topPanel.add(quitButton, BorderLayout.EAST);

        // event panel:
        eventPane = new eventPanel();

        back.addActionListener(this);
        //eventPane.addActionListener(this);
        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(monthCal.getMonthPanel(), BorderLayout.WEST);
        mainContainer.add(eventPane);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        today.roll(Calendar.DAY_OF_MONTH, -1);
        label.setText(String.valueOf(today.getTime()));
        eventPanel(today);
        monthCal.resetToday(today);
    }

    private class eventPanel implements ActionListener {

        private JPanel eventPan = new JPanel(new BorderLayout(1, 1));
        private int day; 
        private int month;
        private int acToday;
        private String daysOfTheWeek[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                "Sunday" };
        private JLabel todayLabel; 
        private JLabel event;
        public eventPanel() {

            resetElements(today);
            event.setBackground(Color.lightGray);
            eventPan.add(todayLabel, BorderLayout.NORTH);
            eventPan.add(event, BorderLayout.CENTER);
        }

        public void resetToday(Calendar newDay){
            day = newDay.get(Calendar.DAY_OF_WEEK);
            month = newDay.get(Calendar.MONTH) + 1;
            acToday = newDay.get(Calendar.DAY_OF_MONTH);
        }

        public void resetElements(Calendar newDay){
            resetToday(newDay);
            todayLabel = new JLabel(daysOfTheWeek[day - 1] + " " + month + "/" + acToday,
            SwingConstants.CENTER);
            event = new JLabel("Event...", SwingConstants.CENTER);
        }

        public void setEventPan(Calendar newDay){

            resetElements(newDay);
            event.setBackground(Color.lightGray);
            eventPan.add(todayLabel, BorderLayout.NORTH);
            eventPan.add(event, BorderLayout.CENTER);
        }

        public void actionPerformed(ActionEvent e){
            
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

}