import SearchService from "../../service/SearchService";

export const SEARCH_START = "search_start";
export const SEARCH_LOADED = "search_loaded";

const prepareQuery = (query) => {
    return {
        searchTypes: query.searchTypes,
        joinType: query.joinType,
        fuzzyMaskSize: query.fuzzyMaskSize,
        selectFrom: query.selectType,
        attributes: query.attributes.map(attr => {
            return {
                selectClass: attr.selectedType,
                property: attr.selectedProp,
                operation: attr.selectedOperation,
                valueType: attr.valueType,
                value: attr.value
            }
        })
    };
};

export const searchStart = () => {
    return (dispatch, getState) => {
        dispatch({
            type: SEARCH_START
        });
        const query = prepareQuery(getState().query);
        SearchService.search(query)
            .then(response => {
                dispatch({
                    type: SEARCH_LOADED,
                    payload: response
                });
            });
    }
};