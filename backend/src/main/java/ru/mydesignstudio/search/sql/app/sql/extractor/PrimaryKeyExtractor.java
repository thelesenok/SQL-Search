package ru.mydesignstudio.search.sql.app.sql.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PrimaryKeyExtractor implements Extractor<String> {
    private static final Pattern PK_PATTERN = Pattern.compile("PRIMARY KEY \\(\\`(.*)\\`\\)");

    @Override
    public String extract(String sourceSql) {
        Validations.assertTrue(StringUtils.isNoneBlank(sourceSql), "SQL wasn't provided");

        final Matcher matcher = PK_PATTERN.matcher(sourceSql);
        if (!matcher.find()) {
            throw new RuntimeException(String.format(
                    "SQL %s has no primary key definition",
                    sourceSql
            ));
        }
        return matcher.group(1);
    }
}
