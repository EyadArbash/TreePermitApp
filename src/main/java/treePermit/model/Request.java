package treePermit.model;


import javax.persistence.*;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import treePermit.enums.RequestStatus;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "request")
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "familienname")
    private String familienname;

    @Column(name = "vornamen")
    private String vornamen;

    @Column(name = "strasse")
    private String strasse;

    @Column(name = "hausnummer")
    private String hausnummer;

    @Column(name = "plz")
    private String plz;

    @Column(name = "ort")
    private String ort;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "email")
    private String email;

    @Column(name = "art_gewaechs")
    private String artGewaechs;

    @Column(name = "baum_ausserhalb_wald")
    private Boolean baumAusserhalbWald;

    @Column(name = "baum_ausserhalb_kurzumtriebsplantage")
    private Boolean baumAusserhalbKurzumtriebsplantage;

    @Column(name = "baum_ausserhalb_gaertnerisch_genutzte_flaeche")
    private Boolean baumAusserhalbGaertnerischGenutzteFlaeche;

    @Column(name = "strasse_standort")
    private String strasseStandort;

    @Column(name = "hausnummer_standort")
    private String hausnummerStandort;

    @Column(name = "plz_standort")
    private String plzStandort;

    @Column(name = "ort_standort")
    private String ortStandort;

    @Column(name = "startdatum_vorhaben")
    private LocalDate startdatumVorhaben;

    @Column(name = "enddatum_vorhaben")
    private LocalDate enddatumVorhaben;

    @Column(name = "beschreibung_vorhaben")
    private String beschreibungVorhaben;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status = RequestStatus.OFFEN;;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Notification notification;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "rejected")
    private Boolean rejected;
  
    public Request() {}

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public Boolean getBaumAusserhalbWald() {
        return baumAusserhalbWald;
    }

    public void setBaumAusserhalbWald(Boolean baumAusserhalbWald) {
        this.baumAusserhalbWald = baumAusserhalbWald;
    }

    public Boolean getBaumAusserhalbKurzumtriebsplantage() {
        return baumAusserhalbKurzumtriebsplantage;
    }

    public void setBaumAusserhalbKurzumtriebsplantage(Boolean baumAusserhalbKurzumtriebsplantage) {
        this.baumAusserhalbKurzumtriebsplantage = baumAusserhalbKurzumtriebsplantage;
    }

    public Boolean getBaumAusserhalbGaertnerischGenutzteFlaeche() {
        return baumAusserhalbGaertnerischGenutzteFlaeche;
    }

    public void setBaumAusserhalbGaertnerischGenutzteFlaeche(Boolean baumAusserhalbGaertnerischGenutzteFlaeche) {
        this.baumAusserhalbGaertnerischGenutzteFlaeche = baumAusserhalbGaertnerischGenutzteFlaeche;
    }

    public String getStrasseStandort() {
        return strasseStandort;
    }

    public void setStrasseStandort(String strasseStandort) {
        this.strasseStandort = strasseStandort;
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

    public LocalDate getStartdatumVorhaben() {
        return startdatumVorhaben;
    }

    public void setStartdatumVorhaben(LocalDate startdatumVorhaben) {
        this.startdatumVorhaben = startdatumVorhaben;
    }

    public LocalDate getEnddatumVorhaben() {
        return enddatumVorhaben;
    }

    public void setEnddatumVorhaben(LocalDate enddatumVorhaben) {
        this.enddatumVorhaben = enddatumVorhaben;
    }

    public String getBeschreibungVorhaben() {
        return beschreibungVorhaben;
    }

    public void setBeschreibungVorhaben(String beschreibungVorhaben) {
        this.beschreibungVorhaben = beschreibungVorhaben;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
    

}
