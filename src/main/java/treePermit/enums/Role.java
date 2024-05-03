package treePermit.enums;
public enum Role {
    USER, CLERK;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
