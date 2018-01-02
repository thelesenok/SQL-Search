package ru.mydesignstudio.search.sql.app.service.search.weight.calculator;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Collection;

@Component
public class DefaultWeightCalculator implements WeightCalculator {
    @Override
    public double calculate(SqlResultSetRow row) {
        Validations.assertNotNull(row, "Row wasn't provided");

        final Collection<Double> weights = row.getWeights();
        double fullWeight = 0;
        for (double weight : weights) {
            fullWeight += weight;
        }
        return (double) fullWeight / weights.size();
    }
}
