package win.jieblog.questionnaire.enums;

public enum  MqDestinationName {
    NOTIFY("notify");
    private String  name;

    public String getName() {
        return name;
    }

    MqDestinationName(String name) {
        this.name = name;
    }
}
