import {applyMiddleware, combineReducers, createStore} from 'redux';
import dataReducer from "./dataReducer";
import thunkMiddleware from 'redux-thunk';
import queryReducer from "./queryReducer";
import {composeWithDevTools} from "redux-devtools-extension";

const middleware = applyMiddleware(
    thunkMiddleware
);

const rootReducer = combineReducers({
    data: dataReducer,
    query: queryReducer
});

const store = createStore(
    rootReducer,
    composeWithDevTools(middleware)
);

export default store;