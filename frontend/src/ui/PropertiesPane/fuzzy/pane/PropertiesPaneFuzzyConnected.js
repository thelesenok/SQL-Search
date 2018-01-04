import {connect} from "react-redux";
import PropertiesPaneFuzzy from "./PropertiesPaneFuzzy";
import {onFuzzyLikeMaskChange} from "../../../../store/query/fuzzyLike";

const mapStateToProps = (state, ownProps) => ({
    fuzzyMaskSize: state.query.fuzzyMaskSize
});

const mapDispatchToProps = (dispatch) => ({
    onFuzzyMaskChange: (value) => {
        dispatch(onFuzzyLikeMaskChange(value));
    }
});

const PropertiesPaneFuzzyConnected = connect(mapStateToProps, mapDispatchToProps)(PropertiesPaneFuzzy);
export default PropertiesPaneFuzzyConnected;