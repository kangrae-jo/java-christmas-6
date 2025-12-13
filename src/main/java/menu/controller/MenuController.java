package menu.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import menu.domain.Coach;
import menu.dto.CoachNames;
import menu.dto.RecommendedResults;
import menu.service.PickService;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PickService pickService;

    public MenuController(InputView inputView, OutputView outputView, PickService pickService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pickService = pickService;
    }

    public void run() {
        outputView.printStartMsg();
        outputView.printReadCoachesName();
        List<Coach> coaches = readCoachesName();
        readRestrictions(coaches);

        RecommendedResults results = makeMenuResult(coaches);
        outputView.printResults(results, CoachNames.from(coaches));
    }

    private List<Coach> readCoachesName() {
        return retryUntilValid(() -> {
            String names = inputView.readCoachesName();
            List<Coach> coaches = Arrays.stream(names.split(","))
                    .map(Coach::register)
                    .toList();

            if (coaches.size() < 2 || coaches.size() > 5) {
                throw new IllegalArgumentException("[ERROR] 코치는 최소 2명, 최대 5명까지 식사를 함께 합니다.");
            }

            return coaches;
        });
    }

    private void readRestrictions(List<Coach> coaches) {
        for (Coach coach : coaches) {
            retryUntilValid(() -> {
                outputView.printRestrictionOfCoach(coach.name());
                String restrictions = inputView.readRestrictions();
                return coach.addRestrictions(Arrays.stream(restrictions.split(",")).toList());
            });
        }
    }

    private RecommendedResults makeMenuResult(List<Coach> coaches) {
        RecommendedResults results = new RecommendedResults();
        results.init(coaches);

        for (int day = 1; day <= 5; day++) {
            pickService.makeRecommend(results, coaches);
        }

        return results;
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMsg(e.getMessage());
            }
        }
    }

}
