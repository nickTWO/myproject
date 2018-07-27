import React from 'react'
import { Menu, Icon, Layout, Select } from 'antd'
import { Link } from 'react-router-dom'
import './header.less'
import { logOut } from '../utils/auth';
import { setLocalStorage, getLocalObjectStorage, getLocalStorage } from '../utils/helper';
import { isObject } from '../utils/common';

import { language } from "../actions/login/index";
import { connect } from "react-redux";


const SubMenu = Menu.SubMenu
const { Header } = Layout

const Option = Select.Option;

class Top extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            username: ''
        }
    }

    componentDidMount() {
        this.getUser()
    }

    getUser = () => {
        const userInfo = getLocalObjectStorage("USER_INFO");
        if (!isObject(userInfo)){
            return;
        }
        let user = userInfo.partnerId + "/" +userInfo.userName;
        this.setState({
            username: user
        })

    }

    clear = (item) => {
        if (item.key === 'logOut') {
            this.props.clear();
            logOut();
        }
    }

    /*中英文切换*/
    changeLocale = (value) => {
        const localeValue = value;
        if (localeValue === 'cn') {
            setLocalStorage("language", localeValue);
            const { language } = this.props;
            language({ type: 'LANGUAGE',   data: "cn"});
        } else if (localeValue === 'en') {
            setLocalStorage("language", localeValue);
            const { language } = this.props;
            language({ type: 'LANGUAGE',   data: "en"});

        }
    }

    render() {
        let language = getLocalStorage("language");
        return (
            <Header style={{ background: '#fff'}}>
                <Icon
                    className="trigger"
                    type={this.props.collapsed ? 'menu-unfold' : 'menu-fold'}
                    onClick={this.props.toggle}
                />
                <Menu mode="horizontal" className="logOut" onClick={this.clear}>
                    <SubMenu title={<span><Icon type="user" />{ this.state.username }</span>} >
                        <Menu.Item key="logOut"><Link to="/login" >退出</Link></Menu.Item>
                    </SubMenu>

                    <Select onChange={(e)=>this.changeLocale(e)} defaultValue={ language ? language : 'cn' }>
                        <Option value="cn">中文</Option>
                        <Option value="en">English</Option>
                    </Select>
                </Menu>
            </Header>
        );
    }
}

// export default  Top
export default connect((state, ownProps) => {
    return {
        menuList: state.menuList,
        userInfor: state.userInfor,
        menuKeys: state.menuKey,
    };
},(dispatch) => {
    return {
        language: (data) => dispatch(language(data)),
    }
})(Top);


