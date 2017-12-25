import PropertyValueType from "./PropertyValueType";

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
    propsLoaded: false,
    selectedType: null,
    selectedProp: null,
    value: null
});

const PropertyService = {};

PropertyService.createAttribute = (selectType, searchTypes, attributes) => {
    // mock implementation
    return new Promise((resolve) => {
        // actually we should load only available types here
        setTimeout(() => {
            resolve(Object.assign(
                createEmptyAttribute(attributes),
                {
                    availableTypes: [selectType, 3 - selectType]
                }
            ));
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

export default PropertyService;