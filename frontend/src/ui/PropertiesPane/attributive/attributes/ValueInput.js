import React from 'react';
import PropTypes from 'prop-types';
import {ProgressBar, FormControl} from "react-bootstrap";

const ValueInput = (props) => {
    const {
        index,
        valueTypeLoaded,
        componentType,
        onValueChange,
        items
    } = props;

    if (!valueTypeLoaded) {
        return (
            <ProgressBar active now={100} />
        );
    }

    const values = componentType === 'select' ? items.map(item => {
        return <option key={item.value} value={item.value}>
            {item.label}
        </option>
    }) : null;

    return (
        <FormControl componentClass={componentType}
                     onChange={e => onValueChange(index, e.target.value)}>
            {values}
        </FormControl>
    );
};

ValueInput.propTypes = {
    index: PropTypes.number.isRequired,
    items: PropTypes.array.isRequired,
    valueTypeLoaded: PropTypes.bool.isRequired,
    componentType: PropTypes.string,
    onValueChange: PropTypes.func.isRequired
};

export default ValueInput;