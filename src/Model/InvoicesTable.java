package Model;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import Controller.Controller;

import static Controller.Controller.SaveCSV;
import static Controller.Controller.ScanCSV;

public class InvoicesTable extends View.InvoiceFrame
{
    static String FilePath ;
    public static void LoadFile()
    {
        ScanCSV();
    }
    public static void SaveFile() throws IOException { SaveCSV();}
}
