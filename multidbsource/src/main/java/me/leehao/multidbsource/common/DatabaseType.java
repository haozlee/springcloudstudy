package me.leehao.multidbsource.common;

public enum DatabaseType {
    TOTAL("total", "1"),
    PART1("part1", "2");

    private final String name;
    private final String value;

    DatabaseType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
