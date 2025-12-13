package menu.domain;

public enum DayOfWeek {

    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일");

    private final String korean;

    DayOfWeek(String korean) {
        this.korean = korean;
    }

    public static String of(int index) {
        if (index < 1 || index > values().length) {
            throw new IllegalArgumentException("[ERROR] 요일 인덱스는 1~5 사이여야 합니다.");
        }
        return values()[index - 1].korean;
    }

}
