import React from "react";
import '../../App.css'
import SearchRisk from '../../components/SearchRisk';

class Risk extends React.Component {
    render () {
        return (
            <div className="page">
                <p className= "p-text">위험도 분석</p>
                <SearchRisk/>
            </div>
        )
    }
}

export default Risk;