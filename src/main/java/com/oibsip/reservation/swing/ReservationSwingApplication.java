package com.oibsip.reservation.swing;

import javax.swing.*;
import java.awt.*;

public class ReservationSwingApplication {

    private static final String DEMO_USERNAME = "admin";
    private static final String DEMO_PASSWORD = "admin123";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                ReservationSwingApplication::showLoginScreen
        );
    }

    private static void showLoginScreen() {
        Color navy = new Color(12, 39, 76);
        Color blue = new Color(29, 103, 190);
        Color pale = new Color(241, 246, 252);
        Color muted = new Color(105, 121, 142);

        JFrame frame = new JFrame("RailConnect | Online Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 620);
        frame.setMinimumSize(new Dimension(900, 580));
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);

        JPanel imagePanel = new JPanel() {
            private Image trainImage;

            {
                java.net.URL imageUrl = getClass().getResource("/images/train-banner.jpg");
                if (imageUrl != null) {
                    trainImage = new ImageIcon(imageUrl).getImage();
                }
            }

            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);

                Graphics2D g2 = (Graphics2D) graphics.create();
                int w = getWidth();
                int h = getHeight();

                if (trainImage != null) {
                    g2.setRenderingHint(
                            RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR
                    );

                    double scale = Math.max(
                            (double) w / trainImage.getWidth(this),
                            (double) h / trainImage.getHeight(this)
                    );

                    int imageW = (int) (trainImage.getWidth(this) * scale);
                    int imageH = (int) (trainImage.getHeight(this) * scale);

                    g2.drawImage(
                            trainImage,
                            (w - imageW) / 2,
                            (h - imageH) / 2,
                            imageW,
                            imageH,
                            this
                    );
                } else {
                    g2.setColor(navy);
                    g2.fillRect(0, 0, w, h);
                }

                g2.setPaint(new GradientPaint(
                        0, 0, new Color(5, 25, 52, 225),
                        0, h, new Color(5, 25, 52, 55)
                ));
                g2.fillRect(0, 0, w, h);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("SansSerif", Font.BOLD, 14));
                g2.drawString("RAILCONNECT", 34, 45);

                g2.setFont(new Font("SansSerif", Font.BOLD, 31));
                g2.drawString("Your journey,", 34, h - 125);
                g2.drawString("our priority.", 34, h - 85);

                g2.setFont(new Font("SansSerif", Font.PLAIN, 15));
                g2.drawString("Simple and convenient train reservation management.", 34, h - 52);

                g2.dispose();
            }
        };

        imagePanel.setPreferredSize(new Dimension(530, 620));

        JPanel formArea = new JPanel(new GridBagLayout());
        formArea.setBackground(pale);
        formArea.setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 232, 241)),
                BorderFactory.createEmptyBorder(36, 30, 32, 30)
        ));
        card.setPreferredSize(new Dimension(350, 390));

        JLabel logo = new JLabel("RAILCONNECT");
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        logo.setForeground(blue);
        logo.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel title = new JLabel("Welcome back");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(navy);
        title.setFont(new Font("SansSerif", Font.BOLD, 27));

        JLabel subtitle = new JLabel("Sign in to continue");
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setForeground(muted);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        userLabel.setForeground(navy);

        JTextField usernameField = new JTextField();
        styleTextField(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        passLabel.setForeground(navy);

        JPasswordField passwordField = new JPasswordField();
        styleTextField(passwordField);

        JButton loginButton = new JButton("SIGN IN");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        loginButton.setPreferredSize(new Dimension(280, 46));
        loginButton.setBackground(blue);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel demo = new JLabel("Demo: admin / admin123");
        demo.setAlignmentX(Component.LEFT_ALIGNMENT);
        demo.setForeground(muted);
        demo.setFont(new Font("SansSerif", Font.PLAIN, 12));

        card.add(logo);
        card.add(Box.createVerticalStrut(17));
        card.add(title);
        card.add(Box.createVerticalStrut(7));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(30));
        card.add(userLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(usernameField);
        card.add(Box.createVerticalStrut(18));
        card.add(passLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(24));
        card.add(loginButton);
        card.add(Box.createVerticalStrut(20));
        card.add(demo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        formArea.add(card, gbc);

        root.add(imagePanel, BorderLayout.CENTER);
        root.add(formArea, BorderLayout.EAST);

        loginButton.addActionListener(event -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (DEMO_USERNAME.equals(username)
                    && DEMO_PASSWORD.equals(password)) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Login successful!",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE
                );

                frame.dispose();
                showDashboard();
            } else {
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid username or password. Please try again.",
                        "Access Denied",
                        JOptionPane.ERROR_MESSAGE
                );
                passwordField.setText("");
                passwordField.requestFocusInWindow();
            }
        });

        passwordField.addActionListener(event -> loginButton.doClick());

        frame.setContentPane(root);
        frame.getRootPane().setDefaultButton(loginButton);
        frame.setVisible(true);
        usernameField.requestFocusInWindow();
    }

    private static void styleTextField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(280, 42));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 216, 231)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private static void showDashboard() {
        Color navy = new Color(12, 39, 76);
        Color blue = new Color(29, 103, 190);
        Color pale = new Color(241, 246, 252);
        Color textColor = new Color(42, 57, 78);

        JFrame frame = new JFrame("RailConnect | Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 650);
        frame.setMinimumSize(new Dimension(820, 580));
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(pale);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(BorderFactory.createEmptyBorder(22, 32, 22, 32));

        JPanel brand = new JPanel();
        brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));

        JLabel brandTitle = new JLabel("RAILCONNECT");
        brandTitle.setForeground(Color.WHITE);
        brandTitle.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel brandSubtitle = new JLabel("ONLINE TRAIN RESERVATION");
        brandSubtitle.setForeground(new Color(205, 224, 247));
        brandSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));

        brand.add(brandTitle);
        brand.add(Box.createVerticalStrut(4));
        brand.add(brandSubtitle);
        header.add(brand, BorderLayout.WEST);

        JLabel status = new JLabel("RESERVATION MANAGEMENT");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.add(status, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(15, 25));
        content.setBackground(pale);
        content.setBorder(BorderFactory.createEmptyBorder(30, 35, 25, 35));

        JPanel welcomePanel = new JPanel();
        welcomePanel.setOpaque(false);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("Welcome aboard!");
        welcome.setForeground(navy);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 29));
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "What would you like to do today?"
        );
        subtitle.setForeground(new Color(105, 121, 142));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        welcomePanel.add(welcome);
        welcomePanel.add(Box.createVerticalStrut(7));
        welcomePanel.add(subtitle);

        content.add(welcomePanel, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2, 2, 18, 18));
        cards.setOpaque(false);

        JButton bookButton = createDashboardButton(
                "01",
                "Book Reservation",
                "Enter passenger and journey details",
                blue
        );

        JButton searchButton = createDashboardButton(
                "02",
                "Find Reservation",
                "View a booking using its PNR",
                navy
        );

        JButton cancelButton = createDashboardButton(
                "03",
                "Cancel Reservation",
                "Retrieve and cancel an existing booking",
                navy
        );

        JButton logoutButton = createDashboardButton(
                "04",
                "Logout",
                "Exit your current session",
                new Color(86, 105, 130)
        );

        cards.add(bookButton);
        cards.add(searchButton);
        cards.add(cancelButton);
        cards.add(logoutButton);

        content.add(cards, BorderLayout.CENTER);

        JLabel footer = new JLabel(
                "RAILCONNECT  |  Train reservation management"
        );
        footer.setForeground(new Color(115, 130, 148));
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        content.add(footer, BorderLayout.SOUTH);
        root.add(content, BorderLayout.CENTER);

        bookButton.addActionListener(event -> showBookingForm(frame));
        searchButton.addActionListener(event -> showSearchForm(frame));
        cancelButton.addActionListener(event -> showCancellationForm(frame));

        logoutButton.addActionListener(event -> {
            frame.dispose();
            showLoginScreen();
        });

        frame.setContentPane(root);
        frame.setVisible(true);
    }

    private static JButton createDashboardButton(
            String number,
            String title,
            String description,
            Color accent
    ) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(12, 8));
        button.setBackground(Color.WHITE);
        button.setForeground(accent);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 230, 242)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        numberLabel.setForeground(accent);
        numberLabel.setVerticalAlignment(SwingConstants.TOP);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(new Color(25, 43, 67));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descriptionLabel = new JLabel(
                "<html><div style='width:210px'>"
                        + description
                        + "</div></html>"
        );
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descriptionLabel.setForeground(new Color(105, 121, 142));
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(9));
        textPanel.add(descriptionLabel);

        button.add(numberLabel, BorderLayout.WEST);
        button.add(textPanel, BorderLayout.CENTER);

        return button;
    }
    private static void showBookingForm(JFrame dashboard) {
        Color navy = new Color(12, 39, 76);
        Color blue = new Color(29, 103, 190);
        Color pale = new Color(241, 246, 252);

        JDialog dialog = new JDialog(dashboard, "RailConnect | Book Reservation", true);
        dialog.setSize(760, 700);
        dialog.setMinimumSize(new Dimension(700, 650));
        dialog.setLocationRelativeTo(dashboard);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(pale);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JLabel title = new JLabel("Book a train reservation");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 23));

        JLabel subtitle = new JLabel("Enter passenger and journey details below");
        subtitle.setForeground(new Color(205, 224, 247));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.add(title);
        heading.add(Box.createVerticalStrut(5));
        heading.add(subtitle);
        header.add(heading, BorderLayout.WEST);
        root.add(header, BorderLayout.NORTH);

        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(pale);
        background.setBorder(BorderFactory.createEmptyBorder(22, 28, 22, 28));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 230, 242)),
                BorderFactory.createEmptyBorder(22, 25, 22, 25)
        ));

        JTextField passengerField = new JTextField();
        JTextField trainNumberField = new JTextField();
        JTextField trainNameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField sourceField = new JTextField();
        JTextField destinationField = new JTextField();

        styleTextField(passengerField);
        styleTextField(trainNumberField);
        styleTextField(trainNameField);
        styleTextField(dateField);
        styleTextField(sourceField);
        styleTextField(destinationField);

        trainNameField.setEditable(false);
        trainNameField.setBackground(new Color(243, 247, 252));

        JComboBox<String> classBox = new JComboBox<>(
                new String[]{"Sleeper", "AC 3 Tier", "AC 2 Tier", "AC First Class"}
        );
        classBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        classBox.setBackground(Color.WHITE);
        classBox.setPreferredSize(new Dimension(250, 42));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;

        addBookingField(form, gbc, 0, "Passenger name", passengerField);
        addBookingField(form, gbc, 1, "Train number", trainNumberField);
        addBookingField(form, gbc, 2, "Train name (auto-filled)", trainNameField);
        addBookingField(form, gbc, 3, "Travel date (YYYY-MM-DD)", dateField);
        addBookingField(form, gbc, 4, "Source station", sourceField);
        addBookingField(form, gbc, 5, "Destination station", destinationField);
        addBookingField(form, gbc, 6, "Travel class", classBox);

        JButton closeButton = new JButton("Back");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        closeButton.setPreferredSize(new Dimension(105, 42));

        JButton bookButton = new JButton("CONFIRM BOOKING");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        bookButton.setPreferredSize(new Dimension(190, 42));
        bookButton.setBackground(blue);
        bookButton.setForeground(Color.WHITE);
        bookButton.setOpaque(true);
        bookButton.setBorderPainted(false);
        bookButton.setFocusPainted(false);
        bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        buttons.setOpaque(false);
        buttons.add(closeButton);
        buttons.add(bookButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(20, 8, 4, 8);
        form.add(buttons, gbc);

        JLabel note = new JLabel("Supported trains: 16591, 12627, 12007 and 12649");
        note.setFont(new Font("SansSerif", Font.PLAIN, 11));
        note.setForeground(new Color(105, 121, 142));
        note.setBorder(BorderFactory.createEmptyBorder(12, 2, 0, 2));

        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setOpaque(false);
        formContainer.add(form, BorderLayout.CENTER);
        formContainer.add(note, BorderLayout.SOUTH);

        background.add(formContainer, BorderLayout.CENTER);
        root.add(background, BorderLayout.CENTER);

        trainNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent event) {
                String number = trainNumberField.getText().trim();
                switch (number) {
                    case "16591" -> trainNameField.setText("Hampi Express");
                    case "12627" -> trainNameField.setText("Karnataka Express");
                    case "12007" -> trainNameField.setText("Shatabdi Express");
                    case "12649" -> trainNameField.setText("Sampark Kranti Express");
                    default -> trainNameField.setText("");
                }
            }
        });

        bookButton.addActionListener(event -> {
            try {
                String passenger = passengerField.getText().trim();
                String trainNumber = trainNumberField.getText().trim();
                String trainName = trainNameField.getText().trim();
                String dateText = dateField.getText().trim();
                String source = sourceField.getText().trim();
                String destination = destinationField.getText().trim();
                String travelClass = (String) classBox.getSelectedItem();

                if (passenger.isEmpty() || trainNumber.isEmpty()
                        || dateText.isEmpty() || source.isEmpty()
                        || destination.isEmpty()) {
                    throw new IllegalArgumentException("Please fill in all required fields.");
                }

                if (!trainNumber.matches("\\d+")) {
                    throw new IllegalArgumentException("Train number must contain digits only.");
                }

                if (trainName.isEmpty()) {
                    throw new IllegalArgumentException(
                            "Unsupported train number. Try 16591, 12627, 12007, or 12649."
                    );
                }

                java.time.LocalDate travelDate = java.time.LocalDate.parse(dateText);

                com.oibsip.reservation.service.ReservationBookingService service =
                        new com.oibsip.reservation.service.ReservationBookingService();

                String pnr = service.bookReservation(
                        passenger, trainNumber, trainName, travelDate,
                        source, destination, travelClass
                );

                JOptionPane.showMessageDialog(
                        dialog,
                        "Reservation booked successfully!\n\n"
                                + "PNR: " + pnr + "\n"
                                + "Passenger: " + passenger + "\n"
                                + "Train: " + trainName + " (" + trainNumber + ")\n"
                                + "Travel date: " + travelDate + "\n"
                                + "Route: " + source + " to " + destination + "\n"
                                + "Class: " + travelClass,
                        "Booking Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dialog.dispose();

            } catch (java.time.format.DateTimeParseException exception) {
                JOptionPane.showMessageDialog(
                        dialog,
                        "Invalid date. Use YYYY-MM-DD, for example 2026-11-05.",
                        "Invalid Date",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(
                        dialog, exception.getMessage(),
                        "Invalid Input", JOptionPane.ERROR_MESSAGE
                );
            } catch (java.sql.SQLException exception) {
                JOptionPane.showMessageDialog(
                        dialog,
                        "Database error. Check MySQL and your database password configuration.",
                        "Database Error", JOptionPane.ERROR_MESSAGE
                );
                exception.printStackTrace();
            }
        });

        closeButton.addActionListener(event -> dialog.dispose());

        dialog.setContentPane(root);
        dialog.getRootPane().setDefaultButton(bookButton);
        dialog.setVisible(true);
    }

    private static void addBookingField(
            JPanel form, GridBagConstraints gbc, int row,
            String labelText, JComponent input
    ) {
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(7, 8, 7, 14);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(new Color(42, 57, 78));
        form.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.2;
        gbc.insets = new Insets(7, 8, 7, 8);
        form.add(input, gbc);
    }


    private static void showSearchForm(JFrame dashboard) {
        Color navy = new Color(12, 39, 76);
        Color blue = new Color(29, 103, 190);
        Color pale = new Color(241, 246, 252);
        Color muted = new Color(105, 121, 142);

        JDialog dialog = new JDialog(dashboard, "RailConnect | Find Reservation", true);
        dialog.setSize(650, 560);
        dialog.setMinimumSize(new Dimension(600, 520));
        dialog.setLocationRelativeTo(dashboard);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(pale);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(navy);
        header.setBorder(BorderFactory.createEmptyBorder(22, 28, 22, 28));
        JLabel title = new JLabel("Find your reservation");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 23));
        JLabel subtitle = new JLabel("Enter your PNR to view the complete booking details");
        subtitle.setForeground(new Color(205, 224, 247));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        header.add(title);
        header.add(Box.createVerticalStrut(6));
        header.add(subtitle);
        root.add(header, BorderLayout.NORTH);

        JPanel area = new JPanel(new BorderLayout(0, 16));
        area.setBackground(pale);
        area.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 230, 242)),
                BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 8, 0);
        JLabel fieldLabel = new JLabel("Reservation PNR");
        fieldLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        fieldLabel.setForeground(navy);
        card.add(fieldLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 14, 0);
        JTextField pnrField = new JTextField();
        styleTextField(pnrField);
        pnrField.setToolTipText("Enter the PNR shown on your booking confirmation");
        card.add(pnrField, gbc);

        JLabel hint = new JLabel("Your PNR is printed on the booking confirmation.");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(muted);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 18, 0);
        card.add(hint, gbc);

        JTextArea details = new JTextArea();
        details.setEditable(false);
        details.setLineWrap(true);
        details.setWrapStyleWord(true);
        details.setFont(new Font("SansSerif", Font.PLAIN, 14));
        details.setForeground(new Color(42, 57, 78));
        details.setBackground(new Color(247, 250, 254));
        details.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        details.setText("Reservation details will appear here after you search.");
        details.setRows(7);
        JScrollPane detailsScroll = new JScrollPane(details);
        detailsScroll.setBorder(BorderFactory.createLineBorder(new Color(225, 232, 241)));
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        card.add(detailsScroll, gbc);

        JButton searchButton = new JButton("FIND RESERVATION");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        searchButton.setPreferredSize(new Dimension(190, 42));
        searchButton.setBackground(blue);
        searchButton.setForeground(Color.WHITE);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton closeButton = new JButton("Back to dashboard");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        closeButton.setPreferredSize(new Dimension(160, 42));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);
        buttons.add(closeButton);
        buttons.add(searchButton);

        area.add(card, BorderLayout.CENTER);
        area.add(buttons, BorderLayout.SOUTH);
        root.add(area, BorderLayout.CENTER);

        searchButton.addActionListener(event -> {
            String pnr = pnrField.getText().trim().toUpperCase();
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a PNR.",
                        "Required Field", JOptionPane.WARNING_MESSAGE);
                pnrField.requestFocusInWindow();
                return;
            }
            try {
                com.oibsip.reservation.dao.ReservationDAO dao =
                        new com.oibsip.reservation.dao.ReservationDAO();
                com.oibsip.reservation.model.Reservation reservation = dao.findByPnr(pnr);
                if (reservation == null) {
                    details.setText("No reservation was found for PNR: " + pnr
                            + "\n\nCheck the PNR and try again.");
                    JOptionPane.showMessageDialog(dialog, "No reservation found for PNR: " + pnr,
                            "Not Found", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                details.setText("RESERVATION CONFIRMED\n\n"
                        + "PNR: " + reservation.getPnr() + "\n"
                        + "Passenger: " + reservation.getPassengerName() + "\n"
                        + "Train: " + reservation.getTrainName() + "\n"
                        + "Train number: " + reservation.getTrainNumber() + "\n"
                        + "Travel date: " + reservation.getTravelDate() + "\n"
                        + "Source: " + reservation.getSource() + "\n"
                        + "Destination: " + reservation.getDestination() + "\n"
                        + "Travel class: " + reservation.getTravelClass());
                details.setCaretPosition(0);
            } catch (java.sql.SQLException exception) {
                JOptionPane.showMessageDialog(dialog,
                        "Could not retrieve reservation. Check MySQL and database configuration.",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        pnrField.addActionListener(event -> searchButton.doClick());
        closeButton.addActionListener(event -> dialog.dispose());
        dialog.setContentPane(root);
        dialog.getRootPane().setDefaultButton(searchButton);
        dialog.setVisible(true);
    }

    private static void showCancellationForm(JFrame dashboard) {
        Color navy = new Color(12, 39, 76);
        Color blue = new Color(29, 103, 190);
        Color pale = new Color(241, 246, 252);
        Color muted = new Color(105, 121, 142);

        JDialog dialog = new JDialog(dashboard, "RailConnect | Cancel Reservation", true);
        dialog.setSize(650, 560);
        dialog.setMinimumSize(new Dimension(600, 520));
        dialog.setLocationRelativeTo(dashboard);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(pale);
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(navy);
        header.setBorder(BorderFactory.createEmptyBorder(22, 28, 22, 28));
        JLabel title = new JLabel("Cancel a reservation");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 23));
        JLabel subtitle = new JLabel("Retrieve the booking and confirm before cancellation");
        subtitle.setForeground(new Color(205, 224, 247));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        header.add(title);
        header.add(Box.createVerticalStrut(6));
        header.add(subtitle);
        root.add(header, BorderLayout.NORTH);

        JPanel area = new JPanel(new BorderLayout(0, 16));
        area.setBackground(pale);
        area.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 230, 242)),
                BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 8, 0);
        JLabel fieldLabel = new JLabel("Reservation PNR");
        fieldLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        fieldLabel.setForeground(navy);
        card.add(fieldLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 14, 0);
        JTextField pnrField = new JTextField();
        styleTextField(pnrField);
        pnrField.setToolTipText("Enter the PNR shown on your booking confirmation");
        card.add(pnrField, gbc);

        JLabel hint = new JLabel("You will be asked to confirm before the booking is deleted.");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(muted);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 18, 0);
        card.add(hint, gbc);

        JTextArea details = new JTextArea();
        details.setEditable(false);
        details.setLineWrap(true);
        details.setWrapStyleWord(true);
        details.setFont(new Font("SansSerif", Font.PLAIN, 14));
        details.setForeground(new Color(42, 57, 78));
        details.setBackground(new Color(247, 250, 254));
        details.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        details.setText("Enter a PNR and choose Fetch Reservation to review the booking.");
        details.setRows(7);
        JScrollPane detailsScroll = new JScrollPane(details);
        detailsScroll.setBorder(BorderFactory.createLineBorder(new Color(225, 232, 241)));
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        card.add(detailsScroll, gbc);

        JButton fetchButton = new JButton("FETCH RESERVATION");
        fetchButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        fetchButton.setPreferredSize(new Dimension(190, 42));
        fetchButton.setBackground(blue);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setOpaque(true);
        fetchButton.setBorderPainted(false);
        fetchButton.setFocusPainted(false);
        fetchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton closeButton = new JButton("Back to dashboard");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        closeButton.setPreferredSize(new Dimension(160, 42));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);
        buttons.add(closeButton);
        buttons.add(fetchButton);

        area.add(card, BorderLayout.CENTER);
        area.add(buttons, BorderLayout.SOUTH);
        root.add(area, BorderLayout.CENTER);

        fetchButton.addActionListener(event -> {
            String pnr = pnrField.getText().trim().toUpperCase();
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a PNR.",
                        "Required Field", JOptionPane.WARNING_MESSAGE);
                pnrField.requestFocusInWindow();
                return;
            }
            try {
                com.oibsip.reservation.dao.ReservationDAO dao =
                        new com.oibsip.reservation.dao.ReservationDAO();
                com.oibsip.reservation.model.Reservation reservation = dao.findByPnr(pnr);
                if (reservation == null) {
                    details.setText("No reservation was found for PNR: " + pnr
                            + "\n\nCheck the PNR and try again.");
                    JOptionPane.showMessageDialog(dialog, "No reservation found for PNR: " + pnr,
                            "Not Found", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                details.setText("RESERVATION TO BE CANCELLED\n\n"
                        + "PNR: " + reservation.getPnr() + "\n"
                        + "Passenger: " + reservation.getPassengerName() + "\n"
                        + "Train: " + reservation.getTrainName() + "\n"
                        + "Train number: " + reservation.getTrainNumber() + "\n"
                        + "Travel date: " + reservation.getTravelDate() + "\n"
                        + "Source: " + reservation.getSource() + "\n"
                        + "Destination: " + reservation.getDestination() + "\n"
                        + "Travel class: " + reservation.getTravelClass());
                details.setCaretPosition(0);

                int choice = JOptionPane.showConfirmDialog(dialog,
                        "Review the reservation details.\n\nAre you sure you want to cancel PNR "
                                + pnr + "? This action cannot be undone.",
                        "Confirm Cancellation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (choice != JOptionPane.YES_OPTION) {
                    details.append("\n\nCancellation not performed. The reservation remains active.");
                    return;
                }
                boolean cancelled = dao.cancelByPnr(pnr);
                if (cancelled) {
                    JOptionPane.showMessageDialog(dialog,
                            "Reservation " + pnr + " cancelled successfully.",
                            "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog,
                            "The reservation could not be cancelled. It may no longer exist.",
                            "Cancellation Failed", JOptionPane.WARNING_MESSAGE);
                }
            } catch (java.sql.SQLException exception) {
                JOptionPane.showMessageDialog(dialog,
                        "Could not process cancellation. Check MySQL and database configuration.",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        pnrField.addActionListener(event -> fetchButton.doClick());
        closeButton.addActionListener(event -> dialog.dispose());
        dialog.setContentPane(root);
        dialog.getRootPane().setDefaultButton(fetchButton);
        dialog.setVisible(true);
    }
}
