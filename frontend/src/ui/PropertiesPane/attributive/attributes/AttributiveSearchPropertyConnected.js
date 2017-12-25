import {connect} from "react-redux";
import AttributiveSearchProperty from "./AttributiveSearchProperty";
import {attributeTypeChange} from "../../../../store/query/properties";

const mapStateToProps = (state, ownProps) => {
    const { value } = ownProps;
    return {
        index: value.index,
        selectedType: value.selectedType,
        availableTypes: value.availableTypes,
        availableProps: value.availableProps,
        selectedProp: value.selectedProp,
        propsLoaded: value.propsLoaded,
        allSelectTypes: state.data.availableTypes
    };
};

const mapDispatchToProps = (dispatch) => ({
    onSelectTypeChange: (propertyIndex, propertyType) => {
        dispatch(attributeTypeChange(
            propertyIndex,
            propertyType
        ));
    },
    onPropertyChange: (propertyIndex, propertyType) => {
        debugger;
    }
});

const AttributiveSearchPropertyConnected = connect(mapStateToProps, mapDispatchToProps)(AttributiveSearchProperty);
export default AttributiveSearchPropertyConnected;