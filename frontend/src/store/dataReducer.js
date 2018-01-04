import {SELECT_TYPES_LOAD, SELECT_TYPES_LOADED} from "./data/selectType";

const initialState = {
    availableTypes: []
};

const dataReducer = (store = initialState, action) => {
    switch (action.type) {
        case SELECT_TYPES_LOAD: return {
            ...store
        };

        case SELECT_TYPES_LOADED: return {
            ...store,
            availableTypes: action.payload
        };

        default: return store;
    }
};

export default dataReducer;