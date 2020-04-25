import javax.swing.*;
import java.awt.*;



public class EventView {

    private final int HEIGHT;
    private final int WIDTH;

    private JPanel eventPanel;

    public EventView(){
        HEIGHT = 100;
        WIDTH = 460;
        eventPanel = createEventPanel();
        eventPanel.setBackground(Color.blue);
    }

    public JPanel getEventPanel(){
        return eventPanel;
    }


    private JPanel createEventPanel(){

        JPanel newEventPanel = new JPanel(new BorderLayout());
        
        JLabel todayText = new JLabel("today");
        
        newEventPanel.add(todayText,BorderLayout.NORTH);
        newEventPanel.add(eventTextPanel(), BorderLayout.SOUTH);

        return newEventPanel;
    }

    private JPanel eventTextPanel(){
        JPanel events = new JPanel();
        JLabel eventLabel = new JLabel("event area 1");

        events.add(eventLabel);
        events.setBackground(Color.CYAN);
        return events;
    }

    public Dimension preferredLayoutSize(Container parent){
        return minimumLayoutSize(parent);
    }
    public Dimension minimumLayoutSize(Container parent){
        return new Dimension(WIDTH,HEIGHT);
    }


}