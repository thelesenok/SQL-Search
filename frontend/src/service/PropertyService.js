import PropertyValueType from "./PropertyValueType";
import LogicalOperation from './LogicalOperation';

// this is mock functions
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

const createEmptyAttribute = (properties) => ({
    index: getIndex(properties),
    availableTypes: [],
    availableProps: [],
    availableOperations: [],
    typesLoaded: false,
    propsLoaded: false,
    operationsLoaded: false,
    selectedType: null,
    selectedProp: null,
    selectedOperation: null,
    value: null
});

const PropertyService = {};

PropertyService.createAttribute = (attributes) => {
    return new Promise((resolve) => {
        resolve(createEmptyAttribute(attributes));
    });
};

PropertyService.getAvailableTypes = (selectType, searchTypes) => {
    // mock implementation
    return new Promise(resolve => {
        setTimeout(() => {
            resolve([
                +selectType,
                3 - +selectType
            ]);
        }, 500);
    });
};

PropertyService.getAvailableProperties = (selectedType, searchTypes) => {
    // mock implementation
    const props = {
        1: [
            {
                valueType: PropertyValueType.STRING,
                label: "Some property 11",
                value: "some_property_1"
            }, {
                valueType: PropertyValueType.STRING,
                label: "Some property 12",
                value: "some_property_2"
            }
        ],
        2: [
            {
                valueType: PropertyValueType.STRING,
                label: "Some property 21",
                value: "some_property_3"
            }, {
                valueType: PropertyValueType.STRING,
                label: "Some property 22",
                value: "some_property_4"
            }
        ]
    };
    return new Promise(resolve => {
        setTimeout(() => {
            resolve(props[selectedType]);
        }, 500);
    });
};

PropertyService.getAvailableLogicalOperations = (selectedProp, searchTypes) => {
    // mock implementation
    return new Promise(resolve => {
        setTimeout(() => {
            resolve([
                LogicalOperation.MORE_THAN,
                LogicalOperation.EQUALS
            ]);
        }, 500);
    });
};

export default PropertyService;