package favero.moreira.sma.termoreg;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsWindow {
    private JPanel panel1;
    private JTextField groupValue;
    private JButton setGroups;
    private JLabel groupLabel;
    private JSlider slider1;
    private JTextPane textPane1;

    public SettingsWindow() {
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
