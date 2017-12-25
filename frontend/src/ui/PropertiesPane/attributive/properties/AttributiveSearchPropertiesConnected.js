import {connect} from "react-redux";
import AttributiveSearchProperties from "./AttributiveSearchProperties";

const mapStateToProps = (state) => ({
    properties: state.query.properties
});

const mapDispatchToProps = (dispatch) => ({
    onChange: (type) => {
        debugger;
    }
});

const AttributiveSearchPropertiesConnected = connect(mapStateToProps, mapDispatchToProps)(AttributiveSearchProperties);
export default AttributiveSearchPropertiesConnected;