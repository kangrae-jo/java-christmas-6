package menu.view;

import menu.dto.RecommendedResults;

public class OutputView {

    public OutputView() {
    }

    public void writeStartMsg() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
    }

    public void writeReadCoachesName() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void writeRestrictionOfCoach(String coach) {
        System.out.println();
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요.", coach);
        System.out.println();
    }

    public void writeResults(RecommendedResults results) {
        System.out.println();
        System.out.println("메뉴 추천 결과입니다.");
        results.printResults();
    }

    public void writeErrorMsg(String errorMsg) {
        System.out.println(errorMsg);
    }

}
