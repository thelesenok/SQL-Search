package ru.mydesignstudio.search.sql.app.sql.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ForeignKeysExtractor implements Extractor<Collection<ForeignKeyInfo>> {
    private static final Pattern FK_PATTERN =
            Pattern.compile("CONSTRAINT \\`(.*)\\` FOREIGN KEY \\(\\`(.*)\\`\\) REFERENCES \\`(.*)\\` \\(\\`(.*)\\`\\) ON");

    @Override
    public Collection<ForeignKeyInfo> extract(String sourceSql) {
        Validations.assertTrue(StringUtils.isNoneBlank(sourceSql), "SQL wasn't provided");

        final Matcher matcher = FK_PATTERN.matcher(sourceSql);
        final Collection<ForeignKeyInfo> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(new ForeignKeyInfo(
                    matcher.group(2),
                    matcher.group(3)
            ));
        }
        return result;
    }
}
