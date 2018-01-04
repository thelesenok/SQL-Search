export const SELECT_TYPE_CHANGE = "select_type_change";

export const changeSelectType = (type) => {
    return {
        type: SELECT_TYPE_CHANGE,
        payload: type
    };
};