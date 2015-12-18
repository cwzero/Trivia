package trivia.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends GamePanel {
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;

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
		usernameLabel.setPreferredSize(new Dimension(600, 100));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(southPark);
		add(usernameLabel, gbc_usernameLabel);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 0;
		gbc_usernameField.gridwidth = 3;
		gbc_usernameField.gridheight = 1;
		gbc_usernameField.fill = GridBagConstraints.BOTH;
		gbc_usernameField.weightx = 0;
		gbc_usernameField.weighty = 0;

		usernameField = new JTextField("Username");
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setFont(southParkBig);
		usernameField.setOpaque(false);
		usernameField.setPreferredSize(new Dimension(600, 100));
		usernameField.setForeground(Color.WHITE);
		add(usernameField, gbc_usernameField);

		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 1;
		gbc_passwordLabel.gridwidth = 1;
		gbc_passwordLabel.gridheight = 1;
		gbc_passwordLabel.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel.weightx = 0;
		gbc_passwordLabel.weighty = 0;

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setPreferredSize(new Dimension(600, 100));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(southPark);
		add(passwordLabel, gbc_passwordLabel);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.gridheight = 1;
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.weightx = 0;
		gbc_passwordField.weighty = 0;

		passwordField = new JPasswordField();

		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(southParkBig);
		passwordField.setOpaque(false);
		passwordField.setPreferredSize(new Dimension(600, 100));
		passwordField.setForeground(Color.WHITE);
		add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 2;
		gbc_loginButton.gridwidth = 1;
		gbc_loginButton.gridheight = 1;
		gbc_loginButton.fill = GridBagConstraints.BOTH;
		gbc_loginButton.weightx = 0;
		gbc_loginButton.weighty = 0;

		JButton loginButton = new JButton("Log In");
		loginButton.setFont(southPark);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorderPainted(false);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPanel.this.loginButton_Click();
			}
		});

		add(loginButton, gbc_loginButton);

		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.gridx = 2;
		gbc_backButton.gridy = 2;
		gbc_backButton.gridwidth = 1;
		gbc_backButton.gridheight = 1;
		gbc_backButton.fill = GridBagConstraints.BOTH;
		gbc_backButton.weightx = 0;
		gbc_backButton.weighty = 0;

		JButton backButton = new JButton("Back");
		backButton.setFont(southPark);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPanel.this.backButton_Click();
			}
		});
		add(backButton, gbc_backButton);
	}

	public void loginButton_Click() {
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();

		login(username, password);

		for (int a = 0; a < password.length; a++) {
			password[a] = ' ';
		}
	}

	public void backButton_Click() {
		new MainMenuPanel(gameFrame);
	}

	public void login(String username, char[] password) {
		new MainMenuPanel(gameFrame);
	}
}
