import React from 'react';
import PropTypes from 'prop-types';
import Table from 'react-bootstrap/lib/Table';
import PropertiesTableRow from './PropertiesTableRow';

const PropertiesTable = (props) => {
    const { properties } = props;
    const rows = properties.map(item => {
        return <PropertiesTableRow value={item} />
    });

    return (
        <Table>
            <thead>
                <tr>
                    <th>Class</th>
                    <th>Property</th>
                    <th>Logical operation</th>
                    <th>Value</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </Table>
    );
};

PropertiesTable.propTypes = {
    properties: PropTypes.array.isRequired
};

export default PropertiesTable;