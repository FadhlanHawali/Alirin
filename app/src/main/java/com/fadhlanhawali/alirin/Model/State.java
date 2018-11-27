package com.fadhlanhawali.alirin.Model;

public class State {
    public Long humidity;
    public Long lampu;
    public Long pompa;


    public State() {
    }

    public State(Long durasi_sinar, Long humidity, Long lampu, Long pompa) {
       this.humidity = humidity;
       this.lampu = lampu;
       this.pompa = pompa;
    }
}
