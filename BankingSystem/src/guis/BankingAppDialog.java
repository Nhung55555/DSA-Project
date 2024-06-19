package guis;

import javax.swing.*;
import javax.swing.text.*;

import db_objs.MyJDBC;
import db_objs.Transaction;
import db_objs.User;
import java.util.Queue;
import java.util.LinkedList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import mainmain.ConnectionListener;

//import java.util.LinkedList;
//import java.util.Map;
//import java.util.Stack;
//import java.util.concurrent.ThreadLocalRandom;

import static db_objs.MyJDBC.getUsernameByCardNumber;

/*
 Displays a custom dialog for our BankingAppGui
 */
public class BankingAppDialog extends JDialog implements ActionListener {
//    private Stack<String> undoStack = new Stack<>();
    private Queue<String> undoQueue = new LinkedList<>();

    private User user;
    private BankingAppGui bankingAppGui;
    private JLabel balanceLabel, enterAmountLabel,
            enterUserLabel, enterCardNumberLabel,
            currentBalanceLabel, usernameLabel =null;
    private JTextField enterAmountField, enterUserField, enterCardNumberField;
    private JButton actionButton,checkbutton, undoButton=null;
    private JPanel pastTransactionPanel;
    private String currentUsername = null;
    private String currentCardNumber = null;

    public BankingAppDialog(BankingAppGui bankingAppGui, User user) {
        // set the size for the GUI in the total GUI
        setSize(420, 600);

        // add focus to the dialog (can't interact with anything else until dialog is closed)
        setModal(true);

        // loads in teh center of our banking gui
        setLocationRelativeTo(bankingAppGui);

        // when suer closes dialog, it releases its resources that are being used
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // prevents dialog from being resized
        setResizable(false);

        // allows us to manually specify the size and position of each component
        setLayout(null);

        // we will need reference to our gui so that we can update the current balance
        this.bankingAppGui = bankingAppGui;

        // we will need access to the user info to make updates to our db or retrieve data about the user
        this.user = user;
    }
//Stack

//    public void addcheck() {
//        checkbutton = new JButton("check");
//        checkbutton.setBounds(15, 350, getWidth() - 50, 40);
//        checkbutton.setFont(new Font("Dialog", Font.BOLD, 20));
//        checkbutton.addActionListener(e -> {
//            String cardNumber = enterCardNumberField.getText();
//
//            // Check if the username is different from the current one
//            if (!cardNumber.equals(currentCardNumber)) {
//                // Remove the existing username label and undo button, if any
//                if (usernameLabel != null) {
//                    remove(usernameLabel);
////                    remove(undoButton);
//                    undoStack.push(currentCardNumber);
//                    usernameLabel = null;
////                    undoButton = null;
//                }
//                String username = getUsernameByCardNumber(cardNumber);
//
//                // Display the username in a label
//                usernameLabel = new JLabel("Username: " + username);
//                usernameLabel.setBounds(20, 220, getWidth() - 50, 40);
//                usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
//                add(usernameLabel);
//
//                // Create the "Undo" button
//                undoButton = new JButton("Undo");
//                undoButton.setBounds(15, 410, getWidth() - 50, 40);
//                undoButton.setFont(new Font("Dialog", Font.BOLD, 20));
//                undoButton.addActionListener(e2 -> {
//                    // Remove the username label and undo button
//                    remove(usernameLabel);
////                    remove(undoButton);
//                    usernameLabel = null;
////                    undoButton = null;
//                    // Restore the previous card number, if any
//                    if (!undoStack.isEmpty()) {
//                        currentCardNumber = undoStack.pop();
//                    } else {
//                        currentCardNumber = null;
//                    }
//                    revalidate();
//                    repaint();
//                });
//                add(undoButton);
//                currentCardNumber = cardNumber;
//                currentUsername = username;
//            }
//            revalidate();
//            repaint();
//        });
//        add(checkbutton);
//    }

//queue
//    public void addcheck() {
//        checkbutton = new JButton("check");
//        checkbutton.setBounds(15, 350, getWidth() - 50, 40);
//        checkbutton.setFont(new Font("Dialog", Font.BOLD, 20));
//        checkbutton.addActionListener(e -> {
//            String cardNumber = enterCardNumberField.getText();
//
//            // Check if the username is different from the current one
//            //at first, the currentCardNumber still = null so the cardNumber != currentCardNumber
//            if (!cardNumber.equals(currentCardNumber)) {
//                // Remove the existing username label and undo button, if any
////                if (usernameLabel != null) {
////                    remove(usernameLabel);
////                    usernameLabel = null;
////                }
//                String username = getUsernameByCardNumber(cardNumber);
//
//                // Display the username in a label
//                usernameLabel = new JLabel("Username: " + username);
//                usernameLabel.setBounds(20, 220, getWidth() - 50, 40);
//                usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
//                add(usernameLabel);
//
//                // Create the "Undo" button
//                undoButton = new JButton("Undo");
//                undoButton.setBounds(15, 410, getWidth() - 50, 40);
//                undoButton.setFont(new Font("Dialog", Font.BOLD, 20));
//                undoButton.addActionListener(e2 -> {
//                    // Remove the username label and undo button
//                    remove(usernameLabel);
//                    usernameLabel = null;
//                    // Restore the previous card number, if any
//                    if (!undoQueue.isEmpty()) {
//                        currentCardNumber = undoQueue.poll();
//                    } else {
//                        currentCardNumber = null;
//                    }
//                    revalidate();
//                    repaint();
//                });
//                add(undoButton);
//                currentCardNumber = cardNumber;
//                currentUsername = username;
//            }
//            revalidate();
//            repaint();
//        });
//        add(checkbutton);
//    }

