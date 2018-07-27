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


//=============sessionStorage=================
export function getSessionObjectStorage(key) {
    let value;
    try {
        value = JSON.parse(sessionStorage.getItem(key));
    }
    catch (error) {
        console.error('sessionStorage.getItem报错：', error.message)
    }
    finally {
        return value;
    }
}

export function setSessionObjectStorage(key, value) {
    try {
        return sessionStorage.setItem(key, JSON.stringify(value));
    }
    catch (error) {
        console.error('sessionStorage.setItem报错：', error.message)
    }
}
//=============sessionStorage=================


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
