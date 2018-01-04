package ru.mydesignstudio.search.sql.app.model;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Data model definition.
 */
@XmlRootElement(name = "definition")
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
@XmlAccessorType(XmlAccessType.FIELD)
public class ModelDefinition {
    /**
     * Types definitions.
     */
    @XmlElement(name = "type")
	private Collection<TypeDefinition> types = new ArrayList<>();

    public Collection<TypeDefinition> getTypes() {
        return types;
    }

    public void setTypes(Collection<TypeDefinition> types) {
        this.types = types;
    }
}