    public void addcheck() {
        checkbutton = new JButton("check");
        checkbutton.setBounds(15, 350, getWidth() - 50, 40);
        checkbutton.setFont(new Font("Dialog", Font.BOLD, 20));
        checkbutton.addActionListener(e -> {
            String cardNumber = enterCardNumberField.getText();

            // Check if the card number is different from the current one
            if (!cardNumber.equals(currentCardNumber)) {
                // Push the current card number onto the queue
                if (currentCardNumber != null) {
                    undoQueue.offer(currentCardNumber);
                }

                String username = getUsernameByCardNumber(cardNumber);

                // Display the username in a label
                if (usernameLabel != null) {
                    remove(usernameLabel);
                }
                usernameLabel = new JLabel("Username: " + username);
                usernameLabel.setBounds(20, 220, getWidth() - 50, 40);
                usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                add(usernameLabel);

                // Create the "Undo" button
                if (undoButton == null) {
                    undoButton = new JButton("Undo");
                    undoButton.setBounds(15, 410, getWidth() - 50, 40);
                    undoButton.setFont(new Font("Dialog", Font.BOLD, 20));
                    undoButton.addActionListener(e2 -> {
                        // Remove the username label and undo button
                        if (usernameLabel != null) {
                            remove(usernameLabel);
                            usernameLabel = null;
                        }

                        // Clear the card number input field
                        enterCardNumberField.setText("");

                        // Restore the previous card number, if any
                        if (!undoQueue.isEmpty()) {
                            currentCardNumber = undoQueue.poll();
                            String previousUsername = getUsernameByCardNumber(currentCardNumber);

                            if (usernameLabel != null) {
                                remove(usernameLabel);
                            }
                            usernameLabel = new JLabel("Username: " + previousUsername);
                            usernameLabel.setBounds(20, 220, getWidth() - 50, 40);
                            usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                            add(usernameLabel);
                        } else {
                            currentCardNumber = null;
                            currentUsername = null;
                        }
                        revalidate();
                        repaint();
                    });
                    add(undoButton);
                }

                currentCardNumber = cardNumber;
                currentUsername = username;
            }
            revalidate();
            repaint();
        });
        add(checkbutton);
    }

    public void addCurrentBalanceAndAmount() {
        //balance label
// balance label
        balanceLabel = new JLabel("Balance: $" + user.getCurrentBalance());
        balanceLabel.setBounds(0, 10, getWidth() - 20, 20);
        balanceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        // enter amount label
        enterAmountLabel = new JLabel("Enter Amount:");
        enterAmountLabel.setBounds(0, 50, getWidth() - 20, 20);
        enterAmountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

//        // enter amount field
//        enterAmountField = new JTextField();
//        enterAmountField.setBounds(15, 80, getWidth() - 50, 40);
//        enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
//        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
//        add(enterAmountField);

        // enter amount field
        enterAmountField = new JTextField();
        enterAmountField.setBounds(15, 80, getWidth() - 50, 40);
        enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountField);

