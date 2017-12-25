import React from 'react';
import PropTypes from 'prop-types';

const PropertiesTableRow = (props) => {
    return (
        <tr>
            <td colspan={5}>
                Some value here
            </td>
        </tr>
    );
}

PropertiesTableRow.propTypes = {
    value: PropTypes.shape().isRequired
};

export default PropertiesTableRow;