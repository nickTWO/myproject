import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import rootReducer from './reducers';
import { createStore } from 'redux';
import routes from './routes';
import {message} from "antd/lib/index";
message.config({
  top: 60,
  duration: 2
});
export const store = createStore(rootReducer,
    window.devToolsExtension ? window.devToolsExtension() : f => f
)

ReactDOM.render(
    <Provider store={store}>
        { routes }
    </Provider>,
    document.getElementById('root')
);