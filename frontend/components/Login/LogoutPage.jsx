import {Button, Form, Input} from "antd";
import React from "react";
import axios from "axios";

const layout = {
  labelCol: {
    span: 8,
  },
  wrapperCol: {
    span: 16,
  },
};
const tailLayout = {
  wrapperCol: {
    offset: 8,
    span: 16,
  },
};


const LogoutPage = () => {

  const onFinish = (values) => {
    console.log('Success:', values);
    axios.post(`http://localhost:8080/api/v1/auth/logout`,
      {
      },
      {
        withCredentials: true
      })
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <Form
      {...layout}
      name="basic"
      initialValues={{ remember: true }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >

      <Form.Item
        name="_csrf"
        hidden={true}
        initialValue={'90242fa4-1337-4865-8136-f1a8612a0027'}
        rules={[{ required: true, message: 'Please input your username!' }]}
      >
        <Input />
      </Form.Item>

      <Form.Item {...tailLayout}>
        <Button type="primary" htmlType="submit">
          Logout
        </Button>
      </Form.Item>
    </Form>
  )
}

export default LogoutPage
