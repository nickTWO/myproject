/**
 * Created by Barry on 2018/1/17.
 */
import {getLocalStorage} from '../utils/helper'

// 从localStorage中获取当前用户信息
let localCurrencyOfStorage = getLocalStorage('local_currency');

let initState = localCurrencyOfStorage;

const currency = (state = initState, action) => {
  switch (action.type) {
    case 'CURRENCY':
      return action.data+'';
    default:
      return state;
  }
}

export default currency;