import React from "react";
import {Button, Card, Col, Menu, Row, Space} from "antd";
import {CalendarOutlined, HistoryOutlined} from "@ant-design/icons";
import ava from '../../assets/avatar.jpg'
import PhotoUploader from "../../utils/PhotoUploader";

const Profile = () => {


  return (
    <Space direction={'horizontal'} align={'start'} size={15}>
      <Space direction={'vertical'} size={15}>
        <Card style={{ borderRadius: 5 }}>
          <img src={ava} alt={'avatar'} style={{width: 250, borderRadius: 5}} align={'center'}/>
          {/*<Menu>*/}
          {/*<Menu.Item key={1}>Memories</Menu.Item>*/}
          {/*<Menu.Item key={2}>Story archive</Menu.Item>*/}
          {/*<Menu.Item key={3}>Money transfers</Menu.Item>*/}
          {/*</Menu>*/}
          <Button type={"text"}
                  style={{textAlign:"left", height:40}}
                  icon={<HistoryOutlined style={{fontSize:25}}/>}
                  block>Memories</Button>

          <Button type={"text"}
                  style={{textAlign:"left", height:40}}
                  icon={<CalendarOutlined style={{fontSize:25}}/>}
                  block>Story archive</Button>
        </Card>
        <Card style={{ width: 300, height: 200, borderRadius: 5}}>
          <p>
            UnderAva
          </p>
        </Card>
      </Space>
      <Space direction={'vertical'} size={15}>
        <Card>
          <h2>Nick</h2>
          <span>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit.
            Architecto at dolore eaque iste iusto omnis, quo saepe sunt totam veniam?
            Consectetur neque officia possimus totam vel voluptates voluptatibus!
            Molestias, vitae.
          </span>
          <hr/>
          <Row style={{marginTop:5}}>
            <Col span={8}>Birth:</Col>
            <Col span={16}>July 8, 1999</Col>
          </Row>
          <Row style={{marginTop:5}}>
            <Col span={8}>City</Col>
            <Col span={16}>Saint Petersburg</Col>
          </Row>
          <hr/>
          <Row style={{marginTop:10}}>
            <Col span={5} style={{padding:10}}>
              <Row justify={"center"}>1</Row>
              <Row justify={"center"}>friends</Row>
            </Col>

            <Col span={5} style={{padding:10}}>
              <Row justify={"center"}>1</Row>
              <Row justify={"center"}>followers</Row>
            </Col>

            <Col span={5} style={{padding:10}}>
              <Row justify={"center"}>1</Row>
              <Row justify={"center"}>photos</Row>
            </Col>

            <Col span={5} style={{padding:10}}>
              <Row justify={"center"}>1</Row>
              <Row justify={"center"}>videos</Row>
            </Col>

            <Col span={4} style={{padding:10}}>
              <Row justify={"center"}>1</Row>
              <Row justify={"center"}>tracks</Row>
            </Col>
          </Row>
        </Card>
        <Card style={{ borderRadius: 5}}>
          <p>
            My photos {1}
          </p>
          <PhotoUploader/>
        </Card>
      </Space>
    </Space>
  )
}



export default Profile