package treePermit.controller;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "antrag") // Der Tabellenname in der Datenbank
public class Antrag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Eindeutiger Identifier für jeden Antrag

    @Column(name = "nummer")
    private String nummer;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "stadt")
    private String stadt;

    @Column(name = "status")
    private String status;

    // Standardkonstruktor wird für JPA benötigt
    public Antrag() {}

    // Konstruktor mit Parametern
    public Antrag(String nummer, String bezeichnung, String stadt, String status) {
        this.nummer = nummer;
        this.bezeichnung = bezeichnung;
        this.stadt = stadt;
        this.status = status;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
