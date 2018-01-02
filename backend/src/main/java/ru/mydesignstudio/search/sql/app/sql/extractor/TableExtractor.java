package ru.mydesignstudio.search.sql.app.sql.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.sql.extractor.Extractor;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TableExtractor implements Extractor<String> {
    private static final Pattern TABLE_PATTERN = Pattern.compile("CREATE TABLE \\`(.*)\\` \\(");

    @Override
    public String extract(String sourceSql) {
        Validations.assertTrue(StringUtils.isNoneBlank(sourceSql), "SQL wasn't provided");

        final Matcher matcher = TABLE_PATTERN.matcher(sourceSql);
        if (!matcher.find()) {
            throw new RuntimeException(String.format(
                    "Can't find table declaration in SQL %s",
                    sourceSql
            ));
        }
        return matcher.group(1);
    }
}
