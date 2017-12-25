import React from 'react';
import PropTypes from 'prop-types';
import Tabs from 'react-bootstrap/lib/Tabs';
import Tab from 'react-bootstrap/lib/Tab';
import SearchType from '../SearchTypeSelector/SearchType';
import PropertiesPaneAttributive from "./attributive/PropertiesPaneAttributive";

const PropertiesPane = (props) => {
    const { searchTypes, onTypeChange, availableTypes } = props;
    return (
        <Tabs id="Properties pane" 
                defaultActiveKey={SearchType.ATTRIBUTIVE} 
                animation={false}>

            <Tab title="Attributive" 
                    disabled={searchTypes.indexOf(SearchType.ATTRIBUTIVE) === -1}
                    eventKey={SearchType.ATTRIBUTIVE}>

                <PropertiesPaneAttributive availableTypes={availableTypes}
                                           onTypeChange={onTypeChange} />
            </Tab>
            
            <Tab title="Fuzzy smth" 
                    disabled={searchTypes.indexOf(SearchType.FUZZY) === -1}
                    eventKey={SearchType.FUZZY}>
                1111
            </Tab>
        </Tabs>
    );
};

PropertiesPane.propTypes = {
    availableTypes: PropTypes.array.isRequired,
    onTypeChange: PropTypes.func.isRequired,

    searchTypes: PropTypes.array.isRequired,

    properties: PropTypes.array.isRequired
};

export default PropertiesPane;