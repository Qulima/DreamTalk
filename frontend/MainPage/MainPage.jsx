import {Breadcrumb, Layout, Menu} from "antd";
import {MessageOutlined, TeamOutlined, UserOutlined} from '@ant-design/icons'
import s from './MainPage.module.css'
import 'antd/dist/antd.css'
import React from "react";
import {Route} from "react-router";
import {NavLink} from "react-router-dom";
import LoginPage from "../components/Login/LoginPage";
import LogoutPage from "../components/Login/LogoutPage";


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;


function MainPage() {

  return (
    <Layout>

      <Header className="header">
        <div className={s.logo} />{/*<img src={logo}  alt={'logo'}/></div>*/}
        <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']} style={{float: 'right'}}>
          <Menu.Item key="1">
            <NavLink to={'/login'} >Login</NavLink>
          </Menu.Item>
          <Menu.Item key="2">
            <NavLink to={'/logout'} >Logout</NavLink>
          </Menu.Item>
        </Menu>
      </Header>
      <Content style={{ padding: '0 50px' }}>
        <Layout className="site-layout-background" style={{ padding: '24px 0' }}>
          <Sider className="site-layout-background" width={200}>
            <Menu
              mode="inline"
              defaultSelectedKeys={['1']}
              defaultOpenKeys={['sub1']}
              style={{ height: '100%' }}
            >
              <Menu.Item key="sub1" icon={<UserOutlined />} title="Profile page">Profile page</Menu.Item>
              <Menu.Item key="sub2" icon={<TeamOutlined />} title="Friends">Friends</Menu.Item>
              <Menu.Item key="sub3" icon={<MessageOutlined />} title="Messenger">Messenger</Menu.Item>
            </Menu>
          </Sider>
          <Content style={{ padding: '0 24px', minHeight: '75vh', overflow: 'auto' }}>
            Content and more
            <Route render={() => <LoginPage />} path={'/login'} />
            <Route render={() => <LogoutPage />} path={'/logout'} />
          </Content>
        </Layout>
      </Content>
      <Footer style={{ textAlign: 'center' }}>DreamTalk ©2021 Created by DT</Footer>
    </Layout>
  )
}

export default MainPage;
