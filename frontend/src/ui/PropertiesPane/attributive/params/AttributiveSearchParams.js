import {connect} from "react-redux";
import AttributiveSearchParamsPure from "./AttributiveSearchParamsPure";
import {changeJoinType} from "../../../../store/query/joinType";
import {changeSelectType} from "../../../../store/query/selectType";
import {propertiesClear, attributeAdd} from "../../../../store/query/properties";

const mapState = (state) => ({
    availableTypes: state.data.availableTypes,
    joinType: state.query.joinType,
    propertyCreationAvailable: state.query.propertyCreationAvailable
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
    }
});

const AttributiveSearchParamsPane = connect(mapState, mapDispatch)(AttributiveSearchParamsPure);
export default AttributiveSearchParamsPane;