import React from 'react';
import PropTypes from 'prop-types';
import {FormControl, ProgressBar} from "react-bootstrap";

const LogicalOperationSelector = (props) => {
    const {
        index,
        availableOperations,
        operationsLoaded,
        selectedOperation,
        onOperationChange
    } = props;

    const operationsForSelect = availableOperations.map(item => {
        return <option key={item.value} value={item.value}>
            {item.label}
        </option>
    });

    if (!operationsLoaded) {
        return (
            <ProgressBar active now={100} />
        );
    }

    return (
        <FormControl componentClass="select"
                     onChange={e => onOperationChange(index, e.target.value)}
                     defaultValue={selectedOperation}>
            {operationsForSelect}
        </FormControl>
    )
};

LogicalOperationSelector.propTypes = {
    onOperationChange: PropTypes.func.isRequired,
    index: PropTypes.number.isRequired,
    operationsLoaded: PropTypes.bool.isRequired,
    selectedOperaion: PropTypes.any // todo fix it
};

export default LogicalOperationSelector;