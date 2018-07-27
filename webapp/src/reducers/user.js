import {getLocalObjectStorage} from '../utils/helper'

// 从localStorage中获取当前用户信息
let localStorageInfo = getLocalObjectStorage('USER_INFO')

let initState = localStorageInfo;

const userInfor = (state = initState, action) => {
    switch (action.type) {
        case 'USER_LOGIN':
            return {
                ...state,
                sso_token: action.data
            };
        case 'PARTNERID':
            return {
                ...state,
                partnerId: action.data
            };
        case 'PUID':
            return {
                ...state,
                puid: action.data
            };
        case 'USERNAME':
            return {
                ...state,
                userName: action.data
            };
        default:
            return {...state};
    }
}

export default userInfor;
