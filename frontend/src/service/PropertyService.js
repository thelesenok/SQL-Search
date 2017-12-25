// this is mock function
import PropertyValueType from "./PropertyValueType";

const getIndex = (properties = []) => {
    let index = 0;
    properties.forEach(prop => {
        if (prop.index > index) {
            index = prop.index
        }
    });
    index++;
    return index;
};

const PropertyService = {};

PropertyService.createProperty = (selectType, searchTypes, properties) => {
    // mock implementation
    return new Promise((resolve) => {
        resolve(
            {
                index: getIndex(properties),
                availableTypes: [],
                availableProps: [],
                valueType: PropertyValueType.STRING
            }
        );
    });
};

export default PropertyService;