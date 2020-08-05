package com.comparator.qa.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIDataProvider {

    public static List<String> readFile(BufferedReader reader) throws IOException {

        List<String> fileContent = new ArrayList<String>();
        String line;
        while((line = reader.readLine())!=null) {
            fileContent.add(line);
        }
        return fileContent;

    }

    @DataProvider(name = "requestURL")
    public static Object[][] requestURL() throws Exception {
        Object[][] reqs = null;
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader("./resources/testdata/fileone.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("./resources/testdata/filetwo.txt"));

            List<String> file1 = readFile(reader1);
            List<String> file2 = readFile(reader2);

            reqs = new Object[file1.size()][2];

            for (int i = 0; i < file1.size(); i++) {
                reqs[i][0] = file1.get(i);
                reqs[i][1] = file2.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reqs;
    }
}
