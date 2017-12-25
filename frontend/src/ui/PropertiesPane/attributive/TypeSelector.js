import React from 'react';
import PropTypes from 'prop-types';
import FormControl from 'react-bootstrap/lib/FormControl';

const TypeSelector = (props) => {
    const { onTypeChange, availableTypes } = props;
    return (
        <FormControl componentClass="select" onChange={e => onTypeChange(e.target.value)}>
            {availableTypes.map(item => {
                return <option key={item.value}
                               value={item.value}>{item.label}</option>
            })}
        </FormControl>
    );
};

TypeSelector.propTypes = {
    onTypeChange: PropTypes.func.isRequired,
    availableTypes: PropTypes.arrayOf(PropTypes.shape({
        value: PropTypes.any.isRequired,
        label: PropTypes.string.isRequired
    })).isRequired
};

export default TypeSelector;