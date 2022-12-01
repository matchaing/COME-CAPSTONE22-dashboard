import '../App.css';
import { useEffect, useState } from "react";
import LineChart from "../pages/charts/LineChart";
import { Line } from "react-chartjs-2";

const DataRequester = (props) =>{
    const startdate_str = props.s.toString().replace(/-/g,"");
    const enddate_str = props.e.toString().replace(/-/g, "");
    console.log(startdate_str,enddate_str);

    //climate : 20180101~20180103
    //gochang : 20211208
    let rawUrl = `http://localhost:8080/Dashboard/detail/climate?sdate=${startdate_str}&edate=${enddate_str}`;
    let encodeUrl = encodeURI(rawUrl);
    console.log(encodeUrl);

    const [data, setData] = useState([0]);

    const [labels, setLabel] = useState([]);
    const [humidity, setHumidity] = useState([]);
    const [temperature, setTemperature] = useState([]);


    const getData = async()=>{
        const res = await fetch(encodeUrl, {method: 'GET'})
        .then((res)=>res.json());
        
        let result = JSON.stringify(res);
        
        let jsondata = (JSON.parse(result));
        
        console.log("climate getData");

        ///데이터 존재 확인
        let length = jsondata.length;
        console.log(length);
        if(!(enddate_str == 0) && length == 0){
            alert("기후 데이터가 비어있습니다.");
            return;
        }

        for(let i=0; i<length; i++){
            if(!Number.isNaN(parseInt(jsondata[i].DAILYDATADT))){
                labels.push(parseInt(jsondata[i].DAILYDATADT));
                humidity.push(parseInt(jsondata[i].HUMIDITY));
                temperature.push(parseInt(jsondata[i].TEMPERATURE));
            }
        }
        
            
        // console.log(temperature);

        //가져온 데이터 넣기
        setData(jsondata);
        setLabel(labels);
        setHumidity(humidity);
        setTemperature(temperature);
    };
    

    console.log(data);
    console.log(labels);

    useEffect(()=>{ //컴포넌트 렌더링 이후에 데이터 가져오기
        // if(!(data.length == 1)){
            getData();
            
            console.log("climate : useEffect");
        // }
    },[encodeUrl])

    
    //그래프
    const humidity_data ={
        labels: labels,
        datasets: [
            {
            label: "Humidity",
            data: humidity,
            backgroundColor: "#ecf0f1",
            tension:0.4,
            },
        ],
    // });
    
    };
    const temperature_data ={
        labels: labels,
        datasets: [
            {
            label: "Temperature",
            data: temperature,
            backgroundColor: "#5bbfd9",
            tension:0.4,
            },
        ],
    // });
    };

    return (
        <div>
                    <div className="chart">
                        <LineChart chartData={humidity_data}/>
                    </div>
                    <div style={{display:'flex'}} className="chart">
                        <LineChart chartData={temperature_data}/>
                    </div>
            
        </div>
    )
}


export default DataRequester;