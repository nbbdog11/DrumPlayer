package main;

import drum.DrumView;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Welcome extends JPanel implements DrumView {

    protected JButton helpButton = new JButton("Help");
	private ActionHandler action = new ActionHandler();

	@Override
	public void initializeView() {
		this.add(new JLabel("Welcome to the DrumPlayer"));
		helpButton.addActionListener(action);
		this.add(helpButton);
	}

	private class ActionHandler implements ActionListener{

        private final String helpString = "Click on a pad to play a drum sample.\nClick the \"Play Backing Track\" button " +
                "to play a track to help you groove.\nYou can select what set you want to jam with " +
                "by selecting one of the options from the dropdown above.";

        @Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Help")){
				Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog (null, helpString,
			               "INSTRUCTIONS", JOptionPane.INFORMATION_MESSAGE );
			}
			
		}
	}
}
