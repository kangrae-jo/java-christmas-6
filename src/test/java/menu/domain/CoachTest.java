package menu.domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CoachTest {


    @Test
    void 코치는_최대_2개의_메뉴를_먹지못한다() {
        assertThatThrownBy(() -> {
                    Coach coach = new Coach("제이슨", List.of("우동", "스시", "뇨끼"));
                }
        ).isInstanceOf(IllegalArgumentException.class);

    }

}