package com.fadhlanhawali.alirin.Model;

public class Control {
    private Long automated;
    private Long durasi_sinar;
    private String jam_sinar;
    private String jam_siram;
    private Long max_humidity;

    public Control(){

    }
    public Control(Long automated, Long durasi_sinar, String jam_sinar, String jam_siram, Long max_humidity) {
        this.automated = automated;
        this.durasi_sinar = durasi_sinar;
        this.jam_sinar = jam_sinar;
        this.jam_siram = jam_siram;
        this.max_humidity = max_humidity;
    }

    public Long getAutomated() {
        return automated;
    }

    public void setAutomated(Long automated) {
        this.automated = automated;
    }

    public Long getDurasi_sinar() {
        return durasi_sinar;
    }

    public void setDurasi_sinar(Long durasi_sinar) {
        this.durasi_sinar = durasi_sinar;
    }

    public String getJam_sinar() {
        return jam_sinar;
    }

    public void setJam_sinar(String jam_sinar) {
        this.jam_sinar = jam_sinar;
    }

    public String getJam_siram() {
        return jam_siram;
    }

    public void setJam_siram(String jam_siram) {
        this.jam_siram = jam_siram;
    }

    public Long getMax_humidity() {
        return max_humidity;
    }

    public void setMax_humidity(Long max_humidity) {
        this.max_humidity = max_humidity;
    }
}
