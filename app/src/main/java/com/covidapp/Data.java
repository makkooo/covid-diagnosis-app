package com.covidapp;

import java.util.HashMap;

public class Data {

    private HashMap<String, Float> ageSex = new HashMap<String, Float>();
    private HashMap<String, Float> region = new HashMap<String, Float>();

    public HashMap<String, Float> getAgeSexMap() {

        ageSex.put("below 24 Male", 10.57f);
        ageSex.put("below 24 Female", 11.51f);
        ageSex.put("25 to 34 Male", 13.40f);
        ageSex.put("25 to 34 Female", 14.02f);
        ageSex.put("35 to 44 Male", 9.41f);
        ageSex.put("35 to 44 Female", 8.79f);
        ageSex.put("45 to 54 Male", 6.65f);
        ageSex.put("45 to 54 Female", 6.53f);
        ageSex.put("55 to 64 Male", 4.77f);
        ageSex.put("55 to 64 Female", 5.28f);
        ageSex.put("65 above Male", 4.04f);
        ageSex.put("65 above Female", 4.97f);

        return ageSex;
    }

    public HashMap<String, Float> getRegionMap() {

        region.put("I: Ilocos Region", 3.67f);
        region.put("II: Cagayan Valley", 4.47f);
        region.put("III: Central Luzon", 9.98f);
        region.put("IV‑A: CALABARZON", 18.15f);
        region.put("IV‑B: MIMAROPA", 1.20f);
        region.put("V: Bicol Region", 1.81f);
        region.put("VI: Western Visayas", 5.24f);
        region.put("VII: Central Visayas", 5.25f);
        region.put("VIII: Eastern Visayas", 1.780f);
        region.put("IX: Zamboanga Peninsula", 1.79f);
        region.put("X: Northern Mindanao", 2.89f);
        region.put("XI: Davao Region", 3.78f);
        region.put("XII: SOCCSKSARGEN", 2.012f);
        region.put("XIII: Caraga", 1.69f);
        region.put("NCR", 32.31f);
        region.put("CAR", 3.26f);
        region.put("BARMM", 0.70f);

        return region;
    }
}
