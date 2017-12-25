import React from 'react';
import PropTypes from 'prop-types';
import Tabs from 'react-bootstrap/lib/Tabs';
import Tab from 'react-bootstrap/lib/Tab';
import SearchType from '../../service/SearchType';
import PropertiesPaneAttributive from "./attributive/PropertiesPaneAttributive";

const PropertiesPanePure = (props) => {
    const { selectedSearchTypes } = props;
    return (
        <Tabs id="Properties pane" 
                defaultActiveKey={SearchType.ATTRIBUTIVE} 
                animation={false}>

            <Tab title="Attributive" 
                    disabled={selectedSearchTypes.indexOf(SearchType.ATTRIBUTIVE) === -1}
                    eventKey={SearchType.ATTRIBUTIVE}>

                <PropertiesPaneAttributive />
            </Tab>
            
            <Tab title="Fuzzy smth" 
                    disabled={selectedSearchTypes.indexOf(SearchType.FUZZY) === -1}
                    eventKey={SearchType.FUZZY}>
                1111
            </Tab>
        </Tabs>
    );
};

PropertiesPanePure.propTypes = {
    selectedSearchTypes: PropTypes.array.isRequired
};

export default PropertiesPanePure;