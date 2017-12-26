import React from 'react';
import PropTypes from 'prop-types';
import SelectTypeSelector from "./SelectTypeSelector";
import PropertySelector from "./PropertySelector";
import LogicalOperationSelector from './LogicalOperationSelector';
import ValueInput from './ValueInput';
import {Button} from "react-bootstrap";

const AttributiveSearchProperty = (props) => {
    return (
        <tr>
            <td>
                <SelectTypeSelector onSelectTypeChange={props.onSelectTypeChange}
                                    selectedType={props.selectedType}
                                    index={props.index}
                                    availableTypes={props.availableTypes}
                                    typesLoaded={props.typesLoaded}
                                    allTypes={props.allSelectTypes}/>
            </td>
            <td>
                <PropertySelector selectedProperty={props.selectedProp}
                                  onPropertyChange={props.onPropertyChange}
                                  index={props.index}
                                  propsLoaded={props.propsLoaded}
                                  availableProps={props.availableProps}/>
            </td>
            <td>
                <LogicalOperationSelector index={props.index} 
                                          availableOperations={props.availableOperations}
                                          operationsLoaded={props.operationsLoaded}
                                          selectedOperation={props.selectedOperation}
                                          onOperationChange={props.onOperationChange} />
            </td>
            <td>
                <ValueInput index={props.index}
                            valueTypeLoaded={props.valueTypeLoaded} />
            </td>
            <td>
                <Button block 
                        disabled={!props.attributeRemoveEnabled}
                        onClick={e => props.onAttributeRemove(props.index)}>
                    Remove
                </Button>
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
    typesLoaded: PropTypes.bool.isRequired,

    onPropertyChange: PropTypes.func.isRequired,
    availableProps: PropTypes.array.isRequired,
    selectedProp: PropTypes.any, // todo fix it
    propsLoaded: PropTypes.bool.isRequired,

    onOperationChange: PropTypes.func.isRequired,
    operationsLoaded: PropTypes.bool.isRequired,
    availableOperations: PropTypes.array.isRequired,
    selectedOperaion: PropTypes.any, // todo fix it,

    valueTypeLoaded: PropTypes.bool.isRequired,

    onAttributeRemove: PropTypes.func.isRequired,
    attributeRemoveEnabled: PropTypes.bool.isRequired
};

export default AttributiveSearchProperty;