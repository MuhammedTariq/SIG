package Model;

import static Controller.Controller.invoicesHeaderTable;
import static View.InvoiceFrame.*;

public class Invoice extends Line
{
    public static int invoiceNumValue = 0;
    public static String invoiceDateValue;
    public static String customerNameValue;
    public static double invoiceTotalValue;
    //ArrayList<Line()>;
    public static void getInvoiceHeader(int selectedRow)
    {
        invoiceNumField.setText((String) invoicesHeaderTable.getValueAt(selectedRow,0));
        invoiceDateField.setText((String) invoicesHeaderTable.getValueAt(selectedRow,1));
        customerNameField.setText((String) invoicesHeaderTable.getValueAt(selectedRow,2));
        invoiceTotalField.setText((String) invoicesHeaderTable.getValueAt(selectedRow,3));
    }
}
