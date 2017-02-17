import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;

/**
 * GUI and main file of the registration system.
 *
 * @author Jose Cabrera, Raul Hinostroza, Max Morales
 * @version 4
 * @see SpringUtilities
 */
public class InterfacePrototype {

	/*
    /**
	 * An instance of StudentSystem.
	static StudentSystem system = new StudentSystem();
	 */

	/**
	 * The current user of the system.
	 */
	private static User currentUser;

	/**
	 * Controls whether a popup windows is open, since only 1 is allowed at a time.
	 */
	private static boolean popupOpen;

	/**
	 * Opens the login window.
	 *
	 * @param args A string array of arguments
	 */
	public static void main(String[] args) {
		popupOpen = false;
		StudentSystem.initializeStudentSystem();
		logIn();
	}

	/**
	 * Logs user into the system after entering a
	 * username and password.
	 */
	private static void logIn() {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Student Registration System", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));
		pageTitle.setForeground(Color.WHITE);
		pageTitlePanel.add(pageTitle);

		//The following is to add an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		//The following is a test of label with textField
		String[] labels = {"Username ", "Password"};

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JLabel fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		JButton loginButton = new JButton("Login");
		JPasswordField passwordField = new JPasswordField(10);
		JTextField userField = new JTextField(10);
		for (int i = 0; i <= labels.length; i++) {

			if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				inputPanel.add(loginButton);
			} else {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				if (labels[i].equals("Password")) {
					textFieldLabels.setLabelFor(passwordField);
					inputPanel.add(passwordField);
				} else {
					textFieldLabels.setLabelFor(userField);
					inputPanel.add(userField);
				}

			}
		}

		//Logs user into his account
		loginButton.addActionListener(e -> {
			String username = userField.getText();
			String password = new String((passwordField.getPassword()));
			boolean success = StudentSystem.logIn(username, password);

			if (success) {
				registrationFrame.setVisible(false);
				currentUser = StudentSystem.accounts.get(username);
				if (StudentSystem.accounts.get(username).getAccountType() == 1)
					studentMenu((Student) StudentSystem.accounts.get(username));
				else if (StudentSystem.accounts.get(username).getAccountType() == 2)
					facultyMenu();
				else if (StudentSystem.accounts.get(username).getAccountType() == 3)
					staffMenu(StudentSystem.accounts.get(username));
				else if (StudentSystem.accounts.get(username).getAccountType() == 4)
					adminMenu(StudentSystem.accounts.get(username));

			} else
				JOptionPane.showMessageDialog(null, "Invalid Username/Password.");

		});

		SpringUtilities.makeCompactGrid(inputPanel,
				4, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 60);         //xPad yPad

