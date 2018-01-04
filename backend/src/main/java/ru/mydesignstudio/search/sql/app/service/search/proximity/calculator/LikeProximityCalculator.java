package ru.mydesignstudio.search.sql.app.service.search.proximity.calculator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component("likeCalculator")
public class LikeProximityCalculator implements ProximityCalculator {
    @Override
    public double calculate(SqlResultSetRow row, SearchRequest.SearchAttribute attribute) {
        Validations.assertNotNull(row, "Row wasn't provided");
        Validations.assertNotNull(attribute, "Attribute wasn't provided");

        // по какому свойству ищем
        final String propertyToCheck = attribute.getProperty();
        // что пользователь просил
        final String userInput = attribute.getValue().toLowerCase();
        // что нашлось
        final String propertyValue = row.getValue(propertyToCheck).toLowerCase();

        if (StringUtils.equalsAnyIgnoreCase(userInput, propertyValue)) {
            return 1;
        }

        if (LogicalOperation.LIKE.equals(attribute.getLogicalOperation()) ||
                LogicalOperation.CONTAINS.equals(attribute.getLogicalOperation())) {
            return (double) userInput.length() / propertyValue.length();
        }

        /**
         * Надо посчитать количество общих букв между тем, что спросил
         * пользователь и тем, что нашлось
         */
        int validCharacters = 0;
        for (int i = 0; i < userInput.length(); i++) {
            final char targetChar = userInput.charAt(i);
            if (propertyValue.charAt(i) == targetChar) {
                validCharacters++;
            }
        }
        return (double) validCharacters / userInput.length();
    }
}
