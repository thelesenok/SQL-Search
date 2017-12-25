import React from 'react';
import PropTypes from 'prop-types';
import SelectTypeSelector from "./SelectTypeSelector";
import PropertySelector from "./PropertySelector";

const AttributiveSearchProperty = (props) => {
    return (
        <tr>
            <td>
                <SelectTypeSelector onSelectTypeChange={props.onSelectTypeChange}
                                    selectedType={props.selectedType}
                                    index={props.index}
                                    availableTypes={props.availableTypes}
                                    allTypes={props.allSelectTypes}/>
            </td>
            <td>
                <PropertySelector selectedProperty={props.selectedProp}
                                  onPropertyChange={props.onPropertyChange}
                                  index={props.index}
                                  propsLoaded={props.propsLoaded}
                                  availableProps={props.availableProps}/>
            </td>
        </tr>
    );
};

AttributiveSearchProperty.propTypes = {
    index: PropTypes.number.isRequired,

    onSelectTypeChange: PropTypes.func.isRequired,
    selectedType: PropTypes.any, // todo fix it
    availableTypes: PropTypes.array.isRequired,
    allSelectTypes: PropTypes.array.isRequired,

    onPropertyChange: PropTypes.func.isRequired,
    availableProps: PropTypes.array.isRequired,
    selectedProp: PropTypes.any, // todo fix it
    propsLoaded: PropTypes.bool.isRequired
};

export default AttributiveSearchProperty;