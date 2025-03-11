package models.interfaces;

import java.io.IOException;

public interface ReportPrinter {
    void print(StringBuilder report) throws IOException;
}
