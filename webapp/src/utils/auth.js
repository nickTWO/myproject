
import {  clearLocalStorage, getLocalObjectStorage } from '../utils/helper'
import { isNotNull } from '../utils/common'

const config = {
    redirectUrl: 'http://'+ window.location.host + '/login',
};

// Auth
export function getAuthHeader(sso_token) {
  return ({
    headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + sso_token,
      'Content-Type': 'application/json',
    },
  });
}

export function redirectLogin() {
  sessionStorage.clear();
  clearLocalStorage();
  window.location.href = config.redirectUrl;
}

//首页重定向
export function redirectLoginPage() {
  const authInfo = getLocalObjectStorage("USER_INFO")
  // console.log(authInfo);
  if(authInfo === null){
    sessionStorage.clear();
    clearLocalStorage();
    window.location.href = config.redirectUrl;
    return
  }
  if( !isNotNull(authInfo.token) || !isNotNull(authInfo.puid) || !isNotNull(authInfo.partnerId) ) {
    sessionStorage.clear();
    clearLocalStorage();
    window.location.href = config.redirectUrl;
    return
  }
}

export function logOut() {
  redirectLogin();
}
