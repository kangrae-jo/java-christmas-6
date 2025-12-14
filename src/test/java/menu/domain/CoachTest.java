package menu.domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachTest {

    @Test
    void 코치는_최대_2개의_메뉴를_먹지못한다() {
        Coach coach = Coach.register("제이슨");
        assertThatThrownBy(() -> coach.addRestrictions(List.of("우동", "스시", "뇨끼")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"토미토미토미", "제임스제임스"})
    void 코치_이름은_2에서_4자_이다(String name) {
        assertThatThrownBy(() -> Coach.register(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
