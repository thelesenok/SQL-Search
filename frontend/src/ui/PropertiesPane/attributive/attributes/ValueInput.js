import React from 'react';
import PropTypes from 'prop-types';
import {ProgressBar, FormControl} from "react-bootstrap";

const ValueInput = (props) => {
    const {
        index,
        valueTypeLoaded,
        componentType,
        onValueChange
    } = props;

    if (!valueTypeLoaded) {
        return (
            <ProgressBar active now={100} />
        );
    }

    return (
        <FormControl type={componentType}
                     onChange={e => onValueChange(index, e.target.value)}/>
    );
};

ValueInput.propTypes = {
    index: PropTypes.number.isRequired,
    valueTypeLoaded: PropTypes.bool.isRequired,
    componentType: PropTypes.string,
    onValueChange: PropTypes.func.isRequired
};

export default ValueInput;