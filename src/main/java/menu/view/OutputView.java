package menu.view;

import java.util.List;
import menu.domain.DayOfWeek;
import menu.dto.CoachNames;
import menu.dto.RecommendedResults;

public class OutputView {

    private final static String RESULT_FORMAT = "[ %s | %s | %s | %s | %s | %s ]";

    public OutputView() {
    }

    public void printErrorMsg(String msg) {
        System.out.println(msg);
    }

    public void printStartMsg() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
    }

    public void printReadCoachesName() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void printRestrictionOfCoach(String coach) {
        System.out.println();
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요.", coach);
        System.out.println();
    }

    public void printResults(RecommendedResults results, CoachNames names) {
        System.out.println();
        System.out.println("메뉴 추천 결과입니다.");

        printDayOfWeek();                           // 요일 헤더
        printDayOfCategories(results);              // 카테고리 라인
        printDayOfMenus(results, names.names());    // 코치별 메뉴 라인
    }

    private void printDayOfWeek() {
        System.out.printf(RESULT_FORMAT, "구분",
                DayOfWeek.of(1),
                DayOfWeek.of(2),
                DayOfWeek.of(3),
                DayOfWeek.of(4),
                DayOfWeek.of(5)
        );
        System.out.println();
    }

    private void printDayOfCategories(RecommendedResults results) {
        System.out.printf(RESULT_FORMAT, "카테고리",
                results.category(1),
                results.category(2),
                results.category(3),
                results.category(4),
                results.category(5)
        );
        System.out.println();
    }

    private void printDayOfMenus(RecommendedResults results, List<String> names) {
        for (String name : names) {
            System.out.printf(RESULT_FORMAT, name,
                    results.menu(1, name),
                    results.menu(2, name),
                    results.menu(3, name),
                    results.menu(4, name),
                    results.menu(5, name)
            );
            System.out.println();
        }
    }

}
