import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class testView extends JFrame{

    private final int WIDTH = 600;
    private final int HEIGHT = 250;
    private MonthView newMonth;
    public testView(){
        super("test view");
        setSize(WIDTH,HEIGHT);
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.LIGHT_GRAY);


        // top panel:
        JPanel topPanel = new JPanel();

        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new FlowLayout(3));
        JButton back = new JButton("<");
        JButton forth = new JButton(">");
        topLeftPanel.add(back);
        topLeftPanel.add(forth);

        JButton quitButton = new JButton("QUIT");

        topPanel.setLayout(new BorderLayout());
        topPanel.add(topLeftPanel,BorderLayout.WEST);
        topPanel.add(quitButton,BorderLayout.EAST);


        mainContainer.add(topPanel,BorderLayout.NORTH);
        

        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize(){return new Dimension(WIDTH,HEIGHT);}
    @Override
    public Dimension getMinimumSize(){return getPreferredSize();}
}