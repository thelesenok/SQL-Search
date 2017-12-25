import JoinType from '../service/JoinType';
import SearchType from "../service/SearchType";
import {SEARCH_TYPE_ADD, SEARCH_TYPE_REMOVE} from './query/searchType';
import {JOIN_TYPE_CHANGE} from "./query/joinType";
import {SELECT_TYPE_CHANGE} from "./query/selectType";
import {
    ATTRIBUTES_CLEAR, ATTRIBUTE_ADD, ATTRIBUTE_CREATED, ATTRIBUTE_TYPE_CHANGE,
    ATTRIBUTE_PROPS_LOADED, ATTRIBUTE_PROP_CHANGE
} from "./query/properties";
import ArrayUtils from "../utils/ArrayUtils";

const initialState = {
    joinType: JoinType.AND,
    searchTypes: [
        SearchType.ATTRIBUTIVE
    ],
    selectType: undefined,
    attributes: [],

    propertyCreationAvailable: true
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
            attributes: []
        };

        case JOIN_TYPE_CHANGE: return {
            ...state,
            joinType: action.payload
        };

        case ATTRIBUTE_ADD: return {
            ...state,
            propertyCreationAvailable: false
        };

        case ATTRIBUTE_CREATED: return {
            ...state,
            propertyCreationAvailable: true,
            attributes: state.attributes.concat(action.payload)
        };

        case ATTRIBUTE_TYPE_CHANGE: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    selectedType: action.payload.selectedType
                }
            )
        };

        case ATTRIBUTE_PROPS_LOADED: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                (attr) => attr.index === action.payload.index,
                {
                    propsLoaded: true,
                    availableProps: action.payload.props
                }
            )
        };

        case ATTRIBUTE_PROP_CHANGE: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    selectedProp: action.payload.selectedProp
                }
            )
        };

        case ATTRIBUTES_CLEAR: return {
            ...state,
            attributes: []
        };

        default: return state;
    }
};

export default queryReducer;