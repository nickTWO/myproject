//写cookies
/**
* setCookie 方法
* name: 名字
* value: 值
* times: 过期时间 单位秒
*/
export function setCookie(name, value, times) {
  // const Days = 30;  //Days*24*60*60*1000
  const exp = new Date();
  exp.setTime(exp.getTime() + times*60*1000);
  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
}
export function getCookie(name) {
  const reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
  const arr = document.cookie.match(reg);
  if (arr) {
    return decodeURIComponent(arr[2]);
  } else {
    return null;
  }
}

// Operation Cookie
export function getJSONCookie(name) {
  const reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
  const arr = document.cookie.match(reg);
  if (arr) {
    return JSON.parse(unescape(decodeURIComponent(escape(arr[2]))));
  } else {
    return null;
  }
}

export function delCookie({ name, domain, path }) {
  if (getCookie(name)){
    const exp = new Date();
    exp.setTime(exp.getTime() - 1);
    document.cookie = name + '=;expires='+ exp.toGMTString()+";path=/";// + path + '; domain=' + domain;
  }
}

// 删除所有cookie
export function clearAllCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=)/g);
    if(keys) {
        for(var i = keys.length; i--;) {
            let exp = new Date();
            exp.setTime(exp.getTime() - (1*24*60*60*1000) );
            // exp.setTime(exp.getTime() - 1);
            document.cookie = keys[i] + '=;expires=' + exp.toGMTString()+";path=/";// + path + '; domain=' + domain;
        }
    }
}

// Operation LocalStorage
export function setLocalStorage(key, value) {
  try {
    return localStorage.setItem(key, value);
  }
  catch (error) {
      console.error('localStorage.setItem报错：', error.message)
  }
}

// Operation LocalStorage
export function setLocalObjectStorage(key, value) {
    try {
        return localStorage.setItem(key, JSON.stringify(value));
    }
    catch (error) {
        console.error('localStorage.setItem报错：', error.message)
    }
}

export function clearLocalStorage() {
    try {
        return localStorage.clear();
    }
    catch (error) {
        console.error('localStorage.clear报错：', error.message)
    }
}

export function getLocalStorage(key) {
  let value;
  try {
    value = localStorage.getItem(key);
  }
  catch (error) {
      console.error('localStorage.getItem报错：', error.message)
  }
  finally {
    return value;
  }
}

export function getLocalObjectStorage(key) {
    let value;
    try {
        value = JSON.parse(localStorage.getItem(key));
    }
    catch (error) {
        console.error('localStorage.getItem报错：', error.message)
    }
    finally {
        return value;
    }
}

//如果所有Map的键都是字符串，它可以转为对象。
export function strMapToObj(strMap) {
  let obj = Object.create(null);
  for (let [k,v] of strMap) {
    obj[k] = v;
  }
  return obj;
}
//对象转为Map
export function objToStrMap(obj) {
  let strMap = new Map();
  for (let k of Object.keys(obj)) {
    strMap.set(k, obj[k]);
  }
  return strMap;
}
//============Map转为JSON============
//Map的键名都是字符串
export function mapToArrayJson(map) {
  return JSON.stringify([...map]);
}
//Map的键名有非字符串，这时可以选择转为数组JSON。
export function strMapToJson(strMap) {
  return JSON.stringify(strMapToObj(strMap));
}
//============JSON转为Map============
//所有键名都是字符串。
export function jsonToStrMap(jsonStr) {
  return objToStrMap(JSON.parse(jsonStr));
}
export function jsonToMap(jsonStr) {
  return new Map(JSON.parse(jsonStr));
}
