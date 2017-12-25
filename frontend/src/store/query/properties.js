import PropertyService from "../../service/PropertyService";

export const PROPERTY_ADD = "property_add";
export const PROPERTY_CREATED = "property_created";
export const PROPERTIES_CLEAR = "properties_clear";

export const propertyAdd = () => {
    return (dispatch, getState) => {
        dispatch({
            type: PROPERTY_ADD
        });
        const state = getState();
        const {
            selectType,
            searchTypes,
            properties
        } = state.query;
        PropertyService.createProperty(selectType.value, searchTypes, properties)
            .then((property) => {
                dispatch({
                    type: PROPERTY_CREATED,
                    payload: property
                });
            })
    };

};

export const propertiesClear = () => {
    return {
        type: PROPERTIES_CLEAR
    };
};