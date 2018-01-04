import {connect} from "react-redux";
import AttributiveSearchProperties from "./AttributiveSearchProperties";

const mapStateToProps = (state) => ({
    attributes: state.query.attributes
});

const mapDispatchToProps = (dispatch) => ({
});

const AttributiveSearchPropertiesConnected = connect(mapStateToProps, mapDispatchToProps)(AttributiveSearchProperties);
export default AttributiveSearchPropertiesConnected;