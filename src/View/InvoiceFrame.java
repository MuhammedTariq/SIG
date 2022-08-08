package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Model.InvoicesTable.LoadFile;

public class InvoiceFrame extends JFrame implements ActionListener
{
    public  JFrame InvoiceFrame = new JFrame();
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
    public static JTable invoicesTable = new JTable(1,4);
    private JButton createNewInvoice = new JButton("Create Invoice");
    private JButton deleteInvoice = new JButton("Delete Invoice");
    private JTextField invoiceTotal = new JTextField(50);
    private JTextField customerName = new JTextField(50);
    private JTextField invoiceDate = new JTextField(50);
    private JTextField invoiceNum = new JTextField(50);
    public static JTable invoiceItemsTable = new JTable(1,5);
    private JButton createNewLine = new JButton("Create New Line");
    private JButton deleteLine = new JButton("Delete Line");
    private JPanel invoiceDetailsForm = new JPanel();
    private JPanel invoicesForm = new JPanel();
    private JLabel invoiceNumLbl = new JLabel("Invoice Number", SwingConstants. LEFT);
    private JLabel invoiceDateLbl = new JLabel("Invoice Date", SwingConstants. LEFT);
    private JLabel customerNameLbl = new JLabel("Customer Name", SwingConstants. LEFT);
    private JLabel invoiceTotalLbl = new JLabel("Invoice Total", SwingConstants. LEFT);
    private JPanel invoicesFooterButtons = new JPanel();
    private JPanel invoiceHeaderForm = new JPanel();
    private JPanel invoiceItemsForm = new JPanel();
    private JPanel invoiceDetailsFooterButtons = new JPanel();
    private JPanel invoiceNumForm = new JPanel();
    private JPanel invoiceDateForm = new JPanel();
    private JPanel customerNameForm = new JPanel();
    private JPanel invoiceTotalForm = new JPanel();

    public InvoiceFrame()
    {
        super("Sales Invoice Generator");

        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double ScreenWidth =  ScreenSize.getWidth();
        double ScreenHeight = ScreenSize.getWidth();
        setSize((int) ScreenWidth,(int) ScreenHeight);
        setLocation(0,0);

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
        createNewLine.setActionCommand("InvoiceSavePressed");
        deleteLine.addActionListener(this);
        deleteLine.setActionCommand("CancelPressed");

        fileMenu.add(loadFile);
        fileMenu.add(saveFile);
        fileMenu.addSeparator();
        fileMenu.add(exitBtn);
        mainMenuBar.add(fileMenu);

        mainPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(invoicesForm);
        mainPanel.add(invoiceDetailsForm);


        invoicesFooterButtons.add(createNewInvoice);
        invoicesFooterButtons.add(deleteInvoice);
        invoicesForm.setLayout(new GridLayout(2, 1));
        invoicesForm.setBorder(BorderFactory.createTitledBorder("Invoices Table"));
        invoicesForm.add(invoicesTable);
        invoicesTable.setEnabled(false);
//        invoicesTable.setCellEditor(Disabled );
////        invoicesTable.getCellEditor();
//        invoicesTable.setColumnSelectionAllowed(false);
//        for (int i = 0; i < invoicesTable.getRowCount(); i++)
//        {
//            for (int j = 0; j < invoicesTable.getColumnCount(); j++)
//            {
//                isCellEditable(i , j);
//            }
//        }
        invoicesTable.setRowSelectionAllowed(true);
        invoicesForm.add(invoicesFooterButtons);
        Controller.addInvoicesTableHeader();

        invoiceDetailsForm.setLayout(new GridLayout(3, 1));
        invoiceDetailsForm.add(invoiceHeaderForm);
        invoiceDetailsForm.add(invoiceItemsForm);
        invoiceDetailsForm.add(invoiceDetailsFooterButtons);

        invoiceNumForm.add(invoiceNumLbl);
        invoiceNumForm.add(invoiceNum);
        invoiceNumForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        invoiceNum.setEnabled(false);
        invoiceDateForm.add(invoiceDateLbl);
        invoiceDateForm.add(invoiceDate);
        invoiceDateForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        customerNameForm.add(customerNameLbl);
        customerNameForm.add(customerName);
        customerNameForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalForm.add(invoiceTotalLbl);
        invoiceTotalForm.add(invoiceTotal);
        invoiceTotalForm.setLayout(new FlowLayout(FlowLayout.LEFT));
        invoiceTotal.setEnabled(false);

        invoiceHeaderForm.setLayout(new GridLayout(4,1));
        invoiceHeaderForm.add(invoiceNumForm);
        invoiceHeaderForm.add(invoiceDateForm);
        invoiceHeaderForm.add(customerNameForm);
        invoiceHeaderForm.add(invoiceTotalForm);


        invoiceItemsForm.setBorder(BorderFactory.createTitledBorder("Invoice Items"));
        invoiceItemsForm.setLayout(new GridLayout(1, 1));
        invoiceItemsForm.add(invoiceItemsTable);
        Controller.addInvoiceItemsTableHeader();
        invoiceDetailsFooterButtons.add(createNewLine);
        invoiceDetailsFooterButtons.add(deleteLine);


        setJMenuBar(mainMenuBar);
        add(mainPanel);
    }

    public boolean isCellEditable(int row, int column)
    {
        return (false);
    }

    public void actionPerformed(ActionEvent ButtonPressed) {
        if (ButtonPressed.getActionCommand().equals("OpenPressed"))
        {
            Controller.LoadFile();
        }
        if (ButtonPressed.getActionCommand().equals("SavePressed"))
        {
            try {
                Controller.SaveFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (ButtonPressed.getActionCommand().equals("ExitPressed"))
        {
            JOptionPane.showMessageDialog(InvoiceFrame ,
                    "Application is Closed Successfully" ,
                    "Close the Sales Invoice Generator App" , JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (ButtonPressed.getActionCommand().equals("CreateNewInvoicePressed"))
        {

        }
        if (ButtonPressed.getActionCommand().equals("DeleteInvoicePressed"))
        {

        }
        if (ButtonPressed.getActionCommand().equals("InvoiceSavePressed"))
        {

        }
        if (ButtonPressed.getActionCommand().equals("CancelPressed"))
        {

        }
    }
    public static void main(String[] args)
    {
        new InvoiceFrame().setVisible(true);
    }
}