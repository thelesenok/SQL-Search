package ru.mydesignstudio.search.sql.app.rest.transformer;

/**
 * Transformer interface.
 * @param <S> source type
 * @param <D> destination type
 */
public interface Transformer<S, D> {
    /**
     * Transform source instance to destination type.
     *
     * @param source source instance.
     * @return
     */
    D transform(S source);
}
