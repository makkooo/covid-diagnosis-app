package com.covidapp;

import java.util.HashMap;

public class Data {

    private HashMap<String, Float> ageSex = new HashMap<String, Float>();
    private HashMap<String, Float> region = new HashMap<String, Float>();

    public HashMap<String, Float> getAgeSexMap() {

        ageSex.put("below 24 Male", 10.99f);
        ageSex.put("below 24 Female", 11.52f);
        ageSex.put("25 to 34 Male", 13.15f);
        ageSex.put("25 to 34 Female", 12.84f);
        ageSex.put("35 to 44 Male", 9.38f);
        ageSex.put("35 to 44 Female", 8.37f);
        ageSex.put("45 to 54 Male", 6.84f);
        ageSex.put("45 to 54 Female", 6.61f);
        ageSex.put("55 to 64 Male", 5.08f);
        ageSex.put("55 to 64 Female", 5.57f);
        ageSex.put("65 above Male", 4.35f);
        ageSex.put("65 above Female", 5.31f);

        return ageSex;
    }

    public HashMap<String, Float> getRegionMap() {

        region.put("I: Ilocos Region", 3.76f);
        region.put("II: Cagayan Valley", 4.88f);
        region.put("III: Central Luzon", 10.09f);
        region.put("IV‑A: CALABARZON", 17.79f);
        region.put("IV‑B: MIMAROPA", 1.32f);
        region.put("V: Bicol Region", 1.84f);
        region.put("VI: Western Visayas", 5.44f);
        region.put("VII: Central Visayas", 5.57f);
        region.put("VIII: Eastern Visayas", 1.93f);
        region.put("IX: Zamboanga Peninsula", 1.88f);
        region.put("X: Northern Mindanao", 3.08f);
        region.put("XI: Davao Region", 3.84f);
        region.put("XII: SOCCSKSARGEN", 2.14f);
        region.put("XIII: Caraga", 1.81f);
        region.put("NCR", 30.52f);
        region.put("CAR", 3.34f);
        region.put("BARMM", 0.69f);

        return region;
    }
}
