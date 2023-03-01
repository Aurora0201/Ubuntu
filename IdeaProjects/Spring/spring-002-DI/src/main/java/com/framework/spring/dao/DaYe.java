package com.framework.spring.dao;

import java.util.Arrays;

public class DaYe {
    private String[] aiHao;
    private Woman[] women;

    @Override
    public String toString() {
        return "DaYe{" +
                "aiHao=" + Arrays.toString(aiHao) +
                ", women=" + Arrays.toString(women) +
                '}';
    }

    public void setAiHao(String[] aiHao) {
        this.aiHao = aiHao;
    }

    public void setWomen(Woman[] women) {
        this.women = women;
    }
}
