package enums;

public enum DifferentElementsPageRadioButtons {
    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    SELEN("Selen");

    public String getText() {
        return text;
    }

    private String text;

    DifferentElementsPageRadioButtons(String text) {
        this.text = text;
    }
}
