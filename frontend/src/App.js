import React from 'react';
import {Provider} from 'react-redux';
import store from './store';
import ApplicationConnected from "./ui/Application/ApplicationConnected";

const App = (props) => {
    return (
        <Provider store={store}>
            <ApplicationConnected />
        </Provider>
    )
};

export default App;