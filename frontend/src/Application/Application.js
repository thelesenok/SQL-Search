import React, { Component } from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import TypeService from '../TypeService';
import SearchTypeSelector from '../SearchTypeSelector';
import SearchType from '../SearchTypeSelector/SearchType';
import PropertiesPane from '../PropertiesPane';
import ResultsPane from '../ResultsPane';

class Application extends Component {
    state = {
        availableTypes: [],
        userQuery: {
            selectFrom: null,
            searchTypes: [
                SearchType.ATTRIBUTIVE
            ],
            properties: []
        }
    };

    componentDidMount = () => {
        // load types
        TypeService.getAllTypes()
                .then(types => {
                    this.setState({
                        availableTypes: types
                    });
                    this._selectType(types[0].value);
                });
    };

    handleTypeChange = (selectedType) => {
        this._selectType(selectedType);
    };

    handleSearchTypeChange= (types) => {
        this._selectSearchType(types);
    };

    /**
     * Handle search type change
     */
    _selectSearchType = (types) => {
        const newQuery = Object.assign(this.state.userQuery, {
            searchTypes: types
        });
        this._setUserQuery(newQuery);
    };

    /**
     * Handle search class change
     */
    _selectType = (selectedType) => {
        // it's necessary to clean properties
        const newQuery = Object.assign(this.state.userQuery, {
            selectFrom: selectedType,
            properties: []
        });
        this._setUserQuery(newQuery);
    };

    _setUserQuery = (query) => {
        this.setState({
            userQuery: query
        });
    };

    render = () => {
        return (
            <Grid>
                <Row>
                    <Col xs={12}>
                        <SearchTypeSelector onChange={this.handleSearchTypeChange} />
                    </Col>
                </Row>
                <Row>
                    <Col xs={12}>
                        <PropertiesPane availableTypes={this.state.availableTypes}
                                        searchTypes={this.state.userQuery.searchTypes}
                                        onTypeChange={this.handleTypeChange}
                                        properties={this.state.userQuery.properties}/>
                    </Col>
                </Row>
                <Row>
                    <Col xs={12}>
                        <ResultsPane />
                    </Col>
                </Row>
            </Grid>
        );
    }
}

export default Application;