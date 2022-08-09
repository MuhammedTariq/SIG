package Model;

import javax.swing.*;
import java.io.IOException;

import static Controller.Controller.SaveInvoiceLines;
import static Controller.Controller.ScanInvoiceItems;

public class LineTable
{
    public static void LoadFile()
    {
        ScanInvoiceItems();
    }
    public static void SaveFile() {
        try {
            SaveInvoiceLines();
        } catch (IOException cannotSave) {
            JOptionPane.showMessageDialog(null, "Can't Save  the File", "Saving is Blocked", JOptionPane.ERROR_MESSAGE);
            cannotSave.printStackTrace();
        }
    }
}
