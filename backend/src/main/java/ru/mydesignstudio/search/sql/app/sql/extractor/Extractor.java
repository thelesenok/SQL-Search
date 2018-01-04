package ru.mydesignstudio.search.sql.app.sql.extractor;

public interface Extractor<T> {
    T extract(String sourceSql);
}
