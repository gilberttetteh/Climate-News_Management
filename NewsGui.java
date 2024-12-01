import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.ParseException;

public class NewsGui extends JFrame {
    private ClimateNews newsPiece;   //creates an instance of climate news to to manage news articles
    private JTextField titleField, contentField, dateField, keywordField, articleNameField;  //input fields for the gui
    private JTextArea outputArea;  //area for diplaying the article

    public NewsGui() {
        newsPiece = new ClimateNews();  //climate news instance
        setTitle("Climate News Management System");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));  

        JTabbedPane tabbedPane = new JTabbedPane();   // Create a tabbed pane
        tabbedPane.addTab("Add Article", createAddArticlePanel());   // Add "Add Article" tab
        tabbedPane.addTab("Search", createSearchPanel());  //adds a search tab
        tabbedPane.addTab("Manage Articles", createManageArticlesPanel());  // Add "Manage Articles" tab

        // Output area for displaying meassages
        outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 14)); //sets the font
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);  //adds a scroll bar
        scrollPane.setBorder(new CompoundBorder(
                new EmptyBorder(10, 20, 20, 20),
                new LineBorder(Color.GRAY)
        ));

        // Add components to frame
        add(tabbedPane, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private JPanel createAddArticlePanel() {   //method to create a panel for adding the article
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));  //sets the padding and background
        panel.setBackground(new Color(230, 230, 230));

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());  // Use GridBagLayout for input panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        addLabelAndTextField(inputPanel, "Title:", titleField = createTextField(), gbc);

        // Add content label
        JLabel contentLabel = new JLabel("Content:");
        contentLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridy++;
        inputPanel.add(contentLabel, gbc);

        // Add content text area
        JTextArea contentArea = createMultilineTextArea();
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set the height of the content area to be 4 times the height of the title field
        Dimension textFieldSize = titleField.getPreferredSize();
        Dimension contentSize = new Dimension(textFieldSize.width, textFieldSize.height * 3);
        contentScrollPane.setPreferredSize(contentSize);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;  // Allow vertical expansion
        inputPanel.add(contentScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        addLabelAndTextField(inputPanel, "Date (dd-MM-yyyy):", dateField = createTextField(), gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(220, 220, 220));
        addButton(buttonPanel, "Add Article", e -> addArticle(contentArea));
        addButton(buttonPanel, "Sort by Date", e -> sortByDate());
        addButton(buttonPanel, "Display Summary", e -> displaySummary());

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSearchPanel() {   // Method to create the "Search" panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));   // Set padding around the panel
        panel.setBackground(new Color(230, 230, 230));  // Set background color

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));  // FlowLayout for search panel

        JLabel keywordLabel = new JLabel("Keyword:");
        keywordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchPanel.add(keywordLabel);

        keywordField = createTextField();   // Create keyword text field
        keywordField.setPreferredSize(new Dimension(200, 30)); // Set fixed width of 200 pixels
        searchPanel.add(keywordField);

        addButton(searchPanel, "Search Keyword", e -> searchKeyword());  // Add search button

        panel.add(searchPanel, BorderLayout.NORTH);

        return panel;
    }
    private JPanel createManageArticlesPanel() {     // Method to create the "Manage Articles" panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));  // Set padding around the panel
        panel.setBackground(new Color(230, 230, 230));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));  // FlowLayout for input panel

        JLabel articleNameLabel = new JLabel("Article Title:");
        articleNameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        inputPanel.add(articleNameLabel);

        articleNameField = createTextField();  // Create article title text field
        articleNameField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(articleNameField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));  // Button panel for Delete Article and Display Full Article buttons
        buttonPanel.setBackground(new Color(220, 220, 220));
        addButton(buttonPanel, "Delete Article", e -> deleteArticle());
        addButton(buttonPanel, "Display Full Article", e -> displayFullArticle());

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createTextField() {  // Method to create a standard JTextField
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(() -> {
                    field.selectAll();   // Select all text when the field gains focus
                });
            }
        });
        return field;
    }

    private JTextArea createMultilineTextArea() {   // Method to create a JTextArea for multiline input
        JTextArea area = new JTextArea();
        area.setFont(new Font("SansSerif", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return area;
    }

    private void addLabelAndTextField(JPanel panel, String labelText, JTextField field, GridBagConstraints gbc) {   // Method to add a label and text field to a panel with specified GridBagConstraints
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(label, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(field, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {  // // Method to add a button with specified text and action listener to a panel
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addArticle(JTextArea contentArea) {  // Method to handle adding an article
        String title = titleField.getText();
        String content = contentArea.getText();
        String date = dateField.getText();
        if (title.isEmpty() || content.isEmpty()) {
            outputArea.setText("Title and/or Content cannot be empty.");
            return;
        }
        try {
            newsPiece.addArticle(title, content, date);
            outputArea.setText("Article added successfully.");
            clearInputFields(contentArea);    // Clear input fields after adding the article
        } catch (ParseException e) {
            outputArea.setText("Invalid date format. Please try again.");
        } catch (IllegalArgumentException e) {
            outputArea.setText(e.getMessage());
        }
    }

    private void searchKeyword() {   // Method to handle searching articles by keyword
        String keyword = keywordField.getText();
        String result = newsPiece.searchByKeyword(keyword);
        outputArea.setText(result);
    }

    private void sortByDate() {  // Method to handle sorting articles by date
        String result = newsPiece.sortByDate();
        outputArea.setText(result);
    }

    private void displaySummary() {  // Method to handle displaying summary of all articles
        String summary = newsPiece.displaySummaries();
        outputArea.setText(summary);
    }

    private void deleteArticle() {   // Method to handle deleting an article by title
        String title = articleNameField.getText();
        if (title.isEmpty()) {
            outputArea.setText("Please enter an article title.");
            return;
        }
        boolean deleted = newsPiece.deleteArticle(title);
        if (deleted) {
            outputArea.setText("Article '" + title + "' has been deleted.");
        } else {
            outputArea.setText("Article '" + title + "' not found.");
        }
    }

    private void displayFullArticle() {   // Method to handle displaying the full content of an article by title
        String title = articleNameField.getText();
        if (title.isEmpty()) {
            outputArea.setText("Please enter an article title.");
            return;
        }
        String articleContent = newsPiece.displayFullArticle(title);
        outputArea.setText(articleContent);
    }

    private void clearInputFields(JTextArea contentArea) {   // Method to clear input fields after adding an article
        titleField.setText("");
        contentArea.setText("");
        dateField.setText("");
    }
}