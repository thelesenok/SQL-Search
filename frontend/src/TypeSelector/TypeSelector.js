import React from 'react';
import PropTypes from 'prop-types';
import Form from 'react-bootstrap/lib/Form';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import Col from 'react-bootstrap/lib/Col';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';

const TypeSelector = (props) => {
    const { onChange, types } = props
    return (
        <Form horizontal>
            <FormGroup>
                <Col componentClass={ControlLabel} sm={4}>
                    Search class
                </Col>
                <Col sm={8}>
                    <FormControl componentClass="select" onChange={e => onChange(e.target.value)}>
                        {types.map(item => {
                            return <option key={item.value} 
                                    value={item.value}>{item.label}</option>
                        })}
                    </FormControl>
                </Col>
            </FormGroup>
        </Form>
    );
}

TypeSelector.propTypes = {
    onChange: PropTypes.func.isRequired,
    types: PropTypes.arrayOf(PropTypes.shape({
        value: PropTypes.any.isRequired,
        label: PropTypes.string.isRequired
    })).isRequired
};

export default TypeSelector;