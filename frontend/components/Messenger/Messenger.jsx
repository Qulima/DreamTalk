import React from "react";
import {Avatar, Card, Space} from "antd";
import {compose} from "redux";
import {connect} from "react-redux";
import {getDialogs} from "../../selectors/MessageSelector";

const {Meta} = Card

const Messenger = (props) => {
  return (
    <Space>
      <Card style={{ borderRadius: 5 }}>
        {
          props.dialogs.map(d => {
            return (
              <Card style={{ width: 50 + 'vw', marginTop: 16 }}>
                <Meta
                  avatar={
                    <Avatar src={d.ava} size={"large"} />
                  }
                  title={d.name}
                  description={d.lastMessage}
                />
              </Card>
            )
          })
        }
      </Card>
    </Space>
  )
}
let mstp = (state) => {
  return {
    dialogs: state.dialogs.dialogs
  }
}

export default compose(
  connect(mstp, {})
)(Messenger)