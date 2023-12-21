package ru.geekbrains.lesson3.dz3.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Main {

    public static final String FILE_BIN = "D:/Docs/Programming/Code/Java/Junior/lesson3/lesson3/src/main/java/ru/geekbrains/lesson3/dz3/task2/dz3.bin";
    public static final String FILE_XML = "D:/Docs/Programming/Code/Java/Junior/lesson3/lesson3/src/main/java/ru/geekbrains/lesson3/dz3/task2/dz3.xml";
    public static final String FILE_JSON = "D:/Docs/Programming/Code/Java/Junior/lesson3/lesson3/src/main/java/ru/geekbrains/lesson3/dz3/task2/dz3.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Student student = new Student("Max", 35, 4.5);

        serialObjToBin(student, FILE_BIN);
        System.out.println("BIN File: " + deSerialObjFromBin(FILE_BIN));
        // Результат: BIN File: Student{name='Max', age=35, gpa=4.5}

        serialObjToXml(student, FILE_XML);
        System.out.println("XML File: " + deSerialObjFromXml(FILE_XML));
        // Результат: XML File: Student{name='Max', age=35, gpa=4.5}

        serialObjToJson(student, FILE_JSON);
        System.out.println("JSON File: " + deSerialObjFromJson(FILE_JSON));
        // Результат: JSON File: Student{name='Max', age=35, gpa=4.5}
    }

    public static void serialObjToBin(Object o, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }

    public static Object deSerialObjFromBin(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }

    public static void serialObjToXml(Object o, String fileName) throws IOException {
        xmlMapper.writeValue(new File(fileName), o);
    }

    public static Student deSerialObjFromXml(String fileName) throws IOException {
        return xmlMapper.readValue(new File (fileName), xmlMapper.getTypeFactory().constructType(Student.class));
    }

    private static void serialObjToJson(Object o, String fileName) throws IOException {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(new File(fileName), o);
    }

    private static Student deSerialObjFromJson(String fileName) throws IOException {
        return objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructType(Student.class));
    }


}