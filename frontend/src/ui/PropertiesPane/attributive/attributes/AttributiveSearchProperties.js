import React from 'react';
import PropTypes from 'prop-types';
import {Table} from "react-bootstrap";
import AttributiveSearchPropertyConnected from "./AttributiveSearchPropertyConnected";

const AttributiveSearchProperties = (props) => {
    const { attributes } = props;
    const rows = attributes.map(item => {
        return <AttributiveSearchPropertyConnected key={item.index} value={item} />
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
    attributes: PropTypes.array.isRequired
};

export default AttributiveSearchProperties;