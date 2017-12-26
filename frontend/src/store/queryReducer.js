import JoinType from '../service/JoinType';
import SearchType from "../service/SearchType";
import {SEARCH_TYPE_ADD, SEARCH_TYPE_REMOVE} from './query/searchType';
import {JOIN_TYPE_CHANGE} from "./query/joinType";
import {SELECT_TYPE_CHANGE} from "./query/selectType";
import {
    ATTRIBUTE_ADD,
    ATTRIBUTE_CREATED,
    ATTRIBUTE_OPERATION_CHANGE,
    ATTRIBUTE_OPERATIONS_LOADED,
    ATTRIBUTE_PROP_CHANGE,
    ATTRIBUTE_PROPS_LOAD,
    ATTRIBUTE_PROPS_LOADED,
    ATTRIBUTE_REMOVE,
    ATTRIBUTE_TYPE_CHANGE,
    ATTRIBUTE_TYPES_LOAD,
    ATTRIBUTE_TYPES_LOADED, ATTRIBUTE_VALUE_CHANGE,
    ATTRIBUTE_VALUE_TYPE_LOAD,
    ATTRIBUTE_VALUE_TYPE_LOADED,
    ATTRIBUTES_CLEAR
} from "./query/properties";
import ArrayUtils from "../utils/ArrayUtils";
import {FUZZY_LIKE_MASK_CHANGE} from "./query/fuzzyLike";

const initialState = {
    joinType: JoinType.AND,
    searchTypes: [
        SearchType.ATTRIBUTIVE
    ],
    selectType: null,
    attributes: [],
    fuzzyMaskSize: 2,

    propertyCreationAvailable: true
};

const queryReducer = (state = initialState, action) => {
    switch (action.type) {
        case SEARCH_TYPE_ADD: return {
            ...state,
            searchTypes: state.searchTypes.concat(action.payload),
            attributes: []
        };

        case SEARCH_TYPE_REMOVE: return {
            ...state,
            searchTypes: state.searchTypes.filter(item => {
                return item !== action.payload
            }),
            attributes: []
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

        case ATTRIBUTE_TYPES_LOAD: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    typesLoaded: false
                }
            )
        };

        case ATTRIBUTE_TYPES_LOADED: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    typesLoaded: true,
                    availableTypes: action.payload.types
                }
            )
        };

        case ATTRIBUTE_TYPE_CHANGE: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    selectedType: action.payload.selectedType,
                    propsLoaded: false,
                    operationsLoaded: false,
                    valueTypeLoaded: false
                }
            )
        };

        case ATTRIBUTE_PROPS_LOAD: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    propsLoaded: false
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
                    selectedProp: action.payload.selectedProp,
                    operationsLoaded: false,
                    valueTypeLoaded: false
                }
            )
        };

        case ATTRIBUTE_OPERATIONS_LOADED: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    operationsLoaded: true,
                    availableOperations: action.payload.operations
                }
            )
        };

        case ATTRIBUTE_OPERATION_CHANGE: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    valueTypeLoaded: false,
                    selectedOperation: action.payload.selectedOperation
                }
            )
        };

        case ATTRIBUTE_VALUE_TYPE_LOAD: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    valueTypeLoaded: false
                }
            )
        };

        case ATTRIBUTE_VALUE_TYPE_LOADED: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    valueTypeLoaded: true,
                    valueType: action.payload.valueType
                }
            )
        };

        case ATTRIBUTE_REMOVE: return {
            ...state,
            attributes: state.attributes.filter(item => {
                return item.index !== action.payload.index
            })
        };

        case ATTRIBUTE_VALUE_CHANGE: return {
            ...state,
            attributes: ArrayUtils.update(
                state.attributes,
                attr => attr.index === action.payload.index,
                {
                    value: action.payload.value
                }
            )
        };

        case ATTRIBUTES_CLEAR: return {
            ...state,
            attributes: []
        };

        case FUZZY_LIKE_MASK_CHANGE: return {
            ...state,
            fuzzyMaskSize: action.payload
        };

        default: return state;
    }
};

export default queryReducer;