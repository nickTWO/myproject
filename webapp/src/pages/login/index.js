import React from 'react';
import {connect} from 'react-redux';
import { Link } from 'react-router-dom'
import {Form, Input, Button, message, Row, Col, Icon} from 'antd';
import {userLogin, partnerMenu, partnerKey, language} from '../../actions/login/index';
import utils from '../../axios/utils';
import CryptoJS from 'crypto-js';
import {getLocalObjectStorage, setLocalObjectStorage, setLocalStorage} from '../../utils/helper'
import {isNotNull, isObject} from '../../utils/common'

import './index.less'

const mapStateToProps = (state) => {
  return ({
      userInfor: state.userInfor
    }
  )
}

const mapDispatchToProps = (dispatch) => {
  return {
    userLogin: (data) => dispatch(userLogin(data)),
    menuList: (data) => dispatch(partnerMenu(data)),
    setMenuKey: (data) => dispatch(partnerKey(data)),
    language: (data) => dispatch(language(data)),
  }
}

const FormItem = Form.Item;

class LoginPage extends React.Component {

  constructor(props) {
    super();
    this.state = {
      token: '',
      isLoginClickable: true,
      isHiddenGAInput: true
    }
  }

  componentWillMount() {
    // 判断用户是否已经登录 authority
    const userInfo = getLocalObjectStorage("USER_INFO");
    if (!isObject(userInfo)) {
    } else {
      // 有 Storage 当前浏览器已经登陆过用户，不在进行登陆操作 直接进入首页
      this.fastLogin()
    }
  }

  //快速登陆
  fastLogin = () => {
    const result = utils.postData('api/v1/puser/fastLogin', {userClient: "1"});
    // console.log(result);
    this.handleFastResult(result)
  }

  handleFastResult = (result) => {
    result.then(value => {
      if (value.code === '200') {
        // 把接口得到的数据放入, store
        const menusList = value.entry.menusList;

        const {menuList, setMenuKey} = this.props;
        /*两个遍历不能放一起*/
        menusList.map((menuItem) => {
          setMenuKey(menuItem);
          return menuItem;
        });

        menusList.map((menuItem) => {
          menuList(menuItem);
          return menuItem;
        });

        message.success("快速登陆成功");
        const history = this.props.history;
        history.push('/merchant/console');
      }
    })
  }

  //登陆点击事件
  handleSubmit = (e) => {
    this.setState({isLoginClickable: false})
    e.preventDefault();

    this.props.form.validateFields((err, values) => {
      if (!err) {
        // console.log('Received values of form: ', values);
      }
    });
    let n = this.props.form.getFieldsValue().username;
    let p = this.props.form.getFieldsValue().password;
    let g = this.props.form.getFieldsValue().gacode;
    if (!isNotNull(n) || !isNotNull(p)) {
      this.setState({isLoginClickable: true})
      return
    }
    //请求登录接口
    let data = {
      appid: n,
      secret: CryptoJS.MD5(p).toString(),
      gacode: g,
    }
    // 管理员权限获取
    const result = utils.postData('api/v1/puser/userLogin', data)
    this.handleLoginResult(result)
  }

  //处理登陆
  handleLoginResult = (result) => {
    result.then(value => {
      if (value.code === '200') {
        const resp_data = value.entry;
        // 把接口得到的数据放入store
        const menusList = resp_data.menusList;
        const {menuList, setMenuKey, userLogin, language} = this.props;

        //localstorage====
        userLogin({type: 'USER_LOGIN', data: resp_data.token});//jwt
        userLogin({type: 'PARTNERID', data: resp_data.partnerId});
        userLogin({type: 'PUID', data: resp_data.puid});
        userLogin({type: 'USERNAME', data: resp_data.userName});
        userLogin({type: 'isAdmin', data: resp_data.isAdmin});
        userLogin({type: 'partnerType', data: resp_data.partnerType});
        language({type: 'LANGUAGE', data: 'cn'});

        const user = {
          token: resp_data.token,
          partnerId: resp_data.partnerId,
          puid: resp_data.puid,
          userName: resp_data.userName,
          isAdmin: resp_data.isAdmin,
          partnerType: resp_data.partnerType
        }

        setLocalObjectStorage('USER_INFO', user);
        setLocalStorage("language", 'cn');

        /*两个遍历不能放一起*/
        menusList.map((menuItem) => {
          setMenuKey(menuItem);
          return menuItem;
        });
        menusList.map((menuItem) => {
          menuList(menuItem);
          return menuItem;
        });

        message.success("登陆成功");
        const history = this.props.history;
        history.push('/merchant/console');
      } else if (value.code === "500") {
        message.error(value.resp_data);
        this.setState({isLoginClickable: true})
      } else {
        this.setState({
          isLoginClickable: true,
        });
        message.error(value.msg);
      }
    });
  }




  render() {
    const {getFieldDecorator} = this.props.form
    return (
      <div className="loginpagewrap">
        <div className="box">
          <Row>
            <Col span={7} offset={3}>
              <h1 className="loginH1">Welcome Back My Partners</h1>
              <Form onSubmit={this.handleSubmit}>
                <FormItem>
                  {getFieldDecorator('username', {
                    rules: [{required: true, message: '请输入邮箱账号'}],
                  })(
                    <Input placeholder="请输入邮箱账号" className="loginInput" prefix={<Icon type="user"/>}
                           style={{fontSize: '16px'}}/>
                  )}
                </FormItem>
                <FormItem>
                  {getFieldDecorator('password', {
                    rules: [{required: true, message: '请输入登录密码'}],
                  })(
                    <Input type="password" placeholder="请输入登录密码" className="loginInput" prefix={<Icon type="lock"/>}/>
                  )}
                </FormItem>
                {this.state.isHiddenGAInput === false &&
                <FormItem>
                  {getFieldDecorator('gacode', {
                    rules: [{required: true, message: '请输入GA码'},
                      {
                        trigger: "onChange", pattern: /^[0-9]+$/, message: "请输入正确GA验证码！"
                      }],
                  })(
                    <Input maxLength='6' placeholder="请输入GA码" className="loginInput" prefix={<Icon type="lock"/>}/>
                  )}
                </FormItem>
                }
                <br/>
                <Button type="primary" htmlType="submit" className="loginBtn" size="large" ghost
                        style={{width: '100%', height: '45px', fontSize: '20px'}}
                        disabled={!this.state.isLoginClickable}>
                  {this.state.isLoginClickable ? '登   录' : '正在登录中...'}
                </Button>
                <br/>
                <br/>
                <div className="expand">
                  <Link to="/forgot">忘记密码?</Link>
                  <Link to="/register">注册</Link>
                </div>
              </Form>
            </Col>
            <Col span={8}>

            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

let Login = Form.create()(LoginPage);
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login);
