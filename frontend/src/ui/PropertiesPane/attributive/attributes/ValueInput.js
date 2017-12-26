import React from 'react';
import PropTypes from 'prop-types';
import {ProgressBar} from "react-bootstrap";

const ValueInput = (props) => {
    const {
        index,
        valueTypeLoaded
    } = props;

    if (!valueTypeLoaded) {
        return (
            <ProgressBar active now={100} />
        );
    }

    return (
        <div>1111</div>
    );
};

ValueInput.propTypes = {
    index: PropTypes.number.isRequired,
    valueTypeLoaded: PropTypes.bool.isRequired
};

export default ValueInput;