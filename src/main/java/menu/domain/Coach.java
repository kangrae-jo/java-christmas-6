package menu.domain;

import java.util.List;

public class Coach {

    private final static int MAX_RESTRICTION_RANGE = 2;

    private final String name;
    private final List<Menu> restrictions;

    public Coach(String name, List<String> restrictions) {
        validateRestrictionRange(restrictions);
        this.restrictions = Menu.from(restrictions);
        this.name = name;
    }

    private void validateRestrictionRange(List<String> restrictions) {
        if (restrictions.size() > MAX_RESTRICTION_RANGE) {
            throw new IllegalArgumentException("[ERROR] 코치가 먹지 못하는 메뉴는 최대 2개 입니다.");
        }
    }

}
