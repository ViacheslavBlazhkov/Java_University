package models.reports;

import models.interfaces.ReportPrinter;

public class ConsoleReportPrinter implements ReportPrinter {
    @Override
    public void print(StringBuilder report) {
        System.out.print(report);
    }
}
