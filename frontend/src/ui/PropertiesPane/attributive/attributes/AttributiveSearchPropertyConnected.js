import {connect} from "react-redux";
import AttributiveSearchProperty from "./AttributiveSearchProperty";
import {attributeTypeChange, attributePropChange} from "../../../../store/query/properties";

const mapStateToProps = (state, ownProps) => {
    const { value } = ownProps;
    return {
        index: value.index,

        selectedType: value.selectedType,
        availableTypes: value.availableTypes,
        typesLoaded: value.typesLoaded,
        allSelectTypes: state.data.availableTypes,

        availableProps: value.availableProps,
        selectedProp: value.selectedProp,
        propsLoaded: value.propsLoaded,
        
        operationsLoaded: value.operationsLoaded,
        availableOperations: value.availableOperations,
        selectedOperaion: value.selectedOperaion
    };
};

const mapDispatchToProps = (dispatch) => ({
    onSelectTypeChange: (attributeIndex, propertyType) => {
        dispatch(attributeTypeChange(
            attributeIndex,
            propertyType
        ));
    },
    onPropertyChange: (attributeIndex, propertyType) => {
        dispatch(attributePropChange(
            attributeIndex, 
            propertyType
        ));
    },
    onOperationChange: (attributeIndex, propertyType) => {
        debugger;
    }
});

const AttributiveSearchPropertyConnected = connect(mapStateToProps, mapDispatchToProps)(AttributiveSearchProperty);
export default AttributiveSearchPropertyConnected;