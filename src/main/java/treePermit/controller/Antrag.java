package treePermit.controller;

public class Antrag {
    private String nummer;
    private String bezeichnung;
    private String stadt; // Neue Variable für die Stadt hinzugefügt
    private String status;

    public Antrag(String nummer, String bezeichnung, String stadt, String status) {
        this.nummer = nummer;
        this.bezeichnung = bezeichnung;
        this.stadt = stadt;
        this.status = status;
    }

    // Getter und Setter für die Stadt hinzugefügt
    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    // Die vorhandenen Getter und Setter bleiben unverändert
    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
