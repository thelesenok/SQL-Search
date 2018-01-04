import React from 'react';
import PropTypes from 'prop-types';
import {Checkbox, Form, FormGroup, Panel} from "react-bootstrap";
import SearchType from '../../service/SearchType';

const SearchTypeSelectorPure = (props) => {
    const {
        onSearchTypeChange,
        selectedSearchTypes
    } = props;

    return (
        <Panel header="Search params:">
            <Form horizontal>
                <FormGroup>
                    <Checkbox checked={selectedSearchTypes.indexOf(SearchType.ATTRIBUTIVE) !== -1}
                              readOnly
                              inline
                              name={SearchType.ATTRIBUTIVE}
                              onChange={e => onSearchTypeChange(SearchType.ATTRIBUTIVE, e.target.checked)}
                              disabled>
                        Attributive lookup
                    </Checkbox>

                    <Checkbox checked={selectedSearchTypes.indexOf(SearchType.RELATIONAL) !== -1}
                              onChange={e => onSearchTypeChange(SearchType.RELATIONAL, e.target.checked)}
                              name={SearchType.RELATIONAL}
                              inline>
                        Relational lookup
                    </Checkbox>

                    <Checkbox checked={selectedSearchTypes.indexOf(SearchType.FUZZY) !== -1}
                              onChange={e => onSearchTypeChange(SearchType.FUZZY, e.target.checked)}
                              name={SearchType.FUZZY}
                              inline>
                        Fuzzy like lookup
                    </Checkbox>
                </FormGroup>
            </Form>
        </Panel>
    )
};

SearchTypeSelectorPure.propTypes = {
    onSearchTypeChange: PropTypes.func.isRequired,
    selectedSearchTypes: PropTypes.array.isRequired
};

export default SearchTypeSelectorPure;