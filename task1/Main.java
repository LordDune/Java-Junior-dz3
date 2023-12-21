package ru.geekbrains.lesson3.dz3.task1;

import java.io.*;

public class Main {

    public static final String FILE_BIN = "D:/Docs/Programming/Code/Java/Junior/lesson3/lesson3/src/main/java/ru/geekbrains/lesson3/dz3/task1/dz3.bin";


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Student student = new Student("Max", 35, 4.5);

        // 1. Сериализация в файл .bin
        serialObjToBin(student, FILE_BIN);
        System.out.println("BIN File: " + deSerialObjFromBin(FILE_BIN)); // Результат: Student{name='Max', age=35, GPA=0.0}
        // Так как поле gpa класса Student имеет модификатор transient, данное поле не сериализуется.
        // Поскольку это поле является примитивом (double), по умолчанию оно имеет значение 0

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
}