import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BankingSystemGUI {
    private HashMap<String, Double> accounts = new HashMap<>();
    
    public static void main(String[] args) {
        new BankingSystemGUI().createGUI();
    }

    public void createGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Banking System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        // Create buttons for each action
        JButton createAccountButton = new JButton("Create Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the frame
        frame.add(createAccountButton);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(checkBalanceButton);
        frame.add(exitButton);

        // Add action listeners for each button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to create a new account
    private void createAccount() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        if (accountNumber == null || accountNumber.isEmpty()) {
            showMessage("Invalid account number.");
            return;
        }
        if (accounts.containsKey(accountNumber)) {
            showMessage("Account already exists!");
        } else {
            accounts.put(accountNumber, 0.0);
            showMessage("Account created successfully.");
        }
    }

    // Method to deposit money into an account
    private void deposit() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        if (!accounts.containsKey(accountNumber)) {
            showMessage("Account does not exist!");
            return;
        }

        String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                showMessage("Enter a valid amount.");
            } else {
                double currentBalance = accounts.get(accountNumber);
                accounts.put(accountNumber, currentBalance + amount);
                showMessage("Deposit successful. New Balance: " + accounts.get(accountNumber));
            }
        } catch (NumberFormatException e) {
            showMessage("Invalid amount.");
        }
    }

    // Method to withdraw money from an account
    private void withdraw() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        if (!accounts.containsKey(accountNumber)) {
            showMessage("Account does not exist!");
            return;
        }

        String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountStr);
            double currentBalance = accounts.get(accountNumber);
            if (amount > currentBalance) {
                showMessage("Insufficient balance.");
            } else if (amount <= 0) {
                showMessage("Enter a valid amount.");
            } else {
                accounts.put(accountNumber, currentBalance - amount);
                showMessage("Withdrawal successful. New Balance: " + accounts.get(accountNumber));
            }
        } catch (NumberFormatException e) {
            showMessage("Invalid amount.");
        }
    }

    // Method to check balance of an account
    private void checkBalance() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        if (accounts.containsKey(accountNumber)) {
            showMessage("Current Balance: " + accounts.get(accountNumber));
        } else {
            showMessage("Account does not exist!");
        }
    }

    // Utility method to show messages in a dialog box
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
