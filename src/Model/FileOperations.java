package Model;

import java.util.ArrayList;

import static Controller.Controller.invoicesHeaderTable;
import static View.InvoiceFrame.invoiceItemsTable;

public class FileOperations
{
    public static ArrayList<InvoicesTable> readFile() {

        for (int z=1 ; z < invoicesHeaderTable.getRowCount();z++) {
            System.out.print("Invoice Number ");
            System.out.println(invoicesHeaderTable.getValueAt(z, 0) + ",");
            System.out.print(invoicesHeaderTable.getValueAt(z, 1) + ",");
            System.out.print(invoicesHeaderTable.getValueAt(z, 2) + ",");
            System.out.println(invoicesHeaderTable.getValueAt(z, 3));
        }
        System.out.println("Invoice Items");
            for (int y = 1; y < invoiceItemsTable.getRowCount(); y++) {
                //System.out.println(invoiceItemsTable.getValueAt(y, 0));
                System.out.print(invoiceItemsTable.getValueAt(y, 1) + ",");
                System.out.print(invoiceItemsTable.getValueAt(y, 2) + ",");
                System.out.print(invoiceItemsTable.getValueAt(y, 3) + ",");
                System.out.println(invoiceItemsTable.getValueAt(y, 4));
            }
        return null;
    }
}
