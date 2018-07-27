import axios from 'axios'
import qs from 'qs'
import { message } from 'antd';
import { getLocalObjectStorage, clearLocalStorage, setLocalObjectStorage } from '../utils/helper';
import {  isObject, isArray, isNotNull } from '../utils/common';
import history from '../history'

import CryptoJS from 'crypto-js';
import download from "downloadjs";
import {store} from '../index'
import { userLogin } from '../actions/login/index';

let token = "";
let puid = "";
let partnerId = "";

axios.interceptors.request.use(config => {
  // loading
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  return response
}, error => {
  return Promise.resolve(error.response)
})

function checkStatus (response) {
  // loading
  // 如果http状态码正常，则直接返回数据
  if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
    return response.data
    // 如果不需要除了data之外的数据，可以直接 return response.data
  }
  // 异常状态下，把错误信息返回去
  return {
    status: -404,
    msg: '网络异常'
  }
}

function checkCode (res) {
  // 如果code异常(这里已经包括网络错误，服务器错误，后端抛出的错误)，可以弹出一个错误提示，告诉用户
  if (res.status === -404) {
    message.warn(res.msg);
  }
  if (res.data && (!res.data.success)) {
    message.warn(res.data.error_msg);
  }
  if(res.code === "400"){
    // history.goBack();
    message.error("无权访问，建议您重新登录");
  }
  if(res.code === "401"){
      // 身份验证失败情况，清空LocalStorage和Cookie，并重定向到登录页面重新登陆
      sessionStorage.clear();
      clearLocalStorage();
      message.error("登陆已过期，请退出重新操作");
      token = "";
      puid = "";
      partnerId = "";
      history.push("/login");
  } else if(res.code === '404'){
      history.push("/exception/404");
  } else if(res.code === '200'){
      if(isNotNull(res.token)) {
          const userInfo = getLocalObjectStorage("USER_INFO");
          const newToken = res.token;

          //store
          store.dispatch(userLogin({type: 'TOKEN', data: newToken}));
          const user = {
              partnerId: userInfo.partnerId,
              puid: userInfo.puid,
              userName: userInfo.userName,
              token: newToken,
              isAdmin: userInfo.isAdmin,
              partnerType: userInfo.partnerType
          };
          setLocalObjectStorage('USER_INFO', user);
          token = newToken;//重置token
      }
  }
  return res
}

let timestamp = '';

const getSign = (params, url) => {
    timestamp = new Date().getTime();//每次请求都是新的时间戳

    let t = '';
    if(url.indexOf("isVerifyCode") < 0 && url.indexOf("userLogin") < 0
        && url.indexOf("forgotPwd") < 0 && url.indexOf("doForgotPwd") < 0
        && url.indexOf("doForgotPwdView") < 0
        && url.indexOf("sendMailCodeNoSign") < 0 && url.indexOf("puser/register") < 0){
        t = getLocalObjectStorage("USER_INFO").token;
    }

    const map = new Map();
    if(JSON.stringify(params) !== "{}" && params !== null && params !== undefined) {
        for (let key of Object.keys(params)) {

            if(key !== "range-time-picker"  && key !== "keys"
                && isObject(params[key]) && isArray(params[key]) && isNotNull(params[key])){
                map.set(key, params[key]);
            }
        }
    }
    if(isNotNull(puid)){
        map.set("uid", puid);
    }
    if(isNotNull(partnerId)){
        map.set("partnerId", partnerId);
    }
    map.set("timestamp", timestamp);
    map.set("lang", "zh-CN");

    const mapArr = Array.from(map.keys());
    mapArr.sort();
    let sign = "";
    for(let key of mapArr){
        sign = sign + key + "=" + map.get(key) + "&";
    }
    sign = sign.substring(0, sign.length - 1);
    sign = CryptoJS.MD5(sign + t).toString();
    return sign;
}
 function checkUser(){
     if(partnerId ===''|| token === '' || puid ===''){
         const authInfo = getLocalObjectStorage("USER_INFO");
         if (authInfo !== null) {
             token  = authInfo.token;
             puid      = authInfo.puid;
             partnerId = authInfo.partnerId;
         }
     }

 }
export default {
  postData (url, data) {
      checkUser();
      const sign = getSign(data, url);
      return axios({
      method: 'post',
      // baseURL: baseURL, url,
      url: url,
      data: qs.stringify({
          uid: puid,
          partnerId: partnerId,
          timestamp: timestamp,
          sign: sign,
          signType: 'MD5',
          lang: "zh-CN",
          ...data,
          'range-time-picker': "",
      }),
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'Authorization': 'Bearer ' + token
      }
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  },
  getData (url, params) {

    checkUser();
    const sign = getSign(params, url);
    return axios({
      method: 'get',
      // baseURL: baseURL, url,
      url: url,
      params: {
          uid: puid,
          partnerId: partnerId,
          timestamp: timestamp,
          sign: sign,
          signType: 'MD5',
          lang: "zh-CN",
          ...params,
          'range-time-picker': ""
      },// get 请求时带的参数
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
          'Authorization': 'Bearer ' + token
      }
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  },
    postFile (url,dataForm) {
        checkUser();
        const sign = getSign(dataForm, url);
        dataForm.append("uid",puid);
        dataForm.append("partnerId",partnerId);
        dataForm.append("timestamp",timestamp);
        dataForm.append("sign",sign);
        dataForm.append("signType",'MD5');
        dataForm.append("lang",'zh-CN');
        return axios({
            method: 'post',
            // baseURL: baseURL, url,
            url: url,
            data: dataForm,
            timeout: 60000,
            headers: {
                'Content-Type': 'multipart/form-data',
                'Authorization': 'Bearer ' + token
            }
        }).then(
            (response) => {
                return checkStatus(response)
            }
        ).then(
            (res) => {
                return checkCode(res)
            }
        )
    },
    downloadExcel (url, data) {

        checkUser();
        const sign = getSign(data, url);
        return axios({
            method: 'post',
            // baseURL: baseURL, url,
            url: url,
            data: qs.stringify({
                uid: puid,
                partnerId: partnerId,
                timestamp: timestamp,
                sign: sign,
                signType: 'MD5',
                lang: "zh-CN",
                ...data,
                'range-time-picker': "",
            }),
            responseType:'blob',
            timeout: 60000,
            headers: {
                'Authorization': 'Bearer ' + token,
                'accept':"application/msexcel",
            }
        }).then(
            (res) => {
                try {
                    let disposition = res.headers["content-disposition"]
                    let filename = disposition.split("Filename=")[1]
                    download(res.data,decodeURI(filename))
                }catch (error){
                       message.error("权限不足")
                }
            }
        )
    },
}
