package ru.mydesignstudio.search.sql.app.model;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Type (actually, table) definition. Stores information about table name
 * and it's properties
 * 
 * @author Aleksandr_Barmin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class TypeDefinition {
	/**
	 * Description of the type.
	 */
	@XmlElement(name = "type", required = true)
	private String typeName;
	/**
	 * Associated table name.
	 */
	@XmlElement(name = "table", required = true)
	private String tableName;
	/**
	 * Collection of properties.
	 */
	@XmlElementWrapper(name = "properties")
    @XmlElement(name = "property")
	private Collection<PropertyDefinition> properties = new ArrayList<>();
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Collection<PropertyDefinition> getProperties() {
		return properties;
	}
	public void setProperties(Collection<PropertyDefinition> properties) {
		this.properties = properties;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeDefinition that = (TypeDefinition) o;

        if (!typeName.equals(that.typeName)) return false;
        return tableName.equals(that.tableName);
    }

    @Override
    public int hashCode() {
        int result = typeName.hashCode();
        result = 31 * result + tableName.hashCode();
        return result;
    }
}
