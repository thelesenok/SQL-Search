import React from 'react';
import PropTypes from 'prop-types';
import {
    Col,
    Grid,
    Row,
    Button,
    Radio
} from "react-bootstrap";
import TypeSelector from "../TypeSelector";
import JoinType from '../../../../service/JoinType';

const AttributiveSearchParamsPure = (props) => {
    const {
        onSelectTypeChange,
        availableTypes,
        joinType,
        onJoinTypeChange,
        onPropertyAdd,
        onPropertiesClear,
        propertyCreationAvailable
    } = props;

    return (
        <Grid>
            <Row>
                <Col xs={3}>
                    <Radio name="joinType"
                           checked={joinType === JoinType.AND}
                           onChange={e => onJoinTypeChange(JoinType.AND)}
                           inline>
                        AND
                    </Radio>
                    <Radio name="joinType"
                           checked={joinType === JoinType.OR}
                           onChange={e => onJoinTypeChange(JoinType.OR)}
                           inline>
                        OR
                    </Radio>
                </Col>
                <Col xs={3}>
                    <TypeSelector onTypeChange={onSelectTypeChange}
                                  availableTypes={availableTypes}/>
                </Col>
                <Col xs={3}>
                    <Button block
                            disabled={!propertyCreationAvailable}
                            onClick={onPropertyAdd}>
                        +
                    </Button>
                </Col>
                <Col xs={3}>
                    <Button block onClick={onPropertiesClear}>
                        Clear all conditions
                    </Button>
                </Col>
            </Row>
        </Grid>
    )
};

AttributiveSearchParamsPure.propTypes = {
    onSelectTypeChange: PropTypes.func.isRequired,
    availableTypes: PropTypes.array.isRequired,

    joinType: PropTypes.oneOf([
        JoinType.AND,
        JoinType.OR
    ]).isRequired,
    onJoinTypeChange: PropTypes.func.isRequired,

    onPropertyAdd: PropTypes.func.isRequired,
    onPropertiesClear: PropTypes.func.isRequired,

    propertyCreationAvailable: PropTypes.bool.isRequired
};

export default AttributiveSearchParamsPure;