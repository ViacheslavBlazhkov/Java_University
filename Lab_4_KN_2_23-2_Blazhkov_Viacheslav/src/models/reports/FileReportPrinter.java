package models.reports;

import models.interfaces.ReportPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static terminal.MenuOperations.scanner;

public class FileReportPrinter implements ReportPrinter {
    @Override
    public void print(StringBuilder report) throws IOException {
        System.out.print("Enter file name:");
        String fileName = scanner.nextLine();
        FileWriter file = new FileWriter(fileName);
        BufferedWriter fileOut = new BufferedWriter(file);
        fileOut.write(report.toString());
        fileOut.close();
        System.out.println("Report has been printed to: " + fileName + ".txt");
    }
}
