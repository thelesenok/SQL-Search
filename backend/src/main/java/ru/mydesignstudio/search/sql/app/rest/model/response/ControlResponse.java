package ru.mydesignstudio.search.sql.app.rest.model.response;

import java.util.ArrayList;
import java.util.Collection;

public class ControlResponse {
    private String controlType;
    private Collection<SelectItem> items = new ArrayList<>();

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public Collection<SelectItem> getItems() {
        return items;
    }

    public void setItems(Collection<SelectItem> items) {
        this.items = items;
    }

    public class SelectItem {
        private String label;
        private String value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
