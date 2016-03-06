package view;

import main.DrumPlayerConstants;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Welcome extends JPanel implements DrumView {

	@Override
	public void initializeView() {
		this.add(new JLabel(DrumPlayerConstants.WELCOME_LABEL));
		final JButton helpButton = new JButton(DrumPlayerConstants.HELP_BUTTON_TEXT);
		helpButton.addActionListener(new ActionHandler());
		this.add(helpButton);
	}

	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(DrumPlayerConstants.HELP_BUTTON_TEXT)){
				Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog (null, DrumPlayerConstants.HELP_STRING,
						DrumPlayerConstants.HELP_BOX_TITLE, JOptionPane.INFORMATION_MESSAGE );
			}
			
		}
	}
}
