package Controller;

import Model.Invoice;
import Model.InvoicesTable;
import View.InvoiceFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

import static Model.Invoice.*;

public class Controller extends InvoicesTable
{
    static BufferedReader CSVReader;
    static BufferedWriter CSVSaver;
    static String Line = "";
    static Object[] ValuesData;
    static String[] ValuesRow;
    public static DefaultTableModel invoicesHeaderTable =
            (DefaultTableModel) invoicesTable.getModel();
    public static DefaultTableModel invoiceLinesTable =
            (DefaultTableModel) invoiceItemsTable.getModel();

    private static ArrayList<String> itemInvoiceNum = new ArrayList<String>();
//    private static ArrayList<[]> itemInvoiceNum ;

    public static void ScanInvoicesHeader() {
        try {
            JFileChooser SelectForm = new JFileChooser();
            int SelectionResult = SelectForm.showOpenDialog(null);
            if (SelectionResult == JFileChooser.APPROVE_OPTION)
            {
                String FilePath = SelectForm.getSelectedFile().getPath();
                CSVReader = new BufferedReader(new FileReader(FilePath));
                ValuesData = CSVReader.lines().toArray();
                for (int i = 0; i < ValuesData.length; i++)
                {
                    Line = ValuesData[i].toString().trim();
                    ValuesRow = Line.split(",");
                    invoicesHeaderTable.addRow(ValuesRow);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null , "There is No File Loaded" ,
                        "File Open is Canceled" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(FileNotFoundException fileNotFound)
        {
            JOptionPane.showMessageDialog(null, "File Not Found", "No File", JOptionPane.ERROR_MESSAGE);
            fileNotFound.printStackTrace();
        }
        catch(IOException noLines2Read)
        {
            JOptionPane.showMessageDialog(null, "No Data Remains", "No Data", JOptionPane.ERROR_MESSAGE);
            noLines2Read.printStackTrace();
        }
        finally
        {
            try
            {
                CSVReader.close();
            }
            catch (IOException cannotCloseReading)
            {
                JOptionPane.showMessageDialog(null, "Can't Stop Reading the File", "Stop Reading is Blocked", JOptionPane.ERROR_MESSAGE);
                cannotCloseReading.printStackTrace();
            }
        }
    }
    public static void ScanInvoiceItems() {
        try
        {
            JFileChooser SelectForm = new JFileChooser();
            int SelectionResult = SelectForm.showOpenDialog(null);
            if (SelectionResult == JFileChooser.APPROVE_OPTION)
            {
                String FilePath = SelectForm.getSelectedFile().getPath();
                CSVReader = new BufferedReader(new FileReader(FilePath));
                ValuesData = CSVReader.lines().toArray();
                for (int i = 0; i < ValuesData.length; i++) {
                    Line = ValuesData[i].toString().trim();
                    ValuesRow = Line.split(",");
                    invoiceLinesTable.addRow(ValuesRow);
                }
//                for (int v = 1; v < invoiceItemsTable.getRowCount() ; v++)
//                {
//                    if (selectedInvoiceNum != invoiceLinesTable.getValueAt(v, 0)) {
//                        invoiceLinesTable.removeRow(v);
//                    }
//                }

//                    String itemInvoiceNum = (String) invoiceItemsTable.getValueAt(v,0);
//                    if (selectedInvoiceNum != itemInvoiceNum)
//                    {
//                        invoiceLinesTable.removeRow(v);
//                    }
//                    if (v == invoiceItemsTable.getRowCount()) {v=0;}
//                }

                for (int v = 1; v < invoiceItemsTable.getRowCount(); v++)
                {
                    itemInvoiceNum.add((String) invoiceItemsTable.getValueAt(v, 0));
                }
                    while (invoiceItemsTable.getRowCount() > 1 && itemInvoiceNum.contains(1))
                    {
                        for (int z = 1; z < invoiceItemsTable.getRowCount() ; z++)
                        {
                            if (selectedInvoiceNum != itemInvoiceNum.get(z))
                            {
                                //invoiceLinesTable.removeRow(invoiceItemsTable.getRowCount());
                                itemInvoiceNum.remove(z);
                                invoiceLinesTable.removeRow(z);
                            }
                            if (z == invoiceItemsTable.getRowCount()) {z=0;}
                        }
                    }
                JOptionPane.showMessageDialog(null,
                        "Only Items Related to the Selected Invoice have been Loaded",
                        "Loaded Items", JOptionPane.INFORMATION_MESSAGE);
                }
            else
            {
                JOptionPane.showMessageDialog(null , "There is No File Loaded" ,
                        "File Open is Canceled" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(FileNotFoundException fileNotFound)
        {
            JOptionPane.showMessageDialog(null, "File Not Found", "No File", JOptionPane.ERROR_MESSAGE);
            fileNotFound.printStackTrace();
        }
        catch(IOException noLines2Read)
        {
            JOptionPane.showMessageDialog(null, "No Data Remains", "No Data", JOptionPane.ERROR_MESSAGE);
            noLines2Read.printStackTrace();
        }
        finally
        {
            try
            {
                CSVReader.close();
            }
            catch (IOException cannotCloseReading)
            {
                JOptionPane.showMessageDialog(null, "Can't Stop Reading the File", "Stop Reading is Blocked", JOptionPane.ERROR_MESSAGE);
                cannotCloseReading.printStackTrace();
            }
        }
    }
    public static void SaveInvoiceHeader () throws IOException {
        JFileChooser SaveForm = new JFileChooser();
        FileOutputStream SavingFile = null;
        int SaveResult = SaveForm.showSaveDialog(null);
        if (SaveResult == JFileChooser.APPROVE_OPTION)
        {
            String SavePath = SaveForm.getSelectedFile().getPath();
            FileWriter SaveCSV = new FileWriter(new File(SavePath));
                try {
                    for (int i = 0; i < invoicesHeaderTable.getRowCount(); i++)
                    {
                        for (int j = 0; j < invoicesHeaderTable.getColumnCount(); j++)
                        {
                            SaveCSV.write(invoicesHeaderTable.getValueAt(i,j) + ",");
                        }
                        SaveCSV.write("\n");
                    }
                    JOptionPane.showMessageDialog(null,
                            "Invoices File is Saved Successfully",
                            "Saved Successfully", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException saveException) {
                    JOptionPane.showMessageDialog(null, "Can't Save  the File", "Saving is Blocked", JOptionPane.ERROR_MESSAGE);
                    saveException.printStackTrace();
                } finally {
                    try {
                        SaveCSV.close();
                    } catch (IOException cannotCloseWriting) {
                        JOptionPane.showMessageDialog(null, "Can't Stop Writing the File", "Stop Writing is Blocked", JOptionPane.ERROR_MESSAGE);
                        cannotCloseWriting.printStackTrace();
                    }
                }
            }
        else
        {
            JOptionPane.showMessageDialog(null ,
                    "The File Isn't Saved" , "File Save is Canceled"
                    , JOptionPane.ERROR_MESSAGE);
        }

    }
    public static void SaveInvoiceLines () throws IOException {
        JFileChooser SaveForm = new JFileChooser();
        FileOutputStream SavingFile = null;
        int SaveResult = SaveForm.showSaveDialog(null);
        if (SaveResult == JFileChooser.APPROVE_OPTION)
        {
            String SavePath = SaveForm.getSelectedFile().getPath();
            FileWriter SaveCSV = new FileWriter(new File(SavePath));
            try {
                for (int i = 0; i < invoiceLinesTable.getRowCount(); i++)
                {
                    for (int j = 0; j < invoiceLinesTable.getColumnCount(); j++)
                    {
                        SaveCSV.write(invoiceLinesTable.getValueAt(i,j) + ",");
                    }
                    SaveCSV.write("\n");
                }
                JOptionPane.showMessageDialog(null,
                        "Invoice Items File is Saved Successfully",
                        "Saved Successfully", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException saveException) {
                JOptionPane.showMessageDialog(null, "Can't Save  the File", "Saving is Blocked", JOptionPane.ERROR_MESSAGE);
                saveException.printStackTrace();
            } finally {
                try {
                    SaveCSV.close();
                } catch (IOException cannotCloseWriting) {
                    JOptionPane.showMessageDialog(null, "Can't Stop Writing the File", "Stop Writing is Blocked", JOptionPane.ERROR_MESSAGE);
                    cannotCloseWriting.printStackTrace();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null ,
                    "The File Isn't Saved" , "File Save is Canceled"
                    , JOptionPane.ERROR_MESSAGE);
        }

    }
    public static void addInvoicesTableHeader()
    {
        invoicesTable.setValueAt(invoiceNumClm,0,0);
        invoicesTable.setValueAt(invoiceDateClm,0,1);
        invoicesTable.setValueAt(invoiceCustomerClm,0,2);
        invoicesTable.setValueAt(invoiceTotalClm,0,3);
    }
    public static void addInvoiceItemsTableHeader()
    {
        invoiceItemsTable.setValueAt(invoiceNumClm,0,0);
        invoiceItemsTable.setValueAt(itemNameClm,0,1);
        invoiceItemsTable.setValueAt(itemPriceClm,0,2);
        invoiceItemsTable.setValueAt(itemCountClm,0,3);
        invoiceItemsTable.setValueAt(itemTotalClm,0,4);
    }
}
