import {connect} from "react-redux";
import AttributiveSearchParamsPure from "./AttributiveSearchParamsPure";
import {changeJoinType} from "../../../../store/query/joinType";
import {changeSelectType} from "../../../../store/query/selectType";
import {propertiesClear, attributeAdd} from "../../../../store/query/properties";
import {searchStart} from "../../../../store/search/search";

const mapState = (state) => ({
    availableTypes: state.data.availableTypes,
    joinType: state.query.joinType,
    propertyCreationAvailable: state.query.propertyCreationAvailable,
    searchInProgress: state.search.searchInProgress
});

const mapDispatch = (dispatch) => ({
    onSelectTypeChange: (type) => {
        dispatch(changeSelectType(type));
    },
    onJoinTypeChange: (type) => {
        dispatch(changeJoinType(type));
    },
    onPropertyAdd: () => {
        dispatch(attributeAdd());
    },
    onPropertiesClear: () => {
        dispatch(propertiesClear());
    },
    onSearchStart: () => {
        dispatch(searchStart());
    }
});

const AttributiveSearchParamsPane = connect(mapState, mapDispatch)(AttributiveSearchParamsPure);
export default AttributiveSearchParamsPane;