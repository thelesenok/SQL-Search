import React from 'react';
import PropTypes from 'prop-types';
import {ProgressBar, Table} from "react-bootstrap";

const ResultsPane = (props) => {
    const {
        searchInProgress,
        searchResults
    } = props;

    if (searchInProgress) {
        return (
            <ProgressBar active now={100} />
        );
    }

    if (searchResults.length === 0) {
        return (
            <div>
                No results found
            </div>
        );
    }

    const rows = searchResults.map((row, rIndex) => {
        const cells = row.map((cell, cIndex) => {
            return <td key={cIndex}>{cell}</td>
        });
        return <tr key={rIndex}>{cells}</tr>;
    });

    return (
        <Table>
            <tbody>
                {rows}
            </tbody>
        </Table>
    )
};

ResultsPane.propTypes = {
    searchResults: PropTypes.array.isRequired,
    searchInProgress: PropTypes.bool.isRequired
};

export default ResultsPane;