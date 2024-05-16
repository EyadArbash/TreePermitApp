package treePermit.model;
import javax.persistence.*;

@Entity
@Table(name = "request_check")
public class RequestCheck {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requestId")
    private Long requestId;

    @Column(name = "zustaendigkeit")
    private boolean zustaendigkeit;

    @Column(name = "vollstaendigkeit")
    private boolean vollstaendigkeit;

    @Column(name = "vorhaben_umgesetzt")
    private boolean vorhabenUmgesetzt;

    @Column(name = "voraussetzungen_erfuellt")
    private boolean voraussetzungenErfuellt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public boolean isZustaendigkeit() {
		return zustaendigkeit;
	}

	public void setZustaendigkeit(boolean zustaendigkeit) {
		this.zustaendigkeit = zustaendigkeit;
	}

	public boolean isVollstaendigkeit() {
		return vollstaendigkeit;
	}

	public void setVollstaendigkeit(boolean vollstaendigkeit) {
		this.vollstaendigkeit = vollstaendigkeit;
	}

	public boolean isVorhabenUmgesetzt() {
		return vorhabenUmgesetzt;
	}

	public void setVorhabenUmgesetzt(boolean vorhabenUmgesetzt) {
		this.vorhabenUmgesetzt = vorhabenUmgesetzt;
	}

	public boolean isVoraussetzungenErfuellt() {
		return voraussetzungenErfuellt;
	}

	public void setVoraussetzungenErfuellt(boolean voraussetzungenErfuellt) {
		this.voraussetzungenErfuellt = voraussetzungenErfuellt;
	}

    
}
