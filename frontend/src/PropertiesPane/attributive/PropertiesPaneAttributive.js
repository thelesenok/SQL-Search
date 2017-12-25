import React from 'react';
import PropTypes from 'prop-types';
import AttributiveSearchParams from "./AttributiveSearchParams";
import JoinType from './JoinType';

const PropertiesPaneAttributive = (props) => {
    const {
        onTypeChange,
        availableTypes,

        joinType,
        onJoinTypeChange
    } = props;

    return (
        <AttributiveSearchParams availableTypes={availableTypes}
                                 onTypeChange={onTypeChange}
                                 onJoinTypeChange={onJoinTypeChange}
                                 joinType={joinType}  />
    );
};

PropertiesPaneAttributive.propTypes = {
    availableTypes: PropTypes.array.isRequired,
    onTypeChange: PropTypes.func.isRequired,
    joinType: PropTypes.oneOf([
        JoinType.AND,
        JoinType.OR
    ]).isRequired,
    onJoinTypeChange: PropTypes.func.isRequired
};

export default PropertiesPaneAttributive;