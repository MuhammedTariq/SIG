package Controller;

import Model.Invoice;
import Model.InvoicesTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

import static Model.Invoice.*;

public class Controller extends InvoicesTable
{
    static BufferedReader CSVReader;
    static BufferedWriter CSVSaver;
    static String Line = "";
    static Object[] ValuesData;
    static String[] ValuesRow;
    static DefaultTableModel CSVTable =
            (DefaultTableModel) invoicesTable.getModel();

    public static void ScanCSV() {
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
                    CSVTable.addRow(ValuesRow);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null , "There is No File Loaded" ,
                        "File Open is Canceled" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(FileNotFoundException FileNotFound)
        {
            JOptionPane.showMessageDialog(null, "File Not Found", "No File", JOptionPane.ERROR_MESSAGE);
            FileNotFound.printStackTrace();
        }
        catch(IOException NoLines2Read)
        {
            JOptionPane.showMessageDialog(null, "No Data Remains", "No Data", JOptionPane.ERROR_MESSAGE);
            NoLines2Read.printStackTrace();
        }
        finally
        {
            try
            {
                CSVReader.close();
            }
            catch (IOException CannotClose)
            {
                CannotClose.printStackTrace();
            }
        }
    }
    public static void SaveCSV () throws IOException {
        JFileChooser SaveForm = new JFileChooser();
        FileOutputStream SavingFile = null;
        int SaveResult = SaveForm.showSaveDialog(null);
        if (SaveResult == JFileChooser.APPROVE_OPTION)
        {
            String SavePath = SaveForm.getSelectedFile().getPath();
            FileWriter SaveCSV = new FileWriter(new File(SavePath));
                try {
                    for (int i = 0; i < CSVTable.getRowCount(); i++)
                    {
                        for (int j = 0; j < CSVTable.getColumnCount(); j++)
                        {
                            SaveCSV.write(CSVTable.getValueAt(i,j) + ",");
                        }
                        SaveCSV.write("\n");
                    }
                    JOptionPane.showMessageDialog(null,
                            "File is Saved Successfully",
                            "Saved Successfully", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException SaveException) {
                    SaveException.printStackTrace();
                } finally {
                    try {
                        SaveCSV.close();
                    } catch (IOException CannotClose) {
                        CannotClose.printStackTrace();
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