		//End test of TextField


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	} // End of LogIn

	/**
	 * Shows the menu for the admin after entering an admin
	 * username and password.
	 *
	 * @param user the current user of the system.
	 */
	private static void adminMenu(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Student Registration & Assessment", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));
		pageTitle.setForeground(Color.WHITE);
		pageTitlePanel.add(pageTitle);

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		//Button Grid
		String[] buttonNames = {"Register Staff", "Register Student", "Check Classes",
				"Register Faculty", "Graduation Status", "Register Classes",
				"Retrieve Password", "Log Off"};

		JPanel buttonPanel = new JPanel(new SpringLayout());
		buttonPanel.setOpaque(false);

		JButton regStaffButton = new JButton(buttonNames[0]);
		JLabel fillerLabel = new JLabel("");
		buttonPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		buttonPanel.add(fillerLabel);
		buttonPanel.add(regStaffButton);
		regStaffButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			registerStaff(user);
		});

		JButton regStudentButton = new JButton(buttonNames[1]);
		buttonPanel.add(regStudentButton);

		regStudentButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			registerStudent(user);
		});

		JButton checkClassesButton = new JButton(buttonNames[2]);
		buttonPanel.add(checkClassesButton);

		checkClassesButton.addActionListener(e -> printCourses());

		JButton regFacultyButton = new JButton(buttonNames[3]);
		buttonPanel.add(regFacultyButton);

		regFacultyButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			registerFaculty(user);
		});

		JButton gradStatusButton = new JButton(buttonNames[4]);
		buttonPanel.add(gradStatusButton);

		gradStatusButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			gradStatus1(user);
		});

		JButton regClassesButton = new JButton(buttonNames[5]);
		buttonPanel.add(regClassesButton);

		regClassesButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			addClasses1(user);
		});

		JButton retrievePassButton = new JButton(buttonNames[6]);
		buttonPanel.add(retrievePassButton);

		retrievePassButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			retrievePassword(user);
		});

		JButton logOffButton = new JButton(buttonNames[7]);
		buttonPanel.add(logOffButton);

		logOffButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			logIn();
		});

		SpringUtilities.makeCompactGrid(buttonPanel,
				5, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(buttonPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of adminMenu

	/**
	 * Shows the menu for the staff after entering a staff
	 * username and password.
	 *
	 * @param user the current user of the system.
	 */
	private static void staffMenu(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Student Registration & Assessment", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));
		pageTitle.setForeground(Color.WHITE);
		pageTitlePanel.add(pageTitle);

		//The following is to add an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		//Button Grid
		String[] buttonNames = {"Register Student", "Register Faculty", "Check Classes",
				"Register Classes", "Graduation Status", "Log Off"};

		JPanel buttonPanel = new JPanel(new SpringLayout());
		buttonPanel.setOpaque(false);

		JButton regStudentButton = new JButton(buttonNames[0]);
		buttonPanel.add(regStudentButton);

		regStudentButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			registerStudent(user);
		});

		JButton regFacultyButton = new JButton(buttonNames[1]);
		buttonPanel.add(regFacultyButton);

		regFacultyButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			registerFaculty(user);
		});

		JButton checkClassesButton = new JButton(buttonNames[2]);
		buttonPanel.add(checkClassesButton);

		checkClassesButton.addActionListener(e -> printCourses());

		JButton regClassesButton = new JButton(buttonNames[3]);
		buttonPanel.add(regClassesButton);

		regClassesButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			addClasses1(user);
		});

		JButton gradStatusButton = new JButton(buttonNames[4]);
		buttonPanel.add(gradStatusButton);

		gradStatusButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			gradStatus1(user);
		});

		JButton logOffButton = new JButton(buttonNames[5]);
		buttonPanel.add(logOffButton);

		logOffButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			logIn();
		});

		SpringUtilities.makeCompactGrid(buttonPanel,
				6, 1,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(buttonPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	} // End of staffMenu

	/**
	 * Prints all of the available courses.
	 */
	private static void printCourses() {

		// For the Courses To Be Displayed
		JPanel panel = new JPanel(new SpringLayout());
		Set<String> keys = StudentSystem.courses.keySet();
		JLabel textArea;
		for (String key : keys) {
			textArea = new JLabel("Class Name: " + StudentSystem.courses.get(key).getName() + " CRN: " + key);
			textArea.setFont(textArea.getFont().deriveFont(18.0f));    // Bigger Font Size
            textArea.setForeground(Color.BLUE);
            textArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			panel.add(textArea);
		}
		panel.setOpaque(false);
		panel.setBackground(Color.ORANGE);

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(
				panel, //parent
				StudentSystem.courses.size(), 1, 3, 3, //initX, initY
				3, 3 //xPad, yPad
				);

		// For the Window Frame
		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// For the Title Frame
		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Available Courses", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.BLUE);                       // Changes Font Color
		pageTitlePanel.add(pageTitle);


		registrationFrame.add(panel);
		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(panel, BorderLayout.CENTER);
		registrationFrame.setResizable(false);
		registrationFrame.pack();


		registrationFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				popupOpen = false;
			}
		});

		if (!popupOpen) {
			registrationFrame.setVisible(true);
			popupOpen = true;
		} else {
			JOptionPane.showMessageDialog(null, "Close your current popup window.");
			registrationFrame.removeAll();
		}

	}

	// To Print Available Courses

	/**
	 * Prints the courses the student being looked up is
	 * registered for.
	 *
	 * @param currentStudent the student who is being looked up.
	 */
	private static void printRegisteredCourses(Student currentStudent) {

		// For the Courses To Be Displayed
		JPanel panel = new JPanel(new SpringLayout());
		Set<String> keys = currentStudent.courses;
		JLabel textArea;
		for (String key : keys) {
			textArea = new JLabel("Class Name: " + StudentSystem.courses.get(key).getName() + " CRN: " + key);
			textArea.setFont(textArea.getFont().deriveFont(18.0f));    // Bigger Font Size
            textArea.setForeground(Color.BLUE);
            textArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			panel.add(textArea);
		}
		panel.setOpaque(false);
		panel.setBackground(Color.ORANGE);

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(
				panel, //parent
				currentStudent.courses.size(), 1, 3, 3, //initX, initY
				3, 3 //xPad, yPad
				);

		// For the Window Frame
		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// For the Title Frame
		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Registered Courses", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.BLUE);                       // Changes Font Color
		pageTitlePanel.add(pageTitle);


		registrationFrame.add(panel);
		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(panel, BorderLayout.CENTER);
		registrationFrame.setResizable(false);
		registrationFrame.pack();

		registrationFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				popupOpen = false;
			}
		});

		if (!popupOpen) {
			registrationFrame.setVisible(true);
			popupOpen = true;
		} else {
			JOptionPane.showMessageDialog(null, "Close your current popup window.");
			registrationFrame.removeAll();
		}

	}

	/**
	 * Shows the menu for the student after entering a student
	 * username and password.
	 *
	 * @param currentStudent the student currently using the system.
	 */
	private static void studentMenu(Student currentStudent) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Student Assessment ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following is to add an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		//Button Grid
		String[] buttonNames = {"Check Available Classes", "Check Registered Classes", "Register Classes",
				"Graduation Status", "Log Off"};

		JPanel buttonPanel = new JPanel(new SpringLayout());
		buttonPanel.setOpaque(false);

		JButton checkAvailableButton = new JButton(buttonNames[0]);
		buttonPanel.add(checkAvailableButton);

		checkAvailableButton.addActionListener(e -> printCourses());

		JButton checkRegisteredButton = new JButton(buttonNames[1]);
		buttonPanel.add(checkRegisteredButton);

		checkRegisteredButton.addActionListener(e -> printRegisteredCourses(currentStudent));

		JButton regClassesButton = new JButton(buttonNames[2]);
		buttonPanel.add(regClassesButton);

		regClassesButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			addClasses2(currentStudent);
		});

		JButton gradStatusButton = new JButton(buttonNames[3]);
		buttonPanel.add(gradStatusButton);

		gradStatusButton.addActionListener(e -> displayGraduationStatusPopup(currentStudent));

		JButton logOffButton = new JButton(buttonNames[4]);
		buttonPanel.add(logOffButton);

		logOffButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			logIn();
		});

		SpringUtilities.makeCompactGrid(buttonPanel,
				5, 1,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(buttonPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of studentMenu

	/**
	 * Shows the menu for the faculty after entering a faculty
	 * username and password.
	 */
	private static void facultyMenu() {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		JPanel pageTitlePanel = new JPanel();                    // For login of software
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Student Assessment", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following is to add an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		//Button Grid
		String[] buttonNames = {"Check Courses", "Assign Grades", "Log Off"};

		JPanel buttonPanel = new JPanel(new SpringLayout());
		buttonPanel.setOpaque(false);

		JButton checkCoursesButton = new JButton(buttonNames[0]);
		buttonPanel.add(checkCoursesButton);

		checkCoursesButton.addActionListener(e -> printCourses());

		JButton assignGradeButton = new JButton(buttonNames[1]);
		buttonPanel.add(assignGradeButton);

		assignGradeButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			gradeClass1();
		});

		JButton logOffButton = new JButton(buttonNames[2]);
		buttonPanel.add(logOffButton);

		logOffButton.addActionListener(e -> {
			registrationFrame.setVisible(false);
			logIn();
		});

		SpringUtilities.makeCompactGrid(buttonPanel,
				3, 1,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(buttonPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes


	}  // End of facultyMenu

	/**
	 * Registers a new student into the system.
	 *
	 * @param user the current user of the system.
	 */
	private static void registerStudent(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Register Student     ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following is to add an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Student Name", "Student ID", "Student Username",
				"Student Password", "Major"};
		JTextField textField[] = new JTextField[labels.length];
		String[] buttonLabels = {"Register", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
		JLabel fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		JPasswordField passwordField = new JPasswordField(10);
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		for (int i = 0; i <= numLabels; i++) {

			if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
			} 
			else if (i == 3){
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				passwordField.setMaximumSize(textField[0].getPreferredSize());
				textFieldLabels.setLabelFor(passwordField);
				inputPanel.add(passwordField);
			}
			else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				textField[i] = new JTextField(10);
                textField[i].setMaximumSize(textField[i].getPreferredSize());
                textFieldLabels.setLabelFor(textField[i]);
				inputPanel.add(textField[i]);
			} else {
				menuButton[i - (labels.length) - 1] = new JButton(buttonLabels[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i - (labels.length) - 1]);
			}
		}

		//Register new Student
		menuButton[0].addActionListener(e -> {
			String name = textField[0].getText();
            String id = textField[1].getText();
			String username = textField[2].getText();
			String password = new String((passwordField.getPassword()));
			String major = textField[4].getText();

			boolean success = StudentSystem.registerStudent(name, id, username, password, major);
			if (success)
				JOptionPane.showMessageDialog(null, "Success!");
			else
				JOptionPane.showMessageDialog(null, "Username already exists.");


		});

		//Return to menu
		menuButton[1].addActionListener(e -> {
			if (user instanceof Staff)
				staffMenu(user);
			else if (user.getAccountType() == 4)
				adminMenu(user);
			registrationFrame.setVisible(false);
		});

		//Log off
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});


		SpringUtilities.makeCompactGrid(inputPanel,
				9, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of registerStudent

	/**
	 * Registers a new staff member into the system.
	 *
	 * @param user the current user of the system.
	 */
    private static void registerStaff(User user) {

        JFrame registrationFrame = new JFrame();                // Creates window
        registrationFrame.getContentPane().setBackground(Color.orange);
        BorderLayout registrationLayout = new BorderLayout();
        registrationFrame.setLayout(registrationLayout);

        // The following is for the North portion of the BorderLayout
        JPanel pageTitlePanel = new JPanel();
        pageTitlePanel.setOpaque(false);
        FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
        pageTitlePanel.setLayout(pageTitleLayout);
        JLabel pageTitle = new JLabel("Register Staff     ", SwingConstants.RIGHT);
        pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
        pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
        pageTitlePanel.add(pageTitle);

        //The following adds an image in BorderLayout Center

        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        try {
            BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
            imagePanel.add(imageLabel);

        } catch (IOException e) {
            JLabel errorMsg = new JLabel("Could not load image.");
            imagePanel.add(errorMsg);
        }

        // End of image

        // The following is for the East portion of the BorderLayout
        String[] labels = {"Name", "Staff Username", "Staff Password"};
        String[] buttonLabels = {"Register", "Back to menu", "Log off"};
        int numLabels = labels.length + buttonLabels.length;

        JPanel inputPanel = new JPanel(new SpringLayout());
        inputPanel.setOpaque(false);
        JButton menuButton[] = new JButton[buttonLabels.length];
        JTextField textField[] = new JTextField[labels.length];
        JLabel fillerLabel = new JLabel("");
        inputPanel.add(fillerLabel);
        fillerLabel = new JLabel("");
        inputPanel.add(fillerLabel);
        fillerLabel = new JLabel("");
        inputPanel.add(fillerLabel);
        fillerLabel = new JLabel("");
        JPasswordField passwordField = new JPasswordField(10);
        inputPanel.add(fillerLabel);
        for (int i = 0; i < numLabels; i++) {

            if (i >= labels.length) {
                fillerLabel = new JLabel("");
                inputPanel.add(fillerLabel);
                menuButton[i - (labels.length)] = new JButton(buttonLabels[i - (labels.length)]);
                inputPanel.add(menuButton[i - (labels.length)]);
            } else if (i == 2){
                JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
                textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));
                textFieldLabels.setForeground(Color.WHITE);
                inputPanel.add(textFieldLabels);
                passwordField.setMaximumSize(textField[0].getPreferredSize());
                textFieldLabels.setLabelFor(passwordField);
                inputPanel.add(passwordField);
            } else if (i < labels.length) {
                JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
                textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
                textFieldLabels.setForeground(Color.WHITE);
                inputPanel.add(textFieldLabels);
                textField[i] = new JTextField(10);
                textField[i].setMaximumSize(textField[i].getPreferredSize());
                textFieldLabels.setLabelFor(textField[i]);
                inputPanel.add(textField[i]);
            }
        }

        //Register new staff
        menuButton[0].addActionListener(e -> {
            String name = textField[0].getText();
            String username = textField[1].getText();
            String password = new String((passwordField.getPassword()));

            boolean success = StudentSystem.registerStaff(name, username, password);

            if (success)
                JOptionPane.showMessageDialog(null, "Staff Registered.");
            else
                JOptionPane.showMessageDialog(null, "Username already exists.");
        });

        //Return to menu
        menuButton[1].addActionListener(e -> {
            adminMenu(user);
            registrationFrame.setVisible(false);
        });

        //Log off
        menuButton[2].addActionListener(e -> {
            logIn();
            registrationFrame.setVisible(false);
        });

        SpringUtilities.makeCompactGrid(inputPanel,
                8, 2,          //rows, columns
                6, 6,          //initX, initY
                10, 10);         //xPad yPad

        // End of the East portion


        registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
        registrationFrame.add(inputPanel, BorderLayout.EAST);
        registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
        registrationFrame.pack();
        registrationFrame.setVisible(true);                                   // Shows window
        registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

    }  // End of registerStaff

	/**
	 * Registers a new faculty member into the system.
	 *
	 * @param user the current user of the system.
	 */
	private static void registerFaculty(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Register Faculty      ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Name", "Faculty Username", "Faculty Password",
		"Faculty ID"};
		String[] buttonLabels = {"Register", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
		JTextField textField[] = new JTextField[labels.length];
		JLabel fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
        JPasswordField passwordField = new JPasswordField(10);

        for (int i = 0; i < numLabels; i++) {

			if (i >= labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i - (labels.length)] = new JButton(buttonLabels[i - (labels.length)]);
				inputPanel.add(menuButton[i - (labels.length)]);
			} else if (i == 2){
                JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
                textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));
                textFieldLabels.setForeground(Color.WHITE);
                inputPanel.add(textFieldLabels);
                passwordField.setMaximumSize(textField[0].getPreferredSize());
                textFieldLabels.setLabelFor(passwordField);
                inputPanel.add(passwordField);
            }  else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				textField[i] = new JTextField(10);
                textField[i].setMaximumSize(textField[i].getPreferredSize());
				textFieldLabels.setLabelFor(textField[i]);
				inputPanel.add(textField[i]);
			}
		}

		//Register faculty
		menuButton[0].addActionListener(e -> {
			String name = textField[0].getText();
			String username = textField[1].getText();
			String password = new String((passwordField.getPassword()));
            String id = textField[3].getText();

			boolean success = StudentSystem.registerFaculty(name, id, username, password);

			if (success)
				JOptionPane.showMessageDialog(null, "Faculty Registered.");
			else
				JOptionPane.showMessageDialog(null, "Username already exists.");

		});

		//Return to menu
		menuButton[1].addActionListener(e -> {
			if (user.getAccountType() == 3)
				staffMenu(user);
			else if (user.getAccountType() == 4)
				adminMenu(user);
			registrationFrame.setVisible(false);
		});

		//Log off
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});

		SpringUtilities.makeCompactGrid(inputPanel,
				8, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of registerFaculty


	/**
	 * Allows staff and admin to add courses to the students.
	 *
	 * @param user the current user of the system.
	 */
	// Menu for Staff and Admin
	private static void addClasses1(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Add Classes      ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Student Username", "Class CRN"};
		String[] buttonLabels = {"Add Class", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
		JTextField textField[] = new JTextField[labels.length];
		JLabel fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		for (int i = 0; i <= numLabels; i++) {

			if (i > labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i - (labels.length) - 1] = new JButton(buttonLabels[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i - (labels.length) - 1]);
			} else if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
			} else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				textField[i] = new JTextField(10);
				textField[i].setMaximumSize(textField[i].getPreferredSize());
				textFieldLabels.setLabelFor(textField[i]);
				inputPanel.add(textField[i]);
			}
		}

		//Add class to student
		menuButton[0].addActionListener(e -> {
			String username = textField[0].getText();
			String CRN = textField[1].getText();
			if (StudentSystem.accounts.containsKey(username)) {
                if(StudentSystem.accounts.get(username).getAccountType() == 1) {
                    Student student = (Student) StudentSystem.accounts.get(username);
                    boolean success = student.addCourses(CRN);
                    if (success)
                        JOptionPane.showMessageDialog(null, "Course Added.");
                    else
                        JOptionPane.showMessageDialog(null, "Wrong CRN.");
                } else {
                    JOptionPane.showMessageDialog(null, "User Not A Student");
                }

			} else
				JOptionPane.showMessageDialog(null, "Username not found.");

		});

		//Return to menu
		menuButton[1].addActionListener(e -> {
			if (user.getAccountType() == 3)
				staffMenu(user);
			else if (user.getAccountType() == 4)
				adminMenu(user);
			registrationFrame.setVisible(false);
		});

		//Log off
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});

		SpringUtilities.makeCompactGrid(inputPanel,
				8, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of addClasses1

	/**
	 * Allows students to add their own courses.
	 *
	 * @param currentStudent the student who is currently using the system.
	 */
	// Menu for Students
	private static void addClasses2(Student currentStudent) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Add Classes        ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Class CRN"};
		String[] buttonLabels = {"Add Class", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
		JLabel fillerLabel = new JLabel("");
		JTextField textField = new JTextField(10);
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		for (int i = 0; i < (numLabels + 1); i++) {

			// Puts space between text field and buttons
			if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
			} else if (i > labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i - (labels.length) - 1] = new JButton(buttonLabels[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i - (labels.length) - 1]);
			} else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);

				textField.setMaximumSize(textField.getPreferredSize());
				textFieldLabels.setLabelFor(textField);
				inputPanel.add(textField);
			}
		}

		//Does not work
		menuButton[0].addActionListener(e -> {
			Student student = (Student) currentUser;
			boolean success = student.addCourses(textField.getText());

			if (success)
				JOptionPane.showMessageDialog(null, "Course Added.");
			else
				JOptionPane.showMessageDialog(null, "Wrong CRN.");
		});

		//Return to menu
		menuButton[1].addActionListener(e -> {
			studentMenu(currentStudent);
			registrationFrame.setVisible(false);
		});

		//Log off
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});

		SpringUtilities.makeCompactGrid(inputPanel,
				8, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of addClasses2

	/**
	 * Asks for the ID of the student checking their graduation status.
	 *
	 * @param user the current user of the system.
	 */
	// Menu for Staff and Admin
	private static void gradStatus1(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Graduation Status    ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Student Username"};
		String[] buttonLabels = {"Check Status", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
        JTextField textField[] = new JTextField[1];
        JLabel fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		for (int i = 0; i < (numLabels + 1); i++) {

			// Puts space between text field and buttons
			if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
			} else if (i > labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i - (labels.length) - 1] = new JButton(buttonLabels[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i - (labels.length) - 1]);
			} else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
                textField[0] = new JTextField(10);
				textField[0].setMaximumSize(textField[0].getPreferredSize());
				textFieldLabels.setLabelFor(textField[0]);
				inputPanel.add(textField[0]);
			}
		}

        menuButton[0].addActionListener(e ->{
                String username = textField[0].getText();
                if (StudentSystem.accounts.containsKey(username)) {
                    if (StudentSystem.accounts.get(username).getAccountType() == 1) {
                        displayGraduationStatusPopup((Student) StudentSystem.accounts.get(username));
                    } else {
                        JOptionPane.showMessageDialog(null, "User Not A Student");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Student Not In The System");
                }
        });

		//Return to menu
		menuButton[1].addActionListener(e -> {
			if (user.getAccountType() == 3)
				staffMenu(user);
			else if (user.getAccountType() == 4)
				adminMenu(user);
			registrationFrame.setVisible(false);
		});

		//Log off
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});

		SpringUtilities.makeCompactGrid(inputPanel,
				8, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of gradStatus1


	private static void displayGraduationStatusPopup(Student currentStudent){

        // For the Courses To Be Displayed
        JPanel panel = new JPanel(new SpringLayout());

        boolean gradStatus = currentStudent.getGraduationStatus();

        JLabel textArea;

        if (gradStatus){
            textArea = new JLabel(currentStudent.getName() + " is eligible for graduation!");
        } else {
            textArea = new JLabel(currentStudent.getName() + " is NOT eligible for graduation!");
        }

        textArea.setFont(textArea.getFont().deriveFont(18.0f));    // Bigger Font Size
        textArea.setForeground(Color.BLUE);

        textArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        panel.add(textArea);

        panel.setOpaque(false);
        panel.setBackground(Color.ORANGE);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(
                panel, //parent
                1, 1, 3, 3, //initX, initY
                3, 3 //xPad, yPad
        );

        // For the Window Frame
        JFrame registrationFrame = new JFrame();
        registrationFrame.setLayout(new BorderLayout());
        registrationFrame.getContentPane().setBackground(Color.orange);

        // For the Title Frame
        JPanel pageTitlePanel = new JPanel();                    // For login of software
        pageTitlePanel.setOpaque(false);
        FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
        pageTitlePanel.setLayout(pageTitleLayout);
        JLabel pageTitle = new JLabel("Graduation Status", SwingConstants.RIGHT);
        pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
        pageTitle.setForeground(Color.BLUE);                        // Changes Font Color
        pageTitlePanel.add(pageTitle);

        registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
        registrationFrame.add(panel, BorderLayout.CENTER);
        registrationFrame.setResizable(false);
        registrationFrame.pack();

        registrationFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                popupOpen = false;
            }
        });

        if (!popupOpen) {
            registrationFrame.setVisible(true);
            popupOpen = true;
        } else {
            JOptionPane.showMessageDialog(null, "Close your current popup window.");
            registrationFrame.removeAll();
        }

    }


	/**
	 * Asks faculty to enter the CRN of a course to grade students.
	 */
	// Menu for Faculty
	private static void gradeClass1() {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Grade Class         ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Class CRN"};
		String[] buttonLabels = {"Grade Class", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
		JLabel fillerLabel = new JLabel("");
		JTextField textField = new JTextField(10);
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		for (int i = 0; i < (numLabels + 1); i++) {

			// Puts space between text field and buttons
			if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
			} else if (i > labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i - (labels.length) - 1] = new JButton(buttonLabels[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i - (labels.length) - 1]);
			} else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);

				textField.setMaximumSize(textField.getPreferredSize());
				textFieldLabels.setLabelFor(textField);
				inputPanel.add(textField);
			}
		}

		//Does not work
		menuButton[0].addActionListener(e ->{
			String CRN = textField.getText();
			gradeClass2(CRN);
			registrationFrame.setVisible(false);
		});

		//Return to menu
		menuButton[1].addActionListener(e -> {
			facultyMenu();
			registrationFrame.setVisible(false);
		});

		//Log out
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});

		SpringUtilities.makeCompactGrid(inputPanel,
				8, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of gradeClass1


	/**
	 * Allows faculty to grade the students that
	 * are in the given course.
	 *
	 * @see InterfacePrototype#gradeClass1()
	 */
	// Menu for Faculty after entering CRN
	private static void gradeClass2(String CRN) {

		LinkedList<Student> currentStudent = new LinkedList<>();

		for (Student s : StudentSystem.courses.get(CRN).getStudent()){
			currentStudent.add(s);
		}

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Grade Class         ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Student Name: "};
		String[] passbuttons = {"Pass", "Fail"};
		String[] buttonLabels2 = {"Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels2.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton[] menuButton = new JButton[7];
		
		JLabel nameLabel = new JLabel("N/A");
		if (!currentStudent.isEmpty()){
			nameLabel.setText(currentStudent.get(0).name);    // Student Name goes in here
		}
		
		nameLabel.setBackground(Color.WHITE);
		//JTextField[] textFields = new JTextField[2];
		JLabel fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);

		for (int i = 0; i < (numLabels +1); i++) {

			if (i == labels.length) {
				menuButton[0] = new JButton(passbuttons[0]);
				menuButton[1] = new JButton(passbuttons[1]);

				inputPanel.add(menuButton[0]);
				inputPanel.add(menuButton[1]);

				inputPanel.add(menuButton[1]);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);

			} else if (i > labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i] = new JButton(buttonLabels2[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i]);
			} else if (i < labels.length) {
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				//textFields[i] = new JTextField(10);
				//textFields[i].setMaximumSize(textFields[i].getPreferredSize());
				//textFieldLabels.setLabelFor(textFields[i]);
				inputPanel.add(nameLabel);
			}

		}
		//Pass
		menuButton[0].addActionListener(e ->{
			if(!currentStudent.isEmpty()){
				if(currentStudent.size() == 1){
					nameLabel.setText(currentStudent.get(0).name);
					currentStudent.pop().setClassGrade(CRN, true);
					JOptionPane.showMessageDialog(null, "No more students.");
				}
				else{
					currentStudent.pop().setClassGrade(CRN, true);
					nameLabel.setText(currentStudent.get(0).name);
				}
			} else {
				nameLabel.setText("N/A");
			}
		});
		//Fail
		menuButton[1].addActionListener(e ->{
			if(!currentStudent.isEmpty()){
				if(currentStudent.size() == 1){
					nameLabel.setText(currentStudent.get(0).name);
					currentStudent.pop().setClassGrade(CRN, false);
					JOptionPane.showMessageDialog(null, "No more students.");
				}
				else{
					currentStudent.pop().setClassGrade(CRN, false);
					nameLabel.setText(currentStudent.get(0).name);
				}
			} else {
				nameLabel.setText("N/A");
			}
		});
		//Back to Menu
		menuButton[2].addActionListener(e ->{
			facultyMenu();
			registrationFrame.setVisible(false);
		});
		//Log off
		menuButton[3].addActionListener(e ->{
			logIn();
			registrationFrame.setVisible(false);
		});
		SpringUtilities.makeCompactGrid(inputPanel,
				9, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion

		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo
        registrationFrame.setResizable(false);
		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	} // End of gradeClass2

	/**
	 * Retrieves a forgotten password of the username that is given.
	 *
	 * @param user the user currently using the system.
	 */
	private static void retrievePassword(User user) {

		JFrame registrationFrame = new JFrame();                // Creates window
		registrationFrame.getContentPane().setBackground(Color.orange);
		BorderLayout registrationLayout = new BorderLayout();
		registrationFrame.setLayout(registrationLayout);

		// The following is for the North portion of the BorderLayout
		JPanel pageTitlePanel = new JPanel();
		pageTitlePanel.setOpaque(false);
		FlowLayout pageTitleLayout = new FlowLayout(FlowLayout.TRAILING);
		pageTitlePanel.setLayout(pageTitleLayout);
		JLabel pageTitle = new JLabel("Retrieve Password    ", SwingConstants.RIGHT);
		pageTitle.setFont(pageTitle.getFont().deriveFont(24.0f));    // Bigger Font Size
		pageTitle.setForeground(Color.WHITE);                        // Changes Font Color
		pageTitlePanel.add(pageTitle);

		//The following adds an image in BorderLayout Center

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		try {
			BufferedImage myPicture = ImageIO.read(new File("utepLogo.png"));
			JLabel imageLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(imageLabel);

		} catch (IOException e) {
			JLabel errorMsg = new JLabel("Could not load image.");
			imagePanel.add(errorMsg);
		}

		// End of image

		// The following is for the East portion of the BorderLayout
		String[] labels = {"Username", "Recovered Password"};
		String[] buttonLabels = {"Recover", "Back to menu", "Log off"};
		int numLabels = labels.length + buttonLabels.length;

		JPanel inputPanel = new JPanel(new SpringLayout());
		inputPanel.setOpaque(false);
		JButton menuButton[] = new JButton[buttonLabels.length];
		JTextField textField[] = new JTextField[labels.length];
		JLabel fillerLabel = new JLabel("");  // Several fillerLabels to center the boxes.
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		fillerLabel = new JLabel("");
		inputPanel.add(fillerLabel);
		for (int i = 0; i < numLabels + 1; i++) {

			if (i > labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				menuButton[i - (labels.length) - 1] = new JButton(buttonLabels[i - (labels.length) - 1]);
				inputPanel.add(menuButton[i - (labels.length) - 1]);
			} else if (i == labels.length) {
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
				fillerLabel = new JLabel("");
				inputPanel.add(fillerLabel);
			} else if (i < labels.length) {
				//This block of code puts a textField
				JLabel textFieldLabels = new JLabel(labels[i], JLabel.TRAILING);
				textFieldLabels.setFont(pageTitle.getFont().deriveFont(16.0f));    // Bigger Font Size
				textFieldLabels.setForeground(Color.WHITE);
				inputPanel.add(textFieldLabels);
				textField[i] = new JTextField(10);
				textField[i].setMaximumSize(textField[i].getPreferredSize());
				textFieldLabels.setLabelFor(textField[i]);
				inputPanel.add(textField[i]);
			}
		}

		//Gets username from field and writes password in other text field
		menuButton[0].addActionListener(e -> {
			String username = textField[0].getText();
			if (StudentSystem.accounts.containsKey(username)) {
				textField[1].setText(StudentSystem.retrievePassword(username));
			} else
				JOptionPane.showMessageDialog(null, "Username does not exist.");

		});

		//Return to menu
		menuButton[1].addActionListener(e -> {
			if (user.getAccountType() == 3)
				staffMenu(user);
			else if (user.getAccountType() == 4)
				adminMenu(user);
			registrationFrame.setVisible(false);
		});

		//Log out
		menuButton[2].addActionListener(e -> {
			logIn();
			registrationFrame.setVisible(false);
		});

		SpringUtilities.makeCompactGrid(inputPanel,
				10, 2,          //rows, columns
				6, 6,          //initX, initY
				10, 10);         //xPad yPad

		// End of the East portion


		registrationFrame.add(pageTitlePanel, BorderLayout.NORTH);
		registrationFrame.add(inputPanel, BorderLayout.EAST);
		registrationFrame.add(imagePanel, BorderLayout.WEST);  // Puts UTEP logo

		registrationFrame.pack();
		registrationFrame.setVisible(true);                                   // Shows window
		registrationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Ends program when window closes

	}  // End of retrievePassword
}
