import '../App.css';
import { useEffect, useState } from "react";
import LineChart from "../pages/charts/LineChart";
import BarChart from "../pages/charts/BarChart";
import { Line } from "react-chartjs-2";
import { Bar } from "react-chartjs-2";

const GochangRequester = (props) =>{
    const startdate_str = props.s.toString().replace(/-/g,"");
    const enddate_str = props.e.toString().replace(/-/g, "");
    console.log(startdate_str,enddate_str);

    //climate : 20180101~20180103
    //gochang : 20211208
    let rawUrl = `http://localhost:8080/Dashboard/detail/gochang?sdate=${startdate_str}&edate=${enddate_str}`;
    let encodeUrl = encodeURI(rawUrl);
    console.log(encodeUrl);

    const [data, setData] = useState([0]);

    const [file_dt, setFile_dt] = useState([]);
    const [equipment, setEquipment] = useState([]);
    const [force_1, setForce_1] = useState([]);
    const [force_2, setForce_2] = useState([]);
    const [force_3, setForce_3] = useState([]);
    const [force_4, setForce_4] = useState([]);
    const [qmmax, setQmmax] = useState([]);


    const getData = async()=>{
        const res = await fetch(encodeUrl, {method: 'GET'})
        .then((res)=>res.json());
        
        let result = JSON.stringify(res.slice(0,20));

        let jsondata = (JSON.parse(result));
        console.log(jsondata);
        

        console.log("gochang getData");
        ///데이터 존재 확인
        let length = jsondata.length;
        console.log(length);
        if(!(enddate_str == 0) && length == 0){
            alert("고창 데이터가 비어있습니다.");
            return;
        }


        for(let i=0; i<length; i++){
            if(!Number.isNaN(parseInt(jsondata[i].FILE_DT))){
                file_dt.push(parseInt(jsondata[i].FILE_DT));
                equipment.push(parseInt(jsondata[i].EQUIPMENT_NAME));
                force_1.push(parseInt(jsondata[i].FORCE_1));
                force_2.push(parseInt(jsondata[i].FORCE_2));
                force_3.push(parseInt(jsondata[i].FORCE_3));
                force_4.push(parseInt(jsondata[i].FORCE_4));
                qmmax.push(parseInt(jsondata[i].QMMAX));
            }
        }
        
        //가져온 데이터 넣기
        setFile_dt(file_dt);
        setEquipment(equipment);
        setForce_1(force_1);
        setForce_2(force_2);
        setForce_3(force_3);
        setForce_4(force_4);
        setQmmax(qmmax);
    };
    

    console.log(data);

    useEffect(()=>{ //컴포넌트 렌더링 이후에 데이터 가져오기
        // if(!(data.length == 1)){
            getData();
            
            console.log("gochang : useEffect");
        // }
    },[encodeUrl])

    //그래프
    const force_data ={
        labels: file_dt,
        datasets: [
            {
            label: "force 1",
            data: force_1,
            backgroundColor: "#fd6059",
            tension:0.4,
            },
            {
            label: "force 2",
            data: force_2,
            backgroundColor: "#febe2e",
            tension:0.4,
            },
            {
            label: "force 3",
            data: force_3,
            backgroundColor: "#34c85a",
            tension:0.4,
            },
            {
            label: "force 4",
            data: force_4,
            backgroundColor: "#0075e8",
            tension:0.4,
            },
        ],
    };

    const qmmax_data ={
        labels: file_dt,
        datasets: [
            {
            label: "qmmax",
            data: qmmax,
            backgroundColor: "#7fb39d",
            },
        ],
    };

    return (
        <div>
                    <div className="chart">
                        <LineChart chartData={force_data}/>
                    </div>
                    <div style={{display:'flex'}} className="chart">
                        <BarChart chartData={qmmax_data}/>
                    </div>
        </div>
    )
}


export default GochangRequester;