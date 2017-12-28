import ValueType from './ValueType';
import axios from 'axios';

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
    valueTypeLoaded: false,
    valueType: null,
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
        axios.post('/types/available', {
            selectType: selectType,
            searchTypes: searchTypes
        }).then(response => {
            resolve(response.data.map(type => {
                return type.value;
            }));
        });
    });
};

PropertyService.getAvailableProperties = (selectedType, searchTypes) => {
    return new Promise(resolve => {
        axios.post('/types/properties', {
            requestedType: selectedType,
            searchTypes: searchTypes
        }).then(response => {
            resolve(response.data);
        });
    });
};

PropertyService.getAvailableLogicalOperations = (selectedType, selectedProp, searchTypes) => {
    // mock implementation
    return new Promise(resolve => {
        axios.post('/types/operations', {
            requestedType: selectedType,
            requestedProp: selectedProp,
            searchTypes: searchTypes
        }).then(response => {
            resolve(response.data);
        });
    });
};

PropertyService.getAvailableValueType = (selectedType, selectedProp, selectedOperation, searchTypes) => {
    // mock implementation
    return new Promise(resolve => {
        axios.post('/types/control', {
            requestedType: selectedType,
            requestedProp: selectedProp,
            requestedOperation: selectedOperation,
            searchTypes: searchTypes
        }).then(response => {
            resolve(response.data);
        });
    })
};

export default PropertyService;