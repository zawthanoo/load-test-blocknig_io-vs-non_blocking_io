package com.mutu.spring.rest.api.zprocess;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    public String readFileFromResources()  {
        StringBuilder content = new StringBuilder();
        try {
        // Use ClassPathResource to load the file from the resources folder
        ClassPathResource resource = new ClassPathResource("sampledata.txt");

        // Read the file using BufferedReader
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}