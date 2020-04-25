import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ButtonGui extends JFrame implements ActionListener {

    private JLabel label;
    private JButton button;

    public ButtonGui() {
        super("button gui");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label = new JLabel("Test");
        button = new JButton("Click Me");
        // button.addActionListener(l);
        Container pane = this.getContentPane();
        pane.setLayout(new FlowLayout());

        pane.add(label);
        pane.add(button);

        button.addActionListener(this);
        setVisible(true);
    }

    public static void main(String arg[]) {
        ButtonGui gui = new ButtonGui();
    }

    public void actionPerformed(ActionEvent e) {
        label.setText(e.getActionCommand());
    }

}