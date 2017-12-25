import React from 'react';
import PropTypes from 'prop-types';
import AttributiveSearchProperty from "./AttributiveSearchProperty";
import {Table} from "react-bootstrap";

const AttributiveSearchProperties = (props) => {
    const { properties } = props;
    const rows = properties.map(item => {
        return <AttributiveSearchProperty key={item.index}
                                          value={item} />
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

AttributiveSearchProperties.propTypes = {
    properties: PropTypes.array.isRequired
};

export default AttributiveSearchProperties;