package menu.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import menu.domain.Coach;
import menu.dto.MenuResult;
import menu.dto.Recommend;
import menu.view.InputView;

public class MenuController {

    private final InputView inputView;

    public MenuController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        List<Coach> coaches = readCoachesName();
        readRestrictions(coaches);

        makeMenuResult();
    }

    private List<Coach> readCoachesName() {
        return retryUntilValid(() -> {
            String names = inputView.readCoachesName();
            return Arrays.stream(names.split(","))
                    .map(Coach::register)
                    .toList();
        });
    }

    private void readRestrictions(List<Coach> coaches) {
        for (Coach coach : coaches) {
            retryUntilValid(() -> {
                String restrictions = inputView.readRestrictions();
                return coach.addRestrictions(Arrays.stream(restrictions.split(",")).toList());
            });
        }
    }

    private void makeMenuResult() {
        MenuResult menuResult = new MenuResult();
        for (int day = 1; day <= 5; day++) {
            Recommend recommend = new Recommend();
            Category category = Category.pick(Randoms.pickNumberInRange(1, 5));

            menuResult.put(DayOfWeek.of(day), recommend);
        }
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
