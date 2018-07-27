import React from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom'
import {Menu, Icon, Layout, message, LocaleProvider} from 'antd'
import Top from './header'
import Contents from './content'
import Footer from './bottom'
import './index.less'
import {redirectLoginPage} from '../utils/auth';
import {
  getLocalObjectStorage, setLocalObjectStorage, setSessionObjectStorage,
  getSessionObjectStorage, clearLocalStorage, getLocalStorage
} from '../utils/helper'
import {isArray, isNotNull} from '../utils/common'
import history from '../history'
import enUS from 'antd/lib/locale-provider/en_US';
import zhCN from 'antd/lib/locale-provider/zh_CN';
import en_US from "../locale/en_US";
import zh_CN from "../locale/zh_CN";
import {IntlProvider} from 'react-intl';


const SubMenu = Menu.SubMenu;
const {Sider} = Layout

class Container extends React.Component {

  state = {
    theme: 'dark',
    selectCurrent: ['merchant/console'],//默认选中项
    collapsed: false,
    mode: 'inline',  // 水平垂直展现
    openKeys: ['My Account'],
    lang: this.props.userInfor
  };

  //获取所有菜单key值，用于展开一个菜单，将其他菜单收起
  rootSubmenuKeys = getLocalObjectStorage("menukeys");

  onOpenChange = (openKeys) => {
    let rootSubmenuKey = this.rootSubmenuKeys;
    if (rootSubmenuKey === null) {
      rootSubmenuKey = this.props.menuKeys;
    }

    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1);
    if (rootSubmenuKey.indexOf(latestOpenKey) === -1) {
      this.setState({openKeys});
    } else {
      this.setState({
        openKeys: latestOpenKey ? [latestOpenKey] : [],
      });
      if (isNotNull(latestOpenKey)) {
        setSessionObjectStorage("openKey", [latestOpenKey]);
      }
    }
  };

  onSelect = (item, key, selectdKeys) => {
    if (item === undefined) {
      return false;
    }
    const select = item.key;
    setSessionObjectStorage("selectKey", Array.of(select));
  }

  componentWillMount() {
    // console.log("======= container -> index.js -> willMount -> Hello");
    redirectLoginPage();

    //左边菜单栏赋值
    const menuStore = this.props.menuList;
    const menukeys = this.props.menuKeys;

    if (JSON.stringify(menuStore) !== '{}' && menuStore !== null) {
      setLocalObjectStorage('allMenu', menuStore);
    }
    if (JSON.stringify(menukeys) !== '[]' && menukeys !== null) {
      setLocalObjectStorage('menukeys', menukeys);
    }
    let openArr = getSessionObjectStorage("openKey");
    let selectArr = getSessionObjectStorage("selectKey");
    if (isArray(openArr)) {
      // console.log(openArr);
      this.setState({
        openKeys: openArr,
      });
    }
    if (isArray(selectArr)) {
      // console.log(selectArr);
      this.setState({
        selectCurrent: selectArr,
      });
    }
  }

  componentDidMount() {
    this.handleClick([], 'index');
  }

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
      mode: this.state.collapsed ? 'inline' : 'vertical',
      selectCurrent: [],
      openKeys: [],
    });
  }
  clear = () => {
    this.setState({
      current: 'index',
    });
  }
  handleClick = (e, special) => {
    this.setState({
      current: e.key || special,
    });
  }

  render() {
    let menu = getLocalObjectStorage('allMenu');

    let language = getLocalStorage("language");

    if (menu === null || menu === undefined) {
      menu = [];
      message.error("请重新登录，获取菜单权限！")
      sessionStorage.clear();
      clearLocalStorage();
      history.push("/login");
    }
    return (
      <LocaleProvider locale={language === 'en' ? enUS : zhCN}>
        <IntlProvider
          locale={'en'}
          messages={language === 'en' ? en_US : zh_CN}
        >
          <Layout className="containAll">
            <Sider
              collapsible
              collapsed={this.state.collapsed}
              onCollapse={this.onCollapse}
              className="leftMenu"
            >
              <a href="/merchant/console" rel='noopener noreferrer'><Icon type="github" className="github white"/></a>
              <span className="author white">商户系统</span>
              <Menu
                theme={this.state.theme}
                onClick={this.handleClick}
                openKeys={this.state.openKeys}
                defaultSelectedKeys={this.state.selectCurrent}
                className="menu"
                mode={this.state.mode}
                onOpenChange={this.onOpenChange}
                onSelect={this.onSelect}
              >
                {
                  menu.map((subMenu) => {
                    if (subMenu.children && subMenu.children.length) {
                      return (
                        <SubMenu key={subMenu.url} title={<span><Icon
                          type={subMenu.icon}/><span>{language === 'en' ? subMenu.url : subMenu.name}</span></span>}>
                          {subMenu.children.map(menu => (
                            <Menu.Item key={menu.url}><Link
                              to={`/${menu.url}`}>{language === 'en' ? menu.partnersMenusDto.menuNameEn : menu.name}</Link></Menu.Item>
                          ))}
                        </SubMenu>
                      )
                    }
                    return (
                      <Menu.Item key={subMenu.url}>
                        <Link to={`/${subMenu.url}`}>
                          <Icon type={subMenu.icon}/><span className="nav-text">{subMenu.name}</span>
                        </Link>
                      </Menu.Item>
                    )
                  })
                }
              </Menu>
            </Sider>
            <Layout>
              <Top toggle={this.toggle} collapsed={this.state.collapsed} clear={this.clear}/>
              <Contents/>
              <Footer/>
            </Layout>
          </Layout>
        </IntlProvider>
      </LocaleProvider>
    );
  }
}

export default connect((state, ownProps) => {
  return {
    menuList: state.menuList,
    userInfor: state.userInfor,
    menuKeys: state.menuKey,
  };
})(Container);
