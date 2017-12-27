import PropertyService from "../../service/PropertyService";

export const ATTRIBUTE_ADD = "property_add";
export const ATTRIBUTE_CREATED = "property_created";
export const ATTRIBUTE_REMOVE = "attribute_remove";
export const ATTRIBUTES_CLEAR = "properties_clear";

export const ATTRIBUTE_TYPES_LOAD = "attribute_types_load";
export const ATTRIBUTE_TYPES_LOADED = "attribute_types_loaded";

export const attributeAdd = () => {
    return (dispatch, getState) => {
        dispatch({
            type: ATTRIBUTE_ADD
        });
        const state = getState();
        const {
            selectType,
            searchTypes,
            attributes
        } = state.query;
        // create new attribute
        PropertyService.createAttribute(attributes)
            .then(attribute => {
                dispatch({
                    type: ATTRIBUTE_CREATED,
                    payload: attribute
                });
                // it's necessary to get availabel attribute types
                dispatch({
                    type: ATTRIBUTE_TYPES_LOAD,
                    payload: {
                        index: attribute.index
                    }
                });
                PropertyService.getAvailableTypes(selectType, searchTypes)
                    .then(types => {
                        dispatch({
                            type: ATTRIBUTE_TYPES_LOADED,
                            payload: {
                                index: attribute.index,
                                types: types
                            }
                        });
                        // if there is one or more available types, we can select the first
                        if (types.length > 0) {
                            dispatch(attributeTypeChange(
                                attribute.index,
                                types[0]
                            ));
                        }
                    });
            });
    };
};

export const attributeRemove = (index) => {
    return {
        type: ATTRIBUTE_REMOVE,
        payload: {
            index: index
        }
    };
};

export const propertiesClear = () => {
    return {
        type: ATTRIBUTES_CLEAR
    };
};

export const ATTRIBUTE_TYPE_CHANGE = "property_type_change";

export const attributeTypeChange = (index, selectedType) => {
    return (dispatch, getState) => {
        dispatch({
            type: ATTRIBUTE_TYPE_CHANGE,
            payload: {
                index: index,
                selectedType: selectedType
            }
        });
        const state = getState();
        const {
            searchTypes
        } = state.query;
        // start types loading
        dispatch({
            type: ATTRIBUTE_PROPS_LOAD,
            payload: {
                index: index
            }
        });
        // load available type attributes
        PropertyService.getAvailableProperties(selectedType, searchTypes)
            .then(props => {
                dispatch({
                    type: ATTRIBUTE_PROPS_LOADED,
                    payload: {
                        index: index,
                        props: props
                    }
                });
                // if there are zero or more available props, we can select the first
                if (props.length > 0) {
                    dispatch(attributePropChange(
                        index,
                        props[0].value
                    ));
                }
            });
    };
};

export const ATTRIBUTE_PROP_CHANGE = "attribute_prop_change";
export const ATTRIBUTE_PROPS_LOAD = "attribute_props_load";
export const ATTRIBUTE_PROPS_LOADED = "attribute_props_loaded";

export const attributePropChange = (index, selectedProp) => {
    return (dispatch, getState) => {
        dispatch({
            type: ATTRIBUTE_PROP_CHANGE,
            payload: {
                index: index,
                selectedProp: selectedProp
            }
        });
        // load available property logical operations
        const state = getState();
        const {
            searchTypes,
            attributes
        } = state.query;
        dispatch({
            type: ATTRIBUTE_OPERATIONS_LOAD,
            payload: {
                index: index
            }
        });
        const selectedType = attributes.find(attr => {
            return attr.index === index;
        }).selectedType;
        PropertyService.getAvailableLogicalOperations(selectedType, selectedProp, searchTypes)
            .then(operations => {
                dispatch({
                    type: ATTRIBUTE_OPERATIONS_LOADED,
                    payload: {
                        index:  index,
                        operations: operations
                    }
                });
                // if there is more than zero operations, select first
                if (operations.length > 0) {
                    dispatch(attributeOperationChange(
                        index,
                        operations[0].value
                    ));
                }
            });
    }
};

export const ATTRIBUTE_OPERATIONS_LOAD = "attribute_operations_load";
export const ATTRIBUTE_OPERATIONS_LOADED = "attribute_operations_loaded";
export const ATTRIBUTE_OPERATION_CHANGE = "attribute_operation_change";

export const attributeOperationChange = (index, selectedOperation) => {
    return (dispatch, getState) => {
        dispatch({
            type: ATTRIBUTE_OPERATION_CHANGE,
            payload: {
                index: index,
                selectedOperation: selectedOperation
            }
        });
        // load available value type
        const state = getState();
        const {
            searchTypes,
            attributes
        } = state.query;
        dispatch({
            type: ATTRIBUTE_VALUE_TYPE_LOAD,
            payload: {
                index: index
            }
        });
        const selectedProp = attributes.find(attr => {
            return attr.index === index
        }).selectedProp;
        PropertyService.getAvailableValueType(selectedProp, selectedOperation, searchTypes)
            .then(type => {
                dispatch({
                    type: ATTRIBUTE_VALUE_TYPE_LOADED,
                    payload: {
                        index: index,
                        valueType: type.component
                    }
                });
            });
    }
};

export const ATTRIBUTE_VALUE_TYPE_LOAD = "attribute_value_type_load";
export const ATTRIBUTE_VALUE_TYPE_LOADED = "attribute_value_type_loaded";
export const ATTRIBUTE_VALUE_CHANGE = "attribute_value_change";

export const attributeValueChange = (index, value) => {
    return {
        type: ATTRIBUTE_VALUE_CHANGE,
        payload:  {
            index: index,
            value: value
        }
    };
};
