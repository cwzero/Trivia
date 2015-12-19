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
import java.awt.GridLayout;

public class SignUpPanel extends GamePanel {
	public SignUpPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
	
	public SignUpPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	private JTextField emailField;

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
		passwordField.setPreferredSize(new Dimension(300, 80));
		passwordField.setForeground(Color.WHITE);
		add(passwordField, gbc_passwordField);

		
		GridBagConstraints gbc_confirmLabel = new GridBagConstraints();
		gbc_confirmLabel.gridx = 0;
		gbc_confirmLabel.gridy = 2;
		gbc_confirmLabel.gridwidth = 1;
		gbc_confirmLabel.gridheight = 1;
		gbc_confirmLabel.fill = GridBagConstraints.BOTH;
		gbc_confirmLabel.weightx = 0;
		gbc_confirmLabel.weighty = 0;

		JLabel confirmLabel = new JLabel("Confirm Password:");
		confirmLabel.setPreferredSize(new Dimension(200, 80));
		confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		confirmLabel.setFont(southPark);
		add(confirmLabel, gbc_confirmLabel);

		GridBagConstraints gbc_confirmField = new GridBagConstraints();
		gbc_confirmField.gridx = 1;
		gbc_confirmField.gridy = 2;
		gbc_confirmField.gridwidth = 3;
		gbc_confirmField.gridheight = 1;
		gbc_confirmField.fill = GridBagConstraints.BOTH;
		gbc_confirmField.weightx = 0;
		gbc_confirmField.weighty = 0;

		confirmField = new JPasswordField("confirm123") {
			@Override
			public void setBorder(Border border) {
			}
		};

		confirmField.setHorizontalAlignment(SwingConstants.CENTER);
		confirmField.setFont(southPark);
		confirmField.setOpaque(false);
		confirmField.setPreferredSize(new Dimension(300, 80));
		confirmField.setForeground(Color.WHITE);
		add(confirmField, gbc_confirmField);
		
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 3;
		gbc_emailLabel.gridwidth = 1;
		gbc_emailLabel.gridheight = 1;
		gbc_emailLabel.fill = GridBagConstraints.BOTH;
		gbc_emailLabel.weightx = 0;
		gbc_emailLabel.weighty = 0;

		JLabel emailLabel = new JLabel("Email Address:");
		emailLabel.setPreferredSize(new Dimension(200, 80));
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setFont(southPark);
		add(emailLabel, gbc_emailLabel);

		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		gbc_emailField.gridwidth = 3;
		gbc_emailField.gridheight = 1;
		gbc_emailField.fill = GridBagConstraints.BOTH;
		gbc_emailField.weightx = 0;
		gbc_emailField.weighty = 0;

		emailField = new JTextField("person@example.com") {

			@Override
			public void setBorder(Border border) {
			}
		};
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setFont(southPark);
		emailField.setOpaque(false);
		emailField.setPreferredSize(new Dimension(300, 80));
		emailField.setForeground(Color.WHITE);
		add(emailField, gbc_emailField);

		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.gridx = 2;
		gbc_backButton.gridy = 4;
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
				SignUpPanel.this.backButton_Click();
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

		ImageIcon registerIcon = new ImageIcon("src/images/singup1.png");
		ImageIcon registerHover = new ImageIcon("src/images/singupgif.gif");

		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 4;
		gbc_registerButton.gridwidth = 1;
		gbc_registerButton.gridheight = 1;
		gbc_registerButton.fill = GridBagConstraints.BOTH;
		gbc_registerButton.weightx = 0;
		gbc_registerButton.weighty = 0;

		JButton registerButton = new JButton(registerIcon);
		registerButton.setOpaque(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setBorderPainted(false);
	

		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				registerButton.setIcon(registerHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				registerButton.setIcon(registerIcon);
			}
		});
		add(registerButton, gbc_registerButton);
	}

	public void backButton_Click() {
		new MainMenuPanel(gameFrame);
	}
	
}
