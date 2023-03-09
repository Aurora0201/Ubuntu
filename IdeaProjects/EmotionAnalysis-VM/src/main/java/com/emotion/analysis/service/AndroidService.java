package com.emotion.analysis.service;

public interface AndroidService{
    /**
     * 获取QR
     */
    void getQR();

    /**
     * 数据分析与清洗
     */
    void dataAnalysis();

    /**
     * 数据上传
     */
    void dataUpload();
}
