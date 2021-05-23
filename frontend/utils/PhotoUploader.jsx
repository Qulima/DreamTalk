import React, {useState} from "react";
import {Button, Upload} from "antd";
import ImgCrop from 'antd-img-crop'
import {UploadOutlined} from "@ant-design/icons";
import axios from "axios";

const PhotoUploader = () => {

  const [uploading, setUploading] = useState(true)

  const [fileList, setFileList] = useState([
    {
      uid: '-1',
      name: 'image.png',
      status: 'done',
      url: null
    }
  ])

  const onChange = ({fileList: newFileList}) => {
    setFileList(newFileList)
  }

  const onPreview = async file => {
    let src = file.url
    if (!src) {
      src = await new Promise(resolve => {
        const reader = new FileReader()
        reader.readAsDataURL(file.originFileObj)
        reader.onload = () => resolve(reader.result)
      })
    }
    const image = new Image();
    image.src = src
    const imgWindow = window.open(src)
    imgWindow.document.write(image.outerHTML)
  }

  const handleUpload = () => {
    axios.post('http://localhost:8080/api/v1/upload', {fileList})
  }

  return (
    <ImgCrop rotate>
      <Upload
        action='http://localhost:8080/api/v1/task'
        method={"POST"}
        listType='picture-card'
        withCredentials={true}
        fileList={fileList}
        onChange={onChange}
        onPreview={onPreview}
        >
        {fileList.length < 5 && '+ Upload'}
      </Upload>
      <Button
        type='primary'
        onClick={handleUpload}
        disabled={fileList.length === 0}
        style={{marginTop: 16}}
        >Upload</Button>
    </ImgCrop>
  )
}

export default PhotoUploader