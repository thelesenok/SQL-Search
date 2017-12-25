import React, { Component } from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import TypeSelector from '../TypeSelector';
import TypeService from '../TypeService';

class Application extends Component {
    state = {
        selectTypes: [],
        userQuery: {
            selectType: null,
            properties: []
        }
    };

    componentDidMount = () => {
        // load types
        TypeService.getAllTypes()
                .then(types => {
                    this.setState({
                        selectTypes: types
                    });
                    this._selectType(types[0].value);
                });
    }

    handleTypeChange = (selectedType) => {
        this._selectType(selectedType);
    }

    _selectType = (selectedType) => {
        this.setState({
            userQuery: {
                selectTypes: selectedType,
                properties: []
            }
        });
    }

    render = () => {
        return (
            <Grid>
                <Row>
                    <Col xs={12}>
                        <TypeSelector types={this.state.selectTypes} 
                                        onChange={this.handleTypeChange} />
                    </Col>
                </Row>
            </Grid>
        );
    }
}

export default Application;