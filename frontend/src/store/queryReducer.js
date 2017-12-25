import JoinType from '../service/JoinType';
import SearchType from "../service/SearchType";
import {SEARCH_TYPE_ADD, SEARCH_TYPE_REMOVE} from './query/searchType';
import {JOIN_TYPE_CHANGE} from "./query/joinType";
import {SELECT_TYPE_CHANGE} from "./query/selectType";
import {PROPERTIES_CLEAR, PROPERTY_ADD, PROPERTY_CREATED} from "./query/properties";

const initialState = {
    joinType: JoinType.AND,
    searchTypes: [
        SearchType.ATTRIBUTIVE
    ],
    selectType: undefined,
    properties: []
};

const queryReducer = (state = initialState, action) => {
    switch (action.type) {
        case SEARCH_TYPE_ADD: return {
            ...state,
            searchTypes: state.searchTypes.concat(action.payload)
        };

        case SEARCH_TYPE_REMOVE: return {
            ...state,
            searchTypes: state.searchTypes.filter(item => {
                return item !== action.payload
            })
        };

        case SELECT_TYPE_CHANGE: return {
            ...state,
            selectType: action.payload,
            properties: []
        };

        case JOIN_TYPE_CHANGE: return {
            ...state,
            joinType: action.payload
        };

        case PROPERTY_ADD: return {
            ...state
        };

        case PROPERTY_CREATED: return {
            ...state,
            properties: state.properties.concat(action.payload)
        };

        case PROPERTIES_CLEAR: return {
            ...state,
            properties: []
        };

        default: return state;
    }
};

export default queryReducer;