        // Set a DocumentFilter to prevent negative numbers
        ((AbstractDocument) enterAmountField.getDocument()).setDocumentFilter(new PositiveNumberFilter());

    }
    class PositiveNumberFilter extends DocumentFilter{
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isValidInput(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isValidInput(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
        private boolean isValidInput(String text) {
            try {
                double value = Double.parseDouble(text);
                return value >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public void addActionButton(String actionButtonType) {
        actionButton = new JButton(actionButtonType);
        actionButton.setBounds(15, 500, getWidth() - 50, 40);
        actionButton.setFont(new Font("Dialog", Font.BOLD, 20));
        actionButton.addActionListener(this);
        add(actionButton);
    }
//    public void addUserField(){
//        // enter user label
//        enterUserLabel = new JLabel("Enter User:");
//        enterUserLabel.setBounds(0, 160, getWidth() - 20, 20);
//        enterUserLabel.setFont(new Font("Dialog", Font.BOLD, 16));
//        enterUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        add(enterUserLabel);
//
//        // enter user field
//        enterUserField = new JTextField();
//        enterUserField.setBounds(15, 190, getWidth() - 50, 40);
//        enterUserField.setFont(new Font("Dialog", Font.BOLD, 20));
//        enterUserField.setHorizontalAlignment(SwingConstants.CENTER);
////        add(enterUserField);
//    }
    public void addCardNumberField() {
        // enter card number label
        enterCardNumberLabel = new JLabel("Enter Card Number:");
        enterCardNumberLabel.setBounds(0, 140, getWidth() - 20, 20);
        enterCardNumberLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        enterCardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterCardNumberLabel);

        // enter card number field
        enterCardNumberField = new JTextField();
        enterCardNumberField.setBounds(15, 170, getWidth() - 50, 40);
        enterCardNumberField.setFont(new Font("Dialog", Font.BOLD, 20));
        enterCardNumberField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterCardNumberField);

    }

    public void updateUsernameLabel(String username) {
        // Update the username label on the GUI
        JLabel usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setBounds(0, 320, getWidth(), 20);
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(usernameLabel);
    }
//    BigDecimal runningBalance = null;

    public void addPastTransactionComponents() {
        //container where we will store each transaction
        pastTransactionPanel = new JPanel();

        //make layout 1x1
        pastTransactionPanel.setLayout(new BoxLayout(pastTransactionPanel, BoxLayout.Y_AXIS));

        //add scrollability to the container
        JScrollPane scrollPane = new JScrollPane(pastTransactionPanel);

        //displays the vertical scroll only when it is required
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 30, getWidth() -15, getHeight()- 90 );

        //perform db call to retrieve all of the past transaction and store into array list
        ArrayList<Transaction> pastTransactions = MyJDBC.getPastTransaction(user);

        //iterate through the list and add to the gui
//        Transaction pastTransaction = null;
        for (int i = 0; i < pastTransactions.size(); i++) {

            //store current transaction
            Transaction pastTransaction = pastTransactions.get(i);

            //create a container tp store an individual transaction
            JPanel pastTransactionContainer = new JPanel();
            pastTransactionContainer.setLayout(new BorderLayout());

            //create transaction type label
            JLabel transactionTypeLabel = new JLabel(pastTransaction.getTransactionType());
            transactionTypeLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            //create transaction amount label
//            JLabel transactionAmountLabel = new JLabel(String.valueOf(pastTransaction.getTransactionAmount()));
            JLabel transactionAmountLabel = new JLabel(pastTransaction.getTransactionAmount().setScale(2, RoundingMode.HALF_UP).toString());
            transactionAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            //create transaction date label
            JLabel transactionDateLabel = new JLabel(String.valueOf(pastTransaction.getTransactionDate()));
            transactionDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));
            //add to the container
            pastTransactionContainer.add(transactionTypeLabel, BorderLayout.WEST); //place this on the west side
            pastTransactionContainer.add(transactionAmountLabel, BorderLayout.EAST); //place this on the west side
            pastTransactionContainer.add(transactionDateLabel, BorderLayout.SOUTH); //place this on the west side

            //give a white background to each container
            pastTransactionContainer.setBackground(Color.WHITE);

            //give a white background to each container
            pastTransactionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            //add transaction component to the transaction panel
            pastTransactionPanel.add(pastTransactionContainer);
            // update the running balance based on the current transaction
        }

        //add to the dialog
        add(scrollPane);
    }



