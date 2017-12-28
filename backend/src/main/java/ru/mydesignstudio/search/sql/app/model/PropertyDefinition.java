package ru.mydesignstudio.search.sql.app.model;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Property definition.
 */
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
@XmlAccessorType(XmlAccessType.FIELD)
public class PropertyDefinition {
	/**
	 * Property name.
	 */
	@XmlElement(name = "property", required = true)
	private String propertyName;
	/**
	 * Associated property column.
	 */
	@XmlElement(name = "column", required = true)
	private String propertyColumn;
    /**
     * Property type.
     */
    @XmlElement(name = "type")
	private PropertyType propertyType = PropertyType.STRING;
    /**
     * Is property can be used in lookup.
     */
    @XmlElement(name = "display-property", defaultValue = "false")
	private boolean isDisplayProperty = false;
    /**
     * Is element primary key.
     */
    @XmlElement(name = "primary-key", required = false, defaultValue = "false")
    private boolean isPrimaryKey = false;
    /**
     * It {propertyType} is {REFERENCE}, this is a reference to other type.
     */
    @XmlElement(name = "reference", required = false)
	private TypeReference typeReference;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyColumn() {
        return propertyColumn;
    }

    public void setPropertyColumn(String propertyColumn) {
        this.propertyColumn = propertyColumn;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public boolean isDisplayProperty() {
        return isDisplayProperty;
    }

    public void setDisplayProperty(boolean displayProperty) {
        isDisplayProperty = displayProperty;
    }

    public TypeReference getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(TypeReference typeReference) {
        this.typeReference = typeReference;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyDefinition that = (PropertyDefinition) o;

        if (!getPropertyName().equals(that.getPropertyName())) return false;
        if (!getPropertyColumn().equals(that.getPropertyColumn())) return false;
        return getPropertyType() == that.getPropertyType();
    }

    @Override
    public int hashCode() {
        int result = getPropertyName().hashCode();
        result = 31 * result + getPropertyColumn().hashCode();
        result = 31 * result + getPropertyType().hashCode();
        return result;
    }
}
