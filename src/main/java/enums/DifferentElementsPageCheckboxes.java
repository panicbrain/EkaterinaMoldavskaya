package enums;

public enum DifferentElementsPageCheckboxes {
    WATER("Water"),
    EARTH("Earth"),
    WIND("Wind"),
    FIRE("Fire");

    public String getText() {
        return text;
    }

    private String text;

    DifferentElementsPageCheckboxes(String text) {
        this.text = text;
    }
}
