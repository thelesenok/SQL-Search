import React from 'react';
import PropTypes from 'prop-types';
import {FormControl, ProgressBar} from "react-bootstrap";

const PropertySelector = (props) => {
    const {
        onPropertyChange,
        selectedProperty,
        availableProps,
        index,
        propsLoaded
    } = props;

    const propsForSelect = availableProps.map(prop => {
        return <option key={prop.value}
                       value={prop.value}>{prop.label}</option>
    });

    if (!propsLoaded) {
        return (
            <ProgressBar active now={100} />
        );
    }

    return (
        <FormControl componentClass="select"
                     onChange={e => onPropertyChange(index, e.target.value)}
                     defaultValue={selectedProperty}>
            {propsForSelect}
        </FormControl>
    )
};

PropertySelector.propTypes = {
    propsLoaded: PropTypes.bool.isRequired,
    index: PropTypes.number.isRequired,
    selectedProperty: PropTypes.string,
    onPropertyChange: PropTypes.func.isRequired,
    availableProps: PropTypes.array.isRequired
};

export default PropertySelector;