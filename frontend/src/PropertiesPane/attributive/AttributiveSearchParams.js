import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Col,
    Grid,
    Row,
    Button,
    Radio
} from "react-bootstrap";
import TypeSelector from "./TypeSelector";
import JoinType from './JoinType';

const AttributiveSearchParams = (props) => {
    const {
        onTypeChange,
        availableTypes,
        joinType,
        onJoinTypeChange
    } = props;

    return (
        <Grid>
            <Row>
                <Col xs={3}>
                    <Radio name="joinType"
                           checked={joinType === JoinType.AND}
                           inline>
                        AND
                    </Radio>
                    <Radio name="joinType"
                           checked={joinType === JoinType.OR}
                           inline>
                        OR
                    </Radio>
                </Col>
                <Col xs={3}>
                    <TypeSelector onTypeChange={onTypeChange}
                                  availableTypes={availableTypes}/>
                </Col>
                <Col xs={3}>
                    <Button block>
                        +
                    </Button>
                </Col>
                <Col xs={3}>
                    <Button block>
                        Clear all conditions
                    </Button>
                </Col>
            </Row>
        </Grid>
    )
};

AttributiveSearchParams.propTypes = {
    onTypeChange: PropTypes.func.isRequired,
    availableTypes: PropTypes.array.isRequired,

    joinType: PropTypes.oneOf([
        JoinType.AND,
        JoinType.OR
    ]).isRequired,
    onJoinTypeChange: PropTypes.func.isRequired
};

export default AttributiveSearchParams;