//    private void handleTransaction(String transactionType, float amountVal){
//        Transaction transaction;
//        if(transactionType.equalsIgnoreCase("Deposit")){
//            //deposit transaction type
//            //add to current balance
//            user.setCurrentBalance(user.getCurrentBalance().add(new BigDecimal(amountVal)));
//
//            //create transaction
//            //we leave date null because we are going to be using the NOW() in sql which will get the current date
//            transaction = new Transaction(user.getId(), transactionType, new BigDecimal(amountVal), null);
//
//        } else{
//            //withdraw transaction type
//            //subtract from current balance
//            user.setCurrentBalance(user.getCurrentBalance().subtract(new BigDecimal(amountVal)));
//            //we want to show a negative sign for the amount val when withdrawing
//            transaction = new Transaction(user.getId(), transactionType, new BigDecimal(amountVal), null);
//        }
//        //update database
//        if(MyJDBC.addTransactionToDatabase(transaction) && MyJDBC.updateCurrentBalance(user)){
//            JOptionPane.showMessageDialog(this, transactionType + "Successfully!");
//
//            //reset the fields
//            resetFieldsAndUpdateCurrentBalance();
//        }else {
//            //show failure dialog
//            JOptionPane.showMessageDialog(this, transactionType + "Failed...");
//        }
//    }


        private void handleTransaction(String transactionType, float amountVal) {
            if (!ConnectionListener.InternetChecker.isInternetAvailable()) {
                JOptionPane.showMessageDialog(this, "Internet not connected. Please check your connection.");
                return; // Exit method if no internet connection
            }

            Transaction transaction;
            if (transactionType.equalsIgnoreCase("Deposit")) {
                // Deposit transaction type
                // Add to current balance
                user.setCurrentBalance(user.getCurrentBalance().add(new BigDecimal(amountVal)));

                // Create transaction (date set to null for SQL NOW())
                transaction = new Transaction(user.getId(), transactionType, new BigDecimal(amountVal), null);

            } else {
                // Withdraw transaction type
                // Subtract from current balance
                user.setCurrentBalance(user.getCurrentBalance().subtract(new BigDecimal(amountVal)));

                // Create transaction (date set to null for SQL NOW())
                transaction = new Transaction(user.getId(), transactionType, new BigDecimal(amountVal), null);
            }

            // Update database
            if (MyJDBC.addTransactionToDatabase(transaction) && MyJDBC.updateCurrentBalance(user)) {
                JOptionPane.showMessageDialog(this, transactionType + " successfully!");

                // Reset the fields and update current balance
                resetFieldsAndUpdateCurrentBalance();
            } else {
                JOptionPane.showMessageDialog(this, transactionType + " failed...");
            }
        }

    private void resetFieldsAndUpdateCurrentBalance(){
        //reset fields
        enterAmountField.setText("");

        //only appears when transfer is clicked
        if(enterUserField != null){
            enterUserField.setText("");
        }

        //update current balance on dialog
        balanceLabel.setText("Balance: $" + user.getCurrentBalance());

        //update current balance on main gui
        bankingAppGui.getCurrentBalanceField().setText("$" + user.getCurrentBalance());
    }

    private void handleTransfer(User user, int transferredCardNumber, float amount){
        if (!ConnectionListener.InternetChecker.isInternetAvailable()) {
            JOptionPane.showMessageDialog(this, "Internet not connected. Please check your connection.");
            return; // Exit method if no internet connection
        }
        //attempt to perform transfer
        if(MyJDBC.transfer(user, transferredCardNumber, amount)){
            //show success dialog
            JOptionPane.showMessageDialog(this, "Transfer Success!");
            resetFieldsAndUpdateCurrentBalance();
        }else{
            //show failure dialog
            JOptionPane.showMessageDialog(this, "Please choose an account other than " +
                                                                    "the source account or account do not exist");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();

        //get amount val
        float amountVal = Float.parseFloat(enterAmountField.getText());

        //pressed deposit
        if(buttonPressed.equalsIgnoreCase("Deposit")){
            // we want to handle the deposit transaction
            handleTransaction(buttonPressed, amountVal);
        }else{
            //pressed withdraw or transfer
            //validate input by making sure that withdraw or transfer amount is less than current balance
            //if result is -1 it means that the entered amount is more, 0 means they are equal, and 1 means that the entered amount is less
            int result = user.getCurrentBalance().compareTo(BigDecimal.valueOf(amountVal));
            if(result < 0){
                //display error dialog
                JOptionPane.showMessageDialog(this,"Error: Input value is more than current balance");
                return;
            }
            //check to see if withdraw or transfer was pressed
            if(buttonPressed.equalsIgnoreCase("Withdraw")){
                handleTransaction(buttonPressed, amountVal);
            }else{
                //transfer
                int transferredCardNumber = Integer.parseInt(enterCardNumberField.getText());

                //handle transfer
                handleTransfer(user, transferredCardNumber, amountVal);
            }
        }
    }
}
