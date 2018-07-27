import React, { createElement } from 'react';
import { Button } from 'antd';
import config from './typeConfig';
import './index.less'

export default ({ linkElement = 'a', type, title, desc, img, actions, ...rest }) => {
    const pageType = type in config ? type : '404';
    return (
        <div {...rest} className="exception">
            <div className="imgBlock">
                <div
                    className="imgEle"
                    style={{ backgroundImage: `url(${img || config[pageType].img})` }}
                />
            </div>
            <div className="content">
                <h1>{title || config[pageType].title}</h1>
                <div className="desc">{desc || config[pageType].desc}</div>
                <div>
                    {
                        actions ||
                        createElement(linkElement, {
                            to: '/',
                            href: '/',
                        }, <Button type="primary" style={{marginRight: '8px'}}>返回首页</Button>)
                    }
                </div>
            </div>
        </div>
    );
};
