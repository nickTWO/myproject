import React from 'react'
// BrowserRouter as Router,  browserHistory
import { Router, Route, Switch } from 'react-router-dom'
import Container from './container'
import Contents from './container'
import Login from './pages/login/index'
import history from './history';

const routes = (
    <Router history={history}>
        <Switch>
            <Route exact path="/" component={Container} />
            <Route path="/login" component={Login} />
            <Contents />
        </Switch>
    </Router>
)

export default routes;
