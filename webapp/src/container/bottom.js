import React from 'react'
import { Layout } from 'antd'
import './bottom.less'

const { Footer } = Layout

export default class Bottom extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
        }
    }

    // 组件渲染后开始循环执行tick函数
    componentDidMount() {
    }

    render() {
        return (
            <Footer className="bottom animated bounceInLeft">
                <div className="text">
                    <div>
                        <span className="me">Copyright  2017 Online Payment Service Platform</span>
                    </div>
                </div>
            </Footer>
        );
    }
}
