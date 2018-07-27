import React from 'react';
import {Route, Switch,Redirect} from 'react-router-dom'
import {Layout} from 'antd'
import './content.less'
import asyncComponent from './AsyncComponent'


const AsyncIndex = asyncComponent(() => import('../pages/index/index'))
// const AsyncFinancialList = asyncComponent(() => import('../pages/billing/FinancialList'))
// const AsyncTrade = asyncComponent(() => import('../pages/order/Trade'))
// const AsyncRefund = asyncComponent(() => import('../pages/order/Refund'))
// const AsyncTransfer = asyncComponent(() => import('../pages/order/Transfer'))
// const AsyncPartner = asyncComponent(() => import('../pages/merchant/Partner'))
// const AsyncRole = asyncComponent(() => import('../pages/merchant/Role'))
// const AsyncRoleAuth = asyncComponent(() => import('../pages/merchant/RoleAuth'))
// const AsyncRoleAdd = asyncComponent(() => import('../pages/merchant/RoleAdd'))
// const AsyncUser = asyncComponent(() => import('../pages/merchant/User'))
// const AsyncUserAdd = asyncComponent(() => import('../pages/merchant/UserAdd'))
// const AsyncuserUpdate = asyncComponent(() => import('../pages/merchant/UserUpdate'))
// const AsyncBillingStatisList = asyncComponent(() => import('../pages/billing/BillingStatisList'))
// const AsyncSafetySetting = asyncComponent(() => import('../pages/safety/SafetySetting'))
// const AsyncSafetyPolicy = asyncComponent(() => import('../pages/safety/SafetyPolicy'))
// const AsyncSafetyList = asyncComponent(() => import('../pages/safety/SafetyList'))
// const AsyncDevelopmentResource = asyncComponent(() => import('../pages/developer/DevelopmentResource'))
// const AsyncDevelopmentSetting = asyncComponent(() => import('../pages/developer/DevelopmentSetting'))
// const AsyncResetEmail = asyncComponent(() => import('../pages/safety/ResetEmail'))
// const AsyncBindPhone = asyncComponent(() => import('../pages/safety/BindPhone'))
// const AsyncLoginPwdSetting = asyncComponent(() => import('../pages/safety/LoginPwdSetting'))
// const AsyncPayPwdSetting = asyncComponent(() => import('../pages/safety/PayPwdSetting'))
// const AsyncBindGoogleCode = asyncComponent(() => import('../pages/safety/BindGoogleCode'))
// const AsyncRecharge = asyncComponent(() => import('../pages/user/Recharge'))
// const AsyncOnlineTransfer= asyncComponent(() => import('../pages/user/OnlineTransfer'))
// const AsyncQuickTransfer= asyncComponent(() => import('../pages/user/QuickTransfer'))
// const AsyncBentchTransfer= asyncComponent(() => import('../pages/user/BatchTransfer'))
// const AsyncAccountList= asyncComponent(() => import('../pages/user/AccountList'))
// const AsyncAccountAdd= asyncComponent(() => import('../pages/user/AccountAdd'))
// const AsyncVipcateList= asyncComponent(() => import('../pages/memberCenter/VipcateList'))
// const AsyncVipuserList= asyncComponent(() => import('../pages/memberCenter/VipuserList'))
// const AsyncQpaySetting= asyncComponent(() => import('../pages/memberCenter/QpaySetting'))
const AsyncNotFound= asyncComponent(() => import('../pages/Exception/404'))
const AsyncError= asyncComponent(() => import('../pages/Exception/500'))
const AsyncNoPermission= asyncComponent(() => import('../pages/Exception/403'))





const {Content} = Layout



export default class Contents extends React.Component {
  render() {
    return (
      <Content className="main-content">
        <Switch>
          <Route path="/merchant/console" exact component={AsyncIndex}/>
          {/*<Route path="/trade/index" exact component={AsyncTrade}/>*/}
          {/*<Route path="/transfer/index" exact component={AsyncTransfer}/>*/}
          {/*<Route path="/refund/index" exact component={AsyncRefund}/>*/}
          {/*<Route path="/billing/index" exact component={AsyncFinancialList}/>*/}
          {/*<Route path="/billing/statis" exact component={AsyncBillingStatisList}/>*/}
          {/*<Route path="/safety/index" exact component={AsyncSafetySetting}/>*/}
          {/*<Route path="/safety/policy" exact component={AsyncSafetyPolicy}/>*/}
          {/*<Route path="/safety/history" exact component={AsyncSafetyList}/>*/}
          {/*<Route path="/safety/resetEmail" exact component={AsyncResetEmail}/>*/}
          {/*<Route path="/safety/bindPhone" exact component={AsyncBindPhone}/>*/}
          {/*<Route path="/safety/LoginPwdSetting" exact component={AsyncLoginPwdSetting}/>*/}
          {/*<Route path="/safety/PayPwdSetting" exact component={AsyncPayPwdSetting}/>*/}
          {/*<Route path="/safety/bindGoogleCode" exact component={AsyncBindGoogleCode}/>*/}
          {/*<Route path="/developer/download" exact component={AsyncDevelopmentResource}/>*/}
          {/*<Route path="/developer/settings" exact component={AsyncDevelopmentSetting}/>*/}
          {/*<Route path="/merchant/account" exact component={AsyncPartner}/>*/}
          {/*<Route path="/merchant/role" exact component={AsyncRole}/>*/}
          {/*<Route path="/merchant/worker" exact component={AsyncUser}/>*/}
          {/*<Route path="/roleAdd" exact component={AsyncRoleAdd}/>*/}
          {/*<Route path="/roleAuth/:mrid?" component={AsyncRoleAuth}/>*/}
          {/*<Route path="/userAdd" exact component={AsyncUserAdd}/>*/}
          {/*<Route path="/userUpdate/:uid?" exact component={AsyncuserUpdate}/>*/}
          {/*<Route path="/recharge/payin" exact component={AsyncRecharge}/>*/}
          {/*<Route path="/transfer/onlineTransfer" exact component={AsyncOnlineTransfer}/>*/}
          {/*<Route path="/transfer/quickpay" exact component={AsyncQuickTransfer}/>*/}
          {/*<Route path="/transfer/batch" exact component={AsyncBentchTransfer}/>*/}
          {/*<Route path="/transfer/accountAdd" exact component={AsyncAccountAdd}/>*/}
          {/*<Route path="/transfer/account" exact component={AsyncAccountList}/>*/}
          {/*<Route path="/member/vipcateList" exact component={AsyncVipcateList}/>*/}
          {/*<Route path="/member/vipuserList/:vcid?" exact component={AsyncVipuserList}/>*/}
          {/*<Route path="/member/qpaySetting" exact component={AsyncQpaySetting}/>*/}
          <Route path="/exception/404" exact component={AsyncNotFound}/>
          <Route path="/exception/500" exact component={AsyncError}/>
          <Route path="/exception/403" exact component={AsyncNoPermission}/>
          <Redirect from="*" to="/merchant/console" />
        </Switch>
      </Content>
    );
  }
}
