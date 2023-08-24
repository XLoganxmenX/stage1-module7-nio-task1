package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(file.toPath())){

            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.getStackTrace();
        }

        String name = extractValueFromString(strings, "Name:");
        int age = Integer.parseInt(extractValueFromString(strings, "Age:"));
        String email = extractValueFromString(strings, "Email:");
        Long phone = Long.parseLong(extractValueFromString(strings, "Phone:"));

        return new Profile(name, age, email, phone);
    }

    private String extractValueFromString(List<String> stringList, String keyword) {
        for (String s : stringList) {
            if (s.startsWith(keyword)) {
                return s.substring(keyword.length()).trim();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        fileReader.getDataFromFile(file);



    }
}
