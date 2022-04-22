package com.example.myplatenrro;

import com.example.myplatenrro.dataModel.Judet;
import com.example.myplatenrro.dataModel.ListaCuvinteSingleton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Controller {

    @FXML
    private ListView<String> judeteLista;
    @FXML
    private TextArea numarGenerat;
    @FXML
    private RadioButton cifreRandom;
    @FXML
    private RadioButton textualizare;
    @FXML
    private Label explicatieRadio;
    @FXML
    private CheckBox litere4;
    @FXML
    private CheckBox litere5;
    @FXML
    private CheckBox litere6;
    @FXML
    private CheckBox litere7;
    @FXML
    private Button buttonGenereaza;
    @FXML
    private Button buttonRefresh;
    @FXML
    private Button buttonLista;
    @FXML
    private TextArea listaNumereGenerate;
    private Judet judetSelectatDinLista;
    private Map<String, String> mapCifreLitere = new HashMap<>() {
        {   // 0 1 2 3 4 5 6 7 8 9
            put("A", "4");
            put("B", "8");
//            put("C","6");
//            put("D","4");
            put("E", "3");
            put("I", "1");
//            put("J","1");
            put("L", "7");
            put("O", "0");
            put("P", "4");
            put("S", "5");
            put("T", "7");
//            put("Z","2");
        }
    };
    private List<String> listaCifreRandom = new ArrayList<>();
    private List<String> listaTextualizare = new ArrayList<>();
    private List<Character> listaLitereInterzise = new ArrayList<>() {
        {
            add('O');
            add('I');
            add('o');
            add('i');
        }
    };


    public void initialize() {
        buttonGenereaza.setDisable(true);
        buttonGenereaza.setStyle("-fx-background-color: #0066ff; -fx-text-fill: white; -fx-font-weight: bold");
        buttonGenereaza.setEffect(new Bloom());
        buttonGenereaza.setEffect(new DropShadow());
        buttonRefresh.setDisable(true);
        buttonLista.setDisable(true);
        judeteLista.getItems().setAll(new Judet().getJudete());
        judeteLista.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        judeteLista.getSelectionModel().selectFirst();
        for (String s : ListaCuvinteSingleton.getInstance().getListaCuvinte()) {
            if (s.length() == 3 && !listaLitereInterzise.contains(s.charAt(0)))
                listaCifreRandom.add(s.toUpperCase());
            if (s.length() > 3 && s.length() <= 7) listaTextualizare.add(s.toUpperCase());
        }

        judeteLista.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (t1 != null) {
                    String item = judeteLista.getSelectionModel().getSelectedItem();
                    judetSelectatDinLista = new Judet(item);
                    if (cifreRandom.isSelected() || (textualizare.isSelected() &&
                            (litere4.isSelected() || litere5.isSelected() || litere6.isSelected() || litere7.isSelected()))) {

                        buttonGenereaza.setDisable(false);

                        buttonLista.setDisable(false);
                    }

                }
            }
        });

        litere4.setDisable(true);
        litere5.setDisable(true);
        litere6.setDisable(true);
        litere7.setDisable(true);
    }

    @FXML
    public void handleGenereazaClicked(){
        buttonGenereaza.setEffect(null);
        buttonGenereaza.setStyle("-fx-background-color: #0f0fdb; -fx-text-fill: white; -fx-font-weight: normal");
    }

    @FXML
    public void handleGenereazaReleased(){
        buttonGenereaza.setEffect(new Bloom());
        buttonGenereaza.setEffect(new DropShadow());
        buttonGenereaza.setStyle("-fx-background-color: #0066ff; -fx-text-fill: white; -fx-font-weight: bold");

    }


    public void handlecifreRandom() {
        buttonRefresh.setDisable(true);
        numarGenerat.clear();
        if (judetSelectatDinLista != null) {
            buttonGenereaza.setDisable(false);
            buttonLista.setDisable(false);
        }

        explicatieRadio.setText("Numerele sunt random. Gaseste cuvinte din 3 litere. Ex: DB 73 SAH");
        litere4.setDisable(true);
        litere5.setDisable(true);
        litere6.setDisable(true);
        litere7.setDisable(true);
        litere4.setSelected(false);
        litere5.setSelected(false);
        litere6.setSelected(false);
        litere7.setSelected(false);
    }

    public void handleTextualizare() {
        if (judetSelectatDinLista != null &&
                (litere4.isSelected() || litere5.isSelected() || litere6.isSelected() || litere7.isSelected())) {
            buttonGenereaza.setDisable(false);
            buttonLista.setDisable(false);
        } else {
            buttonGenereaza.setDisable(true);
            buttonLista.setDisable(true);
            numarGenerat.clear();
        }
        explicatieRadio.setText("Compune un cuvant cu tot numarul sau partial. Ex: B 01 ERU (BOIERU)");
        litere4.setDisable(false);
        litere5.setDisable(false);
        litere6.setDisable(false);
        litere7.setDisable(false);
//        explicatieRadio.setFont(Font.font("Times New Roman bold"));
    }

    public void handleCheckLitere() {
        if (judetSelectatDinLista != null &&
                (litere4.isSelected() || litere5.isSelected() || litere6.isSelected() || litere7.isSelected())) {
            buttonGenereaza.setDisable(false);
            buttonLista.setDisable(false);
        } else {
            buttonGenereaza.setDisable(true);
            buttonLista.setDisable(true);
            numarGenerat.clear();
        }
    }

    public void handleClickJudete() {
        String item = judeteLista.getSelectionModel().getSelectedItem();
        judetSelectatDinLista = new Judet(item);
    }

    public void handleListaButton() {
        String s = "";
        List<String> list = generareListaNumere(judetSelectatDinLista);
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i) + "\n";
        }
        listaNumereGenerate.setText(s);
        buttonRefresh.setDisable(false);
        numarGenerat.clear();
    }

    // elimin cuvintele deja afisate si cu refresh le pot reafisa
    public void handleRefreshButton() {
        cuvinteGenerate.clear();
        buttonRefresh.setDisable(true);
        listaNumereGenerateCTRL.clear();
        listaNumereGenerate.clear();
        numarGenerat.clear();
    }

    private String nrGenerat;

    public void handleGenereaza() {
        String listaGenerate = "";

        nrGenerat = generareNr(judetSelectatDinLista);
        numarGenerat.setText(nrGenerat);
        if (nrGenerat.length() > 3 && textualizare.isSelected()) {
            buttonRefresh.setDisable(false);
        } else buttonRefresh.setDisable(true);
        String s;
        for (int i = listaNumereGenerateCTRL.size() - 1; i >= 0; i--) {
            s = listaNumereGenerateCTRL.get(i);
            listaGenerate += s + "\n";
        }
        listaNumereGenerate.setText(listaGenerate);
    }

    private static int lastIter = 0;
    private List<String> cuvinteGenerate = new ArrayList<>();
    private List<String> listaNumereGenerateCTRL = new ArrayList<>();

    //*** Generare Numar
    public String generareNr(Judet judet) {
        String ultimulCuv = "???";
        String jud = judet.getPrescurtare();
        String numar = "" + jud + " ";
        int randomNr = 0;

        // cifreRandom sau textualizare
        // ajustare listaCuvinte,
        List<String> listaCuvinte = new ArrayList<>();
        if (cifreRandom.isSelected()) {
            listaCuvinte = new ArrayList<>(listaCifreRandom);
        } else if (textualizare.isSelected()) {
            listaCuvinte = new ArrayList<>(listaTextualizare);
        }
        listaCuvinte.removeAll(cuvinteGenerate);

        // CIFRE RADOM
        int randomNr2;
        String first, second, litJud, third;
        if (cifreRandom.isSelected()) {
            if (jud == "B") {
                while (randomNr == 0) {
                    randomNr = (int) (Math.random() * 999) + 1;
                }
                if (randomNr < 99) numar += String.format("%2s", randomNr).replace(" ", "0");
                else numar += randomNr;
            } else {
                while (randomNr == 0) {
                    randomNr = (int) (Math.random() * 99) + 1;
                }
                numar += String.format("%2s", randomNr).replace(" ", "0");
            }
            randomNr2 = (int) (Math.random() * listaCuvinte.size()) + 1;
            ultimulCuv = listaCuvinte.get(randomNr2);
            numar += " " + ultimulCuv;

            // TEXTUALIZARE
        } else if (textualizare.isSelected()) {
            // Selectie random nr listere
            List<Integer> listRandLit = new ArrayList<>();
            if (litere4.isSelected()) listRandLit.add(4);
            if (litere5.isSelected()) listRandLit.add(5);
            if (litere6.isSelected()) listRandLit.add(6);
            if (litere7.isSelected()) listRandLit.add(7);

            // Compunere numar in functie de lungimea cuvantului
            randomNr2 = (int) (Math.random() * 10);
            int i = (int) (Math.random() * listaCuvinte.size()) + 1;
            int count = 0;
            while (i < listaCuvinte.size() && count <= 2) {
                int randLit = (int) (Math.random() * listRandLit.size());
                int selected = listRandLit.get(randLit);
                numar = "" + jud + " ";
                i++;
                String s = listaCuvinte.get(i);
                ultimulCuv = s;
                //*** 4 litere
                if (s.length() == 4 && selected == 4) {
                    first = Character.toString(s.charAt(0));
                    if (mapCifreLitere.containsKey(first)) {
                        while (first == "O" && randomNr2 == 0) {
                            randomNr2 = (int) (Math.random() * 10);
                        }
                        if (listaLitereInterzise.contains(s.substring(1).charAt(0))) continue;
                        numar += randomNr2 + mapCifreLitere.get(first) + " " + s.substring(1);
                        lastIter = i + 1;
                        if (numar.equals(nrGenerat)) continue;
                        if (numar.length() <= 3 && listRandLit.size() > 1) {
                            listRandLit.remove(selected);
                            continue;
                        }
                        break;
                    }
                }
                //*** 5 litere
                if (s.length() == 5 && selected == 5) {
                    first = Character.toString(s.charAt(0));
                    second = Character.toString(s.charAt(1));
                    if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)) {
                        if (listaLitereInterzise.contains(s.substring(2).charAt(0))) continue;
                        numar += mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                " " + s.substring(2);
                        lastIter = i + 1;
                        if (numar.equals(nrGenerat)) continue;
                        if (numar.length() <= 3 && listRandLit.size() > 1) {
                            listRandLit.remove(selected);
                            continue;
                        }
                        break;
                    }
                }
                //*** 6 litere
                if (s.length() == 6 && selected == 6) {
                    first = Character.toString(s.charAt(1));
                    second = Character.toString(s.charAt(2));
                    if (jud.equals("B")) {
                        litJud = jud;
                    } else litJud = jud.substring(1);
                    if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)
                            && litJud.equals(s.substring(0, 1))) {
                        if (listaLitereInterzise.contains(s.substring(3).charAt(0))) continue;
                        numar += mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                " " + s.substring(3);
                        lastIter = i + 1;
                        if (numar.equals(nrGenerat)) continue;
                        if (numar.length() <= 3 && listRandLit.size() > 1) {
                            listRandLit.remove(selected);
                            continue;
                        }
                        break;
                    }
                }
                //*** 7 litere
                if (s.length() == 7 && selected == 7) {
                    // B 143 DUL (BIPEDUL)
                    if (jud.equals("B")) {
                        first = Character.toString(s.charAt(2));
                        second = Character.toString(s.charAt(3));
                        third = Character.toString(s.charAt(1));
                        if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)
                                && mapCifreLitere.containsKey(third)
                                && jud.equals(s.substring(0, 1))) {
                            if (listaLitereInterzise.contains(s.substring(4).charAt(0))) continue;
                            numar += mapCifreLitere.get(third) + mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                    " " + s.substring(4);
                            lastIter = i + 1;
                            if (numar.equals(nrGenerat)) continue;
                            if (numar.length() <= 3 && listRandLit.size() > 1) {
                                listRandLit.remove(selected);
                                continue;
                            }
                            break;
                        }
                    } else {
                        first = Character.toString(s.charAt(2));
                        second = Character.toString(s.charAt(3));
                        if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)
                                && jud.substring(0, 2).equals(s.substring(0, 2))) {
                            if (listaLitereInterzise.contains(s.substring(4).charAt(0))) continue;
                            numar += mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                    " " + s.substring(4);
                            lastIter = i + 1;
                            if (numar.equals(nrGenerat)) continue;
                            if (numar.length() <= 3 && listRandLit.size() > 1) {
                                listRandLit.remove(selected);
                                continue;
                            }
                            break;
                        }
                    }

                }
                if (i == listaCuvinte.size() - 1) {
                    count++;
                    i = 0;
                }
            }
        }
        if (numar.length() <= 3) {
            numarGenerat.setFont(Font.font("Times New Roman", 15));
            return numar = "Nu au mai fost gasite numere noi. Selecteaza alte crieterii sau apasa Refresh!";
        }
        numar += "  \t> " + ultimulCuv + " <";
        cuvinteGenerate.add(ultimulCuv);
        listaNumereGenerateCTRL.add(numar);
        numarGenerat.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        return numar;
    }

    //*** Generare Lista Numere
    public List<String> generareListaNumere(Judet judet) {
        List<String> listaGenerate = new ArrayList<>();
//        String listaGenerate = "";
        String ultimulCuv = "???";
        String jud = judet.getPrescurtare();
        String numar = "" + jud + " ";

        // cifreRandom sau textualizare
        // ajustare listaCuvinte,
        List<String> listaCuvinte = new ArrayList<>();
        if (cifreRandom.isSelected()) {
            listaCuvinte = new ArrayList<>(listaCifreRandom);
        } else if (textualizare.isSelected()) {
            listaCuvinte = new ArrayList<>(listaTextualizare);
        }

        // CIFRE RADOM
        int randomNr2;
        int randomNr = 0;
        String first, second, litJud, third;
        if (cifreRandom.isSelected()) {
            for (String s : listaCuvinte) {
                numar = "" + jud + " ";
                // setare cifre B XX ASD
                if (jud == "B") {
                    while (randomNr == 0) {
                        randomNr = (int) (Math.random() * 999) + 1;
                    }
                    if (randomNr < 99) numar += String.format("%2s", randomNr).replace(" ", "0");
                    else numar += randomNr;
                } else {
                    while (randomNr == 0) {
                        randomNr = (int) (Math.random() * 99) + 1;
                    }
                    numar += String.format("%2s", randomNr).replace(" ", "0");
                }

                numar += " " + s;
//                listaGenerate+=numar+"\n";
                listaGenerate.add(numar);
            }
        } else if (textualizare.isSelected()) {
            // Compunere numar in functie de lungimea cuvantului
            randomNr2 = (int) (Math.random() * 10);
            for (int i = 0; i < listaCuvinte.size(); i++) {
                numar = "" + jud + " ";
                String s = listaCuvinte.get(i);
                //*** 4 litere
                if (s.length() == 4 && litere4.isSelected()) {
                    first = Character.toString(s.charAt(0));
                    if (mapCifreLitere.containsKey(first)) {
                        while (first == "O" && randomNr2 == 0) {
                            randomNr2 = (int) (Math.random() * 10);
                        }
                        if (listaLitereInterzise.contains(s.substring(1).charAt(0))) continue;
                        numar += randomNr2 + mapCifreLitere.get(first) + " " + s.substring(1) + "   \t> " + s + " <";
                        listaGenerate.add(numar);
                        continue;
                    }
                }
                //*** 5 litere
                if (s.length() == 5 && litere5.isSelected()) {
                    first = Character.toString(s.charAt(0));
                    second = Character.toString(s.charAt(1));
                    if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)) {
                        if (listaLitereInterzise.contains(s.substring(2).charAt(0))) continue;
                        numar += mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                " " + s.substring(2) + "   \t> " + s + " <";
                        if (numar.equals(nrGenerat)) continue;
                        listaGenerate.add(numar);
                        continue;
                    }
                }
                //*** 6 litere
                if (s.length() == 6 && litere6.isSelected()) {
                    first = Character.toString(s.charAt(1));
                    second = Character.toString(s.charAt(2));
                    if (jud.equals("B")) {
                        litJud = jud;
                    } else litJud = jud.substring(1);
                    if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)
                            && litJud.equals(s.substring(0, 1))) {
                        if (listaLitereInterzise.contains(s.substring(3).charAt(0))) continue;
                        numar += mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                " " + s.substring(3) + "   \t> " + s + " <";
                        if (numar.equals(nrGenerat)) continue;
                        listaGenerate.add(numar);
                        continue;
                    }
                }
                //*** 7 litere
                if (s.length() == 7 && litere7.isSelected()) {
                    // B 143 DUL (BIPEDUL)
                    if (jud.equals("B")) {
                        first = Character.toString(s.charAt(2));
                        second = Character.toString(s.charAt(3));
                        third = Character.toString(s.charAt(1));
                        if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)
                                && mapCifreLitere.containsKey(third)
                                && jud.equals(s.substring(0, 1))) {
                            if (listaLitereInterzise.contains(s.substring(4).charAt(0))) continue;
                            numar += mapCifreLitere.get(third) + mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                    " " + s.substring(4) + "   \t> " + s + " <";
                            if (numar.equals(nrGenerat)) continue;
                            listaGenerate.add(numar);
                        }
                    } else {
                        first = Character.toString(s.charAt(2));
                        second = Character.toString(s.charAt(3));
                        if (mapCifreLitere.containsKey(first) && mapCifreLitere.containsKey(second)
                                && jud.substring(0, 2).equals(s.substring(0, 2))) {
                            if (listaLitereInterzise.contains(s.substring(4).charAt(0))) continue;
                            numar += mapCifreLitere.get(first) + mapCifreLitere.get(second) +
                                    " " + s.substring(4) + "   \t> " + s + " <";
                            if (numar.equals(nrGenerat)) continue;
                            listaGenerate.add(numar);
                        }
                    }

                }
            }
        }

        if (listaGenerate.size() == 0) listaGenerate.add("Nu exista cuvinte.");
        return listaGenerate;

//        if (numar.length() <= 3) {
//            numarGenerat.setFont(Font.font("Times New Roman", 15));
//            return numar = "Nu au mai fost gasite numere noi. Selecteaza alte crieterii sau apasa Refresh!";
//        }
//        numar+="   \t> " + ultimulCuv +" <";
//        cuvinteGenerate.add(ultimulCuv);
//        listaNumereGenerateCTRL.add(numar);
//        numarGenerat.setFont(Font.font("Helvetica", FontWeight.BOLD,28));
//        return numar;
    }


}