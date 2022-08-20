package View;

import Controller.Controller;
import Model.FileOperations;
import Model.Invoice;
import Model.InvoicesTable;
import Model.LineTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.Controller.*;
import static Model.Invoice.invoiceNumValue;

public class InvoiceFrame extends JFrame implements ActionListener {
    public JFrame InvoiceFrame = new JFrame();
    public JMenuBar mainMenuBar = new JMenuBar();
    public JMenu fileMenu = new JMenu("File");
    public JButton loadFile = new JButton("Load File");
    public JButton saveFile = new JButton("Save File");
    public JButton exitBtn = new JButton("Exit");
    private JPanel mainPanel = new JPanel();
    public static String invoiceNumClm = new String("No.");
    public static String invoiceDateClm = new String("Date");
    public static String invoiceCustomerClm = new String("Customer");
    public static String invoiceTotalClm = new String("Total");
    public static String itemNameClm = new String("Item Name");
    public static String itemPriceClm = new String("Item Price");
    public static String itemCountClm = new String("Count");
    public static String itemTotalClm = new String("Item Total");
    public static JTable invoicesTable = new JTable(1, 4);
    private JButton createNewInvoice = new JButton("Create Invoice");
    private JButton deleteInvoice = new JButton("Delete Invoice");
    public static JTextField invoiceTotalField = new JTextField(50);
    public static JTextField customerNameField = new JTextField(50);
    public static JTextField invoiceDateField = new JTextField(50);
    public static JTextField invoiceNumField = new JTextField(50);
    public static JTable invoiceItemsTable = new JTable(1, 5);
    private JButton createNewLine = new JButton("Create New Line");
    private JButton deleteLine = new JButton("Delete Line");
    private JPanel invoiceDetailsForm = new JPanel();
    private JPanel invoicesForm = new JPanel();
    private JLabel invoiceNumLbl = new JLabel("Invoice Number", SwingConstants.LEFT);
    private JLabel invoiceDateLbl = new JLabel("Invoice Date", SwingConstants.LEFT);
    private JLabel customerNameLbl = new JLabel("Customer Name", SwingConstants.LEFT);
    private JLabel invoiceTotalLbl = new JLabel("Invoice Total", SwingConstants.LEFT);
    private JPanel invoicesFooterButtons = new JPanel();
    private JPanel invoiceHeaderForm = new JPanel();
    private JPanel invoiceItemsForm = new JPanel();
    private JPanel invoiceDetailsFooterButtons = new JPanel();
    private JPanel invoiceNumForm = new JPanel();
    private JPanel invoiceDateForm = new JPanel();
    private JPanel customerNameForm = new JPanel();
    private JPanel invoiceTotalForm = new JPanel();
    public static String selectedInvoiceNum;

