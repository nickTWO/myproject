import React from 'react';
import { Spin } from 'antd';
/**
 * Created by Barry on 2017/12/16.
 */

/*对象不为空*/
export function isObject(data) {
    return data !== null && data !== undefined && JSON.stringify(data) !== "{}";
}

/*数组不为空*/
export function isArray(data) {
    return data !== null && data !== undefined && JSON.stringify(data) !== "[]";
}

/*字符串不为空*/
export function isNotNull(data) {
    return data !== null && data !== undefined && data !== "" && JSON.stringify(data) !== "";
}

export function hoc(component) {
    return class HOC extends component {
        render() {
            if (this.state.success) {
                return super.render()
            }
            return <Spin tip="Loading" size="large" style={{fontSize: '20px',left: '45%', top: '50%', position:'absolute'}}></Spin>
        }
    }
}

/*字符串为空*/
export function isNull(data) {
    return data === null || data === undefined || data === "" || JSON.stringify(data) === "";
}

export function getTime(type, value) {
    let sysTime = new Date();
    // let year = sysTime.getFullYear();
    // let month = sysTime.getMonth();
    if(type==='minute'){
        sysTime.setTime(sysTime.getTime()+value*60*1000);
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds();
    }else if(type==='hour'){
        sysTime.setTime(sysTime.getTime()+value*60*60*1000);
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds();
    }else if(type==='day'){
        sysTime.setTime(sysTime.getTime()+value*24*60*60*1000);
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+(sysTime.getHours()>9?sysTime.getHours():('0'+sysTime.getHours()))+':'+sysTime.getMinutes()+':'+sysTime.getSeconds();
    }else if(type==='week'){
        sysTime.setTime(sysTime.getTime()+value*7*24*60*60*1000);
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds();
    }else if(type==='month'){
        sysTime.setTime(sysTime.getTime()+value*30*24*60*60*1000);
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds();
    }else if(type==='year'){
        return (sysTime.getFullYear()+value)+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds()
    }else if(isNull(type) || isNull(value)) {
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds()
    }else {
        return sysTime.getFullYear()+'-'+(sysTime.getMonth()+1)+'-'+sysTime.getDate()+' '+sysTime.getHours()+':'+sysTime.getMinutes()+':'+sysTime.getSeconds()
    }
}

export function exchangeValue(value,count) {
    var numStr = parseFloat(value).toFixed(count)
    if(parseFloat(numStr)===0){
        return "0.00"
    }else {
        return numStr;
    }
}