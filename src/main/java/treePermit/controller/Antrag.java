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

    @Column(name = "familienname")
    private String familienname;

    @Column(name = "vornamen")
    private String vornamen;

    @Column(name = "straße")
    private String straße;

    @Column(name = "hausnummer")
    private String hausnummer;

    @Column(name = "plz")
    private String plz;

    @Column(name = "ort")
    private String ort;

    @Column(name = "email")
    private String email;

    @Column(name = "art_gewaechs")
    private String artGewaechs;

    @Column(name = "straße_standort")
    private String straßeStandort;

    @Column(name = "hausnummer_standort")
    private String hausnummerStandort;

    @Column(name = "plz_standort")
    private String plzStandort;

    @Column(name = "ort_standort")
    private String ortStandort;

    @Column(name = "startdatum_vorhaben")
    private String startdatumVorhaben;

    @Column(name = "enddatum_vorhaben")
    private String enddatumVorhaben;

    @Column(name = "beschreibung_vorhaben")
    private String beschreibungVorhaben;

    // Standardkonstruktor wird für JPA benötigt
    public Antrag() {}

    // Konstruktor mit Parametern
    public Antrag(String familienname, String vornamen, String straße, String hausnummer, String plz, String ort,
                  String email, String artGewaechs, String straßeStandort, String hausnummerStandort, String plzStandort,
                  String ortStandort, String startdatumVorhaben, String enddatumVorhaben, String beschreibungVorhaben) {
        this.familienname = familienname;
        this.vornamen = vornamen;
        this.straße = straße;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.email = email;
        this.artGewaechs = artGewaechs;
        this.straßeStandort = straßeStandort;
        this.hausnummerStandort = hausnummerStandort;
        this.plzStandort = plzStandort;
        this.ortStandort = ortStandort;
        this.startdatumVorhaben = startdatumVorhaben;
        this.enddatumVorhaben = enddatumVorhaben;
        this.beschreibungVorhaben = beschreibungVorhaben;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilienname() {
        return familienname;
    }

    public void setFamilienname(String familienname) {
        this.familienname = familienname;
    }

    public String getVornamen() {
        return vornamen;
    }

    public void setVornamen(String vornamen) {
        this.vornamen = vornamen;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArtGewaechs() {
        return artGewaechs;
    }

    public void setArtGewaechs(String artGewaechs) {
        this.artGewaechs = artGewaechs;
    }

    public String getStraßeStandort() {
        return straßeStandort;
    }

    public void setStraßeStandort(String straßeStandort) {
        this.straßeStandort = straßeStandort;
    }

    public String getHausnummerStandort() {
        return hausnummerStandort;
    }

    public void setHausnummerStandort(String hausnummerStandort) {
        this.hausnummerStandort = hausnummerStandort;
    }

    public String getPlzStandort() {
        return plzStandort;
    }

    public void setPlzStandort(String plzStandort) {
        this.plzStandort = plzStandort;
    }

    public String getOrtStandort() {
        return ortStandort;
    }

    public void setOrtStandort(String ortStandort) {
        this.ortStandort = ortStandort;
    }

    public String getStartdatumVorhaben() {
        return startdatumVorhaben;
    }

    public void setStartdatumVorhaben(String startdatumVorhaben) {
        this.startdatumVorhaben = startdatumVorhaben;
    }

    public String getEnddatumVorhaben() {
        return enddatumVorhaben;
    }

    public void setEnddatumVorhaben(String enddatumVorhaben) {
        this.enddatumVorhaben = enddatumVorhaben;
    }

    public String getBeschreibungVorhaben() {
        return beschreibungVorhaben;
    }

    public void setBeschreibungVorhaben(String beschreibungVorhaben) {
        this.beschreibungVorhaben = beschreibungVorhaben;
    }
}
