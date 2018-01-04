import React from 'react';
import PropTypes from 'prop-types';
import {FormControl, ProgressBar} from "react-bootstrap";

const SelectTypeSelector = (props) => {
    const {
        allTypes,
        availableTypes,
        index,
        onSelectTypeChange,
        selectedType,
        typesLoaded
    } = props;

    const typesForSelect = allTypes.filter(type => {
        return availableTypes.indexOf(type.value) !== -1;
    }).map(type => {
        return <option value={type.value}
                       key={type.value}>{type.label}</option>
    });

    if (!typesLoaded) {
        return (
            <ProgressBar active now={100} />
        );
    }

    return (
        <FormControl componentClass="select"
                     onChange={e => onSelectTypeChange(index, e.target.value)}
                     defaultValue={selectedType}>
            {typesForSelect}
        </FormControl>
    )
};

SelectTypeSelector.propTypes = {
    onSelectTypeChange: PropTypes.func.isRequired,
    selectedType: PropTypes.oneOfType([
        PropTypes.number,
        PropTypes.string
    ]),
    allTypes: PropTypes.array.isRequired,
    index: PropTypes.number.isRequired,
    availableTypes: PropTypes.array.isRequired,
    typesLoaded: PropTypes.bool.isRequired
};

export default SelectTypeSelector;