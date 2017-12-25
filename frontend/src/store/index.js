import {applyMiddleware, combineReducers, createStore} from 'redux';
import dataReducer from "./dataReducer";
import thunkMiddleware from 'redux-thunk';
import queryReducer from "./queryReducer";

const middleware = applyMiddleware(
    thunkMiddleware
);

const rootReducer = combineReducers({
    data: dataReducer,
    query: queryReducer
});

const store = createStore(
    rootReducer,
    middleware
);

export default store;