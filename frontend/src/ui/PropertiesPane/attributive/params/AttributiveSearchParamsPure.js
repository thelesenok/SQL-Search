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
        propertyCreationAvailable,
        onSearchStart,
        searchInProgress
    } = props;

    return (
        <Grid>
            <Row>
                <Col xs={2}>
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
                <Col xs={2}>
                    <Button block
                            disabled={!propertyCreationAvailable}
                            onClick={onPropertyAdd}>
                        +
                    </Button>
                </Col>
                <Col xs={2}>
                    <Button block onClick={onPropertiesClear}>
                        Clear
                    </Button>
                </Col>
                <Col xs={3}>
                    <Button block
                            bsStyle="primary"
                            disabled={searchInProgress}
                            onClick={onSearchStart}>
                        Search
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
    onSearchStart: PropTypes.func.isRequired,
    searchInProgress: PropTypes.bool.isRequired,

    propertyCreationAvailable: PropTypes.bool.isRequired
};

export default AttributiveSearchParamsPure;