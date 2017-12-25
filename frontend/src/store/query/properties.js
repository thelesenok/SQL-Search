import PropertyService from "../../service/PropertyService";

export const ATTRIBUTE_ADD = "property_add";
export const ATTRIBUTE_CREATED = "property_created";
export const ATTRIBUTES_CLEAR = "properties_clear";

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
        // load available types
        PropertyService.createAttribute(+selectType, searchTypes, attributes)
            .then((property) => {
                dispatch({
                    type: ATTRIBUTE_CREATED,
                    payload: property
                });
                // if there are zero or more available types, we should select first
                if (property.availableTypes.length > 0) {
                    dispatch(attributeTypeChange(
                        property.index,
                        property.availableTypes[0]
                    ))
                }
            })
    };
};

export const ATTRIBUTE_TYPE_CHANGE = "property_type_change";
export const ATTRIBUTE_PROP_CHANGE = "attribute_prop_change";
export const ATTRIBUTE_PROPS_LOADED = "attribute_props_loaded";

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

export const attributePropChange = (index, selectedProp) => {
    return {
        type: ATTRIBUTE_PROP_CHANGE,
        payload: {
            index: index,
            selectedProp: selectedProp
        }
    }
};

export const propertiesClear = () => {
    return {
        type: ATTRIBUTES_CLEAR
    };
};