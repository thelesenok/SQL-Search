import React from 'react';
import AttributiveSearchParamsPane from "./params/AttributiveSearchParams";
import AttributiveSearchPropertiesConnected from "./properties/AttributiveSearchPropertiesConnected";

const PropertiesPaneAttributive = (props) => {
    return [
        <AttributiveSearchParamsPane key="toolbar" />,
        <AttributiveSearchPropertiesConnected key="table" />
    ]
};

export default PropertiesPaneAttributive;