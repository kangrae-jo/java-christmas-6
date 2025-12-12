package menu.domain;

import java.util.List;

public class Menu {

    private final String name;

    public Menu(String name) {
        this.name = name;
    }

    public static List<Menu> from(List<String> names) {
        return names.stream()
                .map(Menu::new)
                .toList();
    }

}
