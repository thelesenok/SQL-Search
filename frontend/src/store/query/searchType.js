export const SEARCH_TYPE_ADD = "search_type_add";
export const SEARCH_TYPE_REMOVE = "search_type_remove";

export const searchTypeAdd = (type) => {
    return {
        type: SEARCH_TYPE_ADD,
        payload: type
    };
};

export const searchTypeRemove = (type) => {
    return {
        type: SEARCH_TYPE_REMOVE,
        payload: type
    }
};