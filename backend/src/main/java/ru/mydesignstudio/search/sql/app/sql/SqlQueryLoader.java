package ru.mydesignstudio.search.sql.app.sql;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.sql.pma.PmaXmlHandler;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Collection;

@Component
public class SqlQueryLoader {
    public Collection<String> loadQueries(final File xmlSqlSource) {
        Validations.assertNotNull(xmlSqlSource, "XML source wasn't provided");

        try {
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser parser = factory.newSAXParser();
            final PmaXmlHandler handler = new PmaXmlHandler();
            parser.parse(xmlSqlSource, handler);
            final Collection<String> extractedQueries = handler.getQueries();
            return extractedQueries;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
