import {connect} from "react-redux";
import ResultsPane from "./ResultsPane";

const mapStateToProps = (state, ownProps) => ({
    searchResults: state.search.searchResults.rows,
    searchInProgress: state.search.searchInProgress
});

const mapDispatchToProps = (dispatch) => ({

});

const ResultsPaneConnected = connect(mapStateToProps, mapDispatchToProps)(ResultsPane);
export default ResultsPaneConnected;