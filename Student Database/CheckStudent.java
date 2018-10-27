import DatabaseEngine.DatabaseEngine;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class CheckStudent {

    //java CheckStudent <student name>
    public static void main(String[] args) throws IOException {
        DatabaseEngine databaseEngine = new DatabaseEngine();
        LinkedList<String> name_list = new LinkedList<>(Arrays.asList(args));
        String name = String.join(" ",name_list);
        databaseEngine.CheckStudent(name);
        databaseEngine.store();
    }
}
