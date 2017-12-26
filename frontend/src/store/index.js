import {applyMiddleware, combineReducers, createStore} from 'redux';
import dataReducer from "./dataReducer";
import thunkMiddleware from 'redux-thunk';
import queryReducer from "./queryReducer";
import {composeWithDevTools} from "redux-devtools-extension";
import searchReducer from "./searchReducer";

const middleware = applyMiddleware(
    thunkMiddleware
);

const rootReducer = combineReducers({
    data: dataReducer,
    query: queryReducer,
    search: searchReducer
});

const store = createStore(
    rootReducer,
    composeWithDevTools(middleware)
);

export default store;