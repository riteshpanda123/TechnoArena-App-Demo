package com.example.retrofitappdemo;

import android.os.CpuUsageInfo;

public class DeviceData {
    private String devname;
    private String softwareversion;
    private String operatingsystem;
    private String processor;
    private String CPU;
    private String GPU;
    private String display;
    private String storage_ram;
    private String maincamera;
    private String frontcamera;
    private String videorecording;
    private String sensors;
    private String battery;
    private String imgurl;

    public DeviceData(String devname, String softwareversion, String operatingsystem, String processor, String CPU, String GPU, String display, String storage_ram, String maincamera, String frontcamera, String videorecording, String sensors, String battery, String imgurl) {
        this.devname = devname;
        this.softwareversion = softwareversion;
        this.operatingsystem = operatingsystem;
        this.processor = processor;
        this.CPU = CPU;
        this.GPU = GPU;
        this.display = display;
        this.storage_ram = storage_ram;
        this.maincamera = maincamera;
        this.frontcamera = frontcamera;
        this.videorecording = videorecording;
        this.sensors = sensors;
        this.battery = battery;
        this.imgurl = imgurl;
    }

    public String getDevname() {
        return devname;
    }

    public String getSoftwareversion() {
        return softwareversion;
    }

    public String getOperatingsystem() {
        return operatingsystem;
    }

    public String getProcessor() {
        return processor;
    }

    public String getCPU() {
        return CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public String getDisplay() {
        return display;
    }

    public String getStorage_ram() {
        return storage_ram;
    }

    public String getMaincamera() {
        return maincamera;
    }

    public String getFrontcamera() {
        return frontcamera;
    }

    public String getVideorecording() {
        return videorecording;
    }

    public String getSensors() {
        return sensors;
    }

    public String getBattery() {
        return battery;
    }

    public String getImgurl() {
        return imgurl;
    }
}