    public InvoiceFrame() {
        super("Sales Invoice Generator");

        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double ScreenWidth = ScreenSize.getWidth();
        double ScreenHeight = ScreenSize.getHeight();
        setSize((int) ScreenWidth, (int) ScreenHeight);
        setLocation(0, 0);

        loadFile.setMnemonic('O');
        loadFile.addActionListener(this);
        loadFile.setActionCommand("OpenPressed");
        saveFile.setMnemonic('S');
        saveFile.addActionListener(this);
        saveFile.setActionCommand("SavePressed");
        exitBtn.setMnemonic('X');
        exitBtn.addActionListener(this);
        exitBtn.setActionCommand("ExitPressed");
        createNewInvoice.addActionListener(this);
        createNewInvoice.setActionCommand("CreateNewInvoicePressed");
        deleteInvoice.addActionListener(this);
        deleteInvoice.setActionCommand("DeleteInvoicePressed");
        createNewLine.addActionListener(this);
        createNewLine.setActionCommand("CreateNewLinePressed");
        deleteLine.addActionListener(this);
        deleteLine.setActionCommand("DeleteLinePressed");

        fileMenu.add(loadFile);
        fileMenu.add(saveFile);
        fileMenu.addSeparator();
        fileMenu.add(exitBtn);
        mainMenuBar.add(fileMenu);

        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(invoicesForm);
        mainPanel.add(invoiceDetailsForm);


        invoicesFooterButtons.add(createNewInvoice);
        invoicesFooterButtons.add(deleteInvoice);
        invoicesForm.setLayout(new GridLayout(2, 1));
        invoicesForm.setBorder(BorderFactory.createTitledBorder("Invoices Table"));
        invoicesForm.add(invoicesTable);
        invoicesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent RowSelected) {
                int selectedRow = invoicesTable.getSelectedRow();
                if (selectedRow > 0 && selectedRow <= invoicesHeaderTable.getRowCount()) {
                    selectedInvoiceNum = (String) invoicesHeaderTable.getValueAt(selectedRow, 0);
                    customerNameField.setEnabled(true);
                    invoiceDateField.setEnabled(true);
                    Invoice.getInvoiceHeader(selectedRow);
                    for (int x = 1; x < invoiceItemsTable.getRowCount(); x = 1) {
                        invoiceLinesTable.removeRow(x);
                    }
                    ScanInvoiceItems();
                    invoiceItemsTable.setEnabled(true);
                    createNewLine.setEnabled(true);
                    deleteLine.setEnabled(true);
                    FileOperations.readFile();
                }
            }
        });
        isCellEditable(0, 3);
        invoicesTable.setRowSelectionAllowed(true);

        invoicesForm.add(invoicesFooterButtons);
        Controller.addInvoicesTableHeader();

        invoiceDetailsForm.setLayout(new GridLayout(3, 1));
        invoiceDetailsForm.add(invoiceHeaderForm);
        invoiceDetailsForm.add(invoiceItemsForm);
        invoiceDetailsForm.add(invoiceDetailsFooterButtons);

        invoiceNumForm.add(invoiceNumLbl);
        invoiceNumForm.add(invoiceNumField);
        invoiceNumForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        invoiceNumField.setEnabled(false);
        invoiceDateForm.add(invoiceDateLbl);
        invoiceDateForm.add(invoiceDateField);
        invoiceDateField.setEnabled(false);
        invoiceDateForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        customerNameForm.add(customerNameLbl);
        customerNameForm.add(customerNameField);
        customerNameField.setEnabled(false);
        customerNameForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalForm.add(invoiceTotalLbl);
        invoiceTotalForm.add(invoiceTotalField);
        invoiceTotalForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalField.setEnabled(false);

        invoiceHeaderForm.setLayout(new GridLayout(4, 1));
        invoiceHeaderForm.add(invoiceNumForm);
        invoiceHeaderForm.add(invoiceDateForm);
        invoiceHeaderForm.add(customerNameForm);
        invoiceHeaderForm.add(invoiceTotalForm);

        invoiceItemsForm.setBorder(BorderFactory.createTitledBorder("Invoice Items"));
        invoiceItemsForm.setLayout(new GridLayout(1, 1));
        invoiceItemsForm.add(invoiceItemsTable);
        invoiceItemsTable.setEnabled(false);
        Controller.addInvoiceItemsTableHeader();
        invoiceDetailsFooterButtons.add(createNewLine);
        invoiceDetailsFooterButtons.add(deleteLine);
        createNewLine.setEnabled(false);
        deleteLine.setEnabled(false);

        setJMenuBar(mainMenuBar);
        add(mainPanel);
    }

    public void actionPerformed(ActionEvent ButtonPressed) {
        if (ButtonPressed.getActionCommand().equals("OpenPressed")) {
            Controller.LoadFile();
        }
        if (ButtonPressed.getActionCommand().equals("SavePressed")) {
            InvoicesTable.SaveFile();
            LineTable.SaveFile();
        }
        if (ButtonPressed.getActionCommand().equals("ExitPressed")) {
            JOptionPane.showMessageDialog(InvoiceFrame,
                    "Application is Closed Successfully",
                    "Close the Sales Invoice Generator App", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (ButtonPressed.getActionCommand().equals("CreateNewInvoicePressed")) {
            customerNameField.setText("");
            invoiceDateField.setText("");
            customerNameField.setEnabled(true);
            invoiceDateField.setEnabled(true);
            invoiceTotalField.setText("");
            invoiceNumValue = invoiceNumValue + 1;
            invoiceNumField.setText(String.valueOf(invoiceNumValue));
            invoiceItemsTable.setEnabled(true);
            for (int x = 1; x < invoiceItemsTable.getRowCount(); x = 1)
            {
                invoiceLinesTable.removeRow(x);
            }
            createNewLine.setEnabled(true);
            deleteLine.setEnabled(true);
        }
        if (ButtonPressed.getActionCommand().equals("DeleteInvoicePressed"))
        {
            int selectedRow = invoicesTable.getSelectedRow();
            if(selectedRow>0 && selectedRow<=invoicesTable.getRowCount())
            {
            invoicesHeaderTable.removeRow(invoicesTable.getSelectedRow());
            invoiceNumField.setText("");
            customerNameField.setText("");
            invoiceDateField.setText("");
            invoiceTotalField.setText("");
                for (int x = 1; x < invoiceItemsTable.getRowCount(); x = 1)
                {
                    invoiceLinesTable.removeRow(x);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No Line has been Selected to be Deleted",
                        "No Line Selected", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (ButtonPressed.getActionCommand().equals("CreateNewLinePressed")) {
            invoiceItemsTable.setEnabled(true);
            invoiceLinesTable.insertRow(invoiceItemsTable.getRowCount(), new Object[]{"", "", "", "", ""});
        }
        if (ButtonPressed.getActionCommand().equals("DeleteLinePressed")) {
            int selectedRow = invoiceItemsTable.getSelectedRow();
            if(selectedRow>0 && selectedRow<=invoiceItemsTable.getRowCount())
            {
            invoiceLinesTable.removeRow(invoiceItemsTable.getSelectedRow());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No Line has been Selected to be Deleted",
                        "No Line Selected", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new InvoiceFrame().setVisible(true);
    }

    public boolean isCellEditable(int row, int column) {
        if (column == 0 || column == 3) {
            return (false);
        }
        return true;
    }
}