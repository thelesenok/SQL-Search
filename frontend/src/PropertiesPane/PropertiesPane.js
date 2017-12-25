import React from 'react';
import PropTypes from 'prop-types';
import Tabs from 'react-bootstrap/lib/Tabs';
import Tab from 'react-bootstrap/lib/Tab';
import SearchType from '../SearchTypeSelector/SearchType';
import PropertiesTable from '../PropertiesTable';

const PropertiesPane = (props) => {
    const { types, properties } = props;
    return (
        <Tabs id="Properties pane" 
                defaultActiveKey={SearchType.ATTRIBUTIVE} 
                animation={false}>

            <Tab title="Attributive" 
                    disabled={types.indexOf(SearchType.ATTRIBUTIVE) === -1}
                    eventKey={SearchType.ATTRIBUTIVE}>
                <PropertiesTable properties={properties} />
            </Tab>
            
            <Tab title="Fuzzy smth" 
                    disabled={types.indexOf(SearchType.FUZZY) === -1}
                    eventKey={SearchType.FUZZY}>
                1111
            </Tab>
        </Tabs>
    );
}

PropertiesPane.propTypes = {
    types: PropTypes.array.isRequired,
    properties: PropTypes.array.isRequired
};

export default PropertiesPane;