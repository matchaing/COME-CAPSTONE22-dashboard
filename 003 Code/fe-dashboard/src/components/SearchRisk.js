import '../App.css';
import {useEffect, useState} from "react";
import RiskRequester from './RiskRequester';

function getvalue(){
    var idx = document.getElementById('idx').value;
    var sdate = document.getElementById('s_date').value;
    var edate = document.getElementById('e_date').value;
    var urll = '/m'+idx+'/'+sdate+edate;
    return urll;
}

export default function SearchDate(){
    const [startdate,setStartdate] = useState(0);
    const [enddate,setEnddate] = useState(0);
    // console.log(props);


    return (
        <div>
            <div className='SearchDate'>
                <form>
                    <input type="date" name="sdate" min="2018-01-01" id="s_date" 
                    value={startdate}
                    onChange={(e)=>{
                        console.log(e.target.value);
                        setStartdate(e.target.value);
                        } }/>
                    <input type="date" name="edate" min={startdate} id="e_date"
                    value={enddate}
                    onChange={(e)=>{
                        console.log(e.target.value);
                        setEnddate(e.target.value);
                        } }/>
                </form>
                    <div>
                        <RiskRequester s={startdate.toString()} e={enddate.toString()}/>
                    </div>
                {/* </form> */}
            </div>
        </div>
    )
}
