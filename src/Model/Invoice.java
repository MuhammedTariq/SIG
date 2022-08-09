package Model;

import View.InvoiceFrame;

import java.util.ArrayList;

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
        invoiceNum.setText((String) invoicesHeaderTable.getValueAt(selectedRow,0));
        invoiceDate.setText((String) invoicesHeaderTable.getValueAt(selectedRow,1));
        customerName.setText((String) invoicesHeaderTable.getValueAt(selectedRow,2));
        invoiceTotal.setText((String) invoicesHeaderTable.getValueAt(selectedRow,3));
    }
}
