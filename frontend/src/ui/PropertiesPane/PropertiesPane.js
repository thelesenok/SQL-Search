import {connect} from "react-redux";
import PropertiesPanePure from "./PropertiesPanePure";

const mapStateToProps = (state) => ({
    selectedSearchTypes: state.query.searchTypes
});

const mapDispatchToProps = (dispatch) => ({

});

const PropertiesPane = connect(mapStateToProps, mapDispatchToProps)(PropertiesPanePure);
export default PropertiesPane;