package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Component
public class FuzzyValueBuilder {
    private static final Character TOKEN = '_';

    public Collection<String> buildValues(final String originalValue, final int maskSize) {
        Validations.assertTrue(StringUtils.isNoneBlank(originalValue), "Original value wasn't provided");
        Validations.assertTrue(maskSize > 0, "Invalid mask size");

        if (maskSize >= originalValue.length()) {
            /**
             * маска длинее исходного значения
             */
            return Collections.singleton(StringUtils.repeat(TOKEN, maskSize));
        }
        final Collection<String> values = new HashSet<>();
        for (int start = 0; start < originalValue.length() - maskSize; start++) {
            List<Character> currentToken = createInitialToken(originalValue, start, maskSize);
            values.add(charListToString(currentToken));
            while (hasNextToken(currentToken, maskSize)) {
                currentToken = createNextToken(currentToken, originalValue);
                values.add(charListToString(currentToken));
            }
        }
        return values;
    }

    private List<Character> createNextToken(List<Character> currentValue, String originalValue) {
        /**
         * идем справа налево, ищем токен, который можно сдвинуть
         * здесь гарантировано есть такой токен, так как мы проверили
         */
        int tokenPosition = 0;
        for (int index = currentValue.size() - 2; index >= 0; index--) {
            if (TOKEN.equals(currentValue.get(index)) && !TOKEN.equals(currentValue.get(index + 1))) {
                tokenPosition = index;
                break;
            }
        }
        /**
         * мы узнали номер элемента, который двигаем направо
         * меняем этот элемент на значение из исходной строки,
         * в следующей позиции ставим токен
         */
        currentValue.remove(tokenPosition);
        currentValue.remove(tokenPosition);
        currentValue.add(tokenPosition, originalValue.charAt(tokenPosition));
        currentValue.add(tokenPosition + 1, TOKEN);
        return currentValue;
    }

    /**
     * Можно ли сгенерировать еще токен
     * @param currentValue - текущий токен
     * @param maskSize - сколько символов в токене
     * @return
     */
    private boolean hasNextToken(List<Character> currentValue, int maskSize) {
        /**
         * посчитаем, в скольких симолах с конца токен.
         * если совпадает, то больше нельзя
         */
        int tokens = 0;
        for (int i = currentValue.size() - 1; i >= 0; i--) {
            if (TOKEN.equals(currentValue.get(i))) {
                tokens++;
            } else {
                break;
            }
        }
        return tokens != maskSize;
    }

    /**
     * Создать исходный токен - заменяем первые символы на токен
     * @param originalValue - исходная строка
     * @param start - с какого символа начать токенизация
     * @param maskSize - сколько символов заменять
     * @return
     */
    private List<Character> createInitialToken(String originalValue, int start, int maskSize) {
        final List<Character> list = new LinkedList<>();
        for (int index = 0; index < start; index++) {
            list.add(originalValue.charAt(index));
        }
        for (int index = start; index < originalValue.length(); index++) {
            if (index < maskSize + start) {
                list.add(TOKEN);
            } else {
                list.add(originalValue.charAt(index));
            }
        }
        return list;
    }

    private String charListToString(List<Character> characterList) {
        final StringBuilder builder = new StringBuilder();
        for (Character character : characterList) {
            builder.append(character);
        }
        return builder.toString();
    }
}
