package com.example.myplatenrro.dataModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that manages the Romanian word list for license plate generation
 * This class loads and provides access to a curated list of Romanian words suitable for textualization
 */
public class ListaCuvinteSingleton {
    private List<String> listaCuvinte;
    private static ListaCuvinteSingleton instance = new ListaCuvinteSingleton();

    /**
     * Returns the singleton instance of the word list manager
     * @return the singleton instance
     */
    public static ListaCuvinteSingleton getInstance() {
        return instance;
    }

    /**
     * Returns the loaded list of Romanian words
     * @return the list of Romanian words
     */
    public List<String> getListaCuvinte() {
        return listaCuvinte;
    }

    /**
     * Private constructor to prevent instantiation from outside
     */
    public ListaCuvinteSingleton() {

    }

    /**
     * Loads the Romanian word list from the clean filtered file
     * This method reads Romanian words suitable for license plate generation
     * @throws IOException if the file cannot be read
     */
    public void incarcaLista() throws IOException {
        listaCuvinte = new ArrayList<>();
        Path path = Paths.get("src/main/resources/com/example/myplatenrro/lista_cuvinte_ro.txt");
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try {
            while ((input = br.readLine()) != null) {
                // Add only non-empty lines and convert to uppercase for consistency/
                if (!input.trim().isEmpty()) {
                    listaCuvinte.add(input.trim().toUpperCase());
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }


}
