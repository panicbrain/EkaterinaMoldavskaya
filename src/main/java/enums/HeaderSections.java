package enums;

public enum HeaderSections {
    HOME("HOME"),
    CONTACT_FORM("CONTACT FORM"),
    SERVICE("SERVICE"),
    METAL_AND_COLORS("METALS & COLORS");


    public String getText() {
        return text;
    }

    private String text;

    HeaderSections(String text) {
        this.text = text;
    }
}
