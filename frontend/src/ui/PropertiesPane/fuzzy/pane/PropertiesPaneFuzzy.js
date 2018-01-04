import React from 'react';
import PropTypes from 'prop-types';
import {Form, FormGroup, FormControl, Col, ControlLabel} from "react-bootstrap";

const PropertiesPaneFuzzy = (props) => {
    const {
        fuzzyMaskSize,
        onFuzzyMaskChange
    } = props;

    return (
        <Form horizontal>
            <FormGroup>
                <Col componentClass={ControlLabel} sm={4}>
                    Fuzzy mask size
                </Col>
                <Col sm={8}>
                    <FormControl type="number"
                                 onChange={e => onFuzzyMaskChange(+e.target.value)}
                                 defaultValue={fuzzyMaskSize}>
                    </FormControl>
                </Col>
            </FormGroup>
        </Form>
    )
};

PropertiesPaneFuzzy.propTypes = {
    fuzzyMaskSize: PropTypes.number.isRequired,
    onFuzzyMaskChange: PropTypes.func.isRequired
};

export default PropertiesPaneFuzzy;