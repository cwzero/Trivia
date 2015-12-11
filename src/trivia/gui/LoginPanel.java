package trivia.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginPanel extends GamePanel {
	private static final long serialVersionUID = 1L;

	public LoginPanel() {
		super();
		createGui();
	}

	public LoginPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	@Override
	protected void createGui() {
		gameFrame.setTitle("Log In");
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 0;
		gbc_usernameLabel.gridwidth = 1;
		gbc_usernameLabel.gridheight = 1;
		gbc_usernameLabel.fill = GridBagConstraints.BOTH;
		gbc_usernameLabel.weightx = 0;
		gbc_usernameLabel.weighty = 0;
		
		JLabel usernameLabel = new JLabel("Username:");
		add(usernameLabel, gbc_usernameLabel);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 0;
		gbc_usernameField.gridwidth = 1;
		gbc_usernameField.gridheight = 1;
		gbc_usernameField.fill = GridBagConstraints.BOTH;
		gbc_usernameField.weightx = 0;
		gbc_usernameField.weighty = 0;
		
		JTextField usernameField = new JTextField("Username");
		add(usernameField, gbc_usernameField);
	}
}
