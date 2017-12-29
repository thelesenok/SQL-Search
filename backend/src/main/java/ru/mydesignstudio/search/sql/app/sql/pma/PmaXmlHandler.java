package ru.mydesignstudio.search.sql.app.sql.pma;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collection;

public class PmaXmlHandler extends DefaultHandler {
    private final Collection<String> queries = new ArrayList<>();
    private StringBuilder builder = new StringBuilder();

    private boolean elementStarted = false;

    public Collection<String> getQueries() {
        return queries;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementStarted = StringUtils.equalsAnyIgnoreCase(qName, "pma:table");
        if (elementStarted) {
            builder = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (StringUtils.equalsAnyIgnoreCase(qName, "pma:table")) {
            queries.add(builder.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementStarted) {
            final String sql = new String(ch, start, length);
            builder.append(sql);
        }
    }
}
