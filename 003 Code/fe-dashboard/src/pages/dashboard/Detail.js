import React from "react";
import '../../App.css'
import SearchDate from '../../components/SearchDate';

class Detail extends React.Component {
    render () {
        return (
            <div className="page">
                <p className= "p-text">데이터 상세 분석</p>
                    <SearchDate />
            </div>
            
        )
    }
}

export default Detail;