import {combineReducers} from 'redux'
import userInfor from './user';
import menuList from './menuList';
import menuKey from './menuKey';
import mchManage from './mchManage';
import currency from './currency';
import language from './language';
import changeContent from './changeContent';


const rootReducer = combineReducers({
  userInfor,
  menuList,
  menuKey,
  mchManage,
  currency,
  language,
  changeContent,

})
export default rootReducer
