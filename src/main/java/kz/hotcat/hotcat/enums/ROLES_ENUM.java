package kz.hotcat.hotcat.enums;

public enum ROLES_ENUM {
    ADMIN("ADMIN"),
    USER("USER");

    public final String label;

    private ROLES_ENUM(String label) {
        this.label = label;
    }
}
