import java.io.*;
import DatabaseEngine.DatabaseEngine;
import org.apache.commons.csv.*;

public class Import {

    //java Import <file name>
    public static void main(String[] args) throws IOException {
        DatabaseEngine databaseEngine = new DatabaseEngine();
        String pathname = args[0];
        //FileOutputStream fout = new FileOutputStream(pathname);
        BufferedReader reader = new BufferedReader(new FileReader(pathname));
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
        for (CSVRecord record : records) {
            databaseEngine.InsertOneRecord(record.get(0).trim(),
                    record.get(1).trim(),record.get(2).trim());
        }
        System.out.println("Import successfully");
        databaseEngine.store();
    }
}
