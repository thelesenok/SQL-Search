import {SEARCH_LOADED, SEARCH_START} from "./search/search";

const initialState = {
    searchInProgress: false,
    searchResults: {
        rows: []
    }
};

const searchReducer = (state = initialState, action) => {
    switch (action.type) {
        case SEARCH_START: return {
            ...state,
            searchInProgress: true,
            searchResults: {
                rows: []
            }
        };

        case SEARCH_LOADED: return {
            ...state,
            searchInProgress: false,
            searchResults: action.payload
        };

        default: return state;
    }
};

export default searchReducer;