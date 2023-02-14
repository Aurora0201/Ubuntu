package com.javaframework.mybatis.bean;

public class Car {
    private Long id;
    private Integer carNum;
    private String brand;
    private Double guidePrice;
    private String productTime;
    private String carType;

    public Car() {

    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carNum=" + carNum +
                ", brand='" + brand + '\'' +
                ", guidePrice=" + guidePrice +
                ", productTime='" + productTime + '\'' +
                ", carType='" + carType + '\'' +
                '}';
    }

    public Car(Long id, Integer carNum, String brand, Double guidePrice, String productTime, String carType) {
        this.id = id;
        this.carNum = carNum;
        this.brand = brand;
        this.guidePrice = guidePrice;
        this.productTime = productTime;
        this.carType = carType;
    }

    //setters and getters...

    public Long getId() {
        return id;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
