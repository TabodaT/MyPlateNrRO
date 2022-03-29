package com.example.myplatenrro.dataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Judet {
    private String nume;
    private String prescurtare;
    private final List<String> judete = new ArrayList<>(){
        {
            add("Bucureşti");
            add("Alba");
            add("Arad");
            add("Arges");
            add("Bacău");
            add("Bihor");
            add("Bistriţa-Năsăud");
            add("Botoşani");
            add("Braşov");
            add("Brăila");
            add("Buzău");
            add("Caraş-Severin");
            add("Călăraşi");
            add("Cluj");
            add("Constanţa");
            add("Covasna");
            add("Dâmboviţa");
            add("Dolj");
            add("Galaţi");
            add("Giurgiu");
            add("Gorj");
            add("Harghita");
            add("Hunedoara");
            add("Ialomiţa");
            add("Iaşi");
            add("Ilfov");
            add("Maramureş");
            add("Mehedinţi");
            add("Mureş");
            add("Neamţ");
            add("Olt");
            add("Prahova");
            add("Satu Mare");
            add("Sălaj");
            add("Sibiu");
            add("Suceava");
            add("Teleorman");
            add("Timiş");
            add("Tulcea");
            add("Vâlcea");
            add("Vaslui");
            add("Vrancea");

        }
    };

    public Judet(String nume) {
        this.nume = nume;
        this.prescurtare = presurtareMap.get(nume);
    }

    public Judet() {
    }

    private final Map<String, String> presurtareMap = new HashMap<>(){
        {
            put("Bucureşti","B");
            put("Alba","AB");
            put("Arad","AR");
            put("Arges","AG");
            put("Bacău","BC");
            put("Bihor","BH");
            put("Bistriţa-Năsăud","BN");
            put("Botoşani","BT");
            put("Braşov","BV");
            put("Brăila","BR");
            put("Buzău","BZ");
            put("Caraş-Severin","CS");
            put("Călăraşi","CL");
            put("Cluj","CJ");
            put("Constanţa","CT");
            put("Covasna","CV");
            put("Dâmboviţa","DB");
            put("Dolj","DJ");
            put("Galaţi","GL");
            put("Giurgiu","GR");
            put("Gorj","GJ");
            put("Harghita","HR");
            put("Hunedoara","HD");
            put("Ialomiţa","IL");
            put("Iaşi","IS");
            put("Ilfov","IF");
            put("Maramureş","MM");
            put("Mehedinţi","MH");
            put("Mureş","MS");
            put("Neamţ","NT");
            put("Olt","OT");
            put("Prahova","PH");
            put("Satu Mare","SM");
            put("Sălaj","SJ");
            put("Sibiu","SB");
            put("Suceava","SV");
            put("Teleorman","TR");
            put("Timiş","TM");
            put("Tulcea","TL");
            put("Vâlcea","VL");
            put("Vaslui","VS");
            put("Vrancea","VN");

        }
    };

    public String getNume() {
        return nume;
    }

    public String getPrescurtare() {
        return prescurtare;
    }

    public List<String> getJudete() {
        return judete;
    }
}
