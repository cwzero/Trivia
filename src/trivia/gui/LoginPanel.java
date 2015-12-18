package trivia.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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

	@SuppressWarnings("serial")
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
		usernameLabel.setPreferredSize(new Dimension(200, 80));
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

		usernameField = new JTextField("Username") {

			@Override
			public void setBorder(Border border) {
			}
		};
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setFont(southPark);
		usernameField.setOpaque(false);
		usernameField.setPreferredSize(new Dimension(350, 80));
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
		passwordLabel.setPreferredSize(new Dimension(200, 80));
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

		passwordField = new JPasswordField("Password123") {
			@Override
			public void setBorder(Border border) {
			}
		};

		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(southPark);
		passwordField.setOpaque(false);
		passwordField.setPreferredSize(new Dimension(350, 80));
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

		Icon loginIcon = new ImageIcon("src/images/login1.png");
		Icon loginHover = new ImageIcon("src/images/logingif.gif");
		JButton loginButton = new JButton(loginIcon);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorderPainted(false);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPanel.this.loginButton_Click();
			}
		});
		loginButton.setIcon(loginIcon);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setIcon(loginHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setIcon(loginIcon);
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

		Icon backIcon = new ImageIcon("src/images/back1.png");
		Icon backHover = new ImageIcon("src/images/backgif.gif");
		JButton backButton = new JButton(backIcon);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPanel.this.backButton_Click();
			}
		});
		backButton.setIcon(backIcon);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backIcon);
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
