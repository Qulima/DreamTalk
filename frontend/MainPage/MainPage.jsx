import {Avatar, Card, Collapse, Layout, Menu, PageHeader, Space} from "antd";
import {
  BellOutlined,
  CommentOutlined,
  ContainerOutlined,
  CustomerServiceOutlined,
  PictureOutlined,
  TeamOutlined,
  UserOutlined
} from '@ant-design/icons'
import 'antd/dist/antd.css'
import React from "react";
import {Route, Switch} from "react-router";
import {NavLink} from "react-router-dom";
import LoginPage from "../components/Login/LoginPage";
import LogoutPage from "../components/Login/LogoutPage";
import Feed from "../components/Feed/Feed";
import Friends from "../components/Friends/Friends";
import Messenger from "../components/Messenger/Messenger";
import Music from "../components/Music/Music";
import Photos from "../components/Photos/Photos";
import Profile from "../components/Profile/Profile";
import SubMenu from "antd/es/menu/SubMenu";
import ava from '../assets/dream_talk_logo.png'
import Search from "antd/es/input/Search";

const { Header, Content, Footer, Sider } = Layout;
const { Panel } = Collapse


function MainPage() {

  return (
    <Layout>
      <Header className="site-page-header" style={{backgroundColor: '#fff'}}>
        <Space align={"center"} direction={"horizontal"} style={{padding: '0 50px'}}>
          <img width={35}
               style={{marginBottom: 5, borderRadius: 5}}
               src={'https://bcassetcdn.com/public/blog/wp-content/uploads/2019/07/18094848/kingfisher-2.png'}
               alt={'logo'}/>
          <Search width={200} style={{marginTop: 15, background: 'rgba(255, 255, 255, 0.3)'}} color={'#edeef0'}/>
          <BellOutlined style={{fontSize:20, padding: 10}}/>
          <CustomerServiceOutlined style={{fontSize:20, padding: 10}}/>
        </Space>
        <Menu theme="light" mode="horizontal" defaultSelectedKeys={['2']} style={{float: 'right'}}>
          <SubMenu
            title={<strong style={{padding: 10}}>Nick</strong>}
            icon={<Avatar
              src={'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png'}
              size={"large"}
              shape={"circle"}
              gap={10}
          />}>
            <Menu.Item key="3">
              <p>Settings</p>
            </Menu.Item>
            <Menu.Item key="4">
              <p>Help</p>
            </Menu.Item>
            <Menu.Item key="5">
              <NavLink to={'/logout'}>Logout</NavLink>
            </Menu.Item>
          </SubMenu>
        </Menu>
      </Header>
      <Content style={{ padding: '0 100px' }}>
        <Layout className="site-layout-background" style={{ padding: '24px 0' }}>
          <Sider className="site-layout-background" width={200} style={{ borderRadius: 6 }}>
            <Menu
              mode="inline"
              defaultSelectedKeys={['1']}
              defaultOpenKeys={['sub1']}
              style={{ height: '100%', borderRadius: 5 }}
            >
              <Menu.Item key="sub1" icon={<UserOutlined />} title="Profile page">
                <NavLink to={'profile'}>Profile</NavLink>
              </Menu.Item>

              <Menu.Item key="sub2" icon={<ContainerOutlined />} title="Feed">
                <NavLink to={'feed'}>Feed</NavLink>
              </Menu.Item>

              <Menu.Item key="sub3" icon={<CommentOutlined />} title="Messenger">
                <NavLink to={'messenger'}>Messenger</NavLink>
              </Menu.Item>

              <Menu.Item key="sub4" icon={<TeamOutlined />} title="Friends">
                <NavLink to={'friends'}>Friends</NavLink>
              </Menu.Item>

              <Menu.Item key="sub5" icon={<PictureOutlined />} title="Photos">
                <NavLink to={'photos'}>Photos</NavLink>
              </Menu.Item>

              <Menu.Item key="sub6" icon={<CustomerServiceOutlined />} title="Music">
                <NavLink to={'music'}>Music</NavLink>
              </Menu.Item>
            </Menu>
          </Sider>
          <Content style={{ padding: '0 24px', minHeight: '75vh', overflow: 'auto' }}>
            <Switch>
              <Route render={() => <Feed/>} path={'/feed'}/>
              <Route render={() => <Friends/>} path={'/friends'}/>
              <Route render={() => <Messenger/>} path={'/messenger'}/>
              <Route render={() => <Music/>} path={'/music'}/>
              <Route render={() => <Photos/>} path={'/photos'}/>
              <Route render={() => <Profile/>} path={'/profile'}/>
              <Route render={() => <LoginPage/>} path={'/login'} />
              <Route render={() => <LogoutPage/>} path={'/logout'} />
              <Route render={() => <Feed/>} path={'/*'} />
            </Switch>
          </Content>
        </Layout>
      </Content>
      <Footer style={{ textAlign: 'center' }}>DreamTalk ©2021 Created by DT</Footer>
    </Layout>
  )
}

export default MainPage;
