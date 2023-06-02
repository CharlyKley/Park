package com.example.park.models;

public class ParquearCarroModelo {
    private String id_Carro;
    private String nombre_Carro;
    private String numero_Carro;
    private String placa_Carro;
    private String tipo_Vehiculo_Carro;
    private String tarifa_Carro;
    private long tiempo_Carro;
    private String userId_Carro;

    private String status_Carro;
    public ParquearCarroModelo() {

    }

    public ParquearCarroModelo(String id_Carro, String nombre_Carro, String numero_Carro,
                               String placa_Carro, String tipo_Vehiculo_Carro, String tarifa_Carro,
                               long tiempo_Carro, String userId_Carro, String status_Carro) {
        this.id_Carro = id_Carro;
        this.nombre_Carro = nombre_Carro;
        this.numero_Carro = numero_Carro;
        this.placa_Carro = placa_Carro;
        this.tipo_Vehiculo_Carro = tipo_Vehiculo_Carro;
        this.tarifa_Carro = tarifa_Carro;
        this.tiempo_Carro = tiempo_Carro;
        this.userId_Carro = userId_Carro;
        this.status_Carro = status_Carro;
    }

    public String getStatus_Carro() {
        return status_Carro;
    }

    public void setStatus_Carro(String status_Carro) {
        this.status_Carro = status_Carro;
    }

    public String getId_Carro() {
        return id_Carro;
    }

    public void setId_Carro(String id_Carro) {
        this.id_Carro = id_Carro;
    }

    public String getNombre_Carro() {
        return nombre_Carro;
    }

    public void setNombre_Carro(String nombre_Carro) {
        this.nombre_Carro = nombre_Carro;
    }

    public String getNumero_Carro() {
        return numero_Carro;
    }

    public void setNumero_Carro(String numero_Carro) {
        this.numero_Carro = numero_Carro;
    }

    public String getPlaca_Carro() {
        return placa_Carro;
    }

    public void setPlaca_Carro(String placa_Carro) {
        this.placa_Carro = placa_Carro;
    }

    public String getTipo_Vehiculo_Carro() {
        return tipo_Vehiculo_Carro;
    }

    public void setTipo_Vehiculo_Carro(String tipo_Vehiculo_Carro) {
        this.tipo_Vehiculo_Carro = tipo_Vehiculo_Carro;
    }

    public String getTarifa_Carro() {
        return tarifa_Carro;
    }

    public void setTarifa_Carro(String tarifa_Carro) {
        this.tarifa_Carro = tarifa_Carro;
    }

    public long getTiempo_Carro() {
        return tiempo_Carro;
    }

    public void setTiempo_Carro(long tiempo_Carro) {
        this.tiempo_Carro = tiempo_Carro;
    }

    public String getUserId_Carro() {
        return userId_Carro;
    }

    public void setUserId_Carro(String userId_Carro) {
        this.userId_Carro = userId_Carro;
    }
}
