import DatabaseEngine.DatabaseEngine;

import java.io.IOException;
import java.util.LinkedList;

public class Input {

    //java Input <student name> <course name> <marks>
    public static void main(String[] args) throws IOException {
        DatabaseEngine databaseEngine = new DatabaseEngine();
        LinkedList<String> name_list = new LinkedList<>();
        for(int i = 0;i < args.length-2;i++)
            name_list.add(args[i].trim());
        String name = String.join(" ",name_list);
        databaseEngine.InsertOneRecord(name,args[args.length-2].trim(),args[args.length-1].trim());
        System.out.println("Import successfully");
        databaseEngine.store();
    }
}
