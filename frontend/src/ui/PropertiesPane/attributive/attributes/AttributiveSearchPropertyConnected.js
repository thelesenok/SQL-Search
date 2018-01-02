import {connect} from "react-redux";
import AttributiveSearchProperty from "./AttributiveSearchProperty";
import {
    attributeOperationChange,
    attributePropChange,
    attributeRemove,
    attributeTypeChange,
    attributeValueChange
} from "../../../../store/query/properties";

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
        selectedOperation: value.selectedOperaion,

        valueTypeLoaded: value.valueTypeLoaded,
        valueTypeComponent: value.valueType,
        valueItems: value.valueItems,

        attributeRemoveEnabled: value.typesLoaded &&
                value.propsLoaded &&
                value.operationsLoaded &&
                value.valueTypeLoaded
    };
};

const mapDispatchToProps = (dispatch) => ({
    onSelectTypeChange: (attributeIndex, selectedType) => {
        dispatch(attributeTypeChange(
            attributeIndex,
            selectedType
        ));
    },
    onPropertyChange: (attributeIndex, selectedProperty) => {
        dispatch(attributePropChange(
            attributeIndex, 
            selectedProperty
        ));
    },
    onOperationChange: (attributeIndex, selectedOperaion) => {
        dispatch(attributeOperationChange(
            attributeIndex,
            selectedOperaion
        ));
    },
    onAttributeRemove: (attributeIndex) => {
        dispatch(attributeRemove(
            attributeIndex
        ));
    },
    onValueChange: (attributeIndex, value) => {
        dispatch(attributeValueChange(
            attributeIndex,
            value
        ));
    }
});

const AttributiveSearchPropertyConnected = connect(mapStateToProps, mapDispatchToProps)(AttributiveSearchProperty);
export default AttributiveSearchPropertyConnected;