import React from "react";
import '../../App.css'
import ClimateRequester from '../../components/ClimateRequester';
import GochangRequester from '../../components/GochangRequester';


export default function Display(){
        return (
            <div className="page">
                <p className= "p-text">데이터 현황</p>
                    <ClimateRequester s={20180103} e={20180103}/>
                    <GochangRequester s={20211208} e={20211208}/>
            </div>
        );
}