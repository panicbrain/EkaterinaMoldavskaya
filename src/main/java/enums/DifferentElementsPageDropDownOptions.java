package enums;

public enum DifferentElementsPageDropDownOptions {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow");

    public String getText() {
        return text;
    }

    private String text;

    DifferentElementsPageDropDownOptions(String text) {
        this.text = text;
    }
}
