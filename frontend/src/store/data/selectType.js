import TypeService from '../../service/TypeService';
import {changeSelectType} from "../query/selectType";

export const SELECT_TYPES_LOAD = "select_types_load";
export const SELECT_TYPES_LOADED = "select_types_loaded";

export const selectTypesLoad = () => {
    return (dispatch) => {
        dispatch({
            type: SELECT_TYPES_LOAD
        });
        TypeService.getAllTypes()
            .then((types) => {
                dispatch(selectTypesLoaded(types));
                if (types.length > 0) {
                    dispatch(changeSelectType(types[0].value));
                }
            });
    }
};

export const selectTypesLoaded = (data) => {
    return {
        type: SELECT_TYPES_LOADED,
        payload: data
    };
};