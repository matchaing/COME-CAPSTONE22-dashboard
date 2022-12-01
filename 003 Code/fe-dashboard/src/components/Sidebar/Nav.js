import React from "react";
import {Route, Link, Redirect,  BrowserRouter as Router } from "react-router-dom";
import {AiOutlineHome} from "react-icons/ai";
import {AiOutlineBarChart} from "react-icons/ai";
import {AiOutlineLineChart} from "react-icons/ai";
import './Nav.css'
import Detail from '../../pages/dashboard/Detail';
import Display from '../../pages/dashboard/Display';
import Risk from '../../pages/dashboard/Risk';

const Nav = () => {
    return (
        <Router>
            <div>
                <nav>
                    <div class="navbar">
                        <AiOutlineHome className="icon" size ="20"/>
                        <Link to="/display">데이터 현황</Link>
                    </div>
                    <div class="navbar">
                        <AiOutlineBarChart className="icon" size ="20"/>
                        <Link to="/detail">데이터 상세 분석</Link>
                    </div>
                    <div class="navbar">
                        <AiOutlineLineChart className="icon" size ="20"/>
                        <Link to="/risk">위험도 분석</Link>
                    </div>
                </nav>
                <switch>
                    <Route
                    exact
                    path="/"
                    render={() => {
                        return (
                            <Redirect to="/display" />
                            )
                    }}
                    />
                    <Route path="/display" component={Display} />
                    <Route path="/detail" component={Detail} />
                    <Route path="/risk" component={Risk} />
                </switch>
            </div>
        </Router>
    );
};

export default Nav;