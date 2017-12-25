import React from 'react';
import PropTypes from 'prop-types';

const AttributiveSearchProperty = (props) => {
    return (
        <tr>
            <td colSpan={5}>
                Some value here
            </td>
        </tr>
    );
};

AttributiveSearchProperty.propTypes = {
    value: PropTypes.shape({}).isRequired
};

export default AttributiveSearchProperty;