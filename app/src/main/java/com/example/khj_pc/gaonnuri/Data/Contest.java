package com.example.khj_pc.gaonnuri.Data;

public class Contest {
    private int ppl;
    private String name;
    
    public Contest(int ppl, String name) {
        this.ppl = ppl;
        this.name = name;
    }


    public int getPpl() {
        return ppl;
    }

    public void setPpl(int ppl) {
        this.ppl = ppl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
