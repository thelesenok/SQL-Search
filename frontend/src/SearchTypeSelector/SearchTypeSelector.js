import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Form from 'react-bootstrap/lib/Form';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import Checkbox from 'react-bootstrap/lib/Checkbox';
import SearchType from './SearchType';

class SearchTypeSelector extends Component {
    state = {
        types: [
            SearchType.ATTRIBUTIVE
        ]
    };

    handleChange = (event) => {
        if (event.target.checked) {
            this.setState({
                types: this.state.types.concat(event.target.name)
            }, () => {
                this._onChange(this.state.types);
            })
        } else {
            this.setState({
                types: this.state.types.filter(item => item !== event.target.name)
            }, () => {
                this._onChange(this.state.types);
            });
        }
    };

    _onChange = (types) => {
        const { onChange } = this.props;
        onChange(types);
    }

    render = () => {
        return (
            <Form horizontal>
                <FormGroup>
                    <Checkbox checked={this.state.types.indexOf(SearchType.ATTRIBUTIVE) !== -1} 
                                readOnly 
                                inline
                                name={SearchType.ATTRIBUTIVE}
                                onChange={this.handleChange}
                                disabled>
                        Attributive lookup
                    </Checkbox>

                    <Checkbox checked={this.state.types.indexOf(SearchType.RELATIONAL) !== -1} 
                                onChange={this.handleChange}
                                name={SearchType.RELATIONAL}
                                inline>
                        Relational lookup
                    </Checkbox>

                    <Checkbox checked={this.state.types.indexOf(SearchType.FUZZY) !== -1}
                                onChange={this.handleChange}
                                name={SearchType.FUZZY}
                                inline>
                        Fuzzy attributive lookup
                    </Checkbox>
                </FormGroup>
            </Form>
        );
    }
}

SearchTypeSelector.propTypes = {
    onChange: PropTypes.func.isRequired
};

export default SearchTypeSelector;