package DatabaseEngine;
import java.io.*;
import java.util.*;
import config.Config;
import org.apache.commons.csv.*;

public class DatabaseEngine {
    private HashMap<String, Student> students = new HashMap<String, Student>();
    private HashMap<String, Course> courses = new HashMap<String, Course>();

    private void restore() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Config.database_path));
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
        for (CSVRecord record : records) {
            InsertOneRecord(record.get(0).trim(),
                    record.get(1).trim(),record.get(2).trim());
        }
    }

    public void store() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(Config.database_path));
        CSVPrinter printer = new CSVPrinter(writer,CSVFormat.DEFAULT);
        Iterator<Map.Entry<String, Student>> entries = students.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String, Student> entry = entries.next();
            Iterator<Map.Entry<String, String>> courses = entry.getValue().course_score.entrySet().iterator();
            while(courses.hasNext()){
                Map.Entry<String, String> course = courses.next();
                printer.printRecord(entry.getKey(),course.getKey(),course.getValue());
            }
        }
        printer.close();
    }

    public DatabaseEngine() {
        try {
            boolean res = true;
            File dir = new File(Config.dir_path);
            if (!dir.exists())
                res = dir.mkdir();
            if (!res)
                throw new Exception("create dir failed.");
            File f = new File(Config.database_path);
            if (!f.exists())
                res = f.createNewFile();
            if (!res)
                throw new Exception("create file failed.");
            restore();
        }
        catch (Exception e){
            System.out.println("error occurred when processing config.config module.\n");
            e.printStackTrace();
        }
    }
    public void InsertOneRecord(String studentName, String courseName, String score){
        try {
            //student record
            Student student = students.get(studentName);
            if (student == null)
                students.put(studentName,new Student());
            student = students.get(studentName);
            student.Insert(courseName,score);
            //course record
            Course course = courses.get(courseName);
            if (course == null)
                courses.put(courseName,new Course());
            course = courses.get(courseName);
            course.Insert(studentName,score);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void CheckStudent(String studentName){
        try {
            Student student = students.get(studentName);
            if (student == null)
                throw new Exception("No such student");
            student.Query();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void CheckCourse(String courseName){
        try {
            Course course = courses.get(courseName);
            if (course == null)
                throw new Exception("No such course");
            course.Query();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

class Student extends Record{
    public HashMap<String, String> course_score = new HashMap<String, String>();
    public void Insert(String name, String score) {
        super.Insert(course_score,name,score);
    }
    public void Query(){
        System.out.println("courseName score");
        System.out.println("-----------------");
        super.Query(course_score);
    }
}

class Course extends Record{
    HashMap<String, String> student_score = new HashMap<String, String>();
    public void Insert(String name, String score) {
        super.Insert(student_score,name,score);
    }
    public void Query(){
        System.out.println("studentName score");
        System.out.println("-----------------");
        super.Query(student_score);
    }
}

class Record{
    void Insert(Map<String, String> map, String Name, String score){
        try {
            map.put(Name,score);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    void Query(Map<String, String> map){
        try {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<String, String> entry = entries.next();
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}