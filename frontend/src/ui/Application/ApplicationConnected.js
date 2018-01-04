import {connect} from "react-redux";
import Application from "./Application";
import {selectTypesLoad} from "../../store/data/selectType";

const mapStateToProps = (state) => ({

});

const mapDispatchToProps = (dispatch) => ({
    loadSelectTypes: () => {
        dispatch(selectTypesLoad());
    }
});

const ApplicationConnected = connect(mapStateToProps, mapDispatchToProps)(Application);
export default ApplicationConnected;