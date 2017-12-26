import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Col, Grid, Row} from "react-bootstrap";
import SearchTypeSelector from "../SearchTypeSelector/SearchTypeSelector";
import PropertiesPane from "../PropertiesPane/PropertiesPane";
import ResultsPaneConnected from "../ResultsPane/ResultsPaneConnected";

class Application extends Component {
    componentDidMount = () => {
        this.props.loadSelectTypes();
    };

    render() {
        return (
            <Grid>
                <Row>
                    <Col xs={12}>
                        <SearchTypeSelector />
                    </Col>
                </Row>
                <Row>
                    <Col xs={12}>
                        <PropertiesPane />
                    </Col>
                </Row>
                <Row>
                    <Col xs={12}>
                        <ResultsPaneConnected />
                    </Col>
                </Row>
            </Grid>
        )
    }
}

Application.propTypes = {
    loadSelectTypes: PropTypes.func.isRequired
};

export default Application;