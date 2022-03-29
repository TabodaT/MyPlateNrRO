package com.example.myplatenrro.dataModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListaCuvinteSingleton {
    private List<String> listaCuvinte;
    private static ListaCuvinteSingleton instance = new ListaCuvinteSingleton();

    public static ListaCuvinteSingleton getInstance() {
        return instance;
    }

    public List<String> getListaCuvinte() {
        return listaCuvinte;
    }

    public ListaCuvinteSingleton() {

    }

    public void incarcaLista() throws IOException {
        listaCuvinte = new ArrayList<>();
        Path path = Paths.get("src/main/resources/com/example/myplatenrro/lista_cuvite_ro.txt");
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try {
            while ((input = br.readLine()) != null) {
                listaCuvinte.add(input);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }


}
