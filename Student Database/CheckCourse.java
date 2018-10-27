import DatabaseEngine.DatabaseEngine;

import java.io.IOException;

public class CheckCourse {

    //java CheckCourse <course name>
    public static void main(String[] args) throws IOException {
        DatabaseEngine databaseEngine = new DatabaseEngine();
        databaseEngine.CheckCourse(args[0]);
        databaseEngine.store();
    }
}
