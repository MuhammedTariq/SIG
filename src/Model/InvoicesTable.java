package Model;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import Controller.Controller;

import static Controller.Controller.SaveInvoiceHeader;
import static Controller.Controller.ScanCSV;

public class InvoicesTable extends View.InvoiceFrame
{
    static String FilePath ;
    public static void LoadFile()
    {
        ScanCSV();
    }
    public static void SaveFile() {
        try {
            SaveInvoiceHeader();
        } catch (IOException cannotSave) {
            JOptionPane.showMessageDialog(null, "Can't Save  the File", "Saving is Blocked", JOptionPane.ERROR_MESSAGE);
            cannotSave.printStackTrace();
        }
    }
}
