import {connect} from "react-redux";
import SearchTypeSelectorPure from "./SearchTypeSelectorPure";
import {searchTypeAdd, searchTypeRemove} from "../../store/query/searchType";

const mapStateToProps = (state) => ({
    selectedSearchTypes: state.query.searchTypes
});

const mapDispatchToProps = (dispatch) => ({
    onSearchTypeChange: (type, selected) => {
        if (selected) {
            dispatch(searchTypeAdd(type));
        } else {
            dispatch(searchTypeRemove(type));
        }
    }
});

const SearchTypeSelector = connect(mapStateToProps, mapDispatchToProps)(SearchTypeSelectorPure);
export default SearchTypeSelector;